package com.bhavan.dto.common;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AmakartResponse {

    /* Product Details */

    private Long productId;

    private String productName;

    private String status;

    private Double productAmount;

}
