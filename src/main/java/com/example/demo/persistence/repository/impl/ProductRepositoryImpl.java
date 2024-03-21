package com.example.demo.persistence.repository.impl;

import com.example.demo.domain.filter.SearchRequest;
import com.example.demo.domain.filter.SearchResponse;
import com.example.demo.domain.model.Product;
import com.example.demo.domain.repository.ProductRepository;
import com.example.demo.persistence.entity.CustomerEntity;
import com.example.demo.persistence.entity.ProductEntity;
import com.example.demo.persistence.entity.StatusEntity;
import com.example.demo.persistence.entity.UserEntity;
import com.example.demo.persistence.repository.CustomerEntityRepository;
import com.example.demo.persistence.repository.ProductEntityRepository;
import com.example.demo.persistence.repository.StatusEntityRepository;
import com.example.demo.persistence.repository.UserEntityRepository;
import com.example.demo.persistence.repository.factory.CustomerFactory;
import com.example.demo.persistence.repository.factory.ProductFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {
    private final ProductEntityRepository productEntityRepository;
    private final CustomerEntityRepository customerEntityRepository;
    private final UserEntityRepository userEntityRepository;
    private final StatusEntityRepository statusEntityRepository;

    @Override
    public SearchResponse<Product> getProductsFilter(SearchRequest request) {
        return request.fetchAndConvert(productEntityRepository, ProductFactory::fromProductEntityToProduct);
    }

    @Override
    public Product createProduct(Product product) {
        Optional<CustomerEntity> customerEntity = customerEntityRepository.findById(product.getCustomer().getId());
        Optional<UserEntity> userEntity = userEntityRepository.findById(product.getUser().getId());
        Optional<StatusEntity> statusEntity = statusEntityRepository.findByCode(product.getStatus().getCode());

        ProductEntity productEntity = ProductFactory.fromProductToEntity(product);
        customerEntity.ifPresent(productEntity::setCustomer);
        userEntity.ifPresent(productEntity::setUser);
        statusEntity.ifPresent(productEntity::setStatus);

        return ProductFactory.fromProductEntityToProduct(productEntityRepository.save(productEntity));
    }
}
