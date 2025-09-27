package task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import task.model.User;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String username);
}