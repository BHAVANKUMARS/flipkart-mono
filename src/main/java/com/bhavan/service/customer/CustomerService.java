package com.bhavan.service.customer;

import com.bhavan.dto.common.AmakartRequest;
import com.bhavan.dto.common.AmakartResponse;

public interface CustomerService {

    String login(AmakartRequest loginRequest);

    String register(AmakartRequest loginRequest);

    String resetPassword(AmakartRequest loginRequest);

    String addToCart(AmakartRequest shoppingCartRequest);

    AmakartResponse getUserDetails(String username);

}
