package com.bhavan;

import com.bhavan.model.OrderDetails;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderSpecification {

    public static Specification<OrderDetails> searchByCriteria(Map<String, String> params) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (params.containsKey("orderId") && !params.get("orderId").isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("orderId"), Long.valueOf(params.get("orderId"))));
            }

            if (params.containsKey("status") && !params.get("status").isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("status"), params.get("status")));
            }

            if (params.containsKey("fromDate") && !params.get("fromDate").isEmpty()) {
                LocalDate fromDate = LocalDate.parse(params.get("fromDate"));
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createdOn"), fromDate.atStartOfDay()));
            }

            if (params.containsKey("toDate") && !params.get("toDate").isEmpty()) {
                LocalDate toDate = LocalDate.parse(params.get("toDate"));
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("createdOn"), toDate.atTime(23, 59, 59)));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
