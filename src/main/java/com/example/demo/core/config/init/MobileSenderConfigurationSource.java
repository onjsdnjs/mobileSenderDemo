package com.example.demo.core.config.init;

import com.example.demo.core.config.builder.DefaultMobileSenderBaseClass;
import com.example.demo.core.config.builder.MobileSenderBeanNameGenerator;
import com.example.demo.core.config.builder.MobileSenderComponentProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.boot.autoconfigure.AutoConfigurationPackages;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.data.util.Streamable;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import java.lang.annotation.Annotation;
import java.util.*;

@Slf4j
public class MobileSenderConfigurationSource {

    private static final String MOBILE_SENDER_FACTORY_BEAN_CLASS = "mobileSenderFactoryBeanClass";
    private static final String MOBILE_SENDER_BASE_CLASS = "mobileSenderBaseClass";
    private final AnnotationMetadata metadata;
    private BeanDefinitionRegistry registry;
    private final AnnotationAttributes attributes;
    private BeanNameGenerator importBeanNameGenerator;
    private ResourceLoader resourceLoader;

    public MobileSenderConfigurationSource(BeanDefinitionRegistry registry, AnnotationMetadata metadata, Class<? extends Annotation> annotation,
                                           BeanNameGenerator importBeanNameGenerator, ResourceLoader resourceLoader) {
        Map<String, Object> annotationAttributes = metadata.getAnnotationAttributes(annotation.getName());
        this.metadata = metadata;
        this.resourceLoader = resourceLoader;
        this.importBeanNameGenerator = importBeanNameGenerator;
        this.registry = registry;
        this.attributes = new AnnotationAttributes(annotationAttributes);
    }

    public Collection<DefaultMobileSenderConfiguration> getMobileSenderConfigurations(ResourceLoader loader, MobileSenderConfigurationSource mobileSenderConfigurationSource) {

        Assert.notNull(loader, "Loader must not be null!");

        Set<DefaultMobileSenderConfiguration> result = new HashSet<>();

        for (BeanDefinition candidate : getCandidates(loader)) {

            DefaultMobileSenderConfiguration configuration = getMobileSenderConfiguration(candidate, mobileSenderConfigurationSource);
            Class<?> mobileSenderInterface = loadMobileSenderInterface(configuration, getConfigurationInspectionClassLoader(loader));

            if (mobileSenderInterface == null) {
                result.add(configuration);
                continue;
            }

            result.add(configuration);
        }

        return result;
    }

    protected DefaultMobileSenderConfiguration getMobileSenderConfiguration(BeanDefinition definition, MobileSenderConfigurationSource configSource) {
        return new DefaultMobileSenderConfiguration(definition, configSource,
                new MobileSenderBeanNameGenerator(resourceLoader.getClassLoader(),importBeanNameGenerator, registry));
    }

    private Class<?> loadMobileSenderInterface(DefaultMobileSenderConfiguration configuration, @Nullable ClassLoader classLoader) {

        String mobileSenderInterface = configuration.getMobileSenderInterface();

        try {
            return org.springframework.util.ClassUtils.forName(mobileSenderInterface, classLoader);
        } catch (ClassNotFoundException | LinkageError e) {
        }

        return null;
    }

    @Nullable
    protected ClassLoader getConfigurationInspectionClassLoader(ResourceLoader loader) {
        return loader.getClassLoader();
    }

    protected Iterable<TypeFilter> getIncludeFilters() {
        return Collections.emptySet();
    }

    public Streamable<TypeFilter> getExcludeFilters() {
        return Streamable.empty();
    }

    public Streamable<BeanDefinition> getCandidates(ResourceLoader loader) {

        MobileSenderComponentProvider scanner = new MobileSenderComponentProvider(getIncludeFilters(), (BeanDefinitionRegistry)registry);
        scanner.setResourceLoader(loader);

        getExcludeFilters().forEach(it -> scanner.addExcludeFilter(it));

        return Streamable.of(() -> getBasePackages().stream()//
                .flatMap(it -> scanner.findCandidateComponents(it).stream()));
    }

    protected Streamable<String> getBasePackages() {
        return Streamable.of(AutoConfigurationPackages.get((BeanFactory) this.registry));
    }

    public Optional<String> getMobileSenderBaseClassName() {

        if (!attributes.containsKey(MOBILE_SENDER_BASE_CLASS)) {
            return Optional.empty();
        }

        Class<? extends Object> mobileSenderBaseClass = attributes.getClass(MOBILE_SENDER_BASE_CLASS);
        return DefaultMobileSenderBaseClass.class.equals(mobileSenderBaseClass) ? Optional.empty()
                : Optional.of(mobileSenderBaseClass.getName());
    }


    public Optional<Object> getMobileSenderFactoryBeanClassName() {
        return Optional.of(attributes.getClass(MOBILE_SENDER_FACTORY_BEAN_CLASS).getName());
    }

    public Object getSource() {
        return metadata;
    }
}
