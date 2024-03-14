package com.example.demo.persistence.repository;

import com.example.demo.persistence.entity.ClothesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClothesEntityRepository extends JpaRepository<ClothesEntity, Long>{
    
}
