package com.example.demo.core.config.annotation;

import com.example.demo.core.config.build.DefaultMobileSenderBaseClass;
import com.example.demo.core.config.init.MobileSenderRegistrar;
import com.example.demo.core.factory.MobileSenderFactoryBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(MobileSenderRegistrar.class)
public @interface EnableMobileSender {

    String[] value() default {};

    String[] basePackages() default {};

    Class<?>[] basePackageClasses() default {};

    ComponentScan.Filter[] includeFilters() default {};

    ComponentScan.Filter[] excludeFilters() default {};

    Class<?> mobileSenderFactoryBeanClass() default MobileSenderFactoryBean.class;

    Class<?> mobileSenderBaseClass() default DefaultMobileSenderBaseClass.class;

    String entityManagerFactoryRef() default "entityManagerFactory";

    String transactionManagerRef() default "transactionManager";

    boolean enableDefaultTransactions() default true;

    char escapeCharacter() default '\\';
}
