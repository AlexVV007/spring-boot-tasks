package task.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import task.model.Task;
import task.model.User;
import task.service.UserService;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private User user1;
    private User user2;
    private static final long user1ID = 1L;
    private static final long user2ID = 2L;


    @BeforeEach
    void setUp() {

        user1 = User.builder()
                .userId(user1ID)
                .userName("user1Name")
                .build();

        user2 = User.builder()
                .userId(user2ID)
                .userName("user2Name")
                .build();
    }

    @Test
    void register_ReturnCreatedUser() {

        User savedUser = User.builder()
                .userId(user1ID)
                .userName("user1Name")
                .build();

        when(userService.register(user1)).thenReturn(savedUser);

        ResponseEntity<User> response = userController.register(user1);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(savedUser, response.getBody());
        verify(userService).register(user1);
    }

    @Test
    void getUserById_WhenUserExists_ReturnUser() {

        when(userService.getUserById(user1ID)).thenReturn(Optional.of(user1));

        ResponseEntity<User> response = userController.getUserById(user1ID);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(user1, response.getBody());
        verify(userService).getUserById(user1ID);
    }

    @Test
    void getUserById_WhenUserNotExists_ReturnNotFound() {

        Long userId = 9999L;
        when(userService.getUserById(userId)).thenReturn(Optional.empty());

        ResponseEntity<User> response = userController.getUserById(userId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(userService).getUserById(userId);
    }

    }

}