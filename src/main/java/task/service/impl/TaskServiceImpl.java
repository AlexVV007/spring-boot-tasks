package task.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import task.model.Notification;
import task.model.Task;
import task.repository.TaskRepository;
import task.service.TaskService;



@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {

	private final TaskRepository taskRepository;

	@Override
	public List<Task> getAllTasks() {
		return taskRepository.findAll();
	}
	@Override
	public List<Task> getUserTasks(Long userId) {
		return taskRepository.findByUserId(userId);
	}
	@Override
	public List<Task> getPendingUserTasks(Long userId) {
		return taskRepository.findPendingByUserId(userId);
	}
	@Override
	public Task createTask(Task task) {
		return taskRepository.save(task);
	}
	@Override
	public void deleteTask(Long id) {
		taskRepository.markAsDeleted(id);
	}

}