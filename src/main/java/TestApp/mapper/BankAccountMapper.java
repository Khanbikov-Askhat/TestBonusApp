package TestApp.mapper;

import TestApp.model.BankAccount;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BankAccountMapper implements RowMapper<BankAccount> {

    @Override
    public BankAccount mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return BankAccount.builder()
                .id(resultSet.getLong("account_id"))
                .amount(resultSet.getDouble("account_amount"))
                .build();
    }
}
