package com.ryb.core.log;

import java.lang.annotation.*;

/**
 * @author 常坤
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {
    String operationType() default "";

    String operationName() default "";
}
