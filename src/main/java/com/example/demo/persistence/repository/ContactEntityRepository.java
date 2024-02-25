package com.example.demo.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.persistence.entity.ContactEntity;

public interface ContactEntityRepository extends JpaRepository<ContactEntity, Long> {
    
}
