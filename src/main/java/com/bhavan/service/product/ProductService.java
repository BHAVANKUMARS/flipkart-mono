package com.bhavan.service.product;

import com.bhavan.dto.common.AmakartRequest;
import com.bhavan.dto.common.AmakartResponse;

public interface ProductService {

    String addProduct(AmakartRequest productDetails);

    void viewProduct(Long productId);

    AmakartResponse findAllActiveProducts();

}
