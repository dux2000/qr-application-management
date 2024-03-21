package com.example.demo.persistence.repository.factory;

import com.example.demo.domain.model.CustomerReference;
import com.example.demo.domain.model.Product;
import com.example.demo.domain.model.ProductReference;
import com.example.demo.domain.model.UserReference;
import com.example.demo.persistence.entity.ProductEntity;

public class ProductFactory {
    public static Product fromProductEntityToProduct(ProductEntity productEntity) {
        if (productEntity == null) return null;

        Product product = new Product();
        product.setId(productEntity.getId().toString());
        product.setName(productEntity.getName());
        product.setDescription(productEntity.getDescription());
        product.setStatus(StatusFactory.fromStatusEntityToStatus(productEntity.getStatus()));
        product.setCustomer(new CustomerReference(productEntity.getCustomer().getId()));
        product.setUser(new UserReference(productEntity.getUser().getId(), productEntity.getUser().getFullName()));
        product.setCreated(productEntity.getCreated());
        product.setUpdated(productEntity.getUpdated());

        if (productEntity.getCharacteristics() != null && !productEntity.getCharacteristics().isEmpty()) {
            product.setCharacteristics(productEntity.getCharacteristics().stream().map(CharacteristicFactory::fromCharacteristicEntityToCharacteristic).toList());
        }

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
}
