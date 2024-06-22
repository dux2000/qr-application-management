package com.example.demo.persistence.repository.factory;

import com.example.demo.domain.model.User;
import com.example.demo.domain.model.UserType;
import com.example.demo.persistence.entity.UserEntity;
import com.example.demo.persistence.entity.UserTypeDefinitionEntity;
import com.example.demo.persistence.entity.UserTypeEntity;

import java.time.LocalDateTime;

public class UserFactory {
    public static UserEntity fromUserToUserEntity(User user) {
        if (user == null)
            return null;

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(user.getUsername());
        userEntity.setFullName(user.getFullName());
        userEntity.setPassword(user.getPassword());
        userEntity.setCreated(LocalDateTime.now());
        userEntity.setUpdate(user.getUpdate());

        return userEntity;
    }

    public static User fromUserEntityToUser(UserEntity userEntity) {
        if (userEntity == null)
            return null;

        User user = new User();
        user.setId(userEntity.getId());
        user.setUsername(userEntity.getUsername());
        user.setFullName(userEntity.getFullName());
        user.setPassword(userEntity.getPassword());
        user.setCreated(userEntity.getCreated());
        user.setTypes(userEntity.getTypes().stream().map(x -> toUserType(x.getDefinition())).toList());
        user.setUpdate(userEntity.getUpdate());

        return user;
    }

    public static UserType toUserType(UserTypeDefinitionEntity userTypeDefinitionEntity) {
        if (userTypeDefinitionEntity == null) return null;

        UserType userType = new UserType();
        userType.setCode(userTypeDefinitionEntity.getCode());
        userType.setName(userTypeDefinitionEntity.getName());

        return userType;
    }
}
