package TestApp.storage.dao;

import TestApp.model.BankAccount;

import java.util.List;
import java.util.Optional;

public interface BankAccountDao {
    Optional<BankAccount> save(BankAccount bankAccount);
    List<BankAccount> getMoney();
    Optional<BankAccount> update(BankAccount bankAccount);
    Optional<BankAccount> getAccountById(Long id);
}
