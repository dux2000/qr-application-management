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
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.order.AuditOrder;
import org.hibernate.query.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {
    private final ProductEntityRepository productEntityRepository;
    private final CustomerEntityRepository customerEntityRepository;
    private final UserEntityRepository userEntityRepository;
    private final StatusEntityRepository statusEntityRepository;
    private final EntityManager entityManager;

    @Override
    public Product getProductById(String id) {
        return productEntityRepository.findById(UUID.fromString(id))
                .map(ProductFactory::fromProductEntityToProduct)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is no product with that id!"));
    }

    @Override
    public List<Product> getProductRevision(String id) {
        List<ProductEntity> list = AuditReaderFactory.get(entityManager).createQuery()
                .forRevisionsOfEntity(ProductEntity.class, true, false)
                .add(AuditEntity.id().eq(UUID.fromString(id)))
                .add(AuditEntity.property("updated").isNotNull())
                .addOrder(AuditEntity.property("updated").desc())
                .getResultList();

        return list.stream().map(ProductFactory::fromProductEntityToProduct).toList();
    }

    @Override
    public SearchResponse<Product> getProductsFilter(SearchRequest request) {
        return request.fetchAndConvert(productEntityRepository, ProductFactory::fromProductEntityToProduct);
    }

    @Override
    public Product createProduct(Product product) {
        Optional<CustomerEntity> customerEntity = customerEntityRepository.findById(product.getCustomer().getId());
        Optional<UserEntity> currentUserEntity = userEntityRepository.findById(product.getCurrentUser().getId());
        Optional<UserEntity> createdUserEntity = userEntityRepository.findById(product.getCreatedBy().getId());
        Optional<StatusEntity> statusEntity = statusEntityRepository.findByCode(product.getStatus().getCode());

        ProductEntity productEntity = ProductFactory.fromProductToEntity(product);
        customerEntity.ifPresent(productEntity::setCustomer);
        currentUserEntity.ifPresent(productEntity::setCurrentUser);
        statusEntity.ifPresent(productEntity::setStatus);
        createdUserEntity.ifPresent(productEntity::setCreatedBy);

        return ProductFactory.fromProductEntityToProduct(productEntityRepository.save(productEntity));
    }

    @Override
    public Product updateProduct(Product product) {
        Optional<ProductEntity> oldProduct = productEntityRepository.findById(UUID.fromString(product.getId()));
        Optional<UserEntity> updatedUserEntity = userEntityRepository.findById(product.getUpdatedBy().getId());

        if (oldProduct.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is no product with that id!");
        }

        oldProduct.get().setName(product.getName() == null ? oldProduct.get().getName() : product.getName());
        oldProduct.get().setDescription(product.getDescription() == null ? oldProduct.get().getDescription() : product.getDescription());
        updatedUserEntity.ifPresent(oldProduct.get()::setUpdatedBy);
        oldProduct.get().setUpdated(product.getUpdated());

        if (product.getCurrentUser().getId() != null) {
            Optional<UserEntity> currentUserEntity = userEntityRepository.findById(product.getCurrentUser().getId());
            currentUserEntity.ifPresent(oldProduct.get()::setCurrentUser);
        }

        if (product.getStatus() != null) {
            Optional<StatusEntity> statusEntity = statusEntityRepository.findByCode(product.getStatus().getCode());
            statusEntity.ifPresent(oldProduct.get()::setStatus);
        }

        return ProductFactory.fromProductEntityToProduct(productEntityRepository.save(oldProduct.get()));
    }
}
