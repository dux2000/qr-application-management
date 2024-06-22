package com.example.demo.persistence.repository.impl;

import com.example.demo.domain.filter.SearchRequest;
import com.example.demo.domain.filter.SearchResponse;
import com.example.demo.domain.model.User;
import com.example.demo.domain.model.UserType;
import com.example.demo.domain.repository.UserRepository;
import com.example.demo.persistence.entity.UserEntity;
import com.example.demo.persistence.entity.UserTypeDefinitionEntity;
import com.example.demo.persistence.entity.UserTypeEntity;
import com.example.demo.persistence.repository.UserEntityRepository;
import com.example.demo.persistence.repository.UserTypeDefinitionRepository;
import com.example.demo.persistence.repository.factory.UserFactory;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserEntityRepository userEntityRepository;
    private final UserTypeDefinitionRepository userTypeDefinitionRepository;
    @Override
    public SearchResponse<User> getUsers(SearchRequest request) {
        return request.fetchAndConvert(userEntityRepository, UserFactory::fromUserEntityToUser);
    }

    @Override
    public User createUser(User user) {
        UserEntity userEntity = UserFactory.fromUserToUserEntity(user);
        saveUserTypes(user, userEntity);

        return UserFactory.fromUserEntityToUser(userEntityRepository.save(userEntity));
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
        oldUserEntity.setUpdated(LocalDateTime.now());
        oldUserEntity.setUpdate(user.getUpdate() == null ? oldUserEntity.getUpdate() : user.getUpdate());

        // Clear the old types and add the new ones
        oldUserEntity.getTypes().clear();
        saveUserTypes(user, oldUserEntity);

        return UserFactory.fromUserEntityToUser(userEntityRepository.save(oldUserEntity));

    }

    @Override
    public List<UserType> getUserTypes() {
        return userTypeDefinitionRepository.findAll().stream().map(UserFactory::toUserType).toList();
    }

    public UserEntity testIfUserExists(Long id) {
        Optional<UserEntity> userEntity = userEntityRepository.findById(id);

        if(userEntity.isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is no user with that id!");
        return userEntity.get();
    }

    private void saveUserTypes(User user, UserEntity userEntity) {
        user.getTypes().forEach(userType -> {
            UserTypeEntity userTypeEntity = new UserTypeEntity();

            UserTypeDefinitionEntity userTypeDefinitionEntity = userTypeDefinitionRepository.findById(userType.getCode())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is no user type with that code!"));

            userTypeEntity.setUser(userEntity);
            userTypeEntity.setDefinition(userTypeDefinitionEntity);

            userEntity.getTypes().add(userTypeEntity);
        });
    }
}
