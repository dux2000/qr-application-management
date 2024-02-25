package com.example.demo.persistence.repository.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.domain.model.Customer;
import com.example.demo.domain.repository.CustomerRepository;
import com.example.demo.persistence.entity.CustomerEntity;



import com.example.demo.persistence.repository.CustomerEntityRepository;
import com.example.demo.persistence.repository.factory.CustomerFactory;


@Repository

public class CustomerRepositoryImpl implements CustomerRepository {

    @Autowired
    private CustomerEntityRepository customerEntityRepository;
    

    @Override
    public List<Customer> getCustomers() {
        return customerEntityRepository.findAll().stream().map(CustomerFactory::fromCustomerEntityToCustomer).toList();
    }

    @Override
    public Customer getCustomerWithId(Long id) {
        return CustomerFactory.fromCustomerEntityToCustomer(testIfCustomerExists(id));
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return CustomerFactory.fromCustomerEntityToCustomer(customerEntityRepository.save(CustomerFactory.fromCustomerToCustomerEntity(customer)));
    }

    @Override
    public Customer deleteCustomer(Long id) {
        CustomerEntity customerEntity = testIfCustomerExists(id);
        
        customerEntity.setDeleted(LocalDateTime.now());
        customerEntityRepository.save(customerEntity);

        return CustomerFactory.fromCustomerEntityToCustomer(customerEntity);
    }
    
    
    @Override
    public Customer updateCustomer(Customer customer) {
        CustomerEntity customerEntity = testIfCustomerExists(customer.getId());
        customerEntity.setName(customer.getName() == null ? customerEntity.getName() : customer.getName());
        customerEntity.setSurname(customer.getSurname() == null ? customerEntity.getSurname() : customer.getSurname());
        return CustomerFactory.fromCustomerEntityToCustomer(customerEntityRepository.save(customerEntity));
    }

    public CustomerEntity testIfCustomerExists(Long id) {
        Optional<CustomerEntity> customerEntity = customerEntityRepository.findById(id);
    
        if(customerEntity.isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is no customer with that id!");
        return customerEntity.get();
    }
}
