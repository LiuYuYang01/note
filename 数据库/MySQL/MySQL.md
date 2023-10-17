# MySQL

## 初识

### 登录

```mysql
mysql -uroot -p
```



### 退出

```mysql
exit
```



### 查看

查看所有的数据库

```mysql
show databases; //数据库
```



### 当前

查看当前的数据库

```sql
select database();
```



### 创建

创建一个数据库

```mysql
create database 数据库名称 charset=utf8
```



### 注释

```mysql
-- 注释的内容
```



### 切换

切换到指定的数据库

```mysql
use 需要切换到的数据库名称
```



### 删除

删除数据库

```mysql
drop database 需要删除的数据库名称
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



### 字段

**添加字段**

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

修改 `cate` 数据表的 `name` 字段值为 `varbinary(30)`

```sql
alter table cate modify name varchar(30)
```



将数据表的 `name` 字段名称更改为 `names` 并将字段值更改为 `varbinary(50)`

```sql
alter table cate change name names varchar(50);
```



将 `cate` 数据表名称修改为 `cates`

```sql
rename table cate to cates;
```



## 字段

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



## 增删改

### 增加

**单个增加**

一次增加一个数据

```mysql
insert into stu values(0, '关羽', 18, 165.3, '男');
```



只添加 name 和 age 字段的数据,其他的字段使用默认值,或者 null 值

```mysql
insert into stu(name, age) values ('刘皇叔', 19);
```



**多个增加**

一次增加多个

```mysql
insert into stu values(0, '关羽', 18, 165.3, '男'),(0, '关羽', 18, 165.3, '男'),(0, '关羽', 18, 165.3, '男'),(0, '关羽', 18, 165.3, '男'),(0, '关羽', 18, 165.3, '男')
```



**示例**

```mysql
mysql> insert into stu values(0, '关羽', 18, 165.3, '男'),(0, '关羽', 18, 165.3, '男'),(0, '关羽', 18, 165.3, '男'),(0, '关羽', 18, 165.3, '男'),(0, '关羽', 18, 165.3, '男')
    -> ;
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

删除id=16的数据

```mysql
mysql> delete from stu where id = 16;
Query OK, 1 row affected (0.00 sec)

mysql> select * from stu;
+----+--------+------+--------+--------+
| id | name   | age  | height | gender |
+----+--------+------+--------+--------+
|  1 | 张飞   |   14 | 168.00 | 男     |
|  2 | 关羽   |   20 | 185.00 | 保密   |
|  3 | 刘皇叔 |   19 | 175.00 | 男     |
|  4 | 赵云   |   23 | 176.00 | 女     |
| 15 | 关羽   |   19 |   NULL | NULL   |
+----+--------+------+--------+--------+
5 rows in set (0.00 sec)
```



把id大于等于18的数据删除

```mysql
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

mysql> delete from stu where id >= 18;
Query OK, 9 rows affected (0.01 sec)

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
+----+--------+------+--------+--------+
6 rows in set (0.00 sec)
```



### 修改

查看数据

```mysql
mysql> select * from stu;
+----+--------+------+--------+--------+
| id | name   | age  | height | gender |
+----+--------+------+--------+--------+
|  1 | 张~飞  |   14 | 168.00 | 男     |
|  2 | 关羽   |   16 | 180.00 | 保密   |
|  3 | 刘皇叔 |   19 | 175.00 | 男     |
|  4 | 赵云   |   23 | 176.00 | 女     |
| 15 | 关羽   |   19 |   NULL | NULL   |
| 16 | 关~羽  |   21 |   NULL | NULL   |
+----+--------+------+--------+--------+
6 rows in set (0.00 sec)
```



将id为1的数据 name 张~飞 改成 张飞

```mysql
mysql> update stu set name='张飞' where id =1;
Query OK, 1 row affected (0.01 sec)
Rows matched: 1  Changed: 1  Warnings: 0

mysql> select * from stu;
+----+--------+------+--------+--------+
| id | name   | age  | height | gender |
+----+--------+------+--------+--------+
|  1 | 张飞   |   14 | 168.00 | 男     |
|  2 | 关羽   |   16 | 180.00 | 保密   |
|  3 | 刘皇叔 |   19 | 175.00 | 男     |
|  4 | 赵云   |   23 | 176.00 | 女     |
| 15 | 关羽   |   19 |   NULL | NULL   |
| 16 | 关~羽  |   21 |   NULL | NULL   |
+----+--------+------+--------+--------+
6 rows in set (0.00 sec)
```



将id为2的数据 age 改成20 同时 给身高改成185.00

```mysql
mysql> update stu set age=20,height=185 where id = 2;
Query OK, 1 row affected (0.00 sec)
Rows matched: 1  Changed: 1  Warnings: 0

mysql> select * from stu;
+----+--------+------+--------+--------+
| id | name   | age  | height | gender |
+----+--------+------+--------+--------+
|  1 | 张飞   |   14 | 168.00 | 男     |
|  2 | 关羽   |   20 | 185.00 | 保密   |
|  3 | 刘皇叔 |   19 | 175.00 | 男     |
|  4 | 赵云   |   23 | 176.00 | 女     |
| 15 | 关羽   |   19 |   NULL | NULL   |
| 16 | 关~羽  |   21 |   NULL | NULL   |
+----+--------+------+--------+--------+
6 rows in set (0.00 sec)
```



修改全部

```mysql
mysql> select * from stu;
+----+------+-----+--------+--------+
| id | name | age | height | gender |
+----+------+-----+--------+--------+
|  1 | 刘备 |  18 | 165.00 | 男     |
|  2 | 关羽 |  18 | 165.00 | 女     |
|  3 | 张飞 |  18 | 165.00 | 女     |
|  5 | 关羽 |  18 | 165.00 | 女     |
|  6 | 赵云 |  25 | 180.50 | 男     |
|  7 | 黄忠 |  25 | 170.00 | 男     |
|  8 | 赵云 |  25 | 180.50 | 男     |
+----+------+-----+--------+--------+
7 rows in set (0.03 sec)

mysql> update stu set height=180.00;
Query OK, 7 rows affected (0.00 sec)
Rows matched: 7  Changed: 7  Warnings: 0

mysql> select * from stu;
+----+------+-----+--------+--------+
| id | name | age | height | gender |
+----+------+-----+--------+--------+
|  1 | 刘备 |  18 | 180.00 | 男     |
|  2 | 关羽 |  18 | 180.00 | 女     |
|  3 | 张飞 |  18 | 180.00 | 女     |
|  5 | 关羽 |  18 | 180.00 | 女     |
|  6 | 赵云 |  25 | 180.00 | 男     |
|  7 | 黄忠 |  25 | 180.00 | 男     |
|  8 | 赵云 |  25 | 180.00 | 男     |
+----+------+-----+--------+--------+
7 rows in set (0.06 sec)
```



## 查询

 查询 `stu` 数据表中所有的数据

```mysql
mysql> select * from stu
    -> ;
+----+--------+------+--------+--------+
| id | name   | age  | height | gender |
+----+--------+------+--------+--------+
|  1 | 张飞   |   14 | 170.00 | 男     |
|  2 | 关羽   |   16 | 180.00 | 保密   |
|  3 | 刘皇叔 |   19 | 175.00 | 男     |
| 14 | 赵云   |   23 | 176.00 | 女     |
+----+--------+------+--------+--------+
4 rows in set (0.00 sec)
```



查询stu数据表中name跟age数据：`select name,age from stu`

```mysql
mysql> select name,age from stu
    -> ;
+--------+------+
| name   | age  |
+--------+------+
| 张飞   |   14 |
| 关羽   |   16 |
| 刘皇叔 |   19 |
| 赵云   |   23 |
+--------+------+
4 rows in set (0.00 sec)
```



查询stu数据表中name跟age数据并给他们分别取个别名

`select name as 姓名,age as 年龄 from stu;`

```mysql
mysql> select name as 姓名,age as 年龄 from stu;
+--------+------+
| 姓名   | 年龄 |
+--------+------+
| 张飞   |   14 |
| 关羽   |   16 |
| 刘皇叔 |   19 |
| 赵云   |   23 |
+--------+------+
4 rows in set (0.00 sec)
```

as也可以省略为：`select name 姓名,age 年龄 from stu;`



只查询stu数据表中的身高：`select height 身高 from stu`

```mysql
mysql> select height 身高 from stu
    -> ;
+--------+
| 身高   |
+--------+
| 170.00 |
| 180.00 |
| 175.00 |
| 176.00 |
+--------+
4 rows in set (0.00 sec)
```



让JavaScript数据库中stu数据表中的age从小到大进行排序

`select * from stu order by age asc;`

```mysql
mysql> select * from stu order by age asc;
+----+--------+------+--------+--------+
| id | name   | age  | height | gender |
+----+--------+------+--------+--------+
|  1 | 张飞   |   14 | 170.00 | 男     |
|  2 | 关羽   |   16 | 180.00 | 保密   |
|  3 | 刘皇叔 |   19 | 175.00 | 男     |
| 14 | 赵云   |   23 | 176.00 | 女     |
+----+--------+------+--------+--------+
4 rows in set (0.00 sec)
```



让JavaScript数据库中stu数据表中的age从大到小进行排序

```mysql
mysql> select * from stu order by age desc;
+----+--------+------+--------+--------+
| id | name   | age  | height | gender |
+----+--------+------+--------+--------+
| 14 | 赵云   |   23 | 176.00 | 女     |
|  3 | 刘皇叔 |   19 | 175.00 | 男     |
|  2 | 关羽   |   16 | 180.00 | 保密   |
|  1 | 张飞   |   14 | 170.00 | 男     |
+----+--------+------+--------+--------+
4 rows in set (0.00 sec)
```

只要是数字类型都可以进行排序，如ID：`select * from stu order by id desc;`



### 条件查询

查看数据

```mysql
mysql> select * from stu;
+----+--------+------+--------+--------+
| id | name   | age  | height | gender |
+----+--------+------+--------+--------+
|  1 | 张~飞  |   14 | 168.00 | 男     |
|  2 | 关羽   |   16 | 180.00 | 保密   |
|  3 | 刘皇叔 |   19 | 175.00 | 男     |
|  4 | 赵云   |   23 | 176.00 | 女     |
| 15 | 关羽   |   19 |   NULL | NULL   |
| 16 | 关~羽  |   21 |   NULL | NULL   |
+----+--------+------+--------+--------+
6 rows in set (0.00 sec)
```



查询id小于3的数据：`select * from stu where id < 3`

```mysql
mysql> select * from stu where id < 3;
+----+-------+------+--------+--------+
| id | name  | age  | height | gender |
+----+-------+------+--------+--------+
|  1 | 张~飞 |   14 | 168.00 | 男     |
|  2 | 关羽  |   16 | 180.00 | 保密   |
+----+-------+------+--------+--------+
2 rows in set (0.00 sec)
```



查询id等于3的数据：`select * from stu where id = 3`

```mysql
mysql> select * from stu where id = 3;
+----+--------+------+--------+--------+
| id | name   | age  | height | gender |
+----+--------+------+--------+--------+
|  3 | 刘皇叔 |   19 | 175.00 | 男     |
+----+--------+------+--------+--------+
1 row in set (0.01 sec)
```



**AND**

查询id大于3并且age大于20的数据：`select * from stu where id > 3 && age > 20`

```mysql
mysql> select * from stu where id > 3 && age > 20;
+----+-------+------+--------+--------+
| id | name  | age  | height | gender |
+----+-------+------+--------+--------+
|  4 | 赵云  |   23 | 176.00 | 女     |
| 16 | 关~羽 |   21 |   NULL | NULL   |
+----+-------+------+--------+--------+
2 rows in set, 1 warning (0.00 sec)

也可以用and表示&&号，写法：

mysql> select * from stu where id > 3 and age > 20;
+----+-------+------+--------+--------+
| id | name  | age  | height | gender |
+----+-------+------+--------+--------+
|  4 | 赵云  |   23 | 176.00 | 女     |
| 16 | 关~羽 |   21 |   NULL | NULL   |
+----+-------+------+--------+--------+
2 rows in set (0.00 sec)
```



**OR**

查询id小于3或者age大于15的数据

```mysql
mysql> select * from stu where id < 3 || age > 20;
+----+-------+------+--------+--------+
| id | name  | age  | height | gender |
+----+-------+------+--------+--------+
|  1 | 张~飞 |   14 | 168.00 | 男     |
|  2 | 关羽  |   16 | 180.00 | 保密   |
|  4 | 赵云  |   23 | 176.00 | 女     |
| 16 | 关~羽 |   21 |   NULL | NULL   |
+----+-------+------+--------+--------+
4 rows in set, 1 warning (0.00 sec)

也可以用or表示||号，写法：

mysql> select * from stu where id < 3 or age > 20;
+----+-------+------+--------+--------+
| id | name  | age  | height | gender |
+----+-------+------+--------+--------+
|  1 | 张~飞 |   14 | 168.00 | 男     |
|  2 | 关羽  |   16 | 180.00 | 保密   |
|  4 | 赵云  |   23 | 176.00 | 女     |
| 16 | 关~羽 |   21 |   NULL | NULL   |
+----+-------+------+--------+--------+
4 rows in set, 1 warning (0.00 sec)
```



### 去重查询

去除 age 中重复的数据

```mysql
mysql> select distinct age from stu;
+------+
| age  |
+------+
|   14 |
|   16 |
|   19 |
|   23 |
|   21 |
+------+
5 rows in set (0.00 sec)
```



查询 gender 为null的数据：`select * from stu where gender is null`

```mysql
mysql> select * from stu where gender is null;
+----+-------+------+--------+--------+
| id | name  | age  | height | gender |
+----+-------+------+--------+--------+
| 15 | 关羽  |   19 |   NULL | NULL   |
| 16 | 关~羽 |   21 |   NULL | NULL   |
+----+-------+------+--------+--------+
2 rows in set (0.00 sec)
```



### 模糊查询

查询数据表中所有姓关的学生,不限字数：`select * from stu where name like '关%'`

```mysql
mysql> select * from stu where name like '关%';
+----+-------+------+--------+--------+
| id | name  | age  | height | gender |
+----+-------+------+--------+--------+
|  2 | 关羽  |   16 | 180.00 | 保密   |
| 15 | 关羽  |   19 |   NULL | NULL   |
| 16 | 关~羽 |   21 |   NULL | NULL   |
+----+-------+------+--------+--------+
3 rows in set (0.00 sec)
```



查询数据表中所有名字是两个字的学生：`select * from stu where name like '__'`

```mysql
mysql> select * from stu where name like '__';
+----+------+------+--------+--------+
| id | name | age  | height | gender |
+----+------+------+--------+--------+
|  2 | 关羽 |   16 | 180.00 | 保密   |
|  4 | 赵云 |   23 | 176.00 | 女     |
| 15 | 关羽 |   19 |   NULL | NULL   |
+----+------+------+--------+--------+
3 rows in set (0.00 sec)
```



查询数据表中所有姓关并且只有两个字的学生：`select * from stu where bame like '关_'`

```mysql
mysql> select * from stu where name like '关_';
+----+------+------+--------+--------+
| id | name | age  | height | gender |
+----+------+------+--------+--------+
|  2 | 关羽 |   16 | 180.00 | 保密   |
| 15 | 关羽 |   19 |   NULL | NULL   |
+----+------+------+--------+--------+
2 rows in set (0.00 sec)
```



## 聚合函数

查看数据

```mysql
mysql> select * from stu;
+----+--------+------+--------+--------+
| id | name   | age  | height | gender |
+----+--------+------+--------+--------+
|  1 | 张~飞  |   14 | 168.00 | 男     |
|  2 | 关羽   |   16 | 180.00 | 保密   |
|  3 | 刘皇叔 |   19 | 175.00 | 男     |
|  4 | 赵云   |   23 | 176.00 | 女     |
| 15 | 关羽   |   19 |   NULL | NULL   |
| 16 | 关~羽  |   21 |   NULL | NULL   |
+----+--------+------+--------+--------+
6 rows in set (0.00 sec)
```



### 求和

求前三个数据的 age 总和： `select sum(age) from stu where id <= 3`

```mysql
mysql> select sum(age) from stu where id <= 3;
+----------+
| sum(age) |
+----------+
|       49 |
+----------+
1 row in set (0.00 sec)

取别名：

mysql> select sum(age) as 年龄和  from stu where id <= 3;
+--------+
| 年龄和 |
+--------+
|     49 |
+--------+
1 row in set (0.00 sec)
```



### 平均值

求前三个数据的 height 平均值：`select avg(height) from stu where id <= 3`

```mysql
mysql> select avg(height) from stu where id <= 3;
+-------------+
| avg(height) |
+-------------+
|  174.333333 |
+-------------+
1 row in set (0.00 sec)
```



写法二：求id3、4的平均值

```mysql
mysql> select avg(height) from stu where id in (3,4);
+-------------+
| avg(height) |
+-------------+
|  175.500000 |
+-------------+
1 row in set (0.00 sec)
```



### 总人数

求出当前数据表中有多少数据：`select count(id) from stu`

```mysql
mysql> select count(*) as 总数据有  from stu;
+----------+
| 总数据有 |
+----------+
|        6 |
+----------+
1 row in set (0.01 sec)
```



如果数据为null，则统计不到：`select count(gender) from stu`

```mysql
mysql> select count(gender) from stu;
+---------------+
| count(gender) |
+---------------+
|             4 |
+---------------+
1 row in set (0.00 sec)
```



## 数据排序

查询 height 数据并且 从小到大排序 / 从大到小

```mysql
-- asc 小到大排序
-- 默认就是asc 从小到大排序 可以省略不写
mysql> select * from stu order by height asc;
+----+--------+------+--------+--------+
| id | name   | age  | height | gender |
+----+--------+------+--------+--------+
|  1 | 张飞   |   14 | 168.00 | 男     |
|  3 | 刘皇叔 |   19 | 175.00 | 男     |
|  4 | 赵云   |   23 | 176.00 | 女     |
|  2 | 关羽   |   20 | 185.00 | 保密   |
+----+--------+------+--------+--------+
4 rows in set (0.00 sec)

-- desc 从大到小
mysql> select * from stu order by height desc;
+----+--------+------+--------+--------+
| id | name   | age  | height | gender |
+----+--------+------+--------+--------+
|  2 | 关羽   |   20 | 185.00 | 保密   |
|  4 | 赵云   |   23 | 176.00 | 女     |
|  3 | 刘皇叔 |   19 | 175.00 | 男     |
|  1 | 张飞   |   14 | 168.00 | 男     |
+----+--------+------+--------+--------+
4 rows in set (0.00 sec)
```



查找出所有 gender 为男的数据，然后以id进行倒序

```mysql
mysql> select * from stu where gender='男';
+----+--------+------+--------+--------+
| id | name   | age  | height | gender |
+----+--------+------+--------+--------+
|  1 | 张飞   |   14 | 168.00 | 男     |
|  3 | 刘皇叔 |   19 | 175.00 | 男     |
+----+--------+------+--------+--------+
2 rows in set (0.01 sec)

mysql> select * from stu where gender='男' order by id desc;
+----+--------+------+--------+--------+
| id | name   | age  | height | gender |
+----+--------+------+--------+--------+
|  3 | 刘皇叔 |   19 | 175.00 | 男     |
|  1 | 张飞   |   14 | 168.00 | 男     |
+----+--------+------+--------+--------+
2 rows in set (0.00 sec)
```





## 扩展

判断 增 删 改是否完成，`affectedRows` 大于 `1` 就证明操作完成

 
