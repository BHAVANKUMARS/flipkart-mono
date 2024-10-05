package com.bhavan.service.order;

import com.bhavan.dto.common.AmakartRequest;
import com.bhavan.dto.common.AmakartResponse;

import java.util.List;

public interface OrderService {

    void saveOrderDetails(AmakartRequest amakartRequest);

    List<AmakartResponse> getOrderDetails(String userName);
}
