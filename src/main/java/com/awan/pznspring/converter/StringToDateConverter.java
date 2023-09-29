package com.awan.pznspring.converter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Slf4j
public class StringToDateConverter implements Converter<String, Date> {


    private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

    @Override
    public Date convert(String source) {
        try {
            Date dateParse = sdf.parse(source);
            log.info("success convert Date {}", source);
            return dateParse;
        } catch (ParseException e) {
            log.error(e.getMessage());
            return null;
        }
    }
}
