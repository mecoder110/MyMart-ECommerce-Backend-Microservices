package com.murtaza.mymart.customer.service;

import com.murtaza.mymart.customer.model.CustomerDto;
import com.murtaza.mymart.customer.model.ResetPwdDto;

public interface CustomerService {

    public boolean register(CustomerDto customerDto);

    public boolean login(ResetPwdDto resetPwdDto);

    public boolean resetPwd(ResetPwdDto resetPwdDto);

    public CustomerDto retrieveCustomerByEmail(String email);

    boolean updateCustomer(CustomerDto customerDto);
}
