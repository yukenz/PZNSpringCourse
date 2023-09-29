package com.awan.pznspring.mvc;


import static org.junit.jupiter.api.Assertions.*;

import com.awan.pznspring.DisplayNameGeneratorImpl;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayNameGeneration(DisplayNameGeneratorImpl.class)
public class HelloControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    TestRestTemplate rest;

    @MockBean
    private HelloService helloService;

    @BeforeEach
    void setUp() {
        Mockito.when(helloService.hello(Mockito.anyString())).thenReturn("Hello Guys");
    }

    @Test
    void testGuest() {

        ResponseEntity<String> getHelloGuest = rest.getForEntity(String.format("http://localhost:%d/hello", port), String.class);
        String body = getHelloGuest.getBody();
        HttpStatusCode statusCode = getHelloGuest.getStatusCode();

        assertSame(HttpStatus.OK, statusCode);
        assertNotNull(body);
        assertEquals("Hello Guest", body.trim());

    }

    @Test
    void testUser() {

        ResponseEntity<String> getHelloGuest = rest.getForEntity(String.format("http://localhost:%d/hello?name=Awan", port), String.class);
        String body = getHelloGuest.getBody();
        HttpStatusCode statusCode = getHelloGuest.getStatusCode();

        assertSame(HttpStatus.OK, statusCode);
        assertNotNull(body);
        assertEquals("Hello Awan", body.trim());

    }
}
