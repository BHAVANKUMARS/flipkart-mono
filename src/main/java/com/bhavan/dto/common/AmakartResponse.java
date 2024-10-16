package com.bhavan.dto.common;


import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@SuperBuilder
public class AmakartResponse {

    /* Product Details */

    private Long productId;

    private String productName;

    private String status;

    private Double productAmount;

    private String username;

    private String password;

    private String productImageUrl;

    private Long orderId;

    private String orderStatus;

    private String paymentStatus;

    private LocalDate orderDate;

    private String deliveryOption;

}
