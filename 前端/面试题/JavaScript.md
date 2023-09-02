# JavaScript

## 理论题

### JavaScript数据类型有哪些

`Undefined`、`Null`、`Boolean`、`Number`、`String`、`Symbol`、`BigInt` 

**Symbol** 在ES6中新增，表示独一无二的值

**BigInt** 在ES10中新增，表示任意大的整数



**为什么会有Biglnt属性？以及他的用法**

因为在数值过大时，使用默认的 `number` 类型会导致丢失精度问题，比如这段代码

```js
console.log(9999999999999999); //10000000000000000
console.log(9007199254740993); //9007199254740992
```



而 `BigInt` 就是为了解决`number` 丢失精度的问题

只需要在数值后面加上 `n` 即可转换为 `BigInt` 类型

```javascript
console.log(9999999999999999n); //9999999999999999n
console.log(9007199254740993n); //9007199254740993n
```



或者使用 `BigInt` 构造函数模式 等价于后面加上 `n`

```javascript
console.log(BigInt("9999999999999999")); //9999999999999999n
console.log(BigInt("9007199254740993")); //9007199254740993n
```

**详细使用：** https://juejin.cn/post/6844903902295359502



### new 关键字执行的过程

1. 在 `new` 构造函数时，内存中会新增一个地址，也可以说是内存空间
2. 然后 `this` 指向这个内存空间
3. 我们可以通过 `this` 给这个内存空间添加属性和方法
4. 最后该构造函数会自动返回出来

```javascript
function Person(){

    this.name="monster1935";
    this.age='24';
    this.sex="male";

}

// 如果不new的话就跟普通函数没有区别了
console.log(Person());  //undefined

// new一下就会自动将this中的属性和方法返回出来
console.log(new Person());//Person {name: "monster1935", age: "24", sex: "male"}
```



## 编程题

### 手写Promise

```javascript
function Pro(excutor) {
  const self = this;

  // 状态
  self.status = "pending";
  // 成功结果
  self.value = null;
  // 失败结果
  self.err = null;

  self.onFulfilledCallbacks = () => {};
  self.onRejectedCallbacks = () => {};

  // 成功的回调
  function resolve(value) {
    // Promise的状态一旦改变后就不允许再次改变了
    if (self.status === "pending") {
      self.value = value;
      self.status = "fulfilled";

      self.onFulfilledCallbacks(value);
    }
  }

  // 失败的回调
  function reject(err) {
    if (self.status === "pending") {
      self.err = err;
      self.status = "rejected";

      console.log(err);
      self.onRejectedCallbacks(err);
    }
  }

  // 容错处理  防止出错导致整个程序无法运行
  try {
    excutor(resolve, reject);
  } catch (err) {
    reject(err);
  }
}

// 将方法挂在到原型上
Pro.prototype.then = function (onFulfilled) {
  // 判断传递过来的是不是函数
  onFulfilled =
    typeof onFulfilled === "function"
      ? onFulfilled
      : function (data) {
          console.log(data);
        };

  // 异步代码解决方案，先把异步的代码保存起来，等到异步代码执行完毕后自动调用
  if (this.status === "pending") {
    this.onFulfilledCallbacks = onFulfilled;
  }
};


Pro.prototype.catch = function (onRejected) {
  onRejected =
    typeof onRejected === "function"
      ? onRejected
      : function (data) {
          console.log(data);
        };

  if (this.status === "pending") {
    this.onRejectedCallbacks = onRejected;
  }
};

const pro = new Pro((resolve, reject) => {
  reject("Hello")
  
  setTimeout(() => {
    resolve("Hello World!");
  }, 1000);
});

pro.then(data => {
  console.log(data, 222);
});

pro.catch(data => {
  console.log(data, 333);
});
```



### 手写节流

```html
<body>
    <button class="btn">按钮</button>

    <script>
        let time = null

        const btn = document.querySelector('.btn')
        btn.addEventListener('click', throttling(function () {
            console.log("Hello");
        }, 1000))

        // 回调函数
        function throttling(func, delay) {
            return function () {
                if (!time) {
                    // 逻辑代码
                    func()

                    // 禁用按钮
                    btn.disabled = true


                    // 在规定时间内不能被再次点击
                    time = setTimeout(() => {
                        time = null

                        // 开启按钮
                        btn.disabled = false
                    }, delay)
                }
            }
        }
    </script>
</body>
```



### 手写防抖

```html
<body>
    <input type="text" class="ipt">

    <script>
        const ipt = document.querySelector('.ipt')

        ipt.addEventListener('input', antiShake(function (e) {
            console.log(this.value);
        }))

        // 回调函数
        function antiShake(func) {
            let time = null

            return function () {
                // 每次先清除上一个定时器
                if (time) clearTimeout(time)

                // 执行最后一次的
                time = setTimeout(() => {
                    // 改变func中函数的this
                    func.call(this)
                }, 1000)
            }
        }
    </script>
</body>
```
