package com.awan.pznspring.configproc;

import com.awan.pznspring.PznspringApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = PznspringApplication.class)
public class ResourceTest {

    @Autowired
    SampleResource sampleResource;


    @Test
    void sampleResource() {

        assertNotNull(sampleResource);
        System.out.println(sampleResource.getProperties());

    }

    @Test
    void testResources() throws IOException {

        Resource classPathResource = new ClassPathResource("/application.properties");

        assertNotNull(classPathResource);
        System.out.println(classPathResource.getFile().getAbsolutePath());
        System.out.println(classPathResource.getFilename());
        System.out.println(classPathResource.getDescription());


    }
}
