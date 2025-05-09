package com.users;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserDAO userDAO;

    @Autowired
    public UserController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable String userId) {
        try {
            User user = this.userDAO.getUserById(userId);
            return user != null ? ResponseEntity.ok(user)
                    : ResponseEntity.notFound().build();
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            List<User> users = this.userDAO.getAllUsers();
            return ResponseEntity.ok(users);
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<String> updateUser(@PathVariable int userId,
            @RequestBody User user) {
        if (this.userDAO.updateUser(userId, user)) {
            return ResponseEntity.ok("User updated successfully!");
        } else {
            return ResponseEntity.internalServerError().body("Error updating user.");
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable String userId) {
        if (this.userDAO.deleteUser(userId)) {
            return ResponseEntity.ok("User deleted successfully!");
        } else {
            return ResponseEntity.internalServerError().body("Error deleting user.");
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAllUsers() {
        if (this.userDAO.deleteAllUsers()) {
            return ResponseEntity.ok("All users deleted successfully!");
        } else {
            return ResponseEntity.internalServerError().body("Error deleting users.");
        }
    }

    @PostMapping
    public ResponseEntity<String> addUser(@RequestBody User user) {
        try {
            this.userDAO.addUser(user);
            return ResponseEntity.ok("User added successfully!");
        } catch (SQLException e) {
            return ResponseEntity.internalServerError()
                    .body("Error adding user: " + e.getMessage());
        }
    }

}
