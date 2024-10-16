package com.bhavan.service.order.Impl;

import com.bhavan.OrderSpecification;
import com.bhavan.dto.common.AmakartRequest;
import com.bhavan.dto.common.AmakartResponse;
import com.bhavan.model.OrderDetails;
import com.bhavan.model.PaymentDetails;
import com.bhavan.model.ProductDetails;
import com.bhavan.repository.customer.CustomerDetailsRepo;
import com.bhavan.repository.order.OrderRepository;
import com.bhavan.repository.payment.PaymentRepository;
import com.bhavan.repository.product.ProductDetailsRepo;
import com.bhavan.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private CustomerDetailsRepo customerDetailsRepo;

    @Autowired
    private ProductDetailsRepo productDetailsRepo;

    @Override
    public void saveOrderDetails(AmakartRequest amakartRequest) {

        Long userId = customerDetailsRepo.findByUserName(amakartRequest.getUserName()).get().getUserId();

        PaymentDetails paymentDetails = PaymentDetails.builder()
//                .paidAmount(amakartRequest.getPaidAmount())
                .userId(userId)
                .status("PENDING")
                .build();

        paymentDetails = paymentRepository.save(paymentDetails);

        OrderDetails orderDetails = OrderDetails.builder().status("PENDING")
                .productId(amakartRequest.getProductId())
                .orderName(amakartRequest.getProductName())
                .userId(userId)
                .paymentId(paymentDetails.getPaymentId())
                .productAmount(amakartRequest.getProductAmount())
                .deliveryAddress(amakartRequest.getDeliveryAddress())
                .deliveryOptions(amakartRequest.getDeliveryOption())
        .build();

        orderRepository.save(orderDetails);

    }

    @Override
    public List<AmakartResponse> getOrderDetails(String userName) {

        Long userId = customerDetailsRepo.findByUserName(userName).get().getUserId();

        List<OrderDetails> orderDetailsList = orderRepository.findByUserId(userId);

        List<AmakartResponse> amakartResponsesList = new ArrayList<>();

        if(orderDetailsList.size() > 0){

            List <OrderDetails> orderDetailsList1 = orderDetailsList.stream().filter(orderDetails -> orderDetails.getUserId()!=null)
                    .collect(Collectors.toList());

            if(orderDetailsList1.size()> 0){

                for(OrderDetails o : orderDetailsList1){

                    AmakartResponse amakartResponse = new AmakartResponse();

                    PaymentDetails paymentDetails = paymentRepository.findById(o.getPaymentId()).get();

                    ProductDetails productDetails = productDetailsRepo.findById(o.getProductId()).get();

                    amakartResponse.setProductImageUrl(productDetails.getImageUrl());
                    amakartResponse.setOrderId(o.getOrderId());
                    amakartResponse.setProductName(productDetails.getProductName());
                    amakartResponse.setOrderStatus(o.getStatus().equalsIgnoreCase("PENDING") ?"NOT DELIVERED":"DELIVERED");
                    amakartResponse.setPaymentStatus(paymentDetails.getStatus().equals("PENDING")?"PAYMENT_PENDING":"PAYMENT_SUCCESS");

                    amakartResponsesList.add(amakartResponse);
                }
            }

        }
        return amakartResponsesList;
    }

    @Override
    public List<OrderDetails> getOrderTotalCount() {
        return orderRepository.findAll();
    }

    @Override
    public List<OrderDetails> getAllOrderDetails() {
        return orderRepository.findAll();
    }

    @Override
    public List<OrderDetails> searchCriteria(Map<String, String> params) {
        Specification<OrderDetails> spec = OrderSpecification.searchByCriteria(params);
        return orderRepository.findAll(spec);
    }


}
