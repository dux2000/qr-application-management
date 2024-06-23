package com.example.demo.domain.service.impl;

import com.example.demo.domain.filter.SearchRequest;
import com.example.demo.domain.filter.SearchResponse;
import com.example.demo.domain.model.*;
import com.example.demo.domain.repository.CharacteristicRepository;
import com.example.demo.domain.repository.ProductRepository;
import com.example.demo.domain.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CharacteristicRepository characteristicRepository;

    @Override
    public Product getProductById(String id) {
        Product product = productRepository.getProductById(id);

        Set<String> userTypes = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet());
        Set<String> statusCodes = product.getStatus().getTransitions().stream().map(StatusTransition::getCode).collect(Collectors.toSet());
        List<StatusTransition> statusTransitions = productRepository.getProductStatusTransitionForUserType(userTypes, statusCodes);

        Status status = product.getStatus();
        status.setTransitions(statusTransitions);

        return product;
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

    @Override
    public List<Product> getProductRevision(SearchRequest request) {
        return productRepository.getProductRevision(request);
    }

    @Override
    public List<ProductType> getProductTypes() {
        return productRepository.getProductTypes();
    }

    @Override
    public void deleteProduct(String id) {
        productRepository.deleteProduct(id);
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
