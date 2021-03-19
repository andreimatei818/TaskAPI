package com.task.ING.service;

import com.task.ING.exceptions.user.UserAlreadyExistsException;
import com.task.ING.exceptions.user.UserDoesNotExistsException;
import com.task.ING.model.User;
import com.task.ING.model.UserDTO;
import com.task.ING.repository.UserRepository;
import com.task.ING.validator.UserValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDTO createUser(User user) {
        logger.info("Creating new user");
        UserValidator.validateUser(user);
        checkIfUserAlreadyExists(user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User createdUser = userRepository.save(user);
        logger.info("The user with username: " + createdUser.getUsername()+ " was created");
        return new UserDTO(user);
    }

    public List<UserDTO> getAllUsers() {
        logger.info("Getting all users");
        return userRepository.findAll().stream().map(UserDTO::new).collect(Collectors.toList());
    }

    private void checkIfUserAlreadyExists(User user) {
        Optional<User> currentUser = userRepository.findUserByUsername(user.getUsername());
        if (currentUser.isPresent()) {
            logger.info("The username: " + user.getUsername()+ " already exists");
            throw new UserAlreadyExistsException(user.getUsername());
        }
    }


    public void checkExistUser(String userId) {
        userRepository.findById(Integer.parseInt(userId)).orElseThrow(() -> new UserDoesNotExistsException(userId));
    }
}
