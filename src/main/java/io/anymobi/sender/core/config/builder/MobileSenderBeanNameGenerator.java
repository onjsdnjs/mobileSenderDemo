package io.anymobi.sender.core.config.builder;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.annotation.AnnotatedGenericBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.util.ClassUtils;

public class MobileSenderBeanNameGenerator {

    private final ClassLoader beanClassLoader;
    private final BeanNameGenerator generator;
    private final BeanDefinitionRegistry registry;

    public MobileSenderBeanNameGenerator(ClassLoader beanClassLoader, BeanNameGenerator generator,
                                       BeanDefinitionRegistry registry) {

        this.beanClassLoader = beanClassLoader;
        this.generator = generator;
        this.registry = registry;
    }

    public String generateBeanName(BeanDefinition definition) {

        AnnotatedBeanDefinition beanDefinition = definition instanceof AnnotatedBeanDefinition //
                ? (AnnotatedBeanDefinition) definition //
                : new AnnotatedGenericBeanDefinition(getMobileSenderInterfaceFrom(definition));

        return generator.generateBeanName(beanDefinition, registry);
    }

    private Class<?> getMobileSenderInterfaceFrom(BeanDefinition beanDefinition) {

        ConstructorArgumentValues.ValueHolder argumentValue = beanDefinition.getConstructorArgumentValues().getArgumentValue(0, Class.class);

        if (argumentValue == null) {
            throw new IllegalStateException(
                    String.format("Failed to obtain first constructor parameter value of BeanDefinition %s!", beanDefinition));
        }

        Object value = argumentValue.getValue();

        if (value == null) {

            throw new IllegalStateException(
                    String.format("Value of first constructor parameter value of BeanDefinition %s is null!", beanDefinition));

        } else if (value instanceof Class<?>) {

            return (Class<?>) value;

        } else {

            try {
                return ClassUtils.forName(value.toString(), beanClassLoader);
            } catch (Exception o_O) {
                throw new RuntimeException(o_O);
            }
        }
    }
}
