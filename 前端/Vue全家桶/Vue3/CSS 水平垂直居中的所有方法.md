# CSS 四中方法实现水平 垂直居中

## 效果

![image-20230908155112142](image/image-20230908155112142.png)



## 结构

```html
  <div class="a">
    <div class="b"></div>
  </div>
```



## 样式

### 方法一

```scss
.a {
  width: 500px;
  height: 500px;
  margin: 50px auto;
  background-color: crimson;

  // 核心代码
  display: flex;
  justify-content: center;
  align-items: center;
}

.b {
  width: 200px;
  height: 200px;
  background-color: bisque;
}
```



### 方法二

```scss
.a {
  width: 500px;
  height: 500px;
  margin: 50px auto;
  background-color: crimson;

  // 核心代码
  position: relative;
}

.b {
  width: 200px;
  height: 200px;
  background-color: bisque;

  // 核心代码
  position: absolute;
  top: 0;
  left: 0;
  bottom: 0;
  right: 0;
  margin: auto;
}
```



### 方法三

```scss
.a {
  width: 500px;
  height: 500px;
  margin: 50px auto;
  background-color: crimson;

  // 核心代码
  position: relative;
}

.b {
  width: 200px;
  height: 200px;
  background-color: bisque;

  // 核心代码
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  // margin-top: -50%;
  // margin-left: -50%;
}
```



### 方法四

```scss
.a {
  width: 500px;
  height: 500px;
  margin: 50px auto;
  background-color: crimson;

  // 核心代码
  display: grid;
  place-items: center;
}

.b {
  width: 200px;
  height: 200px;
  background-color: bisque;
}
```

