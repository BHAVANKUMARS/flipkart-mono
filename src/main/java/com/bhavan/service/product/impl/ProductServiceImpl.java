package com.bhavan.service.product.impl;

import com.bhavan.ProductSpecification;
import com.bhavan.dto.common.AmakartRequest;
import com.bhavan.dto.common.AmakartResponse;
import com.bhavan.model.ProductDetails;
import com.bhavan.repository.product.ProductDetailsRepo;
import com.bhavan.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDetailsRepo productDetailsRepo;
    @Override
    public String addProduct(AmakartRequest productDetails) {
        return null;
    }

    @Override
    public void viewProduct(Long productId) {

    }

    @Override
    public List<ProductDetails> findAllActiveProducts() {
        return productDetailsRepo.findByStatus("A");
    }

    @Override
    public Long getProductTotalCount() {
        return productDetailsRepo.countByStatus("A");
    }

    @Override
    public List<ProductDetails> searchCriteria(Map<String, String> params) {
        Specification<ProductDetails> spec = ProductSpecification.searchByCriteria(params);
        return productDetailsRepo.findAll(spec);
    }
}
