package com.murtaza.mymart.customer.service;

import com.murtaza.mymart.customer.model.ShippingAddressDto;

import java.util.List;

public interface AddressService {

    public boolean saveAddress(ShippingAddressDto addressDto);

    public boolean updateAddress(ShippingAddressDto addressDto);

    public List<ShippingAddressDto> retrieveAddressByCustomerId(Integer customerId);

    public ShippingAddressDto retrieveAddressById(Integer addressId);

    public boolean deleteAddressById(Integer addressId);

}
