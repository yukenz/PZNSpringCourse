package com.awan.pznspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@SpringBootApplication
@EnableAspectJAutoProxy //Perlu untuk enable aspectJ
public class PznspringApplication {


    public static void main(String[] args) {
        SpringApplication.run(PznspringApplication.class, args);
    }

}
