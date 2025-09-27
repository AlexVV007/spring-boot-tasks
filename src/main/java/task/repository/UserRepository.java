package task.repository;

import task.model.User;

import java.util.List;
import java.util.Optional;


public interface UserRepository {

    public List<User> findAll();
    public User save(User user);
    public Optional<User> findByUserName(String userName);
    public Optional<User> findById(Long userId);
}
