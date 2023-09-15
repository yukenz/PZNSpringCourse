package com.awan.pznspring.configproc;

import com.awan.pznspring.PznspringApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class MessageResourceTest {

    @Autowired
    SampleMessageSource sampleMessageSource;

    @Test
    void testGetResourceMessage() {
        assertNotNull(sampleMessageSource);

        System.out.println(sampleMessageSource.hello(Locale.getDefault()));

    }
}
