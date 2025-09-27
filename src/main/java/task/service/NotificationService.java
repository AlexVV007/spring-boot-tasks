package task.service;

import task.model.Notification;

import java.util.List;

public interface NotificationService {
    public List<Notification> getAllNotifications();
    public List<Notification> getUserNotifications(Long userId);
    public List<Notification> getUnreadUserNotifications(Long userId);
    public Notification createNotification(Notification notification) ;
}
