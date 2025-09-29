package task.service;

import org.springframework.stereotype.Service;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import task.model.Task;
import task.repository.TaskRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final KafkaTemplate<String, Task> kafkaTemplate;


    public TaskService(TaskRepository taskRepository, KafkaTemplate<String, Task> kafkaTemplate) {
        this.taskRepository = taskRepository;
        this.kafkaTemplate = kafkaTemplate;
    }


    public List<Task> getAllTasks() {
        return taskRepository.findByIsDeleteFalse();
    }

    @Cacheable(cacheNames = "user_task", key = "#userId")
    public List<Task> getUserTasks(Long userId) {
        System.out.printf("Cache missed: %s\n", userId);
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

    @Scheduled(fixedRate = 1, timeUnit = TimeUnit.SECONDS)
    public void checkForOverdueTasks() {
        LocalDateTime now = LocalDateTime.now();
        List<Task> activeTask = taskRepository.findByIsDeleteFalse();
        System.out.printf("Total active task: %d\n",activeTask.size());
    }


    @Async
    public CompletableFuture<List<Task>> getCompletedUserTasks(Long userId) {
        List<Task> retrieval = taskRepository.findByUserIdAndIsCompleteFalseAndIsDeleteFalse(userId);
        return CompletableFuture.completedFuture(retrieval);
    }

}

}
