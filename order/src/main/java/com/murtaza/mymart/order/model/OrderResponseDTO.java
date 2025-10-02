package com.murtaza.mymart.order.model;

import lombok.Data;

@Data
public class OrderResponseDTO {

    private String razorpayOrderId;
    private String orderStatus;
    private String orderTrackingNumber;
}
