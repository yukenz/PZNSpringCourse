package com.awan.pznspring.aop;

import lombok.Setter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AspectTest implements HelloServiceAware {

    @Setter
    private HelloService helloService;

    @Autowired
    public PingService pingService;

    @Test
    void testService() {
        helloService.sayHello();
        helloService.sayBye();
    }

    @Test
    void testPingExecution() {
        pingService.send(5);
        pingService.reply(5);
    }
}
