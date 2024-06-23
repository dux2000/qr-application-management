package com.example.demo.persistence.repository.impl;

import com.example.demo.domain.filter.*;
import com.example.demo.domain.model.Product;
import com.example.demo.domain.model.ProductType;
import com.example.demo.domain.model.StatusTransition;
import com.example.demo.domain.repository.ProductRepository;
import com.example.demo.persistence.entity.*;
import com.example.demo.persistence.repository.*;
import com.example.demo.persistence.repository.factory.ProductFactory;
import com.example.demo.persistence.repository.factory.StatusFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {
    private final ProductEntityRepository productEntityRepository;
    private final CustomerEntityRepository customerEntityRepository;
    private final UserEntityRepository userEntityRepository;
    private final StatusEntityRepository statusEntityRepository;
    private final ProductTypeEntityRepository productTypeEntityRepository;
    private final EntityManager entityManager;
    private final UserTypeProductStatusTransitionRepository userTypeProductStatusTransitionRepository;
    @Override
    public Product getProductById(String id) {
        ProductEntity productEntity = productEntityRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is no product with that id!"));

        return ProductFactory.fromProductEntityToProduct(productEntity);
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
        Optional<ProductTypeEntity> productTypeEntity = productTypeEntityRepository.findByCode(product.getType().getCode());

        ProductEntity productEntity = ProductFactory.fromProductToEntity(product);
        customerEntity.ifPresent(productEntity::setCustomer);
        currentUserEntity.ifPresent(productEntity::setCurrentUser);
        statusEntity.ifPresent(productEntity::setStatus);
        createdUserEntity.ifPresent(productEntity::setCreatedBy);

        if (productTypeEntity.isPresent()) {
            productEntity.setType(productTypeEntity.get());
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is no product type with that code!");
        }

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

    @Override
    public List<Product> getProductRevision(SearchRequest request) {
        List<SearchCriteria> searchCriteria = request.getSearchFilter().getSearchCriteria();
        AuditQuery auditQueryCreator = AuditReaderFactory
                .get(entityManager)
                .createQuery()
                .forRevisionsOfEntity(ProductEntity.class, true, false);


        searchCriteria.forEach(criteria -> {
            if (criteria.getFilterKey().equals("currentUser")) {
                auditQueryCreator.add(AuditEntity.relatedId("currentUser").eq(criteria.getValue()));
            }
            if (criteria.getFilterKey().equals("updated")) {
                auditQueryCreator.add(AuditEntity.property("updated").ge(LocalDateTime.parse(criteria.getValue().toString(), DateTimeFormatter.ISO_LOCAL_DATE_TIME)));
            }
            if (criteria.getFilterKey().equals("id")) {
                auditQueryCreator.add(AuditEntity.id().eq(UUID.fromString((String) criteria.getValue())));
            }
        });

        List<ProductEntity> list = auditQueryCreator
                .add(AuditEntity.property("updated").isNotNull())
                .addOrder(AuditEntity.property("updated").desc())
                .getResultList();

        return list.stream().map(ProductFactory::fromProductEntityToProduct).toList();
    }

    @Override
    public List<ProductType> getProductTypes() {
        return productTypeEntityRepository.findAll().stream()
                .map(ProductFactory::toProductType)
                .toList();
    }

    @Override
    public void deleteProduct(String id) {
        productEntityRepository.deleteById(UUID.fromString(id));
    }

    @Override
    public List<StatusTransition> getProductStatusTransitionForUserType(Set<String> userTypes, Set<String> statusCodes) {
        List<StatusEntity> statusEntities = userTypeProductStatusTransitionRepository.findByUserTypeDefinitionAndStatusCode(userTypes, statusCodes);
        return statusEntities.stream().map(status -> new StatusTransition(status.getCode(), status.getName())).toList();
    }
}
