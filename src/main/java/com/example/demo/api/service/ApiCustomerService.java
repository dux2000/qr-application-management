package com.example.demo.api.service;

import com.example.demo.api.command.CustomerCommand;
import com.example.demo.api.dto.CustomerDto;
import com.example.demo.domain.filter.SearchRequest;
import com.example.demo.domain.filter.SearchResponse;

import java.util.List;

public interface ApiCustomerService {
    List<CustomerDto> getCustomers();
    CustomerDto getCustomerWithId(Long id);
    SearchResponse<CustomerDto> getCustomersFilter(SearchRequest request);
    CustomerDto createCustomer(CustomerCommand customer);
    CustomerDto deleteCustomer(Long id);
    CustomerDto updateCustomer(Long id, CustomerCommand updatedCustomer);
}