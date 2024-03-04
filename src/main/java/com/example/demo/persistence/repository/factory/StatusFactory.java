package com.example.demo.persistence.repository.factory;

import com.example.demo.domain.model.Status;
import com.example.demo.persistence.entity.StatusEntity;

public class StatusFactory {

    public static Status fromStatusEntityToStatus(StatusEntity statusEntity) {
        if (statusEntity == null)
            return  null;

        Status status = new Status();
        status.setCode(statusEntity.getCode());
        status.setDescription(statusEntity.getDescription());

        return status;
    }
}
