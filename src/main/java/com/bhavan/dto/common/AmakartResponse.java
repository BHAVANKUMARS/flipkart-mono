package com.bhavan.dto.common;


import lombok.*;
import lombok.experimental.SuperBuilder;

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

}
