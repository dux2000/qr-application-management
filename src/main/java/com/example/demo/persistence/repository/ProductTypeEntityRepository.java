package com.example.demo.persistence.repository;

import com.example.demo.persistence.entity.ProductTypeEntity;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface ProductTypeEntityRepository extends JpaRepository<ProductTypeEntity, Long>, JpaSpecificationExecutor<ProductTypeEntity> {
    @Override
    Page<ProductTypeEntity> findAll(@NonNull Specification<ProductTypeEntity> specification, @NonNull Pageable pageable);

    Optional<ProductTypeEntity> findByCode(String code);
}
