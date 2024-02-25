package com.example.demo.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.api.command.ContactCommand;
import com.example.demo.api.dto.ContactDto;
import com.example.demo.api.service.ApiContactService;

@RestController
@RequestMapping("/api/v1/contact")
public class ContactController {

    @Autowired
    private ApiContactService apiContactService;
    
    @PostMapping("/{customerId}")
    public ContactDto createdContact(@PathVariable Long customerId, @RequestBody ContactCommand contactCommand) {
        return apiContactService.createContact(customerId, contactCommand);
    }

    @PutMapping("/{id}")
    public ContactDto updateContact(@PathVariable Long id, @RequestBody ContactCommand updatedContact) {
        return apiContactService.updateContact(id, updatedContact);
    }
}
