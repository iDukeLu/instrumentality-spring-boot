package cn.ibaleine.boot.instrumentality.spring.boot.starter.web.annotation;

import org.springframework.beans.factory.annotation.Configurable;

import java.lang.annotation.*;

/**
 * 全局跨域注解
 *
 * @author luhongyu
 * @date 2020.06.08
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface EnableGlobalCors {

    /**
     * 跨域配置集合，默认配置全局跨域
     */
    Cors[] corses() default {@Cors()};

    /**
     * 跨域配置，默认全局跨域
     *
     * @author luhongyu
     * @date 2020.06.08
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target({})
    @interface Cors {
        /**
         * 允许跨域路径，默认 *
         */
        String mapping() default "*";

        /**
         * 允许跨域的远程路径，默认 *
         */
        String allowedOrigins() default "*";

        /**
         * 允许跨域的请求方式，默认 *
         */
        String allowedMethods() default "*";

        /**
         * 允许跨域的请求头，默认 *
         */
        String allowedHeaders() default "*";

        /**
         * 允许跨域的响应头，默认 *
         */
        String exposedHeaders() default "*";

        /**
         * 浏览器是否应发送凭据，默认 true
         */
        boolean allowCredentials() default true;

        /**
         * 预请求缓存时间，默认 1800 s
         */
        int maxAge() default 1800;
    }
}
