package com.example.demo.persistence.repository.factory;

import com.example.demo.domain.model.User;
import com.example.demo.persistence.entity.UserEntity;

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
        userEntity.setRole(user.getRole());

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

        return user;
    }
}
