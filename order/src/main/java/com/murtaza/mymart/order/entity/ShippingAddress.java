package com.murtaza.mymart.order.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "shipping_addr")
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
    private String zipcode;
    private String country;
    private String addrType;
    private boolean deleteSw;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;
}
