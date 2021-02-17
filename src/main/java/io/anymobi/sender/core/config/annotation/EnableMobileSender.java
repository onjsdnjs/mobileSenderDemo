package io.anymobi.sender.core.config.annotation;

import io.anymobi.sender.core.config.builder.DefaultMobileSenderBaseClass;
import io.anymobi.sender.core.config.init.MobileSenderRegistrar;
import io.anymobi.sender.core.factory.MobileSenderFactoryBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(MobileSenderRegistrar.class)
@Configuration
public @interface EnableMobileSender {

    String[] value() default {};

    String[] basePackages() default {};

    Class<?>[] basePackageClasses() default {};

    ComponentScan.Filter[] includeFilters() default {};

    ComponentScan.Filter[] excludeFilters() default {};

    Class<?> mobileSenderFactoryBeanClass() default MobileSenderFactoryBean.class;

    Class<?> mobileSenderBaseClass() default DefaultMobileSenderBaseClass.class;

    String transactionManagerRef() default "transactionManager";

    boolean enableDefaultTransactions() default true;

    char escapeCharacter() default '\\';
}
