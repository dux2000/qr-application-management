package com.example.demo.domain.repository;

import java.util.List;

import com.example.demo.domain.model.Customer;

public interface CustomerRepository {
    List<Customer> getCustomers();
    Customer getCustomerWithId(Long id);
    Customer createCustomer(Customer customer);
    Customer deleteCustomer(Long id);
    Customer updateCustomer(Customer customer);
}
