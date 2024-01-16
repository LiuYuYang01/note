# 私有方法、属性，继承问题

```python

class Animal(object):
    def __init__(self):
        self.num1 = 1
        self.__num2 = 2

    def __run(self):
        print("----跑---")

    def eat(self):
        print("-----吃-----")

    def drink(self):
        print("-----喝-----")

    def test(self):
        print(self.__num2)
        self.__run()


class Dog(Animal):
    def bark(self):
        print("-----汪汪叫------")
        # self.__run()  # 父类中的私有方法，没有被子类继承
        print(self.num1)
        # print(self.__num2)  # 父类中的私有属性，没有被子类继承

wang_cai = Dog()
wang_cai.bark()
wang_cai.test()

```

* 父类中的 私有方法、属性，不会被子类继承
* 可以通过调用继承的父类的共有方法，间接的访问父类的私有方法、属性