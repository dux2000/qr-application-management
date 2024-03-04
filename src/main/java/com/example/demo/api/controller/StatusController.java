package com.example.demo.api.controller;

import com.example.demo.api.dto.StatusDto;
import com.example.demo.api.service.ApiStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/status")
public class StatusController {



    @Autowired
    private ApiStatusService apiStatusService;

    @GetMapping("/{statusCode}")
    public List<StatusDto> getStatusTransitions(@PathVariable String statusCode) {
        return apiStatusService.getStatusTransitions(statusCode);
    }
}
