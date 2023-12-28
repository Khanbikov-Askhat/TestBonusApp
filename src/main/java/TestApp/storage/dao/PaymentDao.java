package TestApp.storage.dao;

import TestApp.model.Payment;

import java.util.List;
import java.util.Optional;

public interface PaymentDao {
    Optional<Payment> save(Payment payment);
    List<Payment> getPayment();
    Optional<Payment> update(Payment payment);
    Optional<Payment> getPaymentById(Long id);
}
