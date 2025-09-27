package com.murtaza.mymart.order.model;

import lombok.Data;

@Data
public class Address {

    private String houseNum;
    private String street;
    private String city;
    private String state;
    private String zipCode;
}
