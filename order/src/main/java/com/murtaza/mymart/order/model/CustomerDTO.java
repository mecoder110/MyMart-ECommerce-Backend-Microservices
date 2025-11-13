package com.murtaza.mymart.order.model;

import lombok.Data;

@Data
public class CustomerDTO {

    private Integer customerId;
    private String name;
    private String email;
    private String phoneNo;

}
