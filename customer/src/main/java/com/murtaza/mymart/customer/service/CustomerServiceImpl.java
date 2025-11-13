package com.murtaza.mymart.customer.service;

import com.murtaza.mymart.customer.entity.Customer;
import com.murtaza.mymart.customer.exception.ApiException;
import com.murtaza.mymart.customer.exception.NoResourceFoundException;
import com.murtaza.mymart.customer.model.CustomerDto;
import com.murtaza.mymart.customer.model.ResetPwdDto;
import com.murtaza.mymart.customer.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public boolean register(CustomerDto customerDto) {
        Customer customer = modelMapper.map(customerDto, Customer.class);

        if (customerRepository.findByEmail(customerDto.getEmail()).isPresent()) {
            throw new ApiException("Customer : ","Email already taken!",409);
        }
        customer.setPwd(RandomPwdGenerator.generate(6));
        customer.setPwdUpdated(false);

        Customer save = customerRepository.save(customer);
        return true;
    }

    @Override
    public boolean login(ResetPwdDto resetPwdDto) {
        Customer customer = customerRepository.findByEmail(resetPwdDto.getEmail()).orElseThrow(()->
               new NoResourceFoundException(resetPwdDto.getEmail()," Record not found!",404));
        if (!resetPwdDto.getCurrentPwd().equals(customer.getPwd())) {
            throw new ApiException("Customer ","Email or Password may not correct!",404);
        }
        return true;
    }

    @Override
    public boolean resetPwd(ResetPwdDto resetPwdDto) {
        Customer customer = customerRepository.findByEmail(resetPwdDto.getEmail()).orElseThrow(() -> new RuntimeException("Resourse not found"));
        if (!resetPwdDto.getCurrentPwd().equals(customer.getPwd())) {
            throw new ApiException("Customer ","Email or Password may not correct!",404);

        }
        if (resetPwdDto.getNewPwd() == null && resetPwdDto.getNewPwd().trim() == "") {
            throw new ApiException("Customer : "," Password can not be empty",409);
        }
        customer.setPwd(resetPwdDto.getNewPwd());
        customer.setPwdUpdated(true);
        customerRepository.save(customer);
        return true;
    }

    @Override
    public CustomerDto retrieveCustomerByEmail(String email) {
        Customer customer = customerRepository.findByEmail(email).orElseThrow(() ->
                new NoResourceFoundException(email," Record not found!",404));
        return modelMapper.map(customer, CustomerDto.class);

    }

    @Override
    public boolean updateCustomer(CustomerDto customerDto) {
        Customer customer = modelMapper.map(customerDto, Customer.class);

        Customer save = customerRepository.save(customer);
        return true;

    }
}
