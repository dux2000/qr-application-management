package com.example.demo.persistence.repository;

import com.example.demo.persistence.entity.CustomerEntity;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CustomerEntityRepository extends JpaRepository<CustomerEntity, Long>, JpaSpecificationExecutor<CustomerEntity> {
    @NonNull
    Page<CustomerEntity> findAll(@NonNull Specification<CustomerEntity> specification, @NonNull Pageable pageable);
}
