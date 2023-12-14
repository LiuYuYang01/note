# Sprint Boot 常用注解

## 请求与响应

### @RequestMapping

可以给类或类的属性设置该注解，表示支持的所有 `HTTP` 请求方法，如 `GET`、`POST`、`PATCH`、`DELETE` 等

如果给类设置，表示该路径的前缀。给方法设置，表示让这个方法支持所有的请求方式

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



### @RequestBody

默认参数传递方式为 `x-www-form-urlencoded` ，该注解主要用于将参数传递方式设置为 `application/json` 格式，这样就支持 `JSON` 格式数据作为参数传递了

```java
@PostMapping
public Result add(@RequestBody User user) {
	return Result.success(UserService.add(user));
}
```



### @PathVariable

该注解主要用于从 `URL` 路径中获取动态参数的值

**代码示例**

```java
@GetMapping("/users/{uid}")
public <List<Course> get(@PathVariable("uid") Integer id){
	return null;
}
```

上述代码 `{uid}` 是一个变量，通过 `@PathVariable` 获取到它的值然后映射给参数 `id` 使用。



如果参数名与路径变量名一致，则可以省略括号中的内容 `uid`，如下：

```java
public <List<Course> get(@PathVariable Integer uid)
```



## 数据

### @Param

该注解用于映射



## Mybatis

### @Table

该注解用于在实体类总表示当前操作的数据表名

```java
@Table(name = "tb_student")
public class Student {...}
```



### @Id

该注解用于在实体类中表示主键的字段



### @KeySql

使用该注解时，在执行插入操作后，数据库会生成一个自增的主键值，并将其返回给应用程序。然后将自动生成的主键值注入到对象的主键属性中。

通过注解 `@KeySql(useGeneratedKeys = true)`，就可以获取到新增后的数据 `id` 



### @Column

该注解用于指定实体类中该成员变量所对应的数据库字段名

```java
@Column(name = "s_id")
private Integer sid;
```



### @Param

通过 `@Param` 注解，可以为 `SQL` 语句中的参数指定一个具体的名称，而不是使用默认的参数名称，从而避免名称冲突

```java
@Mapper
public interface UserMapper {
    @Select("select * from user where id = #{id}")
    User getUserById(@Param("id") Integer uid);
}
```



### @Transient

当某个属性或类被设置了该注解后，在持久化数据时会被忽略，不会保存到数据库中。

该注解通常用于临时存储数据，不让他保存到数据库中



### @Transactional

该注解的作用是事务回滚，保证一组数据库操作要么全部成功执行，要么全部回滚到事务开始状态



## Mybatis Plus

### @TableName

类似于 `Mybatis` 中的 `@Table` 注解，

不同的是它还可以通过 `keepGlobalPrefix` 属性设置表前缀

```java
@Data
@TableName(value = "customer", keepGlobalPrefix = true) // 核心代码
public class Customer {}
```



### @TableId

类似于 `Mybatis` 中的 `@Id` 注解

不同的是它还可以通过 `type` 属性设置主键类型

| 注解                                | 含义                                                         |
| ----------------------------------- | ------------------------------------------------------------ |
| @TableId(type = IdType.ASSIGN_UUID) | 插入数据后 会自动生成一个唯一的 `UUID` 值作为主键(string)    |
| @TableId(type = IdType.ASSIGN_ID)   | 插入数据时 需要手动赋值一个唯一的 `ID` 作为主键(number\|string) |
| @TableId(type = IdType.AUTO)        | 插入数据时，数据库会自动给该字段生成一个唯一的递增值作为主键 |

```java
@Data
@TableName(value = "customer", keepGlobalPrefix = true)
public class Customer {
    @TableId(type = IdType.AUTO) // 根据数据库中的主键自动增长
    private Integer cid;
}
```



### @TableField

结合了 `Mybatis` 中的 `Column` 与 `@Transient` 注解的功能

```java
@Data
@TableName(value = "customer", keepGlobalPrefix = true)
public class Customer {
    // 映射数据库字段
    @TableField(value = "c_id")
    private Integer cid;

    // 设置为临时数据，不需要存储到数据库
    @TableField(exist = false)
    private List<Integer> ids;
}
```

**注意：** 当使用了 `@TableId` 注解主键后，就不能再使用 `@TableField` 注解映射对应的数据库字段了，可以使用 `@TableId(value = "字段名", type = IdType.AUTO)`



## JSON

### @JsonFormat

该注解可以设置在类或属性上，如果传递的数据是时间格式的，则按照格式转换为指定的时间。否则保持原字符串数据

```
@JsonFormat(pattern = "yyy-MM-dd", timezone = "GMT+8")
```

如果该注解声明在类上，相当于给类的所有属性都设置了该注解



### @JsonInclude

该注解默认为 `ALWAYS` 表示序列化全部字段，即使属性值为 `null` ，

如果设置为 `NON_ABSENT` 则表示过滤掉所有为空的属性值

```
@JsonInclude(JsonInclude.Include.NON_ABSENT)
```

