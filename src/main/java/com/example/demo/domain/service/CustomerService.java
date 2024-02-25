package com.example.demo.domain.service;

import java.util.List;

import com.example.demo.domain.model.Customer;

public interface CustomerService {
    List<Customer> getCustomers();
    Customer getCustomerWithId(Long id);
    Customer createCustomer(Customer customer);
    Customer deleteCustomer(Long id);
    Customer updateCustomer(Long id, Customer updatedCustomer);
}
