package com.murtaza.mymart.Notifications.service;

import com.murtaza.mymart.Notifications.entity.Customer;
import com.murtaza.mymart.Notifications.entity.Order;
import com.murtaza.mymart.Notifications.repository.CustomerRepository;
import com.murtaza.mymart.Notifications.repository.OrderRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
@Log
public class NotificationImpl implements NotificationService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private EmailService emailService;

    @Scheduled(cron = "0 * * * * *")
    public void sendDeliveryNotification() {
        log.info("schedular trigget==> at : " + LocalDateTime.now());
        List<Order> ordersList = orderRepository.findOrdersByDeliveryDate(LocalDate.now());

        for (Order order : ordersList) {
            Customer c = customerRepository.getCustomerByEmail(order.getEmail());
            String otp = generateOTP(6);
            order.setOtp(otp);
            sendEmailNotification(c, otp);
            sendWhatsAppNotification(c, otp);
        }
    }

    private String generateOTP(int length) {
        Random random = new Random();
        String str = "123456789";
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {

            int digit = random.nextInt(str.length());
            sb.append(digit);
        }
        return sb.toString();
    }

    @Override
    public void sendEmailNotification(Customer c, String otp) {
        String body = "Your Otp please share while recieve product: " + otp;
        boolean outForDeleivey = emailService.sendMail(c.getEmail(), "OUT FOR DELEIVEY", body);
    }

    @Override
    public void sendWhatsAppNotification(Customer c, String otp) {
        log.info("message delivered to whatsapp "+c.getPhoneNo()+" OTP: "+otp);

    }
}
