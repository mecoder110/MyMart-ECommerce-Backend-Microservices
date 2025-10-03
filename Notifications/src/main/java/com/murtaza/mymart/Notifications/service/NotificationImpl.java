package com.murtaza.mymart.Notifications.service;

public class NotificationImpl implements NotificationService{


    @Schedule("* 7 * * * *")
    public void sendDeliveryNotification(){

        List<Order> ordersList = service.findOrdersByDeliveryDate(LocalDate.now());

        for(Order order : ordersList){
            Customer c = customerService.getCustomerByEmail(order.getEmail());
            String otp = generateOTP();
            sendEmailNotification(c, otp);
            sendWhatsAppNotification(c, otp);
        }
    }
}
