package task.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import task.model.Task;
import task.service.TaskService;

import java.util.List;

@RestController
@RequestMapping(path = "/api/Tasks")
@AllArgsConstructor
public class TaskController {
	private final TaskService taskService;

	@GetMapping
	public ResponseEntity<List<Task>> getAllTasks() {
		return ResponseEntity.ok(taskService.getAllTasks());
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Task>> getUserTasks(@PathVariable Long userId) {
		return ResponseEntity.ok(taskService.getUserTasks(userId));
	}

	@PostMapping("/insert")
	public ResponseEntity<Task> createTask(@RequestBody Task task) {
		return new ResponseEntity<>(taskService.createTask(task), HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
		taskService.deleteTask(id);
		return ResponseEntity.noContent().build();
	}
}