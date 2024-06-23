package com.example.demo.api.factory;

import com.example.demo.api.dto.StatusDto;
import com.example.demo.api.dto.StatusTransitionDto;
import com.example.demo.domain.model.Status;
import com.example.demo.domain.model.StatusTransition;

import java.util.List;

public class ApiStatusFactory {
    
    public static StatusDto fromStatusToDto(Status status) {
        if (status == null)
            return null;

        StatusDto statusDto = new StatusDto();
        statusDto.setCode(status.getCode());
        statusDto.setDescription(status.getDescription());
        statusDto.setName(status.getName());

        if (status.getTransitions() != null && !status.getTransitions().isEmpty()) {
            statusDto.setTransitions(status.getTransitions().stream().map(ApiStatusFactory::fromStatusTransitionToDto).toList());
        }

        return statusDto;
    }

    private static StatusTransitionDto fromStatusTransitionToDto(StatusTransition statusTransition) {
        if (statusTransition == null)
            return null;

        StatusTransitionDto statusTransitionDto = new StatusTransitionDto();
        statusTransitionDto.setCode(statusTransition.getCode());
        statusTransitionDto.setName(statusTransition.getName());

        return statusTransitionDto;
    }
}
