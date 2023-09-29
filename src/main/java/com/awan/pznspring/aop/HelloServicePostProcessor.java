package com.awan.pznspring.aop;

import lombok.Setter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;


/*
 * Menambahkan Bean Post Processor
 * Sehingga bean yang implementasi HelloServiceAware akan dilakukan injeksi
 * */
@Component
public class HelloServicePostProcessor implements BeanPostProcessor, ApplicationContextAware {

    @Setter
    private ApplicationContext applicationContext;


    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        if (bean instanceof HelloServiceAware) {
            ((HelloServiceAware) bean)
                    .setHelloService(applicationContext.getBean(HelloService.class));
        }

        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }
}
