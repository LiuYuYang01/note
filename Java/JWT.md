# JWT

`JWT`，全称是 `Json Web Token`， 是 `JSON` 风格轻量级的授权和身份认证规范，可实现无状态、分布式的 `Web` 应用授权；官网：https://jwt.io

* `Token` 需要加密，进行保护，采用 `RSA` 加密



接下来本文将介绍如何使用加密算法 `Rsa` 来生成 `Token`



## 起步

### 相关依赖

```xml
<!--Jwt核心依赖，用于生成&解析Token-->
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt</artifactId>
    <version>0.9.0</version>
</dependency>

<!--JavaBean工具类，用于JavaBean数据封装-->
<dependency>
    <groupId>commons-beanutils</groupId>
    <artifactId>commons-beanutils</artifactId>
</dependency>
```



### 相关配置

```yml
sc:
  jwt:
    secret: "liuyuyang" # 登录校验的密钥（自定义内容）
    pubKeyPath: C:\rsa\rsa.pub # 公钥地址
    priKeyPath: C:\rsa\rsa.pri # 私钥地址
    expire: 360 # 过期时间, 单位分钟
```



## Utils

### RasUtils

定义一个 `utils/RasUtils.java` 工具类

```java
package liuyuyang.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RsaUtils {

    /**
     * 从文件中读取公钥
     *
     * @param filename 公钥保存路径，相对于classpath
     * @return 公钥对象
     * @throws Exception
     */
    public static PublicKey getPublicKey(String filename) throws Exception {
        byte[] bytes = readFile(filename);
        return getPublicKey(bytes);
    }

    /**
     * 从文件中读取密钥
     *
     * @param filename 私钥保存路径，相对于classpath
     * @return 私钥对象
     * @throws Exception
     */
    public static PrivateKey getPrivateKey(String filename) throws Exception {
        byte[] bytes = readFile(filename);
        return getPrivateKey(bytes);
    }

    /**
     * 获取公钥
     *
     * @param bytes 公钥的字节形式
     * @return
     * @throws Exception
     */
    public static PublicKey getPublicKey(byte[] bytes) throws Exception {
        X509EncodedKeySpec spec = new X509EncodedKeySpec(bytes);
        KeyFactory factory = KeyFactory.getInstance("RSA");
        return factory.generatePublic(spec);
    }

    /**
     * 获取密钥
     *
     * @param bytes 私钥的字节形式
     * @return
     * @throws Exception
     */
    public static PrivateKey getPrivateKey(byte[] bytes) throws Exception {
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(bytes);
        KeyFactory factory = KeyFactory.getInstance("RSA");
        return factory.generatePrivate(spec);
    }

    /**
     * 根据密文，生存rsa公钥和私钥,并写入指定文件
     *
     * @param publicKeyFilename  公钥文件路径
     * @param privateKeyFilename 私钥文件路径
     * @param secret             生成密钥的密文
     * @throws Exception
     */
    public static void create(String publicKeyFilename, String privateKeyFilename, String secret) throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        SecureRandom secureRandom = new SecureRandom(secret.getBytes());
        keyPairGenerator.initialize(1024, secureRandom);
        KeyPair keyPair = keyPairGenerator.genKeyPair();
        // 获取公钥并写出
        byte[] publicKeyBytes = keyPair.getPublic().getEncoded();
        writeFile(publicKeyFilename, publicKeyBytes);
        // 获取私钥并写出
        byte[] privateKeyBytes = keyPair.getPrivate().getEncoded();
        writeFile(privateKeyFilename, privateKeyBytes);
    }

    private static byte[] readFile(String fileName) throws Exception {
        return Files.readAllBytes(new File(fileName).toPath());
    }

    private static void writeFile(String destPath, byte[] bytes) throws IOException {
        File dest = new File(destPath);

        //创建父文件夹
        if(!dest.getParentFile().exists()){
            dest.getParentFile().mkdirs();
        }
        //创建需要的文件
        if (!dest.exists()) {
            dest.createNewFile();
        }

        Files.write(dest.toPath(), bytes);
    }
}
```



测试上述工具类创建与获取 `Ras` 秘钥

```java
public class TestRSA {
    // 本地的秘钥路径
    private static final String pubKeyPath = "C:\\rsa\\rsa.pub";
    private static final String priKeyPath = "C:\\rsa\\rsa.pri";

    // 测试创建
    @Test
    public void create() throws Exception {
        // 创建公钥和私钥
        RsaUtils.generateKey(pubKeyPath, priKeyPath, "这里可以自定义一些加密内容");
    }

    // 测试获取
    @Test
    public void run() throws Exception {
        // 获取公钥
        PublicKey publicKey = RsaUtils.getPublicKey(pubKeyPath);
        System.out.println(publicKey);

        // 获取私钥
        PrivateKey privateKey = RsaUtils.getPrivateKey(priKeyPath);
        System.out.println(privateKey);
    }
}
```



### JwtConfig

定义一个 `utils/JwtConfig.java` 配置类用于从 `application.yml` 中获取过期时间、秘钥等数据

```java
package liuyuyang.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.security.PrivateKey;
import java.security.PublicKey;

@Data
@Component
@ConfigurationProperties(prefix = "sc.jwt")
public class JwtConfig {
    private String secret; // 秘钥
    private int expire; // 过期时间
    private String pubKeyPath; // 公钥
    private String priKeyPath; // 私钥

    private PublicKey publicKey; // 公钥
    private PrivateKey privateKey; // 私钥

    // 在类被加载时候，就会自动执行该方法
    @PostConstruct
    public void init() {
        try {
            // 获取秘钥
            File pubFile = new File(this.pubKeyPath);
            File priFile = new File(this.priKeyPath);

            // 如果公钥或私钥不存在，就自动生成一个
            if (!pubFile.exists() || priFile.exists()) {
                RsaUtils.create(this.pubKeyPath, this.priKeyPath, this.secret);
            }

            // 根据公钥和私钥的路径，生成对应的对象
            this.publicKey = RsaUtils.getPublicKey(this.pubKeyPath);
            this.privateKey = RsaUtils.getPrivateKey(this.priKeyPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```



### Jwt

定义一个 `utils/Jwt.java` 工具类用于生成与解析 `Token`

```java
package liuyuyang.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.beanutils.BeanUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

@Component
public class Jwt {
    @Resource
    private JwtConfig jwtConfig;

    // 创建 Token
    public String createToken(Object data) {
        try {
            // 获得jwt构建对象
            JwtBuilder jwtBuilder = Jwts.builder();

            // 设置数据
            if (data == null) throw new RuntimeException("数据不能为空");

            BeanInfo beanInfo = Introspector.getBeanInfo(data.getClass());

            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

            for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
                // 获得属性名
                String name = propertyDescriptor.getName();

                // 获得属性值
                Object value = propertyDescriptor.getReadMethod().invoke(data);

                if (value != null) {
                    jwtBuilder.claim(name, value);
                }
            }

            // 设置过期时间
            jwtBuilder.setExpiration(DateTime.now().plusMinutes(jwtConfig.getExpire()).toDate());

            // 设置加密方式
            jwtBuilder.signWith(SignatureAlgorithm.RS256, jwtConfig.getPrivateKey());

            // 生成Token
            return jwtBuilder.compact();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // 直接解析Token
    public Claims parserToken(String token) {
        Claims claims = null;

        try {
            claims = Jwts.parser().setSigningKey(jwtConfig.getPublicKey()).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return claims;
    }

    // 以JavaBean形式解析Token
    public <T> T resolverToken(String token, Class<T> beanClass) throws Exception {
        // 获得解析后内容
        Claims body = Jwts.parser().setSigningKey(jwtConfig.getPublicKey()).parseClaimsJws(token).getBody();

        // 将内容封装到对象JavaBean
        T bean = beanClass.newInstance();

        BeanInfo beanInfo = Introspector.getBeanInfo(beanClass);
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            // 获得属性名
            String name = propertyDescriptor.getName();

            // 通过属性名，获得对应解析的数据
            Object value = body.get(name);

            if (value != null) {
                // 将获得的数据封装到对应的JavaBean中
                BeanUtils.setProperty(bean, name, value);
            }
        }

        return bean;
    }
}
```



## 创建与解析

下面将演示创建与解析 `Token`

```java
public class TestJwt {
    private static final String pubKeyPath = "C:\\rsa\\rsa.pub";
    private static final String priKeyPath = "C:\\rsa\\rsa.pri";

    // 生成
    @Test
    public void createToken() throws Exception {
        // 定义需要携带在token中的数据
        User data = new User();
        data.setUserName("jack");
        data.setPassword("123123");

        // 核心代码：生成token
        String token = jwt.createToken(data);
    }

    // 解析
    @Test
    public void parseToken() throws Exception {
        String token = "xxxx.xxxx.xxxx";
        
        // 核心代码：直接解析Token
        Claims parser = parserToken(token);
        System.out.println(parser); 
		// {class=liuyuyang.domain.User, password=123123, username=jack, exp=1702558959} 
		System.out.println(parser.get("username")); // jack
        
        
        // 核心代码：以JavaBean解析Token
        User resolver = jwt.resolverToken(Token, User.class);
        // User(uid=null, username=admin, password=123123, gender=null, image=null)
    }
}
```



## 权限认证

就拿微服务中的过滤器举例，我们可以通过它来实现权限认证，也就是说只要在白名单 `allowPathList` 中定义的接口可以直接访问，比如登录、注册这种公开的接口



如果不在白名单中，那么就必须在前端请求时候给请求头携带 `Token`，并且为了验证 `Token` 是否有效，我们需要解析 `Token` ，解析成功就放行，失败就返回没有权限

```java
package liuyuyang.filter;

import liuyuyang.utils.Jwt;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import liuyuyang.domain.User;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
@EnableConfigurationProperties({FilterProperties.class})
public class LoginFilter implements GlobalFilter, Ordered {
    @Resource
    private Jwt jwt;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 获得请求的路径
        String path = exchange.getRequest().getURI().getPath();
        // /user-service/user/verify

        // 白名单列表
        String[] allowPathList = {"/user"};

        // 如果请求的路径在白名单中就放行，允许访问
        for (String allow : allowPathList) {
            if (path.contains(allow)) {
                return chain.filter(exchange);
            }
        }

        // 校验Token && 获取Token
        String Token = exchange.getRequest().getHeaders().getFirst("Authorization");

        try {
            // 解析Token
            User user = jwt.resolverToken(Token, User.class);

            // 如果解析成功就放行
            return chain.filter(exchange);
        } catch (Exception e) {
            System.out.println(e);
            
            // 处理失败结果
            ServerHttpResponse response = exchange.getResponse();

            // 设置状态码为401 表示没有权限
            response.setStatusCode(HttpStatus.UNAUTHORIZED);        // 401
            response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
            DataBuffer wrap = response.bufferFactory().wrap("没有权限".getBytes(StandardCharsets.UTF_8));

            // 将指定内容写入到网页
            return exchange.getResponse().writeWith(Flux.just(wrap));
        }
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
```

