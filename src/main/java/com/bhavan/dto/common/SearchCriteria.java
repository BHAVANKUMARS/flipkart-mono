package com.bhavan.dto.common;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class SearchCriteria {

    private String orderName;
    private String orderStatus;
    private String paymentStatus;
    private String fromDate; // or LocalDate
    private String toDate;
    private String status;
    private Double fromAmount;
    private Double toAmount;
    private Long productId;
}
