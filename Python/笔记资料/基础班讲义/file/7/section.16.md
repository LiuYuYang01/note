# 飞机大战代码：代码优化-抽象出基类

```python
#coding=utf-8
import pygame
from pygame.locals import *
import time
import random

'''
    代码优化：抽出基类
'''

class Plane(object):
    def __init__(self, screen, imageName):

        #设置要显示内容的窗口
        self.screen = screen
        self.image = pygame.image.load(imageName)

        #用来存储英雄飞机发射的所有子弹
        self.bulletList = []

    def display(self):
        self.screen.blit(self.image,(self.x,self.y))

        #用来存放需要删除的对象引用
        needDelItemList = []

        #保存需要删除的对象
        for i in self.bulletList:
            if i.judge():
                needDelItemList.append(i)
        
        #删除self.bulletList中需要删除的对象
        for i in needDelItemList:
            self.bulletList.remove(i)

        for bullet in self.bulletList:
            bullet.display()#显示一个子弹的位置
            bullet.move()#让这个子弹进行移动，下次再显示的时候就会看到子弹在修改后的位置

class HeroPlane(Plane):

    def __init__(self, screen):
        super(HeroPlane, self).__init__(screen, "./feiji/hero1.png")
        #设置飞机默认的位置
        self.x = 230
        self.y = 700

    def moveLeft(self):
        self.x -= 10

    def moveRight(self):
        self.x += 10

    def sheBullet(self):
        newBullet = Bullet(self.x, self.y, self.screen, "hero")
        self.bulletList.append(newBullet)

class EnemyPlane(Plane):

    def __init__(self, screen):
        super(EnemyPlane, self).__init__(screen, "./feiji/enemy0.png")
        #设置飞机默认的位置
        self.x = 0
        self.y = 0
        self.direction = "right"

    def move(self):
        #如果碰到了右边的边界，那么就往左走，如果碰到了左边的边界，那么就往右走
        if self.direction == "right":
            self.x += 4
        elif self.direction == "left":
            self.x -= 4

        if self.x>480-50:
            self.direction = "left"
        elif self.x<0:
            self.direction = "right"

    def sheBullet(self):
        num = random.randint(1,100)
        if num == 88:
            newBullet = Bullet(self.x,self.y,self.screen, "enemy")
            self.bulletList.append(newBullet)

class Bullet(object):
    def __init__(self,x,y,screen,planeName):

        self.name = planeName
        self.screen = screen

        if self.name == "hero":
            self.x = x+40
            self.y = y-20
            imageName = "./feiji/bullet.png"

        elif self.name == "enemy":
            self.x = x+30
            self.y = y+30
            imageName = "./feiji/bullet1.png"
        self.image = pygame.image.load(imageName)
    
    def move(self):
        if self.name == "hero":
            self.y -= 4
        elif self.name == "enemy":
            self.y += 4

    def display(self):
        self.screen.blit(self.image,(self.x,self.y))

    def judge(self):
        if self.y>852 or self.y<0:
            return True
        else:
            return False

def key_control(heroPlane):
    #判断是否是点击了退出按钮
    for event in pygame.event.get():
        # print(event.type)
        if event.type == QUIT:
            print("exit")
            exit()
        elif event.type == KEYDOWN:
            if event.key == K_a or event.key == K_LEFT:
                print('left')
                heroPlane.moveLeft()
                #控制飞机让其向左移动
            elif event.key == K_d or event.key == K_RIGHT:
                print('right')
                heroPlane.moveRight()
            elif event.key == K_SPACE:
                print('space')
                heroPlane.sheBullet()

def main():
    #1. 创建一个窗口，用来显示内容
    screen = pygame.display.set_mode((480,852),0,32)

    #2. 创建一个和窗口大小的图片，用来充当背景
    background = pygame.image.load("./feiji/background.png")

    #3.1 创建一个飞机对象
    heroPlane = HeroPlane(screen)
    #3.2 创建一个敌人飞机
    enemyPlane = EnemyPlane(screen)

    #4. 把背景图片放到窗口中显示
    while True:
        screen.blit(background,(0,0))

        heroPlane.display()

        enemyPlane.move()
        enemyPlane.display()
        enemyPlane.sheBullet()

        key_control(heroPlane)

        pygame.display.update()

        #通过延时的方式，来降低这个while循环的循环速度，从而降低了cpu占用率
        time.sleep(0.01)

if __name__ == "__main__":
    main()

```