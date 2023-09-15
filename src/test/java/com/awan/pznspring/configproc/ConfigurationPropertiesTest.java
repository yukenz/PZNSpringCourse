package com.awan.pznspring.configproc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ConfigurationPropertiesTest {

    @Autowired
    SampleConfigurationProperties propertiesClass;

    @Test
    void testConfigurationProperties() {

        System.out.println(propertiesClass.getName());
        System.out.println(propertiesClass.getVersion());
        System.out.println(propertiesClass.getDatabase().getUsername());
        System.out.println(propertiesClass.getDatabase().getPassword());


    }
}
