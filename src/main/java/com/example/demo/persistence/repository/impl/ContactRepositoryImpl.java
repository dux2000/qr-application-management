package com.example.demo.persistence.repository.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.domain.model.Contact;
import com.example.demo.domain.repository.ContactRepository;
import com.example.demo.persistence.entity.ContactEntity;
import com.example.demo.persistence.entity.CustomerEntity;
import com.example.demo.persistence.repository.ContactEntityRepository;
import com.example.demo.persistence.repository.CustomerEntityRepository;
import com.example.demo.persistence.repository.factory.ContactFactory;

@Repository
public class ContactRepositoryImpl implements ContactRepository {

    @Autowired
    private ContactEntityRepository contactEntityRepository;

    @Autowired
    private CustomerEntityRepository customerEntityRepository;

    @Override
    public Contact createContact(Contact contact) {
        Optional<CustomerEntity> customerEntity = customerEntityRepository.findById(contact.getCustomer().getId());

        if (customerEntity.isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is no customer with that id!");

        ContactEntity contactEntity = ContactFactory.fromContactToContactEntity(contact);
        contactEntity.setCustomer(customerEntity.get());
        return ContactFactory.fromContactEntityToContact(contactEntityRepository.save(contactEntity));
    }

    @Override
    public Contact updateContact(Contact contact) {
        Optional<ContactEntity> contactEntity = contactEntityRepository.findById(contact.getId());
        
        if (contactEntity.isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is no contact with that id!");

        contactEntity.get().setType(contact.getType() == null ? contactEntity.get().getType() : contact.getType());
        contactEntity.get().setContactInfo(contact.getContactInfo() == null ? contactEntity.get().getContactInfo() : contact.getContactInfo());

        return ContactFactory.fromContactEntityToContact(contactEntityRepository.save(contactEntity.get()));
    }

    

    
}