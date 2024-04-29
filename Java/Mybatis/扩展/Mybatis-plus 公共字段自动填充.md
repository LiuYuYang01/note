# 公共字段自动填充

项目开发过程中一定会遇到创建与修改时间，再复杂一点就是创建用户与修改的用户。默认情况下我们需要手动给他们赋值，但这样的话每次新增和修改都要写一堆重复的代码，产生了代码冗余。



所以可以使用 `Mybatis-plus` 的字段填充功能实现这个功能



首先需要在 `JavaBean` 的属性中设置 `@TableField` 注解，他的属性值有：

|     属性      |        作用        |
| :-----------: | :----------------: |
|    DEFAULT    |   默认值，不填充   |
|    INSERT     |    新增时候填充    |
|    UPDATE     |    修改时候填充    |
| INSERT_UPDATE | 新增或修改时候填充 |


 **代码示例：** 

```java
@Data
public class BaseEntity implements Serializable {
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 创建人id
     */
    @TableField(fill = FieldFill.INSERT)
    private Long createUser;

    /**
     * 更新人id
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;
}
```



然后我们可以实现 `Mybatis-plus` 的 `MetaObjectHandler` 接口，重写 `insertFill` 和 `updateFill` 方法，最后设置好填充的数据就可以了，这里为了方便演示，所以将填充的数据写死。

```java
package liuyuyang.net.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
public class MybatisPlusMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        System.out.println("插入填充");

        // 插入
        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, "createUser", Long.class, 100L);
        this.strictInsertFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, "updateUser", Long.class, 100L);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        System.out.println("更新填充");

        // 更新
        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
        this.strictUpdateFill(metaObject, "updateUser", Long.class, 100L);
    }
}
```

需要注意的是填充类型必须要和 `JavaBean` 以及数据库一致，否则会导致填充失败



到这一步就完成了 `Mybatis-plus` 公共字段自动填充功能，当对应的 `JavaBean` 新增或修改时就会自动触发对应的填充方法，实现自动填充功能