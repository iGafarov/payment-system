package ru.iprody.model;

import ru.iprody.persistence.entity.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record PaymentFilterDTO (
    UUID guid,
    BigDecimal minAmount,
    BigDecimal maxAmount,
    PaymentStatus paymentStatus,
    LocalDateTime createdAfter,
    LocalDateTime createdBefore
) {
}