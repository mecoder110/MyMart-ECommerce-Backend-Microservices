package com.murtaza.mymart.order.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

@Entity
@Table(name = "orders")
@Setter
@Getter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;

    private String orderTrackingNum;

    private Double totalPrice;

    private Integer totalQuantity;

    private String orderStatus;

    private LocalDate deliveyDate;

    private String paymentStatus;

    private String razorpayOrderId;

    private String razorpayPaymentId;

    @CreationTimestamp
    @Column(name = "date_created", updatable = false)
    private LocalDate createdDate;

    @UpdateTimestamp
    @Column(name = "date_updated", insertable = false)
    private LocalDate lastUpdate;

    private String customerEmail;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customerEntity;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private ShippingAddress shippingAddress;

}
