package com.example.demo.domain.repository;

import com.example.demo.domain.filter.SearchRequest;
import com.example.demo.domain.filter.SearchResponse;
import com.example.demo.domain.model.Customer;

import java.util.List;

public interface CustomerRepository {
    List<Customer> getCustomers();
    SearchResponse<Customer> getCustomersFilter(SearchRequest request);
    Customer getCustomerWithId(Long id);
    Customer createCustomer(Customer customer);
    Customer deleteCustomer(Long id);
    Customer updateCustomer(Customer customer);
}
