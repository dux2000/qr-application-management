package com.example.demo.domain.service.impl;

import com.example.demo.domain.filter.SearchRequest;
import com.example.demo.domain.filter.SearchResponse;
import com.example.demo.domain.model.*;
import com.example.demo.domain.repository.CharacteristicRepository;
import com.example.demo.domain.repository.ProductRepository;
import com.example.demo.domain.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CharacteristicRepository characteristicRepository;

    @Override
    public Product getProductById(String id) {
        return productRepository.getProductById(id);
    }

    @Override
    public List<Product> getProductRevision(String id) {
        return productRepository.getProductRevision(id);
    }

    @Override
    public SearchResponse<Product> getProductsFilter(SearchRequest request) {
        return productRepository.getProductsFilter(request);
    }

    @Override
    public Product createProduct(Long userId, Product product) {
        product.setCreatedBy(new UserReference(userId));
        product.setCurrentUser(new UserReference(userId));
        product.setCreated(LocalDateTime.now());
        product.setStatus(new Status("CREATED"));
        Product createdProduct = productRepository.createProduct(product);
        saveCharacteristics(createdProduct, product.getCharacteristics());

        return createdProduct;
    }

    @Override
    public Product updateProduct(String id, Long userId, Product product) {
        product.setId(id);
        product.setUpdatedBy(new UserReference(userId));
        product.setUpdated(LocalDateTime.now());

        return productRepository.updateProduct(product);
    }

    private void saveCharacteristics(Product product, List<Characteristic> characteristics) {
        if (characteristics == null) return;

        List<Characteristic> characteristicsList = new ArrayList<>();
        characteristics.forEach(x -> {
            x.setProduct(new ProductReference(product.getId()));
            characteristicsList.add(characteristicRepository.createCharacteristic(x));
        });

        product.setCharacteristics(characteristicsList);
    }
}
