package com.murtaza.mymart.order.controller;

import com.murtaza.mymart.order.model.ApiResponse;
import com.murtaza.mymart.order.model.OrderRequestDTO;
import com.murtaza.mymart.order.model.OrderResponseDTO;
import com.murtaza.mymart.order.model.UpdateOrderDTO;
import com.murtaza.mymart.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create-order")
    public ResponseEntity<ApiResponse<OrderResponseDTO>> createOrder(@RequestBody OrderRequestDTO orderRequestDTO) {
        OrderResponseDTO orderResponseDTO = orderService.createOrder(orderRequestDTO);

        ApiResponse body = new ApiResponse<>();
        body.setStatusCode(200);
        body.setMessage("order-created successful!!");
        body.setData(orderResponseDTO);

        return new ResponseEntity<>(body, HttpStatus.CREATED);
    }

    @PostMapping("/update-order")
    public ResponseEntity<ApiResponse<Boolean>> updateOrder(@RequestBody UpdateOrderDTO updateOrderDTO) {
        boolean b = orderService.updateOrder(updateOrderDTO);


        ApiResponse body = new ApiResponse<>();
        body.setStatusCode(200);
        body.setMessage("order-updated successful!!");
        body.setData(b);

        return new ResponseEntity<>(body, HttpStatus.ACCEPTED);
    }

    @PostMapping("/cancle-order")
    public ResponseEntity<ApiResponse<Boolean>> cancleOrder(@RequestBody UpdateOrderDTO updateOrderDTO) {
        boolean b = orderService.cancleOrder(updateOrderDTO);

        ApiResponse body = new ApiResponse<>();
        body.setStatusCode(200);
        body.setMessage("cancle-updated successful!!");
        body.setData(b);

        return new ResponseEntity<>(body, HttpStatus.OK);
    }
}