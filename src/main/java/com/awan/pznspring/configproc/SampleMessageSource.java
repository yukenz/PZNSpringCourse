package com.awan.pznspring.configproc;

import lombok.Setter;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.stereotype.Component;

import java.util.Locale;

/*
 * Internationalization di Spring menggunakan Bean MessageSource yang otomatis dibuat Spring
 * Menggunakan Aware untuk melakukan injection
 *
 * Resouce bundle menggunakan nama messages.properties
 * I18n menggunakan messages_in_ID.properties1 contohnya
 *
 * */
@Component
public class SampleMessageSource implements MessageSourceAware {

    @Setter
    private MessageSource messageSource;

    public String hello(Locale locale) {
        return messageSource.getMessage("hello", new Object[]{"Awan"}, locale);
    }

}