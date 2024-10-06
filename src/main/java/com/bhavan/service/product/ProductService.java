package com.bhavan.service.product;

import com.bhavan.dto.common.AmakartRequest;
import com.bhavan.dto.common.AmakartResponse;
import com.bhavan.model.ProductDetails;

import java.util.List;
import java.util.Map;

public interface ProductService {

    String addProduct(AmakartRequest productDetails);

    void viewProduct(Long productId);

    List<ProductDetails> findAllActiveProducts();

    Long getProductTotalCount();

    List<ProductDetails> searchCriteria(Map<String,String> params);



}
