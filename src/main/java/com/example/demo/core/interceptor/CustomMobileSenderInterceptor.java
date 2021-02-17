package com.example.demo.core.interceptor;

import com.example.demo.core.sender.CustomMobileSender;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.AopInvocationException;

import java.io.Serializable;
import java.lang.reflect.Method;

public class CustomMobileSenderInterceptor implements MethodInterceptor, Serializable {

    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {

        try {
            return mi.proceed();

        } catch (Throwable e) {

            if (e instanceof AopInvocationException) {

                CustomMobileSender sender = new CustomMobileSender();
                Method[] methods = sender.getClass().getDeclaredMethods();
                Method method = mi.getMethod();

                for (Method m : methods) {
                    if (m.getName().equals(method.getName())){
                        Object[] arguments = mi.getArguments();
                        m.setAccessible(true);
                        return m.invoke(sender, arguments);
                    }
                }
            }

            return null;
        }
    }
}
