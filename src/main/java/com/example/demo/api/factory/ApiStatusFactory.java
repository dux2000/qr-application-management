package com.example.demo.api.factory;

import com.example.demo.api.dto.StatusDto;
import com.example.demo.domain.model.Status;

public class ApiStatusFactory {
    
    public static StatusDto fromStatusToDto(Status status) {
        if (status == null)
            return null;

        StatusDto statusDto = new StatusDto();
        statusDto.setCode(status.getCode());
        statusDto.setDescription(status.getDescription());
        statusDto.setName(status.getName());

        return statusDto;
    }
}
