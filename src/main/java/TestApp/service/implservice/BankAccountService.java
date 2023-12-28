package TestApp.service.implservice;

import TestApp.model.BankAccount;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import TestApp.storage.dao.BankAccountDao;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class BankAccountService {
    private BankAccountDao bankAccountDao;
    public List<BankAccount> getMoney() {
        return bankAccountDao.getMoney();
    }


    public Optional<BankAccount> getAccountById(Long Id) {
        return bankAccountDao.getAccountById(Id);
    }

    public Optional<BankAccount> save(BankAccount bankAccount) {
        return bankAccountDao.save(bankAccount);
    }

    public Optional<BankAccount> update(BankAccount bankAccount) {
        return bankAccountDao.update(bankAccount);
    }

    @Autowired
    @Qualifier("bankAccountDaoImpl")
    public void setAccountDao(BankAccountDao accountStorage) {
        this.bankAccountDao = accountStorage;
    }
}
