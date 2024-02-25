package com.example.demo.api.service;

import java.util.List;

import com.example.demo.api.command.CustomerCommand;
import com.example.demo.api.dto.CustomerDto;

public interface ApiCustomerService {
    List<CustomerDto> getUsers();
    CustomerDto getCustomerWithId(Long id);
    CustomerDto createCustomer(CustomerCommand customer);
    CustomerDto deleteCustomer(Long id);
    CustomerDto updateCustomer(Long id, CustomerCommand updatedCustomer);
}