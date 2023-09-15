package com.awan.pznspring.pzntest;

import org.junit.jupiter.api.DisplayNameGenerator;

import java.lang.reflect.Method;

public class DisplayNameGeneratorImpl implements DisplayNameGenerator {

    @Override
    public String generateDisplayNameForClass(Class<?> testClass) {
        return "Class :" + testClass.getName();
    }

    @Override
    public String generateDisplayNameForMethod(Class<?> testClass, Method testMethod) {
        return String.format("Test : %s", testMethod.getName());
    }

    @Override
    public String generateDisplayNameForNestedClass(Class<?> nestedClass) {
        return null;
    }

}
