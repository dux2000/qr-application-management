package com.example.demo.domain.service;

import com.example.demo.domain.model.Contact;

public interface ContactService {
    Contact updateContact(Long id, Contact updatedContact);
    Contact createContact(Long customerId, Contact contact);
}
