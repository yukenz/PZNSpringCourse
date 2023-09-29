package com.awan.pznspring.aop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HelloService {

    /*
     * Membuat Log seperti dibawah ini tidaklah efektif
     * */
    public void sayHello() {
        log.info("Hello Called");
    }

    public void sayBye() {
        log.info("Bye Called");
    }

}
