package com.awan.pznspring;

import org.junit.jupiter.api.DisplayNameGenerator;

import java.lang.reflect.Method;

public class DisplayNameGeneratorImpl implements DisplayNameGenerator {
    @Override
    public String generateDisplayNameForClass(Class<?> testClass) {
        return String.format("Class : %s", testClass.getName());
    }

    @Override
    public String generateDisplayNameForNestedClass(Class<?> nestedClass) {
        return String.format("Class : %s", nestedClass.getName());
    }

    @Override
    public String generateDisplayNameForMethod(Class<?> testClass, Method testMethod) {
        return String.format("Test : %s", testMethod.getName());
    }
}
