package com.awan.pznspring.mvc;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.Objects;

@Controller
public class HelloController {

    @Autowired
    private HelloService helloService;

    @GetMapping(path = "/hello")
    @ResponseBody
    public String helloWorld(@RequestParam(name = "name", defaultValue = "") String name) throws IOException {

        /* HttpServletRequest request, HttpServletResponse response */
        //String name = request.getParameter("name");

        String responseBody = helloService.hello(name);

        if (responseBody.isBlank()) {
            responseBody = "Guest";
        }

        /* response.getWriter().println(String.format("Hello %s", responseBody)); */
        return String.format("Hello %s", responseBody);
    }

}
