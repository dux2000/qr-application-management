package com.example.demo.domain.service;

import com.example.demo.domain.model.Status;

import java.util.List;

public interface StatusService {
    List<Status> getStatusTransitions(String statusCode);
}
