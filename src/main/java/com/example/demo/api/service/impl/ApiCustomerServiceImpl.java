package com.example.demo.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.api.command.CustomerCommand;
import com.example.demo.api.dto.CustomerDto;
import com.example.demo.api.factory.ApiCustomerFactory;
import com.example.demo.api.service.ApiCustomerService;
import com.example.demo.domain.service.CustomerService;

@Service
public class ApiCustomerServiceImpl implements ApiCustomerService {

    @Autowired
    private CustomerService domainCustomerService;

    @Override
    public List<CustomerDto> getUsers() {
        return domainCustomerService.getCustomers().stream().map(ApiCustomerFactory::fromCustomerToDto).toList();
    }

    @Override
    public CustomerDto createCustomer(CustomerCommand customer) {
        return ApiCustomerFactory.fromCustomerToDto(domainCustomerService.createCustomer(ApiCustomerFactory.fromCommandToCustomer(customer)));
    }

    @Override
    public CustomerDto deleteCustomer(Long id) {
        return ApiCustomerFactory.fromCustomerToDto(domainCustomerService.deleteCustomer(id));
    }

    @Override
    public CustomerDto getCustomerWithId(Long id) {
        return ApiCustomerFactory.fromCustomerToDto(domainCustomerService.getCustomerWithId(id));
    }

    @Override
    public CustomerDto updateCustomer(Long id, CustomerCommand updatedCustomer) {
        return ApiCustomerFactory.fromCustomerToDto(domainCustomerService.updateCustomer(id, ApiCustomerFactory.fromCommandToCustomer(updatedCustomer)));
    }
    
}
