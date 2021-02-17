package io.anymobi.sender.core.config.builder;

import io.anymobi.sender.core.config.annotation.MobileSenderDefinition;
import io.anymobi.sender.core.config.annotation.NoMobileSenderBean;
import io.anymobi.sender.core.sender.Sender;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import java.io.IOException;

public class MobileSenderComponentProvider extends ClassPathScanningCandidateComponentProvider {

    private BeanDefinitionRegistry registry;

    public MobileSenderComponentProvider(Iterable<? extends TypeFilter> includeFilters, BeanDefinitionRegistry registry) {

        super(false);

        Assert.notNull(includeFilters, "Include filters must not be null!");
        Assert.notNull(registry, "BeanDefinitionRegistry must not be null!");

        this.registry = registry;

        if (includeFilters.iterator().hasNext()) {
            for (TypeFilter filter : includeFilters) {
                addIncludeFilter(filter);
            }
        } else {
            super.addIncludeFilter(new InterfaceTypeFilter(Sender.class));
            super.addIncludeFilter(new AnnotationTypeFilter(MobileSenderDefinition.class, true, true));
        }

        addExcludeFilter(new AnnotationTypeFilter(NoMobileSenderBean.class));
    }

    private static class InterfaceTypeFilter extends AssignableTypeFilter {

        public InterfaceTypeFilter(Class<?> targetType) {
            super(targetType);
        }

        @Override
        public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory)
                throws IOException {

            return metadataReader.getClassMetadata().isInterface() && super.match(metadataReader, metadataReaderFactory);
        }
    }

    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {

        boolean isNonMobileSenderInterface = !isGenericMobileSenderInterface(beanDefinition.getBeanClassName());
        boolean isTopLevelType = !beanDefinition.getMetadata().hasEnclosingClass();

        return isNonMobileSenderInterface && isTopLevelType;
    }

    public static boolean isGenericMobileSenderInterface(@Nullable String interfaceName) {
        return Sender.class.getName().equals(interfaceName);
    }
}
