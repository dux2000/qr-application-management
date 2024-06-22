package com.example.demo.api.factory;

import com.example.demo.api.command.UserCommand;
import com.example.demo.api.command.UserTypeCommand;
import com.example.demo.api.dto.UserDto;
import com.example.demo.api.dto.UserTypeDto;
import com.example.demo.domain.model.User;
import com.example.demo.domain.model.UserType;
import com.example.demo.persistence.entity.UserTypeEntity;

public class ApiUserFactory {
    public static UserDto fromUserToDto(User user) {
        if (user == null)
            return null;

        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setFullName(user.getFullName());
        userDto.setCreated(user.getCreated());
        userDto.setTypes(user.getTypes().stream().map(ApiUserFactory::toUserTypeDto).toList());
        userDto.setUpdate(user.getUpdate());

        return userDto;
    }

    public static User fromUserCommandToUser(UserCommand userCommand) {
        if (userCommand == null)
            return null;

        User user = new User();
        user.setUsername(userCommand.getUsername());
        user.setFullName(userCommand.getFullName());
        user.setPassword(userCommand.getPassword());
        user.setUpdate(userCommand.getUpdate());

        if (userCommand.getTypes() != null)
            user.setTypes(userCommand.getTypes().stream().map(ApiUserFactory::toUserType).toList());

        return user;
    }

    public static UserTypeDto toUserTypeDto(UserType userType) {
        if (userType == null) return null;

        UserTypeDto userTypeDto = new UserTypeDto();
        userTypeDto.setCode(userType.getCode());
        userTypeDto.setName(userType.getName());

        return userTypeDto;
    }

    private static UserType toUserType(UserTypeCommand userTypeCommand) {
        if (userTypeCommand == null) return null;

        UserType userType = new UserType();
        userType.setCode(userTypeCommand.getCode());
        userType.setName(userTypeCommand.getName());

        return userType;
    }
}
