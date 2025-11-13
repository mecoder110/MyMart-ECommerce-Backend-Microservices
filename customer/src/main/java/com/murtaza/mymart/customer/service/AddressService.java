package com.murtaza.mymart.customer.service;

import com.murtaza.mymart.customer.model.ShippingAddressDto;

import java.util.List;

public interface AddressService {

    public boolean saveAddress(ShippingAddressDto addressDto, Integer customerId);

    public boolean updateAddress(ShippingAddressDto addressDto);

    public List<ShippingAddressDto> getCustomerAddress(Integer customerId);

    public ShippingAddressDto getAddress(Integer addressId);

    public boolean deleteAddress(Integer addressId);

}
