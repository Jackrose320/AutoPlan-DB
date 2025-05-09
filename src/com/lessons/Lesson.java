package com.lessons;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Lesson {
    private String lesson_id;
    private String title;
    private String subject;
    private String description;
    private String level;
    private String status;
    private LocalDateTime created_at;
    private String user_id;
}
