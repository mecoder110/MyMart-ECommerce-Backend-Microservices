package com.murtaza.mymart.order.mapper;

import com.murtaza.mymart.order.entity.CustomerEntity;
import com.murtaza.mymart.order.model.CustomerDTO;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Setter
public class CustomerMapper {

    private static ModelMapper modelMapper;

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        CustomerMapper.modelMapper = modelMapper;
    }

    public static CustomerEntity toEntity(CustomerDTO customerDTO) {
        return modelMapper.map(customerDTO, CustomerEntity.class);
    }

    public static CustomerDTO toDto(CustomerEntity customer) {
        return modelMapper.map(customer, CustomerDTO.class);
    }
}
