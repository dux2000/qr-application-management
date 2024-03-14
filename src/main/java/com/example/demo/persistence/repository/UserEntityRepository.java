package com.example.demo.persistence.repository;

import com.example.demo.persistence.entity.UserEntity;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface UserEntityRepository extends JpaRepository<UserEntity, Long>, JpaSpecificationExecutor<UserEntity> {

    @NonNull
    Page<UserEntity> findAll(@NonNull Specification<UserEntity> specification, @NonNull Pageable pageable);
    Optional<UserEntity> findByUsername(String username);
}
