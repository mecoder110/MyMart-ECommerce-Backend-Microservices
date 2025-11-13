package com.murtaza.mymart.order.model;

import lombok.Data;

@Data
public class OrderResponseDTO {

    private String razorpayOrderId;
    private String paymentStatus;
    private String orderStatus;
    private String orderTrackingNumber;
    private String razorpayPaymentId;
}
