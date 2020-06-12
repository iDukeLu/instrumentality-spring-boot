# instrumentality-spring-boot-starter
## 简介

## 快速开始
### 定义拦截器
> 注册拦截器，仅需以下两个步骤即可
> - 实现 `HandlerInterceptor` 接口
> - 使用 `@Interceptor` 注解
    
```
@Interceptor
public class TestInterceptor implements HandlerInterceptor {

}
```

### 添加跨域处理
> 
```
@EnableGlobalCors
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
```