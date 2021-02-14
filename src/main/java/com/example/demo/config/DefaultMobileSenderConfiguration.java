package com.example.demo.config;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.data.config.ConfigurationUtils;

public class DefaultMobileSenderConfiguration {

    private final BeanDefinition definition;

    public DefaultMobileSenderConfiguration(BeanDefinition definition) {
        this.definition = definition;
    }

    public String getMobileSenderInterface() {
        return ConfigurationUtils.getRequiredBeanClassName(definition);
    }
}