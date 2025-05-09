package com.settings;

import java.sql.SQLException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user-settings")
public class UserSettingsController {
    private final User_SettingsDAO userSettingsDAO;

    public UserSettingsController(User_SettingsDAO userSettingsDAO) {
        this.userSettingsDAO = userSettingsDAO;
    }

    @PostMapping
    public ResponseEntity<String> addSettings(@RequestBody User_Settings userSettings) {
        try {
            this.userSettingsDAO.addSettings(userSettings);
            return ResponseEntity.ok("User settings added successfully!");
        } catch (SQLException e) {
            return ResponseEntity.internalServerError()
                    .body("Error adding user settings: " + e.getMessage());
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User_Settings> getSettingsByID(@PathVariable String userId) {
        try {
            User_Settings settings = this.userSettingsDAO.getSettingsByID(userId);
            return settings != null ? ResponseEntity.ok(settings)
                    : ResponseEntity.notFound().build();
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<User_Settings>> getAllSettings() {
        try {
            List<User_Settings> settings = this.userSettingsDAO.getAllSettings();
            return ResponseEntity.ok(settings);
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUserSettings(@PathVariable String userId) {
        try {
            return this.userSettingsDAO.deleteUserSettings(userId)
                    ? ResponseEntity.ok("User settings deleted successfully!")
                    : ResponseEntity.internalServerError()
                            .body("Error deleting user settings.");
        } catch (SQLException e) {
            return ResponseEntity.internalServerError()
                    .body("SQL error: " + e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAllSettings() throws SQLException {
        return this.userSettingsDAO.deleteAllSettings()
                ? ResponseEntity.ok("All user settings deleted successfully!")
                : ResponseEntity.internalServerError()
                        .body("Error deleting all user settings.");
    }
}
