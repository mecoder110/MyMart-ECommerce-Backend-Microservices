package com.murtaza.mymart.order.mapper;

import com.murtaza.mymart.order.entity.Order;
import com.murtaza.mymart.order.entity.OrderItem;
import com.murtaza.mymart.order.model.OrderDTO;
import com.murtaza.mymart.order.model.OrderItemDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderItemMapper {

    private static ModelMapper modelMapper;

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        OrderItemMapper.modelMapper = modelMapper;
    }

    public static OrderItem toEntity(OrderItemDTO orderItemDTO) {
        return modelMapper.map(orderItemDTO, OrderItem.class);
    }

    public static OrderItemDTO toDto(OrderItem orderItem) {
        return modelMapper.map(orderItem, OrderItemDTO.class);
    }
}
