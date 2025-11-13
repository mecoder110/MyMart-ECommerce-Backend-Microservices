package com.murtaza.mymart.order.mapper;

import com.murtaza.mymart.order.entity.Order;
import com.murtaza.mymart.order.model.OrderDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    private static ModelMapper modelMapper;

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        OrderMapper.modelMapper = modelMapper;
    }

    public static Order toEntity(OrderDTO orderDTO) {
        return modelMapper.map(orderDTO, Order.class);
    }

    public static OrderDTO toDto(Order order) {
        return modelMapper.map(order, OrderDTO.class);
    }
}
