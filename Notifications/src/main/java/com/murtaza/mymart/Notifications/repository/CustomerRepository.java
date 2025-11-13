package com.murtaza.mymart.Notifications.repository;

import com.murtaza.mymart.Notifications.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Customer getCustomerByEmail(String email);
}
