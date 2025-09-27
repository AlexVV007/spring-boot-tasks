package task.repository.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import task.model.Task;
import task.repository.TaskRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class TaskRepositoryImpl implements TaskRepository {

    private final ConcurrentHashMap<Long, Task> tasks = new ConcurrentHashMap<>();
    private final AtomicLong idCounter = new AtomicLong(1);
    @Override
    public List<Task> findAll() {
        return tasks.values().stream()
                .filter(task -> !task.getIsDelete())
                .collect(Collectors.toList());
    }
    @Override
    public List<Task> findByUserId(Long userId) {
        return tasks.values().stream()
                .filter(task -> task.getUserId().equals(userId) && !task.getIsDelete())
                .collect(Collectors.toList());
    }
    @Override
    public List<Task> findPendingByUserId(Long userId) {
        return tasks.values().stream()
                .filter(task -> task.getUserId().equals(userId)
                        && !task.getIsComplete()
                        && !task.getIsDelete())
                .collect(Collectors.toList());
    }
    @Override
    public Optional<Task> findById(Long id) {
        Task task = tasks.get(id);
        return (task != null && !task.getIsDelete()) ? Optional.of(task) : Optional.empty();
    }
    @Override
    public Task save(Task task) {
        if (task.getTaskId() == null) {
            task.setTaskId(idCounter.getAndIncrement());
            task.setCreateDate(LocalDateTime.now());
        }
        tasks.put(task.getTaskId(), task);
        return task;
    }
    @Override
    public void markAsDeleted(Long taskId) {
        Optional.ofNullable(tasks.get(taskId)).ifPresent(task -> task.setIsDelete(true));
    }
}
