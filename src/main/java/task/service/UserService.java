package task.service;

import org.springframework.stereotype.Service;
import task.model.User;
import task.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User register(User user) {
        return userRepository.save(user);
    }

    public Optional<User> login(String username, String password) {
        return userRepository.findByUserName(username)
                .filter(user -> user.getPassword().equals(password));
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
}