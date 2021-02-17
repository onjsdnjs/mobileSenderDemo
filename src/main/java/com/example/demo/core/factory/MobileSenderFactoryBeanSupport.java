package com.example.demo.core.factory;

import com.example.demo.core.interceptor.CustomMobileSenderInterceptor;
import com.example.demo.core.sender.MobileSender;
import com.example.demo.core.sender.Sender;
import com.example.demo.core.sender.SimpleMobileSender;
import lombok.NoArgsConstructor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.interceptor.ExposeInvocationInterceptor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.data.projection.DefaultMethodInvokingMethodInterceptor;
import org.springframework.data.util.Lazy;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.interceptor.TransactionInterceptor;
import org.springframework.transaction.interceptor.TransactionalProxy;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@NoArgsConstructor
public class MobileSenderFactoryBeanSupport<E extends MobileSender<REQ,RES>,REQ,RES> implements FactoryBean<E>{

    private Class<? extends E> mobileSenderInterface;

    private Lazy<E> mobileSender;

    public MobileSenderFactoryBeanSupport(Class<? extends E> mobileSenderInterface) {
        this.mobileSenderInterface = mobileSenderInterface;
        this.mobileSender = Lazy.of(() -> getMobileSender(mobileSenderInterface));
        this.mobileSender.get();
    }

    @Override
    public E getObject() throws Exception {
        return this.mobileSender.get();
    }

    @Override
    public Class<?> getObjectType() {
        return mobileSenderInterface;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public <E> E getMobileSender(Class<E> mobileSenderInterface) {

        Object target = getTargetMobileSenderViaReflection(SimpleMobileSender.class);

        // 프록시 생성
        ProxyFactory result = new ProxyFactory();
        result.setTarget(target);
        result.setInterfaces(mobileSenderInterface, Sender.class, TransactionalProxy.class);

        result.addAdvice(new CustomMobileSenderInterceptor(result, mobileSenderInterface));
        result.addAdvisor(ExposeInvocationInterceptor.ADVISOR);
        result.addAdvice(new TransactionInterceptor());
        result.addAdvice(new DefaultMethodInvokingMethodInterceptor());

        E mobileSender = (E) result.getProxy(getClass().getClassLoader());

        return (E) mobileSender;
    }

    private <E> E getTargetMobileSenderViaReflection(Class<?> baseClass, Object... constructorArguments) {
        Optional<Constructor<?>> constructor = ReflectionUtils.findConstructor(baseClass, constructorArguments);

        return (E) constructor.map(it -> (E) BeanUtils.instantiateClass(it, constructorArguments))
                .orElseThrow(() -> new IllegalStateException(String.format(
                        "No suitable constructor found on %s to match the given arguments: %s. Make sure you implement a constructor taking these",
                        baseClass, Arrays.stream(constructorArguments).map(Object::getClass).collect(Collectors.toList()))));
    }
}
