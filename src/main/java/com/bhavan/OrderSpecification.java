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

            if (params.containsKey("fromAmount")&& params.get("fromAmount")!=null && !params.get("fromAmount").isEmpty()) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("productAmount"), Double.valueOf(params.get("fromAmount"))));
            }

            if (params.containsKey("toAmount") &&params.get("toAmount")!=null && !params.get("toAmount").isEmpty()) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("productAmount"), Double.valueOf(params.get("toAmount"))));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
