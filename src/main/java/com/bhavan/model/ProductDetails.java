package com.bhavan.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;

@Entity
@Table(name = "product_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
public class ProductDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "product_name")
    private String productName;

    private String status;

    private Double productAmount;

    @Column(name = "product_desc", length = 2000) // Example for a larger VARCHAR
    private String productDesc;

    private String imageUrl;

    //    @CreationTimestamp
    @Column(name = "created_on")
    private String createdOn;

    //    @UpdateTimestamp
    @Column(name = "updated_on")
    private String updatedOn;


}
