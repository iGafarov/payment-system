package ru.iprody.persistence.repository;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import ru.iprody.model.PaymentFilterDTO;
import ru.iprody.persistence.entity.Payment;

import java.util.ArrayList;
import java.util.List;

public class PaymentSpecification {

    public static Specification<Payment> filterBy(PaymentFilterDTO filter) {
        return ((root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.getGuid() != null) {
                predicates.add(builder.equal(root.get("guid"), filter.getGuid()));
            }

            if (filter.getPaymentStatus() != null) {
                predicates.add(builder.equal(root.get("status"), filter.getPaymentStatus()));
            }

            if (filter.getMinAmount() != null && filter.getMaxAmount() != null) {
                predicates.add(builder.between(root.get("amount"), filter.getMinAmount(), filter.getMaxAmount()));
            }

            if (filter.getMinAmount() != null) {
                predicates.add(builder.gt(root.get("amount"), filter.getMinAmount()));
            }

            if (filter.getMaxAmount() != null) {
                predicates.add(builder.lt(root.get("amount"), filter.getMaxAmount()));
            }

            if (filter.getCreatedAfter() != null && filter.getCreatedBefore() != null) {
                predicates.add(builder.between(root.get("createdAt"), filter.getCreatedAfter(), filter.getCreatedBefore()));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        });
    }
}
