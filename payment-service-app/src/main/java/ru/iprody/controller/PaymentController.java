package ru.iprody.controller;

import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.iprody.model.Payment;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final Map<Long, Payment> payments;

    public PaymentController(Map<Long, Payment> payments) {
        this.payments = payments;
    }

    @GetMapping
    public List<Payment> getPayments() {
        return payments.values().stream().toList();
    }

    @GetMapping("{paymentId}")
    public Payment getPayment(@PathVariable Long paymentId) {
        return payments.get(paymentId);
    }

    @PostConstruct
    private void initializePayments() {
        payments.put(1L, new Payment(1, 1.1));
        payments.put(2L, new Payment(2, 2.2));
        payments.put(3L, new Payment(3, 3.3));
        payments.put(4L, new Payment(4, 4.4));
        payments.put(5L, new Payment(5, 5.5));
    }
}
