# JavaScript

## JS数据类型有哪些

`JS` 数据类型分为基本类型与引用类型

**基本类型：** `number`、`string`、`boolean`、`undefined`、`null`

**引用类型：** `Object`、`Array`、`Function`

**Symbol** 在ES6中新增，表示独一无二的值

**BigInt** 在ES10中新增，表示任意大的整数



## 判断值类型有哪些方法

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



## 箭头与普通函数的区别

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



## var let const 的区别

`var` 存在变量提升，不存在块级作用域

`let` 不存在变量提升，存在块级作用域

`const` 与 `let` 作用一样，区别是 `const` 定义的值必须初始化并且值一旦定义，不能二次更改



## null 与 undefined 的区别

`null` 表示一个对象定义了，值为空

`undefined` 表示不存在这个值，通常会出现在以下几个场景：

1. 变量声明未赋值
2. 函数参数未提供
3. 对象属性未赋值
4. 返回没有返回值



## === 与 == 的区别

**值比较：** `==` 表示当两个数据相等时就为 `true`

**严格比较：** `===` 表示当两个数据相等并且数据类型也相等时才会为 `true`

**总结：** `==` 不会比较类型，而 `===` 会比较值与类型

```javascript
console.log(1 == '1') // true
console.log(1 === '1') // false
```



## eval 的作用是什么

用于解析字符串形式的 `js` 代码并执行

```javascript
eval("console.log(1000 + 24)") // 1024
```

尽可能避免使用 `eval`，不安全，非常耗性能（2次，一次解析成 js 语句，一次执行）



## 怎么判断一个变量是否是数组

```javascript
const arr = [1, 2, 3]
console.log(arr instanceof Array); // true
console.log(arr.constructor == Array); // true
console.log(Object.prototype.toString.call(arr) == '[object Array]'); //true
console.log(Array.isArray(arr)); // true
```



## new 关键字具体干了什么

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



## 箭头函数有哪些特点

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



## document.write 和 innerHTML 的区别

前者在给页面写入数据时会替换整个 `body` 中的内容，而后者只会在指定容器中写入内容
