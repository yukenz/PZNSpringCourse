package com.awan.pznspring.mvc;

import static org.junit.jupiter.api.Assertions.*;

import com.awan.pznspring.DisplayNameGeneratorImpl;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayNameGeneration(DisplayNameGeneratorImpl.class)
public class HelloServiceTest {

    @Autowired
    HelloService helloService;

    @Test
    @EnabledOnOs(OS.MAC)
    void testHelloService() {

        assertEquals("Hello Guest", helloService.hello(null));
        assertEquals("Hello Awan", helloService.hello("Awan"));

    }
}
