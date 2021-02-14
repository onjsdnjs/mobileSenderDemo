package com.example.demo.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(MobileSenderRegistrar.class)
public @interface EnableMobileSender {
}
