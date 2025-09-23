package com.murtaza.mymart.customer.repository;

import com.murtaza.mymart.customer.entity.ShippingAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShippingAddressRepository extends JpaRepository<ShippingAddress, Integer> {
    List<ShippingAddress> findByCustomerCustomerId(Integer customerId);
}
