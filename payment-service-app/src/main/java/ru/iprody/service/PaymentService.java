package ru.iprody.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.iprody.model.PaymentDto;
import ru.iprody.model.PaymentFilter;

import java.util.List;
import java.util.UUID;

public interface PaymentService {

    PaymentDto findByPaymentId(UUID paymentId);

    List<PaymentDto> findAll();

    List<PaymentDto> search(PaymentFilter filter);

    Page<PaymentDto> searchPaged(PaymentFilter filter, Pageable pageable);
}
