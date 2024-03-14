package com.example.demo.persistence.repository;

import com.example.demo.persistence.entity.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactEntityRepository extends JpaRepository<ContactEntity, Long> {
    
}
