package com.murtaza.mymart.customer.service;

import com.murtaza.mymart.customer.entity.Customer;
import com.murtaza.mymart.customer.model.CustomerDto;
import com.murtaza.mymart.customer.model.PasswordDto;
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
            throw new RuntimeException("Email already taken");
        }
        customer.setPwd(RandomPwdGenerator.generate(6));
        customer.setPwdUpdated("NO");

        Customer save = customerRepository.save(customer);
        return true;
    }

    @Override
    public boolean login(PasswordDto passwordDto) {
        Customer customer = customerRepository.findByEmail(passwordDto.getEmail()).orElseThrow(() -> new RuntimeException("Resourse not found"));
        if (!passwordDto.getCurrentPwd().equals(customer.getPwd())) {
            throw new RuntimeException("Email or Pwd wrong");
        }
        return true;
    }

    @Override
    public boolean resetPwd(PasswordDto passwordDto) {
        Customer customer = customerRepository.findByEmail(passwordDto.getEmail()).orElseThrow(() -> new RuntimeException("Resourse not found"));
        if (!passwordDto.getCurrentPwd().equals(customer.getPwd())) {
            throw new RuntimeException("Email or Pwd wrong");
        }
        if (passwordDto.getNewPwd() == null && passwordDto.getNewPwd().trim() == "") {
            throw new RuntimeException(" Empty Password");
        }
        customer.setPwd(passwordDto.getNewPwd());
        customer.setPwdUpdated("YES");
        customerRepository.save(customer);
        return true;
    }

    @Override
    public CustomerDto retrieveCustomerByEmail(String email) {
        Customer customer = customerRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Resourse not found"));
        return modelMapper.map(customer, CustomerDto.class);

    }

    @Override
    public boolean updateCustomer(CustomerDto customerDto) {
        Customer customer = modelMapper.map(customerDto, Customer.class);

        Customer save = customerRepository.save(customer);
        return true;

    }
}
