package TestApp.mapper;

import org.springframework.jdbc.core.RowMapper;
import TestApp.model.Payment;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentMapper implements RowMapper<Payment> {

    @Override
    public Payment mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return Payment.builder()
                .id(resultSet.getLong("bonus_id"))
                .accountId(resultSet.getLong("account_id"))
                .operationType(resultSet.getString("operation_type"))
                .bonusId(resultSet.getLong("bonus_id"))
                .operationAmount(resultSet.getDouble("operation_amount"))
                .build();
    }
}