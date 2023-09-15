package com.awan.pznspring.configproc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.TestPropertySources;

@SpringBootTest
@TestPropertySources(@TestPropertySource("/test.properties"))
public class ValueAnnotationTest {

    @Autowired
    AuthorBean authorBean;

    @Value("${test.author.name.first}")
    private String firstNameTester;
    @Value("${test.author.name.last}")
    private String lastNameTester;
    @Value("${test.author.age}")
    private Integer ageTester;

    @Test
    void testValueAuthor() {

        String firstName = authorBean.getFirstName();
        String lastName = authorBean.getLastName();
        System.out.printf("%s %s%n", firstName, lastName);

    }

    @Test
    void testResourceTest() {

        System.out.printf("author is %s %s (%d) %n", firstNameTester, lastNameTester, ageTester);

    }
}
