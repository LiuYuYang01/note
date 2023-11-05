# MySQL

## 初识

```mysql
# 登录
mysql -uroot -p

# 退出
exit

# 查看所有数据库
show databases;

# 查看当前数据库
select database;

# 创建数据库
create database 数据库名称 charset=utf8;

# 切换数据库
use 数据库名称;

# 删除数据库
drop database 数据库名称;

# 注释
-- 注释的内容
```



## 数据表

### 创建

创建数据表

```mysql
create table 表名(字段 数据类型 数据约束,字段 数据类型 数据约束, ... );
```



**示例**

```mysql
	create table stu(
        id int unsigned primary key not null auto_increment, 
        name varchar(20) not null, 
        age tinyint default 0, 
        height decimal(5,2), 
        gender enum('男', '女', '保密')
    );
```



### 查看

查看数据库中所有数据表，注意必须先 `use 需要查看的数据库` 切换到需要查看的数据库才能查看他的数据表

```mysql
-- 查看所有数据表
show tables;

-- 查看数据表结构
desc cate;

-- 查看数据表信息
show create table cate;
```



### 删除

删除数据表

```mysql
drop table 表名;
```



### 修改

**新增字段**

在 `cate` 数据表中新增一个 `status` 字段

```mysql
alter table cate add status VARCHAR(10)
```



**删除字段**

将 `cate` 数据表中的 `url` 字段删除

```sql
alter table cate drop column url;
```



**修改字段**

将 `cate` 数据表名称修改为 `cates`

```sql
rename table cate to cates;
```



修改 `cate` 数据表的 `name` 字段值为 `varbinary(30)`

```sql
alter table cate modify name varchar(30)
```



将数据表的 `name` 字段名称更改为 `names` 并将字段值更改为 `varbinary(50)`

```sql
alter table cate change name names varchar(50);
```



**修改字段约束类型**

```sql
-- 添加非空约束
alter table 表名 modify 列名 数据类型 not null;

-- 移除非空约束
alter table 表名 modify 列名 数据类型 null;

-- 添加唯一约束
alter table 表名 add constraint 约束名 unique (列名);

-- 移除唯一约束
alter table 表名 drop index 索引名;

-- 添加主键约束
alter table 表名 add constraint 约束名 primary key (列名);

-- 移除主键约束
alter table 表名 drop primary key;

-- 添加外键约束
alter table 子表名 add constraint 约束名 foreign key (列名) references 父表名(父表列名);

-- 移除外键约束
alter table 子表名 drop foreign key 约束名;

-- 修改字段默认值
alter table 表名 alter column 列名 set default 新默认值;

-- 移除字段默认值
alter table 表名 alter column 列名 drop default;
```



## 字段

### 字段类型

常用的 `MySQL` 字段类型

| 大分类——————— | 类型        | 描述                                                         |
| ------------- | ----------- | ------------------------------------------------------------ |
| 数值类型      | int         | 大整数                                                       |
|               | float       | 浮点数类型                                                   |
|               | double      | 浮点数类型                                                   |
| 字符类型      | varchar(30) | 可变长度字符串：如果只用了 5 个字符，实际上只使用了 5 个字符的存储空间 |
|               | char(10)    | 固定长度字符串：如果只用了 5 个字符，他会把剩余的都作为空格填充，实际上使用了10个字符的存储空间 |
| 日期类型      | date        | 日期：年月日                                                 |
|               | datetime    | 日期时间：年月日时分秒                                       |
|               | timestamp   | 时间戳                                                       |



**练习：创建一个数据表**

1. id：标识，数字类型
2. name：姓名，字符串类型，长度限制最多30个字符
3. gender：性别，字符串类型，长度限制为1个字符
4. age：年龄，数值类型
5. birthday：生日，日期类型，格式YYYY-mm-dd 

~~~sql
create table student(
    id int comment '标识',
    name varchar(30) comment '姓名',
    gender char(1) comment '性别',
    age int comment '年龄',
    birthday date
);
~~~



### 字段约束



## 增删改

### 增加

**单个增加**

```mysql
# 一次增加一个数据
insert into stu values(0, '关羽', 18, 165.3, '男');

# 只添加name和age字段的数据,其他的字段使用默认值,或null
insert into stu(name, age) values ('刘皇叔', 19);
```



**多个增加**

```mysql
# 一次增加多个
insert into stu values(0, '关羽', 18, 165.3, '男'),(0, '关羽', 18, 165.3, '男'),(0, '关羽', 18, 165.3, '男'),(0, '关羽', 18, 165.3, '男'),(0, '关羽', 18, 165.3, '男')
```



**示例**

```mysql
mysql> insert into stu values(0, '关羽', 18, 165.3, '男'),(0, '关羽', 18, 165.3, '男'),(0, '关羽', 18, 165.3, '男'),(0, '关羽', 18, 165.3, '男'),(0, '关羽', 18, 165.3, '男');
Query OK, 5 rows affected (0.00 sec)
Records: 5  Duplicates: 0  Warnings: 0

mysql> select * from stu;
+----+--------+------+--------+--------+
| id | name   | age  | height | gender |
+----+--------+------+--------+--------+
|  1 | 张飞   |   14 | 168.00 | 男     |
|  2 | 关羽   |   20 | 185.00 | 保密   |
|  3 | 刘皇叔 |   19 | 175.00 | 男     |
|  4 | 赵云   |   23 | 176.00 | 女     |
| 15 | 关羽   |   19 |   NULL | NULL   |
| 17 | 张飞   |    0 | 170.00 | NULL   |
| 18 | 关羽   |    0 | 180.00 | NULL   |
| 19 | 刘皇叔 |    0 | 175.00 | NULL   |
| 20 | 赵云   |    0 | 176.00 | NULL   |
| 21 | 关羽   |   18 | 165.30 | 男     |
| 22 | 关羽   |   18 | 165.30 | 男     |
| 23 | 关羽   |   18 | 165.30 | 男     |
| 24 | 关羽   |   18 | 165.30 | 男     |
| 25 | 关羽   |   18 | 165.30 | 男     |
| 26 | 关羽   |   18 | 165.30 | 男     |
+----+--------+------+--------+--------+
15 rows in set (0.00 sec)
```



### 删除

```mysql
# 删除id=16的数据
delete from stu where id = 16;
```



```mysql
# 删除id>=18的数据
delete from stu where id >= 18;
```



### 修改

```mysql
# 将所有数据age改为20
update set set age=20;

# 将id=1的数据name改成张飞
update stu set name='张飞' where id =1;

# 将id=2的数据age改成20同时把身高改成185.00
update stu set age=20, height=185 where id = 2;
```



## 查询

```sql
# 查询全部数据
select * from stu;

# 只查询name、age字段的数据
select name,age from stu;

# 查询为null的数据
select * from stu where email is null;
# 查询不为null的数据
select * from stu where email  is not null;

# 查询为空的数据
select * from stu where email = '';
# 查询不为空的数据
select * from stu where email != '';

# 去除name字段重复的数据
select distinct name from stu

# 字段取别名
select name as 姓名, age as 年龄 from users;
select name 姓名, age 年龄 from stu;
```



### 条件查询

```mysql
# 查询id<3的数据
select * from stu where id < 3;

# 查询id=3的数据
select * from stu where id = 3;

# 查询id!=3的数据
select * from stu where id != 3;
select * from stu where id <> 3;

# 查询id>3并且age<20的数据
select * from stu where id > 3 && age < 20;
select * from stu where id > 3 and age < 20

# 查询id<3或者age>15的数据
select * from stu where id < 3 || age > 20;
select * from stu where id < 3 or age > 20;

# 查询id=3到20的数据
select * from stu where id between 3 and 20;
```



### 模糊查询

`%` 表示多个，而 `_` 表示单个任意字符

```sql
# 查询所有姓关的数据
select * from stu where name like '关%';

# 查询所有包含关的数据
select * from stu where name like '%关%';

# 查询所有名字为两个字的数据
select * from stu where name like '__';

# 查询所有姓关且名为两个字的数据
select * from stu where name link '关_';
select * from stu where name link '关' and name like '__';

# 查询不包含A的数据
select * from stu where name not like '%A%'
```



### 分页查询

```sql
# 查询5条数据
select * from stu limit 5;

# 查询id大于10之后的5个数据
select * from stu where id >= 10 limit 5;
```



```sql
# 每页显示5条数据
select * from stu limit 0, 5; # 第一页
select * from stu limit 5, 5; # 第二页
select * from stu limit 10, 5; # 第三页
```



### 分组查询

```sql
# 查询每个年龄的出现次数
select id, age, count(*) as 数量 from stu group by age;

# 查询每个年龄的出现次数, 最少要出现3次才能被统计到
select id, age, count(1) as 数量 from stu group by age having count(1) > 3;

# 查询每个年龄的平均值
select id, age, avg(*) as 数量 from stu group by age;
```



## 数据排序

``` sql
# 根据age进行排序
select * from stu order by age asc;  # 小到达
select * from stu order by age desc; # 大到小

# 查询为男的数据后从大到小排序
select * from stu where gender='男' order by id desc;

# 先根据年龄从大到小排序，在根据id从小到大排序
select * from stu where order by age desc, id asc;
```



## 聚合函数

### 长度

```sql
# 求出name长度等于3的数据
select * from stu where length(name) = 3;
select * from stu where like "___";
```



### 求和

```mysql
# 求所有薪资的总和
select sum(sal) 所有薪资的和 from sal;

# 求id小于5的所有薪资的和
select sum(sal) from sal where id < 5;
```



### 平均值

```sql
# 求所有薪资的平均值
select avg(sal) from emp;

# 求id为3、4、5的平均薪资
select avg(sal) from emp where id in (3,4,5);
```



四舍五入

```sql
# 不管是不是整数 保留2位小数
select ename, format(sal / 30, 2) as 日薪 from emp;

# 如果为整数就不保留小数
select ename, round(sal / 30, 2) as 日薪 from emp;
```



### 大小值

```sql
# 求最大的年龄的数据
select max(age) 年龄最大 from stu;

# 求最小的年龄的数据
select min(age) 年龄最小 from stu;

# 求出年龄最大的是谁 多少岁
select * from stu where age = (select max(age) from stu);
```



### 统计

求出当前数据表中有多少数据

```sql
# 统计所有comm字段不为null的所有数据
select count(e.comm) as count from emp e;
select count(*) as count from emp e where e.comm is not null;

# 统计所有comm字段为0的数据
select count(*) as count from emp e where e.comm = 0;

# 统计所有comm字段为null或0的数据
select count(*) as count from emp e where e.comm is null || e.comm = 0;
```



### having

`having` 是 `SQL` 中的一个条件筛选语句，它可以用来筛选基于聚合函数后的结果。和 `where` 不同，`where` 语句在聚合函数执行前过滤掉数据行，而 `having` 在聚合函数执行后对分组结果进行筛选

**简单来说：** `where` 用于处理过滤前的数据，而 `having` 用于处理聚合函数后的数据



**示例：** 查询所有手机品牌数量大于 `3` 的编号、名称、数量

```sql
select p.pid, p.productName, count(*) as count from tab_product p group by p.productName having count(*) >= 3;
```



**示例：** 查询所有课程的平均成绩并筛选出高于 `60` 低于 `80` 的课程数据

```sql
select m.cid, c.name, round(avg(m.score), 0) as avgScore
from mark m, course c
where m.cid = c.id group by m.cid
# 错误：where m.cid = c.id && avg(m.score) between 60 and 80 group by m.cid
# 使用having可以对avg聚合函数的结果进行筛选
having avg(m.score) between 60 and 80
order by avgScore desc;
```



## 练习

查询用户的信息，年龄降序，身高升序排列

```sql
select * from user order by age desc, height asc
```



查询所有姓刘、张、李的用户数据

```sql
select * from emp where ename like '刘%' or ename like '张%' or ename like '李%';
```



找出姓名3个字的用户数据

```sql
select * from emp where length(ename) = 3;
```



