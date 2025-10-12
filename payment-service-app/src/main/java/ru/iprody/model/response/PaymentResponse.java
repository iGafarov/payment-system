package ru.iprody.model.response;

import lombok.Builder;
import ru.iprody.persistence.entity.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record PaymentResponse(
    UUID guid,
    BigDecimal amount,
    String currency,
    PaymentStatus status,
    UUID inquiryRefId,
    UUID transactionRefId,
    String note,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {
}