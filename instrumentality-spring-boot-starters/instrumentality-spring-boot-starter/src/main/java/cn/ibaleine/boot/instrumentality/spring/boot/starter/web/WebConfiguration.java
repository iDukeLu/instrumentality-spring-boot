package cn.ibaleine.boot.instrumentality.spring.boot.starter.web;

import cn.ibaleine.boot.instrumentality.spring.boot.starter.web.annotation.EnableGlobalCors;
import cn.ibaleine.boot.instrumentality.spring.boot.starter.web.annotation.Interceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.Map;

/**
 * TODO: 编辑说明信息
 *
 * @author luhongyu
 * @date 2020.06.06
 */
@Slf4j
@Configuration
public class WebConfiguration implements WebMvcConfigurer, ApplicationContextAware, EnvironmentAware {

    private Environment environment;

    private ApplicationContext applicationContext;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        Map<String, Object> interceptorMap = applicationContext.getBeansWithAnnotation(Interceptor.class);
        for (Map.Entry<String, Object> entry : interceptorMap.entrySet()) {
            if (entry.getValue() instanceof HandlerInterceptor) {
                HandlerInterceptor interceptor = (HandlerInterceptor) entry.getValue();
                Interceptor annotation = interceptor.getClass().getAnnotation(Interceptor.class);
                String profile = annotation.profile();
                if (StringUtils.isEmpty(profile) || Arrays.asList(environment.getActiveProfiles()).contains(profile)) {
                    registry.addInterceptor(interceptor)
                        .addPathPatterns(annotation.pathPatterns())
                        .excludePathPatterns(annotation.excludePatterns())
                        .order(annotation.order());
                log.info("Add Interceptor: {}", entry.getKey());
                }
            }
        }
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        Map<String, Object> corsMap = applicationContext.getBeansWithAnnotation(EnableGlobalCors.class);
        for (Map.Entry<String, Object> entry : corsMap.entrySet()) {
            EnableGlobalCors annotation = entry.getValue().getClass().getAnnotation(EnableGlobalCors.class);
            for (EnableGlobalCors.Cors cors : annotation.corses()) {
                registry.addMapping(cors.mapping())
                        .allowedOrigins(cors.allowedOrigins())
                        .allowedMethods(cors.allowedMethods())
                        .allowedHeaders(cors.allowedHeaders())
                        .exposedHeaders(cors.exposedHeaders())
                        .allowCredentials(cors.allowCredentials())
                        .maxAge(cors.maxAge());
                log.info("Add CorsMapping: {mappping: {}, allowedOrigins: {}, " +
                        "allowedMethods: {}, allowedHeaders: {}, exposedHeaders: {}, " +
                        "allowCredentials: {}, maxAge: {}}", cors.mapping(),
                        cors.allowedOrigins(), cors.allowedMethods(), cors.allowedHeaders(),
                        cors.exposedHeaders(), cors.allowCredentials(), cors.maxAge());
            }
        }
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
