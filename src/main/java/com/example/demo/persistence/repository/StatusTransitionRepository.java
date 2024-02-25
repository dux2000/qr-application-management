package com.example.demo.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.persistence.entity.StatusTransitionEntity;

public interface StatusTransitionRepository extends JpaRepository<StatusTransitionEntity, Long>{
    StatusTransitionEntity findByCurrentStatusCode(String code);
    
}
