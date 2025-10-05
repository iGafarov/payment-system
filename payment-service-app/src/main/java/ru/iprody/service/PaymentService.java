package ru.iprody.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.iprody.model.PaymentFilterDTO;
import ru.iprody.persistence.entity.Payment;
import ru.iprody.persistence.repository.PaymentRepository;
import ru.iprody.persistence.repository.PaymentSpecification;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Optional<Payment> findByPaymentId(UUID paymentId) {
        return paymentRepository.findById(paymentId);
    }

    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }

    public List<Payment> search(PaymentFilterDTO filter) {
        final Specification<Payment> spec = PaymentSpecification.filterBy(filter);
        return paymentRepository.findAll(spec);
    }

    public Page<Payment> searchPaged(PaymentFilterDTO filter, Pageable pageable) {
        final Specification<Payment> spec = PaymentSpecification.filterBy(filter);
        return paymentRepository.findAll(spec, pageable);
    }
}
