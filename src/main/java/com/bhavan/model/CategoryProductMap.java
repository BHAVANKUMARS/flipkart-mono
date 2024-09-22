package com.bhavan.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Table(name = "category_product_map")
@Getter
@Setter
@ToString
@NoArgsConstructor
@SuperBuilder
public class CategoryProductMap {

    @Id
    @Column(name = "category_product_map_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long categoryId;

    private Long productId;

    private String status;

    //    @CreationTimestamp
    @Column(name = "created_on")
    private String createdOn;

    //    @UpdateTimestamp
    @Column(name = "updated_on")
    private String updatedOn;

}
