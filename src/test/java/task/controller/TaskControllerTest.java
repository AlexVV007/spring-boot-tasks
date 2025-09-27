package task.controller;

import lombok.Builder;
import lombok.NonNull;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import task.model.Task;
import task.service.TaskService;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@ExtendWith(MockitoExtension.class)
class TaskControllerTest {


    @Mock
    private TaskService taskService;

    @InjectMocks
    private TaskController taskController;

    private Task task1, task2;

    private static final long user1ID = 1L;
    private static final LocalDateTime DateNow = LocalDateTime.now();

    @BeforeEach
    void setUp() {

        task1 = Task.builder()
                .taskId(1L)
                .userId(user1ID)
                .taskText("Task 1 Text")
                .createDate(DateNow.minusDays(1))
                .actionDate(DateNow.plusDays(5))
                .isComplete(false)
                .isDelete(false)
                .build();


        task2 = Task.builder()
                .taskId(2L)
                .userId(user1ID)
                .taskText("Task 2 Text")
                .createDate(DateNow.minusDays(1))
                .actionDate(DateNow.plusDays(5))
                .isComplete(false)
                .isDelete(false)
                .build();
    }


    @Test
    void getAllTasks_ReturnAllTasks() {
        // Arrange
        List<Task> tasksExist = Arrays.asList(task1, task2);

        when(taskService.getAllTasks()).thenReturn(tasksExist);

        ResponseEntity<List<Task>> response = taskController.getAllTasks();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(tasksExist, response.getBody());
        verify(taskService).getAllTasks();

    }

    @Test
    void getUserTasks_ReturnUserTasks() {

        List<Task> tasksExist = Arrays.asList(task1);

        when(taskService.getUserTasks(user1ID)).thenReturn(tasksExist);

        ResponseEntity<List<Task>> response = taskController.getUserTasks(user1ID);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(tasksExist, response.getBody());
        verify(taskService).getUserTasks(user1ID);
    }

    @Test
    void createTask_ReturnCreatedTask() {

        Task taskSave = Task.builder()
                .taskId(1L)
                .userId(user1ID)
                .taskText("Task 1 Text")
                .createDate(DateNow.minusDays(1))
                .actionDate(DateNow.plusDays(5))
                .isComplete(false)
                .isDelete(false)
                .build();

        when(taskService.createTask(task1)).thenReturn(taskSave);

        ResponseEntity<Task> response = taskController.createTask(task1);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(taskSave, response.getBody());
        verify(taskService).createTask(task1);
    }

    @Test
    void deleteTask_ReturnNoContent() {

        Long taskId = 1L;
        doNothing().when(taskService).deleteTask(taskId);

        ResponseEntity<Void> response = taskController.deleteTask(taskId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(taskService).deleteTask(taskId);
    }
}