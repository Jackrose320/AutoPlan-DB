package main.java.com.settings;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User_Settings {
    private String user_id;
    private String theme;
    private boolean email_notifications;
    private boolean profile_private;
    private String font_size;
    private boolean reduced_motion;
    private boolean online_status;
    private boolean search_engine_indexing;
    private String notification_frequency;
    private boolean sms_alerts;
    private boolean is_premium_user;
}
