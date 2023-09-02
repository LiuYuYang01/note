## MongoDB

### 下载数据库

选择社区版本进行下载：`MongoDB Community Server`

```
https://www.mongodb.com/try/download/community
```



### 第一步

1. 找到数据库存放的目录：`C:\MongoDB`

2. 创建 `data` 目录，`data` 目录中再创建 `db` 目录
3. 创建 `log` 目录，`log` 目录中再创建 `mongod.log` 文件



### 开启服务

在 `MongoDB` 目录中的 `bin` 下以管理员身份运行cmd命令：`mongod --dbpath C:\MongoDB\data\db`

```javascript
C:\MongoDB\bin>mongod --dbpath C:\MongoDB\data\db
```



### 使用命令行操作数据库

命令行工具：`C:\MongoDB\bin\mongo.exe` 



### 图形化工具

一种更简单的数据处理方式

```
https://www.mongodb.com/products/compass
```



**注意：**

1. 使用命令行与图形化工具可以实现同样的效果，只是使用方式不同。
2. **使用命令行与图形化工具之前必须开启服务**



### 基本使用

#### 查看数据库

查看数据库的方式有以下两种

```mysql
//第一种
> show databases
admin   0.000GB
config  0.000GB
local   0.000GB

//第二种
> show dbs
admin   0.000GB
config  0.000GB
local   0.000GB
```



#### 切换数据库

**注意：**如果切换到没有的数据库并不会报错，而是等这个数据库有数据时候会自动创建

```mysql
> show databases
admin   0.000GB
config  0.000GB
local   0.000GB

//切换到已有的数据库
> use admin
switched to db admin

//切换到没有的数据库也不会报错，等有数据时候会自动创建
> use liuyuyang
switched to db liuyuyang

//当前liuyuyang这个数据库没有数据，所以不会自动创建
> show databases
admin   0.000GB
config  0.000GB
local   0.000GB
```



#### 删除数据库

删除当前的数据库，需要先use选择一个数据库

```mysql
db.dropDatabase()
```



#### 修改数据库

`mongodb` 不能直接修改数据库名称。

但是可以先复制原来的数据库，再删除原来的数据库。

```mysql
db.copyDatabase('old_name', 'new_name'); 
use old_name 
db.dropDatabase();
```



#### 创建数据库集合

给 `liuyuyang` 数据库添加集合之后就有了数据，然后自动创建了数据库

```mysql
> db.createCollection('c1')
{ "ok" : 1 }

//可以看到已经能够查询到liuyuyang这个数据库了
> show databases
admin      0.000GB
config     0.000GB
liuyuyang  0.000GB
local      0.000GB
```



#### 查看数据库集合

```mysql
> show collections
c1
```



#### 删除数据库集合

```mysql
> show collections
a1
a2

> db.a2.drop()
true

> show collections
a1
>
```



### 连接数据库

```javascript
// 导包
const MongoClient = require('mongodb').MongoClient

//本地启动的MongoDB地址
const url = 'mongodb://localhost:27017';
//数据库名称
const dbName = 'lyy';

// 连接数据库
MongoClient.connect(url, {
    useUnifiedTopology: true,
    useNewUrlParser: true
}, (err, client) => {
    // 报错提示
    if (err) {
        console.error('MongoDB 连接出错!', err);
        return
    }

    console.log('MongoDB 连接成功!');

    // 切换数据库
    const db = client.db(dbName)
    // 切换到指定数据库的集合
    const userCollection = db.collection('users');

    // 获取users数据信息
    userCollection.find().toArray((err, result) => {
        if (err) {
            console.error('查询数据出错', err)
            return
        }

        console.log('查询结果', result)

        // 关闭数据库
        client.close()
    })
})
```



### 增删改查

#### 插入数据

**语法：**`db.sql.insert({uname:'刘宇阳',age:19})`

```mysql
> db.sql.insert({uname:'刘宇阳',age:19})
WriteResult({ "nInserted" : 1 }) //插入成功

> db.sql.find()
{ "_id" : ObjectId("62f5cde51b7ab6448fcad6f8"), "uname" : "刘宇阳", "age" : 19 }
{ "_id" : ObjectId("62f5d01a1b7ab6448fcad6f9"), "uname" : "刘宇阳", "age" : 18 }
{ "_id" : ObjectId("62f5cde51b7ab6448fcad6f8"), "uname" : "刘宇阳", "age" : 19 }
```



**栗子一：一次性插入多条数据**

用数组套对象结构一次性插入多条数据

```mysql
> db.sql.insert([
... {uname:"zs",age:10},
... {uname:"ls",age:15},
... {uname:"ww",age:18}
... ])

BulkWriteResult({
        "writeErrors" : [ ],
        "writeConcernErrors" : [ ],
        "nInserted" : 3, //插入三行数据
        "nUpserted" : 0,
        "nMatched" : 0,
        "nModified" : 0,
        "nRemoved" : 0,
        "upserted" : [ ]
})

//含义：一次性插入3条数据到c1集合
```



**栗子二：快速插入10条数据**

MongoDB底层使用JS引擎实现的，所以支持部分JS语法，因此可以写for循环

```javascript
for(var i = 1; i <= 5; i++){
	print(i)
}
```



需求：在lyy数据库c1集合中插入10条数据，分别为a1 a2 ... a10

```mysql
use lyy
for(var i = 0; i < 10; i++){
	db.sql.insert({uname:"a"+i,age:i})
}
```



```mysql
> use lyy
switched to db lyy //切换到lyy数据库

> for(var i = 0; i < 10; i++){
... db.sql.insert({uname:"a"+i,age:i})
... }
WriteResult({ "nInserted" : 1 })

> db.sql.find()
{ "_id" : ObjectId("62f5f5bb1b7ab6448fcad701"), "uname" : "a0", "age" : 0 }
{ "_id" : ObjectId("62f5f5bb1b7ab6448fcad702"), "uname" : "a1", "age" : 1 }
{ "_id" : ObjectId("62f5f5bb1b7ab6448fcad703"), "uname" : "a2", "age" : 2 }
{ "_id" : ObjectId("62f5f5bb1b7ab6448fcad704"), "uname" : "a3", "age" : 3 }
{ "_id" : ObjectId("62f5f5bb1b7ab6448fcad705"), "uname" : "a4", "age" : 4 }
{ "_id" : ObjectId("62f5f5bb1b7ab6448fcad706"), "uname" : "a5", "age" : 5 }
{ "_id" : ObjectId("62f5f5bb1b7ab6448fcad707"), "uname" : "a6", "age" : 6 }
{ "_id" : ObjectId("62f5f5bb1b7ab6448fcad708"), "uname" : "a7", "age" : 7 }
{ "_id" : ObjectId("62f5f5bb1b7ab6448fcad709"), "uname" : "a8", "age" : 8 }
{ "_id" : ObjectId("62f5f5bb1b7ab6448fcad70a"), "uname" : "a9", "age" : 9 }
```



#### 查询数据

**基础语法：**`db.sql.find()`

```mysql
//查询所有
> db.sql.find() //查询所有数据
{ "_id" : ObjectId("62f5cde51b7ab6448fcad6f8"), "uname" : "zs", "age" : 19 }
{ "_id" : ObjectId("62f5d01a1b7ab6448fcad6f9"), "uname" : "ls", "age" : 18 }
{ "_id" : ObjectId("62f5d01a1b7ab6448fcad6f9"), "uname" : "刘宇阳", "age" : 18 }
{ "_id" : ObjectId("62f5d01a1b7ab6448fcad6f10"), "uname" : "刘宇阳", "age" : 19 }
{ "_id" : ObjectId("62f5d01a1b7ab6448fcad6f11"), "uname" : "ww", "age" : 18 }
//查询所有数据可以加{}也可不加
db.sql.find() === db.c1.find({}) 

//查询uname=刘宇阳的数据
> db.sql.find({uname:"刘宇阳"})
{ "_id" : ObjectId("62f5d01a1b7ab6448fcad6f9"), "uname" : "刘宇阳", "age" : 18 }
{ "_id" : ObjectId("62f5d01a1b7ab6448fcad6f10"), "uname" : "刘宇阳", "age" : 19 }

//查询uname=刘宇阳并且age=19的数据
> db.sql.find({uname:"刘宇阳",age:19})
{ "_id" : ObjectId("62f5d01a1b7ab6448fcad6f10"), "uname" : "刘宇阳", "age" : 19 }

//只查询uname的数据
> db.sql.find({},{uname:1}) //只要值不等于0，不管值多少都是一样的效果
{ "_id" : ObjectId("62f5cde51b7ab6448fcad6f8"), "uname" : "zs"}
{ "_id" : ObjectId("62f5d01a1b7ab6448fcad6f9"), "uname" : "ls"}
{ "_id" : ObjectId("62f5d01a1b7ab6448fcad6f9"), "uname" : "刘宇阳"}
{ "_id" : ObjectId("62f5d01a1b7ab6448fcad6f10"), "uname" : "刘宇阳"}
{ "_id" : ObjectId("62f5d01a1b7ab6448fcad6f11"), "uname" : "ww"}

//除了uname，查询所有数据
> db.sql.find({},{uname:0})
{ "_id" : ObjectId("62f5cde51b7ab6448fcad6f8"), "age" : 19}
{ "_id" : ObjectId("62f5d01a1b7ab6448fcad6f9"), "age" : 18}
{ "_id" : ObjectId("62f5d01a1b7ab6448fcad6f9"), "age" : 18}
{ "_id" : ObjectId("62f5d01a1b7ab6448fcad6f10"), "age" : 19}
{ "_id" : ObjectId("62f5d01a1b7ab6448fcad6f11"), "age" : 18}
```



**高级语法：**

```mysql
db.集合名.find({键:值})   注：值不直接写
				 {运算符:值}
db.集合名.find({
	键:{运算符:值}
}) 
```

| 运算符 | 作用     |
| :----: | :------- |
|  $gt   | 大于     |
|  $gte  | 大于等于 |
|  $lt   | 小于     |
|  $lte  | 小于等于 |
|  $ne   | 不等于   |
|  $in   | in       |
|  $nin  | not in   |



**栗子一：查询年龄大于5岁的数据**

```mysql
> db.sql.find({age:{$gt:5}})
{ "_id" : ObjectId("62f5f5bb1b7ab6448fcad707"), "uname" : "a6", "age" : 6 }
{ "_id" : ObjectId("62f5f5bb1b7ab6448fcad708"), "uname" : "a7", "age" : 7 }
{ "_id" : ObjectId("62f5f5bb1b7ab6448fcad709"), "uname" : "a8", "age" : 8 }
{ "_id" : ObjectId("62f5f5bb1b7ab6448fcad70a"), "uname" : "a9", "age" : 9 }
```



**栗子二：查询年龄是1岁、3岁、5岁的数据**

```mysql
> db.sql.find({age:{$in:[1,3,5]}})
{ "_id" : ObjectId("62f5f5bb1b7ab6448fcad702"), "uname" : "a1", "age" : 1 }
{ "_id" : ObjectId("62f5f5bb1b7ab6448fcad704"), "uname" : "a3", "age" : 3 }
{ "_id" : ObjectId("62f5f5bb1b7ab6448fcad706"), "uname" : "a5", "age" : 5 }
```



##### pretty

**语法：**`db.sql.find().pretty()`

使JSON格式更加易读



**查询数据的默认呈现方式**

```mysql
> db.sql.find()
{ "_id" : ObjectId("62f62503f00b16e16d8e8358"), "id" : 1, "no" : "QF1", "uname" : "神龙教1", "tel" : "1111111111", "sex" : "女", "age" : 1, "school" : "研究生", "remark" : "土豪" }
{ "_id" : ObjectId("62f62503f00b16e16d8e8359"), "id" : 2, "no" : "QF2", "uname" : "神龙教2", "tel" : "1111111111", "sex" : "女", "age" : 2, "school" : "研究生", "remark" : "土豪" }
{ "_id" : ObjectId("62f62503f00b16e16d8e835a"), "id" : 3, "no" : "QF3", "uname" : "神龙教3", "tel" : "1111111111", "sex" : "女", "age" : 3, "school" : "研究生", "remark" : "土豪" }
```



**使用pretty查询数据的呈现方式**

```mysql
> db.sql.find().pretty()
{
        "_id" : ObjectId("62f62503f00b16e16d8e8358"),
        "id" : 1,
        "no" : "QF1",
        "uname" : "神龙教1",
        "tel" : "1111111111",
        "sex" : "女",
        "age" : 1,
        "school" : "研究生",
        "remark" : "土豪"
}
{
        "_id" : ObjectId("62f62503f00b16e16d8e8359"),
        "id" : 2,
        "no" : "QF2",
        "uname" : "神龙教2",
        "tel" : "1111111111",
        "sex" : "女",
        "age" : 2,
        "school" : "研究生",
        "remark" : "土豪"
}
{
        "_id" : ObjectId("62f62503f00b16e16d8e835a"),
        "id" : 3,
        "no" : "QF3",
        "uname" : "神龙教3",
        "tel" : "1111111111",
        "sex" : "女",
        "age" : 3,
        "school" : "研究生",
        "remark" : "土豪"
}
```



#### 修改数据

**基础语法：** `db.集合名.update(条件,新数据,是否新增,是否修改多条)`

```
是否新增：指条件匹配不到数据则插入(true是插入，false否不插入默认)
是否修改多条：指将匹配成功的数据都修改（true是，false否默认）默认只修改一条数据
```



`db.c1.update({uname:"a5"},{uname:"刘宇阳"})` 

直接修改等于覆盖数据，并不是真正的修改

```mysql
> db.c1.update({uname:"a5"},{uname:"刘宇阳"})
WriteResult({ "nMatched" : 1, "nUpserted" : 0, "nModified" : 1 })

> db.c1.find()
{ "_id" : ObjectId("62f5f5bb1b7ab6448fcad701"), "uname" : "a0", "age" : 0 }
{ "_id" : ObjectId("62f5f5bb1b7ab6448fcad702"), "uname" : "a1", "age" : 1 }
{ "_id" : ObjectId("62f5f5bb1b7ab6448fcad703"), "uname" : "a2", "age" : 2 }
{ "_id" : ObjectId("62f5f5bb1b7ab6448fcad704"), "uname" : "a3", "age" : 3 }
{ "_id" : ObjectId("62f5f5bb1b7ab6448fcad705"), "uname" : "a4", "age" : 4 }
//可见新数据{uname:"刘宇阳"}把旧数据{uname:"刘宇阳",age:5}覆盖了
{ "_id" : ObjectId("62f5f5bb1b7ab6448fcad706"), "uname" : "刘宇阳" }
{ "_id" : ObjectId("62f5f5bb1b7ab6448fcad707"), "uname" : "a6", "age" : 6 }
{ "_id" : ObjectId("62f5f5bb1b7ab6448fcad708"), "uname" : "a7", "age" : 7 }
{ "_id" : ObjectId("62f5f5bb1b7ab6448fcad709"), "uname" : "a8", "age" : 8 }
{ "_id" : ObjectId("62f5f5bb1b7ab6448fcad70a"), "uname" : "a9", "age" : 9 }
```

可见新数据 `{uname:"刘宇阳"}` 把旧数据 `{uname:"刘宇阳",age:5}` 覆盖了。所以就要用到高级语法了



**高级语法**

是否新增：指匹配不到数据则插入（true-是插入，false-否不插入默认）

是否修改多条：将匹配成功的数据都修改（true-是，false-否默认）

```mysql
db.集合名.update（条件， 新数据）
					  {修改器: {键:值}}
```

| 修改器  | 作用       |
| :-----: | :--------- |
|  $inc   | 递增       |
| $rename | 重命名字段 |
|  $set   | 修改字段值 |
| $unset  | 删除字段   |



**栗子一：修改 uname:a6 的值为 uanme:刘宇阳**

```mysql
> db.c1.update({uname:"a6"},{$set:{uname:"刘宇阳"}})
WriteResult({ "nMatched" : 1, "nUpserted" : 0, "nModified" : 1 })

> db.c1.find()
{ "_id" : ObjectId("62f5f5bb1b7ab6448fcad701"), "uname" : "a0", "age" : 0 }
{ "_id" : ObjectId("62f5f5bb1b7ab6448fcad702"), "uname" : "a1", "age" : 1 }
{ "_id" : ObjectId("62f5f5bb1b7ab6448fcad703"), "uname" : "a2", "age" : 2 }
{ "_id" : ObjectId("62f5f5bb1b7ab6448fcad704"), "uname" : "a3", "age" : 3 }
{ "_id" : ObjectId("62f5f5bb1b7ab6448fcad705"), "uname" : "a4", "age" : 4 }
{ "_id" : ObjectId("62f5f5bb1b7ab6448fcad706"), "uname" : "a5", "age" : 5 }
{ "_id" : ObjectId("62f5f5bb1b7ab6448fcad707"), "uname" : "刘宇阳", "age" : 6 }
{ "_id" : ObjectId("62f5f5bb1b7ab6448fcad708"), "uname" : "a7", "age" : 7 }
{ "_id" : ObjectId("62f5f5bb1b7ab6448fcad709"), "uname" : "a8", "age" : 8 }
{ "_id" : ObjectId("62f5f5bb1b7ab6448fcad70a"), "uname" : "a9", "age" : 9 }
```



**栗子二：给 {uname:"zs10"} 的 age 年龄加 5 岁或者减 5 岁**

加5岁

```mysql
> db.c1.update({uname:"a7"},{$inc:{age:5}})
WriteResult({ "nMatched" : 1, "nUpserted" : 0, "nModified" : 1 })

> db.c1.find()
{ "_id" : ObjectId("62f5f5bb1b7ab6448fcad701"), "uname" : "a0", "age" : 0 }
{ "_id" : ObjectId("62f5f5bb1b7ab6448fcad702"), "uname" : "a1", "age" : 1 }
{ "_id" : ObjectId("62f5f5bb1b7ab6448fcad703"), "uname" : "a2", "age" : 2 }
{ "_id" : ObjectId("62f5f5bb1b7ab6448fcad704"), "uname" : "a3", "age" : 3 }
{ "_id" : ObjectId("62f5f5bb1b7ab6448fcad705"), "uname" : "a4", "age" : 4 }
{ "_id" : ObjectId("62f5f5bb1b7ab6448fcad706"), "uname" : "a5", "age" : 5 }
{ "_id" : ObjectId("62f5f5bb1b7ab6448fcad707"), "uname" : "a6", "age" : 6 }
{ "_id" : ObjectId("62f5f5bb1b7ab6448fcad708"), "uname" : "a7", "age" : 12 }
{ "_id" : ObjectId("62f5f5bb1b7ab6448fcad709"), "uname" : "a8", "age" : 8 }
{ "_id" : ObjectId("62f5f5bb1b7ab6448fcad70a"), "uname" : "a9", "age" : 9 }
```



减5岁

```mysql
> db.c1.update({uname:"a7"},{$inc:{age:-5}})
WriteResult({ "nMatched" : 1, "nUpserted" : 0, "nModified" : 1 })

> db.c1.find()
{ "_id" : ObjectId("62f5f5bb1b7ab6448fcad701"), "uname" : "a0", "age" : 0 }
{ "_id" : ObjectId("62f5f5bb1b7ab6448fcad702"), "uname" : "a1", "age" : 1 }
{ "_id" : ObjectId("62f5f5bb1b7ab6448fcad703"), "uname" : "a2", "age" : 2 }
{ "_id" : ObjectId("62f5f5bb1b7ab6448fcad704"), "uname" : "a3", "age" : 3 }
{ "_id" : ObjectId("62f5f5bb1b7ab6448fcad705"), "uname" : "a4", "age" : 4 }
{ "_id" : ObjectId("62f5f5bb1b7ab6448fcad706"), "uname" : "a5", "age" : 5 }
{ "_id" : ObjectId("62f5f5bb1b7ab6448fcad707"), "uname" : "a6", "age" : 6 }
{ "_id" : ObjectId("62f5f5bb1b7ab6448fcad708"), "uname" : "a7", "age" : 7 }
{ "_id" : ObjectId("62f5f5bb1b7ab6448fcad709"), "uname" : "a8", "age" : 8 }
{ "_id" : ObjectId("62f5f5bb1b7ab6448fcad70a"), "uname" : "a9", "age" : 9 }
```



**验证语法最后两个参数（了解）**

**是否新增：**

- 第三个参数默认为 `false`，修改没有的数据不会有任何变化。
- 如果为 `true`，则找不到需要修改的数据就自动插入这个数据。

```mysql
//false 找不到需要修改的数据就没有任何效果
> db.sql.update({uname:"LiuYuYang"},{uname:"刘宇阳"},false)
WriteResult({ "nMatched" : 0, "nUpserted" : 0, "nModified" : 0 })

> db.sql.find()
{ "_id" : ObjectId("62f610cb1b7ab6448fcad70b"), "uname" : "东方求败", "age" : 999, "sex" : "男" }


//true 找不到需要修改的数据就会自动插入这个数据
> db.sql.update({uname:"LiuYuYang"},{uname:"刘宇阳"},true)
WriteResult({
        "nMatched" : 0,
        "nUpserted" : 1,
        "nModified" : 0,
        "_id" : ObjectId("62f61713b413ca375308757b")
})

> db.sql.find()
{ "_id" : ObjectId("62f610cb1b7ab6448fcad70b"), "uname" : "东方求败", "age" : 999, "sex" : "男" }
{ "_id" : ObjectId("62f61713b413ca375308757b"), "uname" : "刘宇阳" }
```



**是否修改多条：**

- 第四个参数默认为 `false`，一次只能修改一条数据。
- 如果为 `true` 则一次可以修改多条数据



准备多条数据测试

```mysql
for(var i = 1; i <= 5; i++){
	db.sql.insert({age:i})
}
```



**false**

```mysql
//修改全部age数据为100
> db.sql.update({},{$set:{age:100}},false,false)
WriteResult({ "nMatched" : 1, "nUpserted" : 0, "nModified" : 1 })

//查询数据
> db.sql.find()
{ "_id" : ObjectId("62f619c51b7ab6448fcad70c"), "age" : 100 } //只会修改第一条数据
{ "_id" : ObjectId("62f619c51b7ab6448fcad70d"), "age" : 2 }
{ "_id" : ObjectId("62f619c51b7ab6448fcad70e"), "age" : 3 }
{ "_id" : ObjectId("62f619c51b7ab6448fcad70f"), "age" : 4 }
{ "_id" : ObjectId("62f619c51b7ab6448fcad710"), "age" : 5 }
```



**true**

```mysql
//修改全部age数据为100
> db.sql.update({},{$set:{age:100}},false,true)
WriteResult({ "nMatched" : 5, "nUpserted" : 0, "nModified" : 5 })

//查询数据
> db.sql.find()
{ "_id" : ObjectId("62f619c51b7ab6448fcad70c"), "age" : 100 } //修改全部数据
{ "_id" : ObjectId("62f619c51b7ab6448fcad70d"), "age" : 100 }
{ "_id" : ObjectId("62f619c51b7ab6448fcad70e"), "age" : 100 }
{ "_id" : ObjectId("62f619c51b7ab6448fcad70f"), "age" : 100 }
{ "_id" : ObjectId("62f619c51b7ab6448fcad710"), "age" : 100 }
```



##### 综合练习

**插入数据：**`db.sql.insert( {uname:"神龙教主",age:888,who:"男",other:"非国人"});`

**需求：**

```
uname  改成     webopenfather      (修改器：$set）
age    增加     111	          	 (修改器：\$inc）
who    改字段   sex  	            (修改器：\$rename）
other  删除		            	 (修改器：\$unset）
```



**一次性修改多个设置**

```mysql
db.sql.update({uname:"神龙教主"},{
	$set:{uname:"东方求败"},
    $inc:{age:111},
    $rename:{who:"sex"},
    $unset:{other:true}
})
```



**实现：**

```mysql
//插入数据
> db.sql.insert( {uname:"神龙教主",age:888,who:"男",other:"非国人"});
WriteResult({ "nInserted" : 1 })

//查询数据
> db.sql.find()
{ "_id" : ObjectId("62f610cb1b7ab6448fcad70b"), "uname" : "神龙教主", "age" : 888, "who" : "男", "other" : "非国人" }

//修改数据
> db.sql.update({uname:"神龙教主"},{
... $set:{uname:"东方求败"},
...     $inc:{age:111},
...     $rename:{who:"sex"},
...     $unset:{other:true}
... })
WriteResult({ "nMatched" : 1, "nUpserted" : 0, "nModified" : 1 })

//查询数据
> db.sql.find()
{ "_id" : ObjectId("62f610cb1b7ab6448fcad70b"), "uname" : "东方求败", "age" : 999, "sex" : "男" }
```



#### 删除数据

**语法：**`db.集合名.remove(条件,false/true)`

```
db.sql.remove({},true)
```



- 第二个参数默认为false，代表删除全部数据
- 如果参数为true，则代表删除一条数据

```mysql
//true 删除一条数据
> db.sql.remove({age:100},true)
WriteResult({ "nRemoved" : 1 })

//false 删除全部数据
> db.sql.remove({})
WriteResult({ "nRemoved" : 7 })

> db.sql.find()
```



栗子：使用for循环删除数据

```mysql
//创建数据
> for(var i = 0; i < 5; i++){
... db.sql.insert({age:i})
... }
WriteResult({ "nInserted" : 1 })
> db.sql.find()
{ "_id" : ObjectId("62f61f211b7ab6448fcad711"), "age" : 0 }
{ "_id" : ObjectId("62f61f211b7ab6448fcad712"), "age" : 1 }
{ "_id" : ObjectId("62f61f211b7ab6448fcad713"), "age" : 2 }
{ "_id" : ObjectId("62f61f211b7ab6448fcad714"), "age" : 3 }
{ "_id" : ObjectId("62f61f211b7ab6448fcad715"), "age" : 4 }

//删除数据
> for(var i = 0; i < 5; i++){
... db.sql.remove({age:i},true)
... }
WriteResult({ "nRemoved" : 1 })
> db.sql.find()
```



#### CRUD总结

增Create

````mysql
db.集合名.insert({uname:"刘宇阳"})
````

删Delete

````mysql
db.集合名.remove(条件,默认false删除全部/true删除一个)
````

改Update

```mysql
db.集合名.update({uname:"LiuYuYang"},{$set:{uname:"刘宇阳"}},是否新增,是否修改多条)
```

查Read

```mysql
db.集合名.find({uname:"刘宇阳"})

db.集合名.find({},{uname:1}) //只查询uname字段的数据
db.集合名.find({},{uname:0}) //除了uname字段，查询其他所有数据
```



### 排序

语法：`db.集合名.find().sort({age:1代表从小到大排序 / -1代表从大到小排序})`



**栗子1：从小到大排序**

```mysql
> db.sql.find().sort({age:1})
{ "_id" : 1, "name" : "a", "sex" : "男", "age" : 1 }
{ "_id" : 4, "name" : "c", "sex" : "未知", "age" : 2 }
{ "_id" : 3, "name" : "b", "sex" : "女", "age" : 3 }
{ "_id" : 5, "name" : "a", "sex" : "女", "age" : 4 }
{ "_id" : 2, "name" : "a", "sex" : "男", "age" : 5 }
{ "_id" : 6, "name" : "b", "sex" : "人妖", "age" : 7 }
```



**栗子2：从大到小排序**

```mysql
> db.sql.find().sort({age:-1})
{ "_id" : 6, "name" : "b", "sex" : "人妖", "age" : 7 }
{ "_id" : 2, "name" : "a", "sex" : "男", "age" : 5 }
{ "_id" : 5, "name" : "a", "sex" : "女", "age" : 4 }
{ "_id" : 3, "name" : "b", "sex" : "女", "age" : 3 }
{ "_id" : 4, "name" : "c", "sex" : "未知", "age" : 2 }
{ "_id" : 1, "name" : "a", "sex" : "男", "age" : 1 }
```



### 分页

```mysql
> db.sql.find()
{ "_id" : 1, "name" : "a", "sex" : "男", "age" : 1 }
{ "_id" : 2, "name" : "a", "sex" : "男", "age" : 5 }
{ "_id" : 3, "name" : "b", "sex" : "女", "age" : 3 }
{ "_id" : 4, "name" : "c", "sex" : "未知", "age" : 2 }
{ "_id" : 5, "name" : "a", "sex" : "女", "age" : 4 }
{ "_id" : 6, "name" : "b", "sex" : "人妖", "age" : 7 }
```

**Skip：**跳过指定的数量

**Limit：**限制查询的数量



#### Count

查询当前集合有多少个数据

```mysql
> db.sql.find().count()
6
```



#### Skip

跳过2个

```mysql
> db.sql.find().sort({age:1}).skip(2)
{ "_id" : 3, "name" : "b", "sex" : "女", "age" : 3 }
{ "_id" : 5, "name" : "a", "sex" : "女", "age" : 4 }
{ "_id" : 2, "name" : "a", "sex" : "男", "age" : 5 }
{ "_id" : 6, "name" : "b", "sex" : "人妖", "age" : 7 }
```



#### Limit

只查询2个

```mysql
> db.sql.find().sort({age:-1}).limit(2)
{ "_id" : 6, "name" : "b", "sex" : "人妖", "age" : 7 }
{ "_id" : 2, "name" : "a", "sex" : "男", "age" : 5 }
```



**栗子1：跳过2条并查询两条**

```mysql
> db.sql.find().sort({age:1}).skip(2).limit(2)
{ "_id" : 3, "name" : "b", "sex" : "女", "age" : 3 }
{ "_id" : 5, "name" : "a", "sex" : "女", "age" : 4 }
```



#### **终极案例：实现分页效果**

**skip公式：**`(页数 -1) * 每页的数量`

**举个栗子**

```mysql
第一页  1  2    skip：0    ->1（页数）-1 *2（每页的数量）= 1-1*2=0
第二页  3  4    skip：2    ->2（页数）-1 *2（每页的数量）= 2-1*2=2
第三页  5  6    skip：4    ->3（页数）-1 *2（每页的数量）= 3-1*2=4
第四页  7  8    skip：6    ->4（页数）-1 *2（每页的数量）= 4-1*2=6
第五页  9  10   skip：8    ->5（页数）-1 *2（每页的数量）= 5-1*2=8
```



### 聚合查询

**聚合查询：**顾名思义就是把数据聚起来，然后分组统计

**语法：**

```mysql
db.集合名称.aggregate([
    {管道:{表达式}}
     ....
])
```



**常用管道**

```
$group 将集合中的文档分组，用于统计结果
$match 过滤数据，只要输出符合条件的文档
$sort  聚合数据进一步排序
$skip  跳过指定文档数
$limit 限制集合数据返回文档数
....
```

**常用表达式**

```
$sum  总和  $sum:1同count表示统计
$avg  平均
$min  最小值
$max  最大值
...
```



**准备数据**

```mysql
db.sql.insert([
    {name:"a",sex:"男",age:1},
    {name:"b",sex:"女",age:6},
    {name:"a",sex:"男",age:3},
    {name:"c",sex:"未知",age:5},
    {name:"b",sex:"女",age:2},
    {name:"a",sex:"男",age:9}
])
```



**栗子1：统计男生、女生的总年龄**

```mysql
//这样写法过长，会报错，仅用于阅读
db.sql.aggregate([
    {
    	$group:{
   			_id:"$sex",
    		rs:{$avg:"$age"}
    	}
    }
])

//正确写法
> db.sql.aggregate([
... {
  		//将sex字段相同的值分别分类，然后求每个分类中的所有age的总和
... 	$group:{_id:"$sex",rs:{$sum:"$age"}}
... }
... ])
{ "_id" : "男", "rs" : 13 } 1+3+9=13
{ "_id" : "女", "rs" : 8 } 6+2=8
{ "_id" : "未知", "rs" : 5 }
```



**栗子2：统计男生、女生的总人数**

```mysql
> db.sql.aggregate([
...     {
...     $group:{_id:"$sex",rs:{$sum:1}}
...     }
... ])
{ "_id" : "男", "rs" : 3 }
{ "_id" : "女", "rs" : 2 }
{ "_id" : "未知", "rs" : 1 }
```



**栗子3：求学生总数和平均年龄**

```mysql
db.sql.aggregate([
...     {
...     $group:{_id:null,num:{$sum:1},avg:{$avg:"$age"}}
...     }
... ])
{ "_id" : null, "num" : 6, "avg" : 4.333333333333333 }

db.sql.aggregate([
    {
    $group:{_id:null,sum:{$sum:1},avg:{$avg:"$age"}}
    }
])
```



**栗子4：查询男生、女生人数，按人数从大到小排序**

```mysql
> db.sql.aggregate([
...     {
...     $group:{_id:"$sex",rs:{$sum:1}}
...     },
...     {
...     $sort:{rs:-1}
...     }
... ])
{ "_id" : "男", "rs" : 3 }
{ "_id" : "女", "rs" : 2 }
{ "_id" : "未知", "rs" : 1 }
```



### 权限认证

```mysql
db.createUser({ 
    "user" : "admin",
    "pwd": "admin888",
    "roles" : [{ 
        role: "root", 
        db: "admin"
    }] 
})

db.createUser({ 
 "user" : "lyy",
 "pwd": "admin888",
 "roles" : [{ 
     role: "read", 
     db: "lyy"
 }] 
})
```



```
mongod --install --dbpath C:\MongoDB\data\db --logpath C:\MongoDB\log\mongod2.log --auth

C:\MongoDB\bin>mongod --dbpath C:\MongoDB\data\db
```



### 使用Koa操作增删改查

#### 查询

查询数据库

```javascript
 userCollection.find().toArray((err, result) => {
        if (err) {
            console.error('查询数据出错', err)
            return
        }

        console.log('查询结果', result)

        // 关闭数据库
        client.close()
    })
```



age的值写1就从小到大，-1就是从大到小进行排序。

查询数据库，以年龄从大到小进行排序

```javascript
 userCollection.find().sort({age:-1}).toArray((err, result) => {
        if (err) {
            console.error('查询数据出错', err)
            return
        }

        console.log('查询结果', result)

        // 关闭数据库
        client.close()
    })
```



#### 案例

```javascript
// 导包
const MongoClient = require('mongodb').MongoClient

//本地启动的MongoDB地址
const url = 'mongodb://localhost:27017';
//数据库名称
const dbName = 'lyy';

// 连接数据库
MongoClient.connect(url, {
    useUnifiedTopology: true,
    useNewUrlParser: true
}, (err, client) => {
    // 报错提示
    if (err) {
        console.error('MongoDB 连接出错!', err);
        return
    }

    console.log('MongoDB 连接成功!');

    // 切换数据库
    const db = client.db(dbName)
    // 切换到指定数据库的集合
    const user = db.collection('users');

    // 查询数据
    user.find().toArray(function (err, result) {
        if (err) {
            console.log('查询数据出错', err);
            return;
        }

        console.log('查询结果', result);

        // 关闭数据库
        client.close()
    })

    // 新增数据
    user.insertOne({
        username: 'lisi',
        password: '123456'
    }, (err, result) => {
        if (err) {
            console.log('新增数据出错', err);
            return
        }

        console.log('插入后的返回结果：', result);
        // 关闭数据库
        client.close()
    })

    // 修改数据
    user.update(
        { username: "lisi" },
        { $set: { password: 123123 } },
        (err, result) => {
            if(err){
                console.log('修改数据失败',err);
                return
            }

            console.log('修改数据成功，返回结果：',result);
        // 关闭数据库
        client.close()
        }
    )

    // 删除数据
    user.deleteOne({ username: 'lisi' }, (err, result) => {
        if (err) {
            console.log('删除数据失败', err);
            return;
        }

        console.log('删除数据成功', result);
    //     // 关闭数据库
        client.close()
    })
})
```





## Mongoose

- Mongoose 是一个让我们可以通过Node来操作MongoDB数据库的一个模块
- Mongoose 是一个对象文档模型（ODM）库，它是对Node原生的MongoDB模块进行了进一步的优化封装
- 大多数情况下，他被用来把结构化的模式应用到一个MongoDB集合，并提供了验证和类型装换等好处
- 基于MongoDB驱动，通过关系型数据库的思想来实现非关系型数据库



### 安装

```javascript
npm i mongoose
```



### 连接

```javascript
const mongoose = require('mongoose');

const url = 'mongodb://localhost:27017';
const dbName = 'comment';

mongoose.set('useCreateIndex', true);
mongoose.set('useFindAndModify', true);

// 开始连接
mongoose.connect(`${url}/${dbName}`, {
    useNewUrlParser: true,
    useUnifiedToplolgy: true
})

const conn = mongoose.connection;

conn.on('error', err => {
    console.log('mongoose 连接出错', err);
})

module.exports = mongoose;
```



### 约束

```javascript
const mongoose = require('./db');

const UserSchema = mongoose.Schema({
    username: {
        type: String, //字符串类型
        required: true, //必填项
        unique: true, //唯一 不能重复
    },
    password: String,
    age: Number, //数字类型
    city: String,
    gender: {
        type: Number,
        //0保密  1男  2女
        default: 0 //默认值为0
    }
}, { timestamps: true }) //时间戳  自动添加文档的创建时间

const User = mongoose.model('user', UserSchema)
```

