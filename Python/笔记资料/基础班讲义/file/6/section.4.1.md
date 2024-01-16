# self

## 使用self替换方法中的对象名

```python

class Cat:
    # 方法
    def eat(self):
        print("猫在吃鱼....")

    def drink(self):
        print("猫在喝可乐...")

    def introduce(self):
        # print("名字是:%s, 年龄是:%d" % (汤姆的名字, 汤姆的年龄))
        # print("名字是:%s, 年龄是:%d" % (tom.name, tom.age))
        print("名字是:%s, 年龄是:%d" % (self.name, self.age))


# 创建了一个对象
tom = Cat()
tom.name = "汤姆"
tom.age = 30
tom.eat()
tom.drink()
print(tom.name)
print(tom.age)
print("-"*30)
tom.introduce()

print("="*30)

# 创建了另外一个对象
lan_mao = Cat()
lan_mao.name = "蓝猫"
lan_mao.age = 20
lan_mao.introduce()  # 相当于lan_mao.introduce(lan_mao)

```

## 一个较为完整的程序

```python
class Cat:
    # 方法
    def eat(self):
        print("%s在吃鱼...." % self.name) # 这里换成self

    def drink(self):
        print("%s在喝可乐..." % self.name)  # 这里换成self

    def introduce(self):
        # print("名字是:%s, 年龄是:%d" % (汤姆的名字, 汤姆的年龄))
        # print("名字是:%s, 年龄是:%d" % (tom.name, tom.age))
        print("名字是:%s, 年龄是:%d" % (self.name, self.age))

# 创建了一个对象
tom = Cat()
tom.name = "汤姆"
tom.age = 30
tom.eat()
tom.drink()
print(tom.name)
print(tom.age)
print("-"*30)
tom.introduce()

print("="*30)

# 创建了另外一个对象
lan_mao = Cat()
lan_mao.name = "蓝猫"
lan_mao.age = 20
lan_mao.introduce()  # 相当于lan_mao.introduce(lan_mao)
lan_mao.eat()
lan_mao.drink()

```

### 总结
* 所谓的self，可以理解为自己
* 可以把self当做C++中类里面的this指针一样理解，就是对象自身的意思
* 某个对象调用其方法时，python解释器会把这个对象作为第一个参数传递给self，所以开发者只需要传递后面的参数即可
* self仅仅是一个变量名，也可将self换为其他任意的名字，但是为了能够让其他开发人员能明白这变量的意思，因此一般都会self当做名字
