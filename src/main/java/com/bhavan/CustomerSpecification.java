package com.bhavan;

import com.bhavan.model.ProductDetails;
import com.bhavan.model.UserDetails;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CustomerSpecification {

    public static Specification<UserDetails> searchByCriteria(Map<String,String> params) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (params.containsKey("userId") && params.get("userId")!=null && !params.get("userId").isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("userId"), Long.valueOf(params.get("userId"))));
            }

            if (params.containsKey("status") && params.get("status")!=null && !params.get("status").isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("status"), params.get("status")));
            }

            if (params.containsKey("userName") && params.get("userName")!=null && !params.get("userName").isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("userName"), params.get("userName")));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
