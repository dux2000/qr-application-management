package com.example.demo.persistence.repository.impl;

import com.example.demo.domain.model.Characteristic;
import com.example.demo.domain.repository.CharacteristicRepository;
import com.example.demo.persistence.entity.CharacteristicEntity;
import com.example.demo.persistence.entity.ProductEntity;
import com.example.demo.persistence.repository.CharacteristicEntityRepository;
import com.example.demo.persistence.repository.ProductEntityRepository;
import com.example.demo.persistence.repository.factory.CharacteristicFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class CharacteristicRepositoryImpl implements CharacteristicRepository {
    private final CharacteristicEntityRepository characteristicEntityRepository;
    private final ProductEntityRepository productEntityRepository;
    @Override
    public Characteristic createCharacteristic(Characteristic characteristic) {
        Optional<ProductEntity> productEntity = productEntityRepository.findById(UUID.fromString(characteristic.getProduct().getId()));
        CharacteristicEntity characteristicEntity = CharacteristicFactory.fromCharacteristicToEntity(characteristic);
        productEntity.ifPresent(characteristicEntity::setProduct);

        return CharacteristicFactory.fromCharacteristicEntityToCharacteristic(characteristicEntityRepository.save(characteristicEntity));
    }
}
