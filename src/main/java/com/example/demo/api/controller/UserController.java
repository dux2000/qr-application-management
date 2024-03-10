package com.example.demo.api.controller;

import com.example.demo.api.command.UserCommand;
import com.example.demo.api.dto.UserDto;
import com.example.demo.api.service.ApiUserService;
import com.example.demo.domain.filter.SearchCriteriaCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final ApiUserService apiUserService;
    @PostMapping("/search")
    public Page<UserDto> getUsers(@RequestParam(name = "pageNum", defaultValue = "0") Integer pageNum,
                                  @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                  @RequestBody SearchCriteriaCommand employeeSearchDto) {
        return apiUserService.getUsers(employeeSearchDto, pageNum, pageSize);
    }
    @PostMapping("")
    public UserDto createUser(@RequestBody UserCommand user) {
        return apiUserService.createUser(user);
    }

    @PostMapping("/login")
    public UserDto loginUser(@RequestBody UserCommand user) {
        return apiUserService.loginUser(user);
    }

    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable Long id, @RequestBody UserCommand updatedUser) {
        return apiUserService.updateUser(id, updatedUser);
    }

}
