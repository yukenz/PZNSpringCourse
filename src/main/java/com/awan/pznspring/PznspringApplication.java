package com.awan.pznspring;

import com.awan.pznspring.configproc.SampleConfigurationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
@EnableConfigurationProperties({
        SampleConfigurationProperties.class
})
@PropertySources({@PropertySource("classpath:/additional.properties")})
public class PznspringApplication {


    public static void main(String[] args) {
        SpringApplication.run(PznspringApplication.class, args);
    }

}
