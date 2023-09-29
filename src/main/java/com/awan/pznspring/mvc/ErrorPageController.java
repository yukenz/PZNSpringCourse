package com.awan.pznspring.mvc;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorPageController implements ErrorController {

    @RequestMapping(path = "/error", produces = MediaType.TEXT_HTML_VALUE)
    ResponseEntity error(HttpServletRequest request) {


        Integer code = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        String message = (String) request.getAttribute(RequestDispatcher.ERROR_MESSAGE);

        String responseHTML = """
                <html>
                <head>
                <title>Error</title>
                </head>
                <body>
                <p> $code : $message </p>
                </body>
                </html>
                """.replace("$code", String.valueOf(code)).replace("$message", message);

        return ResponseEntity.status(code).body(responseHTML);
    }


}
