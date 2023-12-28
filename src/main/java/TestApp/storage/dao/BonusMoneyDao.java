package TestApp.storage.dao;

import TestApp.model.BonusMoney;

import java.util.List;
import java.util.Optional;

public interface BonusMoneyDao {
    Optional<BonusMoney> save(BonusMoney bonusMoney);
    List<BonusMoney> getBonus();
    Optional<BonusMoney> update(BonusMoney bonusMoney);
    Optional<BonusMoney> getBonusById(Long id);
}
