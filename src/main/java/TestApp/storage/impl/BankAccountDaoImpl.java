package TestApp.storage.impl;

import TestApp.exceptions.DataNotFoundException;
import TestApp.mapper.BankAccountMapper;
import TestApp.model.BankAccount;
import TestApp.storage.dao.BankAccountDao;
import TestApp.storage.sqloperation.BankAccountSqlOperation;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class BankAccountDaoImpl implements BankAccountDao {
    private final JdbcTemplate jdbcTemplate;

    public BankAccountDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private static final String ACCOUNT_TABLE_NAME = "accounts";
    private static final String ACCOUNT_TABLE_ID_COLUMN_NAME = "account_id";

    @Override
    public List<BankAccount> getMoney() {
        return jdbcTemplate.query(BankAccountSqlOperation.GET_ALL_BANK_ACCOUNT.getTitle(), new BankAccountMapper());
    }

    @Override
    public Optional<BankAccount> save(BankAccount bankAccount) {
        String sqlQuery = "INSERT INTO accounts (account_amount) VALUES (?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        try {
            jdbcTemplate.update(connection -> {
                PreparedStatement statement = connection.prepareStatement(sqlQuery, new String[]{"account_id"});
                statement.setDouble(1, bankAccount.getAmount());

                return statement;
            }, keyHolder);

        } catch (DataIntegrityViolationException e) {
            throw new DataNotFoundException(e.getMessage());
        }

        bankAccount.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
        return Optional.of(bankAccount);
    }

    @Override
    public Optional<BankAccount> update(BankAccount bankAccount) {
        jdbcTemplate.update(BankAccountSqlOperation.UPDATE_ACCOUNT.getTitle(),
                bankAccount.getAmount(),
                bankAccount.getId());
        return Optional.of(bankAccount);
    }

    @Override
    public Optional<BankAccount> getAccountById(Long id) {
        try {
            return Optional.ofNullable(
                    jdbcTemplate.queryForObject(BankAccountSqlOperation.GET_ACCOUNT_BY_ACCOUNT_ID.getTitle(), new BankAccountMapper(), id));
        } catch (DataAccessException e) {
            throw new DataNotFoundException("Счет не найден" + e.getMessage());
        }
    }

    private void accountInsertAndSetId(BankAccount bankAccount) {
        SimpleJdbcInsert simpleJdbcInsert = getBankAccountSimpleJdbcInsert();
        long accountId = simpleJdbcInsert.executeAndReturnKey(bankAccount.toMap()).longValue();
        bankAccount.setId(accountId);
    }

    private SimpleJdbcInsert getBankAccountSimpleJdbcInsert() {
        return new SimpleJdbcInsert(jdbcTemplate)
                .withTableName(ACCOUNT_TABLE_NAME)
                .usingGeneratedKeyColumns(ACCOUNT_TABLE_ID_COLUMN_NAME);
    }
}
