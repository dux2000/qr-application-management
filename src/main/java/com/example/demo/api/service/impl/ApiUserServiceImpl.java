package com.example.demo.api.service.impl;

import com.example.demo.api.command.UserCommand;
import com.example.demo.api.dto.UserDto;
import com.example.demo.api.factory.ApiUserFactory;
import com.example.demo.api.service.ApiUserService;
import com.example.demo.domain.filter.SearchCriteria;
import com.example.demo.domain.filter.SearchCriteriaCommand;
import com.example.demo.domain.filter.SpecificationBuilder;
import com.example.demo.domain.service.UserService;
import com.example.demo.persistence.entity.UserEntity;
import com.example.demo.persistence.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApiUserServiceImpl implements ApiUserService {
    private final UserService userService;
    private final UserEntityRepository userEntityRepository;

    @Override
    public Page<UserDto> getUsers(SearchCriteriaCommand searchCriteriaCommand, Integer pageNum, Integer pageSize) {
        SpecificationBuilder<UserEntity> builder = new SpecificationBuilder<>();
        List<SearchCriteria> criteriaList =
                searchCriteriaCommand.getSearchCriteriaList();

        if(criteriaList != null){
            criteriaList.forEach(x->
            {x.setDataOption(searchCriteriaCommand
                    .getDataOption());
                builder.with(x);
            });
        }

        Pageable page = PageRequest.of(pageNum, pageSize);
        Page<UserEntity> users = userEntityRepository.findAll(builder.build(), page);
        return null;
    }

    @Override
    public UserDto createUser(UserCommand userCommand) {
        return ApiUserFactory.fromUserToDto(userService.createUser(ApiUserFactory.fromUserCommandToUser(userCommand)));
    }

    @Override
    public UserDto loginUser(UserCommand userCommand) {
        return ApiUserFactory.fromUserToDto(userService.loginUser(ApiUserFactory.fromUserCommandToUser(userCommand)));
    }

    @Override
    public UserDto updateUser(Long id, UserCommand updatedUserCommand) {
        return ApiUserFactory.fromUserToDto(userService.updateUser(id, ApiUserFactory.fromUserCommandToUser(updatedUserCommand)));
    }
}
