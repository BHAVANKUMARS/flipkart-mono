package com.bhavan.dto.common;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AmakartRequest {

    private String userName;

    private String password;

    private String newPassword;

    private String confirmPassword;

    /* Product Details */

    private Long productId;

    private String productName;

    private String status;

    private Double productAmount;


}
