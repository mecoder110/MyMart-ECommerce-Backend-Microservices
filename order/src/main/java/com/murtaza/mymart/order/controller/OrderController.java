package com.murtaza.mymart.order.controller;

import com.murtaza.mymart.order.model.OrderDTO;
import com.murtaza.mymart.order.model.OrderRequestDTO;
import com.murtaza.mymart.order.model.OrderResponseDTO;
import com.murtaza.mymart.order.model.UpdateOrderDTO;
import com.murtaza.mymart.order.response.ApiResponse;
import com.murtaza.mymart.order.service.OrderService;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create-order")
    public ResponseEntity<ApiResponse<OrderResponseDTO>> createOrder(@RequestBody OrderRequestDTO orderRequestDTO) throws Exception {

        OrderResponseDTO orderResponseDTO = orderService.createOrder(orderRequestDTO);
        ApiResponse body = new ApiResponse<>();
        if (orderResponseDTO != null) {
            body.setStatusCode(201);
            body.setMessage("order-created successful!!");
            body.setData(orderResponseDTO);
            return new ResponseEntity<>(body, HttpStatus.CREATED);
        } else {
            body.setStatusCode(500);
            body.setMessage("order-created failed!!");
            body.setData(null);
            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/update-order")
    public ResponseEntity<ApiResponse<OrderResponseDTO>> updateOrder(@RequestBody UpdateOrderDTO updateOrderDTO) {
        OrderResponseDTO orderResponseDTO = orderService.updateOrder(updateOrderDTO);
        ApiResponse body = new ApiResponse<>();

        if (orderResponseDTO != null) {
            body.setStatusCode(200);
            body.setMessage("order-updated successful!!");
            body.setData(orderResponseDTO);
            return new ResponseEntity<>(body, HttpStatus.ACCEPTED);
        } else {
            body.setStatusCode(500);
            body.setMessage("order-updated failed!!");
            body.setData(null);
            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/cancle-order/{trackingNum}")
    public ResponseEntity<ApiResponse<OrderResponseDTO>> cancleOrder(@PathVariable String trackingNum) throws RazorpayException {

        OrderResponseDTO orderResponseDTO = orderService.cancleOrder(trackingNum);
        ApiResponse body = new ApiResponse<>();
        if (orderResponseDTO != null) {
            body.setStatusCode(200);
            body.setMessage("cancel-order successful!!");
            body.setData(orderResponseDTO);
            return new ResponseEntity<>(body, HttpStatus.OK);
        } else {
            body.setStatusCode(500);
            body.setMessage("cancel-order failed!!");
            body.setData(null);
            return new ResponseEntity<>(body, HttpStatus.OK);
        }
    }

    @GetMapping("/find-order/{email}")
    public ResponseEntity<ApiResponse<List<OrderDTO>>> customerOrder(@PathVariable String email) {
        List<OrderDTO> customerOrders = orderService.getCustomerOrders(email);
        ApiResponse body = new ApiResponse<>();
        body.setStatusCode(200);
        body.setMessage("order list !!");
        body.setData(customerOrders);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }
}