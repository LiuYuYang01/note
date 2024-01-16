## 匿名函数

用lambda关键词能创建小型匿名函数。这种函数得名于省略了用def声明函数的标准步骤。
	
lambda函数的语法只包含一个语句，如下：

```python
lambda [arg1 [,arg2,.....argn]]:expression
```

如下实例：
```python
sum = lambda arg1, arg2: arg1 + arg2
 
# 调用sum函数
print("Value of total : " % sum( 10, 20 ))
print("Value of total :  "% sum( 20, 20 ))

```
以上实例输出结果：
```python
Value of total :  30
Value of total :  40
```
Lambda函数能接收任何数量的参数但只能返回一个表达式的值

匿名函数不能直接调用print，因为lambda需要一个表达式

## 应用场合

### 函数作为参数传递

1. 自己定义函数
```python
>>> def fun(a, b, opt):
...     print("a = " % a)
...     print("b = " % b)
...     print("result =" % opt(a, b))
...
>>> fun(1, 2, lambda x,y:x+y)
a = 1
b = 2
result = 3
```
2. 作为内置函数的参数

#### 想一想，下面的数据如何指定按age或name排序？
```python
stus = [
    {"name": "zhangsan", "age": 18}, 
    {"name": "lisi", "age": 19}, 
    {"name": "wangwu", "age": 17}
]
```

#### 按name排序：
```python
>>> stus.sort(key = lambda x: x['name'])
>>> stus
[{'age': 19, 'name': 'lisi'}, {'age': 17, 'name': 'wangwu'}, {'age': 18, 'name': 'zhangsan'}]
```
#### 按age排序：
```python
>>> stus.sort(key = lambda x: x['age'])
>>> stus
[{'age': 17, 'name': 'wangwu'}, {'age': 18, 'name': 'zhangsan'}, {'age': 19, 'name': 'lisi'}]
```
