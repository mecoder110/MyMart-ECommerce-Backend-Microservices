package com.murtaza.mymart.Notifications.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
@Table(name = "orders")
@Setter
@Getter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String email;
    private double totalPrice;
    private int totalQuantity;
    @CreationTimestamp
    private Timestamp createdAt;
    @UpdateTimestamp
    private Timestamp updatedAt;

    private String otp;

    private String razorpayOrderId;
    private String orderStatus;
    private String orderTrackingNumber;

    private LocalDate deliveryDate;
//    @ManyToOne
//    @JoinColumn(name = "customer_id")
//    private Customer customer;
//    @ManyToOne
//    @JoinColumn(name = "address_id")
//    private Address address;

}
