package com.example.demo.persistence.repository.factory;

import com.example.demo.domain.model.*;
import com.example.demo.persistence.entity.ProductEntity;
import com.example.demo.persistence.entity.ProductTypeEntity;

public class ProductFactory {
    public static Product fromProductEntityToProduct(ProductEntity productEntity) {
        if (productEntity == null) return null;

        Product product = new Product();
        product.setId(productEntity.getId().toString());
        product.setName(productEntity.getName());
        product.setDescription(productEntity.getDescription());
        product.setStatus(StatusFactory.fromStatusEntityToStatus(productEntity.getStatus()));
        product.setCustomer(new CustomerReference(productEntity.getCustomer().getId(), productEntity.getCustomer().getFullName()));
        product.setCurrentUser(new UserReference(productEntity.getCurrentUser().getId(), productEntity.getCurrentUser().getFullName(), productEntity.getCurrentUser().getRole()));
        product.setCreated(productEntity.getCreated());
        product.setUpdated(productEntity.getUpdated());
        product.setCreatedBy(new UserReference(productEntity.getCreatedBy().getId(), productEntity.getCreatedBy().getFullName(), productEntity.getCreatedBy().getRole()));
        if (productEntity.getUpdatedBy() != null) {
            product.setUpdatedBy( new UserReference(productEntity.getUpdatedBy().getId(), productEntity.getUpdatedBy().getFullName(), productEntity.getUpdatedBy().getRole()));
        }
        if (productEntity.getCharacteristics() != null && !productEntity.getCharacteristics().isEmpty()) {
            product.setCharacteristics(productEntity.getCharacteristics().stream().map(CharacteristicFactory::fromCharacteristicEntityToCharacteristic).toList());
        }
       product.setType(toProductType(productEntity.getType()));
        return product;
    }

    public static ProductEntity fromProductToEntity(Product product) {
        if (product == null) return null;

        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(product.getName());
        productEntity.setDescription(product.getDescription());
        productEntity.setCreated(product.getCreated());
        productEntity.setUpdated(product.getUpdated());

        return productEntity;
    }

    public static ProductType toProductType(ProductTypeEntity productTypeEntity) {
        if (productTypeEntity == null) return null;

        ProductType productType = new ProductType();
        productType.setCode(productTypeEntity.getCode());
        productType.setName(productTypeEntity.getName());

        return productType;
    }
}
