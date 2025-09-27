package com.murtaza.mymart.order.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Address {

    private String houseNum;
    private String street;
    private String city;
    private String state;
    private String zipCode;
}
