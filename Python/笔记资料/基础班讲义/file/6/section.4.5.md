# 调用对象的方法

```python
class Cat:
    # 属性
    # 方法
    def eat(self):
        print("猫在吃鱼....")

    def drink(self):
        print("猫在喝可乐...")

# 创建了一个对象
tom = Cat()
tom.eat() # 调用对象的eat方法
tom.drink()

```

* 刚开始学习面向对象时，可以把 “方法” 理解为之前的函数，后面的学习中会讲到他们的区别