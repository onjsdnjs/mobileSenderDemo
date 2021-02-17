package io.anymobi.sender.core.config.init;

import io.anymobi.sender.core.config.builder.MobileSenderBeanNameGenerator;
import io.anymobi.sender.core.factory.MobileSenderFactoryBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.data.config.ConfigurationUtils;

import java.util.Optional;

public class DefaultMobileSenderConfiguration {

    private final BeanDefinition definition;
    private final MobileSenderConfigurationSource configurationSource;
    private final MobileSenderBeanNameGenerator beanNameGenerator;

    public DefaultMobileSenderConfiguration(BeanDefinition definition, MobileSenderConfigurationSource configurationSource, MobileSenderBeanNameGenerator beanNameGenerator) {
        this.definition = definition;
        this.configurationSource = configurationSource;
        this.beanNameGenerator = beanNameGenerator;
    }

    public String getMobileSenderInterface() {
        return ConfigurationUtils.getRequiredBeanClassName(definition);
    }

    public String getMobileSenderFactoryBeanClassName() {
        return (String)configurationSource.getMobileSenderFactoryBeanClassName().orElseGet(()->MobileSenderFactoryBean.class.getName());
    }

    public Optional<String> getMobileSenderBaseClassName() {
        return configurationSource.getMobileSenderBaseClassName();
    }

    public String generateBeanName(AbstractBeanDefinition beanDefinition) {
        return beanNameGenerator.generateBeanName(beanDefinition);
    }

    public Object getSource() {
        return configurationSource.getSource();
    }
}