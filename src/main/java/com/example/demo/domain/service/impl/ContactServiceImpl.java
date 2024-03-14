package com.example.demo.domain.service.impl;

import com.example.demo.domain.model.Contact;
import com.example.demo.domain.model.CustomerReference;
import com.example.demo.domain.repository.ContactRepository;
import com.example.demo.domain.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements ContactService {
 
    @Autowired
    private ContactRepository contactRepository;

    @Override
    public Contact updateContact(Long id, Contact updatedContact) {
        updatedContact.setId(id);
        return contactRepository.updateContact(updatedContact);
    }

    @Override
    public Contact createContact(Long customerId, Contact contact) {
        contact.setCustomer(new CustomerReference(customerId));
        return contactRepository.createContact(contact);
    }
    
}
