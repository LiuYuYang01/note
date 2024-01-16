# 飞机大战代码：让敌机移动

```python
#coding=utf-8
import pygame
from pygame.locals import *
import time

'''
    8. 让敌机移动
'''

class HeroPlane(object):

    def __init__(self,screen):

        #设置飞机默认的位置
        self.x = 230
        self.y = 700

        #设置要显示内容的窗口
        self.screen = screen

        self.imageName = "./feiji/hero1.png"
        self.image = pygame.image.load(self.imageName)

        #用来存储英雄飞机发射的所有子弹
        self.bulletList = []

    def display(self):
        self.screen.blit(self.image,(self.x,self.y))

        #判断一下子弹的位置是否越界，如果是，那么就要删除这颗子弹
        #
        #这种方法会漏掉很多需要删除的数据
        # for i in self.bulletList:
        #     if i.y<0:
        #         self.bulletList.remove(i)

        #用来存放需要删除的对象引用
        needDelItemList = []

        #保存需要删除的对象
        for i in self.bulletList:
            if i.judge():
                needDelItemList.append(i)
        
        #删除self.bulletList中需要删除的对象
        for i in needDelItemList:
            self.bulletList.remove(i)

        #因为needDelItemList也保存了刚刚删除的对象的引用，所以可以删除整个列表，那么
        #整个列表中的引用就不存在了，也可以不调用下面的代码，因为needDelItemList是局部变量
        #当这个方法的调用结束时，这个局部变量也就不存在了
        # del needDelItemList

        for bullet in self.bulletList:
            bullet.display()#显示一个子弹的位置
            bullet.move()#让这个子弹进行移动，下次再显示的时候就会看到子弹在修改后的位置

    def moveLeft(self):
        self.x -= 10

    def moveRight(self):
        self.x += 10

    def sheBullet(self):
        newBullet = Bullet(self.x, self.y, self.screen)
        self.bulletList.append(newBullet)

class Bullet(object):
    def __init__(self,x,y,screen):
        self.x = x+40
        self.y = y-20
        self.screen = screen
        self.image = pygame.image.load("./feiji/bullet.png")

    def move(self):
        self.y -= 5

    def display(self):
        self.screen.blit(self.image,(self.x,self.y))

    def judge(self):
        if self.y<0:
            return True
        else:
            return False

class EnemyPlane(object):
    def __init__(self,screen):
        #设置飞机默认的位置
        self.x = 0
        self.y = 0

        #设置要显示内容的窗口
        self.screen = screen

        self.imageName = "./feiji/enemy0.png"
        self.image = pygame.image.load(self.imageName)

        self.direction = "right"

    def display(self):
        self.screen.blit(self.image,(self.x,self.y))

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

        key_control(heroPlane)

        pygame.display.update()

        #通过延时的方式，来降低这个while循环的循环速度，从而降低了cpu占用率
        time.sleep(0.01)

if __name__ == "__main__":
    main()

```