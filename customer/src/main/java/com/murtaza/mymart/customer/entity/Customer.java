package com.murtaza.mymart.customer.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

@Entity
@Setter
@Getter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;

    private String name;
    @Column(unique = true)
    private String email;
    private String pwd;
    private String phone;
    private boolean pwdUpdated;
    @CreationTimestamp
    @Column(name = "created_date", updatable = false)
    private LocalDate createdDate;
    @UpdateTimestamp
    @Column(name = "last_update", insertable = false)
    private LocalDate lastUpdatedDate;
}
