package com.awan.pznspring.pzntest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extensions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@DisplayNameGeneration(DisplayNameGeneratorImpl.class)
/* Dibutuhkan untuk DI pada Junit */
@Extensions({
        @ExtendWith({RandomParameterResolver.class})
})

public class RandomResolverTest {

    @Test
    void testRandomInjection(Random random) {

        Assertions.assertNotNull(random);
        Assertions.assertInstanceOf(Integer.class, random.nextInt());

    }

    /* Parameterized and Iteration Test */
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    void testParameterized(int value) {
        var res = value + value;
        Assertions.assertEquals(res, value + value);

    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS)
    void testTimeout() throws InterruptedException {
        Thread.sleep(999); /*Success*/
//        Thread.sleep(1000); /*Fail*/
    }
}
