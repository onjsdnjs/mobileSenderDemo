package io.anymobi.sender.core.config.builder;

import io.anymobi.sender.core.config.init.DefaultMobileSenderConfiguration;
import io.anymobi.sender.core.config.init.MobileSenderConfigurationSource;
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
