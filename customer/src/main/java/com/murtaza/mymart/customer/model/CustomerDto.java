package com.murtaza.mymart.customer.model;

import lombok.Data;

@Data
public class CustomerDto {

    private Integer customerId;
    private String name;
    private String email;
    private String pwd;
    private String phone;
    private String pwdUpdated;
}
