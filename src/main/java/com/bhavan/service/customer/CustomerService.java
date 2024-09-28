package com.bhavan.service.customer;

import com.bhavan.dto.common.AmakartRequest;

public interface CustomerService {

    String login(AmakartRequest loginRequest);

    String register(AmakartRequest loginRequest);

    String resetPassword(AmakartRequest loginRequest);

    String addToCart(AmakartRequest shoppingCartRequest);

}
