# Python 小技巧

## contains

判断对象某个属性是否存在

```python
obj = {"name": "zs"}
print(obj.__contains__("name"))  # True
print(obj.__contains__("age"))  # False
```



**案例：** `[1, 2, 3, 1]` 如果有值出现两次就返回 **True** 反之 **False**

```python
def fn(nums):
    obj = {}

    # 找出出现两次的属性
    for (i, item) in enumerate(nums):
        # 如果出现第二次就 +1
        if obj.__contains__(item):
            obj[nums[i]] += 1
        else:
            obj[nums[i]] = 1

    # 查找有没有出现第二次的值，有就返回True，反正False
    for item in obj:
        if obj[item] >= 2:
            return True
            break
        else:
            return False


res = fn([1, 2, 3, 1])
print(res)  # True
```



## datetime

将指定的秒数转换为时分秒格式，如：`90 = 0:01:30`

```
import datetime

time = datetime.timedelta(seconds=90)
print(time)  # 0:01:30
```

