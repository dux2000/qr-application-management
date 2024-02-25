package com.example.demo.api.factory;

import com.example.demo.api.command.ContactCommand;
import com.example.demo.api.dto.ContactDto;
import com.example.demo.domain.model.Contact;

public class ApiContactFactory {
     
    public static ContactDto fromContactToDto(Contact contact) {
        if (contact == null)
            return null;

        return new ContactDto(contact.getId(), contact.getType(), contact.getContactInfo());
    }
    
    public static Contact fromCommandToContact(ContactCommand contact) {
        if (contact == null) 
            return null;

        return new Contact(null, contact.getType(), contact.getContactInfo(), null);
    }
}
