package com.murtaza.mymart.order.model;

import lombok.Data;

@Data
public class AddressDTO {

    private Integer addressId;
    private String houseNum;
    private String street;
    private String city;
    private String state;
    private String zipcode;
    private String country;
    private String addrType;
}
