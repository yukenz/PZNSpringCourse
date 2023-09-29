package com.awan.pznspring.aop;

import org.springframework.beans.factory.Aware;

public interface HelloServiceAware extends Aware {

    void setHelloService(HelloService helloService);

}
