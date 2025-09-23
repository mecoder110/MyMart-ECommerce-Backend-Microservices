package com.murtaza.mymart.customer.controller;

import com.murtaza.mymart.customer.model.ApiResponse;
import com.murtaza.mymart.customer.model.CustomerDto;
import com.murtaza.mymart.customer.model.ShippingAddressDto;
import com.murtaza.mymart.customer.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer/address")
public class ShippingAddressController {

    @Autowired
    AddressService addressService;

    @PostMapping("/new-address")
    public ResponseEntity<ApiResponse<Boolean>> saveAddress(@RequestBody ShippingAddressDto addressDto) {
        boolean addrSaved = addressService.saveAddress(addressDto);

        ApiResponse body = new ApiResponse();
        body.setStatusCode(200);
        body.setMessage("Shiping Address Added");
        body.setData(addrSaved);

        return new ResponseEntity<>(body, HttpStatus.CREATED);
    }

    @PostMapping("/edit-address")
    public ResponseEntity<ApiResponse<Boolean>> updateAddress(@RequestBody ShippingAddressDto addressDto) {
        boolean addrEdited = addressService.updateAddress(addressDto);

        ApiResponse body = new ApiResponse();
        body.setStatusCode(200);
        body.setMessage("Shiping Address Added");
        body.setData(addrEdited);

        return new ResponseEntity<>(body, HttpStatus.CREATED);
    }

    @GetMapping("/retrieve-customerAddress/{customerId}")
    public ResponseEntity<ApiResponse<Boolean>> retrieveAllAddressByCustomer(@PathVariable Integer customerId) {
        List<ShippingAddressDto> shippingAddressDtos = addressService.retrieveAddressByCustomerId(customerId);

        ApiResponse body = new ApiResponse();
        body.setStatusCode(200);
        body.setMessage("Shiping Address List");
        body.setData(shippingAddressDtos);

        return new ResponseEntity<>(body, HttpStatus.FOUND);
    }

    @GetMapping("/retrieve-address/{addressId}")
    public ResponseEntity<ApiResponse<Boolean>> retrieveAddressById(@PathVariable Integer addressId) {
        ShippingAddressDto shippingAddressDto = addressService.retrieveAddressById(addressId);

        ApiResponse body = new ApiResponse();
        body.setStatusCode(200);
        body.setMessage("Shiping Address");
        body.setData(shippingAddressDto);

        return new ResponseEntity<>(body, HttpStatus.FOUND);
    }

    @DeleteMapping("/remove-address/{addressId}")
    public ResponseEntity<ApiResponse<Boolean>> deleteAddressById(@PathVariable Integer addressId) {
        boolean addressDeleted = addressService.deleteAddressById(addressId);

        ApiResponse body = new ApiResponse();
        body.setStatusCode(200);
        body.setMessage("Shiping Address Deleted");
        body.setData(addressDeleted);

        return new ResponseEntity<>(body, HttpStatus.OK);
    }
}
