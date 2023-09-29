package com.awan.pznspring.mvc;

import com.awan.pznspring.mvc.model.CreateAddressRequest;
import com.awan.pznspring.mvc.model.CreatePersonRequest;
import com.awan.pznspring.mvc.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.Cookie;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.htmlunit.MockMvcWebConnection;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class HandlerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void getWithParam() throws Exception {

        ResultActions resultActions1 = mockMvc.perform(get("/getWithParam").queryParam("name", "Awan")).andExpectAll(
                content().string(Matchers.containsString("Hello Awan")),
                status().isOk()
        );

        ResultActions resultActions2 = mockMvc.perform(get("/getWithParam")).andExpectAll(
                content().string(Matchers.containsString("Hello Guest")),
                status().isOk()
        );

        resultActions1.andDo(result -> System.out.println(result.getResponse().getContentAsString()));
        System.out.println("------");
        resultActions2.andDo(result -> System.out.println(result.getResponse().getContentAsString()));
    }

    @Test
    void getWithContentType() throws Exception {

        mockMvc.perform(get("/getWithContentType").contentType(MediaType.TEXT_PLAIN).queryParam("name", "Awan")).andExpectAll(
                status().isOk(),
                content().contentType(MediaType.APPLICATION_JSON_VALUE)
        ).andDo(result -> System.out.println(result.getResponse().getContentAsString()));
    }

    @Test
    void getWithHeader() throws Exception {

        mockMvc.perform(get("/getWithHeader").header("Authorization", Base64.getEncoder().encodeToString("awan".getBytes()))).andExpectAll(
                status().isOk(),
                content().string(Matchers.containsString(Base64.getEncoder().encodeToString("awan".getBytes())))
        ).andDo(result -> System.out.println(result.getResponse().getContentAsString()));
    }


    @Test
    void getWithPathVariable() throws Exception {

        mockMvc.perform(get("/getWithPathVariable/" + "Awantheravian")).andExpectAll(
                status().isOk(),
                content().string(Matchers.containsString("Awantheravian"))
        ).andDo(result -> System.out.println(result.getResponse().getContentAsString()));

    }

    @Test
    void getWithFormData() throws Exception {

        LinkedMultiValueMap<String, String> formData = new LinkedMultiValueMap<>();

        formData.add("username", "awan");
        formData.add("password", "theravian");

        mockMvc.perform(get("/getWithFormData").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .params(formData))
                .andExpectAll(
                        status().isOk(),
                        content().string(Matchers.containsString("Sukses Login"))
                ).andDo(result -> System.out.println(result.getResponse().getContentAsString()));

        formData.add("password", "thebelgedes");
        mockMvc.perform(get("/getWithFormData").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .params(formData))
                .andExpectAll(
                        status().isUnauthorized(),
                        content().string(Matchers.containsString("Gagal Login"))
                ).andDo(result -> System.out.println(result.getResponse().getContentAsString()));

    }

    @Test
    void postWithUploadFile() throws Exception {

        Path uploadFile = Path.of("/Users/yukenz/Pictures/KTM.jpg");
        InputStream uploadFileStream = Files.newInputStream(uploadFile);

        mockMvc.perform(multipart("/postWithUploadFile")
                .file(new MockMultipartFile("fileUpload", "awan.jpg", MediaType.IMAGE_JPEG_VALUE, uploadFileStream))
                .contentType(MediaType.MULTIPART_FORM_DATA)
        ).andExpectAll(
                status().isOk(),
                content().string(Matchers.containsString("Sukses Upload"))
        ).andDo(result -> System.out.println(result.getResponse().getStatus() + " : " + result.getResponse().getContentAsString()));

        mockMvc.perform(post("/postWithUploadFile")
                .contentType(MediaType.MULTIPART_FORM_DATA)
        ).andExpectAll(
                status().isNotAcceptable(),
                content().string(Matchers.containsString("File Kosong, Gagal Upload"))
        ).andDo(result -> System.out.println(result.getResponse().getStatus() + " : " + result.getResponse().getContentAsString()));

    }

    @Test
    void postWithBody() throws Exception {


        mockMvc.perform(post("/postWithBody")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "name" : "awan"
                        }
                        """
                )
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> System.out.println(result.getResponse().getStatus() + " : " + result.getResponse().getContentAsString()));

    }

    @Test
    void postAuthLogin() throws Exception {

        LinkedMultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.add("username", "awan");
        form.add("password", "theravian");

        ResultActions resultActionsSuccess = mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .params(form)
        ).andExpectAll(
                status().isOk(),
                cookie().value("Authorization",
                        Base64.getEncoder()
                                .encodeToString("awan".getBytes())
                ));

        resultActionsSuccess.andDo(result -> System.out.println(result.getResponse().getStatus() + " : " + result.getResponse().getContentAsString()));
        resultActionsSuccess.andDo(result -> System.out.println("Cookie Username : " + result.getResponse().getCookie("Authorization").getValue()));


        ResultActions resultActionsFail = mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
        ).andExpectAll(
                status().isUnauthorized());

        resultActionsFail.andDo(result -> System.out.println(result.getResponse().getStatus() + " : " + result.getResponse().getContentAsString()));
    }

    @Test
    void postAuthGet() throws Exception {

        ResultActions resultActionsSuccess = mockMvc.perform(
                get("/auth/get")
                        .cookie(new Cookie("Authorization", "YXdhbg=="))
        ).andExpectAll(
                status().isOk()
        );
        resultActionsSuccess.andDo(result -> System.out.println(result.getResponse().getStatus() + " : " + result.getResponse().getContentAsString()));

        ResultActions resultActionsFail = mockMvc.perform(
                get("/auth/get")
                        .cookie(new Cookie("Authorization", ""))
        ).andExpectAll(
                status().isUnauthorized()
        );
        resultActionsFail.andDo(result -> System.out.println(result.getResponse().getStatus() + " : " + result.getResponse().getContentAsString()));
    }

    @Test
    void postFromToModelAttr() throws Exception {


        mockMvc.perform(
                post("/postFromToModelAttr")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .params(getForm())
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> System.out.println(result.getResponse().getContentAsString()));

    }

    private static LinkedMultiValueMap<String, String> getForm() {
        LinkedMultiValueMap<String, String> form = new LinkedMultiValueMap<>();

        form.add("firstName", "Yuyun");
        form.add("lastName", "Purniawan");
        form.add("middleName", "");
        form.add("email", "yuyun.purniawan@gmail.com");
        form.add("phone", "085156107536");
        form.add("address.city", "Klaten");
        form.add("address.street", "Wiro");
        form.add("address.country", "Indonesia");
        form.add("address.postCode", "085156");
        form.add("hobbies[0]", "Gitaran");
        form.add("hobbies[1]", "Ngoding");
        return form;
    }

    @Test
    void postJacksonJson() throws Exception {

        CreateAddressRequest addressRequest = new CreateAddressRequest();
        addressRequest.setCity("Kota Depok");
        addressRequest.setPostCode("0000");
        addressRequest.setCountry("Indonesia");
        addressRequest.setStreet("Taman Rajawali 3");
        CreatePersonRequest request = new CreatePersonRequest();
        request.setFirstName("Yuyun");
        request.setLastName("Purniawan");
        request.setMiddleName("W");
        request.setHobbies(List.of("Makan", "Tidur", "Ngoding"));
        request.setEmail("yuyun.purniawan@gmail.com");
        request.setPhone("085156107536");
        request.setAddress(addressRequest);

        mockMvc.perform(
                post("/postJacksonJson").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpectAll(
                status().isOk(),
                content().json(objectMapper.writeValueAsString(request))
        ).andDo(result -> System.out.println(result.getResponse().getContentAsString()));

    }

    @Test
    void postJacksonJsonValid() throws Exception {

        CreateAddressRequest addressRequest = new CreateAddressRequest();
        addressRequest.setCity("Kota Depok");
        addressRequest.setPostCode("0000");
        addressRequest.setCountry("Indonesia");
        addressRequest.setStreet("Taman Rajawali 3");
        CreatePersonRequest request = new CreatePersonRequest();
        request.setFirstName("Yuyun");
        request.setLastName("Purniawan");
        request.setMiddleName("W");
        request.setHobbies(List.of("Makan", "Tidur", "Ngoding"));
        request.setEmail("yuyun.purniawan@gmail.com");
//        request.setPhone("085156107536");
        request.setAddress(addressRequest);

        mockMvc.perform(
                post("/postJacksonJsonValid").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpectAll(
                status().isBadRequest()
//                content().json(objectMapper.writeValueAsString(request))
        ).andDo(result -> System.out.println(result.getResponse().getContentAsString()));

    }

    @Test
    void bindingResult() throws Exception {

        LinkedMultiValueMap<String, String> form = new LinkedMultiValueMap<>();

        form.add("city", "Kota Depok");
        form.add("postCode", "0000");
        form.add("country", "Indonesia");
//        form.add("street", "Taman Rajawali 3");


        mockMvc.perform(
                get("/bindingResult").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .params(form)
        ).andExpectAll(
                status().isBadRequest()
        ).andDo(result -> System.out.println(result.getResponse().getContentAsString()));

    }

    @Test
    void middleWareSession() throws Exception {


        User user = new User();
        user.setId(1);
        user.setUsername("awan");
        user.setPassword("theravian");

        mockMvc.perform(
                get("/mdw/user/current")

        ).andExpectAll(
                status().is3xxRedirection()
        );

    }

    @Test
    void getStaticIndexHTML() throws Exception {

        mockMvc.perform(
                get("/index.html")
        ).andExpectAll(
                status().isOk(),
                content().string(Matchers.containsString("Hello Static"))
        );

    }

    @Test
    void getMustacheView() throws Exception {

        mockMvc.perform(
                get("/mustache/get").param("name", "awan")
        ).andExpectAll(
                status().isOk(),
                content().string(Matchers.containsString("Hello awan")),
                content().string(Matchers.containsString("Belajar MVC"))
        ).andDo(result -> System.out.println(result.getResponse().getContentAsString()));

        mockMvc.perform(
                get("/mustache/get")
        ).andExpectAll(
                status().is3xxRedirection()
        ).andDo(result -> {
            String redirectedUrl = result.getResponse().getRedirectedUrl();
            mockMvc.perform(
                    get(redirectedUrl)
            ).andExpectAll(
                    status().isOk(),
                    content().string(Matchers.containsString("Hello Guest")),
                    content().string(Matchers.containsString("Belajar MVC"))
            ).andDo(result1 -> System.out.println(result1.getResponse().getContentAsString()));
        });

    }
}
