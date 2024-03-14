package com.example.demo.api.factory;

import com.example.demo.api.command.UserCommand;
import com.example.demo.api.dto.UserDto;
import com.example.demo.domain.model.User;

public class ApiUserFactory {
    public static UserDto fromUserToDto(User user) {
        if (user == null)
            return null;

        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setFullName(user.getFullName());
        userDto.setCreated(user.getCreated());

        return userDto;
    }

    public static User fromUserCommandToUser(UserCommand userCommand) {
        if (userCommand == null)
            return null;

        User user = new User();
        user.setUsername(userCommand.getUsername());
        user.setFullName(userCommand.getFullName());
        user.setPassword(userCommand.getPassword());
        user.setRole(userCommand.getRole());

        return user;
    }
}
