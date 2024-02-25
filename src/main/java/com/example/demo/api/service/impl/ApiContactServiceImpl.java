package com.example.demo.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.api.command.ContactCommand;
import com.example.demo.api.dto.ContactDto;
import com.example.demo.api.factory.ApiContactFactory;
import com.example.demo.api.service.ApiContactService;
import com.example.demo.domain.service.ContactService;

@Service
public class ApiContactServiceImpl implements ApiContactService {
    
    @Autowired
    private ContactService domainContactService; 
    
    @Override
    public ContactDto updateContact(Long id, ContactCommand updatedContact) {
        return ApiContactFactory.fromContactToDto(domainContactService.updateContact(id, ApiContactFactory.fromCommandToContact(updatedContact)));
    }

    @Override
    public ContactDto createContact(Long customerId, ContactCommand contact) {
        return ApiContactFactory.fromContactToDto(domainContactService.createContact(customerId, ApiContactFactory.fromCommandToContact(contact)));
    }
    
}
