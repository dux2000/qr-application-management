package com.example.demo.persistence.repository.factory;

import com.example.demo.domain.model.Customer;
import com.example.demo.persistence.entity.CustomerEntity;

public class CustomerFactory {

    public static Customer fromCustomerEntityToCustomer(CustomerEntity customerEntity) {
        if (customerEntity == null)
            return null;

        return new Customer(
                customerEntity.getId(), 
                customerEntity.getName(), 
                customerEntity.getSurname(),
                customerEntity.getClothes() == null ? null : customerEntity.getClothes().stream().map(ClothesFactory::fromClothesEntityToClothes).toList(),
                customerEntity.getContacts() == null ? null : customerEntity.getContacts().stream().map(ContactFactory::fromContactEntityToContact).toList()
            );
    }

    public static CustomerEntity fromCustomerToCustomerEntity(Customer customer) {
        if (customer == null) 
            return null;
        
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setId(customer.getId());
        customerEntity.setName(customer.getName());
        customerEntity.setSurname(customer.getSurname());

        return customerEntity;
        
    }
}