package com.task.ING.validator;


import com.task.ING.exceptions.user.InvalidAuthorityException;
import com.task.ING.exceptions.user.PasswordFormatException;
import com.task.ING.exceptions.user.UserParameterCannotBeNullException;
import com.task.ING.model.User;
import com.task.ING.model.UserType;
import com.task.ING.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserValidator {
    private final Logger logger = LoggerFactory.getLogger(UserService.class);
    private static final String INVALID_PARAMETER = "invalid parameter {} ";
    private static final String AUTHORITY = "authority";
    private static final String NULL_PARAMETER = "parameter {} cannot be null";
    private static final String PASSWORD = "password";
    private static final String PASSWORD_INVALID_MESSAGE = "Password should contain at least a digit, " +
            "a lower case latter, an upper case latter," +
            " a special character and the length should be " +
            "8 characters , and also the password should not contains whitespace ";
    public static final String USERNAME = "username";


    public static void validateUser(User user) {
        UserValidator userValidator = new UserValidator();
        userValidator.validateUsername(user);
        userValidator.validatePassword(user);
        userValidator.validateAuthority(user);
    }

    private void validateAuthority(User user) {
        if (StringUtils.isEmpty(user.getAuthority())) {
            logger.info(NULL_PARAMETER, AUTHORITY);
            throw new UserParameterCannotBeNullException(AUTHORITY);
        }

        List<String> enumValues = Stream.of(UserType.values())
                .map(Enum::name)
                .collect(Collectors.toList());

        if (!enumValues.contains(user.getAuthority())) {
            logger.info(INVALID_PARAMETER, AUTHORITY);
            throw new InvalidAuthorityException("Invalid authority field. Authority values :" + Arrays.toString(UserType.values()));
        }
    }

    private void validatePassword(User user) {
        String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
        if (StringUtils.isEmpty(user.getPassword())) {
            logger.info(NULL_PARAMETER, AUTHORITY);
            throw new UserParameterCannotBeNullException(PASSWORD);
        }

        if (!user.getPassword().matches(pattern)) {
            logger.info(INVALID_PARAMETER + PASSWORD_INVALID_MESSAGE, PASSWORD);
            throw new PasswordFormatException(PASSWORD_INVALID_MESSAGE);
        }
    }

    private void validateUsername(User user) {
        if (StringUtils.isEmpty(user.getUsername())) {
            logger.info(NULL_PARAMETER, USERNAME);
            throw new UserParameterCannotBeNullException(USERNAME);
        }
    }
}