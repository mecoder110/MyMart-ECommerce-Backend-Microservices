package com.murtaza.mymart.Notifications.service;

import org.springframework.stereotype.Service;


public interface NotificationService {

    public void sendDeliveryNotification();

    public void sendEmailNotification(Customer c, String otp);

    public void sendWhatsAppNotification(Customer c, String otp);
}
