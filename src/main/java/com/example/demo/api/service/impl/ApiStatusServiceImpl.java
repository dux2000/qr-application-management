package com.example.demo.api.service.impl;

import com.example.demo.api.dto.StatusDto;
import com.example.demo.api.factory.ApiStatusFactory;
import com.example.demo.api.service.ApiStatusService;
import com.example.demo.domain.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiStatusServiceImpl implements ApiStatusService {

    @Autowired
    private StatusService statusService;
    @Override
    public List<StatusDto> getStatusTransitions(String statusCode) {
        return statusService.getStatusTransitions(statusCode).stream().map(ApiStatusFactory::fromStatusToDto).toList();
    }
}
