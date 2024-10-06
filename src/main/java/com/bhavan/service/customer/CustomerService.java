package com.bhavan.service.customer;

import com.bhavan.dto.common.AmakartRequest;
import com.bhavan.dto.common.AmakartResponse;
import com.bhavan.model.ProductDetails;
import com.bhavan.model.UserDetails;

import java.util.List;
import java.util.Map;

public interface CustomerService {

    String login(AmakartRequest loginRequest);

    String register(AmakartRequest loginRequest);

    String resetPassword(AmakartRequest loginRequest);

    String addToCart(AmakartRequest shoppingCartRequest);

    List<ProductDetails> getCartDetail(String userName);

    AmakartResponse getUserDetails(String username);

    Long getCustomerTotalCount();

    List<UserDetails> getActiveUsers();

    List<UserDetails> searchCriteria(Map<String,String> params);

}
