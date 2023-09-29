package com.awan.pznspring.configproc;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;


/*
* Otomatis assign value dari properties sesuai prefix yang dihasilkan
* File META-INF akan otomatis terbuat
*
* Perlu konfiguarsi di root container
* @EnableConfigurationProperties({
        SampleConfigurationProperties.class
})
*
* */
@Getter
@Setter
@ConfigurationProperties("spring.application")
public class SampleConfigurationProperties {
    private String name;
    private String version;
    private DatabaseProperties database;

    @Getter
    @Setter
    public static class DatabaseProperties {
        private String username;
        private String password;
    }
}