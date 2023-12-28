package TestApp.storage.impl;

import TestApp.exceptions.DataNotFoundException;
import TestApp.mapper.PaymentMapper;
import TestApp.storage.sqloperation.PaymentSqlOperation;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import TestApp.model.Payment;
import TestApp.storage.dao.PaymentDao;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository
public class PaymentDaoImpl implements PaymentDao {

    private final JdbcTemplate jdbcTemplate;


    public PaymentDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private static final String PAYMENT_TABLE_NAME = "payments";
    private static final String PAYMENT_TABLE_ID_COLUMN_NAME = "payment_id";
    @Override
    public List<Payment> getPayment() {
        return jdbcTemplate.query(PaymentSqlOperation.GET_ALL_PAYMENT.getTitle(), new PaymentMapper());
    }

    @Override
    public Optional<Payment> update(Payment payment) {
        jdbcTemplate.update(PaymentSqlOperation.UPDATE_PAYMENT.getTitle(),
                payment.getOperationAmount(),
                payment.getId());
        return Optional.of(payment);
    }

    @Override
    public Optional<Payment> getPaymentById(Long id) {
        try {
            return Optional.ofNullable(
                    jdbcTemplate.queryForObject(PaymentSqlOperation.GET_PAYMENT_BY_PAYMENT_ID.getTitle(), new PaymentMapper(), id));
        } catch (DataAccessException e) {
            throw new DataNotFoundException("Оплата не найдена" + e.getMessage());
        }
    }

    @Override
    public Optional<Payment> save(Payment payment) {
        String sqlQuery = "INSERT INTO payments (operation_amount, account_id, bonus_id, operation_type) VALUES (?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        try {
            jdbcTemplate.update(connection -> {
                PreparedStatement statement = connection.prepareStatement(sqlQuery, new String[]{"payment_id"});
                statement.setDouble(1, payment.getOperationAmount());
                statement.setLong(2, payment.getAccountId());
                statement.setLong(3, payment.getBonusId());
                statement.setString(4, payment.getOperationType());
                return statement;
            }, keyHolder);

        } catch (DataIntegrityViolationException e) {
            throw new DataNotFoundException(e.getMessage());
        }
        return Optional.of(payment);
    }

    private void paymentInsertAndSetId(Payment payment) {
        SimpleJdbcInsert simpleJdbcInsert = getPaymentSimpleJdbcInsert();
        long paymentId = simpleJdbcInsert.executeAndReturnKey(payment.toMap()).longValue();
        payment.setId(paymentId);
    }

    private SimpleJdbcInsert getPaymentSimpleJdbcInsert() {
        return new SimpleJdbcInsert(jdbcTemplate)
                .withTableName(PAYMENT_TABLE_NAME)
                .usingGeneratedKeyColumns(PAYMENT_TABLE_ID_COLUMN_NAME);
    }
}
