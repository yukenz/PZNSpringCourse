package com.awan.pznspring.mvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.awan.pznspring.DisplayNameGeneratorImpl;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
/* Otomatis membuat Beans MockMvc yang ready to inject */
@AutoConfigureMockMvc
/* Penamaan Test */
@DisplayNameGeneration(DisplayNameGeneratorImpl.class)
/* Mengurutkan Test based on @Order(int)*/
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
/* Test dibuat satu object */
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
/* Parallel (need junit-platform.properties) */
//@Execution(ExecutionMode.CONCURRENT)
public class HelloControllerMockTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private HelloService helloService;

    @BeforeEach
    void setUp() {
        Mockito.when(helloService.hello(Mockito.anyString())).thenAnswer(invocation -> {
            return "Hello "+ invocation.getArgument(0, String.class);
        });
        Mockito.when(helloService.hello(Mockito.isNull())).thenAnswer(invocation -> {
            return "Hello Guest";
        });
    }

    @Test
    @Order(2)
    void testGuest() throws Exception {
        mockMvc.perform(get("/hello")).andExpectAll(
                status().isOk(),
                content().string(Matchers.containsString("Hello Guest"))
        );

    }

    @Test
    @Order(1)
    void testUser() throws Exception {
        mockMvc.perform(get("/hello").queryParam("name", "Awan")).andExpectAll(
                status().isOk(),
                content().string(Matchers.containsString("Hello Awan"))
        );
    }

    @Test
    void testInfo(TestInfo testInfo) {
        System.out.println(testInfo.getTestClass().get());
    }

    @Test
    void testPost() throws Exception {

        mockMvc.perform(post("/hello")).andExpectAll(
                status().isMethodNotAllowed()
        );

    }
}
