package task.controller;

import lombok.Builder;
import lombok.NonNull;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import task.model.Notification;
import task.model.Task;
import task.service.NotificationService;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NotificationControllerTest {

    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private NotificationController notificationController;
    private Notification notification1;
    private Notification notification2;
    private Notification notification1UnRead;

    private static final long user1ID = 1L;
    private static final long notification1ID = 10L;
    private static final long notification2ID = 20L;
    private static final long task1IDForNotification = 100L;
    private static final long task2IDForNotification = 101L;
    private static final LocalDateTime DATE_NOW = LocalDateTime.now();

    @BeforeEach
    void setUp() {
        notification1 = Notification.builder()
                .notificationId(notification1ID)
                .userId(user1ID)
                .taskId(task1IDForNotification)
                .text("Notification 1 text")
                .dateCreate(DATE_NOW.minusHours(1))
                .isRead(false)
                .build();


        notification2 = Notification.builder()
                .notificationId(notification2ID)
                .userId(user1ID)
                .taskId(task2IDForNotification)
                .text("Notification 2 text")
                .dateCreate(DATE_NOW.minusHours(1))
                .isRead(false)
                .build();

        notification1UnRead = Notification.builder()
                .notificationId(notification1ID)
                .userId(user1ID)
                .taskId(task1IDForNotification)
                .text("Notification 1 text")
                .dateCreate(DATE_NOW.minusHours(1))
                .isRead(false)
                .build();
    }

    @Test
    void getUserNotifications_ReturnNotifications() {

        List<Notification> notificationsExist = Arrays.asList(notification1, notification2);

        when(notificationService.getUserNotifications(user1ID)).thenReturn(notificationsExist);

        ResponseEntity<List<Notification>> response = notificationController.getUserNotifications(user1ID);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(notificationsExist, response.getBody());
        verify(notificationService).getUserNotifications(user1ID);
    }

    @Test
    void getUnreadUserNotifications_ReturnUnreadNotifications() {

        List<Notification> NotificationsExist = Arrays.asList(notification1UnRead);

        when(notificationService.getUnreadUserNotifications(user1ID)).thenReturn(NotificationsExist);

        ResponseEntity<List<Notification>> response = notificationController.getUnreadUserNotifications(user1ID);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(NotificationsExist, response.getBody());
        verify(notificationService).getUnreadUserNotifications(user1ID);
    }

    @Test
    void getAllNotifications_ReturnAllNotifications() {

        List<Notification> notificationsExist = Arrays.asList(notification1, notification2);

        when(notificationService.getAllNotifications()).thenReturn(notificationsExist);

        ResponseEntity<List<Notification>> response = notificationController.getAllNotifications();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(notificationsExist, response.getBody());
        verify(notificationService).getAllNotifications();
    }

}