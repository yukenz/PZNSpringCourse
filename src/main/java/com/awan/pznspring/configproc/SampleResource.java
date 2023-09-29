package com.awan.pznspring.configproc;

import lombok.Setter;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;


/*
 * Mengakses file resouce dengan Bean Resource Loader
 * Inject Helper menggunakan Aware
 *
 * */
@Component
public class SampleResource implements ResourceLoaderAware {

    @Setter
    private ResourceLoader resourceLoader;


    public String getProperties() {
        Resource resource = resourceLoader.getResource("classpath:/resource.txt");
        try {
            InputStream inputStream = resource.getInputStream();
            return new String(inputStream.readAllBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}