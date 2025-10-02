package com.murtaza.mymart.order.model;

import lombok.Data;

@Data
public class UpdateOrderDTO {

    private String razorpayOrderId;
    private String orderStatus;
    private String orderTrackingNumber;
    private String razorpayPaymentId;
}
