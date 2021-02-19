package io.anymobi.sender.controller;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AopTest {

    @Pointcut("@annotation(Mobile)")
    public void mobileAop(){}

    @Around("mobileAop()")
    public Object aopTest(ProceedingJoinPoint joint) throws Throwable {
        Object retVal = joint.proceed();
        return retVal;
    }


}
