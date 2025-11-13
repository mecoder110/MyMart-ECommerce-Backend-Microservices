package com.murtaza.mymart.Notifications.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Customer {

    @Id
    private Integer id;
    private String name;
    private String email;
    private String phoneNo;

}