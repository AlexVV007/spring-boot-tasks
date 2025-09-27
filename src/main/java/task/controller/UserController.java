package task.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import task.model.User;
import task.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/Users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping("/insert")
    public ResponseEntity<User> register(@RequestBody User user) {
        return new ResponseEntity<>(userService.register(user), HttpStatus.CREATED);
    }


    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}