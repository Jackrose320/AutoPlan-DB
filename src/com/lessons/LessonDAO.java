package com.lessons;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * DB interaction with "lessons" table
 */
public class LessonDAO {
    private Connection conn;

    public LessonDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * Adds a lesson to the database.
     *
     * @param lesson
     *            the lesson to add
     * @throws SQLException
     */
    public void addLesson(Lesson lesson) throws SQLException {
        String sql = "INSERT INTO lessons (lesson_id, title, subject, description, level, status, created_at, user_id)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = this.conn.prepareStatement(sql)) {
            stmt.setString(1, lesson.getLesson_id());
            stmt.setString(2, lesson.getTitle());
            stmt.setString(3, lesson.getSubject());
            stmt.setString(4, lesson.getDescription());
            stmt.setString(5, lesson.getLevel());
            stmt.setString(6, lesson.getStatus());
            stmt.setTimestamp(7, Timestamp.valueOf(lesson.getCreated_at())); // Converts LocalDateTime to SQL Timestamp
            stmt.setString(8, String.valueOf(lesson.getUser_id())); // assuming user_id is stored as char(36)
        }
    }

    /**
     * Fetches the lesson by the lesson_id you enter.
     *
     * @param lesson_id
     *            said lesson_id
     * @return SELECT * FROM lessons WHERE lesson_id = ?
     * @throws SQLException
     */
    public Lesson getLessonByID(String lesson_id) throws SQLException {
        String sql = "SELECT * FROM lessons WHERE lesson_id = ?";
        try (PreparedStatement stmt = this.conn.prepareStatement(sql)) {
            stmt.setString(1, lesson_id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Lesson(rs.getString("lesson_id"), rs.getString("title"),
                        rs.getString("subject"), rs.getString("description"),
                        rs.getString("level"), rs.getString("status"),
                        rs.getTimestamp("created_at").toLocalDateTime(), // Convert SQL timestamp to LocalDateTime
                        rs.getString("user_id"));
            }
        }
        return null;
    }

    /**
     * Fetches the lesson(s) by the user_id you enter.
     *
     * @param user_id
     *            said user id
     * @return SELECT * FROM lessons WHERE user_id = ?
     * @throws SQLException
     */
    public List<Lesson> getLessonsByUserID(String user_id) throws SQLException {
        List<Lesson> lessons = new ArrayList<>();
        String sql = "SELECT * FROM lessons WHERE user_id = ?";
        try (PreparedStatement stmt = this.conn.prepareStatement(sql)) {
            stmt.setString(1, user_id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Lesson lesson = new Lesson(rs.getString("lesson_id"),
                        rs.getString("title"), rs.getString("subject"),
                        rs.getString("description"), rs.getString("level"),
                        rs.getString("status"),
                        rs.getTimestamp("created_at").toLocalDateTime(),
                        rs.getString("user_id"));
                lessons.add(lesson);
            }
        }
        return lessons;
    }

    /**
     * Returns a {@link List} of all lessons in the database.
     *
     * @return SELECT * FROM lessons
     * @throws SQLException
     */
    public List<Lesson> getAllLessons() throws SQLException {
        List<Lesson> lessons = new ArrayList<>();
        String sql = "SELECT * FROM lessons";
        try (Statement stmt = this.conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                lessons.add(new Lesson(rs.getString("lesson_id"), rs.getString("title"),
                        rs.getString("subject"), rs.getString("description"),
                        rs.getString("level"), rs.getString("status"),
                        rs.getTimestamp("created_at").toLocalDateTime(), // Convert SQL timestamp to LocalDateTime
                        rs.getString("user_id")));
            }
        }
        return lessons;
    }

    /**
     * Deletes a user (with the corresponding lesson id) from the database.
     *
     * @param lesson_id
     *            lesson's id to identify the lesson to delete
     */
    public boolean deleteLesson(String lesson_id) {
        String sql = "DELETE FROM lessons WHERE lesson_id = ?";

        try (PreparedStatement stmt = this.conn.prepareStatement(sql)) {
            stmt.setString(1, lesson_id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Deletes a lesson (with the corresponding user id) from the database.
     *
     * @param user_id
     */
    public boolean deleteLessonByUser(String user_id) {
        String sql = "DELETE FROM lessons WHERE user_id = ?";

        try (PreparedStatement stmt = this.conn.prepareStatement(sql)) {
            stmt.setString(1, user_id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Deletes all lessons from the database.<br/>
     * <font color="red"><b><u>WARNING:</u></b> This method can cause permanent
     * data loss.</font>
     *
     * @throws SQLException
     */
    public boolean deleteAllLessons() {
        String sql = "DELETE FROM lessons";
        try (PreparedStatement stmt = this.conn.prepareStatement(sql)) {
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
