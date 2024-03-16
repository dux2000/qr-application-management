package com.example.demo.persistence.repository;

import com.example.demo.persistence.entity.CharacteristicEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CharacteristicEntityRepository extends JpaRepository<CharacteristicEntity, UUID> {
}
