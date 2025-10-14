package ru.iprody.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.iprody.mapper.PaymentMapper;
import ru.iprody.model.PaymentDto;
import ru.iprody.model.PaymentFilter;
import ru.iprody.persistence.entity.Payment;
import ru.iprody.persistence.repository.PaymentRepository;
import ru.iprody.persistence.repository.PaymentSpecification;
import ru.iprody.service.PaymentService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    @Override
    public PaymentDto findByPaymentId(UUID paymentId) {
        return paymentRepository.findById(paymentId)
                .map(paymentMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Не найден платёж " + paymentId));
    }

    @Override
    public List<PaymentDto> findAll() {
        return paymentRepository.findAll().stream()
                .map(paymentMapper::toDto)
                .toList();
    }

    @Override
    public List<PaymentDto> search(PaymentFilter filter) {
        final Specification<Payment> spec = PaymentSpecification.filterBy(filter);
        return paymentRepository.findAll(spec).stream()
                .map(paymentMapper::toDto)
                .toList();
    }

    @Override
    public Page<PaymentDto> searchPaged(PaymentFilter filter, Pageable pageable) {
        final Specification<Payment> spec = PaymentSpecification.filterBy(filter);
        return paymentRepository.findAll(spec, pageable)
                .map(paymentMapper::toDto);
    }
}
