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

    @CreationTimestamp
    @Column(name = "created_on", updatable = false)
    private LocalDate createdOn;

    @UpdateTimestamp
    @Column(name = "updated_on")
    private LocalDate updatedOn;;
}
