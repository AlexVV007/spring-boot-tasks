package task.repository;

import task.model.Task;

import java.util.List;
import java.util.Optional;


public interface TaskRepository {

    public List<Task> findAll();
    public List<Task> findByUserId(Long userId) ;
    public List<Task> findPendingByUserId(Long userId) ;
    public Optional<Task> findById(Long id) ;
    public Task save(Task task) ;
    public void markAsDeleted(Long taskId);

}
