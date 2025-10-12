package ru.iprody.persistence.repository;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import ru.iprody.model.PaymentFilterDTO;
import ru.iprody.persistence.entity.Payment;
import ru.iprody.persistence.entity.Payment_;

import java.util.ArrayList;
import java.util.List;

public class PaymentSpecification {

    public static Specification<Payment> filterBy(PaymentFilterDTO filter) {
        return ((root, query, builder) -> {
            final List<Predicate> predicates = new ArrayList<>();

            if (filter.guid() != null) {
                predicates.add(builder.equal(root.get(Payment_.guid), filter.guid()));
            }

            if (filter.paymentStatus() != null) {
                predicates.add(builder.equal(root.get(Payment_.status), filter.paymentStatus()));
            }

            if (filter.minAmount() != null && filter.maxAmount() != null) {
                predicates.add(builder.between(root.get(Payment_.amount), filter.minAmount(), filter.maxAmount()));
            }

            if (filter.minAmount() != null) {
                predicates.add(builder.gt(root.get(Payment_.amount), filter.minAmount()));
            }

            if (filter.maxAmount() != null) {
                predicates.add(builder.lt(root.get(Payment_.amount), filter.maxAmount()));
            }

            if (filter.createdAfter() != null && filter.createdBefore() != null) {
                predicates.add(builder.between(
                    root.get(Payment_.createdAt),
                    filter.createdAfter(),
                    filter.createdBefore())
                );
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        });
    }
}
