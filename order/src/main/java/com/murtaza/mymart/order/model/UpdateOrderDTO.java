package com.murtaza.mymart.order.model;

import lombok.Data;

@Data
public class UpdateOrderDTO {

    private String razorpayPaymentId;
    private String orderStatus;
    private String orderTrackingNum;
    private String paymentStatus;
    private String razorpayOrderId;

}
