package com.example.demo.persistence.repository.impl;

import com.example.demo.domain.filter.SearchRequest;
import com.example.demo.domain.filter.SearchResponse;
import com.example.demo.domain.model.User;
import com.example.demo.domain.repository.UserRepository;
import com.example.demo.persistence.entity.UserEntity;
import com.example.demo.persistence.repository.UserEntityRepository;
import com.example.demo.persistence.repository.factory.UserFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserEntityRepository userEntityRepository;

    @Override
    public SearchResponse<User> getUsers(SearchRequest request) {
        return request.fetchAndConvert(userEntityRepository, UserFactory::fromUserEntityToUser);
    }

    @Override
    public User createUser(User user) {
        return UserFactory.fromUserEntityToUser(userEntityRepository.save(UserFactory.fromUserToUserEntity(user)));
    }

    @Override
    public User getUserByUsername(String username) {
        Optional<UserEntity> user = userEntityRepository.findByUsername(username);
        return user.map(UserFactory::fromUserEntityToUser).orElse(null);
    }

    @Override
    public User getUserById(Long id) {
        return UserFactory.fromUserEntityToUser(testIfUserExists(id));
    }

    @Override
    public User updateUser(User user) {
        UserEntity oldUserEntity = testIfUserExists(user.getId());
        oldUserEntity.setUsername(user.getUsername() == null ? oldUserEntity.getUsername() : user.getUsername());
        oldUserEntity.setFullName(user.getFullName() == null ? oldUserEntity.getFullName() : user.getFullName());
        oldUserEntity.setPassword(user.getPassword() == null ? oldUserEntity.getPassword() : user.getPassword());
        oldUserEntity.setRole(user.getRole() == null ? oldUserEntity.getRole() : user.getRole());
        oldUserEntity.setUpdated(LocalDateTime.now());
        oldUserEntity.setUpdate(user.getUpdate() == null ? oldUserEntity.getUpdate() : user.getUpdate());

        return UserFactory.fromUserEntityToUser(userEntityRepository.save(oldUserEntity));

    }

    public UserEntity testIfUserExists(Long id) {
        Optional<UserEntity> userEntity = userEntityRepository.findById(id);

        if(userEntity.isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is no user with that id!");
        return userEntity.get();
    }
}
