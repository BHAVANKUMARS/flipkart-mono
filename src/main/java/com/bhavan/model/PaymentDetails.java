package com.bhavan.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Table(name = "payment_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
public class PaymentDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long paymentId;

    private Double paidAmount;

    private Long userId;

    private String status;

    //    @CreationTimestamp
    @Column(name = "created_on")
    private String createdOn;

    //    @UpdateTimestamp
    @Column(name = "updated_on")
    private String updatedOn;

}

