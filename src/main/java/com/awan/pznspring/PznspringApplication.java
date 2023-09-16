package com.awan.pznspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import com.awan.pznspring.configproc.SampleConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;


@SpringBootApplication
@EnableAspectJAutoProxy //Perlu untuk enable aspectJ
@EnableConfigurationProperties({
        SampleConfigurationProperties.class
})
@PropertySources({@PropertySource("classpath:/additional.properties")})
public class PznspringApplication {


    public static void main(String[] args) {
        SpringApplication.run(PznspringApplication.class, args);
    }

}
