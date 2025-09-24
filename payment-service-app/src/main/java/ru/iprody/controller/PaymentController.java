package ru.iprody.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.iprody.persistence.entity.Payment;
import ru.iprody.persistence.repository.PaymentRepository;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentRepository paymentRepository;

    public PaymentController(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @GetMapping
    public List<Payment> getPayments() {
        return paymentRepository.findAll();
    }

    @GetMapping("{paymentId}")
    public Payment getPayment(@PathVariable UUID paymentId) {
        return paymentRepository.findById(paymentId).orElse(null);
    }
}
