package com.awan.pznspring.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect // Menandai bahwa bean adalah aspectHandler
@Component
@Slf4j
public class LogAspect {

    /* Pointcut =
     * execution (modifier class.method(args) throws) //support regex
     * within = regex
     * this
     * target = semua method
     * @target(anotasi) = yang memiliki anotasi
     * args = method dengan args
     *
     * */


    /*
     * pointCut ditulis secara eksplisit
     * */
    @Pointcut("target(com.awan.pznspring.aop.HelloService)")
    public void helloServiceMethod() {
    }

    /*
     * pointCut ditulis secara implisit
     * */
    @Before("helloServiceMethod()")
    public void beforeHelloServiceMethod(JoinPoint joinPoint) {
        log.info(String.format("This log called before %s.%s", joinPoint.getTarget().getClass().getName(), joinPoint.getSignature().getName()));
    }

    @After("helloServiceMethod()")
    public void afterHelloServiceMethod(JoinPoint joinPoint) {
        log.info(String.format("This log called after %s.%s", joinPoint.getTarget().getClass().getName(), joinPoint.getSignature().getName()));
    }

    /*
     * Around memakai ProceedingJoinPoint
     * */
    @Around("helloServiceMethod()")
    public void aroundHelloServiceMethod(ProceedingJoinPoint proceedingJoinPoint) {
        try {
            log.info(String.format("This log called before around after %s.%s", proceedingJoinPoint.getTarget().getClass().getName(), proceedingJoinPoint.getSignature().getName()));
            //Khusus around butuh untuk di invoke terlebih dahulu
            proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
        } catch (Throwable throwable) {
            log.info(String.format("This log called after throw after %s.%s", proceedingJoinPoint.getTarget().getClass().getName(), proceedingJoinPoint.getSignature().getName()));

        } finally {
            log.info(String.format("This log called after around %s.%s", proceedingJoinPoint.getTarget().getClass().getName(), proceedingJoinPoint.getSignature().getName()));

        }
    }


    @Around(value = "execution(* com.awan.pznspring.aop.PingService.*(int)) && args(value))")
    public void aroundPingService(ProceedingJoinPoint proceedingJoinPoint, int value) {
        try {
            log.info(String.format("Before Exec %s.%s(%d)", proceedingJoinPoint.getTarget().getClass().getName(), proceedingJoinPoint.getSignature().getName(), value));
            proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
        } catch (Throwable err) {
            log.info(String.format("After Err %s.%s(%d)", proceedingJoinPoint.getTarget().getClass().getName(), proceedingJoinPoint.getSignature().getName(), value));
        } finally {
            log.info(String.format("After Exec %s.%s(%d)", proceedingJoinPoint.getTarget().getClass().getName(), proceedingJoinPoint.getSignature().getName(), value));
        }
    }
}
