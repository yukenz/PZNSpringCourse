package com.awan.pznspring.configproc;

import lombok.Setter;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/*
 * Object bean environment yang digunakan untuk mengakses properties
 * Object environment otomatis dibuat oleh spring, perlu di inject menggunakan Aware
 *
 * */
@Component
public class SampleEnvironment implements EnvironmentAware {

    @Setter
    private Environment environment;

    public String getAppName() {
        return environment.getProperty("spring.application.name");
    }

    public String getJavaHome() {
        return environment.getProperty("TMPDIR");
    }
}
