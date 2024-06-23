package com.example.demo.domain.service.impl;

import com.example.demo.domain.filter.SearchRequest;
import com.example.demo.domain.filter.SearchResponse;
import com.example.demo.domain.model.Contact;
import com.example.demo.domain.model.Customer;
import com.example.demo.domain.model.CustomerReference;
import com.example.demo.domain.repository.ContactRepository;
import com.example.demo.domain.repository.CustomerRepository;
import com.example.demo.domain.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final ContactRepository contactRepository;

    @Override
    public List<Customer> getCustomers() {
        return customerRepository.getCustomers();
    }

    @Override
    public SearchResponse<Customer> getCustomersFilter(SearchRequest request) {
        return customerRepository.getCustomersFilter(request);
    }

    @Override
    public Customer getCustomerWithId(Long id) {
        return customerRepository.getCustomerWithId(id);
    }

    @Override
    public Customer createCustomer(Customer customer) {
        validateCustomer(customer);
        Customer createdCustomer = customerRepository.createCustomer(customer);
        saveContacts(createdCustomer, customer.getContacts());
        return createdCustomer;
    }

    @Override
    public Customer deleteCustomer(Long id) {
        return customerRepository.deleteCustomer(id);
    }


    private void saveContacts(Customer customer, List<Contact> contacts) {
        if (contacts == null || customer == null || contacts.isEmpty()) return;

        List<Contact> createdContacts = new ArrayList<>();
        contacts.forEach(x -> {
            x.setCustomer(new CustomerReference(customer.getId()));
            createdContacts.add(contactRepository.createContact(x));
        });

        customer.setContacts(createdContacts);
    }

    @Override
    public Customer updateCustomer(Long id, Customer updatedCustomer) {
        validateCustomer(updatedCustomer);
        updatedCustomer.setId(id);
        return customerRepository.updateCustomer(updatedCustomer);
    }

    private void validateCustomer(Customer customer) {
        if (customer == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Customer must not be null.");
        } else if(customer.getFullName() == null || customer.getFullName().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Customer name attribute must not be empty.");
        }
    }
}
