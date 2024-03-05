# JavaScript

## 理论题

### JS数据类型有哪些

`JS` 数据类型分为基本类型与引用类型

**基本类型：** `number`、`string`、`boolean`、`undefined`、`null`

**引用类型：** `Object`、`Array`、`Function`

**Symbol** 在ES6中新增，表示独一无二的值

**BigInt** 在ES10中新增，表示任意大的整数



### 判断值类型有哪些方法

`JS` 中判断值类型有以下四种方法：`typeof`、`instanceof`、`constructor`、`Object.prototype.toString.call()`



**1. typeof**

只能判断基本数据类型，如果判断 `null` 或引用类型都是 `object`

```javascript
typeof 1           //number
typeof 'a'         //string
typeof true        //boolean
typeof undefined   //undefined

// 使⽤typeof来判断null和引⽤类型 返回的结果都是 'object'
typeof null        //object
typeof {}          //object
typeof [1,2,3]     //object
function Fn(){}
typeof new Fn()    //object
typeof new Array() //object
```



**2. instanceof**

这种方式只适合判断引用数据类型

对于基本数据类型：`number` , `string` , `boolean` 只有通过构造函数定义 `let num =new Number(1)` 才能检测出

```javascript
function A(name,age){
  this.name = name;
  this.age = age;
}
a = new A('张三',18);
console.log(a instanceof A)  //true

obj = new Object() //创建⼀个空对象obj
//或者通过字⾯量来创建：
obj = {}
console.log(obj instanceof Object); // true

arr = new Array()  //创建⼀个空数组arr  或arr = []
console.log(arr instanceof Array ); // true

date = new Date()
console.log(date instanceof Date ); // true
// 注意：instanceof后⾯⼀定要是对象类型，instanceof前⾯相当于它的实例对象,
// 后⾯的对象类型⼤⼩写不能写错，该⽅法试⽤⼀些条件选择或分⽀
```



**3. constructor**

`constructor` 是原型对象的属性指向构造函数。这种⽅式除了 `undefined` 和 `null` ，其他数据类型都可以检测出来

```javascript
let num = 23;
let date = new Date();
let str = "biu~";
let reg = new RegExp();
let bool = true;
let fn = function () {
  console.log(886);
};
let array = [1, 2, 3];

console.log(num.constructor);  // [Function: Number]
console.log(date.constructor); // [Function: Date]
console.log(str.constructor);  // [Function: String]
console.log(bool.constructor); // [Function: Boolean]
console.log(fn.constructor);   // [Function: Function]
console.log(reg.constructor);  // [Function: RegExp]
console.log(array.constructor);// [Function: Array]
array.constructor == Array;    // true
```



**4. Object.toString.call()**

该方法可以检测出所有数据类型

```javascript
// 完整写法
Object.prototype.toString.call();

// 简写
toString.call();

console.log(toString.call(123));          //[object Number]
console.log(toString.call('123'));        //[object String]
console.log(toString.call(undefined));    //[object Undefined]
console.log(toString.call(true));         //[object Boolean]
console.log(toString.call({}));           //[object Object]
console.log(toString.call([]));           //[object Array]
console.log(toString.call(function(){})); //[object Function]
console.log(toString.call(null));         //[object Null]
console.log(toString.call(undefined));    //[object Undefined]
```



**5. Array.isArray()**
该静态方法用于确定传递的值是否是 `Array` 类型。

```javascript
Array.isArray() // false
Array.isArray([]) // true
```



### 箭头与普通函数的区别

1. 箭头函数不能用于构造函数
2. 箭头函数的 `this` 取决于谁调用了它，并且一旦确定了 `this` 则无法改变
3. 箭头函数不能绑定 `arguments`，取而代之用 `rest` 参数…解决
4. 其他区别
   （1）.箭头函数不能 `Generator` 函数，不能使用 `yeild` 关键字。
   （2）.箭头函数不具有 `prototype` 原型对象。
   （3）.箭头函数不具有 `super`。
   （4）.箭头函数不具有 `new.target`。
5. 总结：
   (1).箭头函数的 `this` 永远指向其上下文的 `this`，任何方法都改变不了其指向，如 `call(), bind(), apply()`
   (2).普通函数的 `this` 指向调用它的那个对象



### var let const 的区别

`var` 存在变量提升，不存在块级作用域

`let` 不存在变量提升，存在块级作用域

`const` 与 `let` 作用一样，区别是 `const` 定义的值必须初始化并且值一旦定义，不能二次更改



### null 与 undefined 的区别

`null` 表示一个对象定义了，值为空

`undefined` 表示不存在这个值，通常会出现在以下几个场景：

1. 变量声明未赋值
2. 函数参数未提供
3. 对象属性未赋值
4. 返回没有返回值



### === 与 == 的区别

**值比较：** `==` 表示当两个数据相等时就为 `true`

**严格比较：** `===` 表示当两个数据相等并且数据类型也相等时才会为 `true`

**总结：** `==` 不会比较类型，而 `===` 会比较值与类型

```javascript
console.log(1 == '1') // true
console.log(1 === '1') // false
```



### eval 的作用是什么

用于解析字符串形式的 `js` 代码并执行

```javascript
eval("console.log(1000 + 24)") // 1024
```

尽可能避免使用 `eval`，不安全，非常耗性能（2次，一次解析成 js 语句，一次执行）



### 怎么判断一个变量是否是数组

```javascript
const arr = [1, 2, 3]
console.log(arr instanceof Array); // true
console.log(arr.constructor == Array); // true
console.log(Object.prototype.toString.call(arr) == '[object Array]'); //true
console.log(Array.isArray(arr)); // true
```



### new 关键字具体干了什么

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



### 箭头函数有哪些特点

1. 简化写法，不必使用 `function` 创建函数，而是 `()=>{}` 替代

2. 单行代码可以省略 `return` 关键字

3. 不能使用 `arguments` 对象

4. 没有 `prototype` 属性，所以不能创建构造函数

5. 无法通过 `call、apply、bind` 等方式改变 `this` 指向

6. 自身没有 `this`，他的 `this` 继承于外层作用域而不是调用者

   ```javascript
   const obj = {
   	fn1: () => {
   		console.log(this); // window
       },
   	fn2: function () {
   		console.log(this); // obj
       }
   }
   
   obj.fn1()
   obj.fn2()
   ```

   从上述代码可以看出，箭头函数的 `this` 指向并不是他的调用者，而是继承了外层作用域的 `this`



### document.write 和 innerHTML 的区别

前者在给页面写入数据时会替换整个 `body` 中的内容，而后者只会在指定容器中写入内容



### async await 是什么？它有哪些作用？

`async await` 是 `es7` 里面的新语法，它是用于处理异步的语法糖。

`async` 作用是将 `function` 定义为异步的，而 `await` 用于等待异步方法执行完成。它可以很好的替代 `promise` 中的 `then` 方法



`async` 函数返回一个 `Promise` 对象，可以使用 `then` 方法添加回调函数。

当函数执行的时候，一旦遇到 `await` 就会先返回，等到异步操作完成，再接着执行函数体内后面的代码。这样可以有效提高代码可读性，解决回调地狱的问题



### 常用的数组方法有哪些？

**concat()** 方法用于合并两个或多个数组。此方法不会更改现有数组，而是返回一个新数组。

**find()** 方法 **返回数组中满足条件的第一个数据**。否则返回 undefined 。

**findIndex()** 方法返回数组中满足条件的 **第一个数据的索引**。否则返回-1。

**includes()** 方法用来 **判断一个数组中是否包含一个指定的数据**，根据情况，如果包含则返回 true， 否则返回 false。

**indexOf()** 方法返回在数组中**找到一个指定数据的第一个索引**，如果**不存在，则返回-1**。 （通常用它判断数组中有没有这个元素）

**join()** 方法将一个数组的所有元素以指定格式转换为字符串

**pop()** 方法从数组中**删除最后一个元素**，并返回该元素的值。此方法会更改原数组。

**push()** 方法将 **一个或多个元素添加到数组的末尾**，并返回该数组的新长度。

**shift()** 方法从数组中**删除第一个元素**，并返回该元素的值。此方法更改数组的长度。

**unshift()** 方法将**一个或多个元素添加到数组的开头**，并返回该数组的新长度(该方法修改原有数组)。

**splice()** 方法通过删除或替换现有元素或者原地添加新的元素来修改数组, 并以数组形式返回被修改的内容。**此方法会改变原数组**。 由被删除的元素组成的一个数组。如果只删除了一个元素，则返回只包含一个元素的数组。如果没有删除元素，则返回空数组。

**slice()** 方法同上，但不会改变原数组

**reverse()** 方法将数组中元素的位置反转，并返回该数组。该方法会改变原数组。

**sort()** 方法用原地算法对数组的元素进行排序，并返回数组。默认排序顺序是在将元素转换为字 符串，然后比较它们的 UTF-16 代码单元值序列时构建的



### 数组有哪几种循环方式？分别有什么作用？

**every()** 方法会循环数组，只要有一个数据为 `false`，那么返回结果就是 `false`

**filter()** 方法用于过滤数组中的数据

**forEach()** 方法用于替换传统的 `for` 循环

**some()** 方法会循环数组，只要有一个数据为 `true`，那么返回结果就是 `true`



### 常用的字符串方法有哪些？

**charAt()** 方法从一个字符串中返回指定的字符。

**concat()** 方法将一个或多个字符串与原字符串连接合并，形成一个新的字符串并返回。

**includes()** 方法用于判断一个字符串是否包含在另一个字符串中，根据情况返回 true 或 false。

**indexOf()** 方法返回调用它的 String 对象中第一次出现的指定值的索引，从 fromIndex 处进行搜 索。如果未找到该值，则返回 -1。 **match()** 方法检索返回一个字符串匹配正则表达式的的结果。

**padStart()** 方法用另一个字符串填充当前字符串(重复，如果需要的话)，以便产生的字符串达到给定的 长度。填充从当前字符串的开始(左侧)应用的。 (常用于时间补 0)

**replace()** 方法返回一个由替换值（ replacement ）替换一些或所有匹配的模式（ pattern ）后的新 字符串。模式可以是一个字符串或者一个[正则表达式](https://link.juejin.cn?target=https%3A%2F%2Fdeveloper.mozilla.org%2Fzh-CN%2Fdocs%2FWeb%2FJavaScript%2FReference%2FGlobal_Objects%2FRegExp)，替换值可以是一个字符串或者一个每次匹配都要 调用的回调函数。 原字符串不会改变。 slice() 方法提取某个字符串的一部分，并返回一个新的字符串，且不会改动原字符串。

**split()** 方法使用指定的分隔符字符串将一个 [String](https://link.juejin.cn?target=https%3A%2F%2Fdeveloper.mozilla.org%2Fzh-CN%2Fdocs%2FWeb%2FJavaScript%2FReference%2FGlobal_Objects%2FString) 对象分割成字符串数组，以将字符串分隔为 子字符串，以确定每个拆分的位置。 **substr()** 方法返回一个字符串中从指定位置开始到指定字符数的字符。 trim() 方法会从一个字符串的两端删除空白字符。在这个上下文中的空白字符是所有的空白字符 (space, tab, no-break space 等) 以及所有行终止符字符（如 LF，CR）。



### 什么是原型链？

在 `JavaScript` 中，每一个对象都有一个指向另一个对象的引用（`__proto__`），这个被引用的对象就是原型。当我们访问一个对象的属性或方法时，如果这个对象本身没有这个属性或方法，那么就会顺着原型链向上查找，直到找到对应的属性或方法，如果找不到就为 `Null` 也就意味着到达了原型链的顶端（Object.prototype）。



**代码验证：查找原型的属性或方法**

默认情况下字符串对象中没有 `fn` 这个方法，他就会一层一层的往原型上找，而原型上也没有这个方法，那么调用时候肯定会出错。

但如果我们可以给他原型上定义方法，这样一来字符串如果没有这个方法他就会查找原型上的方法，如果有就调用

```javascript
const s = ""
String.prototype.fn1 = () => console.log(100);
Object.prototype.fn2 = () => console.log(200);
s.__proto__.__proto__.fn3 = () => console.log(300);
s.fn1() // 100
s.fn2() // 200
s.fn3() // 300
```



**代码验证：原型链的顶端**

每个对象都有一个 `__proto__` 属性，用于访问它原型上的属性或方法

原型链的顶端为 `null`

```javascript
const arr = []
console.log(arr.__proto__); // Array
console.log(arr.__proto__.__proto__); // Object
console.log(arr.__proto__.__proto__.__proto__); // Null
```



**简单来说：** 原型链就是一种用于实现对象之间继承关系的机制。每个对象都有一个指向其原型的链接，通过这种链接，对象可以继承其原型对象的属性和方法。这种继承的特性使得 `JavaScript` 中的对象可以共享属性和方法，同时实现代码的复用和继承。



### 什么是闭包？

闭包指的是有权访问另一个函数作用域中变量或函数。

简单理解就是：一个作用域可以访问另外一个函数内部的局部变量



**代码验证**

延长变量作用域、在函数的外部可以访问函数内部的局部变量

不过需要注意的是使用闭包很容易造成内层泄露，因为闭包中的局部变量永远不会被回收

```javascript
const fn = () => {
    const a = 100;

    return a
}

const b = fn();
console.log(b);
```



### 常见的继承方式有哪些？

1. **原型链继承**：

   - 通过将子类的原型对象指向父类的实例来实现继承。

   - 优点：简单易实现

   - 缺点：所有子类实例共享同一个原型，容易造成属性共享和污染。

     ```javascript
     function Parent() {
       this.name = 'Parent';
     }
     
     Parent.prototype.sayHello = function() {
       console.log('Hello from ' + this.name);
     }
     
     function Child() {
       this.name = 'Child';
     }
     
     Child.prototype = new Parent();
     
     var child = new Child();
     child.sayHello(); // 输出：Hello from Child
     ```

     

2. **构造函数继承**：

   - 在子类构造函数中调用父级构造函数来实现继承。

   - 优点：避免了属性共享和污染

   - 缺点：方法都在构造函数中定义，无法复用。

     ```javascript
     function Parent(name) {
       this.name = name;
     }
     
     function Child(name) {
       Parent.call(this, name);
     }
     
     var child = new Child('Child');
     console.log(child.name); // 输出：Child
     ```

     

3. **组合继承**：

   - 同时使用原型链继承和构造函数继承，结合两者的优点。

   - 通过原型链继承方法，通过构造函数继承属性。

   - 缺点是会调用两次父级构造函数，造成属性的重复定义。

     ```javascript
     function Parent(name) {
       this.name = name;
     }
     
     // 方法在原型上定义
     Parent.prototype.sayHello = function() {
       console.log('Hello from ' + this.name);
     }
     
     // 属性继承于父级构造函数
     function Child(name) {
       Parent.call(this, name);
     }
     
     Child.prototype = new Parent();
     
     var child = new Child('Child');
     child.sayHello(); // 输出：Hello from Child
     ```

     

4. **Class继承**：

   - 使用 `ES6` 新增的 `class` 语法糖。

   - 优点：语法更清晰、更方便

     ```javascript
     class Person {
         constructor(name) {
             this.name = name;
         }
     
         sayHello() {
             console.log('Hello from ' + this.name);
         }
     }
     
     class Child extends Person {
         constructor(name, breed) {
             super(name);
         }
     }
     
     const child = new Child('child');
     console.log(child.name);
     child.sayHello();
     ```

     

### es6 有哪些新特性？

ES6 是 2015 年推出的一个新的版本、这个版本相对于 ES5 的语法做了很多的优化、例如：新增了**let、 const**

**let 和 const**具有块级作用域，**不存在变量提升的问题**。新增了**箭头函数**，简化了定义函数的写法，

同时 可以**巧用箭头函数的 this**、（注意箭头函数本身没有 this,它的 this 取决于外部的环境），

新增了**promise** 解决了回调地域的问题，新增了模块化、利用 import 、export 来实现导入、导出。

新增了**结构赋值**， ES6 允许按照一定模式，从数组和对象中提取值，对变量进行赋值，这被称为解构 （Destructuring）。

新增了**class 类**的概念，它类似于对象。



### cookie 、localstorage 、 sessionstrorage 之间有什么区别？

**与服务器交互：**

- cookie 是网站为了标示用户身份而储存在用户本地终端上的数据（通常经过加密）
- cookie 始终会在同源 http 请求头中携带（即使不需要），在浏览器和服务器间来回传递
- sessionStorage 和 localStorage 不会自动把数据发给服务器，仅在本地保存



**存储大小：**

- cookie 数据根据不同浏览器限制，大小一般不能超过 4k
- sessionStorage 和 localStorage 虽然也有存储大小的限制，但比 cookie 大得多，可以达到 5M 或更大



**有期时间：**

  - ocalStorage 存储持久数据，浏览器关闭后数据不丢失除非主动删除数据
  - sessionStorage 数据在当前浏览器窗口关闭后自动删除
  - cookie 设置的 cookie 过期时间之前一直有效，与浏览器是否关闭无关



## 应用题

### try ... catch 可以捕获到异步代码中的错误吗?

```javascript
try{
    setTimeout(() => {
        throw new Error('err')
    }, 200)
} catch(err){
	console.log(err);
}
```

`setTimeout` 是一个异步函数，它的回调函数会在指定的延时后被放入事件队列，等待当前执行栈清空后才执行。

因此当 `setTimeout` 的回调函数执行并抛出错误时，`try...catch` 已经执行完毕，无法捕捉到异步回调中的错误。
对于异步代码，需要结合 `Promise`、`async / await` 或者事件监听器等机制来处理错误。



### 不使用websocket如何实现多tab页通信

**需求：** 需要在本地实现一个聊天室，多个tab页相互通信，不能用 `websocket`，你会怎么做?

使用 `LocalStorage` 这个存储 `API` 可在浏览器的不同标签页之间共享数据。当一个标签页发送消息时，将消息存储在 `LocalStorage` 中。其他标签页可以监听该存储区的变化，并读取最新的消息内容来实现通信效果。

```javascript
window.addEventListener("storage", (e) => {
	// 监听本地存储变化
});
```

