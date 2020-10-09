package com.fujiang.weiji.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class WebLogAspect {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.fujiang.weiji.controller.product..*.*(..))")
    public void webLog() {
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        System.out.println("Before Before Before Before Before Before Before Before Before");
    }

    @After("webLog()")
    public void doAfter(JoinPoint joinPoint) {
        System.out.println("After After After After After After After After After After After");
    }

    @Around("webLog()")
    public Object Around(ProceedingJoinPoint joinPoint) {
        try {
            System.out.println("Around11 Around1 Around1");
            Object object = joinPoint.proceed();
            System.out.println("Around2 Around2 Around2");
            return object;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }

    @AfterReturning("webLog()")
    public void doAfterReturning(JoinPoint joinPoint) {
        System.out.println("AfterReturning AfterReturning AfterReturning");
    }

    @AfterThrowing("webLog()")
    public void AfterThrowing(JoinPoint joinPoint) {
        System.out.println("AfterThrowing AfterThrowing AfterThrowing");
    }
}
