package task.service;

import org.springframework.stereotype.Service;
import task.model.Task;
import task.repository.TaskRepository;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findByIsDeleteFalse();
    }

    public List<Task> getUserTasks(Long userId) {
        return taskRepository.findByUserIdAndIsDeleteFalse(userId);
    }

    public List<Task> getPendingUserTasks(Long userId) {
        return taskRepository.findByUserIdAndIsCompleteFalseAndIsDeleteFalse(userId);
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        taskRepository.markAsDeleted(id);
    }
}
