package com.bhavan.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Table(name = "categories")
@Getter
@Setter
@ToString
@NoArgsConstructor
@SuperBuilder
public class Categories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String categoryName;

    private String categoryUrl;

    private String status;

    //    @CreationTimestamp
    @Column(name = "created_on")
    private String createdOn;

    //    @UpdateTimestamp
    @Column(name = "updated_on")
    private String updatedOn;
}
