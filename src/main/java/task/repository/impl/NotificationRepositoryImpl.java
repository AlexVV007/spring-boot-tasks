package task.repository.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import task.model.Notification;
import task.model.User;
import task.repository.NotificationRepository;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class NotificationRepositoryImpl implements NotificationRepository {

    private final ConcurrentHashMap<Long, Notification> notifications = new ConcurrentHashMap<>();
    private final AtomicLong idCounter = new AtomicLong(1);
    @Override
    public List<Notification> findAll() {
        return notifications.values().stream()
                .collect(Collectors.toList());
    }

    @Override
    public List<Notification> findByUserId(Long userId) {
        return notifications.values().stream()
                .filter(notification -> notification.getUserId().equals(userId))
                .collect(Collectors.toList());
    }
    @Override
    public List<Notification> findUnreadByUserId(Long userId) {
        return notifications.values().stream()
                .filter(notification -> notification.getUserId().equals(userId)
                        && !notification.getIsRead())
                .collect(Collectors.toList());
    }
    @Override
    public Notification save(Notification notification) {
        if (notification.getNotificationId() == null) {
            notification.setNotificationId(idCounter.getAndIncrement());
        }
        notifications.put(notification.getNotificationId(), notification);
        return notification;
    }
}
