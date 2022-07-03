package com.sdust.stos.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Target - 描述当前的注解可以定义在什么资源上
 * @Retention - 当前注解什么时候有效
 */
@Target(value = {ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation4Swagger {
    //自定义注解中的属性
    String value() default "";
}
