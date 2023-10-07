# Python基础

## 基础

### Hello World

```python
print('Hello Python!')
```



### type

查看数据类型

```python
num = 10;
str = "刘宇阳";
print(num, str)
# 10   刘宇阳

print(type(num), type(str))
# <class 'int'>   <class 'str'>
```



### isinstance

判断数据类型

```python
A = 123
B = [123.'A']
C = {'A':123,'B':45}
D = 1.23
E = 'abc'

isinstance(A, int)    #True
isinstance(B, list)   #True
isinstance(C, dict)   #True
isinstance(D, float)  #True
isinstance(E, str)    #True

type(A)== type(1)     #True
type(B)== type([])    #True
```



### keyword

查询Python中的关键字

```python
# 查看关键字
import keyword

# 打印所有的关键字
print(keyword.kwlist)
# ['False', 'None', 'True', 'and', ......]
```



### \n

```python
# 使用 \n 换行输出
print('Hello \n World!')

"""
Hello 
 World!
"""
```



### 格式化输出

**%**

**基本使用**

```python
num = 100.13

# 默认输出
print('num=%s' % num)  # num=100.13

# 转整数输出
print('num=%d' % num)  # num=100

# 转浮点数输出（默认自带4个小数）
print('num=%f' % num)  # num=100.130000

# 浮点数输出 只保留2位小数
print('保留两位小数：num=%.2f' % num)  # num=100.13

num1 = 100

# 整数输出
print("num=%d" % num)  # num=100
# 保留两位小数输出
print("num=%.2f" % num)  # num=100.13

# %g相当于%d与%.2f 值是什么就输出什么
print("num=%g" % num)  # num=100.13
print("num=%g" % num1)  # num=100
```



**应用场景**

在程序中，看到了 `%` 这样的操作符，这就是 `Python` 中格式化输出。

```python
str = "刘宇阳"
age = 20
print("我的名字叫：%s, 我今年：%d岁了" % (str, age))
```



**format**

```python
age = 20
str = "刘宇阳"

# 使用{}代替% 格式：{}.format(变量名)
print('age={}'.format(age))  # age=20

# 输出多个
print('我叫：{}, 今年：{} 岁了'.format(str, age))  # 我叫：刘宇阳, 今年：20 岁了

# 变量复用 {}中可以写入变量的下标，表示输出哪个变量（从0开始）
print('age={0},str={1},num={0}'.format(age, str))  # age=20,str=刘宇阳,num=20

# 输出浮点数
num = 10.12345
print('num={}'.format(num))  # num=10.12345

print('num={:.2f}'.format(num))  # num=10.12
```



**f-string**

```python
num = 10.123

print(f'{num}')  # 10.123
print(f'{num:.2f}')  # 10.12
print('{:.2f}'.format(num))  # 10.12
```



### input

输入交互

```python
info = input('请输入你的名字：')
print(info)
```



### 运算符

```Python
x = 10
y = 20

print(f"x + y = {x + y}")  # x + y = 30
print(f"x - y = {x - y}")  # x - y = -10
print(f"x * y = {x * y}")  # x * y = 200
print(f"x / y = {x / y}")  # x / y = 0.5
print(f"x % y = {x % y}")  # x % y = 10
print(f"x ** 2 = {x ** 2}")  # x ** 2 = 100
```



### 赋值运算符

```python
x = 20
y = 10

x += y
print(x)  # 30

x -= y
print(x)  # 20

x *= y
print(x)  # 200

x /= y
print(x)  # 20.0

x %= y
print(x)  # 0.0
```



### 数据类型转换

**字符串转整数类型**

```python
num = '10'
print(f'转换前的数据类型：{type(num)}')  # <class 'str'>
num = int(num)
print(f'转换后的数据类型：{type(num)}')  # <class 'int'>
```



**整数转字符串类型**

```python
num1 = 10
print(f'转换前的数据类型：{type(num1)}')  # <class 'int'>
num1 = str(num1)
print(f'转换后的数据类型：{type(num1)}')  # <class 'str'>
```



**整数 / 字符串转浮点数类型**

```python
num2 = 10
# num2 = '10'
print(f'转换前的数据类型：{type(num2)}')  # <class 'int'>
num2 = float(num2)
print(f'转换后的数据类型：{type(num2)}')  # <class 'float'>
print(f'转换后的值：{num2}')  # 10.0
```



**元组类型 tuple**

```python
tuple = (1, 2, 3)
print(type(tuple))  # <class 'tuple'>
```



**列表类型 list**

```python
list = [1, 2, 3]
print(type(list))  # <class 'list'>
print(list)  # [1, 2, 3]
```



**还原 eval**

```python
print(eval(num))
```



```python
# 将字符串数值还原
str = '100'
print(type(eval(str)))
# <class 'int'>

# 将字符串数组还原
list = '[1,2,3,4,5]'
print(type(eval(list)))
# <class 'list'>

# 将字符串元组还原
tuple = '(1,2,3,4,5)'
print(type(eval(tuple)))
# <class 'tuple'>

# 将字符串字典还原
dict = '{"name":"刘宇阳"}'
print(type(eval(dict)))
# <class 'dict'>
```



### 条件判断

```python
if num > 5:
    print('你输入的值大于5')
else:
    print('你输入的值小于5')

# 简写
if 10 > 5: print('简写')
```



#### and

`and` 只要有一个不成立，那么结果就为 `false`

```python
if 2 > 1 and 3 > 2:
    print('条件成立')  # 成立
else:
    print('条件不成立')
```



#### or

`or` 只要有一个成立，那么结果就为 `true`

```python
if 2 > 1 and 3 > 2:
    print('条件成立')  # 成立
else:
    print('条件不成立')
```



**输入成绩案例**

```Python
# 把输入的成绩转换为数值类型
num = int(input('请输入你的成绩：'))

if num < 60:
    print("不及格")
elif num >= 90:
    print("优秀")
elif num >= 60:
    print("及格")
else:
    print('成绩输入有误！')
```



**车票案例**

```python
# 车票
x = 0;
# 有无违禁品
y = 0;

if x:
    print('有车票 可以进站')
    if y:
        print('终于可以见到Ta了~~')
    else:
        print('有违禁品 进站失败')
else:
    print('没有车票 不能进站')
```



**猜拳小游戏案例**

```python
# 导入随机数方法
import random

# 石头：0   剪刀：1   布：2

# 电脑
player = random.randint(0, 2)

# 玩家
computer = int(input('请输入：'))

if (player == 0 and computer == 2) or (player == 1 and computer == 0) or (player == 2 and computer == 1):
    print(f'电脑：{player}, 玩家：{computer}')
    print('电脑获胜！')
elif player == computer:
    print(f'电脑：{player}, 玩家：{computer}')
    print('平局')
else:
    print(f'电脑：{player}, 玩家：{computer}')
    print('玩家获胜！')
```



### 循环语句

#### while

```python
i = 1;
sum = 0

while (i <= 10):
    print(f'第：{i} 次循环')
    # 累加器
    i += 1
    # 求和
    sum += i

print(f'1~10的和为：{sum}')
```



**求1~100的偶数和**

```python
i = 0
sum = 0

while i < 100:
    if i % 2 == 0:
        sum += i
    i+=1

print(f'1~100的偶数和为：{sum}')
```



**打印三角形**

```python
# 打印正三角
i = 1
while i <= 5:
    j = 1
    while j <= i:
        # end代表不换行
        print('⭐', end="")
        j += 1

    print('')
    i += 1
```



**模拟登录案例**

账号：admin    密码：123456

如果输入的账号或密码不正确就不断提示登录，直到登录成功为止

```python
username = input('请输入你的用户名：')
password = input('请输入你的密码：')


while 1:
    if(username == "admin" and password == "123456"):
        print('登录成功！')
        break;
    else:
        print('用户名或密码出错，请重新输入！')
        username = input('请输入你的用户名：')
        password = input('请输入你的密码：')
```



**九九乘法表**

```python
i = 1;
while i <= 9:
    j = 1
    while j <= i:
        print(f'{i}*{j}={i * j} ', end="")
        j += 1
    print('')
    i += 1
```



**while else**

如果 `break` 执行了，就不会执行 `else` 中的代码，反之执行 `else` 的代码

```python
list = [100, 200, 300, 400, 500]

i = 0
while i < len(list):
    if list[i] == 200:
        print("存在")
        break
    i += 1
else:
    print("不存在")
```

​                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           

#### for

循环字符串

```python
str = '加油，未来的架构师！'

for i in str:
    print(i)
# 加
# 油
# ，
# 未
# 来
# 的
# 架
# 构
# 师
# ！
```



**continue**

`continue` 跳出本次循环

```python
str = '加油，未来的架构师！'

for i in str:
    if i == "油":
        print('Hello')
        break #结束循环
        # continue #跳出本次循环
```



**for else**

如果 `break` 执行了，就不会执行 `else` 中的代码，反之执行 `else` 的代码

```python
list = [100, 200, 300, 400, 500]

for i in list:
    if i == 200:
        print("存在")
        break
else:
    print("不存在")
```



### len

```python
str = '加油，未来的架构师！'
print(len(str)) # 10
```



### 三元表达式

```python
n = 20
msg = "满足条件" if n > 18 else "不满足条件"
print(msg) # 满足条件
```



## 字符串

### 字符串截取

基本操作

```python
mystr = 'hello world and Hello python'

# 获取第一个字符，下标从0开始
print(mystr[1])
# e

# 截取前五个
print(mystr[0:5])
print(mystr[:5])
# hello

# 截取world
print(mystr[6:11])
# world

# 起始位置与结束位置都为空则获取全部
print(mystr[:])
# hello world and Hello python

# 从第6个开始，截取到最后一个
print(mystr[6:])
# world and Hello python

# 从第2个开始取，取到倒数第2个
print(mystr[2:-2])
# llo world and Hello pyth

# 从后向前取值
print(mystr[:-2])
# hello world and Hello pyth


# 字符串反转
print(mystr[::-1])
# nohtyp olleH dna dlrow olleh

# 每隔2个取一个
print(mystr[::2])
# hlowrdadHlopto

# 从倒数第4个开始，取到倒数第一个
print(mystr[-4:-1])
# tho

# 从下标1开始，取到下标5 步长为2
print(mystr[1:5:2])
# el
```



### 字符串常用方法

#### find

查询字符串是否包含在 `mystr` 中，如果是返回 `开始` 的索引值，否则返回 `-1`

```python
mystr = 'hello world and Hello python'

print(mystr.find('hello')) #0
print(mystr.find('world')) #6
print(mystr.find('Hello')) #16
print(mystr.find('liuyuyang')) #-1
```



#### index

跟 `find` 方法一样，只不过如果字符串不在 `mystr` 中 就会报错.

```python
mystr = 'hello world and Hello python'

# 存在就返回他的开始下标
print(mystr.index('and')) # 12

# 不存在就会报错
print(mystr.index('dna'))
```



#### count

返回指定字符串出现的次数

```python
mystr = 'hello world and Hello python'

# 查询h出现的次数
print(mystr.count('h')) #2

# 如果一个都没有的话返回0
print(mystr.count('hh')) #0
```



#### split

`split` 以空格进行切分，转换为数组

```python
mystr = 'hello world and Hello python'

res = mystr.split(' ')

print(res)
# ['hello', 'world', 'and', 'hello', 'python']

print(type(res))
# <class 'list'>
```



如果是这样的字符串想要转换为数组，则不能使用 `split` 

```python
mystr = "abcdefg"
mystr = mystr.split('')
print(mystr)
# ValueError: empty separator
```



我们可以使用 `list` 将字符串转换为数组

```python
mystr = "abcdefg"
print(list(mystr))
# ['a', 'b', 'c', 'd', 'e', 'f', 'g']
```



#### join

`join` 数组转字符串

```python
list = ['1', '2', '3']

print(type(list))
# <class 'list'>

str = ','
res1 = str.join(list)
print(res1)
# 1,2,3

str = ''
res2 = str.join(list)
print(res2)
# 123

print(type(res1),type(res2))
# <class 'str'>  <class 'str'>
```



#### swapcase

大写转小写，小写转大写

```python
x = 'abcd'
y = 'ABCD'
z = 'AbCd'
print(f"{x} 转换为：{x.swapcase()}") # abcd 转换为：ABCD
print(f"{y} 转换为：{y.swapcase()}") # ABCD 转换为：abcd
print(f"{z} 转换为：{z.swapcase()}") # AbCd 转换为：aBcD
```



#### upper

转大写

```python
str = 'aAsmr3idd4bgs7Dlsf9eAF'
print(str.upper())
#AASMR3IDD4BGS7DLSF9EAF
```



#### lower

转小写

```python
str = 'AaSMR3IDD4BGS7dLSF9Eaf'
print(str.lower())
# aasmr3idd4bgs7dlsf9eaf
```



#### isdigit

字符串有个内置判断函数，如果字符是纯数字返回 `True`  反之 `False`

```python
str1 = '123'
print(str1.isdigit())
#纯数字：True

str = '123aaa'
print(str.isdigit())
#非纯数字：False
```



#### replace

字符串替换

```python
# 将str字符串中的Python替换为JavaScript
str = "Python is good"
print(str.replace('Python','JavaScript'))
#JavaScript is good
```



#### strip

移除字符串头尾指定的字符

```python
# 去除前后的空格
str1 = '    he  llo  '
print(str1.strip())
#he  llo

# 去除前后的0
str2 = '000001231231000'
print(str2.strip('0'))
#1231231
```

注意 `strip` 只能去除前后指定的字符，中间的字符无法去除



#### startswith

判断 `str` 字符串是否以 `book` 开头

```python
str = "this is a book"
print(str.startswith('this'))
#true
```



#### endswith

判断 `str` 字符串是否以 `book` 结尾

```python
str = "this is a book"
print(str.endswith('book'))
#true
```







## 列表

```python
list = [1, 'a', 5, 'b', 9, 'ten']
```



### 列表遍历

**for循环遍历**

```python
# For循环遍历
for i in list:
    print(i)
```



**while循环遍历**

```python
# while循环遍历
i = 0
while i < len(list):
    print(list[i])
    i += 1
```



### for else

`for else` 循环完毕之后只要没有遇到 `break` 结束循环就会执行 `else` 里面的代码，如果写了 `break` 就不会执行 `else` 的代码

```python
list = ['a', 'b', 'c', 'd', 'e']

for i in list:
    if i == 'b':
        print('存在') # 执行
        break # 遇到break则else不再执行
else:
    print('不存在')
```



### range

- start: 计数从 start 开始。默认是从 0 开始。例如range（5）等价于range（0， 5）;

```python
# range(计数)
for i in range(5):
    print(i)
# 0
# 1
# 2
# 3
# 4
```

- stop: 计数到 stop 结束，**但不包括 stop**。例如：range（0， 5） 是[0, 1, 2, 3, 4]没有5
- step：步长，默认为1。例如：range（0， 5） 等价于 range(0, 5, 1)

```python
# 利用步长求1~100之间的所有偶数
for i in range(0, 101, 2):
    print(i)
```



### 列表常用操作

#### append

添加元素到列表中（默认添加到最后一位）

```python
list = [1, 2, 3]
list.append(4)
print(list)
# [1, 2, 3, 4]
```



#### extend

将 `list1` 列表合并到 ` list` 列表中

```python
l1 = [1,2,3]
l2 = [4,5,6]

l1.extend(l2)
print(l1)
# [1, 2, 3, 4, 5, 6]
```



```python
# extend添加方式
l1 = [1, 2, 3]
l2 = [2, 1]
l1.extend(l1)
print(l1)
# [1, 2, 3, 2, 1]

# append添加方式
l2 = [4, 5]
l1.append(l2)
print(l1)
# [1, 2, 3, 2, 1, [4, 5]]
```



#### insert

在指定的下标位置插入指定的元素

```python
list = [1, 2, 3]

# 列表.insert(添加的下标位置,添加的值)

# 在列表最前面添加一个值：666
list.insert(0, 666)
print(list)
# [666, 1, 2, 3]
```



在列表索引为1的位置添加一个值：666

```python
arr = [1, 2, 3]
arr.insert(1, 666)
print(arr)
# [1, 666, 2, 3]
```



#### enumerate

`enumerate` 用于在 `for` 循环中得到计数，利用它可以同时获得索引和值，即需要 `index` 和 `value` 值的时候可以使用

该方法会返回一个下标，并返回元素

```python
list = [1, 'a', 5, 'b', 9, 'ten']

for index,item in enumerate(list):
    print(f'第{index}位置元素为：{item}')

# 第0位置元素为：1
# 第1位置元素为：a
# 第2位置元素为：5
# 第3位置元素为：b
# 第4位置元素为：9
# 第5位置元素为：ten
```



指定索引从10开始

```python
arr = [1, 2, 3]

for index, item in enumerate(arr, 10):
    print(index, item)

# 10 1
# 11 2
# 12 3
```



#### index

获取指定元素的下标

```python
list = ['a', 'b', 'c', 'd', 'e']

# 获取list数组中元素：'e' 的下标
print(list.index('e'))
# 4
```



#### count

获取指定元素在列表中出现的次数

```python
list = ['a', 'b', 'a', 'c', 'b', 'b', 'd']

# 获取list数组中：'b' 元素出现的次数
print(list.count('b'))
# 3
```



#### pop

从后往前删除

```python
list = ['a', 'b', 'c', 'd', 'e']

# pop没有参数则默认从后往前删除
list.pop()
print(list)
# ['a', 'b', 'c','d]

# list = ['a', 'b', 'c', 'd']
# 将倒数第二个元素：'c' 删除
list.pop(-2)
print(list)
# ['a', 'b', 'd']
```



#### del

指定下标删除

```python
list = ['a', 'b', 'c', 'd', 'e']

# 删除：'c'
del list[2]
print(list)
# ['a', 'b', 'd', 'e']
```



#### remove

根据指定元素删除

```python
list = ['a', 'b', 'c', 'd', 'e']

# 删除：'b'
list.remove('b')
print(list)
# ['a', 'c', 'd', 'e']
```



删除字典类型

```python
list = [
    {"id": 1, "name": "zs", "age": 20},
    {"id": 2, "name": "ls", "age": 22},
    {"id": 3, "name": "ww", "age": 21},
]

list.remove({"id": 2, "name": "ls", "age": 22})

print(list)
# [{'id': 1, 'name': 'zs', 'age': 20}, {'id': 3, 'name': 'ww', 'age': 21}]
```



#### sort

排序

```python
list = [1, 3, 2, 4, 6, 5]

# 默认从小到大排序
# 默认sort() 相当于 sort(reverse=False)
list.sort()
print(list)
# [1, 2, 3, 4, 5, 6]

# 从大到小排序
list.sort(reverse=True)
print(list)
# [6, 5, 4, 3, 2, 1]
```



#### reverse

```python
list = ['o', 'l', 'l', 'e', 'h']

# 反转
list.reverse()
print(list)
# ['h', 'e', 'l', 'l', 'o']
```



#### 合并数组

```python
list1 = [11, 22]
list2 = [33, 44, 55]
print(list1 + list2)
# [11, 22, 33, 44, 55]
```



#### 拷贝数组

```python
list1 = [11, 22]
list2 = list1 * 2
print(list2)
# [11, 22, 11, 22]
```



### 列表嵌套

```python
list = [[1, 2, 3], [4, 5, 6]]
# 取列表中元素：6
print(list[1][2])
# 6
```



### 查找元素

```python
list = ['a', 'b', 'c', 'd', 'e']

# list列表中 存在 元素：'b' 就返回true
if 'b' in list:
    print('存在')
else:
    print('不存在')

# list列表中 不存在 元素：'b' 就返回true
if 'b' not in list:
    print('不存在')
else:
    print('存在')
```



复杂一点的查找

```python
data = [{id: 1}, {id: 2}, {id: 3}]
if {id: 2} in data:
    print('有')
else:
    print('无')
```

**or**

```python
list = ['a', 'b', 'c', 'd', 'e']

for i in list:
    if i == 'b':
        print('存在') # 执行
        break
else:
    print('不存在')
```

**or**

```python
# 找到ID 等于 33的那一项
data = [{'id': 11}, {'id': 22}, {'id': 33}]
for item in data:
    if item['id'] == 33:
        print(item) # {'id': 33}
```



## 公共方法

### 合并

```python
# 数组合并
list1 = [11, 22]
list2 = [33, 44, 55]
print(list1 + list2)
# [11, 22, 33, 44, 55]

# 字符串合并
str1 = 'hello '
str2 = 'world'
print(str1 + str2)
# hello world
```



### 复制

```python
# 快速复制10个-
print('-' * 10)
# ----------

# 复制数组
list1 = [11, 22]
list2 = list1 * 2
print(list2)
# [11, 22, 11, 22]

# 复制字符串
str1 = 'hello '
str2 = str1 * 2
print(str2)
# hello hello


```



### 查找

```python
# 数组查找
list = [1, 3, 5, 7, 2]
if 3 in list:
    print('存在')  # 存在
else:
    print('不存在')

# 字符串查找
str = 'abcdefg'
if 'o' in str:
    print('存在')
else:
    print('不存在')  # 不存在
```



复杂一点的查找

```python
data = [{id: 1}, {id: 2}, {id: 3}]
if {id: 2} in data:
    print('有')
else:
    print('无')
```



## 元组

`Python` 的元组与列表类似，不同之处在于元组的元素不能修改。元组使用小括号，列表使用方括号

获取李四

```python
tuple = ('张三', '李四', '王五')
print(tuple[1])
```



元组里可以嵌套数组

```python
tuple = ([1, 2, 3], 2)
print(tuple[0])
```



元组跟列表不同的是前者可以修改值，后者不能修改值

```python
tuple = ([1, 2, 3], 2)
tuple[1] = 200 #报错
print(tuple)
```



## 字典

在 `Python` 中字典相当于 `JavaScript` 中的对象

```python
obj = {'name': "刘宇阳", 'age': 20}

print(obj)
# {'name': '刘宇阳', 'age': 20}

# 用字面量获取值
print(obj['name'])
# 刘宇阳

# 用get获取值
print(obj.get('age'))
# 20
```

**注意：** 在Python中获取对象的值不能直接点出来

```python
obj.name # 错误
```



### 字典默认值

```python
# obj.get(需要获取的值,默认值)
print(obj.get('age'))
# 20

# 如果有sex就获取sex，没有就获取默认值：男
print(obj.get('sex', '男'))
# 男
```



### 修改 / 添加

```python
obj['age'] = 25 #添加字典
print(obj['age']) #修改字典
# 25
```



### del

删除 `obj` 中的 `age` 数据

```python
del obj['age']
print(obj)
# {'name': '刘宇阳'}
```



### clear

清空字典

```python
# 清空字典
obj.clear()
print(obj)
# {}
```



### keys

获取字典中所有键

```python
obj = {'name': "刘宇阳", 'age': 20}
print(obj.keys())
# dict_keys(['name', 'age'])
```



### values

获取字典中所有值

```python
obj = {'name': "刘宇阳", 'age': 20}
print(obj.values())
# dict_values(['刘宇阳', 20])
```



### items

获取字典中键值对

```python
obj = {'name': "刘宇阳", 'age': 20}
print(obj.items())
# dict_items([('name', '刘宇阳'), ('age', 20)])
```



### update

合并两个字典

```python
d1 = {"name": "YuYang", "age": 20}
d2 = {"hobby": "敲代码"}

d2.update(d1)

print(d2)
# {'hobby': '敲代码', 'name': 'YuYang', 'age': 20}
```

**or**

```python
d1 = {"name": "YuYang", "age": 20}
d2 = {"hobby": "敲代码"}

print({**d1, **d2})
# # {'name': 'YuYang', 'age': 20, 'hobby': '敲代码'}
```



### 综合案例

**一、** 求该字符串中每个字符串出现的次数：`ajakdlasjdkajksldjlasjdlsa`

```python
mystr = "ajakdlasjdkajksldjlasjdlsa"
obj = {}

for item in mystr:
    if item in obj.keys():
        obj[item] += 1
    else:
        obj[item] = 1

print(obj)
# {'a': 6, 'j': 5, 'k': 3, 'd': 4, 'l': 4, 's': 4}
```



**二、** 接着上一个案例求出哪个字符串出现次数最多，一共出现多少次

```python
# 1. 求出每个字符串出现的次数
mystr = "ajakdlasjdkajksldjlasjdlsa"
obj = {}

for item in mystr:
    if item in obj.keys():
        obj[item] += 1
    else:
        obj[item] = 1

print(obj)

# 2. 求出哪个字符串出现次数最多，一共出现多少次
max = 0
maxStr = ""
for item in obj:
    if obj[item] > max:
        max = obj[item]
        maxStr = item
print(f"出现最多的是：{maxStr}  一共出现了：{max} 次")
# 出现最多的是：a  一共出现了：6 次
```



## 还原

```python
print(type("[{},{}]"))
# <class 'str'>

# 还原
print(type(eval("[{},{}]")))
# <class 'list'>
```



把字符串对象还原为真正的对象

```python
d = '{"name": "lyy", "age": 20}'
print(type(d))
# <class 'str'>

# 还原
o = eval(d) # {"name": "lyy", "age": 20}
print(o['age'])  # 20
print(type(o))
# <class 'dict'>
```



## 函数

定义一个简单的函数

```python
# 定义函数
def fn():
    print('Hello')


# 调用函数
fn()
```



**查看函数说明**

```python
def info():
    """打印数据"""
    print('Hello')


# 查看函数说明
help(info)
"""
info()
    打印数据
"""
```



**函数传参**

```python
def fn(x, y):
    print(f'{x} + {y} = {x + y}')
    # 10 + 30 = 40


fn(10, 30)
```



**函数嵌套**

```python
def fn1():
    fn2()
    print(2)


def fn2():
    print(1)


fn1()
# 1
# 2
```



**return 返回多个**

```python
def fn(x, y):
    return x, y  # 默认以元组返回
    # return [x, y]


res = fn(10, 20)
print(res)  # (10, 20)
```



### global

默认情况下函数内部的局部变量不会影响到函数外部的全局变量，所以可以通过 `global` 让局部变量影响全局变量

**局部变量**

局部变量修改全局变量 `num` 相当于重新定义了一个 `num` 变量，修改函数的局部变量 `num` 不会影响到全局变量

```python
num = 100


def fn():
    num = 200
    print('局部：',num)


# 只影响局部作用域的变量，所以num被修改了
fn()
# 200

# 不影响全局变量，所以并没有发生改变
print('全局：',num)
# 100
```



**全局变量**

加上 `global` 标识符可以使局部变量影响到全局变量，这样在局部变量就可以修改全局变量的值

```python
num = 100


def fn():
    # 通过global可以直接对全局变量进行操作
    global num
    num = 200
    print('局部：',num)


# 全局变量被修改
fn()
# 200

# 输出全局变量
print('全局：',num)
# 200
```



### nonlocal

通过 `nonlocal` 可以对最近一级的函数局部变量进行操作

```python
x = 10


def fn():
    x = 20

    def fn1():
        x = 30  # 这个被改变为40

        def fn2():
            nonlocal x
            x = 40

        fn2()
        print('fn1', x)  # fn1 40

    fn1()
    print('fn', x)  # fn 20


fn()
print(x)
# 10
```

`global` 与 `nonlocal` 的区别：一个用于操作全局变量，一个用于操作函数内部的局部变量



### 参数

#### 参数默认值

不传 `y` 值，则它的值为默认值 `100`

```python
def fn(x, y=100):
    print(x + y)  # 110


fn(10)
```



给 `y` 传值之后就会覆盖掉默认值

```python
def fn(x, y=100):
    print(x + y)  # 210


fn(10,200)
```

**注意：** 带有默认值的参数一定要位于参数列表的最后面



#### **剩余参数**

加了星号 `*` 的变量 `args` 会存放所有**未命名参数**，`args` 的值为元组

```python
def fn(x, y, *args):
    print(x, y)  # 100 200
    print(args)  # (300, 400, 500)
    print(args[1])  # 300


fn(100, 200, 300, 400, 500)
```



而加 `**` 的变量 `kwargs` 会存放**命名参数**，如：`key = value` 的参数， `kwargs` 为字典.

```python
def fn(x, y, **kwargs):
    print(kwargs)
    # {'a': 300, 'b': 400, 'c': 500}

fn(100, 200, a=300, b=400, c=500)
```



如果 `*args` 与 `*kwargs` 同时存在的话，`*kwargs` **必须**写在最后

```python
def fn(x, y, *args, **kwargs):
    print(x, y)
    # 100 200

    print(args)
    # (600, 700)

    print(kwargs)
    # {'a': 300, 'b': 400, 'c': 500}


fn(100, 200, 600, 700, a=300, b=400, c=500)
```



**注意：** 如果有 `kwargs`，必须在最后

```python
def fun(*args, **kwargs):
    print(args, *args)  # (5, 2, 3, 1, 4) 5 2 3 1 4
    print(kwargs, *kwargs)  # {'a': 1, 'b': 4} a b


fun(5, 2, 3, a=1, b=4)
```



## 匿名函数

**匿名函数：**lambda 形参1，形参2: 表达式

```python
fn = lambda a, b: print(a * b)
fn(10, 5)
```

```python
fn = lambda a, b: a * b
res = fn(10, 5)
print(res)  # 50
```

- 匿名函数只能写简单的单行代码
- 匿名函数可以不写 `return`，它运行的结果就是返回的结果



### 对比普通函数

```python
# 普通函数
def fn1(a, b):
    return print(a + b)


fn1(10, 20)


# 匿名函数
fn2 = lambda a, b: print(a + b)
fn2(10, 20)
```



### 应用场景

求：加减乘除

```python
def sum(n):
    a = 100
    b = 200
    return n(a, b)


print(sum(lambda a, b: a + b))  # 加
print(sum(lambda a, b: a - b))  # 减
print(sum(lambda a, b: a * b))  # 乘
print(sum(lambda a, b: a / b))  # 除
```



## 内置函数

### reversed

反转字符串 | 数组

```python
# 反转数组
arr = reversed([3, 2, 1])
print(list(arr))
# [1, 2, 3]

# 反转字符串
str = reversed('abcdefg')
print(list(str))
# ['g', 'f', 'e', 'd', 'c', 'b', 'a']
```



### sum

求数组中的累加和

```python
arr = [1, 3, 5, 7, 9, 10]
sum = sum(arr)
print(sum)  # 35
```



### min & max

```python
arr = [1, 3, 5, 7, 9, 10]
min = min(arr)
max = max(arr)
print(min, max)  # 1 10
```



## 解构赋值

```python
a, b, c = 1, 2, 3

print(a, b, c)
# 1 2 3
```



### 字典解构

```python
dict = {
    'name': "刘宇阳",
    'age': 20
}

# 解构键
name, age = dict
# 解构值
name1, age2 = dict.values()

print(name, age)
# name age

print(name1, age2)
# 刘宇阳 20
```



### 数组解构

```python
list = [1, 2, 3]

a, b, c = list

print(a, b, c) # 1 2 3
```



### 交换变量

```python
# 方法一、
temp = None
a = 10
b = 20

temp = a
a = b
b = temp
print(a, b)  # 20 10


# 方法二、
a = 10
b = 20
print(a, b)  # 10 20

a, b = b, a
print(a, b)  # 20 10



# 方法三、
a = 10
b = 20
print(a, b)  # 10 20

a = a + b  # 30
b = a - b  # 10
a = a - b  # 20

print(a, b)  # 20 10
```



## 学生管理系统 1.0

```python
data = [
    {
        "id": "11",
        "name": "刘宇阳",
        "age": 20
    },
    {
        "id": "22",
        "name": "刘宇阳",
        "age": 20
    },
    {
        "id": "33",
        "name": "刘宇阳",
        "age": 20
    }
]

b = None


# 判断是否存在
def is_info(id):
    global b

    for item in data:
        if item['id'] == id:
            b = True
            break
        else:
            b = False


# 添加学生
def add_info():
    id = input("请输入学生学号：")
    name = input("请输入学生姓名：")
    age = input("请输入学生年龄：")

    # 判断该同学是否存在
    is_info(id)
    if b:
        return print('该同学已存在')

    info = {"id": id, "name": name, "age": age}
    data.append(info)
    print(data)


# 删除学生
def del_info():
    id = input("请输入你要删除的学生学号：")

    is_info(id)
    if not b:
        return print('该同学不存在')

    for item in data:
        if item['id'] == id:
            data.remove(item)
            print("删除学生成功")


# 编辑学生
def emit_info():
    id = input('请输入学生的学号：')

    is_info(id)
    if not id:
        return print('该学生不存在')

    for item in data:
        if item['id'] == id:
            print('修改学生信息')
            item['name'] = input("请输入学生姓名：")
            item['age'] = input("请输入学生年龄：")
            print('编辑成功！')
            print(data)


# 查询学生
def find_info():
    type = int(input('请回复指令：1:查询全部 or 2:查询单个：'))

    if type == 1:
        print('学生列表信息：')
        for item in data:
            print(item)
    elif type == 2:
        id = input('请输入该学生的学号：')

        print('该学生的信息：')
        for item in data:
            if item['id'] == id:
                print(item)


# 信息
def print_menu():
    print("---------------------------")
    print("      学生管理系统 V1.0")
    print("1: 添加学生")
    print("2: 删除学生")
    print("3: 修改学生")
    print("4: 查询学生")
    print("5: 显示所有学生")
    print("6: 退出系统")
    print("---------------------------")


def info():
    while True:
        # 打印信息
        print_menu()

        n = int(input("请输入操作命令："))

        if n == 1:
            # 添加学生
            add_info()
        elif n == 2:
            # 删除学生
            del_info()
        elif n == 3:
            # 修改学生
            emit_info()
        elif n == 4:
            # 查询学生
            find_info()
        elif n == 5:
            # 退出系统
            exit_flag = input("亲,你确定要退出么?~~~~(>_<)~~~~(yes or no) ")
            if exit_flag == "yes":
                break
        else:
            print("输入有误,请重新输入......")


info()

```



## 递归

阶乘

```python
def fn(n):
    if n == 1:
        return 1
    return n * fn(n - 1)


res = fn(5)
print('5的阶乘为：', res)
```



## 列表推到式

**语法**

```
[表达式 for 迭代变量 in 可迭代对象 [if 条件表达式] ]
```

`[if 条件表达式]` 不是必须的，可以使用，也可以省略。



**使用列表推到式过滤列表中大于5的值**

```python
l = [4, 6, 7, 3, 2]

# 返回列表中大于5的值
res = [x for x in l if x > 5]
print(res)
# [6, 7]
```



**快速生成5个数据**

```python
l = [4, 6, 7, 3, 2]

# 快速生成5个数据
res = [x + 1 for x in range(5)]
print(res)
# [1, 2, 3, 4, 5]
```



**对比传统方法**

```python
l = []

# 该方法等价于以上方法
for x in range(5):
    l.append(x + 1)

print(l)
```



**利用列表推到式求奇数、偶数**

```python
even = [x for x in range(10) if x % 2 == 0]
print(even)  # [0, 2, 4, 6, 8]

odd = [x for x in range(10) if x % 2 != 0]
print(odd)  # [1, 3, 5, 7, 9]
```



**列表推到式可以写多个循环**

```python
res = [[x, y] for x in range(5) for y in range(2)]
print(len(res))  
# 循环了10次，所以里面有10个

print(res)
# [[0, 0], [0, 1], [1, 0], [1, 1], [2, 0], [2, 1], [3, 0], [3, 1], [4, 0], [4, 1]]
```



## 数据类型转换

```python
# 列表转换
list = [11, 22, 33, 11, 22]

# 列表转换为集合
print(set(list))
# 集合自带去重功能：{33, 11, 22}

# 列表转换元组
print(tuple(list))
# (11, 22, 33, 11, 22)
```



使用 `set` 数组去重

```python
list1 = [11, 22, 33, 11, 22]

# 使用set转换为集合然后达到去重效果
newList = set(list1)

# 然后再将集合转换为数组就实现了数组去重
print(list(newList))
# [33, 11, 22]
```



## 扩展

### 布尔值取反

```python
value = True
print(not value) # False
```



### 一行代码交互两个变量的值

```python
a = 10
b = 20

# 核心代码
a, b = b, a

print(a, b) #20 10
```



## 文件

**打开方式**

读取：r （默认）

写入：w

追加：a

读取图片：rb

写入图片：wb

```python
f = open("文件名", "打开方式", 编码)
```





### w_写入文件数据

有 `123.txt` 就打开，没有就新建

```python
# open(文件名, 打开方式, 编码格式)

# 打开文件
w = open("123.txt", "w", encoding="utf-8")

# 写入数据
w.write('Hello 刘宇阳')

# 关闭文件
w.close()
```



### a_追加文件数据

重新写入数据就会覆盖之前的数据，如果我们想要在原有内容上追加数据，则打开方式为：`a` 表示追加数据

```python
a = open("123.txt", "a", encoding="utf-8")

# 追加数据
a.write('Hello 刘宇阳')

# 关闭文件
a.close()
```



### r_读取文件数据

**读取文件中所有数据**

```python
# 读取文件
r = open('123.txt', 'r', encoding="utf-8")

# 读取数据
content = f.read()
print(content)
# Hello 刘宇阳

# 关闭文件
r.close()
```



`123.txt` 文件中的数据

```txt
111
222
333
```



**读取文件中单行数据**

`readline` 用于读取文件中单行的数据

```python
# 读取单行
f = open('123.txt', 'r', encoding="utf-8")

# 读取内容
content = f.readline()
print(content)  # 111

# 关闭文件
f.close()
```



`readlines` 用于读取文件中指定行的数据，并以数组形式返回

```python
# 读取文件中单行数据
f = open('123.txt', 'r', encoding="utf-8")

# 读取内容
content = f.readlines()

print(content)
# ['111\n', '222\n', '333']
print(content[0])
# 111

# 关闭文件
f.close()
```



### 文件备份案例

```python
fileName = input('请输入需要备份的文件名称：')

# 读取文件内容
read = open(fileName, 'r', encoding='utf-8')
content = read.read()
read.close()  # 关闭文件

# 拿到.后缀的下标
index = fileName.find('.')
# 通过后缀的下标截取到文件名称
new_file_name = fileName[:index] + '[备份].txt'
# 123[备份].txt

# 写入文件内容
write = open(new_file_name, 'w', encoding='utf-8')
write.write(content)
write.close()
```



### 修改文件名称

```python
import os

# os.rename('旧的文件名称', '新的文件名称')
os.rename('123.txt', '321.txt')
```



### 删除文件

```python
import os

os.remove('321.txt')
```



### 判断文件是否存在

```python
import os

# 判断文件
res = os.path.exists('123.txt')
# 判断文件夹
res1 = os.path.exists('wenjianjia')
print(res,res1)  # True存在 False不存在
```



### 创建文件夹

```python
# 创建文件夹
os.mkdir('wenjianjia')
```



### 获取文件夹下的文件

```python
# 获取当前目录下所有文件 以数组形式
files = os.listdir()

print(files)
# ['.idea', '123.txt', 'index.py', 'wenjianjia']
```



### 查看当前文件夹路径

```python
# 查看当前路径
print(os.getcwd())
# C:\Users\33111\Desktop\桌面\杂七八乱\pythonProject
```



### 切换到指定文件夹路径

```python
os.chdir("./wenjianjia")
print(os.getcwd())
# C:\Users\33111\Desktop\桌面\杂七八乱\pythonProject\wenjia
```



### 删除文件夹

```python
# 删除文件夹
os.rmdir('wenjianjia')
```



### 批量更改文件夹名称案例

```python
import os

# 创建一个新的文件夹：wenjianjia
os.mkdir('wenjianjia')

# 切换到wenjiajia这个文件夹中
os.chdir('./wenjianjia')

names = ['a', 'b', 'c']
for item in names:
    # 批量创建文件
    open(item, 'w', encoding='utf-8')

# 批量更改文件夹名称
file_names = os.listdir()
for item in file_names:
    os.rename(item, '[传智出品]_' + item + '.txt')
```



### with

`with` 可以自动调用 `f.close()`，可以不用手动关闭文件，有效防止遗忘

```python
with open('123.txt', 'r', encoding="utf8") as f:
    print(f.read())  # Hello World!
```



## 学生管理系统 2.0

```python
import os

stu_info = []


def print_info():
    """提示信息"""
    print("---------------------------")
    print("      学生管理系统 V2.0")
    print(" 1:添加学生")
    print(" 2:删除学生")
    print(" 3:修改学生")
    print(" 4:查询学生")
    print(" 5:显示所有学生")
    print(" 6:保存数据")
    print(" 7:退出系统")
    print("---------------------------")


def add_stu():
    """添加学生信息"""
    name = input('请输入学生姓名：')
    age = input('请输入学生年龄：')

    if stu_in(name):
        print('该学生已存在，请更换')
        return

    stu_info.append({'name': name, 'age': age})
    print('添加成功！')


def del_stu():
    """删除学生信息"""
    global stu_info

    name = input('请输入需要删除的学生：')

    info = stu_in(name)
    if info:
        stu_info.remove(info)
        print('删除成功')
        return
    else:
        print('没有找到你要删除的学生')


def modify_stu():
    """修改学生信息"""
    name = input('请输入需要修改的学生：')

    item = stu_in(name)

    if item:
        name = input('请输入需要修改的姓名：')
        age = input('请输入需要修改的年龄：')

        item['name'] = name
        item['age'] = age
    else:
        print('没有找到该学生')


def find_stu():
    """查询学生信息"""
    name = input('请输入需要查询的学生：')

    item = stu_in(name)

    if item:
        print(f"姓名：{item['name']}  年龄：{item['age']}")
    else:
        print('没有找到该学生')


def show_stu():
    """展示所有学生信息"""
    if stu_info:
        for item in stu_info:
            print(f"学生姓名：{item['name']}  学生年龄：{item['age']}")
    else:
        print('暂无数据~~')


def stu_in(name):
    """判断学生是否在列表中"""
    for item in stu_info:
        if item['name'] == name:
            return item
    else:
        return


def save_data():
    """保存数据到文件中"""
    # 创建info.txt文件 用于存储学生信息
    f = open('info.txt', 'w', encoding='utf-8')
    # 数据写入到本地文件
    f.write(str(stu_info))
    # 关闭文件
    f.close()
    
    print('保存成功！')


def load_data():
    """加载数据"""
    global stu_info

    # 如果文件存在就执行
    if os.path.exists('info.txt'):
        # 读取
        f = open('info.txt', 'r', encoding='utf-8')
        content = f.read()
        # 将字符串数组还原为数组
        stu_info = eval(content)
    else:
        pass


def main():
    """程序入口函数"""
    load_data()

    while True:
        # 提示信息
        print_info()
        n = int(input("请输入要执行的操作: "))
        if n == 1:
            add_stu()
        elif n == 2:
            del_stu()
        elif n == 3:
            modify_stu()
        elif n == 4:
            find_stu()
        elif n == 5:
            show_stu()
        elif n == 6:
            save_data()
        elif n == 7:
            print("程序已退出！")
            break


main()
```



## 面向对象

```python
class Person(object):
    def info(self):
        print('Hello World!')


person = Person()
person.info()
# Hello World!
```



### 定义类

规范：类名首字母大写，如 `Person`

```python
class Person(object):
    def info(self):
        print('Hello')
```



**基本使用**

```python
class Person(object):
    def hello(self):
        print('hello')

    def world(self):
        print('world')


p = Person()

# 调用方法
p.hello()  # hello
p.world()  # world

# 设置属性
p.name = '刘宇阳'
p.age = 20
p.sex = '男'

print(p.name) # 刘宇阳
print(p.age) # 20
print(p.sex) # 男
```



### 魔法方法

#### self

```python
class Person(object):
    # self相当于JavaScript中的this，指向的是Person
    def info(self):
        name, age, sex = self.name, self.age, self.sex
        print(f"我叫：{name} 今年：{age} 岁 是一个 {sex}生")


p = Person()

p.name = '刘宇阳'
p.age = 20
p.sex = '男'

p.info()
```



#### __init__

`__init__` 初始化方法，在创建对象时候会自动调用，相当于 `JavaScript` 中的构造器

```python
class Person(object):
    # __init__()方法，在创建一个对象时默认被调用，不需要手动调用
    def __init__(self):
        print('Hello World!')


person = Person()
# Hello World!
```



```python
class Person(object):
    # 初始化方法
    def __init__(self):
        self.name = "刘宇阳"
        self.age = 20
        self.sex = "男"

    def info(self):
        name, age, sex = self.name, self.age, self.sex
        print(f"我叫：{name} 今年：{age} 岁 是一个 {sex}生")


p = Person()
p.info()
```



**传参**

```python
class Person(object):
    # 初始化方法
    def __init__(self, name, age, sex):
        self.name = name
        self.age = age
        self.sex = sex

    def info(self):
        name, age, sex = self.name, self.age, self.sex
        print(f"我叫：{name} 今年：{age} 岁 是一个 {sex}生")


p = Person('刘宇阳', 20, '男')
p.info()
```



#### str

`__str__` 在 ` Person` 对象被 `print` 打印时会自动触发，主要用于打印该对象的描述信息

```python
class Person(object):
    # 初始化方法
    def __init__(self, name, age, sex):
        self.name = name
        self.age = age
        self.sex = sex

    # 在类被print打印时候会立即触发，主要用于打印该类的描述信息
    def __str__(self):
        name, age, sex = self.name, self.age, self.sex
        return f"我叫：{name} 今年：{age} 岁 是一个 {sex}生"
    	# 注意：只能返回字符串类型


p = Person('刘宇阳', 20, '男')
print(p)
# 我叫：刘宇阳 今年：20 岁 是一个 男生
```

**注意：** `__str__` 只能返回字符串类型，返回其他类型会报错



#### mro

如果多个父类中有同名的 属性和方法，则默认使用第一个父类的属性和方法（根据类的魔法属性 `__mro__` 的顺序来查找）

```python
class A(object):
    def __init__(self):
        self.name = 'LYY'

    def info(self):
        print('A')

    def ideal(self):
        print('Web 全栈开发工程师')


class B(object):
    def __init__(self):
        self.name = 'ZT'
        self.age = 20

    def info(self):
        print('B')

    def ideal(self):
        print('Python 全栈开发工程师')


class C(A, B):
    pass


c = C()
print(C.__mro__)
# (<class '__main__.C'>, <class '__main__.A'>, <class '__main__.B'>, <class 'object'>)
```

**说明：**因为C类没有自己的属性和方法所以会使用A类中的属性和方法，如果自身有的情况下，就会优先自身



#### dict

查看该类的 **实例属性**

```python
class Test(object):
    name = "鱿鱼须"

    def __init__(self, age, hobby):
        self.__hobby = hobby
        self.age = age

    def info(self):
        print(self.age)


t = Test(20, "敲代码")

# 获取类的所有实例属性（不含类属性）
print(t.__dict__)
# {'_Test__hobby': '敲代码', 'age': 20}

# 获取类的所有方法
print(Test.__dict__)
# {'__module__': '__main__', '__init__': <function Test.__init__ at 0x000002CC49A73E18>, '__dict__': <attribute '__dict__' of 'Test' objects>, '__weakref__': <attribute '__weakref__' of 'Test' objects>, '__doc__': None}

```



#### doc

通过 `__doc__` 可以查看当前类的说明文档

```python
class Person(object):
    """
    在这里定义类的说明文档
    """

    def __init__(self):
        self.age = 20
        self.hobby = "敲代码"


p = Person()
print(p.__doc__)
# 
# 在这里定义类的说明文档
# 
```



#### class

通过 `__class__` 可以查看该类的名称

```python
class Person(object):
    """
    在这里定义类的说明文档
    """

    def __init__(self):
        self.age = 20
        self.hobby = "敲代码"


p = Person()
print(p.__class__)
# <class '__main__.Person'>
```



#### module

通过 `__module__` 可以查看该类所在的模块

```python
class Person(object):
    """
    在这里定义类的说明文档
    """

    def __init__(self, age, hobby):
        self.age = age
        self.hobby = hobby


p = Person(20, "敲代码")
print(p.__module__)
# __main__
```



### 继承

#### 单继承

```python
class A(object):
    def __init__(self, a, b):
        self.a = a
        self.b = b

    def info(self):
        print('Hello World!')


class B(A):
    pass


b = B(100, 20)

# 调用A类的属性
print(b.a) # 100

# 调用A类的方法
b.info()
```

**说明：**

- 虽然子类没有定义 `__init__` 方法初始化属性，也没有定义实例方法，但是父类 `A` 有。所以只要创建子类的对象，就默认执行了那个继承过来的 `__init__` 方法



**总结：**

- 子类在继承的时候，在定义类时，小括号()中为父类的名字
- 父类的属性、方法，会被继承给子类



#### 多继承

```python
class A(object):
    def __init__(self):
        self.name = 'LYY'

    def info(self):
        print('A')

    def ideal1(self):
        print('Python 全栈开发工程师')


class B(object):
    def __init__(self):
        self.name = 'ZT'
        self.age = 20

    def info(self):
        print('B')

    def ideal2(self):
        print('Web 全栈开发工程师')


class C(A, B):
    # 如果自身有info被调用的话就会优先输出C类的info方法
    # def info(self):
    #     print('C')

    pass


c = C()

# 查看继承顺序
print(C.__mro__)
# 继承顺序：C > A > B
# (<class '__main__.C'>, <class '__main__.A'>, <class '__main__.B'>, <class 'object'>)

# 继承A类
print(c.name)  # LYY
# 继承A类
c.info()  # A
# A类没有的就会继承B类
c.ideal2()  # Web 全栈开发工程师
```

**说明：**

- 多继承可以继承多个父类，也继承了所有父类的属性和方法
- **注意：** 如果多个父类中有同名的 属性和方法，则默认使用第一个父类的属性和方法（根据类的魔法属性 `__mro__`  查看继承顺序）
- 多个父类中，不重名的属性和方法，不会有任何影响。
- 多继承只能继承最近类的 `_init_`



#### 子调父类属性 | 方法

```python
class A(object):
    def __init__(self):
        self.hobby = "敲代码"


class B(A):
    def __init__(self):
        self.name = "宇阳"
        self.hobby = "写代码"
        A.__init__(self)
	    # super().__init__()
        

b = B()
print(b.name, b.hobby)
# 宇阳 敲代码
```

![Snipaste_2023-03-04_18-44-07](./image/Snipaste_2023-03-04_18-44-07-16794869905391.jpg)



```python
class A(object):
    def __init__(self):
        self.name = 'LYY'

    def infoA(self):
        print(f'{self.name} 前端架构师')


class B(object):
    def __init__(self):
        self.name = 'ZT'

    def infoB(self):
        print(f'{self.name} 前端架构师')


class C(A, B):
    def __init__(self):
        self.name = '刘宇阳'

    def info(self):
        print(f'{self.name} 前端架构师')

    def infoA(self):
        # 子类C的self 修改前
        print(self.name, 111)  # 刘宇阳  111

        # 在子类的方法中调用父类的同名方法
        # 相当于父类A的self.name重新赋值给了子类C的self.name
        A.__init__(self) # 覆盖C类的：self.name = 'LYY'

        # 子类C的self 修改后
        print(self.name, 222)  # LYY  222

        # 然后调用infoA
        A.infoA(self)

    def infoB(self):
        B.__init__(self)
        B.infoB(self)


c = C()
c.info()  # 刘宇阳 前端架构师
c.infoA()  # LYY 前端架构师
c.infoB()  # ZT 前端架构师
```



### 私有属性 | 方法

**私有权限：**在属性名和方法名 前面 加上两个下划线 `__`

类的私有属性 和 私有方法，都不能通过对象直接访问，但是可以在本类内部访问；

类的私有属性 和 私有方法，都不会被子类继承，子类也无法访问；

私有属性 和 私有方法 往往用来处理类的内部事情，不通过对象处理，起到安全作用。



默认情况下B类可以继承A类的所有属性

```python
class A(object):
    def __init__(self):
        self.a = 100


class B(A):
    pass


a = A()
b = B()
print(b.a) # 100
```



而有些属性我们不想让他被继承，那么就需要用到私有属性

只需要在属性前加上 `__` 即可转换为私有属性



**私有属性**

```python
class A(object):
    def __init__(self):
        # 在属性前加上__即可转换为私有属性
        self.__a = 100
        # 私有属性只能在类的内部使用，不能在类的外部使用并且无法被继承
        print(self.__a)


class B(A):
    pass


a = A()
b = B()
```

**注意：** 私有属性只能在类的内部使用，不能在类的外部使用并且无法被继承



**私有方法**

```python
class A(object):
    # 设置私有方法
    def __aaa(self):
        print('hello')

    # 访问私有方法（只能在类的内部访问）
    def fn(self):
        self.__aaa()


a = A()
a.fn()  # hello
```



私有属性 / 方法相当于把原来的属性名改变为： **_类名__属性名**

```python
class Test(object):
    def __init__(self, name):
        self.__name = name

    def __info(self):
        print(self.__name)


t = Test("鱿鱼须")

# 私有属性相当于把原来的属性名改变为：_类名__属性名
print(t._Test__name)

# 私有方法同理
t._Test__info()
```



**总结**

- `Python` 中没有像 `C++` 中 `public` 和 `private` 这些关键字来区别公有属性和私有属性。
- `Python` 是以属性命名方式来区分，如果在属性和方法名前面加了 `2` 个下划线 `__` ，则表明该属性和方法是私有权限，否则为公有权限。



### 修改私有属性的值

- 如果需要修改一个对象的属性值，通常有2种方法

  > 1. 对象名.属性名 = 数据 ----> 直接修改
  > 2. 对象名.方法名() ----> 间接修改

- 私有属性不能直接访问，所以无法通过第一种方式修改，一般通过第二种间接的方式修改私有属性的值：定义一个可以调用的公有方法，在这个公有方法内访问修改。

```python
class A(object):
    def __init__(self):
        self.__a = 100

	# 私有属性只能通过间接形式修改
    def setA(self, n):
        self.__a = n
        print(self.__a)


class B(A):
    pass


a = A()
b = B()
a.setA(10000)  # 10000
b.setA(99999)  # 99999
```



### super

当子类继承了多个父类，如果父类类名修改了，那么子类也要涉及多次修改。而且需要重复写多次调用，显得代码臃肿。这时可以使用 `super` 来带偶遇父类中的属性、方法 

```Python
class A(object):
    def aaa(self):
        print(100)


class B(A):
    def bbb(self):
        # 可以直接通过类名调用里面的函数
        # A.aaa(self) 括号里必须加self
        # 也可以使用super调用
        super().aaa() # 括号里不能加self


b = B()
b.bbb()  # 100
```



```python
class A(object):
    def __init__(self):
        self.name = "Lyy"

    def fn(self):
        print(100)


class B(A):
    def __init__(self):
        self.name = "鱿鱼须"

    def info(self):
        print(self.name, 1)  # 鱿鱼须 1

        # 获取父类A的属性
        super().__init__()
        # 调用父类A的方法
        super().fn()

        print(self.name, 2)  # Lyy 2


b = B()
b.info()  # 100
```



使用 `super()` 可以逐一调用所有的父类方法，并且只执行一次。调用顺序遵循 `__mro__` 类属性的顺序。

**注意：** 如果继承了多个父类，且父类都有同名方法，则默认只执行第一个父类的 同名方法只执行一次，目前`super()` 不支持执行多个父类的同名方法

```python
class A(object):
    # 调用顺序遵循 `__mro__` 类属性的顺序
    def __init__(self):
        self.hobby = "敲代码"


class B(object):
    # 如果没有A类就会调用B类的__init__
    def __init__(self):
        self.name = "宇阳"
        self.hobby = "写代码"


class C(A, B):
    def __init__(self):
        self.name = "宇阳"
        super().__init__()


c = C()

print(c.name, c.hobby)
# 宇阳 敲代码
```



通过 `super` 调用传参

```python
class A(object):
    def __init__(self, val):
        self.hobby = val


class B(A):
    def __init__(self):
        self.name = "宇阳"
        super().__init__("敲代码")


b = B()

print(b.name, b.hobby)
# 宇阳 敲代码
```



### 多态

```python
class A(object):
    def info(self):
        print('AAAAA')


class B(object):
    def info(self):
        print('BBBBB')


def fn(n):
    n.info()


a = A()
b = B()

fn(b)
```



### 类属性和实例属性

**类属性：** 实例对象跟类都可以访问类属性

```python
class A(object):
    # 公有类属性可以被外部访问
    n = 100
    # 私有类属性不能被外部访问
    __a = 200


a = A()
# 实例对象访问
print(a.n)  # 100
# 类访问
print(A.n)  # 100

print(a.__a) # 不能在外部访问私有类属性
# AttributeError: 'A' object has no attribute '__a'
```



实例对象可以访问类的属性，也可以访问实例的属性

而类不能访问实例属性，因为每个实例属性是私有的，类属性是公有的

所以如果每个类需要有个相同名字的属性，可以写在类属性中。而需要动态变化的属性写在实例对象中

比如说要定义一个地区的类，我们都在中国，所以中国可以写在类属性里，而地区变化莫测如河南 郑州可以写在实例属性里

```python
class A(object):
    # 类属性
    name = "LYY"

    def __init__(self):
        # 实例属性
        self.age = 20
        self.hobby = "计算机编程"


# 实例 = 类()
a = A()

# 实例可以访问类属性与实例属性
print(a.name)  # LYY 类属性
print(a.age)  # 20 实例属性
print(a.hobby)  # 计算机编程 实例属性

# 类只能访问类属性 不能访问实例属性
print(A.name)  # LYY
print(A.age)
# type object 'A' has no attribute 'age'
print(A.hobby)
# type object 'A' has no attribute 'hobby'
```



**类属性和实例属性有什么区别？直接定义类属性是否可以取代实例属性？**

类属性在内存中只保存一份，实例属性在每个对象中都要保存一份。我们通过类创建实例对象时，如果每个对象需要具有相同名字的属性，那么就使用类属性，用一份既可。

因此，我们将共享的属性放在类属性中，而独有的属性放在实例属性中。

```python
class Province(object):  # 类也是一个对象  类对象
    # 类属性 类空间内函数外定义的属性
    country = '中国'
 
    def __init__(self, name):
        # 实例属性
        self.name = name
 
 
# 创建了一个实例对象
obj = Province('山东省')
obj2 = Province('山西省')
# 直接访问实例属性
print(obj.name)
print(obj2.name)
# 直接访问类属性
Province.country
```



### 实例方法、类方法和静态方法

#### 实例方法

通常情况下，在类中定义的方法默认都是实例方法。前面章节中，我们已经定义了不只一个实例方法。不仅如此，类的构造方法理论上也属于实例方法，只不过它比较特殊。

比如，下面的类中就用到了实例方法：

```python
class CLanguage:
    #类构造方法，也属于实例方法
    def __init__(self):
        self.name = "C语言中文网"
        self.add = "http://c.biancheng.net"
    # 下面定义了一个say实例方法
    def say(self):
        print("正在调用 say() 实例方法")
```



实例方法最大的特点就是，它最少也要包含一个 `self` 参数，用于绑定调用此方法的实例对象（`Python` 会自动完成绑定）。实例方法通常会用类对象直接调用，例如：

```python
clang = CLanguage()
clang.say()
```



运行结果：

```
正在调用 say() 实例方法
```



当然，`Python` 也支持使用类名调用实例方法，但此方式需要手动给 self 参数传值。例如：

```python
#类名调用实例方法，需手动给 self 参数传值
clang = CLanguage()
CLanguage.say(clang)
```



运行结果为：

```
正在调用 say() 实例方法
```



#### 类方法

**类方法** 只能通过 `cls` 访问类属性 ，不能访问 `self` 的实例属性

```python
class A(object):
    # 类属性
    name = '刘宇阳'

    def __init__(self):
        self.age = 20

    # 类方法
    @classmethod
    def run(cls):
        # 只能访问类属性
        print(cls.name)  # 刘宇阳
        # 不能访问实例属性
        print(cls.age)  # 报错


a = A()
a.run()
```



```python
class People(object):
    n = 0

    @classmethod
    def count(cls):
        cls.n += 1
        print(cls.n)


People.count()  # 1
People.count()  # 2
People.count()  # 3
People.count()  # 4
```



#### 静态方法

**静态方法：** 使用装饰器 `@staticmethod`。参数随意，没有 `self` 和 `cls` 参数

```python
class A(object):
    # 静态方法
    @staticmethod
    def run():
        print('Hello World!')


A.run()
# Hello World!
```



并且方法体中不能使用类或实例的任何属性和方法

```python
class A(object):
    name = '刘宇阳'

    def __init__(self):
        self.age = 20

    @staticmethod
    def run():
        print(name)  # 报错
        print(self.age)  # 报错


a = A()
a.run()
```



**总结**

```python
class Foo(object):
    def func(self):
        print("实例方法")

    @staticmethod
    def static_func():
        print("静态方法")

    @classmethod
    def class_func(cls):
        print("类方法")


foo = Foo()
foo.func()  # 实例方法
foo.static_func()  # 静态方法
foo.class_func()  # 类方法
```



### 摆放家具案例

```python
# 家具类
class HouseItem(object):
    def __init__(self, name, area):
        self.name = name
        self.area = area

    def __str__(self):
        return f"{self.name} 占地：{self.area}"


bed = HouseItem('席梦思', 4)
chest = HouseItem('衣柜', 2)
table = HouseItem('餐桌', 1.5)


# 房子类
class House(object):
    def __init__(self, type, area):
        self.type = type
        self.area = area
        self.allArea = area
        self.furniture = []

    def addFurniture(self, item):
        b = item.area < self.allArea

        if b:
            # 添加家具
            self.furniture.append(item.name)
            # 剩余面积
            self.allArea -= item.area

            print(f'剩余面积为：{self.allArea}  家具：{self.furniture}')
        else:
            print('面积不够')


myHome = House('三室一厅', 110)
# 添加家具
myHome.addFurniture(bed)
# 剩余面积为：106  家具：['席梦思']
myHome.addFurniture(chest)
# 剩余面积为：104  家具：['席梦思', '衣柜']
myHome.addFurniture(table)
# 剩余面积为：102.5  家具：['席梦思', '衣柜', '餐桌']
```



## 异常

**语法**

```python
try:
     # 可能会出现问题的代码
except 异常类型 as 把异常信息给一个变量存储起来:
    # 异常处理
```



读取一个不存在的文件正常情况下会报错，从而导致整个项目运行不了。而使用异常捕获后出现报错就不会导致整个项目崩溃，而是执行 `except` 异常中的代码

```python
try:
    open('123.txt', 'r', encoding="utf8")
except:
    print("报错，因为123.txt文件不存在")  # 执行
```



### 捕获异常

捕获指定的单个异常

```python
try:
    # 没有该文件
    open('123.txt')  # 可能出现FileNotFoundError类型问题
except FileNotFoundError as e:  # as e 把异常信息报错到e变量中
    # 捕获到的异常
    print("捕获到的异常", e)
    # 捕获到的异常 [Errno 2] No such file or directory: '123.txt'
```

```python
try:
    # 写一个未定义的变量
    print(abc)
except NameError as e:
    print("报错，因为abc变量没有定义")
    # 报错，因为abc变量没有定义
    print("报错信息：", e)
    # 报错信息： name 'abc' is not defined
```



捕获指定的多个异常

```python
try:
    open('123.txt')  # 没有该文件
    print(name)  # 没有定义此变量
except (FileNotFoundError, NameError) as e:
    print("捕获到的异常", e)
```



捕获全部异常

```python
try:
    open('123.txt')  # 没有该文件
    print(name)  # 没有定义此变量
except Exception as e:
    print("捕获到的异常", e)
```

```python
try:
    open('123.txt')  # 没有该文件
    print(name)  # 没有定义此变量
except:
    print("捕获到的异常", e)
```



### else

咱们应该对`else`并不陌生，在if中，它的作用是当条件不满足时执行；同样在 `try...except` 中也是如此，如果没有捕获到异常，那么就执行 `else` 中的事情

```python
try:
    # 写一个为定义的变量
    print(111)
except:
    print("有异常，执行except")
else:
    print("没有异常，执行else") # 执行

# 111
# 没有异常，执行else
```



### finally

```python
try:
    # 写一个为定义的变量
    print(abc)
except:
    print("有异常，执行except") # 执行
else:
    print("没有异常，执行else")
finally:
    print("不管有没有异常，执行finally") # 执行

# 有异常，执行except
# 不管有没有异常，执行finally
```



### 异常传递

**try 嵌套**

```python
# 2. 如果外层有异常处理就执行异常处理，没有就报错
try:
    # 1. 如果这里面没有异常处理，就会把异常传递给外层
    try:
        open('123.txt')
    finally:
        print('不管成功与失败我都会执行')
except:
    print("没有这个文件")
    
# 运行结果：
# 不管成功与失败我都会执行
# 没有这个文件
```



**函数异常**

```python
def fn1():
    print(111)


def fn2():
    fn1()
    # 打开一个没有的文件就会报错
    # 如果这里没有异常处理就会传递给fn3，fn3也没有异常处理就会传递给fn4，直到有异常处理为止，否则就会报错
    open('123.txt')
    print(222)


def fn3():
    print(333)
    fn2()


def fn4():
    print(444)

    try:
        fn3()
    except FileNotFoundError as e:
        print("程序异常：", e)


fn4()
```



### 自定义异常

你可以用 `raise` 语句来引发一个异常。异常/错误对象必须有一个名字，且它们应是 `Error` 或 `Exception` 类的子类

下面是一个引发异常的例子:

```python
class ShortInputException(Exception):
    def __init__(self, length, atleast):
        self.length = length
        self.atleast = atleast


def info():
    n = input('请输入 -->')

    try:
        if len(n) < 3:
            # raise引发一个你定义的异常
            raise ShortInputException(len(n), 3)
    except ShortInputException as result:
        print(f"ShortInputException: 你输入的长度为：{result.length}, 长度至少是：{result.atleast}")
    else:
        print('没有任何异常')


info()
```



## 模块

### 基本使用

```python
# 方法一、导入全部
import random
# 0~10的随机数
res1 = random.randint(0, 10)
print(res1)


# 方法二、按需导入
from random import randint
res2 = randint(0, 9)
print(res2)


# 方法三、*导入模块中所有的方法
from random import *
res3 = randint(0, 9)
# 数组中的随机数
res4 = choice([1, 2, 3, 4, 5])
print(res3, res4)
```



方法一 与 方法三 共同点都可以导入全部模块，但他们的不同点注意以下写法：

```python
# 方法一
import random
random.randint(0, 10)
```

```python
# 方法三
from random import *
randint(0, 9)
```



**取别名**

```python
import time as t

print(t.ctime())
```



### 自定义模块

**test.py**

```python
def add(x, y):
    print(x + y)
```



**index.py**

```python
# 方法一、
import test
test.add(1, 2)  # 3

# 方法二、
from test import *
add(10, 20)  # 30

# 方法三、
from test import add
add(100, 200) # 300
```



**注意**

`Python` 在执行一个文件时有个变量 `__name__`

在 `test.py` 中直接运行则 `__name__` 返回的是 `__main__`

在其他文件中引入 `test.py` 运行，则输出的是：`test` 文件名



### __all__

#### 不指定all

不指定 `__all__` 则导出 `test.py` 中所有模块

**index.py**

```python
from test import *

x() # x
y() # y
z() # z
```



**test.py**

```python
def x():
    print("x")


def y():
    print("y")


def z():
    print("z")
```



#### 指定all

指定了 `__all__` 则导出 `test.py` 中指定的模块

**index.py**

```python
from index import *

x() # x
y() # y
z() # z没有被导出
# NameError: name 'z' is not defined
```



**test.py**

```python
# 以数组形式 只导出指定的模块
__all__ = ["x", "y"] 


def x():
    print("x")


def y():
    print("y")


def z():
    print("z")
```

**总结**

如果一个文件中有__all__变量，那么也就意味着这个变量中的元素，会被 `from xxx import *` 时导入, 没有在这个变量中的不会被导入



### 包

#### 创建包

创建一个文件夹 `bag` 然后在文件夹中新建 `__init__.py` 文件



#### 使用包

在 `bag` 包文件夹里创建 `2` 个文件，分别是 `A.py` 与 `B.py`

**A.py**

```python
def info():
    print("刘宇阳")
```

**B.py**

```python
def add(x, y):
    print(x + y)
```



然后在 `bag` 包文件夹的 `__init__` 中导出这两个包

```python
# 方法一
from A import info
from B import info

# 方法二
from . import A, B

# 方法三
__all__ = ["A", "B"]
```



最后在 `index.py` 中使用

```python
# 方法一
import bag
bag.A.info()

# 方法二
from bag import A, B
A.info()
B.info()

# 方法三
from bag import *
A.info()
B.info()
```

 

#### 第三方包

我们可以使用 `pip install 包名` 进行安装 `Python` 中的包

```pip
pip install numpy
```



默认情况下 `pip` 连接的是国外网站，下载包会比较慢，所以我们可以更改为国内网站

以下是比较常用的镜像源

```pip
清华大学：https://pypi.tuna.tsinghua.edu.cn/simple
阿里云：https://mirrors.aliyun.com/pypi/simple/
豆瓣：https://pypi.douban.com/simple/
```



使用方法

```pip
pip install -i https://pypi.tuna.tsinghua.edu.cn/simple numpy
```



### 自定义工具包

**demo.py**

```python
from my_utils.str_util import *
from my_utils.file_util import *

# 反转
str_reverse("Hello")
# 截取
substr("Hello", 1, 5)
print()

# 读取文件数据
print_file_info("123.txt")
# 写入文件数据
append_to_file_name("123.txt", "Hello World!")
```



**my_utils >** `__init__.py`

```python
__all__ = ["str_util", "file_util"]
```



**my_utils >** `file_util.py`

```python
__all__ = ["print_file_info", "append_to_file_name"]


# 接收传入的文件路径并打印该文件的内容
def print_file_info(file):
    try:
        r = open(file, 'r', encoding="utf8")
        content = r.read()
        print(content)
    except Exception as e:
        print("程序出现错误：", e)
    finally:
        r.close()


# 接收文件路径以及传入数据，将数据追加写入到文件中
def append_to_file_name(file, data):
    a = open(file, 'a', encoding="utf8")
    # 追加数据
    a.write(data)
    a.close()
```



**my_utils >** `str_util.py`

```python
__all__ = ["str_reverse", "substr"]


# 反转字符串
def str_reverse(val):
    print(val[::-1])


# 截取字符串
def substr(s, x, y):
    print(s[x:y])

    # i = x
    # while i < y:
    #     print(s[i], end="")
    #     i += 1
```



## _name__

`__name__` 可以获取当前程序文件名称，如果在本文件输出`__name__` 则返回结果是：`__main__` ，其他程序引入本文件执行后，返回的结果是当前文件的名称：`index`

```python
# demo.py
import index # 其他文件：index
```

```python
# index.py
print(__name__) # 当前文件：__main__
```



## 综合案例

### 删除数组中带4的数据

~~~python
# 删除数组中带4的数据
phones = ['18303517744', '15020030417', '15088931331', '15906878938']


def del_four(n, phones):
    i = 0
    while i < len(phones):
        if n in phones[i]:
            phones.remove(phones[i])
            i -= 1
        i += 1

    print(phones)


# 删除带4的手机号
del_four("4", phones)
```
~~~



## Dir

通过 `dir(参数)` 可查看某个数据类型的内置属性

```python
print(dir(1))
print(dir("1"))
print(dir([]))
print(dir({}))
```