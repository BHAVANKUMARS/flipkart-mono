package com.bhavan.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;

@Entity
@Table(name = "admin_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
public class AdminDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private Long adminId;

    @Column(name = "user_name")
    private String userName;

    private String password;

    private String status;

//    @CreationTimestamp
    @Column(name = "created_on")
    private String createdOn;

//    @UpdateTimestamp
    @Column(name = "updated_on")
    private String updatedOn;


}
