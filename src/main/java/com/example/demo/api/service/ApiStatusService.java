package com.example.demo.api.service;

import com.example.demo.api.dto.StatusDto;

import java.util.List;


public interface ApiStatusService {
    List<StatusDto> getStatusTransitions(String statusCode);
}
