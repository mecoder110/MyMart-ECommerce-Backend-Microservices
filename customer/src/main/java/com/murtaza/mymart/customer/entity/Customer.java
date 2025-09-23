package com.murtaza.mymart.customer.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
    private String pwdUpdated;
}
