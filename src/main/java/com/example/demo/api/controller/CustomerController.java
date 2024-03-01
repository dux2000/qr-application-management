package com.example.demo.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.api.command.CustomerCommand;
import com.example.demo.api.dto.CustomerDto;
import com.example.demo.api.service.ApiCustomerService;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    @Autowired
    private ApiCustomerService apiCustomerService;
    
    @GetMapping("")
    public List<CustomerDto> getUsers() {
        return apiCustomerService.getUsers();
    }

    @GetMapping("/{id}")
    public CustomerDto getCustomerWithId(@PathVariable Long id) {
        return apiCustomerService.getCustomerWithId(id);
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