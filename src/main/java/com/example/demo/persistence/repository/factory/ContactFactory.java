package com.example.demo.persistence.repository.factory;

import com.example.demo.domain.model.Contact;
import com.example.demo.persistence.entity.ContactEntity;

public class ContactFactory {
    
    public static Contact fromContactEntityToContact(ContactEntity contactEntity) {
        if (contactEntity == null) 
            return null;
        
        return new Contact(contactEntity.getId(), contactEntity.getType(), contactEntity.getContactInfo(), null);
    }

    public static ContactEntity fromContactToContactEntity(Contact contact) {
        if (contact == null) 
            return null; 
        
        return new ContactEntity(
            null,
            contact.getType(),
            contact.getContactInfo(),
            null
            );
    }
}
