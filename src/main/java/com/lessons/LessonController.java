package com.lessons;

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
@RequestMapping("/lessons")
public class LessonController {
    private final LessonDAO lessonDAO;

    public LessonController(LessonDAO lessonDAO) {
        this.lessonDAO = lessonDAO;
    }

    @PostMapping
    public ResponseEntity<String> addLesson(@RequestBody Lesson lesson) {
        try {
            this.lessonDAO.addLesson(lesson);
            return ResponseEntity.ok("Lesson added successfully!");
        } catch (SQLException e) {
            return ResponseEntity.internalServerError()
                    .body("Error adding lesson: " + e.getMessage());
        }
    }

    @GetMapping("/{lessonId}")
    public ResponseEntity<Lesson> getLessonById(@PathVariable String lessonId) {
        try {
            Lesson lesson = this.lessonDAO.getLessonByID(lessonId);
            return lesson != null ? ResponseEntity.ok(lesson)
                    : ResponseEntity.notFound().build();
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Lesson>> getLessonsByUserId(@PathVariable String userId) {
        try {
            List<Lesson> lessons = this.lessonDAO.getLessonsByUserID(userId);
            return ResponseEntity.ok(lessons);
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<Lesson>> getAllLessons() {
        try {
            List<Lesson> lessons = this.lessonDAO.getAllLessons();
            return ResponseEntity.ok(lessons);
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @DeleteMapping("/{lessonId}")
    public ResponseEntity<String> deleteLesson(@PathVariable String lessonId) {
        return this.lessonDAO.deleteLesson(lessonId)
                ? ResponseEntity.ok("Lesson deleted successfully!")
                : ResponseEntity.internalServerError().body("Error deleting lesson.");
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<String> deleteLessonsByUser(@PathVariable String userId) {
        return this.lessonDAO.deleteLessonByUser(userId)
                ? ResponseEntity.ok("Lessons deleted successfully!")
                : ResponseEntity.internalServerError()
                        .body("Error deleting lessons for user.");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAllLessons() {
        return this.lessonDAO.deleteAllLessons()
                ? ResponseEntity.ok("All lessons deleted successfully!")
                : ResponseEntity.internalServerError()
                        .body("Error deleting all lessons.");
    }
}
