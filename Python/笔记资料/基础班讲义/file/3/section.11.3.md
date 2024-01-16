# 有序字典:OrderDcit 



我们先看一段代码, 此代码运行在 Python3.5 版本中:

```python
# 创建无序字典
my_dict = dict()
# 向字典中添加元素
my_dict['one'] = 1
my_dict['two'] = 2
my_dict['three'] = 3
my_dict['four'] = 4
print(my_dict)
```

输出结果(不固定):

```python
{'three': 3, 'two': 2, 'four': 4, 'one': 1}
```

输出结果并不是按照我们创建字典、添加元素的顺序输出, 这是由于 dict 是无序的. 如果我们想最终打印输出的顺序和我们操作时的顺序保持一致, 我们就需要使用有序字典:

```python
from collections import OrderedDict

# 创建有序字典
my_dict = OrderedDict()
# 向字典中添加元素
my_dict['one'] = 1
my_dict['two'] = 2
my_dict['three'] = 3
my_dict['four'] = 4
print(my_dict)
```

输出结果:

```python
OrderedDict([('one', 1), ('two', 2), ('three', 3), ('four', 4)])
```



在 Python3.6 版本中, dict 字典已经经过优化, 变为有序字典. 并且字典所占用的内存占用减少了20％到25％. 

第一段代码在 Python3.6 运行下, 输出结果如下:

```python
{'one': 1, 'two': 2, 'three': 3, 'four': 4}
```

