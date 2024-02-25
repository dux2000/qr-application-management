package com.example.demo.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.persistence.entity.ClothesEntity;

public interface ClothesEntityRepository extends JpaRepository<ClothesEntity, Long>{
    
}
