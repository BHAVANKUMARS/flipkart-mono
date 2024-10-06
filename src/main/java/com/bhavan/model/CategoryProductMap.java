package com.bhavan.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;

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

    @CreationTimestamp
    @Column(name = "created_on", updatable = false)
    private LocalDate createdOn;

    @UpdateTimestamp
    @Column(name = "updated_on")
    private LocalDate updatedOn;
}
