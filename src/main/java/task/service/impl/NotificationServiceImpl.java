package task.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import task.model.Notification;
import task.model.Task;
import task.repository.NotificationRepository;
import task.service.NotificationService;

import java.util.List;

@Service
@AllArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository notificationRepository;

    @Override
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }
    @Override
    public List<Notification> getUserNotifications(Long userId) {

        return notificationRepository.findByUserId(userId);
    }
    @Override
    public List<Notification> getUnreadUserNotifications(Long userId) {
        return notificationRepository.findUnreadByUserId(userId);
    }
    @Override
    public Notification createNotification(Notification notification) {
        return notificationRepository.save(notification);
    }
}