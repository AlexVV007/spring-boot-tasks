package task.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "notifications")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false, length = 500)
    private String text;

    @Column(nullable = false)
    private boolean isRead = false;

    @Column(nullable = false)
    private LocalDateTime createDate = LocalDateTime.now();


    @Override
    public String toString() {
        return "Notification{" +
                "id=" + notificationId +
                ", message='" + text + '\'' +
                ", read=" + isRead +
                '}';
    }
}