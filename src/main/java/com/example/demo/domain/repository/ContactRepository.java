package com.example.demo.domain.repository;

import com.example.demo.domain.model.Contact;

public interface ContactRepository {
    Contact createContact(Contact contact);
    Contact updateContact(Contact contact);
}
