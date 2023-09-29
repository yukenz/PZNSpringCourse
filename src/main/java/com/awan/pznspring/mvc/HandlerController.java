package com.awan.pznspring.mvc;

import com.awan.pznspring.mvc.model.CreateAddressRequest;
import com.awan.pznspring.mvc.model.CreatePersonRequest;
import com.awan.pznspring.mvc.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.nio.file.*;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Controller
public class HandlerController {

    public final String TXT = ".txt";

    private final ObjectMapper objectMapper = new ObjectMapper();


    //GetWithParam
    @GetMapping(path = "/getWithParam")
    @ResponseBody
    public String getWithParam(@RequestParam(name = "name", defaultValue = "Guest") String name) {

        return "Hello " + name;
    }

    /*
     * GetWithContentType
     * consume = text/plain
     * result = json
     * */
    @GetMapping(path = "/getWithContentType", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public HashMap getWithContentType(@RequestParam(name = "name", defaultValue = "Guest") String name) {

        HashMap<String, String> body = new HashMap<>();
        body.put("name", name);

        return body;
    }

    @GetMapping(path = "/getWithHeader", produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String getWithHeader(@RequestHeader(name = "Authorization", defaultValue = "null") String name) {
        return name;
    }

    @GetMapping(path = "/getWithPathVariable/{pathVar}", produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String getWithPathVariable(@PathVariable(name = "pathVar") String pathVar) {

        return pathVar;
    }

    @GetMapping(path = "/getWithFormData", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    public ResponseEntity<String> getWithFormData(
            @RequestParam(name = "username", defaultValue = "") String username,
            @RequestParam(name = "password", defaultValue = "") String password) {

        if (username.equals("awan") && password.equals("theravian")) {
            return ResponseEntity.status(HttpStatus.OK).body("Sukses Login");
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Gagal Login");
    }

    @PostMapping(path = "/postWithUploadFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public ResponseEntity<String> postWithUploadFile(
            @RequestPart(name = "fileUpload", required = false) MultipartFile multipartFile
    ) throws IOException {

        if (Objects.isNull(multipartFile))
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("File Kosong, Gagal Upload");

        Path uploadPath = Paths.get("upload/" + multipartFile.getOriginalFilename());

        Files.write(uploadPath, multipartFile.getBytes(), StandardOpenOption.WRITE, StandardOpenOption.CREATE);

        return ResponseEntity.status(HttpStatus.OK).body("Sukses Upload");
    }

    @PostMapping(path = "/postWithBody", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HelloEntity.HelloResponse> postWithBody(
            @RequestBody HelloEntity.HelloRequest request
    ) throws JsonProcessingException {

//        HelloEntity.HelloRequest request = objectMapper.readValue(requestBody, HelloEntity.HelloRequest.class);

        HelloEntity.HelloResponse helloResponse = new HelloEntity.HelloResponse();

        helloResponse.setHello("Hello " + request.getName());

        return ResponseEntity.status(HttpStatus.OK).body(helloResponse);

    }

    @PostMapping(path = "/auth/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> authLogin(
            @RequestParam(name = "username", defaultValue = "") String username,
            @RequestParam(name = "password", defaultValue = "") String password,
            HttpServletResponse response
    ) {

        if (username.equals("awan") && password.equals("theravian")) {

            response.addCookie(new Cookie(
                    "Authorization",
                    Base64.getEncoder()
                            .encodeToString("awan".getBytes())
            ));

            return ResponseEntity.status(HttpStatus.OK).body("Sukses Login");
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Gagal Login");
    }

    @GetMapping(path = "/auth/get", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> authGet(@CookieValue(name = "Authorization", defaultValue = "") String usernameB64) {


        if (!usernameB64.isBlank()) {

            byte[] usernameDecode = Base64.getDecoder().decode(usernameB64);

            String username = new String(usernameDecode);

            return ResponseEntity.status(HttpStatus.OK).body("Kamu Login sebagai " + username);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Gagal Login");
    }

    @PostMapping(path = "/postFromToModelAttr", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<String> postFromToModelAttr(
            @ModelAttribute CreatePersonRequest request
    ) {

        StringBuilder response = new StringBuilder()
                .append("Success Create Person ")
                .append(request.toString() + "\n")
                .append(request.getAddress().toString() + "\n")
                .append(request.getHobbies().toString());

        return ResponseEntity.status(HttpStatus.OK).body(response.toString());

    }

    @PostMapping(path = "/postJacksonJson", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreatePersonRequest> postJacksonJson(
            @RequestBody CreatePersonRequest request
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(request);
    }

    @PostMapping(path = "/postJacksonJsonValid", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreatePersonRequest> postJacksonJsonValid(
            @RequestBody @Valid CreatePersonRequest request
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(request);
    }

    @GetMapping(path = "/bindingResult", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<String> bindingResult(
            @ModelAttribute @Valid CreateAddressRequest request,
            BindingResult bindingResult
    ) {


        if (!bindingResult.getAllErrors().isEmpty()) {
            return ResponseEntity.badRequest().body("You send invalid data");
        }

        return ResponseEntity.ok(request.getCountry());

    }

    @GetMapping(path = "/mdw/user/current")
    public ResponseEntity<String> getCurrentUser(
    ) {

        return ResponseEntity.ok("");

    }

    @GetMapping(path = "/mustache/get")
    public ModelAndView helloMustache(@RequestParam(name = "name", required = false) String name) {

        if (Objects.isNull(name)) {
            return new ModelAndView("redirect:/mustache/get?name=Guest");
        }

        return new ModelAndView("hello", Map.of(
                "title", "Belajar MVC",
                "name", name
        ));
    }


}
