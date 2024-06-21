package com.example.demo.persistence.repository;

import com.example.demo.persistence.entity.UserTypeDefinitionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTypeDefinitionRepository extends JpaRepository<UserTypeDefinitionEntity, String> {

}
