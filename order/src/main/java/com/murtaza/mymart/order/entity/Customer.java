package com.murtaza.mymart.order.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Customer {

    private String name;
    private String email;
    private String phoneNo;

}
