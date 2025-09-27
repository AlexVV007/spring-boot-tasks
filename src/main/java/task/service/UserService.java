package task.service;

import task.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {


    public List<User> getAllUsers();
    public User register(User user);
    public Optional<User> login(String userName, String password) ;
    public Optional<User> getUserById(Long userId);


}
