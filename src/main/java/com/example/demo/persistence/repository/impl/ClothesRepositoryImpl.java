package com.example.demo.persistence.repository.impl;

import java.util.List;
import java.util.Optional;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.domain.model.Clothes;
import com.example.demo.domain.repository.ClothesRepository;
import com.example.demo.persistence.entity.ClothesEntity;
import com.example.demo.persistence.entity.CustomerEntity;
import com.example.demo.persistence.entity.StatusEntity;
import com.example.demo.persistence.entity.StatusTransitionEntity;
import com.example.demo.persistence.repository.ClothesEntityRepository;
import com.example.demo.persistence.repository.CustomerEntityRepository;
import com.example.demo.persistence.repository.StatusEntityRespository;
import com.example.demo.persistence.repository.StatusTransitionRepository;
import com.example.demo.persistence.repository.factory.ClothesFactory;

import javax.swing.text.html.Option;

@Repository
public class ClothesRepositoryImpl implements ClothesRepository {

    @Autowired
    private ClothesEntityRepository clothesEntityRepository;

    @Autowired
    private CustomerEntityRepository customerEntityRepository;

    @Autowired
    private StatusEntityRespository statusEntityRespository;

    @Autowired
    private StatusTransitionRepository statusTransitionRepository;

    @Override
    public Clothes getClothesWithId(Long id) {
        Optional<ClothesEntity> clothesEntity = clothesEntityRepository.findById(id);

        if (clothesEntity.isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is no clothes with that id!");

        return ClothesFactory.fromClothesEntityToClothes(clothesEntity.get());
    }

    @Override
    public Clothes createClothes(Clothes clothes) {
        Optional<CustomerEntity> customerEntity = customerEntityRepository.findById(clothes.getCustomer().getId());

        if (customerEntity.isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is no customer with that id!");
        
        ClothesEntity clothesEntity = ClothesFactory.fromClothesToClothesEntity(clothes);
        clothesEntity.setCustomer(customerEntity.get());
        StatusEntity statusEntity = statusEntityRespository.findById(Long.valueOf(1)).get();
        clothesEntity.setStatus(statusEntity);
        return ClothesFactory.fromClothesEntityToClothes(clothesEntityRepository.save(clothesEntity));
    }

    @Override
    public void deleteClothes(Long id) {
        clothesEntityRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Clothes changeStatus(Long id, String nextStatusCode) {
        Optional<ClothesEntity> clothesEntity = clothesEntityRepository.findById(id);

        if (clothesEntity.isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is no clothes with that id!");
        
        List<StatusTransitionEntity> statusTransitionEntity = statusTransitionRepository.findByCurrentStatusCode(clothesEntity.get().getStatus().getCode()).stream().filter(statusTransition -> statusTransition.getNextStatus().getCode().equals(nextStatusCode)).toList();
        if (statusTransitionEntity.isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid next status!");

        StatusEntity nextStatus = statusTransitionEntity.get(0).getNextStatus();
        clothesEntity.get().setStatus(nextStatus);


        return ClothesFactory.fromClothesEntityToClothes(clothesEntityRepository.save(clothesEntity.get()));

    }
}
