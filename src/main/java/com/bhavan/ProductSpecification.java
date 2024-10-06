package com.bhavan;

import com.bhavan.model.ProductDetails;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductSpecification {

    public static Specification<ProductDetails> searchByCriteria(Map<String,String> params) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (params.containsKey("productId") && params.get("productId")!=null && !params.get("productId").isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("productId"), Long.valueOf(params.get("productId"))));
            }

            if (params.containsKey("status") && params.get("status")!=null && !params.get("status").isEmpty()) {
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
