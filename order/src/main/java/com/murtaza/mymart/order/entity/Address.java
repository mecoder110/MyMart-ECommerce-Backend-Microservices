package com.murtaza.mymart.order.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Setter
@Getter
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String houseNum;
    private String street;
    private String city;
    private String state;
    private String zipCode;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
