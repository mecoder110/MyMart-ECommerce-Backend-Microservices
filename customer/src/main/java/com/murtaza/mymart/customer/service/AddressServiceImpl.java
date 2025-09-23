package com.murtaza.mymart.customer.service;

import com.murtaza.mymart.customer.entity.Customer;
import com.murtaza.mymart.customer.entity.ShippingAddress;
import com.murtaza.mymart.customer.model.ShippingAddressDto;
import com.murtaza.mymart.customer.repository.CustomerRepository;
import com.murtaza.mymart.customer.repository.ShippingAddressRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    ShippingAddressRepository shippingAddressRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ModelMapper modelMapper;


    @Override
    public boolean saveAddress(ShippingAddressDto addressDto) {

        Customer customer = customerRepository.findById(addressDto.getCustomerId()).orElseThrow(
                () -> new RuntimeException("Not found")
        );
        ShippingAddress addr = modelMapper.map(addressDto, ShippingAddress.class);
        addr.setCustomer(customer);
        shippingAddressRepository.save(addr);
        return true;
    }

    @Override
    public boolean updateAddress(ShippingAddressDto addressDto) {
        Customer customer = customerRepository.findById(addressDto.getCustomerId()).orElseThrow(
                () -> new RuntimeException("Not found")
        );
        ShippingAddress addr = modelMapper.map(addressDto, ShippingAddress.class);
        addr.setCustomer(customer);
        shippingAddressRepository.save(addr);
        return true;
    }

    @Override
    public List<ShippingAddressDto> retrieveAddressByCustomerId(Integer customerId) {

        return shippingAddressRepository.findByCustomerCustomerId(customerId)
                .stream().map(addr ->
                        modelMapper.map(addr, ShippingAddressDto.class))
                .toList();

    }

    @Override
    public ShippingAddressDto retrieveAddressById(Integer addressId) {
        ShippingAddress shippingAddress = shippingAddressRepository.findById(addressId).orElseThrow(() ->
                new RuntimeException(""));

        return modelMapper.map(shippingAddress, ShippingAddressDto.class);
    }

    @Override
    public boolean deleteAddressById(Integer addressId) {
        shippingAddressRepository.deleteById(addressId);
        return true;
    }
}
