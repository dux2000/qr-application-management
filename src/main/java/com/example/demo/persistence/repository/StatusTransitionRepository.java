package com.example.demo.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.persistence.entity.StatusTransitionEntity;

import java.util.List;

public interface StatusTransitionRepository extends JpaRepository<StatusTransitionEntity, Long>{
    List<StatusTransitionEntity> findByCurrentStatusCode(String code);
    
}
