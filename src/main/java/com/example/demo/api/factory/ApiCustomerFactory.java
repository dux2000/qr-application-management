package com.example.demo.api.factory;

import com.example.demo.api.command.CustomerCommand;
import com.example.demo.api.dto.CustomerDto;
import com.example.demo.domain.model.Customer;

public class ApiCustomerFactory {
    
    public static CustomerDto fromCustomerToDto(Customer customer) {
        if (customer == null) 
            return null;
        
        return new CustomerDto(
            customer.getId(), 
            customer.getFullName(),
            customer.getContacts() == null ? null : customer.getContacts().stream().map(ApiContactFactory::fromContactToDto).toList());
    }
    
    public static Customer fromCommandToCustomer(CustomerCommand customerCommand) {
        if (customerCommand == null) {
            return null;
        }

        return new Customer(
            null, 
            customerCommand.getFullName(),
            customerCommand.getContacts() == null ? null : customerCommand.getContacts().stream().map(ApiContactFactory::fromCommandToContact).toList()
        );
    }
    
}
