# Mybatis-Plus xml

使用 `Mybatis` 的注解方式，主要是来完成一些简单的增删改查功能。如果需要实现复杂的 `SQL` 功能，建议使用XML来配置映射语句，也就是将 `SQL` 语句写在 `XML` 配置文件中。



## 基本结构

xml文件的基本结构

```xml
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="">
    
</mapper>
```



文件对应关系

```xml
<mapper namespace="mapper接口的路径">
    <select id="mapper接口中的方法" resultType="实体类的路径">
        在这里写SQL语句
    </select>
</mapper>
```

![image-20240221141526949](./image/image-20240221141526949.png)



## 动态SQL

### if

用于判断条件是否成立，使用 `test` 属性进行条件判断，如果条件为 `true` 则拼接 `SQL`

```xml
<select id="list" resultType="yang.domain.Student">
    select * from tb_student
    <where>
        <if test="cid != null">
            c_id = #{cid}
        </if>

        <if test="sname != null">
            and sname like concat('%', #{sname} ,'%')
        </if>
    </where>
</select>
```
```java
public void list(int cid, String sname) {
    List<Student> list = studentMapper.list(cid, sname);
}
```



上述代码使用的 `xml` 方式，而下述代码使用的注解方式，实现效果是等价的。

一般情况下比较复杂的场景适合 `xml` 方式，简单的场景可以使用注解方式

```java
public void list(int cid, String sname) {
    QueryWrapper<Student> queryWrapper = new QueryWrapper<>();

    if (cid != null) {
        queryWrapper.eq("c_id", cid);
    }

    if (sname != null) {
        queryWrapper.like("sname", "%" + sname + "%");
    }

	List<Student> list =  studentMapper.selectList(queryWrapper);
}
```



### where

可以发现上述代码中的 `where` 用 `<>` 套起来了，这样做可以防止SQL的语法错误



比如当第一个 `if` 条件不成立，而第二个就会拼接 `sql` 代码，但它最开始处有一个 `and` 关键字，相当于执行了：

```sql
select * from tb_student where and sname like concat('%', '阳' ,'%')
```

那么程序肯定会报错 `sql` 语法的异常错误



再或者说当条件都不成立时候，那么相当于执行了：

```sql
select * from tb_student where
```

那么自然也会报语法错误



为了解决这些问题，我们最好给 `where` 加上 `<>` ，这样当第一个条件不成立时候，第二个条件的 `and` 会自动省略。并且当条件都不成立时也会自动忽略 `where` 关键字。



### set

可以通过 `if` 进行判断，如果有值就更新对应的值，没有值就不拼接 `sql`，不让他更新

```java
public void edit(){
    Student student = new Student();
    student.setSid(35);
    student.setSname("阳");
    student.setAge(25);

    studentMapper.edit(student);
}
```

```xml
<update id="edit" parameterType="yang.domain.Student">
    update tb_student
    set
    <if test="sname != null">
        sname = #{sname},
    </if>

    <if test="age != null">
        age = #{age},
    </if>

    <if test="gender != null">
        gender = #{gender},
    </if>

    <if test="birthday != null">
        birthday = #{birthday},
    </if>

    <if test="cid != null">
        cid = #{cid}
    </if>
    where s_id = #{sid}
</update>
```

但是这样的话会存在一个问题，如果我们只更新了 `sname` 和 `age` ，相当于执行了：

```sql
update tb_student set sname = '阳', age = 25, where s_id = 35;
```

在 `age = 25` 的后面有个 `,` 从而会导致语法错误

这时可以给 `set` 用 `<>` 包裹起来，就可以解决这个问题，这样当只更新条件时候，会自动把最后一个字段的 `,` 去掉，从而避免了语法错误



### foreach

执行一些批量操作

```xml
<foreach collection="集合名称" item="集合遍历出来的元素/项" separator="每一次遍历使用的分隔符" 
         open="遍历开始前拼接的片段" close="遍历结束后拼接的片段">
</foreach>
```

批量删除

```java
public void del(){
    studentMapper.del(Arrays.asList(52, 53, 54));
}
```

```xml
<delete id="del" parameterType="yang.domain.Student">
    delete
    from tb_student
    where s_id in
    <foreach collection="ids" item="id" separator="," open="(" close=")">
        #{id}
    </foreach>
</delete>
```



### sql & include

在 `xml` 映射文件中配置的 `SQL`，有时可能会存在很多重复的片段，此时就会产生代码冗余的现象，我们可以使用 `sql & include` 来对重复的代码片段进行抽取，将其通过 `<sql>` 标签封装到一个 `SQL` 片段，然后再通过 `<include>` 标签进行引用

封装SQL

```xml
<sql id="commonSelect">
 	select * from tb_student
</sql>
```

引用SQL

```xml
<select id="list" resultType="yang.domain.Student">
    <include refid="commonSelect"/>
    
    <where>
        <if test="cid != null">
            c_id = #{cid}
        </if>

        <if test="sname != null">
            and sname like concat('%', #{sname} ,'%')
        </if>
    </where>
</select>
```

