package com.users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * DB interaction with "user" table
 */
public class UserDAO {
    private Connection conn;

    public UserDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * Adds a user to the database.
     *
     * @param user
     *            the user to add
     * @throws SQLException
     */
    public void addUser(User user) throws SQLException {
        String sql = "INSERT INTO users (user_id, username, preferred_name, email) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = this.conn.prepareStatement(sql)) {
            stmt.setString(1, user.getId());
            stmt.setString(2, user.getUsername());
            stmt.setString(3, user.getPreferredName());
            stmt.setString(4, user.getEmail());
            stmt.executeUpdate();
        }
    }

    /**
     * Fetches the user by the id you enter.
     *
     * @param user_id
     *            ID of said user
     * @return SELECT * FROM users WHERE user_id = ?
     * @throws SQLException
     */
    public User getUserById(String user_id) throws SQLException {
        String sql = "SELECT * FROM users WHERE user_id = ?";
        try (PreparedStatement stmt = this.conn.prepareStatement(sql)) {
            stmt.setString(1, user_id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(rs.getString("user_id"), rs.getString("username"),
                        rs.getString("preferred_name"), rs.getString("email"));
            }
        }
        return null;
    }

    /**
     * Returns a {@link List} of all users in the database.
     *
     * @return SELECT * FROM users
     * @throws SQLException
     */
    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Statement stmt = this.conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                users.add(new User(rs.getString("user_id"), rs.getString("username"),
                        rs.getString("preferred_name"), rs.getString("email")));
            }
        }
        return users;
    }

    public boolean updateUser(int user_id, User newParams) {
        String sql = "UPDATE users SET username = ?, preferred_name = ?, email = ? WHERE user_id = ?";
        try (PreparedStatement stmt = this.conn.prepareStatement(sql)) {
            stmt.setString(1, newParams.getUsername()); // Assuming User has getUsername()
            stmt.setString(2, newParams.getPreferredName()); // Assuming User has getPreferredName()
            stmt.setString(3, newParams.getEmail()); // Assuming User has getEmail()
            stmt.setInt(4, user_id);

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Deletes a user (with the corresponding user id) from the database.
     * <p>
     * DELETE FROM users WHERE user_id = ?
     * </p>
     *
     * @param user_id
     *            user's id to identify the user to delete
     */
    public boolean deleteUser(String user_id) {
        String sql = "DELETE FROM users WHERE user_id = ?";
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
     * Deletes all users from the database.<br/>
     * <font color="red"><b><u>WARNING:</u></b> This method can cause permanent
     * data loss.</font>
     *
     */
    public boolean deleteAllUsers() {
        String sql = "DELETE FROM users";
        try (PreparedStatement stmt = this.conn.prepareStatement(sql)) {
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
