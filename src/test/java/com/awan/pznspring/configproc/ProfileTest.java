package com.awan.pznspring.configproc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProfileTest {

    @Autowired
    DateHandlerEnv dateHandler;

    @Test
    void testDateHandlerProfile() {

        System.out.println(dateHandler.getDayOfYear());

    }
}
