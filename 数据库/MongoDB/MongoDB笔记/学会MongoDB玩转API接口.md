# 学会MongoDB玩转API接口

# ■课程介绍

- 第一章：MongoDB基础

> 数据库简介
> MongoDB简介
> MongoDB安装
> MongoDB基本操作
> MongoDB文档增删修查（CURD）
> MongoDB实战教学管理系统数据库设计

- 第二章：MongoDB高级

> MongoDB分页&排序
> MongoDB聚合查询
> MongoDB优化索引
> MongoDB权限机制
> MongoDB备份还原
> 实战可视化管理工具

- 第三章：玩转API接口

> mongoose简介(schema&model)
> mongoose使用
> 接口概念
> 接口开发规范（Restful API）
> 接口测试工具（Postman&Insomnia）
> 实战教学管理系统学生模块接口开发
> 实战接口文档开发apiDoc



# ■教学目标

能够说出数据库的作用&种类

能够独立完成数据库设计

能够独立完成MongoDB数据库CURD

能够实现分页、数据统计、索引、备份等功能

能够使用mongoose开发接口(略难)

能够使用apiDoc开发接口文档



# ■数据库简介

## 为什么要学数据库

在实战开发中

- 天猫：用户将商品加入购物车-下单-新增/修改收货人信息-创建订单
- 京东：用户将商品加入购物车-下单-新增/修改收货人信息-创建订单
- 等

网站都会产生大量的数据。

![1584866023346](images/1584866023346.png) 

思考：这些数据存放在哪里呢？

回答：数据库中



## 是什么

![1584866055107](images/1584866055107.png) 



- 分析

> Ø 在生活中 
>
> 通过**仓库**来存放**物品**，每个**仓库**有N个**架子**，每个**架子**上有N个**物品**；
>
> Ø 在程序中
>
> 通过变量来临时存储数据，便于后期脚本运行时进行判断和数据处理；
>
> 通过**数据库**来存放**数据**，每个**数据库**有N个**表/集合**，每个**表/集合**里面有N条**数据**；
>
> 
>
> 多学一招：集合/分类目的将数据归类，录入用户放stu集合、订单放order集合



- 结论：数据库就是用来存放数据的仓库（留心仓库架子、数据库中集合





## 能干吗

应用场景

用来存放项目数据、例如淘宝、京东、天猫、滴滴等



## 种类（了解）

- 种类

> 关系型：Oracle、MySQL、SQLite 、SQL Server等
>
> 非关系型（**No**t Only **SQL**）：MongoDB（文档）、Redis/Memcache（内存）



- 关系型和非关系型数据库软件区别

> 相同点：都是数据库软件，用来存放项目数据
>
> 不同点：
>
> ```
> 关系型：1.遵循SQL标准，换句话说语法大同小异、2.有库和表约束等
> 非关系型：1.没有统一标准、2.一般键值对形式存储、3.读取速度更快
> ```

- 关系型和非关系型数据库软件如何选择

![1584866152181](images/1584866152181.png) 

## 小总结

为什么学：项目存放数据需要，加薪

是什么：计算机中用来存放数据的仓库

能干吗：存放淘宝、京东、天猫等项目数据

种类：很多，根据不同的业务场景选择，咱们本套课程主要学习MongoDB



# ■MongoDB简介

##  是什么（了解）   

![1584870927181](images/1584870927181.png) 

- 基于分布式文件存储数据库（就是一个数据库
- C++语言编写
- 支持的数据结构非常松散，是类似json的bson格式（后期插入修改数据写JSON

> JSON（JavaScript Object Notation, JS 对象简谱）是一种轻量级的数据交换格式
> bson（二进制JSON）



## 能干吗

- 存放项目数据
- 实战工作开发写API接口（重要
- 等



## 去哪下

windows版本下载：https://www.mongodb.org/dl/win32
linux版本下载：https://www.mongodb.org/dl/linux

<https://www.mongodb.com/download-center/community> 

版本说明

2.x

3.x  （3.2 、3.4、3.6 

4.x  （4.2  更佳的数据管理能力、更强的分布式架构、多文档事物等



# ■MongoDB安装

## win系统

步骤1：下载  https://www.mongodb.com/download-center/community

步骤2：解压

步骤3：创建服务

```
bin/mongod.exe --install --dbpath  磁盘路径  --logpath 日志路径

留心1：比如通过管理员身份运行DOS窗口 否则没有权限创建失败
留心2：得提前创建数据和日志存放目录
```

步骤4：启动服务

````
net start mongodb
````

步骤5：登录（验证是否安装成功

```
bin/mongo
```



周边语法

```
创建服务：bin/mongod.exe --install  --dbpath  磁盘路径  --logpath 日志路径
删除服务：bin/mongod.exe --remove
启动服务：net start mongodb  （注：service 服务名d restart/stop/start）
关闭服务：net stop mongodb
```



## 非win系统（Linux、mac

**Linux**

```
#步骤1：下载
curl -O https://fastdl.mongodb.org/linux/mongodb-linux-x86_64-3.0.6.tgz
#步骤2：解压
tar -zxvf mongodb-linux-x86_64-3.0.6.tgz 
#步骤3：将解压包拷贝到指定目录
mv mongodb-linux-x86_64-3.0.6/ /usr/local/mongodb
#步骤4：创建数据存放目录与日志存放目录
mkdir -p /usr/local/mongodb/data /usr/local/mongodb/logs
#步骤5：启动MongoDB服务
/usr/local/mongodb/bin/mongod --dbpath=/usr/local/mongodb/data --logpath=/usr/local/mongodb/logs/mongodb.log --logappend --port=27017 --fork

#后期登录即可
/usr/local/mongodb/bin/mongo
```

**mac**

```
# 步骤1：进入/usr/local
cd /usr/local
# 步骤2：下载
sudo curl -O https://fastdl.mongodb.org/osx/mongodb-osx-ssl-x86_64-4.0.9.tgz
# 步骤3：解压
sudo tar -zxvf mongodb-osx-ssl-x86_64-4.0.9.tgz
#步骤4：重命名
sudo mv mongodb-osx-x86_64-4.0.9/ mongodb
#步骤5：创建数据存放目录与日志存放目录
sudo mkdir -p /usr/local/mongodb/data /usr/local/mongodb/logs
#步骤6：启动MongoDB服务
/usr/local/mongodb/bin/mongod --dbpath=/usr/local/mongodb/data --logpath=/usr/local/mongodb/logs/mongodb.log --logappend --port=27017 --fork

#后期登录即可
/usr/local/mongodb/bin/mongo
```




# ■MongoDB基本操作

## #基本概念

- 生活中：仓库、架子、物品
- 计算机：数据库（database）、集合（collection）、数据/文档（document）



## #查看数据库

语法：show  databases

效果

![1584875413549](images/1584875413549.png) 



## #选择数据库 

语法：use 数据库名 

效果

![1584875558493](images/1584875558493.png) 



## #查看集合

语法：show collections

效果

![1584875655482](images/1584875655482.png) 



## #创建集合

语法：db.createCollection('集合名')

效果

![1584875763507](images/1584875763507.png) 



## #删除集合

语法：db.集合名.drop()

效果

![1584875825749](images/1584875825749.png) 



思考：如何删除数据库？

回答：1通过use语法选中数据库，2通过db.dropDatabase()删除数据库



## 小总结

数据库（查看、创建、选择、删除

```
查看：show databases
创建：有单独的语法，但是忽略  隐式创建
选择：use 数据库名
删除：1通过use选中数据库，2通过db.dropDatabase()删除数据库
```



集合（查看、创建、删除

```
查看：show collections
创建：db.createCollection('集合名')      多学一招：忽略  后期插入数据 隐式创建集合
删除：db.集合名.drop()
```



# ■MongoDB文档增删修查（CURD）

## 明确需求

数据库主要用来存放项目数据

然后我们已经学会了数据库和集合的创建

思考：如何实现集合中数据的增删改查呢？

回答：通过MongoDB语法即可



## #C增

**语法：**db.集合名.insert(JSON数据)

**说明：**集合存在-则直接插入数据，集合不存在-隐式创建

**练习：**在test2数据库的c1集合中插入数据（姓名叫webopenfather年龄18岁）

```
use test2
db.c1.insert({uname:"webopenfather", age:18})

留心1：数据库和集合不存在都隐式创建
留心2：对象的键统一不加引号方便看，但是查看集合数据时系统会自动加
留心3：mongodb会给每条数据增加一个全球唯一的_id键 
```

![1584877524411](images/1584877524411.png) 

多学一招：_id的组成

![1584876662100](images/1584876662100.png) 





**思考1：是否可以自定义_id值？**

> 回答：可以，只需要给插入的JSON数据增加_id键即可覆盖（但实战强烈不推荐
>
> db.c1.insert({_id:1, uname:"webopenfather", age:18})

![1584877675163](images/1584877675163.png) 

**思考2：如何一次性插入多条记录？**

> 回答：传递数据，数组中写一个个JSON数据即可
>
> 	db.c1.insert([
> 	    {uname:"z3", age:3},
> 	    {uname:"z4", age:4},
> 	    {uname:"w5", age:5}
> 	])
>
>
> ​	含义：一次性插入3条数据到c1集合

![1584877835658](images/1584877835658.png) 

**思考3：如何快速插入10条数据**

> 回答：mongodb底层使用JS引擎实现的，所以支持部分js语法
>
> 因此：可以写for循环
>
> 	for (var i=1; i<=10; i++) {
> 	    print(i)
> 	}
> 				
> 	需求：在test2数据库c2集合中插入10条数据，分别为 a1 a2 ... a10
> 	use test2
> 	for (var i=1; i<=10; i++) {
> 	    db.c2.insert({uanme: "a"+i, age: i})
> 	}
>

![1584878095317](images/1584878095317.png) 



## #R查

**基础语法：**db.集合名.find(条件  [,查询的列])

```
条件
	查询所有数据  		 {}或者不写
	查询age=6的数据		  {age:6}
    既要age=6又要性别=男    {age:6,sex:'男'}
    
查询的列（可选参数
	不写 - 这查询全部列（字段
	{age:1}  只显示age列（字段
	{age:0}  除了age列（字段都显示
	留心：不管你怎么写系统自定义的_id都会在
```

**升级语法**

````
db.集合名.find({键:值})   注：值不直接写
				 {运算符:值}
db.集合名.find({
	键:{运算符:值}
}) 
````

| 运算符 | 作用     |
| :----: | :------- |
|  $gt   | 大于     |
|  $gte  | 大于等于 |
|  $lt   | 小于     |
|  $lte  | 小于等于 |
|  $ne   | 不等于   |
|  $in   | in       |
|  $nin  | not in   |



**练习1：查询所有数据**

![1584879409036](images/1584879409036.png) 

**练习2：查询年龄大于5岁的数据？**

![1584879582863](images/1584879582863.png) 

**练习3：查询年龄是5岁、8岁、10岁的数据？**

![1584879685355](images/1584879685355.png) 

**练习3：只看年龄列，或者年龄以外的列（_id别管它）？**

![1584879731549](images/1584879731549.png) 



## #U改

**基础语法：** db.集合名.update（条件， 新数据  [,是否新增，是否修改多条]）

```
是否新增：指条件匹配不到数据则插入(true是插入，false否不插入默认)
是否修改多条：指将匹配成功的数据都修改（true是，false否默认）
```

**升级语法**

说明：

是否新增：指匹配不到数据则插入（true-是插入，false-否不插入默认）

是否修改多条：将匹配成功的数据都修改（true-是，false-否默认）

```
db.集合名.update（条件， 新数据）
					  {修改器: {键:值}}
```

| 修改器  | 作用     |
| :-----: | :------- |
|  $inc   | 递增     |
| $rename | 重命名列 |
|  $set   | 修改列值 |
| $unset  | 删除列   |



**准备工作**

```
use test2;
for(var i = 1; i<= 10; i++){
	db.c3.insert( {"uname":"zs"+i,"age":i} );
}
```



**练习1：将{uname:**"**zs1**"**}改为{uname:**"**zs2**"**}**

![1584883099949](images/1584883099949.png) 

> 发现：默认不是修改，而是替换
>
> 解决：使用升级语法 修改器
>
> 需求：使用修改器将zs4的姓名改为zs44
>
> 语法：db.c3.update({uname:"zs4"}, {$set: {uname: "zs44"}})

![1584883391699](images/1584883391699.png) 

**练习2：给{uname:**"**zs10**"**}的年龄加2岁或者减2岁**

![1584883478690](images/1584883478690.png) 



![1584883523057](images/1584883523057.png) 



**练习3：修改器综合练习** 


插入数据：db.c4.insert( {uname:"神龙教主",age:888,who:"男",other:"非国人"});

> 完成需求：
> uname  改成   webopenfather      (修改器：$set）
> age    增加   111	          	     (修改器：\$inc）
> who   改字段 sex	         	     (修改器：\$rename）
> other 删除		            	    (修改器：\$unset）
>
> 语法分析：
>
> ```
> db.c4.update({uname:"神龙教主"}, {uname: "webopenfather"})   #错误，替换
> 							    {$set: {uname: "webopenfather"}}
> 							    {$inc: {age: 111}}
> 							    {$rename: {who: "sex"}}
> 							    {$unset: {other: true}}
> 留心如何一次性写多个修改器
> db.c4.update({uname:"神龙教主"}, {
>     $set: {uname: "webopenfather"},
>     $inc: {age: 111},
>     $rename: {who: "sex"},
>     $unset: {other: true}
> })
> ```

![1584883798685](images/1584883798685.png) 



**练习4：验证语法最后两个参数（了解）**

【验证】是否新增true是false否：修改uame 等于 zs30的年龄 30岁 

![1584883984485](images/1584883984485.png) 

【验证】是否修改多条true是，false否默认

![1584884114315](images/1584884114315.png) 



## #D删

语法：db.集合名.remove(条件 [, 是否删除一条] )

注意：是否删除一条 true是,false否 默认

![1584884531503](images/1584884531503.png) 



## 小总结

高级开发攻城狮统称：所有数据库都需要增删改查CURD标识

MongoDB删除语法：remove 



增Create

````
db.集合名.insert(JSON数据)
````

删Delete

````
db.集合名.remove(条件 [,是否删除一条true是false否默认])

也就是默认删除多条
````

改Update

```
db.集合名.update(条件， 新数据  [,是否新增,是否修改多条])

升级语法db.集合名.update(条件，{修改器：{键：值}})
```

查Read

```
db.集合名.find(条件 [,查询的列])
```






# ■MongoDB实战教学管理系统数据库设计

![1584885052792](images/1584885052792.png) 



## 实战开发流程

![1584885071168](images/1584885071168.png) 



## 数据库设计流程

根据UI设计稿

1确定功能模块所属集合

2确定集合字段

```
UI设计稿每个展示内容对应一个字段
创建于字段
更新于字段
状态字段

最后：先中文、再英文
留心：上述规则照顾基础差的，如果大神 或 久而久之 就变成肌肉记忆 下意识直接创建
```



## 练习

- 需求：根据千锋教学系统，设置存放学生信息的集合，并插入20条测试数据

- 代码

````
1. 先中文
集合名称：学生集合
集合字段：编号、学好、姓名、电话、性别、年龄、学历、备注

2. 再英文

use school
for (var num=1; num<=20; num++) {
    db.stu.insert({
        id:num,
        no: "QF"+num,
        uname: "神龙教"+num,
        tel: "1111111111",
        sex: "女",
        age: num,
        school: "研究生",
        remark: "土豪"
    })
}
````




# ■MongoDB排序&分页

## 明确需求

数据库，是用来存放数据的

咱们后期从数据库获取数据 需要排序  、多页展示如何实现？ 

![1585470698327](images/1585470698327.png) 

![1585470708367](images/1585470708367.png) 

## 准备

```
use test3
db.c1.insert({_id:1,name:"a",sex:1,age:1})
db.c1.insert({_id:2,name:"a",sex:1,age:2})
db.c1.insert({_id:3,name:"b",sex:2,age:3})
db.c1.insert({_id:4,name:"c",sex:2,age:4})
db.c1.insert({_id:5,name:"d",sex:2,age:5})

db.c1.find()
```



## 排序

- 语法：db.集合名.find().sort(JSON数据)
- 说明：键-就是要排序的列/字段、值：1 升序  -1 降序

- 练习：年龄升序&降序

>  ![1585472204585](images/1585472204585.png) 



## Limit与Skip方法

- 语法：db.集合名.find().sort().skip(数字).limit(数字)
- 说明：skip跳过指定数量（可选），limit限制查询的数量
- 练习：1-降序查询查询2条，2-降序跳过2条并查询2条

> ![1585472467480](images/1585472467480.png) 



## 实战分页

需求：数据库 1  - 10数据，每页显示两条（5页）

语法：db.集合名.find().skip().limit(2)

```
skip计算公式：（当前页 - 1） * 每页显示条数

1页    1  2		0
2页    3  4		2
3页    5  6		4
4页    7  8		6
5页    9  10		8
```



## 小总结

db.集合名.find()

.sort({列: 1/-1}) 排序

.skip(数字)   跳过指定数量

.limit(数字) 限制查询条数

.count()   统计总数量

不够：百度或谷歌



# ■MongoDB聚合查询

## 明确需求

![1585472901948](images/1585472901948.png) 

思考：如何统计数据、如何实现分组统计等？

回答：通过MongoDB聚合查询



## 概念

聚合查询

顾名思义就是把数据聚起来，然后统计




## 语法(略复杂

语法

````
db.集合名称.aggregate([
    {管道:{表达式}}
     ....
])
````

常用管道

```
$group 将集合中的文档分组，用于统计结果
$match 过滤数据，只要输出符合条件的文档
$sort  聚合数据进一步排序
$skip  跳过指定文档数
$limit 限制集合数据返回文档数
....
```

常用表达式

```
$sum  总和  $sum:1同count表示统计
$avg  平均
$min  最小值
$max  最大值
...
```





## 准备

````
use test4
db.c1.insert({_id:1,name:"a",sex:1,age:1})
db.c1.insert({_id:2,name:"a",sex:1,age:2})
db.c1.insert({_id:3,name:"b",sex:2,age:3})
db.c1.insert({_id:4,name:"c",sex:2,age:4})
db.c1.insert({_id:5,name:"d",sex:2,age:5})
````



|      |      | 统计 |
| ---- | ---- | ---- |
|      |      |      |
|      |      |      |
|      |      |      |



## 练习

- 统计男生、女生的总年龄

> ```
> db.c1.aggregate([
>     {
>     	$group:{
>             _id: "$sex",
>             rs: {$sum: "$age"}
>     	}
>     }
> ])
> ```
>
> ![1585473992676](images/1585473992676.png) 

- 统计男生、女生的总人数

> ```
> db.c1.aggregate([
>     {
>     	$group:{
>             _id: "$sex",
>             rs: {$sum:1}
>     	}
>     }
> ])
> ```
>
> ![1585474123142](images/1585474123142.png) 

- 求学生总数和平均年龄

> ```
> db.c1.aggregate([
>     {
>     	$group:{
>             _id: null,
>             total_num: {$sum:1},
>             total_avg: {$avg: "$age"}
>     	}
>     }
> ])
> ```

> ![1585474285569](images/1585474285569.png) 

- 查询男生、女生人数，按人数升序

> ```
> db.c1.aggregate([
>     {$group:{_id: "$sex",rs: {$sum: 1}}},
>     {$sort:{rs: -1}}
> ])
> ```
>
> ![1585474498560](images/1585474498560.png) 



# ■MongoDB优化索引

## 生活中的索引

![1585474692372](images/1585474692372.png) 



## 数据库中的索引

- 说明：索引是一种排序好的便于快速查询的数据结构

- 作用：帮助数据库高校的查询数据

![1585475724394](images/1585475724394.png) 




## 索引优缺点

- 优点

> 提高数据查询的效率，降低数据库的IO成本
> 通过索引对数据进行排序，降低数据排序的成本，降低CPU的消耗

- 缺点

> 占用磁盘空间
> 大量索引影响SQL语句效率，因为每次插入和修改数据都需要更新索引



## 语法

- 创建索引语法：db.集合名.createIndex(待创建索引的列 [,额外选项])
- 参数：

> 待创建索引的列：{键:1,...,键:-1} 
> 说明：1升序 -1降序 例如{age:1}表示创建age索引并按照升序的方式存储
> 额外选项：设置索引的名称或者唯一索引等等

- 删除索引语法：

> 全部删除：db.集合名.dropIndexes()
>
> 删除指定：db.集合名.dropIndex(索引名)

- 查看索引语法：db.集合名.getIndexes()



## 练习

**准备：**向数据库中新增十万条数据 

> ```
> //选择数据库
> use test5;
> //向数据库中添加数据
> for(var i=0;i<100000;i++){
> db.c1.insert({'name':"aaa"+i,"age":i});
> }
> ```

**创建普通索引**

> 需  求：给name添加普通索引
>
> 练习1：给name添加普通索引，命令：db.c1.createIndex({name:1})
>
> ![1585476182255](images/1585476182255.png) 
>
> 练习2：删除name索引，命令：db.c1.dropIndex('name_1')
>
> ![1585476235731](images/1585476235731.png) 
>
> 练习3：给name创建索引并起名webopenfather 
>
> 命令：db.c1.createIndex({name:1}, {name: "webopenfather"})
>
> ![1585476311406](images/1585476311406.png) 

**创建复合/组合索引**

> 需求：给name和age添加组合索引
>
> 说明：就是一次性给两个字段建立索引
>
> 语法：db.集合名.createIndex({键1:方式,键2:方式})
>
> ![1585476428632](images/1585476428632.png) 



**创建唯一索引**

> 需 求：给name添加普通索引
>
> 语 法：db.集合名.createIndex(待添加索引的列， {unique: 列名})
>
> 练习1：删除全部索引，命令：db.c1.dropIndexes();
>
> ![1585476535470](images/1585476535470.png) 
>
> 练习2：设置唯一索引，命令：db.c1.createIndex({name:1}, {unique: "name"})
>
> ![1585476576207](images/1585476576207.png) 
>
> 练习3：测试唯一索引特性，
>
> 命 令：
> db.c1.insert({name: "a"});
> db.c1.insert({name: "a"});
>
> ![1585476763631](images/1585476763631.png) 



## 分析索引（explain） 

- 语法：db.集合名.find().explain('executionStats')

- 说明：

> ![1585461681918](images/1585461681918.png) 
> COLLSCAN 全表扫描
> IXSCAN  索引扫描
> FETCH   根据索引去检索指定document

- 练习

> 测试：age未添加索引情况
> 语法：db.c1.find({age:18}).explain('executionStats');
>
> ![1585477310460](images/1585477310460.png) 
>
> 测试：age添加索引情况
> 语法：db.c1.createIndex({age: 1})
> 继续：db.c1.find({age:18}).explain('executionStats')
> ![1585477412871](images/1585477412871.png) 



## 选择规则（如何选择合适的列创建索引）

-为常做条件、排序、分组的字段建立索引
-选择唯一性索引							（ps. 同值较少如性别字段）
-选择较小的数据列，为较长的字符串使用前缀索引      （ps. 索引文件更小）






# ■MongoDB权限机制

## 明确需求

发现我们再DOS窗口直接输入命令就可以登录数据库

这在实战工作中绝对不允许的

思考：如何解决

回答：使用权限机制，开启验证模式即可



## 语法(略难

创建账号

````
db.createUser({ 
    "user" : "账号",
    "pwd": "密码",
    "roles" : [{ 
        role: "角色", 
        db: "所属数据库"
    }] 
})
````

角色

```
#角色种类
超级用户角色：root 
数据库用户角色：read、readWrite; 
数据库管理角色：dbAdmin、userAdmin； 
集群管理角色：clusterAdmin、clusterManager、clusterMonitor、hostManager； 
备份恢复角色：backup、restore； 
所有数据库角色：readAnyDatabase、readWriteAnyDatabase、userAdminAnyDatabase、dbAdminAnyDatabase 

#角色说明
root：只在admin数据库中可用。超级账号，超级权限；
read：允许用户读取指定数据库；
readWrite：允许用户读写指定数据库； 
dbAdmin：允许用户在指定数据库中执行管理函数，如索引创建、删除，查看统计或访问system.profile；
dbAdminAnyDatabase：只在admin数据库中可用，赋予用户所有数据库的dbAdmin权限； 
clusterAdmin：只在admin数据库中可用，赋予用户所有分片和复制集相关函数的管理权限； 
userAdmin：允许用户向system.users集合写入，可以找指定数据库里创建、删除和管理用户；
userAdminAnyDatabase：只在admin数据库中可用，赋予用户所有数据库的userAdmin权限；
readAnyDatabase：只在admin数据库中可用，赋予用户所有数据库的读权限； 
readWriteAnyDatabase：只在admin数据库中可用，赋予用户所有数据库的读写权限； 
```





## 开启验证模式

**开启验证模式概念： ** 名词，指用户需要输入账号密码才能登陆使用

**操作步骤** 

```
1. 添加超级管理员
2. 退出卸载服务
3. 重新安装需要输入账号密码的服务（注在原安装命令基础上加上--auth即可
4. 启动服务 -> 登陆测试
```

**步骤1：添加超级管理员**

> mongo
>
> ```
> use admin
> 
> db.createUser({ 
>     "user" : "admin",
>     "pwd": "admin888",
>     "roles" : [{ 
>         role: "root", 
>         db: "admin"
>     }] 
> })
> ```
>
> 脚下留心：2.x 3.x 4.x 前面版本默认是看不到admin没关系 你直接选中即可



**步骤2：退出卸载服务**

> ```
> bin\mongod --remove
> ```
>
> 脚下留心：DOS窗口必须用管理员省份运行
>
> ![1585478949532](images/1585478949532.png) 



**步骤3：安装需要身份验证的MongoDB服务**

> ```
> bin\mongod --install --dbpath E:\mongodb\data --logpath E:\mongodb\logs\mongodb2.log --auth
> ```
>
> ![1585479066717](images/1585479066717.png) 



**步骤4：启动服务 -> 登录测试**

> ![1585479128562](images/1585479128562.png) 



## 通过超级管理员账号登陆

- 方法1：mongo 服务器IP地址:端口/数据库 -u 用户名 -p 密码

> ![1585479276933](images/1585479276933.png) 
>
> ![1585479291125](images/1585479291125.png) 

- 方法2：a-先登录，b-选择数据库，c-输入db.auth(用户名,密码)

> ![1585479528999](images/1585479528999.png) 



## 练习

- **需求**

> 添加用户shop1可以读shop数据库
> 添加用户shop2可以读写shop数据库 
> 脚下留心：必须在对应数据库创建用户



- **准备：**创建测试数据和测试用户（注：选择shop仓库创建用户）

> ```
> use shop;
> for(var i=1; i<=10; i++) {
>     db.goods.insert({"name":"goodsName"+i,"price":i});
> }
> ```



- **添加用户并设置权限**

> ```
> //切记
> use shop
> 
> //shop1
> db.createUser({ 
>     "user" : "shop1",
>     "pwd": "admin888",
>     "roles" : [{ 
>         role: "read", 
>         db: "shop"
>     }] 
> })
> 
> //shop2
> db.createUser({ 
>     "user" : "shop2",
>     "pwd": "admin888",
>     "roles" : [{ 
>         role: "readWrite", 
>         db: "shop"
>     }] 
> })
> ```



- **验证：shop1可读**

> ![1585479805712](images/1585479805712.png) 

- **验证：shop2验证可读可写**

> ![1585479857883](images/1585479857883.png) 



# ■MongoDB备份还原

## 明确需求

在实战工作中一定要做好数据库备份工作

否则万一数据丢失

带来的影响是巨大的



## 备份数据库mongodump

- **语法**

> ```
> 导出数据语法：mongodump -h -port -u -p -d -o
> 导出语法说明
> -h 		host  服务器IP地址（一般不写 默认本机
> -port 		  端口（一般不写 默认27017
> -u     user   账号
> -p      pwd   密码
> -d  database  数据库（留心：数据库不写则导出全局
> -o     open   备份到指定目录下
> ```

- **练习(备份所有数据)**：mongodump -u admin -p admin888 -o E:\mongodb\bak

> ![1585480505097](images/1585480505097.png) 

- **练习(备份指定数据)**：mongodump -u admin -p admin888 -d shop -o E:\mongodb\bak2

> ![1585480639789](images/1585480639789.png) 





## 还原数据库mongorestore

- **语法：**

> ```
> 还原数据语法：mongorestore -h -port -u -p  -d --drop 备份数据目录
> 还原数据说明：
> -h 
> -port 
> -u 
> -p
> -d       不写则还原全部数据库
> --drop   先删除数据库再导入
> ```

- **练习(还原所有数据)**：mongorestore -u admin -p admin888 --drop E:\mongodb\bak

> ![1585480881340](images/1585480881340.png) 
>
> ![1585480948534](images/1585480948534.png)
>
> ![1585480971194](images/1585480971194.png) 
>
> ![1585480981182](images/1585480981182.png) 



- **练习(还原指定数据)**：mongorestore -u shop2 -p admin888 -d shop --drop E:\mongodb\bak2\shop

> ![1585481050097](images/1585481050097.png)  
>
> ![1585481783387](images/1585481783387.png) 




# ■实战可视化管理工具

## 简介

adminMongo       WEB/PC端口网页管理      https://adminmongo.markmoffat.com/

Robo 3T *  	   客户端软件      	          <https://robomongo.org/download>/

MongoVUE           客户端软件



## 安装Robo 3T

![1571118235273](images/1571118235273.png) 

![1571118246690](images/1571118246690.png) 

![1571118258678](images/1571118258678.png) 

![1571118267131](images/1571118267131.png) 

![1571118274662](images/1571118274662.png) 

![1571118283895](images/1571118283895.png) 



## 使用Robo 3T

>  ![1585482451810](images/1585482451810.png) 
>
> ![1585482549628](images/1585482549628.png) 
>
> ![1585482465701](images/1585482465701.png) 
>
> ![1585482566526](images/1585482566526.png) 
>
> ![1585482668249](images/1585482668249.png) 





# ■mongoose简介


## 明确需求

为什么学数据库，因为要存放项目/网站数据

思考：实战中也像我们刚刚那样敲命令？

回答：非也，而是通过程序来

思考：如何实现呢？

回答：mongoose



## 是什么

是node中提供操作MongoDB的模块



## 能干吗

能够通过node语法实现MongoDB数据库增删改查

从而实现用node写程序来管理MongoDB数据库



## 去哪下

npm i mongoose

或者

yarn add mongoose （推荐



## schema

英文网： http://mongoosejs.com

中文网： <http://mongoosejs.net/> 

作用：用来约束MongoDB文档数据（哪些字段必须，哪些字段可选的）



## model

一个模型  对应 一个集合

后面我们通过模型来管理集合中的数据



## 小总结

为什么要学习mongoose：通过命令管理mongodb麻烦，通过程序管理

是什么：就是node中的一个模块

能干吗：让node借助这个模块实现管理mongodb的程序

去哪下：通过npm或yarn命令来安装

mongoose核心概念

> schema 约束字段/列数据
>
> model模型 对应 集合 后期用它来实现数据增删改查




# ■mongoose使用

## 安装

 步骤1：创建api目录

步骤2：在api目录下安装mongoose模块

![1585489163978](images/1585489163978.png) 

## 语法

```
// 一、导入模块
const mongoose = require('mongoose');
// 二、连接数据库
const db = mongoose.createConnection('mongodb://user:pass@localhost:port/database', {useNewUrlParser: true, useUnifiedTopology: true}, err=>{
    if(err){
        console.log('---------------------------------------')
        console.log('数据库连接失败：', err)
        console.log('---------------------------------------')
        return; 
    }
    console.log('数据库连接成功');
})

// 三、设置数据模型（声明是哪个集合，限制字段个数和字段类型）
const model = db.model('user',{
	name:{type:String, default:"username"},
	age:{type: Number},
	sex:{type:String}
})

// 四、创建实例操作（CURD）

// 增 --------------------------------
const insertObj = new model(数据对象)
方法1：insertObj.save((err) => db.close()) 
方法2（推荐）
insertObj.save()
.then(res=>{
    return res
})
.catch(err => {
    console.log('插入失败' + err)
    return false
})

// 删 --------------------------------
方法1：model.remove/deleteOne/deleteMany(条件对象, (err) => db.close()) 
方法2（推荐）
model.deleteOne(条件对象)
.then(res => {
    return res.deletedCount
})
.catch(err => {
    console.log('删除失败' + err)
    return false
})

// 改 --------------------------------
方法1：model.update/updateOne/updateMany(条件对象, 数据对象, (err) => db.close()) 
方法2（推荐）
model.updateOne(条件对象, 数据对象) 
.then(res => {
    return res.nModified
})
.catch(err => {
    console.log('修改失败' + err)
    return false
})

// 查 --------------------------------
方法1：model.find/findOne(条件对象, 要显示的字段数据对象, (err, result) => db.close()) 
方法2（推荐）
model.findOne(条件对象) 
.then(res => {
    return res
})
.catch(err => {
    console.log(err)
    return false
})
```



## 练习

c.js

```
// 一、导入模块
const mongoose = require('mongoose');
// 二、连接数据库
const db = mongoose.createConnection('mongodb://shop2:admin888@localhost:27017/shop', {useNewUrlParser: true, useUnifiedTopology: true}, err=>{
    if(err){
        console.log('---------------------------------------')
        console.log('数据库连接失败：', err)
        console.log('---------------------------------------')
        return; 
    }
    console.log('数据库连接成功');
})

// 三、设置数据模型（声明是哪个集合，限制字段个数和字段类型）
const model = db.model('api',{
	uanme:{type:String, default:"神龙教主"},
	pwd:{type:String},
	// pwd: String,
	age:{type: Number},
	sex:{type:String}
})

// 四、创建实例操作（CURD）

// 增 --------------------------------
const insertObj = new model({
    uname: "张三",
    pwd: "admin888",
    age: 18,
    sex: "男",
})
insertObj.save()
.then(res=>{
    console.log(res)
    return res
})
.catch(err => {
    console.log('插入失败' + err)
    return false
})

```

r.js

```
// 一、导入模块
const mongoose = require('mongoose');
// 二、连接数据库
const db = mongoose.createConnection('mongodb://shop2:admin888@localhost:27017/shop', {useNewUrlParser: true, useUnifiedTopology: true}, err=>{
    if(err){
        console.log('---------------------------------------')
        console.log('数据库连接失败：', err)
        console.log('---------------------------------------')
        return; 
    }
    console.log('数据库连接成功');
})

// 三、设置数据模型（声明是哪个集合，限制字段个数和字段类型）
const model = db.model('api',{
	uanme:{type:String, default:"神龙教主"},
	pwd:{type:String},
	// pwd: String,
	age:{type: Number},
	sex:{type:String}
})
e = require('mongoose');
// 二、连接数据库
const db2 = mongoose.createConnection('mongodb://shop2:admin888@localhost:27017/shop', {useNewUrlParser: true, useUnifiedTopology: true}, err=>{
    if(err){
        console.log('---------------------------------------')
        console.log('数据库连接失败：', err)
        console.log('---------------------------------------')
        return; 
    }
    console.log('数据库连接成功');
})

// 三、设置数据模型（声明是哪个集合，限制字段个数和字段类型）
const model2 = db2.model('api',{
	uanme:{type:String, default:"神龙教主"},
	pwd:{type:String},
	// pwd: String,
	age:{type: Number},
	sex:{type:String}
})

// 四、创建实例操作（CURD）

// 读 --------------------------------

// model2.findOne({}) 
model2.find({}) 
.then(res => {
    console.log(res)
    db2.close()
    return res
})
.catch(err => {
    console.log(err)
    return false
})
```

rPage.js 排序 分页

````
// 一、导入模块
const mongoose = require('mongoose');
// 二、连接数据库
const db = mongoose.createConnection('mongodb://shop2:admin888@localhost:27017/shop', {useNewUrlParser: true, useUnifiedTopology: true}, err=>{
    if(err){
        console.log('---------------------------------------')
        console.log('数据库连接失败：', err)
        console.log('---------------------------------------')
        return; 
    }
    console.log('数据库连接成功');
})

// 三、设置数据模型（声明是哪个集合，限制字段个数和字段类型）
const model = db.model('api',{
	uanme:{type:String, default:"神龙教主"},
	pwd:{type:String},
	// pwd: String,
	age:{type: Number},
	sex:{type:String}
})
e = require('mongoose');
// 二、连接数据库
const db2 = mongoose.createConnection('mongodb://shop2:admin888@localhost:27017/shop', {useNewUrlParser: true, useUnifiedTopology: true}, err=>{
    if(err){
        console.log('---------------------------------------')
        console.log('数据库连接失败：', err)
        console.log('---------------------------------------')
        return; 
    }
    console.log('数据库连接成功');
})

// 三、设置数据模型（声明是哪个集合，限制字段个数和字段类型）
const model2 = db2.model('api',{
	uanme:{type:String, default:"神龙教主"},
	pwd:{type:String},
	// pwd: String,
	age:{type: Number},
	sex:{type:String}
})

// 四、创建实例操作（CURD）

// 读 --------------------------------

// model2.findOne({}) 
model2.find({}).skip(1).limit(1)
.then(res => {
    console.log(res)
    db2.close()
    return res
})
.catch(err => {
    console.log(err)
    return false
})
````



## 小总结

安装：yarn add mongoose  或者  npm i mongoose 

使用：[http://mongoosejs.net](http://mongoosejs.net/)  或  [http://mongoosejs.com](http://mongoosejs.com/)




# ■接口概念

## 明确需求

随着移动互联网的发展，客户端层出不穷，微信端、WEB/PC、APP等等，而后端业务逻辑基本是一致的，如何做到业务逻辑“一次编写，随时接入”呢？

![1571303654659](images/1571303654659.png) 

答案：就是调用接口、定义接口



## 什么是

就是一个文件(js\json\php等，主要**响应JSON数据（操作方便，体积小）**或XML数据

```
{
  status: 1/0,
  msg: '提示信息'
}
-----
<xml>
  <status>1/0</status>
  <msg>提示信息</msg>
  ...
</xml>
```

-  多学一招：只要响应json数据所有语言都可以操作，例如ios/Android等

- 推荐

```js
{
    meta: {
        msg: 提示信息,
        status: 状态码（200/201/301/302/400/401/403/404/500）
    },
    data: 数据
}
```





## 能干吗

数据角度：让我们的项目静态/固定数据动态（也就是让项目数据来源于数据库

功能角度：短信接口、幽默笑话接口、天气接口、股票接口等



## 去哪下（找）

- 方案1：通过node/go/java/php/asp等语言去开发
- 方法2：使用第三方接口

> ![1585490612280](images/1585490612280.png) 





## 小总结

为什么要有接口：一次编写，多次/随时接入（好处：减少后端工作量方便后期维护

什么是接口：就是一个文件，但是你必须返回json或xml数据

接口能干吗：1-数据角度：页面数据动态，2-功能角度：短信接口、人脸识别接口

去哪下：1-自己写，2-去第三方网站找




# ■接口开发规范（Restful API）

## 明确需求

思考：接口可能是一个人开发，也可能多人开发，如何保证大家的编写规范一样

> 商品模块  /goods/getIndex     get 
> 订单模块  /order/index	   post
> 用户模块  /user 

发现：上述这种case项目没有统一开发规则
问题：后期维护麻烦
回答：通过目前比较火的Restful API思想/架构




## 是什么&好处

- 说明：RESTful是目前最流行的一种互联网软件架构（思想）
- 作用：声明/提供了接口设计原则和约束条件（一个规范）
- 相关：

> 后端将资源发布到URL上->前端通过URL访问资源->并通过HTTP动词表示要对资源进行的操作
> 后端定义接口->前端请求接口->HTTP动词表名操作目的（get获取post新建put更新等）

- 举栗

> 列表页：访问 - /模块名	          （get）
> 详情页：访问 - /模块名/编号        （get）
> 添加页：访问 - /模块名/create     （get）
> 处  理：访问 - /模块名                   （post）
> 修改页：访问 - /模块名/编号/edit（get） 
> 处  理：访问 - /模块名/编号          （put）	
> 删  除：访问 - /模块名/编号           （delete）
>
> HTTP动词：get、post、put、delete

- 好处：统一开发规范，便与团队协作开发



## 标准的RESTful架构/思想/规则需要做到

```
订单模块
/order  	get
/order  	post
/order/编号  put
/order/编号  delete
```

- 项目所有模块有统一的标准
- 看Url就知道要操作的资源是什么（也就是哪个模块）
- 看Http Method就知道操作动作是什么，是添加（post）还是删除（delete）
- 看Http Status Code就知道操作结果如何，是成功（200）还是内部错误（500）



## 小总结

什么是restful api：是一个架构或思想

作用：声明了接口设计原则和约束条件

好处：统一开发规范，便于团队协作开发



# ■接口测试工具（Postman&insomnia）

## 概念

- 什么是Postman：就是一个工具
- 作用：模拟HTTP请求，测试接口，查看接口返回数据
- 官网：www.getpostman.com



## 下载安装

![1585491540713](images/1585491540713.png) 



## 使用 

- 步骤1：双击打开

![1585491573284](images/1585491573284.png) 

- 步骤2：使用postman请求接口 https://jsonview.com/example.json

![1585491766508](images/1585491766508.png)  





## 应用场景

明确：postman就是一个工具

使用：

```
后端：调试接口避免出现BUG
前端：1-看你接口能不能用，2-查看返回的数据内容
```










# ■实战教学管理系统学生模块接口开发

## express简介

- 是什么：基于nodejs开发的一个框架（原理基于node内置http模块封装）
- 好    处：加快项目开发，便于团队协作



## express使用

步骤1：下载 （yarn add express

![1585492638394](images/1585492638394.png) 

步骤2：使用

![1585492848257](images/1585492848257.png) 





## 学生添加接口

步骤1：定义路由   /stu    post

步骤2：响应任意json数据

步骤3：定义stu模块，定义创建数据的方法

步骤4：调用stu模型创建数据的方法，返回结果



## 学生列表接口

步骤1：定义路由  /stu  get

步骤2：响应任意json数据

步骤3：修改stu模型，增加查询方法

步骤4：修改控制，调用模型返回数据



## 学生列表接口分页

步骤1：修改控制器接口请求参数  （get）   pageno  pagesize

步骤2：传递给模型

步骤3：模型增加skip和limit即可





#■实战接口文档开发apiDoc

## 明确需求

实战开发中后端会开发很多很多接口

每个接口呢都有大量的参数

思考：前端如果知道有哪些接口并且每个接口有几个参数？

回答：通过接口文档

思考：接口文档自己写还是怎么样？

回答：有很多，本接口主要是通过该apiDoc来生成



## 是什么

就是nodejs中的一个模块



## 能干吗

通过这个模块可以快速生成接口文档

前提写接口的时候把注释加上



## 去哪下

<https://apidocjs.com/#configuration> 



## 使用

步骤1：先下载模块，后期通过命令基于注释生成文档（仅一次

> npm install apidoc -g

步骤2：在项目根目录创建apidoc.json文件（仅一次

> ```
> {
>   "name": "example",
>   "version": "0.1.0",
>   "description": "apiDoc basic example",
>   "title": "Custom apiDoc browser title",
>   "url" : "https://api.github.com/v1"
> }
> ```
>
> ```
> {
>     "name": "教学管理系统接口文档",
>     "version": "1.0.0",
>     "description": "一个非常NB的接口文档",
>     "title": "Custom apiDoc browser title",
>     "url" : "http://localhost:3000"
>   }
> ```

步骤3：去写接口注释（N次）

> ```
> /**
>  * @api {get} /user/:id Request User information
>  * @apiName GetUser
>  * @apiGroup User
>  *
>  * @apiParam {Number} id Users unique ID.
>  *
>  * @apiSuccess {String} firstname Firstname of the User.
>  * @apiSuccess {String} lastname  Lastname of the User.
>  */
> ```
>
> ```
> /**
>  * @api {get} /stu 学生模块列表
>  * @apiName Add
>  * @apiGroup Stu
>  *
>  * @apiParam {Number} pageno   当前页
>  * @apiParam {Number} pagesize 每页显示条数
>  *
>  * @apiSuccess {String}  meta  状态码&提示信息
>  * @apiSuccess {String}  data  数据
>  */
> ```

步骤4：生成接口文档（N次）

> ```
> apidoc -i ./接口注释目录 -o ./接口文档存放目录
> ```







