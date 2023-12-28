package TestApp.service.implservice;

import TestApp.model.BankAccount;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import TestApp.model.BonusMoney;
import TestApp.model.Payment;
import TestApp.storage.dao.BankAccountDao;
import TestApp.storage.dao.BonusMoneyDao;
import TestApp.storage.dao.PaymentDao;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentService {
    private PaymentDao paymentDao;
    private BankAccountDao bankAccountDao;
    private BonusMoneyDao bonusMoneyDao;
    public List<Payment> getPayment() {
        return paymentDao.getPayment();
    }

    public Optional<Payment> getPaymentById(Long Id) {
        return paymentDao.getPaymentById(Id);
    }

    public Optional<Payment> save(Payment payment) {
        BankAccount foundedAccount = bankAccountDao.getAccountById(payment.getAccountId()).get();
        BonusMoney foundedBonus = bonusMoneyDao.getBonusById(payment.getBonusId()).get();
        foundedAccount.setAmount(foundedAccount.getAmount() - payment.getOperationAmount());
        if (payment.getOperationType().equals("Shop")) {
            foundedBonus.setBonusAmount(foundedBonus.getBonusAmount() + payment.shopOperation());
        } else {
            foundedBonus.setBonusAmount(foundedBonus.getBonusAmount() + payment.onlineOperation());
        }
        bankAccountDao.update(foundedAccount);
        bonusMoneyDao.update(foundedBonus);
        return paymentDao.save(payment);
    }

    @Autowired
    @Qualifier("bonusMoneyDaoImpl")
    public void setBonusMoneyDao(BonusMoneyDao bonusStorage) {
        this.bonusMoneyDao = bonusStorage;
    }

    @Autowired
    @Qualifier("bankAccountDaoImpl")
    public void setAccountDao(BankAccountDao accountStorage) {
        this.bankAccountDao = accountStorage;
    }

    @Autowired
    @Qualifier("paymentDaoImpl")
    public void setPaymentDao(PaymentDao paymentStorage) {
        this.paymentDao = paymentStorage;
    }
}
