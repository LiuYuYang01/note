## `__new__`方法

### `__new__和__init__`的作用
```python
class A(object):
    def __init__(self):
        print("这是 init 方法")

    def __new__(cls):
        print("这是 new 方法")
        return object.__new__(cls)

A()

```

#### 总结

* `__new__`至少要有一个参数cls，代表要实例化的类，此参数在实例化时由Python解释器自动提供

* `__new__`必须要有返回值，返回实例化出来的实例，这点在自己实现`__new__`时要特别注意，可以return父类`__new__`出来的实例，或者直接是object的`__new__`出来的实例

* `__init__`有一个参数self，就是这个`__new__`返回的实例，`__init__`在`__new__`的基础上可以完成一些其它初始化的动作，`__init__`不需要返回值

* 我们可以将类比作制造商，`__new__`方法就是前期的原材料购买环节，`__init__`方法就是在有原材料的基础上，加工，初始化商品环节

### 注意点

![](../Images/Snip20170305_61.png)