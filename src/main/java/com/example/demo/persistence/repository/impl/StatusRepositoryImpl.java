package com.example.demo.persistence.repository.impl;

import com.example.demo.domain.model.Status;
import com.example.demo.domain.repository.StatusRepository;
import com.example.demo.persistence.entity.StatusTransitionEntity;
import com.example.demo.persistence.repository.StatusTransitionRepository;
import com.example.demo.persistence.repository.factory.StatusFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StatusRepositoryImpl implements StatusRepository {
    @Autowired
    private StatusTransitionRepository statusTransitionRepository;
    @Override
    public List<Status> getStatusTransitions(String statusCode) {
        List<StatusTransitionEntity> statusTransitionEntities = statusTransitionRepository.findByCurrentStatusCode(statusCode);
        return statusTransitionEntities.stream().map(statusTransitionEntity -> StatusFactory.fromStatusEntityToStatus(statusTransitionEntity.getNextStatus())).toList();
    }
}
