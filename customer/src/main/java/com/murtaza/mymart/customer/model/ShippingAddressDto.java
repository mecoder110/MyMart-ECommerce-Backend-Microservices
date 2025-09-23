package com.murtaza.mymart.customer.model;

import lombok.Data;

@Data
public class ShippingAddressDto {

    private Integer addressId;
    private String houseNum;
    private String street;
    private String city;
    private String state;
    private int zipcode;
    private String country;

    private Integer customerId;
}
