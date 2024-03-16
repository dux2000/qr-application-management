package com.example.demo.persistence.repository;


import com.example.demo.persistence.entity.StatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StatusEntityRepository extends JpaRepository<StatusEntity, Long>{
    Optional<StatusEntity> findByCode(String code);

}
