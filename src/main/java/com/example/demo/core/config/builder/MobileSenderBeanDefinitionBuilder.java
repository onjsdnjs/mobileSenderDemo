package com.example.demo.core.config.builder;

import com.example.demo.core.config.init.DefaultMobileSenderConfiguration;
import com.example.demo.core.config.init.MobileSenderConfigurationSource;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;

public class MobileSenderBeanDefinitionBuilder {

    private MobileSenderConfigurationSource mobileSenderConfigurationSource;

    public MobileSenderBeanDefinitionBuilder(MobileSenderConfigurationSource mobileSenderConfigurationSource) {
        this.mobileSenderConfigurationSource = mobileSenderConfigurationSource;
    }

    public BeanDefinitionBuilder build(DefaultMobileSenderConfiguration configuration) {

        BeanDefinitionBuilder builder = BeanDefinitionBuilder.rootBeanDefinition(configuration.getMobileSenderFactoryBeanClassName());

        builder.getRawBeanDefinition().setSource(configuration.getSource());
        builder.addConstructorArgValue(configuration.getMobileSenderInterface());

        configuration.getMobileSenderBaseClassName()//
                .ifPresent(it -> builder.addPropertyValue("mobileSenderBaseClass", it));

        return builder;
    }
}
