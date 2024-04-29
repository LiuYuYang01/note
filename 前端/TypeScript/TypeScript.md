# TypeScript

`TypeScript` 是一门基于 JavaScript 的编程语言，它是具有类型系统的 JavaScript，是一种解决 JavaScript 缺点的编程语言。

## 开始

**安装**

安装 `TypeScript` 编译器

```bash
# 全局安装 typescript 编译器
npm install -g typescript  ||  yarn global add typescript

# 通过查看 typescript 编译器版本验证编译器是否安装成功
tsc -version  ||  rsc -v
```



**编译&运行**

将 `TypeScript` 代码编译为 JavaScript 代码并执行

```bash
# 编译 index.ts 文件, 编译后在同级目录下会多出 index.js 文件, 该文件存储的就是编译后的 JavaScript 代码
tsc index.ts

# 执行 JavaScript 代码
node index.js
```



**优化工作流**

```bash
# nodemon: 监听文件的变化, 当 TypeScript 文件内容发生变动后调用 ts-node
# ts-node: 将 TypeScript 编译为 JavaScript 并执行
npm install -g nodemon ts-node  ||  yarn global add nodemon ts-node
```



```js
// package.json
"scripts": {
  "start": "nodemon index.ts"
},
```



```bash
npm start  ||  yarn start
```



**编译高版本**

有些 `JavaScript` 代码版本过高，所以就需要编译为高版本

```bash
# 方法一、
# 通过选项的方式指定 target
tsc -t es2016 index.ts


# 方法二、
# 通过配置文件的方式指定 target
tsc --init
# 若要走配置文件 直接执行 tsc 命令
tsc
```



## 基本类型

**类型注释：**在声明变量时候在变量名后面加上指定的数据类型就可以约束该变量

```ts
let num:number = 100

num = 200

// 只能赋值数值
num = '字符串' //报错
console.log(num);


let str:string = '字符串'

str = '串符字'

// 只能赋值字符串
str = 100 //报错

let b:boolean = true

b = false

// 只能赋值布尔值
b = 1 //报错
```



### Any

在你不确定要传什么类型时候可以使用 `any` ，他可以传任何数据类型。就拿数值与字符串举例

```ts
let r: any = 100;
r = 200
console.log(r); //200

r = '字符串'
console.log(r); //字符串
```



而且 `any` 还可以赋值给任何数据类型

```ts
let a:boolean = true

let b:any = 100

a = b

console.log(a); //100
```



### Unknown

用法与 `any` 一致，区别在于他是 `any` 的强化版本，在使用他时候必须先使用 `if` 确定他的类型

```ts
let a: unknown = 100;
a = function abc() {};

// 如果a为数值就打印111，为字符串就打印222，函数 333
if (typeof a === "number") {
  console.log(111);
} else if (typeof a === "string") {
  console.log(222);
} else if (typeof a === "function") {
  console.log(333); //333
}
```



**类型断言 as**

```ts
let str: string;

let n: unknown = "字符串";

// 告诉TS n是个字符串
str = n as string;

console.log(str);
```



**any 与 unknown的区别**

1. `any` 在使用时候不需要确定数据类型，可以直接使用
2. `unknown` 在使用时候必须确定数据类型才能使用
3. `any` 可以赋值给任何数据类型 而 `unknown` 只能赋值给 `any` 与 `unknown` 数据类型



### Array

Array（数组）规定数组中的数据，以数值为例

```ts
let arr: number[] = [1, 2, 3];
console.log(arr);
// [ 1, 2, 3 ]

// 规定所有数组的值为数值类型
arr[1] = '字符串' //报错
```



**二维数组**

```ts
let arr: number[][] = [
  [1, 2, 3],
  [4, 5, 6],
];

console.log(arr);
// [ [ 1, 2, 3 ], [ 4, 5, 6 ] ]
```



### Tuples

Tuples（元组）规定数组中的每一项数据类型

```ts
let arr: [number, string, object] = [100, "字符串", { name: "刘宇阳" }];

console.log(arr); 
// [ 100, '字符串', { name: '刘宇阳' } ]
```



**二维数组**

```ts
let arr: [number, string][] = [
  [100, "字符串"],
  [200, "字符串1"],
];

console.log(arr);
```



### Enum

enum（枚举）用于存储一组密切相关且有限的值，可以提升代码的可读性，可以限定值的范围，比如比赛结果，考试成绩，颜色种类，性别等等。

```ts
enum Sizes {
  Small, //值可以自定义
  Medium,
  large,
}

// 他的默认值从0依次递增
console.log(Sizes.Small); //0
console.log(Sizes.Medium); //1
console.log(Sizes.large); //2
```



**指定一个值**

```ts
enum Sizes {
  Small = 10, //指定一个值
  Medium,
  large = 'a',
}

// 指定一个值：10  这样其他没有值的话就从10开始
console.log(Sizes.Small); //10
console.log(Sizes.Medium); //11
console.log(Sizes.large); //a
```



**依次赋值**

```ts
enum Sizes {
  Small = "a",
  Medium = "b",
  large = "c",
}

console.log(Sizes.Small); //a
console.log(Sizes.Medium); //b
console.log(Sizes.large); //c
```



**基本使用**

```ts
// 在声明枚举类型时, 如果使用 const 关键字, TypeScript 编译器将输出更加简洁的代码
const enum Sizes {
  Small = "a",
  Medium = "b",
  large = "c",
}

// 赋值给变量
let a:Sizes = Sizes.Small
console.log(a); //a

// 改变他的值，赋值时候必须是enum枚举类型
a = Sizes.Medium
console.log(a); //b


// 函数赋值
function updateA(size:Sizes){
    a = size
}

// 注意必须传过去一个枚举类型
updateA(Sizes.large)

console.log(a); //c
```



### Function

Function（函数）通过类型注释可以规定函数接收的参数的类型以及return返回的值的类型。

```ts
// 基本使用：约束形参x、y、return返回值都必须为数值
function sum(x: number, y: number): number {
  return x + y;
}
console.log(sum(100, 200)); //300


// 无返回值使用:void
function sum1(x: number, y: number): void {
  console.log(x + y);
}
sum1(100, 200) //300
```



**箭头函数**

规定传递的参数与返回值都为 `string` 类型

```ts
let fn: (x: string) => string;
fn = (str) => {return str}
console.log(fn('字符串')); // 字符串

// 无返回值: void
let fn1: (x: string) => void;
fn1 = (str) => {return str}
fn1('字符串')
```



**参数n大于5就+10**

```ts
function fn(n: number): number {
  if (n > 5) {
    return n + 10;
  } else {
    return n;
  }
}

console.log(fn(100));
```



**可选参数** 

在函数实参后面加上？代表可选参数，表示可有可无

```ts
function fn(x: number, y?: number) {
    console.log(x);
}

fn(100) //100
```



**注意：**可选参数不能直接被返回，需要先经过 `if` 类型判断

```ts
function fn1(x: number, y?: number): number {
  return x + y
  // 'y' is possibly 'undefined'.  --->  “y”可能是“未定义”。
}
```

**正确写法**

```ts
function fn1(x: number, y?: number): number {
  if (typeof y === "number") {
    return x + y;
  }

  return x
}

console.log(fn1(10)); //10
console.log(fn1(10, 20)); //20
```



**参数默认值**

设置参数y的值默认为20

```ts
function fn(x: number, y: number = 20): number {
  return x + y;
}

// 没有传y，默认值就是20    10+20=30
console.log(fn(10)); //30
// 传了y，默认值就是100    10+199=110
console.log(fn(10,100)); //110
```



**在函数中将对象进行传参并约束对象属性的类型**

```ts
const fn = ({ date, weather }: { date: Date; weather: string }) => {
  console.log(date, weather);
  // 2022-11-29T02:32:21.302Z 晴朗
};

const today = { date: new Date(), weather: "晴朗" };

fn(today);
```



**剩余参数**

```ts
function fn(x: number, y: number, ...z: string[]) {
  console.log(x); // 10
  console.log(y); // 20
  console.log(z); // [ '30', '40', '50' ]
}
fn(10, 20, "30", "40", "50");
```



### Object

约束对象

```ts
const userInfo = {
  uname: "刘宇阳",
  age: 20,
  love: {
    hobby: "计算机",
    like: "代码",
  },
};

const { uname, age }: { uname: string; age: number } = userInfo;
console.log(uname, age);

const { love }: { love: { hobby: string; like: string } } = userInfo;
console.log(love);
```



```ts
let obj: { uname: string; age: number } = { uname: "刘宇阳", age: 20 };
console.log(obj);
```



**只读属性**

`readonly` 在对象属性值中设置此属性使该属性值只读不可写。不能被再次赋值

```ts
let obj: { uname: string; readonly age: number } = { uname: "刘宇阳", age: 20 };

// 只读，不能再次被赋值了
obj.age = 30 //报错
// TS2540: Cannot assign to 'id' because it is a read-only property.

console.log(obj);
```



**对象可选参数**

```ts
let obj: { name: string; age?: number; hobby: string };

obj = { name: "刘宇阳", hobby: "代码" };
console.log(obj);
// { name: '刘宇阳', hobby: '代码' }

obj = { name: "刘宇阳", age: 20, hobby: "代码" };
console.log(obj);
// { name: '刘宇阳', age: 20, hobby: '代码' }
```



**对象属性任意类型**

```ts
let obj: { name: string; [这里随便写个: string]: any };
obj = { name: "刘宇阳", age: 20, hobby: "代码" };
console.log(obj);
// { name: '刘宇阳', age: 20, hobby: '代码' }
```



### void

一但函数设置了 `void` 属性，则表示没有返回值。不能返回任何东西

```ts
function fn():void{
    return //yes
    return 1 //no
}
```

**注意：**可以写 `return` 但他不能返回任何值



## 高级类型

### 可空类型

**指定值**

指定 `n` 的值为10

```ts
let n: 10;

n = 10; //yes
n = 100; //no
```



指定n的值为 `10、20、30` 其中一个

```ts
let n: 10 | 20 | 30;

n = 10; //yes
n = 30; //yes
n = 50; //no
```



**指定类型**

不仅可以指定值，还可以指定类型，如下 指定 `n` 的值为字符串或布尔类型

```ts
let n: string | boolean;

n = "刘宇阳"; //yes
n = true; //yes
n = 123; //no
```



### 类型推断

TS会根据变量的初始值推断数据类型

```ts
// :strinng[]
let strs = ["1", "2", "3"];

// :number[]
let nums = [1, 2, 3];

// :boolean[]
let bools = [true, true, false];

// :never
let arr = [] //永远不可能有值，不能赋值
```



**变量有初始值10，他默认就是数值类型**

```ts
let a = 10;

// 他只能是数值，不能是字符串等等
a = 100; //yes
a = "100"; //no
```



**约束数组只能是字符串 或者数值**

```ts
let arr: (string | number)[] = [1, "2", 3, 4, "5"];
console.log(arr);
// [ 1, '2', 3, 4, '5' ]
```



### 声明类型

通过 `type` 关键字可以声明类型，声明的类型可以是基本数据类型也可以是复杂数据类型。

**注意：**通过 `interface` 只能声明复杂数据类型。

```ts
// 在不使用 type 声明类型时 如果有这样的需求会让代码非常冗余
let a: number | string | boolean = 10;
let b: number | string | boolean = "20";
let c: number | string | boolean = true;
console.log(a, b, c); //10 20 true

// 而使用了 type 声明类型后
type Size = number | string | boolean;
let d: Size = 10;
let e: Size = "20";
let f: Size = true;
console.log(d, e, f); //10 20 true

// 可见代码变的非常简洁
```



也可以将此处的声明理解为 为 `string` 类型起了个别名

```typescript
type Name = string | number | boolean;
let nameValue: Name = "张三";
```

```typescript
type Point = {
  x: number;
  y: number;
  add: (x: number, y: number) => number;
};

let point: Point = { x: 100, y: 200, add: (x: number, y: number) => x + y };
```

```typescript
type Person = { name: string; age: number };

let p1: Person = { name: "张三", age: 20 };
let p2: Person = { name: "李四", age: 50 };
let p3: Person = { name: "王五", age: 60 };
let p4: Person = { name: "赵六", age: 35 };
```



### 联合类型

约束 `n` 的值可以是数值  字符串 布尔类型

```ts
let n: number | string | boolean = 100;
n = 1; //yes
n = "100"; //yes
n = true; //yes
n = []; //no
```



**联合类型：**编译器只能列出所有类型中的相同的属性

```ts
// 比如string类型有的属性number类型没有，number有的strig没有就不行，必须他们都有同样的属性才行
function fn(arg: string | number[]) {
    
}
```



**告诉编译器 arg 就是数值类型**

```ts
function fn(arg: string | number) {
  if (typeof arg === "number") {
    console.log(arg.toFixed()); //100
  }

  arg = arg as number;
  console.log(arg.toFixed()); //100
}

fn(100.12);
```



如果不这么写的话，假如传递的是 `number` 数值类型，那么他在使用时候会提示：  `Property 'toFixed' does not exist on type 'string'.`

```ts
function fn(arg: string | number) {
  console.log(arg.toFixed());
}

fn(100.12);
```



**约束数组只能是字符串 或者数值**

```ts
let arr: (string | number)[] = [1, "2", 3, 4, "5"];
console.log(arr);
// [ 1, '2', 3, 4, '5' ]
```

**约束数组类型**

```ts
let arr: (string | number | boolean | number[])[];
arr = [1, "2", true, "4", [5, 6, 7]];
console.log(arr);
// [ 1, '2', true, '4', [ 5, 6, 7 ] ]
```



### 交叉类型

交叉类型是指将多个类型叠加合并组成新的类型，新类型中包含了被合并类型的所有属性。

**简单来说：**将两个声明类型 A 、B 合并给 C，这样C就具备了 A 、B的所有属性和方法

如果使用C作为他的类型，那么A 、B中的属性和方法都要求必填

```ts
type A = {
  say: (x: number, y: number) => void;
};

type B = {
  play: () => void;
  name: string;
};

type C = A & B;

// 如果使用C作为他的类型，那么A 、B中的属性和方法都要求必填
let obj: C;

obj = {
  name: "LYY",
  say: (x: number, y: number) => {
    console.log(x + y);
  },
  play: () => {},
};

obj.say(10, 20); //30
console.log(obj.name); //LYY
```



### 字面量类型

他的值只能是 `100` 或 `'LYY'`

```ts
type Num = 100 | "LYY";

let n: Num;
n = 100; //yes
n = "LYY"; //yes
n = "AAA"; //no
```



### 可选链操作

可选链操作符( `?.` )允许开发者安全的链式访问一个对象上可能为 `null` 或 `undefined` 的属性。



默认情况下，`person` 里面没有 `name` 属性，给没有的属性转大写毫无疑问**一定报错**

```ts
const person = {  };
console.log(person.name.toLocaleUpperCase());
```



**我们可以通过 `?.` 标识为可选操作**

有 `str` 就转大写

```ts
interface Person {
  str?: string;
}

const obj: Person = { str: "abcdefg" };

let str = obj.str?.toLocaleUpperCase();
console.log(str);
// ABCDEFG
```



没有 `str` 就输出自身的值：`undefined`

```ts
interface Person {
  //加问号代表可有可无，不加问号代表必选！！！
  str?: string;
}

const person: Person = {  };

// 我们可以通过?. 标识为可选链操作
// 这样有str就转大写，没有str就输出自身的值：undefined
console.log(person.str?.toLocaleUpperCase());
```



**属性、数组、函数可选链操作**

```ts
interface Person {
  name?: string;
  arr?: number[];
  fn?: (x: number, y: number) => void;
}

const person: Person = {
  name: "abcde",
  arr: [1, 2, 3, 4, 5],
  fn: (x: number, y: number) => console.log(x + y),
};

// 有name就转大写，没有就输出undefined
console.log(person.name?.toLocaleUpperCase());

// 有数组就输出数组每一项，没有就略过
person.arr?.forEach((item) => console.log(item))

// 有函数就调用，没有就不调用
person.fn?.(100, 200);
```



**在 JavaScript 中可以这样写**

有 `name` 就转换大写，没有就略过

```ts
const person = {}
if(person.name) console.log(person.name.toLocaleUpperCase());
```



### 空值合并运算符

空值合并运算符等价于 `JavaScript` 中的以下写法，只不过他的语法会更加简洁

```ts
// JavaScript写法
x = n ? n : "Null"
```



```ts
let n: number | null;

n = 100;
// n的值为100满足条件返回100
let x = n ?? "Null";
console.log(x); //100

n = null;
// n的值为null不满足条件返回右侧的值：Null
let y = n ?? "Null";
console.log(y); //Null
```

**注意：**左侧 `n` 结果是 `null` `undefined` 两种情况下会返回右侧结果，否则全部返回左侧结果



### 类型断言

**为什么使用类型断言？**

`unknown` 不能这样写，它需要先确定一下类型

```ts
let n:unknown = 100.12
console.log(n.toFixed()); //报错
```

我们在使用 `unknown`  时必须先确定他的类型

```ts
let n: unknown = 100.12;

if (typeof n === 'number') {
  console.log(n.toFixed()); //100
}
```

而我们使用了类型断言后，代码会更加简洁

```ts
let n: unknown = 100.12

console.log((n as number).toFixed()); //100
```



```ts
let n: number | string = 100.12;

console.log((n as number).toFixed()); //100
console.log((<number>n).toFixed()); //100
```

他的目的是确定值的类型



### 函数重载

JavaScript 实现方法重载

```ts
function sum(x, y) {
  if (typeof x === "number" && typeof y === "number") {
    return x + y;
  } else if (typeof x === "string" && typeof y === "string") {
    return x + "_" + y;
  }
}

// 两个值为数值就相加
console.log(sum(10, 20));
// 两个值为字符串就拼接
console.log(sum("Hello", "World"));
```



TypeScript 实现方法重载

```ts
function sum(x: number, y: number): number;
function sum(x: string, y: string): string;

// 模拟Java方法重载
// function sum(x: number | string, y: number | string) {
function sum(x: any, y: any) {
  if (typeof x === "number" && typeof y === "number") {
    return x + y;
  } else if (typeof x === "string" && typeof y === "string") {
    return x + "_" + y;
  }
}

// 两个实参为数值就相加
console.log(sum(10, 20));
// 两个实参为字符串就拼接
console.log(sum("Hello", "World"));
```



### 索引签名

默认情况下 `TypeScript` 不支持对象动态添加属性，因为在 `TypeScript` 中对象有严格的类型限制。

```ts
const obj = {}
obj.A = 'Lyy'
console.log(obj);
// Property 'A' does not exist on type '{}'.
```



所以在 `TypeScript` 中动态为对象添加属性要使用 **索引签名**，它可以限制对象中属性和属性值的类型，只要满足条件的键值对都可以动态添加到对象中。

```ts
const seats: { [seatNumber: string]: string } = {};
// const seats: { [这里自定义: string]: string } = {};
```



```ts
// 定义所有属性值为字符串
const seats1: { [seatNumber: string]: string } = {};
seats1.A1 = "张三";
seats1.A2 = "李四";
console.log(seats1);
// { A1: '张三', A2: '李四' }

// 定义所有属性值为数值
const seats2: { [seatNumber: string]: number } = {};
seats2.A1 = 100;
seats2.A2 = 200;
console.log(seats2);
// { A1: 100, A2: 200 }

// 定义所有属性值为对象
const seats3: { [seatNumber: string]: object } = {};
seats3.A1 = { name: "Lyy" };
seats3.A2 = { age: 20 };
console.log(seats3);
```



### 类型谓词

```ts
// 获取值的数据类型
function getType(val: unknown) {
  return Object.prototype.toString.call(val).slice(8, -1).toLowerCase();
}

function isString(val: unknown): val is string {
  // 返回值为true就代表字符串类型
  return getType(val) === "string";
}

// 如果为字符串就转大写
function toUpperCase(val: unknown) {
  if(isString(val)){
    console.log(val.toUpperCase());
  }
}

toUpperCase('abcdefg')
// ABCDEFG
```



## 面向对象

### 类与属性

```ts
class Dog {
  name: string;
  age: number;

  constructor(name: string, age: number) {
    this.name = name;
    this.age = age;
  }

  bark() {
    console.log(this);
  }
}

const dog = new Dog("旺财", 5);
console.log(dog);
// Dog { name: '旺财', age: 5 }
dog.bark();
// Dog { name: '旺财', age: 5 }
```



**复用**

```ts
class Animal {
  name: string;
  age: number;

  constructor(name: string, age: number) {
    this.name = name;
    this.age = age;
  }

  sayHello(val: string) {
    console.log(val);
  }
}

const dog = new Animal("旺财", 3);
const cat = new Animal("花花", 5);

console.log(dog);
dog.sayHello('汪汪汪')

console.log(cat);
cat.sayHello('喵喵喵')
```



### 只读属性

在属性前加上 `readonly` 就会把属性转换为只读属性，这样在实例化对象之后，只读属性就不能再赋值了

```ts
class Car {
  color: string;
  readonly maxSpeed: number;

  constructor(color: string, maxSpeed: number) {
    this.color = color
    this.maxSpeed = maxSpeed
  }
}

const car = new Car("白色", 240);

car.maxSpeed = 300
// 报错提示：无法分配到 "maxSpeed" ，因为它是只读属性。ts(2540)
console.log(car);
// 总结：在实例化对象之后，只读属性就不能再赋值了
```



### 继承

```ts
class A {
  fn() {
    console.log("Hello");
  }
}

class B extends A {
  x: number = 100;
}

const b = new B();

b.fn(); //hello
console.log(b.x); //100
```



**继承传参**

```ts
class Person {
  name: string;

  constructor(name: string) {
    this.name = name;
  }

  fn(val: string) {
    console.log(val);
  }
}

// 继承父类Person的属性和方法
class Student extends Person {
  id: number;

  constructor(id: number, name: string) {
    super(name);
    this.id = id;
  }
}

const student = new Student(100, "LYY");
console.log(student);
// Student { name: 'LYY', id: 100 }
student.fn("Hello");
// Hello
```



**函数重写**

当父类的一些功能满足不了自己的需求时，子类可以重写父类的方法对功能进行扩展

`override` 表示该方法是覆盖的父类方法, 仅作为标识提升代码的可阅读性, 不会对代码的实际执行产生任何副作用，**可加可不加**

```ts
class Person {
  fn() {
    console.log(100);
  }
}

class Student extends Person {
  override fn() {
    console.log(200);
  }
}

const student = new Student();
student.fn();
// 200
```



### 访问权限修饰符

通过访问权限修饰符可以指定类中的属性、方法能够在哪些范围内被访问。

#### public

`public` 关键字修饰的类属性和类方法可以在任何地方使用 (当前类、子类、实例对象)

```ts
class Animal {
  // 设置为公共属性
  public a = 100
}

class Dog extends Animal {}

const dog = new Dog();
console.log(dog);
// Dog { a: 100 }
console.log(dog.a);
// 100
```



#### private

`private` 关键字修饰的类属性和类方法只能在当前类中使用

```ts
class Animal {
  // 设置为私有属性
  private a = 100
}

class Dog extends Animal {}

const dog = new Dog();
console.log(dog);
// Dog { a: 100 }
console.log(dog.a);
// 属性“a”是私有的，只能在“动物”类中访问。
// Property 'a' is private and only accessible within class 'Animal'.
```



#### protected

`protected` 关键字修饰的类属性和类方法可以在当前类和子类中使用，不能在实例对象使用

```ts
class Animal {
  // 只能在内部调用
  protected a = 100
}

class Dog extends Animal {
  // 在子类内部调用
  info(){
    console.log(this.a);
  }
}

const dog = new Dog();
console.log(dog);
// Dog { a: 100 }

// 这样调用相当于在子类内部调用
dog.info()
// 100

// 不能通过实例对象来调用
console.log(dog.a);
// Property 'a' is protected and only accessible within class 'Animal' and its subclasses.
```



### Getter | Setter

Getter 和 Setter 是对属性访问(获取和设置)的封装，获取属性值时走 Getter，修改属性值时走 Setter。

可以将属性访问器理解为对属性的保护。

在获取属性或修改属性值时，通过属性访问器可以监听到，监听到以后可以做一些额外的事情。

```ts
class A {
  n = 100;

  // 被输出时自动执行，不能有参数 且必须有返回值
  get info() {
    return "被输出";
  }

  // 赋值传参时候就会执行
  set info(n: number) {
    this.n = n;
    console.log("被修改");
  }
}

const a = new A();

console.log(a);
// A { n: 100 }

// 被输出时自动执行
console.log(a.info); // 被输出

// 赋值传参时自动执行
a.info = 1000; // 被修改

console.log(a);
// A { n: 1000 }
```



### 参数属性

TypeScript 提供了特殊的语法将构造函数参数转换为具有相同名称的类属性。 

```ts
class Params {
  x: number;
  y: number;
  z: number;
  constructor (x: number, y: number, z: number) {
    this.x = x;
    this.y = y;
    this.z = z;
  }
}
```



在构造器的参数中加上 `public` 关键字可以将参数自动赋值给对应的值，与以上写法一致

```ts
class Params {
  constructor(
    public x: number,
    public y: number,
    public z: number
  ) {}

  log() {
    console.log(this.x);
    console.log(this.y);
    console.log(this.z);
  }
}

const p = new Params(10, 20, 30);
p.log();
```



### 静态成员

在类中被 `static` 关键字修饰的类属性和类方法被叫做静态成员，静态成员属于类，所以访问静态成员的方式是类名点上静态成员名称。

```ts
class A {
  // 静态成员
  static score: number = 100;

  // 实例成员
  name: string = "Lyy";
}

// 静态成员通过 类名 + 静态成员名称 访问静态成员
console.log(A.score);
// 100

// 实例成员不能通过对象实例访问
const a = new A();
console.log(a.score);
// 属性“score”在类型“A”上不存在。你的意思是改为访问静态成员“A.score”吗?
```



实例对象可以访问实例成员

```ts
console.log(a.name);
// Lyy
```



### 面试题

判断类被实例多少次

```ts
class A {
  private static _count: number = 0;

  constructor() {
    A._count++;
  }
}

const a1 = new A();
const a2 = new A();
const a3 = new A();

console.log(a1); // 1
console.log(a2); // 2
console.log(a3); // 3
```



### 抽象类

抽象类因继承而存在，通过抽象类可以约束子类必须实现哪些成员，如果抽象类不被继承，它将毫无意义。

在抽象类中可以只定义成员，具体的成员实现由子类完成且必须完成，所以抽象类不能被直接实例化。



### 约束

```ts
class A {
  x = 100;
  constructor(public y: number) {}
}

class B {
  x = 100;
  constructor(public y: number) {}

  z = 300
}

class C {
  // 约束a的类型必须是class类，并且属性必须是：
  // {
  //   x = 100;
  //   constructor(public y: number) {}
  // }
  // 属性可以多，但这些属性必须要有

  constructor(public a: A) {
    console.log(this);
  }
}

new C(new A(200));
// C { a: A { y: 200, x: 100 } }
new C(new B(200));
// C { a: B { y: 200, x: 100, z: 300 } }
```



```ts
interface UserProps {
  name: string;
  age: number;
}

class A {
  name = 'zs'
  age = 20
}

class B {
  name =  'ls'
  age = 30
}

class C {
    c:A = new B()
}

console.log(new C());
// C { c: B { name: 'ls', age: 30 } }
```



## interface

interface（接口）用于声明类型，用于对复杂的数据结构进行类型描述，比如对象、函数、类。

### 基本使用

**使用接口约束对象**

```ts
// 定义User接口
interface User {
  name: string;
  age: number;
}

// 接口里有什么类型就要写什么类型，不能多不能少
const user: User = {
  name: "Lyy",
  age: 20,
};

console.log(user);
// { name: 'Lyy', age: 20 }
```



**使用接口约束函数**

```ts
interface Fn {
  (x: number, y: number): number;
}

// 参数与返回值约束为数值
const fn: Fn = (x, y) => x + y;
console.log(fn(100,200)); //300
```



**约束class类**

```ts
interface Person {
  name: string;
  say(): void;
  play(x: number, y: number): number;
}

class Student implements Person {
  name: string = "Lyy";

  say(): void {}
  play(x: number, y: number): number {
    console.log(x + y);
    return x + y;
  }
}

const student = new Student();
student.play(10, 20);
```



### 接口检查

`interface` 接口对字面量会进行严格检查   对变量宽松检查

#### 严格检查

**严格检查：**接口定义的什么类型就必须是什么类型，不能多也不能少

```ts
interface User {
  name: string;
  age: number;
}

let user: User = {
  name: "刘宇阳",
  age: 20,
  // love: "写代码",
}
console.log(user);
// { name: '刘宇阳', age: 20 }
```



#### 宽松检查

**宽松检查：**当变量满足了接口规范以后，即使变量中存在接口规范以外的属性也是可以的。

```ts
interface User {
  name: string;
  age: number;
}

let user: User = {
  name: "Lyy",
  age: 20,
};


const one = {
  // 必选
  name: "刘宇阳",
  age: 20,
  // 可选
  love: "写代码",
};

user = one
console.log(user);
// { name: '刘宇阳', age: 20, love: '写代码' }
```



```ts
interface User {
  name: string;
  age: number;
}

const one = {
  // 必选
  name: "刘宇阳",
  age: 20,
  // 可选
  love: "写代码",
};

let user: User = one
console.log(user);
// { name: '刘宇阳', age: 20, love: '写代码' }
```



```ts
interface User {
  fn(): void;
}

const user = {
  name: "刘宇阳",
  fn() {
    console.log(`你好，我叫：${this.name}`);
  },
};

function print(user: User) {
  user.fn()
}

print(user)
// 你好，我叫：刘宇阳
```



### 接口继承

**单继承**

```ts
interface A {
  name: string;
}

interface B extends A {
  age: number;
}

let user: B = {
  name: "Lyy",
  age: 20,
};

console.log(user);
// { name: 'Lyy', age: 20 }
```



**多继承**

```TS
interface A {
  name: string;
}

interface B {
  age: number;
}

interface C extends A, B {}

let user: C = {
  name: "Lyy",
  age: 20,
};

console.log(user);
// { name: 'Lyy', age: 20 }
```



### 接口合并

多个相同的接口名称可以自动合并

```ts
interface User {
  name: string;
}

interface User {
  age: number;
}

interface User {
  hobby: string;
}

let user: User = {
  name: "Lyy",
  age: 20,
  hobby: "写代码",
};

console.log(user);
// { name: 'Lyy', age: 20, hobby: '写代码' }
```



## 泛型

泛型是指将类型作为参数进行传递，通过参数传递类型解决代码复用问题 ( 代码中类型的复用问题，通过类型复用减少重复代码 )。



**什么是泛型？**

泛型的本质是参数化类型，通俗的将就是所操作的数据类型被指定为一个参数，这种参数类型可以用在类、接口和方法的创建中，分别成为泛型类，泛型接口、泛型方法。

> TypeScript中的泛型跟java中的泛型基本类似。



**为什么使用泛型？**

TypeScript 中不建议使用 any 类型，不能保证类型安全，调试时缺乏完整的信息。

TypeScript可以使用泛型来创建`可重用`的组件。支持当前数据类型，同时也能支持未来的数据类型。扩展灵活。可以在编译时发现你的类型错误，从而保证了类型安全。



### 泛型函数

**目标：**声明一个泛型函数，接收一个参数，接收什么参数就返回什么值。

```ts
function fn<T>(n: T): T {
  return n;
}

// 如果不指定类型，则类型推断
const result1 = fn(100);
const result2 = fn<number>(100);
const result3 = fn<string>("字符串");
const result4 = fn<boolean>(true);

console.log(result1); // 100
console.log(result2); // 100
console.log(result3); // 字符串
console.log(result4); // true
```



**指定多个泛型**

```ts
function fn<T, B>(x: T, y: B): T | B {
  return x; //100
  // return y; //字符串
}

const result = fn<number, string>(100, "字符串");
console.log(result);
```



### 泛型类

```ts
interface Obj {
  name: string;
  age: number;
}

const obj: Obj = { name: "Lyy", age: 20 };

class Person<O, H> {
  constructor(public object: O, public hobby: H) {}
}

// const person = new Person(obj, "写代码");
const person = new Person<Obj, string>(obj, "写代码");

console.log(person);
// Person { object: { name: 'Lyy', age: 20 }, hobby: '写代码' }
```



```ts
class Student<T> {
  constructor(public collection: T[]) {}

  getIndex(index:number){
    console.log(this.collection[index]);
  }
}

// const student = new Student<number>([1,2,3]);
// const student = new Student<string>(['1','2','3']);
const student = new Student([1, '2', 3, "4"]);
console.log(student);
// Student { collection: [ 1, '2', 3, '4' ] }

student.getIndex(0) //1
student.getIndex(3) //4
```



### 泛型接口

**需求：**

创建 fetch 方法用于获取数据

当获取用户数据时，fetch 方法的返回值类型为用户

当获取产品数据时， fetch 方法的返回值类型为产品

不论是用户数据还是产品数据都要被包含在响应对象中。

```ts
interface Product {
  title: number;
}

interface User {
  username: string;
}

interface myResponse<T> {
  data: T | null;
}

function fetch<T>(username: T): myResponse<T> {
  return {
    data: username,
  };
}

console.log(fetch<User>({ username: "刘宇阳" }).data?.username); //刘宇阳
console.log(fetch<Product>({ title: 100 }).data?.title); //100
```



### 泛型约束

默认情况下不写指定的泛型类型则类型推断，支持所有类型

```ts
class Stu<T> {
  constructor(public list: T[]) {}
}

const stu = new Stu([1, "2", true, [], {}]);
console.log(stu);
// Stu { list: [ 1, '2', true, [], {} ] }
```



我们可以这么做来约束他的数组必须全部都是数值

```ts
class Stu<T> {
  constructor(public list: T[]) {}
}

const stu = new Stu<number>([1, 2, 3]);
console.log(stu);
// Stu { list: [ 1, 2, 3 ] }
```



约束泛型数组中的值只能是数值或字符串

```ts
class Stu<T extends number | string> {
  constructor(public list: T[]) {}
}

const stu = new Stu([1, 2, 3, "4", "5"]);
console.log(stu);
// Stu { list: [ 1, 2, 3, '4', '5' ] }
```



约束泛型数组中的值只能是数值或布尔

```ts
class Stu<T extends number | Boolean> {
  constructor(public list: T[]) {}
}

const stu = new Stu<number | Boolean>([1, 2, 3, true, false]);
console.log(stu);
// Stu { list: [ 1, 2, 3, true, false ] }
```



约束泛型数组中的值只能是数值或字符串和对象 `{name:string}`

```ts
interface Num {
  name: string;
}

class Stu<T extends number | string | Num> {
  constructor(public list: T[]) {}
}

const stu = new Stu([1, 2, 3, "4", "5", { name: "字符串" }]);
console.log(stu);
// Stu { list: [ 1, 2, 3, '4', '5', { name: '字符串' } ] }
```



约束泛型第一个参数为Obj类型，第二个为字符串类型

```ts
interface Obj {
  name: string;
  age: number;
}

const obj: Obj = { name: "Lyy", age: 20 };

class Person<O, H> {
  constructor(public object: O, public hobby: H) {}
}

// const person = new Person(obj, "写代码");
const person = new Person<Obj,string>(obj, "写代码");
console.log(person);
```



约束函数的参数必须是 `Father` 类 类型

```ts
class Father {
  constructor(public name: number[]) {}
}

class Son extends Father {}

function fn<T extends Father>(value: T): T {
  return value;
}

const res = fn<Son>(new Son([1, 2, 3]));
console.log(res);
// Son { name: [ 1, 2, 3 ] }
```



## 类型操作符

### keyof

keyof 是类型运算符，接收类型返回类型，返回的是接收类型的属性字面量联合类型。

```ts
interface User {
  name: string;
  age: number
}

type userKeys = keyof User

// userKeys类型的值约束必须为：name | age
const key1:userKeys = "name"
const key2:userKeys = "age"

// 否则就报错
// const key3:userKeys = "hobby"
```



```ts
interface Product {
  name: string;
  price: number;
}

class Store<T> {
  protected _objects: T[] = [];

  add(obj: T) {
    this._objects.push(obj);
  }

  find(property: keyof T, value: unknown): T | undefined {
    return this._objects.find((obj) => obj[property] === value);
  }
}

const store = new Store<Product>();
store.add({ name: "Lyy", price: 999 });
const find =  store.find('name',"Lyy")
console.log(find);
// { name: 'Lyy', price: 999 }
```



### typeof

typeof 用于查看数据类型

```ts
const p = {
  name: "Lyy",
  age: 20,
};

type p1 = typeof p;
// type p1 = {
//   name: string;
//   age: number;
// }


function fn(x: number, y: number) {
  return x + y;
}


// ReturnType TS内置的泛型类型，可以用来获取函数返回值
type fn1 = ReturnType<typeof fn>
// type fn1 = number
```



## readonly

默认情况下对象属性的值可以被自由更改

```ts
interface Obj {
  name: string;
  age: number;
}

const obj: Obj = {
  name: "Lyy",
  age: 20,
};

console.log(obj);
// { name: 'Lyy', age: 20 }

obj.age = 30

console.log(obj);
// { name: 'Lyy', age: 30 }
```

但是加了 `readonly` 属性后对象就转换为只读模式了，一经定义，就没办法再次修改属性值

```ts
interface Obj {
    readonly name: string;
    readonly age: number;
}

const obj: Obj = {
  name: "Lyy",
  age: 20,
};

console.log(obj);
// { name: 'Lyy', age: 20 }

obj.age = 30;
console.log(obj);
// 报错
```



## 映射类型

映射类型是指基于现有类型产生新的类型。

通过遍历语法拷贝原有类型，再在拷贝类型的基础上进行修改从而产生新的类型。

如何拷贝原有类型？拷贝原有类型主要拷贝的是两部分，原有类型中有哪些属性、属性的值是什么类型。

```typescript
interface Product {
  name: string;
  price: number;
}

type NProduct = {
  // 1. 如何拷贝原有类型中的属性?
  // keyof Product => "name" | "price"
  // K in (name | price)
  // name
  // price
  
  // 2.如何获取属性(name, price)值原有的类型
  // 类型[属性名称] 得到的结果就是属性值的类型
  // Product[K] => Product[name] => string
  // Product[K] => Product[price] => number
  [k in keyof Product]: Product[k];
};
```



### 基本使用

```ts
interface Product {
  name: string;
  age: 20;
}

// 拷贝Product
type ReadOnlyProduct = {
  [k in keyof Product]: Product[k];
};

const product: ReadOnlyProduct = {
  name: "刘宇阳",
  age: 20,
  // hobby: "写代码", 类型不通过
};

console.log(product);
// { name: '刘宇阳', age: 20 }
```



基于 `Product` 类型，创建新类型，新类型中的属性都是只读的。 

```ts
// 最终目标如下
interface Product {
  name: string;
  price: number;
}

type ReadOnlyProduct = {
  readonly name: string;
  readonly price: number;
};
```



### 设置为只读

```ts
interface Product {
  name: string;
  age: number;
}

// 拷贝Product
type ReadOnlyProduct = {
  readonly [k in keyof Product]: Product[k];
};

const product: ReadOnlyProduct = {
  name: "刘宇阳",
  age: 20,
};

console.log(product);
// { name: '刘宇阳', age: 20 }

// readonly 只读属性不能再次被更改
product.age = 30
// TS2540: Cannot assign to 'name' because it is a read-only property.
```



通过泛型参数接收旧的类型，返回新类型，新类型中的属性都是只读的。

```ts
interface Product {
  name: string;
  age: number;
}

// 拷贝Product
type ReadOnlyProduct<T> = {
  readonly [k in keyof T]: T[k];
};

const product: ReadOnlyProduct<Product> = {
  name: "刘宇阳",
  age: 20,
};

console.log(product);
// { name: '刘宇阳', age: 20 }

 // readonly 只读属性不能再次被更改
product.age = 30
```



### 设置为可选

通过泛型参数接收旧的类型，返回新类型，新类型中的属性都是可选的。

```ts
interface Product {
  name: string;
  age: number;
}

// 拷贝Product
type ReadOnlyProduct<T> = {
  [k in keyof T]?: T[k];
};

const product: ReadOnlyProduct<Product> = {
  name: "刘宇阳",
//   age: 20 可选参数不写也行
};

console.log(product);
// { name: '刘宇阳', age: 20 }
```



### 设置为可读可写

`-readonly` 全部设置为可读可写

```ts
interface Stu {
  name: string;
  age: number;
}

type myStu = {
  -readonly [k in keyof Stu]: Stu[k] | null;
};

const obj: myStu = {
  name: null,
  age: 20,
};
```



### 设置为必选

`-?` 全部设置为必选

```ts
interface Stu {
  name: string;
  age: number;
}

type myStu = {
  [k in keyof Stu]-?: Stu[k] | null;
};

const obj: myStu = {
  name: null,
  // age: 20,
};
// Property 'age' is missing in type '{ name: null; }' but required in type 'myStu'.
```



## 工具类型

### Partial

将类型中的属性都变成可选的，接收类型，返回类型。

```ts
interface Stu {
  name: string;
  age: number;
  hobby: string;
}

// 将Stu的属性全部转换为可选参数  返回一个新类型
type newStu = Partial<Stu>;

const stu: newStu = {
  name: "刘宇阳",
  age: 20,
  // hobby:"写代码"
};
```



```ts
interface Stu {
  name: string;
  age: number;
  hobby: string;
}

const stu = {
  name: "刘宇阳",
  age: 20,
  hobby: "写代码",
};

function updateObject<T>(obj: T, props: Partial<T>) {
  return { ...obj, ...props };
}

let obj = updateObject<Stu>(stu, { name: "Lyy" });
console.log(obj);
// { name: 'Lyy', age: 20, hobby: '写代码' }
```



### Readonly

将类型中的属性都变成只读的，接收类型，返回类型。

```ts
interface Stu {
  name: string;
  age: number;
  hobby: string;
}

// 将Stu的属性全部转换为只读状态  返回一个新类型
type newStu = Readonly<Stu>

const stu:newStu = {
  name: "刘宇阳",
  age: 20,
  hobby: "写代码",
};

// 写法二
// const stu:Readonly<Stu> = {
//   name: "刘宇阳",
//   age: 20,
//   hobby: "写代码",
// };

// 只读状态不能改值
stu.age = 30
// Cannot assign to 'age' because it is a read-only property.  
```



### Record

```ts
interface User {
  name: string;
  age: number;
  hobby: string;
}

// 约束键为number类型，值为User类型
type newUser = Record<number, User>;

const user: newUser = {
// const user: Record<number, User> = {
  1: { name: "zs", age: 15, hobby: "学习" },
  2: { name: "ls", age: 18, hobby: "睡觉" },
  3: { name: "ww", age: 21, hobby: "写代码" },
};

console.log(user);
// {
//   '1': { name: 'zs', age: 15, hobby: '学习' },
//   '2': { name: 'ls', age: 18, hobby: '睡觉' },
//   '3': { name: 'ww', age: 21, hobby: '写代码' }
// }
```



### Omit

接收类型，得到新类型，在新类型中不要包含 keys

```ts
语法：Omit<接口类型, "需要忽略的属性">
例如：Omit<User, "hobby" | "age">
```



```ts
interface User {
  name: string;
  age: number;
  hobby: string;
}

// 把User的hobby属性忽略掉
type newUser = Omit<User, "hobby" | "age">;

// 现在newUser的属性相当于
// type newUser = {
//   name: string;
// }

const user: newUser = {
  name: "Lyy",
  // age: 20,
  // hobby:"写代码"
};
```



### Pick

接收类型，返回新类型，在新类型中要包含 keys

```ts
interface User {
  name: string;
  age: number;
  hobby: string;
}

// 只选择User类型中的hobby属性
type newUser = Pick<User, "hobby">;

// 现在newUser的属性相当于
// type newUser = {
//   hobby:"写代码"
// }

const user: newUser = {
  // name: "Lyy",
  // age: 20,
  hobby:"写代码"
};
console.log(user);
// { hobby: '写代码' }
```



### clude

接收联合类型，得到新类型，在新类型中排除联合类型中的 ExcludedMembers。

```ts
type X = string | number | boolean;

let x: X;
x = "字符串";
x = 100;
x = true;


// 排除联合类型中的string
type Y = Exclude<X, string>;
// 此时Y相当于：type Y = number | boolean

let y: Y;

// Y类型中没有string类型，所以会报错
// y = "字符串"; 报错
y = 100;
y = true;
```



## 排序案例

### 概述

> 目标：通过面向对象编程的方式实现对数值数组、字符串进行排序

```typescript
// 数值数组排序
[10, 5, 18, -3] => [-3, 5, 10, 18]
```

```typescript
// 字符串排序
"PoaJB" => "aBJoP"
```

### 准备

创建案例目录并通过 `tsconfig.json` 文件配置应用源码目录和应用输出目录

```bash
# 创建项目目录
mkdir sort
# 创建项目源码目录
cd sort && mkdir src build
# 初始化 package.json
npm init -y
# 创建 ts 配置文件 tsconfig.json
tsc --init
```

```json
{
  "compilerOptions": {
    // 配置项目源码目录
    "rootDir": "./src",
    // 项目源码的输出目录
    "outDir": "./build",
  },
  // 规定只有src目录包含模块才会进行编译
  "include": ["./src"]
}
```

配置应用程序的启动命令

```bash
npm install nodemon concurrently -D
```

```json
"scripts": {
  // 编译
  "start:build": "tsc -w",
  // 运行
  "start:run": "nodemon build/index.js",
   // 这里会将build、run依次执行一遍
  "start": "concurrently npm:start:*"
}
```



### 使用 JS 完成

```typescript
    let arr = [5, 4, 3, 2, 1]

    for (let i = 0; i < arr.length - 1; i++) {
      for (let j = 0; j < arr.length - 1 - i; j++) {
        [arr[j], arr[j + 1]] = [arr[j + 1], arr[j]];
      }
    }

    console.log(arr);
    // [ 1, 2, 3, 4, 5 ]
```



### 使用 TS 完成

```ts
class A {
  constructor(public arr: number[]) {}

  sort():number[]{
    for (let i = 0; i < this.arr.length - 1; i++) {
      for (let j = 0; j < this.arr.length - 1 - i; j++) {
        [this.arr[j], this.arr[j + 1]] = [this.arr[j + 1], this.arr[j]];
      }
    }

    return this.arr
  }
}

const a = new A([5, 4, 3, 2, 1])
let sort = a.sort()
console.log(sort);
// [ 1, 2, 3, 4, 5 ]
```



**需求：**传入数字类型的数组与字符串数字从小到大排序

```ts
class A {
  constructor(public arr: number[] | string) {}

  sort(): number[] | string[] {
    if (this.arr instanceof Array) {
      for (let i = 0; i < this.arr.length - 1; i++) {
        for (let j = 0; j < this.arr.length - 1 - i; j++) {
          [this.arr[j], this.arr[j + 1]] = [this.arr[j + 1], this.arr[j]];
        }
      }

      return this.arr;
    } else {
      let list: string[] = this.arr.split("");

      for (let i = 0; i < this.arr.length - 1; i++) {
        for (let j = 0; j < this.arr.length - 1 - i; j++) {
          [list[j], list[j + 1]] = [list[j + 1], list[j]];
        }
      }

      return list;
    }
  }
}

const number = new A([5, 4, 3, 2, 1])
let numberSort = number.sort();
console.log(numberSort);
// [ 1, 2, 3, 4, 5 ]

const string = new A("54321");
let stringSort = string.sort();
console.log(stringSort);
// [ '1', '2', '3', '4', '5' ]
```



## 扩展

屏蔽当前文件中所有的类型错误

```
// @ts-nocheck
```



屏蔽指定行的类型错误

```
// @ts-ignore
```

