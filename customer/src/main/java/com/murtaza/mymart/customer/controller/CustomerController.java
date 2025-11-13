package com.murtaza.mymart.customer.controller;

import com.murtaza.mymart.customer.model.ApiResponse;
import com.murtaza.mymart.customer.model.CustomerDto;
import com.murtaza.mymart.customer.model.ResetPwdDto;
import com.murtaza.mymart.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Boolean>> customerRegister(@RequestBody CustomerDto customerDto) {
        boolean isRegister = customerService.register(customerDto);
        ApiResponse body = new ApiResponse();
        body.setStatusCode(200);
        body.setMessage("Customer reqister successfully");
        body.setData(isRegister);

        return new ResponseEntity<>(body, HttpStatus.CREATED);
    }

    @PostMapping("/updateCustomer")
    public ResponseEntity<ApiResponse<Boolean>> updateCustomer(@RequestBody CustomerDto customerDto) {
        boolean isUpdate = customerService.updateCustomer(customerDto);
        boolean register = isUpdate;
        ApiResponse body = new ApiResponse();
        body.setStatusCode(200);
        body.setMessage("Customer update successfully");
        body.setData(isUpdate);

        return new ResponseEntity<>(body, HttpStatus.ACCEPTED);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<Boolean>> customerLogin(@RequestBody ResetPwdDto resetPwdDto) {
        boolean isLogin = customerService.login(resetPwdDto);

        ApiResponse body = new ApiResponse();
        body.setStatusCode(200);
        body.setMessage("Customer Loged in");
        body.setData(isLogin);

        return new ResponseEntity<>(body, HttpStatus.ACCEPTED);
    }

    @PostMapping("/resetPassword")
    public ResponseEntity<ApiResponse<Boolean>> resetCustomerPassword(@RequestBody ResetPwdDto resetPwdDto) {
        boolean isPwdUpdated = customerService.resetPwd(resetPwdDto);

        ApiResponse body = new ApiResponse();
        body.setStatusCode(200);
        body.setMessage("Customer Password Updated");
        body.setData(isPwdUpdated);

        return new ResponseEntity<>(body, HttpStatus.ACCEPTED);
    }

    @PostMapping("/retrieveCustomer/{email}")
    public ResponseEntity<ApiResponse<Boolean>> retrieveCustomerByEmail(@PathVariable String email) {
        CustomerDto customerDto = customerService.retrieveCustomerByEmail(email);

        ApiResponse body = new ApiResponse();
        body.setStatusCode(200);
        body.setMessage("Customer Found By Email");
        body.setData(customerDto);

        return new ResponseEntity<>(body, HttpStatus.FOUND);
    }
}
