package com.awan.pznspring.configproc;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/*
* Mengakses value dari application properties
* */
@Component
@Getter
public class AuthorBean {

    @Value("${author.name.first}")
    private String firstName;

    @Value("${author.name.last}")
    private String lastName;

    @Value("${author.age}")
    private Integer age;

}
