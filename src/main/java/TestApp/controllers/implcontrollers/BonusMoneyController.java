package TestApp.controllers.implcontrollers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import TestApp.model.BonusMoney;
import TestApp.service.implservice.BonusMoneyService;

import javax.validation.constraints.Min;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bonus")
@Slf4j
public class BonusMoneyController {
    private final BonusMoneyService bonusService;


    public BonusMoneyController(BonusMoneyService bonusService) {
        this.bonusService = bonusService;
    }

    @GetMapping
    public List<BonusMoney> getMoney() {
        log.info("Получен GET-запрос /bonus");
        List<BonusMoney> foundedBonus = bonusService.getBonus();
        log.info("Отправлен ответ на GET-запрос /bonus с телом: {}", foundedBonus);
        return foundedBonus;
    }

    @GetMapping("/{bonusId}/bankAccountOfEMoney")
    public double findById(@PathVariable("bonusId") @Min(0) Long bonusId) {
        log.info("Получен GET-запрос /bonus/{}", bonusId);
        Optional<BonusMoney> foundedBonus = bonusService.getBonusById(bonusId);
        log.info("Отправлен ответ на GET-запрос /bonus/{} с телом: {}", bonusId, foundedBonus);
        return foundedBonus.get().getBonusAmount();
    }

    @GetMapping("/addBonus")
    public Optional<BonusMoney> save(@RequestBody BonusMoney bonusMoney) {
        log.info("Получен GET-запрос /addAccount c телом: {}", bonusMoney);
        Optional<BonusMoney> addedBonus = bonusService.save(bonusMoney);
        log.info("Отправлен ответ на GET-запрос /addAccount c телом: {}", addedBonus);
        return addedBonus;
    }
}
