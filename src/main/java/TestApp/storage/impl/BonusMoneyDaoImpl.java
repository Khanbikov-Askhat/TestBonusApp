package TestApp.storage.impl;

import TestApp.exceptions.DataNotFoundException;
import TestApp.mapper.BonusMoneyMapper;
import TestApp.storage.sqloperation.BonusMoneySqlOperation;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import TestApp.model.BonusMoney;
import TestApp.storage.dao.BonusMoneyDao;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class BonusMoneyDaoImpl implements BonusMoneyDao {

    private final JdbcTemplate jdbcTemplate;
    public BonusMoneyDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private static final String BONUS_TABLE_NAME = "bonus";
    private static final String BONUS_TABLE_ID_COLUMN_NAME = "bonus_id";

    @Override
    public Optional<BonusMoney> save(BonusMoney bonusMoney) {
        String sqlQuery = "INSERT INTO bonus (account_id) VALUES (? )";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        try {
            jdbcTemplate.update(connection -> {
                PreparedStatement statement = connection.prepareStatement(sqlQuery, new String[]{"bonus_id"});
                statement.setLong(1, bonusMoney.getAccountId());

                return statement;
            }, keyHolder);

        } catch (DataIntegrityViolationException e) {
            throw new DataNotFoundException(e.getMessage());
        }

        bonusMoney.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
        return Optional.of(bonusMoney);
    }
    @Override
    public List<BonusMoney> getBonus() {
        return jdbcTemplate.query(BonusMoneySqlOperation.GET_ALL_BONUS_MONEY.getTitle(), new BonusMoneyMapper());
    }

    @Override
    public Optional<BonusMoney> update(BonusMoney bonusMoney) {
        jdbcTemplate.update(BonusMoneySqlOperation.UPDATE_BONUS.getTitle(),
                bonusMoney.getBonusAmount(),
                bonusMoney.getId());
        return Optional.of(bonusMoney);
    }

    @Override
    public Optional<BonusMoney> getBonusById(Long id) {
        try {
            return Optional.ofNullable(
                    jdbcTemplate.queryForObject(BonusMoneySqlOperation.GET_BONUS_BY_BONUS_ID.getTitle(), new BonusMoneyMapper(), id));
        } catch (DataAccessException e) {
            throw new DataNotFoundException("Бонус не найден" + e.getMessage());
        }
    }

    private void bonusInsertAndSetId(BonusMoney bonusMoney) {
        SimpleJdbcInsert simpleJdbcInsert = getBonusSimpleJdbcInsert();
        long accountId = simpleJdbcInsert.executeAndReturnKey(bonusMoney.toMap()).longValue();
            bonusMoney.setId(accountId);
    }

    private SimpleJdbcInsert getBonusSimpleJdbcInsert() {
        return new SimpleJdbcInsert(jdbcTemplate)
                .withTableName(BONUS_TABLE_NAME)
                .usingGeneratedKeyColumns(BONUS_TABLE_ID_COLUMN_NAME);
    }
}
