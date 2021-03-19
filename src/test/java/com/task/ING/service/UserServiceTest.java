package com.task.ING.service;

import com.task.ING.exceptions.user.PasswordFormatException;
import com.task.ING.exceptions.user.UserDoesNotExistsException;
import com.task.ING.exceptions.user.UserParameterCannotBeNullException;
import com.task.ING.model.User;
import com.task.ING.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;


    @Test(expected = PasswordFormatException.class)
    public void createUserWrongFormatPassword() {
        //given
        User user = User.builder().username("user1").password("2").build();
        //when
        userService.createUser(user);

    }

    @Test(expected = UserParameterCannotBeNullException.class)
    public void createUserWithoutUsername() {
        //given
        User user = User.builder().password("Abcd1234@").build();
        //when
        userService.createUser(user);

    }

    @Test(expected = UserDoesNotExistsException.class)
    public void checkExistsUserNegativeCase() {
        //given
        when(userRepository.findById(1)).thenReturn(Optional.empty());
        //when
        userService.checkExistUser("1");
    }
}