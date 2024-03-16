package com.example.demo.persistence.repository.factory;

import com.example.demo.domain.model.Characteristic;
import com.example.demo.persistence.entity.CharacteristicEntity;

public class CharacteristicFactory {
    public static Characteristic fromCharacteristicEntityToCharacteristic(CharacteristicEntity characteristicEntity) {
        if (characteristicEntity == null) return null;

        Characteristic characteristic = new Characteristic();
        characteristic.setCode(characteristicEntity.getCode());
        characteristic.setGlobalCode(characteristicEntity.getGlobalCode());
        characteristic.setValue(characteristicEntity.getValue());

        return characteristic;
    }
    public static CharacteristicEntity fromCharacteristicToEntity(Characteristic characteristic) {
        if (characteristic == null) return null;

        CharacteristicEntity characteristicEntity = new CharacteristicEntity();
        characteristicEntity.setCode(characteristic.getCode());
        characteristicEntity.setGlobalCode(characteristic.getGlobalCode());
        characteristicEntity.setValue(characteristic.getValue());

        return characteristicEntity;
    }
}
