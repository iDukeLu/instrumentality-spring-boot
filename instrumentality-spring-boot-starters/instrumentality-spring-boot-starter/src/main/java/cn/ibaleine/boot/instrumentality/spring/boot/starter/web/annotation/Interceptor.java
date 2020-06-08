package cn.ibaleine.boot.instrumentality.spring.boot.starter.web.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;

import java.lang.annotation.*;
import java.util.List;

/**
 * 拦截器注解。对实现了 {@link HandlerInterceptor} 接口的类（即，拦截器类）添加此注解后，会自动注册该拦截器类
 * 注意：目前暂不支持 {@link PathMatcher} 的配置
 *
 * @author luhongyu
 * @date 2020.06.08
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Component
public @interface Interceptor {

    String[] pathPatterns() default {"/**"};

    String[] excludePatterns() default {};

    int order() default 0;

    String profile() default "";
}
