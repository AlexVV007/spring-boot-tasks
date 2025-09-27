package task.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import task.model.Task;
import task.model.User;
import task.repository.UserRepository;
import task.service.UserService;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    @Override
    public User register(User user) {
        return userRepository.save(user);
    }
    @Override
    public Optional<User> login(String userName, String password) {
        return userRepository.findByUserName(userName)
                .filter(user -> user.getPassword().equals(password));
    }
    @Override
    public Optional<User> getUserById(Long UserId) {

        return userRepository.findById(UserId);
    }

}