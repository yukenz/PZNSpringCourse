package com.awan.pznspring.configproc;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoField;

/*
* Melakukan conditional Bean dengan @Profile
* tergantung pada spring.profiles.active=production
* Class Kandidat harus diwakilkan onleh Interface saat AutoWired
*
* */
public class DateHandler {

    @Profile("local")
    @Component
    public static class DateHandlerLocal implements DateHandlerEnv {

        private LocalDateTime now = LocalDateTime.now();

        @Override
        public int getDayOfYear() {
            return now.get(ChronoField.DAY_OF_YEAR);
        }
    }

    @Profile("production")
    @Component
    public static class DateHandlerProduction implements DateHandlerEnv {

        private LocalDateTime now = LocalDateTime.now().plusDays(1);

        @Override
        public int getDayOfYear() {
            return now.get(ChronoField.DAY_OF_YEAR);
        }
    }

}
