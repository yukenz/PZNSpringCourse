package com.awan.pznspring.mvc;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class DateController {

    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    @GetMapping(path = "/stringToDate", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String getDate(@RequestParam(name = "date") Date date) {
        return sdf.format(date).toString();
    }

}
