package com.awan.pznspring.mvc;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/rest")
public class RestHandlerController {

    @GetMapping()
    public String getHello(@RequestHeader(name = "Authorization") String authorization) {
        return authorization;
    }

}
