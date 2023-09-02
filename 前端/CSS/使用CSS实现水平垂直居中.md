# 使用CSS实现水平垂直居中

## 方法一

```html
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style>
        .box {
            position: relative;
            width: 600px;
            height: 600px;
            background-color: crimson;
        }

        .item {
            position: absolute;
            top: 0;
            left: 0;
            bottom: 0;
            right: 0;
            width: 200px;
            height: 200px;
            margin: auto;
            background-color: #fff;
        }
    </style>
</head>

<body>
    <div class="box">
        <div class="item"></div>
    </div>
</body>

</html>
```



## 方法二

```css
    <style>
        .box {
            position: relative;
            width: 600px;
            height: 600px;
            background-color: crimson;
        }

        .item {
            position: absolute;
            top: 50%;
            left: 50%;
            width: 200px;
            height: 200px;

            /* 减去宽高的一半 */
            margin-top: -100px;
            margin-left: -100px;
            background-color: #fff;
        }
    </style>
```



## 方法三

```css
    <style>
        .box {
            position: relative;
            width: 600px;
            height: 600px;
            background-color: crimson;
        }

        .item {
            position: absolute;
            top: 50%;
            left: 50%;
            width: 200px;
            height: 200px;

            /* 减去宽高的一半 */
            transform: translate(-50%,-50%);
            background-color: #fff;
        }
    </style>
```



## 方法四

```css
    <style>
        .box {
            display: flex;
            justify-content: center;
            align-items: center;
            width: 600px;
            height: 600px;
            background-color: crimson;
        }

        .item {
            width: 200px;
            height: 200px;
            background-color: #fff;
        }
    </style>
```

