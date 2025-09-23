package com.murtaza.mymart.customer.service;

import com.murtaza.mymart.customer.model.CustomerDto;
import com.murtaza.mymart.customer.model.PasswordDto;

public interface CustomerService {

    public boolean register(CustomerDto customerDto);

    public boolean login(PasswordDto passwordDto);

    public boolean resetPwd(PasswordDto passwordDto);

    public CustomerDto retrieveCustomerByEmail(String email);

    boolean updateCustomer(CustomerDto customerDto);
}
