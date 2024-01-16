## 参考案例：英雄solo战

#### 案例介绍：
创建两个英雄，拥有相同的技能和不同的属性，通过互相攻击，最后判断胜负结果。

#### 参考代码：

``` python
import random

class Hero(object):
    """定义了一个英雄类，可以移动和攻击"""
    def __init__(self, name, skill, hp, atk, armor):
        """ __init__() __init__方法，用来做变量初始化 或 赋值 操作"""
        # 实例变量 英雄名
        self.name = name
        # 实例变量 技能
        self.skill = skill
        # 实例变量 生命值：
        self.hp = hp   # 实例变量
        # 实例变量 攻击力
        self.atk = atk
        # 实例变量 护甲值
        self.armor = armor

    def move(self):
        """实例方法"""
        print("%s 正在前往solo地点..." % self.name)

    def attack(self, enemy):
        """
            实例方法， enemy 代表当前对象的敌人对象：
            如果self是盖伦，则enemy是泰达米尔；
            如果self是泰达米尔，则enemy是盖伦
        """
        # 50% 暴击几率
        crit = random.randint(1, 2)
        # 攻击力 * 暴击值 - 敌人护甲，做为造成的伤害
        damage = self.atk * crit - enemy.armor
        # 敌人的当前生命值 减少 伤害值，作为剩余生命值
        enemy.hp -= damage

        if crit == 2:
            print("(暴击)", end=" ") # 打印暴击信息
        print("%s 对 %s 发出了一招强力的%s，造成了 %d 的伤害..." % (self.name, enemy.name, self.skill, damage))

    def __str__(self):
        return "英雄 <%s> 数据：\t生命值 %d, 攻击力 %d, 护甲值 %d" % (self.name, self.hp, self.atk, self.armor)

    def __del__(self):
        print("游戏结束，英雄%s被系统清理..." % self.name)

# 实例化了一个英雄对象时，参数默认传递到__init__方法里。
taidamier = Hero("泰达米尔", "旋风斩", 2600, 450, 200)
gailun = Hero("盖伦", "大宝剑", 4200, 260, 400)


# 调用实例方法
taidamier.move()
gailun.move()

# 回合数
round_num = 1

while True:
    input() # 等待键盘输入（回车即可）
    
    print(("当前是第 %d 回合" % round_num).center(50))
    
    # 每回合开始，打印英雄信息
    print(taidamier)
    print(gailun)

    # 泰达米尔攻击盖伦的处理
    taidamier.attack(gailun)
    if gailun.hp <= 0:  # 生命值判断，盖伦生命值小于等于0 则break，游戏结束
        print("\nGame Over! %s 体力不支，被 %s 一刀砍倒在地..." % (gailun.name, taidamier.name))
        break

    # 盖伦攻击泰达米尔的处理
    gailun.attack(taidamier)
    if taidamier.hp <= 0:  # 生命值判断，泰达米尔生命值小于等于0 则break，游戏结束
        print("\nGame Over! %s 弱不禁风，被 %s 一招大宝剑劈晕在地.." % (taidamier.name, gailun.name))
        break

    # 回合结束，回合数加1，继续循环
    round_num += 1
    print("---" * 20)

print("\n")
del(taidamier)  # 游戏里的英雄再强大，终究只是数据而已...
del(gailun)

# 程序结束后，内存会系统自动回收。
# 如果后面没有代码要执行，通常不用手动清理对象。
```
