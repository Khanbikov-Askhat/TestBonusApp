package TestApp.mapper;

import org.springframework.jdbc.core.RowMapper;
import TestApp.model.BonusMoney;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BonusMoneyMapper implements RowMapper<BonusMoney> {

    @Override
    public BonusMoney mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return BonusMoney.builder()
                .id(resultSet.getLong("bonus_id"))
                .bonusAmount(resultSet.getDouble("bonus_amount"))
                .accountId(resultSet.getLong("account_id"))
                .build();
    }
}
