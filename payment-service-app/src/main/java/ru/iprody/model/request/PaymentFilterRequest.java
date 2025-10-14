package ru.iprody.model.request;

import lombok.Builder;
import ru.iprody.persistence.entity.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record PaymentFilterRequest(
    UUID guid,
    BigDecimal minAmount,
    BigDecimal maxAmount,
    PaymentStatus paymentStatus,
    LocalDateTime createdAfter,
    LocalDateTime createdBefore
) {
}