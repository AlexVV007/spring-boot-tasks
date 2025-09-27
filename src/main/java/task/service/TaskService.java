package task.service;

import task.model.Task;

import java.util.List;

public interface TaskService {

    public List<Task> getAllTasks() ;
    public List<Task> getUserTasks(Long userId);
    public List<Task> getPendingUserTasks(Long userId);
    public Task createTask(Task task);
    public void deleteTask(Long id);
}
