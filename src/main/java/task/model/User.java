package task.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long userId;
    private String login;
    private String password;
    private String userName;
    private String email;

    @Override
    public String toString() {
        return "User{" +
                "id=" + userId +
                ", username='" + userName + '\'' +
                '}';
    }

}
