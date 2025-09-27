package task.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import task.model.Notification;
import task.model.Task;
import task.service.NotificationService;

import java.util.List;

@RestController
@RequestMapping("/api/Notifications")
@AllArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;
    @GetMapping
    public ResponseEntity<List<Notification>> getAllNotifications() {
        return ResponseEntity.ok(notificationService.getAllNotifications());
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Notification>> getUserNotifications(@PathVariable Long userId) {
        return ResponseEntity.ok(notificationService.getUserNotifications(userId));
    }

    @GetMapping("/user/{userId}/unread")
    public ResponseEntity<List<Notification>> getUnreadUserNotifications(@PathVariable Long userId) {
        return ResponseEntity.ok(notificationService.getUnreadUserNotifications(userId));
    }

}
