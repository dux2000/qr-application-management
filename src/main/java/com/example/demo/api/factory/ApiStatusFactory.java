package com.example.demo.api.factory;

import com.example.demo.api.dto.StatusDto;
import com.example.demo.domain.model.Status;

public class ApiStatusFactory {
    
    public static StatusDto fromStatusToDto(Status status) {
        if (status == null)
            return null;

        return new StatusDto(
            status.getCode(),
            status.getDescription()
        );
    }
}
