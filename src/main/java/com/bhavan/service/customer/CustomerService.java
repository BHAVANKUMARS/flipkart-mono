package com.bhavan.service.customer;

import com.bhavan.dto.common.AmakartRequest;
import com.bhavan.dto.common.AmakartResponse;
import com.bhavan.model.ProductDetails;

import java.util.List;

public interface CustomerService {

    String login(AmakartRequest loginRequest);

    String register(AmakartRequest loginRequest);

    String resetPassword(AmakartRequest loginRequest);

    String addToCart(AmakartRequest shoppingCartRequest);

    List<ProductDetails> getCartDetail(String userName);

    AmakartResponse getUserDetails(String username);

}
