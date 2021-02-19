package io.anymobi.sender.core.config.annotation;

import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

@Indexed
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Inherited
public @interface MobileSenderDefinition {
}
