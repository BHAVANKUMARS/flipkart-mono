package com.bhavan.service.order;

import com.bhavan.dto.common.AmakartRequest;
import com.bhavan.dto.common.AmakartResponse;
import com.bhavan.model.OrderDetails;

import java.util.List;
import java.util.Map;

public interface OrderService {

    void saveOrderDetails(AmakartRequest amakartRequest);

    List<AmakartResponse> getOrderDetails(String userName);

    List<OrderDetails> getOrderTotalCount();

    List<OrderDetails> getAllOrderDetails();

    public List<OrderDetails> searchCriteria(Map<String, String> params) ;

    public void updateOrderDetails(AmakartRequest amakartRequest);

    }
