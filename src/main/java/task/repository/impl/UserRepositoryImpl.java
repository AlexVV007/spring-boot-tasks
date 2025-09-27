package task.repository.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import task.model.Task;
import task.model.User;
import task.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class UserRepositoryImpl implements UserRepository{


    private final ConcurrentHashMap<Long, User> users = new ConcurrentHashMap<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    @Override
    public List<User> findAll() {
        return users.values().stream()
                .collect(Collectors.toList());
    }
    @Override
    public User save(User user) {
        if (user.getUserId() == null) {
            user.setUserId(idCounter.getAndIncrement());
        }
        users.put(user.getUserId(), user);
        return user;
    }
    @Override
    public Optional<User> findByUserName(String userName) {
        return users.values().stream()
                .filter(user -> user.getUserName().equals(userName))
                .findFirst();
    }
    @Override
    public Optional<User> findById(Long userId) {
        return Optional.ofNullable(users.get(userId));
    }}
