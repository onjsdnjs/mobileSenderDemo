package com.example.demo.core.interceptor;

import com.example.demo.core.exception.NotFoundCustomMethodException;
import com.example.demo.core.sender.MobileSender;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.AopInvocationException;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.objenesis.instantiator.util.ClassUtils;

import java.io.Serializable;
import java.lang.reflect.Method;

public class CustomDynamicServiceInterceptor implements MethodInterceptor, Serializable {

    private ProxyFactory advised;
    private Class<?> mobileSenderInterface;
    private String suffix = "Impl";

    public <E> CustomDynamicServiceInterceptor(ProxyFactory advised, Class<?> mobileSenderInterface) {

        this.advised = advised;
        this.mobileSenderInterface = mobileSenderInterface;
    }

    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {

        try {
            return mi.proceed();

        } catch (Throwable e) {

            if (e instanceof AopInvocationException) {

                Class<?>[] interfaces = mobileSenderInterface.getInterfaces();

                for (Class clz : interfaces) {

                    if (!clz.isAssignableFrom(MobileSender.class)) {

                        String fullName = clz.getName() + suffix;
                        Class<?> clazz = Class.forName(fullName);
                        Object instance = ClassUtils.newInstance(clazz);
                        Method[] methods = instance.getClass().getDeclaredMethods();
                        Method method = mi.getMethod();

                        for (Method m : methods) {
                            if (m.getName().equals(method.getName())) {
                                Object[] arguments = mi.getArguments();
                                m.setAccessible(true);
                                return m.invoke(instance, arguments);
                            }
                        }
                    }
                }
            }

            throw new NotFoundCustomMethodException(e);
        }
    }
}
