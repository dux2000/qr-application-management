package com.example.demo.domain.service.impl;

import com.example.demo.domain.model.Status;
import com.example.demo.domain.repository.StatusRepository;
import com.example.demo.domain.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusServiceImpl implements StatusService {

    @Autowired
    private StatusRepository statusRepository;
    @Override
    public List<Status> getStatusTransitions(String statusCode) {
        return statusRepository.getStatusTransitions(statusCode);
    }
}
