package task.repository;

import task.model.Notification;

import java.util.List;

public interface NotificationRepository {
    public List<Notification> findAll();
    public List<Notification> findByUserId(Long userId);
    public List<Notification> findUnreadByUserId(Long userId);
    public Notification save(Notification notification);

}
