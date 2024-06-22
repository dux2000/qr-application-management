package com.example.demo.api.controller;

import com.example.demo.api.dto.CommonResourceDto;
import com.example.demo.api.service.CommonResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/common")
@RequiredArgsConstructor
public class CommonResourceController {
    private final CommonResourceService commonResourceService;
    @GetMapping("")
    public CommonResourceDto getCommonResource() {
        return commonResourceService.getCommonResource();
    }
}
