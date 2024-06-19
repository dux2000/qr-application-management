package com.example.demo.domain.service.impl;

import com.example.demo.domain.filter.SearchRequest;
import com.example.demo.domain.filter.SearchResponse;
import com.example.demo.domain.model.User;
import com.example.demo.domain.repository.UserRepository;
import com.example.demo.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public SearchResponse<User> getUsers(SearchRequest request) {
        return userRepository.getUsers(request);
    }

    @Override
    public User createUser(User user) {
        validateCreateUser(user);
        User userInDatabase = userRepository.getUserByUsername(user.getUsername());
        if (userInDatabase != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with that username already exists.");
        }
        user.setPassword(encryptPassword(user.getPassword()));

        return userRepository.createUser(user);
    }

    @Override
    public User loginUser(User user) {
        validateLogin(user);
        User userInDatabase = userRepository.getUserByUsername(user.getUsername());

        if (userInDatabase == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong username or password.");
        if (!checkPassword(user.getPassword(), userInDatabase.getPassword()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong username or password.");

        return userInDatabase;
    }

    @Override
    public User updateUser(Long id, User updatedUser) {
        updatedUser.setId(id);
        updatedUser.setPassword(updatedUser.getPassword() == null || updatedUser.getPassword().isEmpty() ? null : encryptPassword(updatedUser.getPassword()));
        return userRepository.updateUser(updatedUser);
    }

    @Override
    public User changePassword(Long id, String oldPassword, String newPassword) {
        if (oldPassword.equals(newPassword)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "New password must be different from the old password.");
        }

        User user = userRepository.getUserById(id);
        if (!checkPassword(oldPassword, user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Old password is incorrect.");
        }

        user.setPassword(encryptPassword(newPassword));
        user.setUpdate(false);

        return userRepository.updateUser(user);
    }

    private void validateCreateUser(User user) {
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User must not be null.");
        } else if(user.getUsername() == null || user.getUsername().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User username attribute must not be empty.");
        } else if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User password attribute must not be empty.");
        } else if (user.getRole() == null || user.getRole().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User role attribute must not be empty.");
        } else if(user.getFullName() == null || user.getFullName().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User fullName attribute must not be empty.");
        }
    }

    private void validateLogin(User user) {
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User must not be null.");
        } else if(user.getUsername() == null || user.getUsername().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User username attribute must not be empty.");
        } else if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User password attribute must not be empty.");
        }
    }

    private String encryptPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    private boolean checkPassword(String rawPassword, String encryptedPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(rawPassword, encryptedPassword);
    }
}
