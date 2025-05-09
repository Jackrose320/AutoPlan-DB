package com.users;

/**
 * Data representation of {@code User}.
 */
public class User {
    private String user_id;
    private String username;
    private String preferredName;
    private String email;

    /**
     * Data representation of {@code User}.
     *
     * @param user_id
     *            1st param
     * @param username
     *            2nd param
     * @param preferredName
     *            3rd param
     * @param email
     *            4th param
     */
    public User(String user_id, String username, String preferredName, String email) {
        this.user_id = user_id;
        this.username = username;
        this.preferredName = preferredName;
        this.email = email;
    }

    public User(User other) {
        this.user_id = other.getId();
        this.username = other.getUsername();
        this.preferredName = other.getPreferredName();
        this.email = other.getEmail();
    }

    // Getters and setters:
    public String getId() {
        return this.user_id;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPreferredName() {
        return this.preferredName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setId(String user_id) {
        this.user_id = user_id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPreferredName(String preferredName) {
        this.preferredName = preferredName;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
