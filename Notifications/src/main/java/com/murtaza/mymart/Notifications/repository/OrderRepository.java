package com.murtaza.mymart.Notifications.repository;

import com.murtaza.mymart.Notifications.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findOrdersByDeliveryDate(LocalDate now);
}
