package com.example.demo.api.service.impl;

import com.example.demo.api.command.CustomerCommand;
import com.example.demo.api.dto.CustomerDto;
import com.example.demo.api.factory.ApiCustomerFactory;
import com.example.demo.api.service.ApiCustomerService;
import com.example.demo.domain.filter.SearchRequest;
import com.example.demo.domain.filter.SearchResponse;
import com.example.demo.domain.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApiCustomerServiceImpl implements ApiCustomerService {

    private final CustomerService domainCustomerService;

    @Override
    public List<CustomerDto> getCustomers() {
        return domainCustomerService.getCustomers().stream().map(ApiCustomerFactory::fromCustomerToDto).toList();
    }

    @Override
    public CustomerDto getCustomerWithId(Long id) {
        return ApiCustomerFactory.fromCustomerToDto(domainCustomerService.getCustomerWithId(id));
    }

    @Override
    public SearchResponse<CustomerDto> getCustomersFilter(SearchRequest request) {
        return domainCustomerService.getCustomersFilter(request).convert(ApiCustomerFactory::fromCustomerToDto);
    }

    @Override
    public CustomerDto createCustomer(CustomerCommand customer) {
        return ApiCustomerFactory.fromCustomerToDto(domainCustomerService.createCustomer(ApiCustomerFactory.fromCommandToCustomer(customer)));
    }

    @Override
    public CustomerDto updateCustomer(Long id, CustomerCommand updatedCustomer) {
        return ApiCustomerFactory.fromCustomerToDto(domainCustomerService.updateCustomer(id, ApiCustomerFactory.fromCommandToCustomer(updatedCustomer)));
    }

    @Override
    public CustomerDto deleteCustomer(Long id) {
        return ApiCustomerFactory.fromCustomerToDto(domainCustomerService.deleteCustomer(id));
    }
}
