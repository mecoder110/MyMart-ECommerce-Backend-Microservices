package com.murtaza.mymart.order.repository;

import com.murtaza.mymart.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    Order findByRazorpayOrderId(String razorpayPaymentId);

    Order findByOrderTrackingNum(String orderTrackingNum);

    List<Order> findByCustomerEmail(String customerEmail);
}
