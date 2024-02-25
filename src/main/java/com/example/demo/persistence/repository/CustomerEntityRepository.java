package com.example.demo.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.persistence.entity.CustomerEntity;

public interface CustomerEntityRepository extends JpaRepository<CustomerEntity, Long> {
    
}
