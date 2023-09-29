package com.awan.pznspring.mvc;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class DateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetDate() throws Exception {
        /* yyyyMMdd to dd-MM-yyyy*/
        mockMvc.perform(get("/stringToDate").contentType(MediaType.TEXT_PLAIN).queryParam("date", "20020112")).andExpectAll(
                status().isOk(),
                content().string(Matchers.containsString("12-01-2002")),
                content().contentType("text/plain;charset=UTF-8")

        );

    }
}
