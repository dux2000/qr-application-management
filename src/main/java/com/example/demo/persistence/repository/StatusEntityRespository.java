package com.example.demo.persistence.repository;


import com.example.demo.persistence.entity.StatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusEntityRespository extends JpaRepository<StatusEntity, Long>{
}
