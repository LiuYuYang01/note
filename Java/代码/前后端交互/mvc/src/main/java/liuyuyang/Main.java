package liuyuyang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        System.out.println("服务启动成功：http://127.0.0.1:9999/");
        System.out.println("API文档：http://localhost:9999/swagger-ui/index.html");
        SpringApplication.run(Main.class, args);
    }
}