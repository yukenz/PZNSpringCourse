package com.awan.pznspring.pzntest;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import java.util.Random;

public class RandomParameterResolver implements ParameterResolver {

    Random random = new Random();

    /* Jika di method Test butuh parameter yang sama dengan Resolver ini yaitu random instance*/
    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType() == Random.class;
    }

    /* Maka Inject kan variabel random */
    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return random;
    }
}
