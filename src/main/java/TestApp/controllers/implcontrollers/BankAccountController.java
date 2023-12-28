package TestApp.controllers.implcontrollers;

import TestApp.model.BankAccount;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import TestApp.service.implservice.BankAccountService;

import javax.validation.constraints.Min;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/accounts")
@Slf4j
public class BankAccountController {
    private final BankAccountService accountService;

    public BankAccountController(BankAccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public List<BankAccount> getMoney() {
        log.info("Получен GET-запрос /accounts");
        List<BankAccount> foundedAccounts = accountService.getMoney();
        log.info("Отправлен ответ на GET-запрос /accounts с телом: {}", foundedAccounts);
        return foundedAccounts;
    }

    @GetMapping("/{accountId}/money")
    public double findById(@PathVariable("accountId") @Min(0) Long accountId) {
        log.info("Получен GET-запрос /accounts/{}", accountId);
        Optional<BankAccount> foundedAccount = accountService.getAccountById(accountId);
        log.info("Отправлен ответ на GET-запрос /accounts/{} с телом: {}", accountId, foundedAccount);
        return foundedAccount.get().getAmount();
    }

    @GetMapping("/addAccount")
    public Optional<BankAccount> save(@RequestBody BankAccount bankAccount) {
        log.info("Получен GET-запрос /addAccount c телом: {}", bankAccount);
        Optional<BankAccount> addedAccount = accountService.save(bankAccount);
        log.info("Отправлен ответ на GET-запрос /addAccount c телом: {}", addedAccount);
        return addedAccount;
    }
}
