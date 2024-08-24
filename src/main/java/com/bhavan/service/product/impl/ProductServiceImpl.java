package com.bhavan.service.product.impl;

import com.bhavan.dto.common.AmakartRequest;
import com.bhavan.dto.common.AmakartResponse;
import com.bhavan.service.product.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    @Override
    public String addProduct(AmakartRequest productDetails) {
        return null;
    }

    @Override
    public void viewProduct(Long productId) {

    }

    @Override
    public AmakartResponse findAllActiveProducts() {
        return null;
    }
}
