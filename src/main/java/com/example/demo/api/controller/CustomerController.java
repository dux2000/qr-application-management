package com.example.demo.api.controller;

import com.example.demo.api.command.CustomerCommand;
import com.example.demo.api.dto.CustomerDto;
import com.example.demo.api.service.ApiCustomerService;
import com.example.demo.domain.filter.SearchRequest;
import com.example.demo.domain.filter.SearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final ApiCustomerService apiCustomerService;
    
    @GetMapping("")
    public List<CustomerDto> getCustomers() {
        return apiCustomerService.getCustomers();
    }

    @GetMapping("/{id}")
    public CustomerDto getCustomerWithId(@PathVariable Long id) {
        return apiCustomerService.getCustomerWithId(id);
    }

    @PostMapping("/filter")
    public SearchResponse<CustomerDto> getCustomers(@RequestBody SearchRequest request) {
        return apiCustomerService.getCustomersFilter(request);
    }
    @PostMapping("")
    public CustomerDto createCustomer(@RequestBody CustomerCommand customer) {
        return apiCustomerService.createCustomer(customer);
    }

    @DeleteMapping("/{id}")
    public CustomerDto deleteCustomer(@PathVariable Long id) {
        return apiCustomerService.deleteCustomer(id);
    }

    @PutMapping("/{id}")
    public CustomerDto updateCustomer(@PathVariable Long id, @RequestBody CustomerCommand updatedCustomer) {
        return apiCustomerService.updateCustomer(id, updatedCustomer);
    }
}