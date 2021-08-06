package com.as.test.annotation;

import java.lang.annotation.*;

/**
 * desc:
 * author: as
 * date: 2019/7/29
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Animal {
    int lag() default 0;

    String name();
}
