# Python 内置方法

## dir

通过 `dir(参数)` 可查看某个数据类型的内置属性

```python
print(dir(1))
print(dir("1"))
print(dir([]))
print(dir({}))
```



## keys

获取指定对象中的所有属性

```python
obj = {"name": "zs", "age": 20}
print(list(obj.keys()))  # ['name', 'age']
```



## values

获取指定对象中的所有值

```python
obj = {"name": "zs", "age": 20}
print(list(obj.values()))  # ['zs', 20]
```



## sort

数组从小到大排序

```python
list = [5, 2, 3, 1, 4]
list.sort()
print(list)
# [1, 2, 3, 4, 5]
```



默认 `reverse=False` 从小到大排序，可以设置为 `True` 从大到小排序

```python
list = [5, 2, 3, 1, 4]
list.sort(reverse=True) # 从大到小排序
print(list)
# [5, 4, 3, 2, 1]
```



## title

使用标题方法使字符串首字母大写

```python
s = "hello python"
print(s.title())
# Hello Python
```

