package com.example.demo.persistence.repository;

import com.example.demo.persistence.entity.StatusEntity;
import com.example.demo.persistence.entity.UserTypeProductStatusTransitionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface UserTypeProductStatusTransitionRepository extends JpaRepository<UserTypeProductStatusTransitionEntity, Integer> {
    @Query("SELECT t.status FROM UserTypeProductStatusTransitionEntity t WHERE t.userTypeDefinition.code IN :typeCodes AND t.status.code IN :statusCodes")
    List<StatusEntity> findByUserTypeDefinitionAndStatusCode(Set<String> typeCodes, Set<String> statusCodes);
}
