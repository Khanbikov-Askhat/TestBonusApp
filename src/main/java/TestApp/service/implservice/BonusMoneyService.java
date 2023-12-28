package TestApp.service.implservice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import TestApp.model.BonusMoney;
import TestApp.storage.dao.BonusMoneyDao;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class BonusMoneyService {
    private BonusMoneyDao bonusMoneyDao;
    public List<BonusMoney> getBonus() {
        return bonusMoneyDao.getBonus();
    }

    public Optional<BonusMoney> getBonusById(Long Id) {
        return bonusMoneyDao.getBonusById(Id);
    }

    public Optional<BonusMoney> save(BonusMoney bonusMoney) {
        return bonusMoneyDao.save(bonusMoney);
    }

    public Optional<BonusMoney> update(BonusMoney bonusMoney) {
        return bonusMoneyDao.update(bonusMoney);
    }

    @Autowired
    @Qualifier("bonusMoneyDaoImpl")
    public void setBonusMoneyDao(BonusMoneyDao bonusStorage) {
        this.bonusMoneyDao = bonusStorage;
    }
}
