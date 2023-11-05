# MySQL

## 多表关系

### 一对多

**创建数据表**

```sql
# 创建班级表（主表）
create table class(
    cid int primary key, -- 设置为主键
    cname varchar(20)
);

# 创建学生表（从表）
create table student(
    sid int,
    cname varchar(20),
    class_id int -- 设置为外键
);
```



**建立多表之间的关系**

```sql
# alter table 从表表名 add constraint [外键名称] foreign key(外键字段) references 主表表名(主键字段)
alter table student add constraint student_fk foreign key (class_id) references class(cid);
```



**填充数据**

```sql
# 班级（主表）
insert into class values(1, '1班');
insert into class values(2, '2班');

# 学生（从表）
insert into student values(1, 'zs', 1);
insert into student values(2, 'zs', 1);
```



**注意事项**

```sql
# 失败：主表中不存在cid=3的数据
insert into student values(3, 'zs', 3);

# 失败：不能删除已经被从表使用的数据
delete from class where cid = 1;
```



### 多对多

```sql
# 创建学生表（主表）
create table student(
    sid varchar(10) primary key, -- 设置为主键
    sname varchar(20)
);

# 创建课程表（主表）
create table course(
    cid varchar(10) primary key, -- 设置为主键
    cname varchar(20)
);

# 中间表
create table midde(
    student_id varchar(10), -- 设置为外键
    course_id varchar(10) -- 设置为外键
);

# 建立多对多的关系
alter table midde add constraint student_fk foreign key (student_id) references student(sid);
alter table midde add constraint course_fk foreign key (course_id) references course(cid);
```



### 一对一

**方式一：** 主表的主键，与从表的外键(唯一)，形成主外键关系

```sql
create table user(
  uid varchar(32) primary key, -- 主键
	username varchar(50)
);

create table user_card(
  cid varchar(32),
	birthday date,
	user_id varchar(32) unique -- 外键
);

alter table user_card add constraint foreign key (user_id) references user (uid);
```



**方式二：** 主表的主键，与从表的外键(主键)，形成主外键关系

```sql
create table user(
	uid varchar(32) primary key, -- 主键
	username varchar(50)
);

create table user_card(
	cid varchar(32) primary key, -- 主键，既是外键
	birthday date
);

alter table user add constraint foreign key (cid) references user_card (uid);
```



## 多表查询

### 交叉查询

```sql
# 查询两个表的数据
select * from student, course;
```



### 内连接查询

查询所有学生和课程数据

```sql
# 隐式内连接查询
select s.name, c.name from student s, course c where s.sid = c.cid;

# 显示内连接查询
select s.name, c.name from student s inner join course c on s.sid = c.cid;
```



### 外连接查询

```sql
# 左外连接：查询左表student的所有数据，如果条件成立则显示右表course的数据，反之显示null
select * from student s left outer join course c on s.sid = c.cid;

# 右外连接：查询右表course的所有数据，如果条件成立则显示右表student的数据，反之显示null
select * from course c right outer join student s on c.cid = s.sid;
```



查询员工 `empno` 和 所属领导 `mgr` 的姓名

```sql
# 方法一：内连接
select e.empno, e.ename as 员工姓名, m.ename as 经理名称 from emp as e, emp as m where e.mgr = m.empno;
select e.empno, e.ename as 员工姓名, m.ename as 经理名称 from emp as e inner join emp as m on e.mgr = m.empno;;

# 方法二：子查询
select e.empno, e.ename as 员工姓名, (select m.ename from emp m where e.mgr = m.empno) as 经理名称 from emp e;

# 方法三：左外连接
select e.empno, e.ename as 员工姓名, m.ename as 经理名称 from emp e left join emp m on e.mgr = m.empno;
```



查询员工姓名 `ename` 及其 所在部门的名称 `edeptno`

```sql
# 方法一
select e.ename, d.dname from emp e, dept d where e.deptno = d.deptno;

# 方法二
select e.ename, (select d.dname from dept d where e.deptno = d.deptno) as 部门 from emp e;
```



 查询从事 `clerk` 工作的员工姓名和所在部门名称

```sql
select e.ename, d.dname from emp e, dept d where e.job = 'clerk' && e.deptno = d.deptno;
```



## 子表查询

### 标量子查询

查询 `emp` 员工与对应的 `dept` 部门名称

```sql
# select deptno from dept where deptno = 10;

select e.ename, (select dname from dept where e.deptno = deptno) as dept from emp e;
```



### 列子查询

查询编号为 `7369` 和 `7521` 的员工并显示部门信息

```sql
# 找到指定编号的员工
#select e.ename from emp e where e.empno in (7369, 7521);
# 再通过指定编号找到员工的部门
#select d.dname from dept d where e.deptno = d.deptno;

select e.ename, (select d.dname from dept d where e.deptno = d.deptno) as dept from emp e where e.empno in (7369, 7521);
```



查询 `sales` 与 `accounting` 这两个部门的所有员工姓名

```sql
# select deptno as id from dept where dname in ('sales', 'accounting');

select ename from emp where deptno in (select deptno as id from dept where dname in ('sales', 'accounting'));
```



查询在 `blake` 入职之后的员工数据

```sql
# select hiredate from emp where ename = 'blake';

select ename, hiredate from emp where hiredate < (select hiredate from emp where ename = 'blake');
```



### 行子查询

查询与其入职日期 及 职位都相同的员工信息

```sql
# select * from emp where hiredate = '2007-01-01' and job = 2;

# 方式一
select * from emp where hiredate = (select hiredate from emp where ename = '韦一笑') and job = (select job from emp where ename = '韦一笑');

# 方式二：简化上述代码
select * from emp where (hiredate, job) = (select hiredate, job from emp where ename = '韦一笑');
```



### 表子查询

查询 `emp` 表的所有信息，以及 `dept` 表的 `dname`

```sql
select e.*, d.dname as dept from emp e, dept d where e.deptno = d.DEPTNO;
```



### 综合练习

查询所有员工姓名以及所在部门名称

```sql
select e.ename, d.dname from emp e, dept d where e.deptno = d.deptno;
```



查询工资 `sal` 多于平均工资的员工

```sql
# 先找到平均工资是多少
# select round(avg(sal), 2) as 平均工资 from emp e;

select * from emp e where e.sal > (select round(avg(e.sal), 2) as 平均工资 from emp e);
```



查询与 `scott` 从事相同工作的员工，不包含自己

```sql
# 找出scott的从事的工作
# select e.job as job from emp e where e.ename = 'scott';

# 查询所有跟他相同工作的员工
# select * from emp e where e.job = (select e.job as job from emp e where e.ename = 'scott');

# 不包含自己
select * from emp e where e.job = (select e.job as job from emp e where e.ename = 'scott') and e.ename != 'scott';
```



查询部门为 `30` 的所有员工姓名、工资

```sql
select e.ename, e.sal from emp e where e.deptno = 30;
```



查询工资高于部门为 `30` 的所有员工信息

```sql
# 先找到部门30的最高薪资
# select max(sal) as 最高薪资 from emp e where e.deptno = 30;

select * from emp e where e.sal > (select max(sal) as 最高薪资 from emp e where e.deptno = 30);
```



查询所有部门的用户数量

```sql
# 方式一
select d.dname as 部门名称, count(*) as 部门用户 from emp e, dept d where e.deptno = d.deptno group by e.deptno;

# 方式二
select (select d.dname from dept d where d.deptno = e.deptno) as 部门名称, count(*) as 部门用户 from emp e group by e.deptno;

# 方式三
select d.dname as 部门名称, count(*) as 部门用户 from emp e join dept d on e.deptno = d.deptno where e.deptno in (10, 20, 30, 40) group by e.deptno;
```



查询每个工作的最低工资以及员工数量

```sql
select job as 工作, min(sal) as 最低工资, count(*) as 员工数量 from emp group by job;
```



查询岗位 `job=clerk` 的最低工资

```sql
select ename, job, min(sal) as 最低工资 from emp e where job = 'clerk';
```



查询员工的年薪并以从高到低排序

```sql
select e.ename, round(e.sal * 12,0) as 年薪 from emp e order by e.sal desc;
```



查询工资处于第四级别的员工姓名及工资

```sql
# 找出第四级别的工资范围，分别是最高与最低
# select s.losal, s.hisal from salgrade s where s.grade = 4;

select e.ename, e.sal from emp e, (select s.losal, s.hisal from salgrade s where s.grade = 4) s where e.sal between s.losal and s.hisal;
```



查询工资为二级的员工姓名、部门名称以及最高和最低工资

```sql
# 找出部门名称
#select e.ename, d.dname from emp e, dept d where e.deptno = d.deptno;

# 找出第二级别的工资最高与最低数据
#select e.sal, s.hisal from emp e, (select s.losal, s.hisal from salgrade s where s.grade = 2) s where e.sal between s.losal and s.hisal;

select e.ename, d.dname, e.sal, s.hisal as hisal, s.losal as losal from emp e, dept d, (select s.losal, s.hisal from salgrade s where s.grade = 2) s where e.deptno = d.deptno && e.sal between s.losal and s.hisal;
```



## 综合案例

查询价格低于 `10` 元的菜品名称、价格以及菜品分类名称

```sql
select d.name, d.price, (select name from category c where d.category_id = c.id) as cate from dish d where d.price < 10;
```



查询价格在10~50元之间 且 状态为起售的菜品名称、价格以及分类名称

```sql
# 方法一
select d.name, d.price, (select name from category c where d.category_id = c.id) as cate from dish d where (d.price between 10 and 50) && d.status = 1;

# 方法二
select d.name, d.price, c.name as cate from dish d, category c where d.category_id = c.id && (d.price between 10 and 50) && d.status = 1;

# 方法三
select d.name, d.price, c.name as cate from dish d left outer join category c on d.category_id = c.id where (d.price between 10 and 50) && d.status = 1;
```

