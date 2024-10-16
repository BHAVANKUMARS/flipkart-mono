package com.bhavan.service.payment.impl;

import com.bhavan.dto.common.AmakartRequest;
import com.bhavan.model.OrderDetails;
import com.bhavan.model.PaymentDetails;
import com.bhavan.repository.customer.CustomerDetailsRepo;
import com.bhavan.repository.order.OrderRepository;
import com.bhavan.repository.payment.PaymentRepository;
import com.bhavan.service.payment.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private CustomerDetailsRepo customerDetailsRepo;

    @Override
    public void savePaymentDetails(AmakartRequest amakartRequest) {

        Long userId = customerDetailsRepo.findByUserName(amakartRequest.getUserName()).get().getUserId();

        PaymentDetails paymentDetails = PaymentDetails.builder()
                .paidAmount(amakartRequest.getPaidAmount())
                .userId(userId)
                .status("SUCCESS")
                .build();

        paymentDetails = paymentRepository.save(paymentDetails);

        OrderDetails orderDetails = OrderDetails.builder().status("PENDING")
                .productId(amakartRequest.getProductId())
                .orderName(amakartRequest.getProductName())
                .userId(userId)
                .paymentId(paymentDetails.getPaymentId())
                .deliveryAddress(amakartRequest.getDeliveryAddress())
                .deliveryOptions(amakartRequest.getDeliveryOption())
                .productAmount(amakartRequest.getProductAmount()).build();

        orderRepository.save(orderDetails);
    }
}
