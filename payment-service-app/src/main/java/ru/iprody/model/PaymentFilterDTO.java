package ru.iprody.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.iprody.persistence.entity.PaymentStatus;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PaymentFilterDTO {
    private UUID guid;
    private BigDecimal minAmount;
    private BigDecimal maxAmount;
    private PaymentStatus paymentStatus;
    private Instant createdAfter;
    private Instant createdBefore;
}
