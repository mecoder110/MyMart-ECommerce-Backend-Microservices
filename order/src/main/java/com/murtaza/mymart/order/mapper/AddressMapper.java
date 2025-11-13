package com.murtaza.mymart.order.mapper;

import com.murtaza.mymart.order.entity.ShippingAddress;
import com.murtaza.mymart.order.model.AddressDTO;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AddressMapper {

    private static ModelMapper modelMapper;

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        AddressMapper.modelMapper = modelMapper;
    }

    public static ShippingAddress toEntity(AddressDTO addressDTO) {

        return modelMapper.map(addressDTO, ShippingAddress.class);
    }

    public static AddressDTO toDto(ShippingAddress address) {
        return modelMapper.map(address, AddressDTO.class);
    }
}
