package TestApp.controllers.implcontrollers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import TestApp.model.Payment;
import TestApp.service.implservice.PaymentService;

import javax.validation.constraints.Min;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/payment")
@Slf4j
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping
    public List<Payment> getPayment() {
        log.info("Получен GET-запрос /bonus");
        List<Payment> foundedPayment = paymentService.getPayment();
        log.info("Отправлен ответ на GET-запрос /bonus с телом: {}", foundedPayment);
        return foundedPayment;
    }

    @GetMapping("/{paymentId}")
    public Optional<Payment> findById(@PathVariable("paymentId") @Min(0) Long paymentId) {
        log.info("Получен GET-запрос /bonus/{}", paymentId);
        Optional<Payment> foundedPayment = paymentService.getPaymentById(paymentId);
        log.info("Отправлен ответ на GET-запрос /bonus/{} с телом: {}", paymentId, foundedPayment);
        return foundedPayment;
    }

    @GetMapping("/{operationType}/{amount}")
    public Optional<Payment> makeOperation(@PathVariable("operationType") String operationType,
                                              @PathVariable("amount") @Min(0) Double amount,
                                              @RequestBody Payment payment) {
        log.info("Получен GET-запрос /{operationType}/{amount}", operationType, amount);
        payment.setOperationType(operationType);
        payment.setOperationAmount(amount);
        return paymentService.save(payment);
    }

}
