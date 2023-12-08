# Spring Cloud

## 远程调用

### Feign

依赖项

```xml
<!-- openfeign 远程调用 -->
<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>
```



启动类开启 `Feign`

```java
@SpringBootApplication
@EnableDiscoveryClient // 服务发现
@EnableFeignClients // 核心代码：开启Feign远程调用
public class MainConsumer {
    public static void main(String[] args) {
        SpringApplication.run(MainConsumer.class, args);
    }
}
```



服务提供方  `nacos-provider/EchoController`

```java
@RestController
@RequestMapping("/echo") // 存在类级别的访问路径
public class EchoController {
    @Resource
    HttpServletRequest request;

    @GetMapping("/{data}")
    public String echo(@PathVariable String data) {
        // 获取端口号
        Integer port = request.getServerPort();

        return String.format("Provider：%s / port：%d", data, port);
    }
}
```



在 `nacos-consumer/feign/EchoFeign` 编写 `Feign`

```java
//@FeignClient(value = "服务名", path = "controller类路径")
@FeignClient(value = "service-provider", path = "/echo")
public interface EchoFeign {
	//复制controller的方法签名（除方法体外的所有内容）
    @GetMapping("/demo/{string}")
    public String echo(@PathVariable("string") String str);
}
```



在 `nacos-consumer/EchoController` 测试 `Feign`

```java
@RestController
@RequestMapping("/echo")
public class EchoController {
    @Resource
    private EchoFeign echoFeign;

    @GetMapping("/{data}")
    public String echo(@PathVariable String data) {
        System.out.println("调用了服务提供者拿到数据");
        return echoFeign.echo(data);
    }
}
```



**配置文件**

nacos-provider

```yml
server:
  port: 9001

spring:
  application:
    name: service-provider #服务名
  cloud:
    nacos:
      discovery:
        server-addr: 127.0.0.1:8848  #nacos服务地址

```



nacos-consumer

```yml
server:
  port: 9002

spring:
  application:
    name: service-consumer          
  cloud:
    nacos:
      discovery:
        server-addr: 127.0.0.1:8848  
        
```



## 跨域

```yml
spring:
  application:
    name: test-gateway
  cloud:
    nacos:
      discovery:
        server-addr: 127.0.0.1:8848   # nacos服务地址
    gateway:
      discovery:
        locator:
          enabled: true   # 开启服务注册和发现的功能，自动创建router以服务名开头的请求路径转发到对应的服务
      routes:
        - id: consumer # 自定义
          uri: lb://service-consumer # 访问路径
          predicates: # 断言
            - Path=/consumer/**
          filters: # 过滤器
            StripPrefix=1
      globalcors:
        cors-configurations:
          '[/**]':
            allowedOrigins: "*"   # 允许那些网站的跨域请求
            allowedMethods: "*"   # 允许的跨域ajax的请求方式
            allowedHeaders: "*"   # 允许在请求中携带的头信息
```

