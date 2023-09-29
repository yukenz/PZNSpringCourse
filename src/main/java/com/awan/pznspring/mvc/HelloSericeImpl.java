package com.awan.pznspring.mvc;

import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class HelloSericeImpl implements HelloService {
    @Override
    public String hello(String name) {
        return String.format("Hello %s", Objects.isNull(name) ? "Guest" : name);
    }
}
