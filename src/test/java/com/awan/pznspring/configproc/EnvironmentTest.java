package com.awan.pznspring.configproc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class EnvironmentTest {

    @Autowired
    SampleEnvironment sampleEnvironment;

    @Test
    void testAppProp() {

        assertNotNull(sampleEnvironment);
        assertEquals("Belajar Spring Boot Properties", sampleEnvironment.getAppName());

    }

    @Test
    void testOSProp() {
        System.out.println(sampleEnvironment.getJavaHome());
    }
}
