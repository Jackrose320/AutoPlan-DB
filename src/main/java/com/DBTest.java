package main.java.com;

import java.sql.Connection;

public class DBTest {
    public static void main(String[] args) {
        try (Connection conn = Database.getConnection()) {
            if (conn != null) {
                System.out.println("✅ Connected to the database!");
            }
        } catch (Exception e) {
            System.out.println("❌ Connection failed.");
            e.printStackTrace();
        }
    }
}
