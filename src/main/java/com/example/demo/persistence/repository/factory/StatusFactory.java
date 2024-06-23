package com.example.demo.persistence.repository.factory;

import com.example.demo.domain.model.Status;
import com.example.demo.domain.model.StatusTransition;
import com.example.demo.persistence.entity.StatusEntity;
import com.example.demo.persistence.entity.StatusTransitionEntity;

public class StatusFactory {

    public static Status fromStatusEntityToStatus(StatusEntity statusEntity) {
        if (statusEntity == null)
            return  null;

        Status status = new Status();
        status.setCode(statusEntity.getCode());
        status.setDescription(statusEntity.getDescription());
        status.setName(statusEntity.getName());
        status.setTransitions(statusEntity.getTransitions() == null ? null : statusEntity.getTransitions().stream().map(StatusFactory::fromStatusTransitionEntityToStatus).toList());

        return status;
    }

    private static StatusTransition fromStatusTransitionEntityToStatus(StatusTransitionEntity statusTransitionEntity) {
        if (statusTransitionEntity == null)
            return null;

        return StatusTransition.builder()
                .code(statusTransitionEntity.getNextStatus().getCode())
                .name(statusTransitionEntity.getNextStatus().getName())
                .build();
    }
}
