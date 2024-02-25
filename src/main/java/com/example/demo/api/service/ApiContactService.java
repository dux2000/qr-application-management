package com.example.demo.api.service;

import com.example.demo.api.command.ContactCommand;
import com.example.demo.api.dto.ContactDto;

public interface ApiContactService {
    ContactDto updateContact(Long id, ContactCommand updatedContact);
    ContactDto createContact(Long customerId, ContactCommand contact);
    
}
