package org.ptit.soccerrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class SoccerRestApplication {

    public static void main(String[] args) {
        normalizeLegacyTimeZoneAlias();
        SpringApplication.run(SoccerRestApplication.class, args);
    }

    private static void normalizeLegacyTimeZoneAlias() {
        String currentTimeZoneId = TimeZone.getDefault().getID();
        if ("Asia/Saigon".equals(currentTimeZoneId)) {
            String supportedTimeZoneId = "Asia/Ho_Chi_Minh";
            TimeZone.setDefault(TimeZone.getTimeZone(supportedTimeZoneId));
            System.setProperty("user.timezone", supportedTimeZoneId);
        }
    }

}
