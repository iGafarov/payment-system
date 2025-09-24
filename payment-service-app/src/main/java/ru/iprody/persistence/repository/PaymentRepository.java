package ru.iprody.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.iprody.persistence.entity.Payment;
import ru.iprody.persistence.entity.PaymentStatus;

import java.util.UUID;
import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, UUID> {

    List<Payment> findByStatus(PaymentStatus status);
}
