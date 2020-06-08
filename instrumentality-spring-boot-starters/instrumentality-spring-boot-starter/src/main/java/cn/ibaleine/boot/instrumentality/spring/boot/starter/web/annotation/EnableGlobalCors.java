package cn.ibaleine.boot.instrumentality.spring.boot.starter.web.annotation;

import org.springframework.beans.factory.annotation.Configurable;

import java.lang.annotation.*;

/**
 * TODO: 编辑说明信息
 *
 * @author luhongyu
 * @date 2020.06.08
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Configurable
public @interface EnableGlobalCors {
    Cors[] corses() default {@Cors()};

    /**
     * TODO: 编辑说明信息
     *
     * @author luhongyu
     * @date 2020.06.08
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target({})
    @interface Cors {
        String mapping() default "*";
        String allowedOrigins() default "*";
        String allowedMethods() default "*";
        String allowedHeaders() default "*";
        String exposedHeaders() default "*";
        boolean allowCredentials() default true;
        int maxAge() default 1800;
    }
}
