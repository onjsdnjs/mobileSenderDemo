package com.example.demo.core.config.init;

import com.example.demo.core.config.annotation.EnableMobileSender;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;

@Component
public class MobileSenderRegistrar implements ImportBeanDefinitionRegistrar, ResourceLoaderAware, EnvironmentAware {

    private ResourceLoader resourceLoader;
    private Environment environment;

    public static Class<? extends Annotation> getAnnotation() {
        return EnableMobileSender.class;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry, BeanNameGenerator importBeanNameGenerator) {
        MobileSenderConfigurationSource mobileSenderConfigurationSource = new MobileSenderConfigurationSource(registry, importingClassMetadata, MobileSenderRegistrar.getAnnotation(), importBeanNameGenerator, resourceLoader);
        MobileSenderConfigurationDelegate mobileSenderConfigurationDelegate = new MobileSenderConfigurationDelegate(registry, mobileSenderConfigurationSource);
        mobileSenderConfigurationDelegate.getMobileSenderConfigurations(resourceLoader);
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
    }
}

