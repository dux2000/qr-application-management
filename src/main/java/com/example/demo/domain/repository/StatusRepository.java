package com.example.demo.domain.repository;

import com.example.demo.domain.model.Status;

import java.util.List;

public interface StatusRepository {
    List<Status> getStatusTransitions(String statusCode);
}
