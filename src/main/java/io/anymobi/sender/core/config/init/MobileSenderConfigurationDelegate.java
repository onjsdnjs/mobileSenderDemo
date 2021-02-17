package io.anymobi.sender.core.config.init;

import io.anymobi.sender.core.config.builder.MobileSenderBeanDefinitionBuilder;
import org.springframework.beans.factory.parsing.BeanComponentDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.StopWatch;

import java.util.*;

public class MobileSenderConfigurationDelegate {

    static final String FACTORY_BEAN_OBJECT_TYPE = "factoryBeanObjectType";

    private BeanDefinitionRegistry registry;
    private MobileSenderConfigurationSource mobileSenderConfigurationSource;

    public MobileSenderConfigurationDelegate(BeanDefinitionRegistry registry, MobileSenderConfigurationSource mobileSenderConfigurationSource) {
        this.registry = registry;
        this.mobileSenderConfigurationSource = mobileSenderConfigurationSource;
    }

    public List<BeanComponentDefinition> getMobileSenderConfigurations(ResourceLoader resourceLoader) {

        MobileSenderBeanDefinitionBuilder builder = new MobileSenderBeanDefinitionBuilder(mobileSenderConfigurationSource);
        List<BeanComponentDefinition> definitions = new ArrayList<>();

        StopWatch watch = new StopWatch();

        watch.start();

        Collection<DefaultMobileSenderConfiguration> configurations =
                mobileSenderConfigurationSource.getMobileSenderConfigurations(resourceLoader, mobileSenderConfigurationSource);

        Map<String, DefaultMobileSenderConfiguration> configurationsByMobileSenderName = new HashMap<>(configurations.size());

        for (DefaultMobileSenderConfiguration configuration : configurations) {

            configurationsByMobileSenderName.put(configuration.getMobileSenderInterface(), configuration);

            BeanDefinitionBuilder definitionBuilder = builder.build(configuration);

            AbstractBeanDefinition beanDefinition = definitionBuilder.getBeanDefinition();

            String beanName = configuration.generateBeanName(beanDefinition);

            beanDefinition.setAttribute(FACTORY_BEAN_OBJECT_TYPE, configuration.getMobileSenderInterface());

            registry.registerBeanDefinition(beanName, beanDefinition);
            definitions.add(new BeanComponentDefinition(beanDefinition, beanName));
        }

        watch.stop();

        return definitions;
    }
}
