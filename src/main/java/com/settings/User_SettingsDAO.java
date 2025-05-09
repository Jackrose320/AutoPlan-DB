package main.java.com.settings;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * DB interaction with "user_settings" table
 */
public class User_SettingsDAO {
    private Connection conn;

    public User_SettingsDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * Adds settings to the database.
     *
     * @param userSettings
     *            the settings to add
     * @throws SQLException
     */
    public void addSettings(User_Settings userSettings) throws SQLException {
        String sql = "INSERT INTO users (user_id, theme, email_notifications, profile_private, font_size, reduced_motion, online_status, search_engine_indexing, notification_frequency, sms_alerts, is_premium_user) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = this.conn.prepareStatement(sql)) {
            stmt.setString(1, userSettings.getUser_id()); // user_id
            stmt.setString(2, userSettings.getTheme()); // theme
            stmt.setBoolean(3, userSettings.isEmail_notifications()); // email_notifications
            stmt.setBoolean(4, userSettings.isProfile_private()); // profile_private
            stmt.setString(5, userSettings.getFont_size()); // font_size
            stmt.setBoolean(6, userSettings.isReduced_motion()); // reduced_motion
            stmt.setBoolean(7, userSettings.isOnline_status()); // online_status
            stmt.setBoolean(8, userSettings.isSearch_engine_indexing()); // search_engine_indexing
            stmt.setString(9, userSettings.getNotification_frequency()); // notification_frequency
            stmt.setBoolean(10, userSettings.isSms_alerts()); // sms_alerts
            stmt.setBoolean(11, userSettings.is_premium_user()); // is_premium_user

            stmt.executeUpdate();
        }
    }

    /**
     * Fetches the user settings by the id you add
     *
     * @param user_id
     *            ID of said user
     * @return SELECT * FROM user_settings WHERE user_id = ?
     * @throws SQLException
     */
    public User_Settings getSettingsByID(String user_id) throws SQLException {
        String sql = "SELECT * FROM user_settings WHERE user_id = ?";
        try (PreparedStatement stmt = this.conn.prepareStatement(sql)) {
            stmt.setString(1, user_id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User_Settings(rs.getString("user_id"), rs.getString("theme"),
                        rs.getBoolean("email_notifications"),
                        rs.getBoolean("profile_private"), rs.getString("font_size"),
                        rs.getBoolean("reduced_motion"), rs.getBoolean("online_status"),
                        rs.getBoolean("search_engine_indexing"),
                        rs.getString("notification_frequency"),
                        rs.getBoolean("sms_alerts"), rs.getBoolean("is_premium_user"));
            }
        }
        return null;
    }

    /**
     * Returns a {@link List} of all user_settings in the database.
     *
     * @return SELECT * FROM user_settings
     * @throws SQLException
     */
    public List<User_Settings> getAllSettings() throws SQLException {
        List<User_Settings> settings = new ArrayList<>();
        String sql = "SELECT * FROM user_settings";
        try (Statement stmt = this.conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {

                settings.add(this.getSettingsByID(rs.getString("user_id")));
            }
        }
        return settings;
    }

    /**
     * Deletes user settings (with the corresponding user id) from the database.
     *
     * @param user_id
     *            user's id to identify the setting to delete
     */
    public boolean deleteUserSettings(String user_id) throws SQLException {
        String sql = "DELETE FROM user_settings WHERE user_id = ?";
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
     * Deletes all settings from the database.<br/>
     * <font color="red"><b><u>WARNING:</u></b> This method can cause permanent
     * data loss.</font>
     *
     */
    public boolean deleteAllSettings() {
        String sql = "DELETE FROM user_settings";
        try (PreparedStatement stmt = this.conn.prepareStatement(sql)) {
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
