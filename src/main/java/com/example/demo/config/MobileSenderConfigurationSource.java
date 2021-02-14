package com.example.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.autoconfigure.AutoConfigurationPackages;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.AnnotationRepositoryMetadata;
import org.springframework.data.repository.core.support.DefaultRepositoryMetadata;
import org.springframework.data.util.Streamable;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Slf4j
public class MobileSenderConfigurationSource {

    private BeanFactory registry;

    public MobileSenderConfigurationSource(BeanFactory registry) {
        this.registry = registry;
    }

    public Collection<DefaultMobileSenderConfiguration> getMobileSenderConfigurations(ResourceLoader loader) {

        Assert.notNull(loader, "Loader must not be null!");

        Set<DefaultMobileSenderConfiguration> result = new HashSet<>();

        for (BeanDefinition candidate : getCandidates(loader)) {

            DefaultMobileSenderConfiguration configuration = getMobileSenderConfiguration(candidate);
            Class<?> mobileSenderInterface = loadMobileSenderInterface(configuration, getConfigurationInspectionClassLoader(loader));

            if (mobileSenderInterface == null) {
                result.add(configuration);
                continue;
            }

//            RepositoryMetadata metadata = getMetadata(mobileSenderInterface);
            result.add(configuration);
        }

        return result;
    }

    public static RepositoryMetadata getMetadata(Class<?> mobileSenderInterface) {

        Assert.notNull(mobileSenderInterface, "MobileSender interface must not be null!");

        return MobileSender.class.isAssignableFrom(mobileSenderInterface) ? new DefaultRepositoryMetadata(mobileSenderInterface)
                : new AnnotationRepositoryMetadata(mobileSenderInterface);
    }

    protected DefaultMobileSenderConfiguration getMobileSenderConfiguration(BeanDefinition definition) {
        return new DefaultMobileSenderConfiguration(definition);
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
        return Streamable.of(AutoConfigurationPackages.get(this.registry));
    }

}
