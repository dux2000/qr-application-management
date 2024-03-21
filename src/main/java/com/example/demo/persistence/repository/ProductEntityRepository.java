package com.example.demo.persistence.repository;

import com.example.demo.persistence.entity.ProductEntity;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface ProductEntityRepository extends JpaRepository<ProductEntity, UUID>, JpaSpecificationExecutor<ProductEntity> {
    @Override
    Page<ProductEntity> findAll(@NonNull Specification<ProductEntity> specification, @NonNull Pageable pageable);
}
