package com.example.demo.api.factory;

import com.example.demo.api.command.CharacteristicCommand;
import com.example.demo.api.command.ProductCommand;
import com.example.demo.api.dto.*;
import com.example.demo.domain.model.*;

public class ApiProductFactory {
    public static Product fromProductCommandToProduct(ProductCommand productCommand) {
        if (productCommand == null) return null;

        Product product = new Product();
        product.setName(productCommand.getName());
        product.setDescription(productCommand.getDescription());
        product.setCustomer(new CustomerReference(productCommand.getCustomerId()));
        product.setCurrentUser(new UserReference(productCommand.getCurrentUserId()));
        product.setStatus(new Status(productCommand.getStatus()));
        if (productCommand.getCharacteristics() != null && !productCommand.getCharacteristics().isEmpty())
            product.setCharacteristics(productCommand.getCharacteristics().stream().map(ApiProductFactory::toCharacteristic).toList());
        if (productCommand.getType() != null)
            product.setType(new ProductType(productCommand.getType(), null));

        return product;
    }

    public static ProductDto fromProductToDto(Product product) {
        if (product == null) return null;

        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setStatus(ApiStatusFactory.fromStatusToDto(product.getStatus()));
        productDto.setCreated(product.getCreated());
        productDto.setUpdated(product.getUpdated());
        productDto.setCustomer(new CustomerReferenceDto(product.getCustomer().getId(), product.getCustomer().getFullName()));
        productDto.setCurrentUser(new UserReferenceDto(product.getCurrentUser().getId(), product.getCurrentUser().getFullName()));
        productDto.setCreatedBy(new UserReferenceDto(product.getCreatedBy().getId(), product.getCreatedBy().getFullName()));
        if (product.getUpdatedBy() != null) {
            productDto.setUpdatedBy(new UserReferenceDto(product.getUpdatedBy().getId(), product.getUpdatedBy().getFullName()));
        }
        if (product.getCharacteristics() != null && !product.getCharacteristics().isEmpty())
            productDto.setCharacteristics(product.getCharacteristics().stream().map(ApiProductFactory::toCharacteristicDto).toList());
        if (product.getType() != null)
            productDto.setType(toProductTypeDto(product.getType()));

        return productDto;
    }

    public static Characteristic toCharacteristic(CharacteristicCommand characteristicCommand) {
        if (characteristicCommand == null) return null;
        Characteristic characteristic = new Characteristic();
        characteristic.setCode(characteristicCommand.getCode());
        characteristic.setGlobalCode(characteristicCommand.getGlobalCode());
        characteristic.setValue(characteristicCommand.getValue());

        return characteristic;
    }

    public static CharacteristicDto toCharacteristicDto(Characteristic characteristic) {
        if (characteristic == null) return null;

        CharacteristicDto characteristicDto = new CharacteristicDto();
        characteristicDto.setCode(characteristic.getCode());
        characteristicDto.setGlobalCode(characteristic.getGlobalCode());
        characteristicDto.setValue(characteristic.getValue());

        return characteristicDto;
    }

    public static ProductTypeDto toProductTypeDto(ProductType productType) {
        if (productType == null) return null;

        ProductTypeDto productTypeDto = new ProductTypeDto();
        productTypeDto.setCode(productType.getCode());
        productTypeDto.setName(productType.getName());

        return productTypeDto;
    }
}
