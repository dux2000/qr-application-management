package com.example.demo.domain.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.domain.model.Clothes;
import com.example.demo.domain.model.Contact;
import com.example.demo.domain.model.Customer;
import com.example.demo.domain.model.CustomerReference;
import com.example.demo.domain.repository.ClothesRepository;
import com.example.demo.domain.repository.ContactRepository;
import com.example.demo.domain.repository.CustomerRepository;
import com.example.demo.domain.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
    
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ClothesRepository clothesRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public List<Customer> getCustomers() {
        return customerRepository.getCustomers();
    }

    @Override
    public Customer getCustomerWithId(Long id) {
        return customerRepository.getCustomerWithId(id);
    }

    @Override
    public Customer createCustomer(Customer customer) {
        validateCustomer(customer);
        Customer createdCustomer = customerRepository.createCustomer(customer);
        saveClothes(createdCustomer, customer.getClothes());
        saveContacts(createdCustomer, customer.getContacts());
        return createdCustomer;
    }

    @Override
    public Customer deleteCustomer(Long id) {
        return customerRepository.deleteCustomer(id);
    }

    private void saveClothes(Customer customer, List<Clothes> clothes) {
        if (clothes == null || customer == null || clothes.isEmpty()) return;

        List<Clothes> createdClothes = new ArrayList<>();
        clothes.forEach(x -> {
            x.setCustomer(new CustomerReference(customer.getId()));
            createdClothes.add(clothesRepository.createClothes(x));
        });

        customer.setClothes(createdClothes);
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
        } else if(customer.getName() == null || customer.getName().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Customer name attribute must not be empty.");
        } else if (customer.getSurname() == null || customer.getSurname().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Customer surname attribute must not be empty.");
        }
    }
}
