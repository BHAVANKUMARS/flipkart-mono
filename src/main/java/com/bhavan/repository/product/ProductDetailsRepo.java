package com.bhavan.repository.product;

import com.bhavan.model.ProductDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ProductDetailsRepo extends JpaRepository<ProductDetails,Long>, JpaSpecificationExecutor<ProductDetails> {

        Long countByStatus(String status);

        List<ProductDetails> findByStatus(String status);
}
