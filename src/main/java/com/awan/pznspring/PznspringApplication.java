package com.awan.pznspring;

import com.awan.pznspring.configproc.SampleConfigurationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.web.client.RestTemplate;


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


    @Bean
    RestTemplate restTemplate(RestTemplateBuilder rtb) {
        return rtb.build();
    }
}
