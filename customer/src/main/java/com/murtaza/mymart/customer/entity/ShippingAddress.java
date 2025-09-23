package com.murtaza.mymart.customer.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class ShippingAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer addressId;

    private String houseNum;
    private String street;
    private String city;
    private String state;
    private int zipcode;
    private String country;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
