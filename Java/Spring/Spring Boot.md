# Sprint Boot

## 快速入门

### 安装

```xml
<!-- pom.xml-->

<!-- 确定版本-->
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.2.5.RELEASE</version>
    <relativePath/>
</parent>

<!-- 引入相关依赖-->
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
</dependencies>
```



### 启动

```java
// Main.java

package yang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class);
    }
}
```



### 响应

```java
// hello.java

package yang;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class hello {
    @GetMapping
    public String home() {
        return "Hello Spring Boot!";
    }
}
```

我们可以通过：http://localhost:8080/ 来访问应用程序



默认端口号为：`8080`，可以在 `resources/application.yml` 中修改

```yml
server:
  port: 9999
```



## 请求

### 请求方式

在 `Spring Boot` 中，如果希望使用特定的请求方式，可以使用如下的注解：

- `@GetMapping`：处理 **GET** 请求。
- `@PostMapping`：处理 **POST** 请求。
- `@PatchMapping`：处理 **PATCH** 请求。
- `@DeleteMapping`：处理 **DELETE** 请求。

```java
@RestController
public class HomeController {
    @GetMapping("/home")
    public String home() {
        // 处理 GET 请求
    }

    @PostMapping("/home")
    public String home() {
        // 处理 POST 请求
    }

    @PatchMapping("/home")
    public String home() {
        // 处理 PATCH 请求
    }

    @DeleteMapping("/home")
    public String home() {
        // 处理 DELETE 请求
    }
}
```

这样，每个方法只会处理与注解对应的请求方法



如果给类或类方法使用 `@RequestMapping` 注解，表示支持所有的 `HTTP` 请求方法，如 `GET`、`POST`、`PATCH`、`DELETE` 等

给类设置，表示该路径的前缀。

给方法设置，表示该方法支持所有的请求方式

```java
@RestController
@RequestMapping("/user")
public class UserController {
    @RequestMapping
    public String user() {
        // 不管什么请求方式都可以触发该方法
    }
}
```



`@RequestMapping` 注解也可以通过 `method` 来指定请求方式，如下：

```java
@RequestMapping(value="/user", method=RequestMethod.GET)
// 不过这样的话就没有什么意义了，因为它的简写是
@GetMapping("/user")
// 他们两种方式的结果是等价的
```



### 传参方式

默认为 `application/x-www-form-urlencode` 方式接收参数

```java
// @PostMapping(consumes = "application/x-www-form-urlencode")
@PostMapping
public void hello(User user) {
    System.out.println(user);
}
```



设置为 `application/json` 方式接收参数

```java
@PostMapping(consumes = "application/json")
public void hello(@RequestBody User user) {
    System.out.println(user);
}
```

`consumes` 的值可以是任何格式，根据需求而定



如果不确定类型，我们可以设置为 `Object`，这样就可以传递任意的 `JSON`键值对了

```java
@PostMapping
public String hello(@RequestBody Object data) {
    System.out.println(data);
    // {password=1234, username=jack}

    // 将数据转换为Map就可以获取属性值并使用了
    Map<String, String> result = (Map<String, String>) data;
    System.out.println(result.get("username")); // jack
    System.out.println(result.get("password")); // 1234

    // ...

    return "<h1>Hello World!</h1>";
}
```



### 参数处理

#### 简单参数

**接收参数**

**URL：** http://localhost:9999/?uname=zs

```java
@GetMapping
public void home(String uname) {
    System.out.println(uname); // zs
}
```



**参数别名**

**URL：** http://localhost:9999/?uname=zs

```java
@GetMapping
public void home(@RequestParam("uname") String data) {
    System.out.println(data); // zs
}
```



**参数默认值**

如果不传参数 `uname` 值则采取默认值

```java
@GetMapping
public void home(@RequestParam(name = "uname", defaultValue = "我是默认值") String data) {
    // URL：http://localhost:9999/?uname=zs
    System.out.println(data); // zs

    // URL：http://localhost:9999/
    System.out.println(data); // 我是默认值
}
```



**参数是否必填**

默认 `required = true` 表示该参数必填，否则程序就会出异常

如果设置为 `false` 则表示该参数可填也可不填，参数的值为 `null` 

**URL：** http://localhost:9999/

```java
@GetMapping
public void home(@RequestParam(required = false) String uname) {
    System.out.println(uname); // null
}
```

**细节：** `required = false` 等价于 `defaultValue = ""`



#### 实体参数

**简单传参**

当传递的参数过多时会导致非常繁琐，所以可以使用实体参数来解决这个问题

**URL：** http://localhost:9999/?id=2&name=zs&age=20

```java
@GetMapping
// 参数类型设置为类对象
public void home(Info info) {
    System.out.println(info);
    // Info(id=2, name=zs, age=20)
}
```

```java
package liuyuyang.domain;

import lombok.Data;

@Data
public class Info {
    private Integer id;
    private String name;
    private Integer age;
}
```

**注意：** `Info` 类的每个属性必须设置 `set` 方法，否则会导致接收过来的 `url` 参数无法进行赋值，这里我们使用 `lombok` 插件来实现



**复杂传参**

**URL：** http://localhost:9999/?id=2&name=zs&age=20&addRess.aid=1&addRess.city=zhengzhou

```java
@GetMapping
public void home(Info info) {
    System.out.println(info);
    // Info(id=2, name=zs, age=20, addRess=AddRess(aid=1, city=zhengzhou))
}
```

```java
package liuyuyang.domain;

import lombok.Data;

@Data
public class Info {
    private Integer id;
    private String name;
    private Integer age;
    private AddRess addRess;
}
```

```java
package liuyuyang.domain;

import lombok.Data;

@Data
public class AddRess {
    private Integer aid;
    private String city;
}
```



#### JSON参数

```json
{
    "name": "宇阳",
    "age": 20
}
```

```java
@PostMapping
public void home(@RequestBody User user) {
    System.out.println(user);
}
```



#### 多个参数

**URL：** `http://localhost:9999/?hobby=写代码&hobby=敲代码`



**数组**

```java
@GetMapping
public void home(String[] hobby) {
    System.out.println(Arrays.toString(hobby));
    // [写代码, 敲代码]
}
```

**集合**

```java
@GetMapping
public void home(@RequestParam List<String> hobby) {
    System.out.println(hobby);
    // [写代码, 敲代码]
}
```



#### 日期参数

**URL：** http://localhost:9999/?date=2023-11-12

```java
@GetMapping
public void home(@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
    System.out.println(date);
    // 2023-11-12
}
```

如果传递的不是时间格式的数据，就会抛出异常



#### 文件参数

通过 `form-data` 上传，参数必须跟方法中的参数一致

```java
@PostMapping("/file")
public void home(MultipartFile file) throws IOException {
    // 获取文件对象
    System.out.println(file);
    // 获取文件名
    System.out.println(file.getOriginalFilename());
    // 获取文件大小
    System.out.println(file.getSize());
    // 获取字节文件流
    System.out.println(file.getInputStream());
    // 获取文件内字节数组
    System.out.println(file.getBytes());
}
```



#### 动态参数

**URL：** http://localhost:9999/10

```java
@GetMapping("/{uid}")
public void home(@PathVariable("uid") String id) {
    System.out.println(id); // 10
}
```



### 参数校验



### 二级路由

```java
RestController
@RequestMapping("/user")
public class HelloController {
    // 查询数据
    @GetMapping("/{uid}")
    public User home(@PathVariable("uid") Integer uid) {
        return null;
    }

    // 查询全部数据
    @GetMapping
    public List<User> hello() {
        return null;
    }
}
```

```
http://localhost:9999/user/2
http://localhost:9999/user
```



## 响应

### 状态码

返回一个指定状态码的响应

```java
@GetMapping("/")
public ResponseEntity<String> hello() {
    // 返回一个200状态码的响应
    // return ResponseEntity.ok("Hello World!");
    // return ResponseEntity.status(HttpStatus.OK).body("Hello World!");
    
    // 返回一个404状态码的响应
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("404：没有找到该页面");
}
```



## RESTFul

`REST` 是一种软件架构风格，规定的规范。

**特点：**

* 请求与响应的数据类型都是JSON

* 请求方式 + 请求路径，标识一个功能。从而使请求路径没有动词。

  ~~~
  POST http://localhost:8080/user					//添加
  DELETE http://localhost:8080/user/2			    //通过id删除
  PATCH http://localhost:8080/user				//修改
  
  GET http://localhost:8080/user					//查询所有
  GET http://localhost:8080/user/2				//通过id查询详情
  ~~~

**示例：**

```java
@RestController
@RequestMapping("/user")
public class UserController {
    @PostMapping
    public ResponseEntity<String> add(@RequestBody User user) {
        return ResponseEntity.ok("添加成功");
    }
    
    @DeleteMapping("/{uid}")
    public ResponseEntity<String> del(@PathVariable("uid") String uid) {
        return ResponseEntity.ok("删除成功");
    }
    
    @PatchMapping
    public ResponseEntity<String> update(@RequestBody User user) {
        return ResponseEntity.ok("修改成功");
    }
    
    @GetMapping("/{uid}")
    public ResponseEntity<String> get(@PathVariable("uid") String uid) {
        return ResponseEntity.ok("查询数据成功");
    }

    @GetMapping
    public ResponseEntity<String> list() {
        return ResponseEntity.ok("查询全部数据成功");
    }
}
```



## 分层解耦

### 三层架构

在我们进行程序设计以及程序开发时，尽可能让每一个接口、类、方法的职责更单一些（单一职责原则）。

> 单一职责原则：一个类或一个方法，就只做一件事情，只管一块功能。
>
> 这样就可以让类、接口、方法的复杂度更低，可读性更强，扩展性更好，也更利用后期的维护。



我们之前开发的程序呢，并不满足单一职责原则。下面我们来分析下之前的程序：

![image-20221204191650390](../image/image-20221204191650390.png) 



那其实我们上述案例的处理逻辑呢，从组成上看可以分为三个部分：

- 数据访问：负责业务数据的维护操作，包括增、删、改、查等操作。
- 逻辑处理：负责业务逻辑处理的代码。
- 请求处理、响应数据：负责，接收页面的请求，给页面响应数据。

按照上述的三个组成部分，在我们项目开发中呢，可以将代码分为三层：

![image-20221204193837678](../image/image-20221204193837678.png)

- Controller：控制层。接收前端发送的请求，对请求进行处理，并响应数据。
- Service：业务逻辑层。处理具体的业务逻辑。
- Dao：数据访问层(Data Access Object)，也称为持久层。负责数据访问操作，包括数据的增、删、改、查。



基于三层架构的程序执行流程：

![image-20221204194207812](../image/image-20221204194207812.png)

- 前端发起的请求，由Controller层接收（Controller响应数据给前端）
- Controller层调用Service层来进行逻辑处理（Service层处理完后，把处理结果返回给Controller层）
- Serivce层调用Dao层（逻辑处理过程中需要用到的一些数据要从Dao层获取）
- Dao层操作文件中的数据（Dao拿到的数据会返回给Service层）

> 思考：按照三层架构的思想，如何要对业务逻辑(Service层)进行变更，会影响到Controller层和Dao层吗？ 
>
> 答案：不会影响。 （程序的扩展性、维护性变得更好了）





### 代码拆分

我们使用三层架构思想，来改造下之前的程序：

- 控制层包名：xxxx.controller
- 业务逻辑层包名：xxxx.service
- 数据访问层包名：xxxx.dao

![image-20221204195812200](../image/image-20221204195812200.png)

**控制层：**接收前端发送的请求，对请求进行处理，并响应数据

```java
@RestController
public class EmpController {
    //业务层对象
    private EmpService empService = new EmpServiceA();

    @RequestMapping("/listEmp")
    public Result list(){
        //1. 调用service层, 获取数据
        List<Emp> empList = empService.listEmp();

        //3. 响应数据
        return Result.success(empList);
    }
}
```

**业务逻辑层：**处理具体的业务逻辑

- 业务接口

~~~java
//业务逻辑接口（制定业务标准）
public interface EmpService {
    //获取员工列表
    public List<Emp> listEmp();
}
~~~

- 业务实现类

```java
//业务逻辑实现类（按照业务标准实现）
public class EmpServiceA implements EmpService {
    //dao层对象
    private EmpDao empDao = new EmpDaoA();

    @Override
    public List<Emp> listEmp() {
        //1. 调用dao, 获取数据
        List<Emp> empList = empDao.listEmp();

        //2. 对数据进行转换处理 - gender, job
        empList.stream().forEach(emp -> {
            //处理 gender 1: 男, 2: 女
            String gender = emp.getGender();
            if("1".equals(gender)){
                emp.setGender("男");
            }else if("2".equals(gender)){
                emp.setGender("女");
            }

            //处理job - 1: 讲师, 2: 班主任 , 3: 就业指导
            String job = emp.getJob();
            if("1".equals(job)){
                emp.setJob("讲师");
            }else if("2".equals(job)){
                emp.setJob("班主任");
            }else if("3".equals(job)){
                emp.setJob("就业指导");
            }
        });
        return empList;
    }
}
```

**数据访问层：**负责数据的访问操作，包含数据的增、删、改、查

- 数据访问接口

~~~java
//数据访问层接口（制定标准）
public interface EmpDao {
    //获取员工列表数据
    public List<Emp> listEmp();
}
~~~

- 数据访问实现类

```java
//数据访问实现类
public class EmpDaoA implements EmpDao {
    @Override
    public List<Emp> listEmp() {
        //1. 加载并解析emp.xml
        String file = this.getClass().getClassLoader().getResource("emp.xml").getFile();
        System.out.println(file);
        List<Emp> empList = XmlParserUtils.parse(file, Emp.class);
        return empList;
    }
}
```

![image-20221204201342490](../image/image-20221204201342490.png)

三层架构的好处：

1. 复用性强
2. 便于维护
3. 利用扩展





### 分层解耦

刚才我们学习过程序分层思想了，接下来呢，我们来学习下程序的解耦思想。

解耦：解除耦合。



**耦合问题**

首先需要了解软件开发涉及到的两个概念：内聚和耦合。

- 内聚：软件中各个功能模块内部的功能联系。

- 耦合：衡量软件中各个层/模块之间的依赖、关联的程度。

**软件设计原则：高内聚低耦合。**

> 高内聚指的是：一个模块中各个元素之间的联系的紧密程度，如果各个元素(语句、程序段)之间的联系程度越高，则内聚性越高，即 "高内聚"。
>
> 低耦合指的是：软件中各个层、模块之间的依赖关联程序越低越好。

程序中高内聚的体现：

- EmpServiceA类中只编写了和员工相关的逻辑处理代码

![image-20221204202531571](../image/image-20221204202531571.png) 

程序中耦合代码的体现：

- 把业务类变为EmpServiceB时，需要修改controller层中的代码

![image-20221204203904900](../image/image-20221204203904900.png)

高内聚、低耦合的目的是使程序模块的可重用性、移植性大大增强。

![](../image/image-20220828215549593.png)





**解耦思路**

之前我们在编写代码时，需要什么对象，就直接 `new` 一个就可以了。 这种做法呢，层与层之间代码就耦合了，当 `service` 层的实现变了之后， 我们还需要修改 `controller` 层的代码。

![image-20221204204916033](../image/image-20221204204916033.png)

 那应该怎么解耦呢？

- 首先不能在 `EmpController` 中使用 `new` 对象。代码如下：

![image-20221204205328069](../image/image-20221204205328069.png)

- 此时，就存在另一个问题了，不能 `new`，就意味着没有业务层对象（程序运行就报错），怎么办呢？
  - 我们的解决思路是：
    - 提供一个容器，容器中存储一些对象(例：EmpService对象)
    - `controller` 程序从容器中获取 `EmpService` 类型的对象

我们想要实现上述解耦操作，就涉及到 `Spring` 中的两个核心概念：

- **控制反转：** Inversion Of Control，简称IOC。对象的创建控制权由程序自身转移到外部（容器），这种思想称为控制反转。

  > 对象的创建权由程序员主动创建转移到容器(由容器创建、管理对象)。这个容器称为：IOC容器或Spring容器

- **依赖注入：** Dependency Injection，简称DI。容器为应用程序提供运行时，所依赖的资源，称之为依赖注入。

  > 程序运行时需要某个资源，此时容器就为其提供这个资源。
  >
  > 例：EmpController程序运行时需要EmpService对象，Spring容器就为其提供并注入EmpService对象

IOC容器中创建、管理的对象，称之为：`bean` 对象



## IOC&DI

上面我们引出了Spring中IOC和DI的基本概念，下面我们就来具体学习下IOC和DI的代码实现。

- **任务：**
  1. 删除Controller层、Service层中new对象的代码
  2. Service层及Dao层的实现类，交给IOC容器管理
  3. 为Controller及Service注入运行时依赖的对象
     - Controller程序中注入依赖的Service层对象
     - Service程序中注入依赖的Dao层对象



第1步：删除Controller层、Service层中new对象的代码

![image-20221204212807207](../image/image-20221204212807207.png)



第2步：Service层及Dao层的实现类，交给IOC容器管理

- 使用Spring提供的注解：@Component ，就可以实现类交给IOC容器管理

![image-20221204213328034](../image/image-20221204213328034.png)



第3步：为Controller及Service注入运行时依赖的对象

- 使用Spring提供的注解：@Autowired ，就可以实现程序运行时IOC容器自动注入需要的依赖对象

![image-20221204213859112](../image/image-20221204213859112.png)



完整的三层代码：

- **Controller层：**

~~~java
@RestController
public class EmpController {

    @Autowired //运行时,从IOC容器中获取该类型对象,自动赋值给该变量
    private EmpService empService ;

    @RequestMapping("/listEmp")
    public Result list(){
        //1. 调用service, 获取数据
        List<Emp> empList = empService.listEmp();

        //3. 响应数据
        return Result.success(empList);
    }
}
~~~

- **Service层：**

~~~java
@Component //将当前对象交给IOC容器管理,成为IOC容器的bean
public class EmpServiceA implements EmpService {
    @Autowired //运行时,从IOC容器中获取该类型对象,赋值给该变量
    private EmpDao empDao ;

    @Override
    public List<Emp> listEmp() {
        //1. 调用dao, 获取数据
        List<Emp> empList = empDao.listEmp();

        //2. 对数据进行转换处理 - gender, job
        empList.stream().forEach(emp -> {
            //处理 gender 1: 男, 2: 女
            String gender = emp.getGender();
            if("1".equals(gender)){
                emp.setGender("男");
            }else if("2".equals(gender)){
                emp.setGender("女");
            }

            //处理job - 1: 讲师, 2: 班主任 , 3: 就业指导
            String job = emp.getJob();
            if("1".equals(job)){
                emp.setJob("讲师");
            }else if("2".equals(job)){
                emp.setJob("班主任");
            }else if("3".equals(job)){
                emp.setJob("就业指导");
            }
        });
        return empList;
    }
}
~~~

**Dao层：**

~~~java
@Component //将当前对象交给IOC容器管理,成为IOC容器的bean
public class EmpDaoA implements EmpDao {
    @Override
    public List<Emp> listEmp() {
        //1. 加载并解析emp.xml
        String file = this.getClass().getClassLoader().getResource("emp.xml").getFile();
        System.out.println(file);
        List<Emp> empList = XmlParserUtils.parse(file, Emp.class);
        return empList;
    }
}
~~~



运行测试：

- 启动SpringBoot引导类，打开浏览器，输入：http://localhost:8080/emp.html

![image-20221204185455556](../image/image-20221204185455556.png)



 



### IOC详解

通过IOC和DI的入门程序呢，我们已经基本了解了IOC和DI的基础操作。接下来呢，我们学习下IOC控制反转和DI依赖注入的细节。

### 声明Bean

前面我们提到IOC控制反转，就是将对象的控制权交给Spring的IOC容器，由IOC容器创建及管理对象。IOC容器创建的对象称为bean对象。

在之前的入门案例中，要把某个对象交给IOC容器管理，需要在类上添加一个注解：@Component 

而Spring框架为了更好的标识web应用程序开发当中，bean对象到底归属于哪一层，又提供了@Component的衍生注解：

- @Controller    （标注在控制层类上）
- @Service          （标注在业务层类上）
- @Repository    （标注在数据访问层类上）



修改入门案例代码：

- **Controller层：**

~~~java
@RestController  //@RestController = @Controller + @ResponseBody
public class EmpController {

    @Autowired //运行时,从IOC容器中获取该类型对象,赋值给该变量
    private EmpService empService ;

    @RequestMapping("/listEmp")
    public Result list(){
        //1. 调用service, 获取数据
        List<Emp> empList = empService.listEmp();

        //3. 响应数据
        return Result.success(empList);
    }
}
~~~

- **Service层：**

~~~java
@Service
public class EmpServiceA implements EmpService {

    @Autowired //运行时,从IOC容器中获取该类型对象,赋值给该变量
    private EmpDao empDao ;

    @Override
    public List<Emp> listEmp() {
        //1. 调用dao, 获取数据
        List<Emp> empList = empDao.listEmp();

        //2. 对数据进行转换处理 - gender, job
        empList.stream().forEach(emp -> {
            //处理 gender 1: 男, 2: 女
            String gender = emp.getGender();
            if("1".equals(gender)){
                emp.setGender("男");
            }else if("2".equals(gender)){
                emp.setGender("女");
            }

            //处理job - 1: 讲师, 2: 班主任 , 3: 就业指导
            String job = emp.getJob();
            if("1".equals(job)){
                emp.setJob("讲师");
            }else if("2".equals(job)){
                emp.setJob("班主任");
            }else if("3".equals(job)){
                emp.setJob("就业指导");
            }
        });
        return empList;
    }
}
~~~

**Dao层：**

~~~java
@Repository
public class EmpDaoA implements EmpDao {
    @Override
    public List<Emp> listEmp() {
        //1. 加载并解析emp.xml
        String file = this.getClass().getClassLoader().getResource("emp.xml").getFile();
        System.out.println(file);
        List<Emp> empList = XmlParserUtils.parse(file, Emp.class);
        return empList;
    }
}
~~~



要把某个对象交给IOC容器管理，需要在对应的类上加上如下注解之一：

| 注解        | 说明                 | 位置                                            |
| :---------- | -------------------- | ----------------------------------------------- |
| @Controller | @Component的衍生注解 | 标注在控制器类上                                |
| @Service    | @Component的衍生注解 | 标注在业务类上                                  |
| @Repository | @Component的衍生注解 | 标注在数据访问类上（由于与mybatis整合，用的少） |
| @Component  | 声明bean的基础注解   | 不属于以上三类时，用此注解                      |

> 查看源码：![image-20221204221320230](../image/image-20221204221320230.png)

在IOC容器中，每一个Bean都有一个属于自己的名字，可以通过注解的value属性指定bean的名字。如果没有指定，默认为首字母小写的类名。

![image-20221204222650873](../image/image-20221204222650873.png)

> 注意事项: 
>
> - 声明bean的时候，可以通过value属性指定bean的名字，如果没有指定，默认为类名首字母小写。
> - 使用以上四个注解都可以声明bean，但是在springboot集成web开发中，声明控制器bean只能用@Controller。





### 组件扫描

问题：使用前面学习的四个注解声明的bean，一定会生效吗？

答案：不一定。（原因：bean想要生效，还需要被组件扫描）



 下面我们通过修改项目工程的目录结构，来测试bean对象是否生效：

![image-20221204223602694](../image/image-20221204223602694.png)

运行程序后，报错：

![image-20221204223815554](../image/image-20221204223815554.png)

为什么没有找到bean对象呢？

- 使用四大注解声明的bean，要想生效，还需要被组件扫描注解@ComponentScan扫描

> @ComponentScan注解虽然没有显式配置，但是实际上已经包含在了引导类声明注解 @SpringBootApplication 中，==**默认扫描的范围是SpringBoot启动类所在包及其子包**==。
>
> ![image-20221204224643683](../image/image-20221204224643683.png) 

- 解决方案：手动添加@ComponentScan注解，指定要扫描的包   （==仅做了解，不推荐==）

![image-20221204225437297](../image/image-20221204225437297.png)



推荐做法（如下图）：

- 将我们定义的controller，service，dao这些包呢，都放在引导类所在包com.itheima的子包下，这样我们定义的bean就会被自动的扫描到

![image-20221204225815624](../image/image-20221204225815624.png)





### DI详解

上一小节我们讲解了控制反转IOC的细节，接下来呢，我们学习依赖注解DI的细节。

依赖注入，是指IOC容器要为应用程序去提供运行时所依赖的资源，而资源指的就是对象。

在入门程序案例中，我们使用了 `@Autowired` 这个注解，完成了依赖注入的操作，而这个 `Autowired` 翻译过来叫：自动装配。

`@Autowired` 注解，默认是按照**类型**进行自动装配的（去IOC容器中找某个类型的对象，然后完成注入操作）

> 入门程序举例：在EmpController运行的时候，就要到IOC容器当中去查找EmpService这个类型的对象，而我们的IOC容器中刚好有一个EmpService这个类型的对象，所以就找到了这个类型的对象完成注入操作。



那如果在IOC容器中，存在多个相同类型的 `bean` 对象，会出现什么情况呢？

![image-20221204232154445](../image/image-20221204232154445.png)

- 程序运行会报错

![image-20221204231616724](../image/image-20221204231616724.png)



如何解决上述问题呢？Spring提供了以下几种解决方案：

- @Primary

- @Qualifier

- @Resource



使用 `@Primary` 注解：当存在多个相同类型的 `Bean` 注入时，加上 `@Primary` 注解，来确定默认的实现。

![image-20221204232501679](../image/image-20221204232501679.png) 



使用 `@Qualifier` 注解：指定当前要注入的 `bean` 对象。 在 `@Qualifier的value` 属性中，指定注入的 `bean` 的名称。

- `@Qualifier` 注解 **不能单独使用**，必须配合 `@Autowired` 使用

![image-20221204233333606](../image/image-20221204233333606.png)



使用 `@Resource` 注解：默认按照 `bean` 的名称进行注入。可以通过 `name` 属性指定要注入的 `bean` 名称。

![image-20221204233637735](../image/image-20221204233637735.png)



下述代码 `@Resource` 不指定名称，则以类名注入，相当于 `@Resource(name="userService")`

```java
@Resource
private UserService userService;
```



> 面试题 ： @Autowird 与 @Resource的区别
>
> - @Autowired 是spring框架提供的注解，而@Resource是JDK提供的注解
> - @Autowired 默认是按照类型注入，而@Resource是按照名称注入



## AOP

AOP英文全称：`Aspect Oriented Programming`（面向切面编程），其实说白了，面向切面编程就是面向特定方法编程



**使用AOP的优势：**

1. 减少重复代码
2. 提高开发效率
3. 维护方便



**坐标**

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-aop</artifactId>
</dependency>
```



**编写业务逻辑**

```java
package liuyuyang.service;

public interface Cs {
    public void info1();

    public void info2();
}
```

```java
package liuyuyang.service.impl;

import liuyuyang.service.Cs;
import org.springframework.stereotype.Service;

@Service
public class CsImpl implements Cs {
    public void info1(){
        System.out.println("11111111111111111");
    }

    public void info2(){
        System.out.println("22222222222222222");
    }
}
```



**测试程序代码**

```java
import liuyuyang.Main;
import liuyuyang.service.Cs;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
public class TestCsImpl {
    @Resource
    private Cs cs;

    @Test
    public void fun(){
        cs.info1();
        cs.info2();
    }
}
```



### 通知类型

#### 前后通知

在 `liuyuyang.service` 这个包中的所有类以及类的方法会在调用时候自动触发对应的方法

```java
package liuyuyang;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Service;

@Service
// 使当前类为切面类
@Aspect
public class TimeAspect {
    // 在目标方法调用前执行
    @Before("execution(* liuyuyang.service.impl.*.*(..))")
    public void before(JoinPoint j){        
        System.out.println("before~ 方法调用前执行");
    }

    // 在目标方法调用后执行
    @After("execution(* liuyuyang.service.impl.*.*(..))")
    public void after(){
        System.out.println("after~ 方法调用后执行");
    }
}
```

**运行效果**

```
before~ 方法调用前执行
11111111111111111
after~ 方法调用后执行
    
before~ 方法调用前执行
22222222222222222
after~ 方法调用后执行
```



#### 返回后通知

在每次调用完之后触发并返回 `return` 值

```java
@Service
@Aspect
public class TimeAspect {
    // 后置通知
    @AfterReturning(value = "execution(* liuyuyang.service.impl.*.*(..))", returning = "o")
    public void before(JoinPoint j, Object o) {
        System.out.printf("函数名：%s 返回值：%s\n", j.getSignature().getName(), o);
    }
}
```

**运行效果**

```
11111111111111111
函数名：info1 返回值：null

22222222222222222
函数名：info2 返回值：null
```



#### 环绕通知

在目标方法调用前、调用后触发，支持返回 `return`

```java
package liuyuyang;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

@Service
@Aspect
public class TimeAspect {
    // 环绕通知
    @Around("execution(* liuyuyang.service.impl.*.*(..))")
    public Object around(ProceedingJoinPoint p) throws Throwable {
        System.out.println("在调用方法前做哪些操作~");

        // 调用方法并获取方法返回值
        Object r = p.proceed();

        System.out.println("在调用方法后做哪些操作~");

        // 将方法返回值返回
        return r;
    }
}
```

**运行效果**

```
在调用方法前做哪些操作~
11111111111111111
在调用方法后做哪些操作~

在调用方法前做哪些操作~
22222222222222222
在调用方法后做哪些操作~
```



#### 异常通知

程序在异常情况下才会执行

```java
public void info1() {
    // 在这里手动写一个异常
	System.out.println(1/0);
}
```

```java
@Service
@Aspect
public class TimeAspect {
    // 抛出异常通知
    @AfterThrowing(value = "execution(* liuyuyang.service.impl.*.*(..))", throwing = "e")
    public void afterThrowing(Exception e) {
        System.out.printf("抛出异常通知：%s\n", e);
        // 抛出异常通知：java.lang.ArithmeticException: / by zero
    }
}
```

**运行效果**

```
抛出异常通知：java.lang.ArithmeticException: / by zero
```



#### 最终异常

了解

```java
@Service
@Aspect
public class TimeAspect {
    // 最终异常通知
    @After("execution(* liuyuyang.service.impl.*.*(..))")
    public void after() {
        System.out.println("最终异常通知：");
    }
}
```



### 顺序

#### 通知顺序

如果在项目中定义了多个切面类，而多个切面类中多个切入点都匹配了同一个目标方法，此时目标方法在运行的时候，这多个切面类当中的通知方法都会执行



**定义多个切面类：**

~~~java
@Slf4j
@Component
@Aspect
public class MyAspect2 {
    //前置通知
    @Before("execution(* com.itheima.service.impl.*.*(..))")
    public void before(){
        log.info("MyAspect2 -> before ...");
    }

    //后置通知
    @After("execution(* com.itheima.service.impl.*.*(..))")
    public void after(){
        log.info("MyAspect2 -> after ...");
    }
}

~~~

~~~java
@Slf4j
@Component
@Aspect
public class MyAspect3 {
    //前置通知
    @Before("execution(* com.itheima.service.impl.*.*(..))")
    public void before(){
        log.info("MyAspect3 -> before ...");
    }

    //后置通知
    @After("execution(* com.itheima.service.impl.*.*(..))")
    public void after(){
        log.info("MyAspect3 ->  after ...");
    }
}
~~~

~~~java
@Slf4j
@Component
@Aspect
public class MyAspect4 {
    //前置通知
    @Before("execution(* com.itheima.service.impl.*.*(..))")
    public void before(){
        log.info("MyAspect4 -> before ...");
    }

    //后置通知
    @After("execution(* com.itheima.service.impl.*.*(..))")
    public void after(){
        log.info("MyAspect4 -> after ...");
    }
}

~~~



**运行效果**

![image-20230110211208549](../image/image-20230110211208549.png)

通过以上程序运行可以看出在不同切面类中，默认按照切面类的类名字母或数字进行排序：

- 目标方法前的通知方法：字母或数字排名靠前的先执行
- 目标方法后的通知方法：字母或数字字母排名靠前的后执行



#### 控制顺序

如果我们想控制通知的执行顺序有两种方式：

1. 修改切面类的类名（这种方式非常繁琐、而且不便管理）
2. 使用 `Spring` 提供的 `@Order` 注解



使用 `@Order` 注解，控制通知的执行顺序：

~~~java
@Slf4j
@Component
@Aspect
@Order(2)  //切面类的执行顺序（前置通知：数字越小先执行; 后置通知：数字越小越后执行）
public class MyAspect2 {
    //前置通知
    @Before("execution(* com.itheima.service.impl.*.*(..))")
    public void before(){
        log.info("MyAspect2 -> before ...");
    }

    //后置通知 
    @After("execution(* com.itheima.service.impl.*.*(..))")
    public void after(){
        log.info("MyAspect2 -> after ...");
    }
}
~~~

~~~java
@Slf4j
@Component
@Aspect
@Order(3)  //切面类的执行顺序（前置通知：数字越小先执行; 后置通知：数字越小越后执行）
public class MyAspect3 {
    //前置通知
    @Before("execution(* com.itheima.service.impl.*.*(..))")
    public void before(){
        log.info("MyAspect3 -> before ...");
    }

    //后置通知
    @After("execution(* com.itheima.service.impl.*.*(..))")
    public void after(){
        log.info("MyAspect3 ->  after ...");
    }
}
~~~

~~~java
@Slf4j
@Component
@Aspect
@Order(1) //切面类的执行顺序（前置通知：数字越小先执行; 后置通知：数字越小越后执行）
public class MyAspect4 {
    //前置通知
    @Before("execution(* com.itheima.service.impl.*.*(..))")
    public void before(){
        log.info("MyAspect4 -> before ...");
    }

    //后置通知
    @After("execution(* com.itheima.service.impl.*.*(..))")
    public void after(){
        log.info("MyAspect4 -> after ...");
    }
}
~~~

**运行效果**

![image-20230110212523787](../image/image-20230110212523787.png)

**注意：**

1. 不同的切面类当中，默认情况下通知的执行顺序是与切面类的类名字母排序是有关系的
2. 可以在切面类上面加上 `@Order` 注解，来控制不同的切面类通知的执行顺序



### 表达式

#### 切入表达式

其中带 `?` 的表示可以省略的部分

访问修饰符：可省略（比如: public、protected）

包名.类名： 可省略

异常：可省略（注意是方法上声明抛出的异常，不是实际抛出的异常）

```
execution(访问修饰符?  返回值  包名.类名.?方法名(方法参数) throws 异常?)
```



匹配类型为 `public` 返回值为 `void` ，在 `liuyuyang.service` 目录中的 `impl` 包中的 `UserServiceImpl` 类的参数为 `Inteage` 的 `delete` 方法

```java
@Before("execution(public void liuyuyang.service.impl.UserServiceImpl.delete(java.lang.Intege))")
```

- `execution(public void`：匹配公共（public）、无返回值（void）的方法
- `liuyuyang.service.impl.UserServiceImpl`：指定类的全限定名为 `liuyuyang.service.impl.UserServiceImpl`
- `delete`：匹配名为 `delete` 的方法
- `(java.lang.Integer)`：表示该方法接受一个 `java.lang.Integer` 类型的参数



匹配在 `liuyuyang.service` 目录中所有包的所有文件的所有方法

1. 只匹配参数为 `String` 的方法

   ```java
   @Before("execution(* liuyuyang.service.*.*(java.lang.String))")
   ```

2. 至少匹配一个任意类型的方法

   ```java
   @Before("execution(* liuyuyang.service.*.*(*))")
   ```

3. 匹配所有类型的方法

   ```java
   @Before("execution(* liuyuyang.service.*.*(..))")
   ```



匹配 `liuyuyang.service` 目录中所有包、所有文件、所有方法（包含子包）

```java
@Before("execution(* liuyuyang.service..*.*(..))")
```

- `..`：表示匹配当前包及其子包
- `*`：通配符，表示任意类名
- `*`：通配符，表示任意方法名
- `(..)`：表示任意参数类型和个数



看解释

```java
@Before("execution(* liuyuyang.*.service.*.update*(*))")
```

- `liuyuyang.*.service.*`： `*` 代表一个具体的包名，可以匹配任意一个包名，但只有一个层级。
  例如：`liuyuyang.*.service` 可以匹配 `liuyuyang.abc.service`、`liuyuyang.xyz.service`，但不能匹配 `liuyuyang.abc.xyz.service`
- `.*`：通配符，表示匹配所有类名
- `update*`：表示名字以 `update` 开头的方法名，其中 `*` 表示接着匹配任意字符串
- `(*))：表示匹配至少一个任意类型的参数



看解释

```java
@Before("execution(* liuyuyang.service..DeptService.*(..))")
```

- `liuyuyang.service..`：指定包名为 `liuyuyang.service` 并匹配其子包
- `DeptService`：匹配名为 `DeptService` 的接口或类
- `.*`：通配符，表示匹配所有方法名
- `(..)`：表示匹配所有参数类型和个数



根据业务需要，可以使用 且（&&）、或（||）、非（!） 来组合比较复杂的切入点表达式。



#### 抽取表达式

这是没有抽取的写法，重复的代码会显得很冗余

```java
@Service
@Aspect
public class TimeAspect {
    // 在目标方法调用前执行
    @Before("execution(* liuyuyang.service.impl.*.*(..))")
    public void before(JoinPoint j){
        System.out.println("before~ 方法调用前执行");
    }

    // 在目标方法调用后执行
    @After("execution(* liuyuyang.service.impl.*(..))")
    public void after(){
        System.out.println("after~ 方法调用后执行");
    }
}
```



可以通过 `@Pointcut` 来抽取实现切入表达式的复用

```java
@Service
@Aspect
public class TimeAspect {
    @Pointcut("execution(* liuyuyang.service.impl.*.*(..))")
    private void func(){}

    // 在目标方法调用前执行
    @Before("func()")
    public void before(JoinPoint j){
        System.out.println("before~ 方法调用前执行");
    }

    // 在目标方法调用后执行
    @After("func()")
    public void after(){
        System.out.println("after~ 方法调用后执行");
    }
}
```



### 应用场景

**需求一：** 记录业务层方法执行耗时

```java
package liuyuyang;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

@Service
@Aspect
public class TimeAspect {
    // 当目标包中的方法调用时会自动触发该方法
    @Around("execution(* liuyuyang.service.impl.*.*(..))")
    public void recordTime(ProceedingJoinPoint p) throws Throwable {
        // 记录方法执行开始时间
        long start = System.currentTimeMillis();

        // 用于执行目标包中的方法
        p.proceed();

        // 记录方法执行结束时间
        long end = System.currentTimeMillis();

        // 打印目标方法的位置以及名称
        // System.out.println(p.getSignature()); // void liuyuyang.service.impl.CsImpl.info1()
        // System.out.println(p.getSignature().getName()); // info1

        // 计算方法执行耗时并打印日志
        System.out.println(p.getSignature() + " 执行耗时: " + (end - start) + "毫秒");
    }
}
```

**运行效果**

```
11111111111111111
void liuyuyang.service.impl.CsImpl.info1() 执行耗时: 7毫秒
    
22222222222222222
void liuyuyang.service.impl.CsImpl.info2() 执行耗时: 0毫秒
```



**需求二：** 记录业务层方法日志

```java
package liuyuyang;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@Aspect
public class TimeAspect {
    @Pointcut("execution(* liuyuyang.service.impl.*.*(..))")
    private void type() {}

    // 核心代码
    public void func(JoinPoint j, String status) {
        // 获取类名：liuyuyang.service.impl.UserServiceImpl
        String s = j.getTarget().getClass().getName();
        // 找出类名后面的"."下标
        int index = s.lastIndexOf(".");
        // 通过字符串截取出类名
        String c = s.substring(index + 1);
        
        // 获取方法名
        String m = j.getSignature().getName();

        // 获取当前时间
        LocalDateTime date = LocalDateTime.now();

        // 时间格式化
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss-SSS");
        System.out.printf("操作时间：%s 操作类：%s 操作方法 %s %s\n", f.format(date), c, m, status);
    }

    @Before("type()")
    public void logStart(JoinPoint j) {
        func(j,"开始执行");
    }

    @After("type()")
    public void logEnd(JoinPoint j) {
        func(j,"结束执行");
    }
}
```

**运行效果**

```
操作时间：2023-11-06 19-55-51.827 操作类：UserServiceImpl 操作方法 info 开始执行

User(uid=2, uname=rose, orderList=[Order(oid=2, price=34, userId=null), Order(oid=4, price=3453, userId=null)])

操作时间：2023-11-06 19-55-51.852 操作类：UserServiceImpl 操作方法 info 结束执行
```

**代码目录：** `代码/AOP`



## 事务管理

事务是指一组 `Sql` 的集合，集合中有多条 `Sql` 语句，可以是 `insert、update、select、delete`，希望这些SQL语句执行是一致的，作为一个整体执行。要么都成功，要么都失败



### 事务的特点（ACID）

| 特性                  | 描述                                                         |
| --------------------- | ------------------------------------------------------------ |
| 原子性（Atomicity）   | 事务是一个原子操作，由一系列动作组成。事务的原子性确保动作要么全部完成，要么完全不起作用。 |
| 一致性（Consistency） | 一旦事务完成（不管成功还是失败），系统必须确保它所建模的业务处于一致的状态，而不会是部分完成部分失败。在现实中的数据不应该被破坏。 |
| 隔离性（Isolation）   | 可能有许多事务会同时处理相同的数据，因此每个事务都应该与其他事务隔离开来，防止数据损坏。 |
| 持久性（Durability）  | 一旦事务完成，无论发生什么系统错误，它的结果都不应该受到影响，这样就能从任何系统崩溃中恢复过来。通常情况下，事务的结果被写到持久化存储器中。 |



### 应用场景

为什么要使用事务？事务解决了哪些问题？

当操作多个表，或多个SQL语句的 `insert、update、delete`。需要保证这些语句都是成功才能完成功能，或者都失败是符合要求的。（要么都成功，要么都失败）



**如下应用场景：** 在转账时候需要先扣除自己的钱然后转账给对方，而如果中途的代码逻辑发生了报错，则会导致转账失败，并且自己的钱也会被扣除。

```java
@Mapper
public interface AccountMapper {
    @Update("update user set money = money + #{money} where uname = #{uname}")
    public void in(String uname, int money);

    @Update("update user set money = money - #{money} where uname = #{uname}")
    public void out(String uname, int money);
}
```

```java
@Service
// @Transactional
public class AccountServiceImpl implements AccountService {
    @Resource
    private AccountMapper accountMapper;

    @Override
    // @Transactional
    public void transfer(String outUser, String inUser, int money) {
        // outUser转账给inUser用户

        // 扣除自己的钱
        accountMapper.out(outUser, money);

        // 手动写一个报错
        System.out.println(1/0);

        // 转账给对方
        accountMapper.in(inUser, money);
    }
}
```

此时就需要用到事务来解决这个问题，只需要给方法添加一个注解：`@Transactional` ，这样发生异常会执行回滚操作，从而保证事务操作前后数据是一致的



### @Transactional

**@Transactional作用：** 在当前方法执行开始前开启事务，方法执行完毕后提交事务。如果在这个方法执行的过程当中出现了异常，就会进行事务的回滚操作。



**@Transactional注解书写位置：**

- 方法
  - 当前方法交给 `spring` 进行事务管理
- 类
  - 当前类中所有的方法都交由 `spring` 进行事务管理
- 接口
  - 接口下所有的实现类当中所有的方法都交给 `spring` 进行事务管理



### rollbackFor

**为什么要使用 rollbackFor ？先看下面的场景：**

默认情况下 `@Transactional` 注解只能处理 `RuntimeException` 类型的异常才会回滚事务，而遇到其他类型的异常，比如：`Exception` 则没有效果，会跟不加注解一样。



为了解决这个问题，可以这么做，配置 `@Transactional` 注解当中的 `rollbackFor` 属性，通过这个属性可以指定出现哪种异常类型时回滚事务。如下：

```java
 @Transactional(rollbackFor=Exception.class)
```



## 过滤器

- `Filter` 表示过滤器，是  `JavaWeb` 三大组件 (Servlet、Filter、Listener) 之一。
- 过滤器可以把对资源的请求拦截下来，从而实现一些特殊的功能
  - 使用了过滤器之后，要想访问 `web` 服务器上的资源，必须先经过滤器，过滤器处理完毕之后，才可以访问对应的资源。
- 过滤器一般完成一些通用的操作，比如：登录校验、统一编码处理、敏感字符处理等。

![image-20230112120955145](../../image/image-20230112120955145.png) 



下面我们通过 `Filter` 快速入门 掌握过滤器的基本使用操作：

- **第1步，定义过滤器 ：** 定义一个类，实现 `Filter` 接口，并重写其所有方法。
- **第2步，配置过滤器：** `Filter` 类上加 `@WebFilter` 注解，配置拦截资源的路径。最后在启动类上加 `@ServletComponentScan` 开启 `Servlet` 组件支持。



**代码示例：** 实现一个最基本的过滤器

```java
package yang.utils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class DemoFilter implements Filter {
    // 初始化时自动调用一次
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("初始化方法执行了");
        Filter.super.init(filterConfig);
    }

    // 每次网络请求时调用
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("拦截到了请求");
        chain.doFilter(request, response); // 放行
        System.out.println("放行后做哪些事情...");
    }

    // 销毁时自动调用一次
    @Override
    public void destroy() {
        System.out.println("销毁方法执行了");
        Filter.super.destroy();
    }
}
```

其实 `init` 和 `destroy` 方法有默认的实现，可以不用定义。而 `doFilter` 必须定义



- init：在服务器启动的时会自动的创建 `Filter` 过滤器对象，在创建过滤器对象的时会自动调用 `init` 初始化方法，这个方法只会被调用一次。

- doFilter：在每一次拦截到请求之后都会调用这个方法，所以这个方法是会被调用多次的，每拦截到一次请求就会调用一次

- destroy： 在关闭服务器时它会自动调用该方法，并且这个销毁方法跟 `init` 一样，只会被调用一次。



### 拦截路径

`Filter` 可以根据 `@WebFilter(urlPatterns = "/*")` 注解配置不同的拦截资源路径：

| 拦截路径     | urlPatterns值 | 含义                                  |
| ------------ | ------------- | ------------------------------------- |
| 拦截具体路径 | /login        | 只有访问 /login 路径时，才会被拦截    |
| 前缀拦截     | /emps/*       | 访问前缀为/emps的所有资源，都会被拦截 |
| 拦截所有     | /*            | 访问所有资源，都会被拦截              |



### 过滤器链

当有多个过滤器时就形成了过滤器链，假设有 `AAA` 和 `BBB` 过滤器，那么就会根据过滤器名的首字母来决定执行顺序。

过滤器会先执行 `AAA`，只有放行之后才会执行 `BBB` 过滤器，等 `BBB` 放行后才会返回真正的放行



### 登录校验

接下来我们可以通过过滤器实现身份验证功能，如果有 `token` 就放行接口，没有 `token` 或到期、失效则禁止放行

![image-20240226164349602](../../image/image-20240226164349602.png)

**代码示例**

```java
package yang.utils;

import com.alibaba.fastjson.JSONObject;
import com.mysql.cj.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import yang.pojo.Result;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = "/*")
public class DemoFilter implements Filter {
    // 每次网络请求时调用
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("拦截到了请求");
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        // 获取请求的URL
        String url = req.getRequestURI().toString();
        log.info("获取请求的URL：{}", url);

        // 如果访问的是登录接口，就直接放行
        if (url.contains("/login") || url.contains("/swagger") || url.contains("/v3/api-docs")) {
            chain.doFilter(request, response);
            return;
        }

        // 拿到请求头中的Token
        String token = req.getHeader("token");

        // token不能为空
        if (token == null) {
            res.setCharacterEncoding("UTF-8");

            Result data = Result.error("Token不能为空");
            String result = JSONObject.toJSONString(data);
            res.setContentType("application/json;charset=utf-8");

            res.getWriter().write(result);
            return;
        }

        // 如果解析Token失败则表示过期或无效
        try {
            JwtUtils.parseJWT(token);
            chain.doFilter(request, response);
        } catch (Exception e) {
            Result data = Result.error(e.getMessage());
            String result = JSONObject.toJSONString(data);
            res.setContentType("application/json;charset=utf-8");

            res.getWriter().write(result);
        }
    }
}
```



## 拦截器

定义一个基本的拦截器

```java
package yang.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginCheckInterceptor implements HandlerInterceptor {
    // 在请求处理之前被调用
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        System.out.println("preHandle...");
        return true;
    }

    // 在目标方法执行之后被调用
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle ... ");
    }

    // 在整个请求处理完毕之后，无论成功或失败，都会被调用
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion .... ");
    }
}
```



**拦截器三个方法的详细说明**

1. `preHandle` 方法在请求处理之前被调用。在这个方法中，可以做一些前置处理，比如判断请求是否合法，记录日志等。

   如果返回 `true`，则继续执行后续的拦截器以及目标方法；反之中断请求，不再往后执行。

2. `postHandle` 方法在目标方法执行之后，返回 `ModelAndView` 之前执行。在这个方法中，你可以对返回的 `ModelAndView` 进行修改或者做一些额外的处理。

3. `afterCompletion` 方法在整个请求处理完毕之后，无论成功或失败，都会被调用。通常用于清理资源、记录日志等操作。如果请求处理过程中发生异常，异常信息会传递到这个方法中。



**注册并使用拦截器**

想要拦截器生效，就必须先注册

```java
package yang.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    // 自定义的拦截器对象
    @Autowired
    private LoginCheckInterceptor loginCheckInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册自定义拦截器对象
        registry.addInterceptor(loginCheckInterceptor)
                // 设置拦截的请求路径（ /** 表示拦截所有请求）
                .addPathPatterns("/**");
    }
}
```



### 拦截路径

在拦截器中可以通过 `addPathPatterns` 设置拦截路径，下面是一些常见拦截路径配置：

| 拦截路径  | 含义                 | 举例                                                        |
| --------- | -------------------- | ----------------------------------------------------------- |
| /*        | 一级路径             | 能匹配：/depts，/emps，/login   <br />不能匹配：/depts/1    |
| /**       | 任意级路径           | 能匹配：/depts，/depts/1，/depts/1/2                        |
| /depts/*  | /depts下的一级路径   | 能匹配：/depts/1<br />不能匹配：/depts/1/2，/depts          |
| /depts/** | /depts下的任意级路径 | 能匹配：/depts，/depts/1，/depts/1/2<br />不能匹配：/emps/1 |



### 执行流程

假如过滤器与拦截器同时存在，那么会优先执行过滤器，过滤器放行后才会执行拦截器，反之不会执行拦截器



**过滤器**

```java
@WebFilter(urlPatterns = "/*")
public class DemoFilter implements Filter {
    // 每次网络请求时调用
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("拦截到了请求");

        System.out.println("放行前");
        chain.doFilter(request, response);
        System.out.println("放行后");
    }
}
```



**拦截器**

```java
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {
    // 在请求处理之前被调用
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        System.out.println("preHandle...");
        return true;
    }

    // 在目标方法执行之后被调用
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle ... ");
        System.out.println(modelAndView);
    }

    // 在整个请求处理完毕之后，无论成功或失败，都会被调用
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion .... ");
    }
}
```

![image-20240226175212445](../../image/image-20240226175212445.png)



**过滤器和拦截器之间的区别主要是以下两点：**

- 接口规范不同：过滤器需要实现 `Filter` 接口，而拦截器需要实现 `HandlerInterceptor` 接口。
- 拦截范围不同：过滤器 `Filter` 会拦截所有的资源，而 `Interceptor` 只会拦截 `Spring` 环境中的资源。



### 登录校验

```java
package yang.interceptor;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import yang.pojo.Result;
import yang.utils.JwtUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginCheckInterceptor implements HandlerInterceptor {
    // 在请求处理之前被调用
    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws IOException {
        // 获取请求的URL
        String url = req.getRequestURI().toString();

        // 如果访问的是登录接口，就直接放行
        if (url.contains("/login") || url.contains("/swagger") || url.contains("/v3/api-docs")) {
            return true;
        }

        // 拿到请求头中的Token
        String token = req.getHeader("token");

        // token不能为空
        if (token == null) {
            res.setCharacterEncoding("UTF-8");

            Result data = Result.error("Token不能为空");
            String result = JSONObject.toJSONString(data);
            res.setContentType("application/json;charset=utf-8");

            res.getWriter().write(result);
            return false;
        }

        // 如果解析Token失败则表示过期或无效
        try {
            JwtUtils.parseJWT(token);
        } catch (Exception e) {
            Result data = Result.error(e.getMessage());
            String result = JSONObject.toJSONString(data);
            res.setContentType("application/json;charset=utf-8");

            res.getWriter().write(result);
        }

        return true;
    }
}
```



## 配置

### 自定义配置

**application.yml**

定义配置项

```yml
user:
  username: "admin"
  password: "123123"
```



**UserProperties**

通过 `@Value` 从 `application.yml` 中加载配置信息

```java
@Data
@Configuration  // 声明当前类为配置类
public class UserProperties {
    // 从配置中读取属性值
    @Value("${user.username}")
    private String username;

    // 设置默认值：@Value("${user.password:123456}")
    @Value("${user.password}")
    private String password;
}
```


也可以通过 `@ConfigurationProperties` 配置前缀来加载对应的配置信息

```java
@Data
@Configuration
@ConfigurationProperties(prefix = "user")
public class UserProperties {
    private String username;
    private String password;
}
```



**ResApplicationTests**

读取配置类并使用配置项

```java
@SpringBootTest
class ResApplicationTests {
    // 读取配置类
    @Resource
    private UserProperties userProperties;

    @Test
    public void run() {
       System.out.println(userProperties);
    }
}
```



**复杂的配置信息读取**

```yml
user:
  username: "admin"
  password: "123123"
  age: 18
  birthday: "1990-01-01"
  vip: true
  hobbyList:
    - "敲代码"
    - "写代码"
    - "打游戏"
  ageArray:
    - 18
    - 19
    - 20
  propList:
    - username: "张三"
      password: "18"
      age: 18
      birthday: "1990-01-01"
    - username: "李四"
      password: "18"
```

```java
@Data
@Configuration
@ConfigurationProperties(prefix = "user")
public class UserProperties {
    private String username;
    private String password;
    private Integer age;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    private Boolean vip;
    private List<String> hobbyList;
    private List<Integer> ageArray;
    private List<UserProperties> propList;
}
```



### 常见配置项

#### 开启Bean覆盖

默认情况下程序中如果有两个相同名的 `Bean` 则会在控制台出现如下报错：

```
Description:

The bean 'course-service.FeignClientSpecification' could not be registered. A bean with that name has already been defined and overriding is disabled.

Action:

Consider renaming one of the beans or enabling overriding by setting spring.main.allow-bean-definition-overriding=true
```



我们可以通过如下配置来解决这个问题，使新的覆盖旧的 `Bean` 

```yml
spring
	main
		allow-bean-definition-overriding: true # 开启Bean覆盖
```



## 文件上传

```java
package yang.controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/upload")
public class UploadController {
    @PostMapping
    public void upload(@RequestPart MultipartFile file) throws IOException {
        // 获取文件名
        String fileName = file.getOriginalFilename();
        // 获取文件扩展名
        String extName = fileName.substring(fileName.lastIndexOf("."));
        // 获取项目根目录
        String root = System.getProperty("user.dir");
        // 获取文件存放目录
        String fileDir = "/src/main/java/yang/upload/";

        // 如果目标目录不存在就创建
        File dir = new File(root + fileDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // 生成不重复的文件名称
        String uuidName = UUID.randomUUID().toString() + extName;

        // 文件上传的位置
        String uploadDir = root + fileDir + uuidName;

        // 上传文件
        file.transferTo(new File(uploadDir));
    }
}
```



## 单元测试

引入相关依赖

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-test</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-test</artifactId>
</dependency>

<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
</dependency>
```



测试获取数据

```java
@SpringBootTest
@RunWith(SpringRunner.class)
public class Run {

    @Resource
    private UserMapper userMapper;

    @Test
    public void testQueryUserById() {
        System.out.println(userMapper.queryUserById(1L));
    }
}
```

