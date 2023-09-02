# TypeScript 教程

## 1. TypeScript 概述

### 1.1 TypeScript 是什么

TypeScript 是一门基于 JavaScript 的编程语言，它是具有类型系统的 JavaScript，是一种解决 JavaScript 缺点的编程语言。

```typescript
// 所有的 JavaScript 代码都是合法的 TypeScript 代码。
var a = 10;
function fn () {}
if (true) {}
for (var i = 0; i < 10; i++) {}
```

```typescript
// TypeScript 是静态类型编程语言，即编译期间进行类型检查，变量、参数、返回值等都必须有固定的类型。
let x: number = 10;
x = 20;  // ✅
x = "a"; // ❎
```

<img src="./images/02.png" align="left" width="50%"/>

------

TypeScript 不能在浏览器环境或者 Node 环境直接运行，它在执行前需要先被编译为 JavaScript。

```typescript
// TypeScript
let isLogin: boolean = false
```

```javascript
// Javascript
let isLogin = false;
```

TypeScript 提供的类型系统只应用在开发阶段，只有在开发阶段开发者才需要借助它编写出更加健壮的程序。

<img src="./images/03.png" align="left" width="50%"/>

------

TypeScript 由微软发布，第一个正式版的发布时间为是2013年6月19日。

<img src="./images/01.png" />

### 1.2 类型系统带来的好处

通过静态类型检查可以让开发者在编译时就能发现错误而不是在代码运行时，而且静态类型系统还大大增强了代码的可读性以及可维护性。

类型系统为编辑器带来了更加精准的代码提示，以此来提升开发人员的编程体验。

```typescript
app.get("/", function (req, res) {
  res.
    send
    sendDate
    sendfile
    sendFile
    sendStatus
});
```

在声明变量时明确告知编译器它的类型，编译器就知道该变量可以调用哪些属性和方法，当开发者调用了错误的属性或方法时，编译器会及时给出提示。

```typescript
var name: string = "张三";
name.toFixed(2); // 属性"toFixed"在类型"string"上不存在
```

在声明函数时明确告知编译器参数的类型，当开发者调用该函数时如果传递的参数的类型不正确，编译器会及时给出提示。

```typescript
function sum(x: number, y: number) {}
sum(10, "a"); // 类型"string"的参数不能赋给类型"number"的参数。
```

在声明函数时明确告知编译器返回值的类型，当开发者返回的值的类型错误时，编译器会及时给出提示。

```typescript
function sayHello(): string {
  return 100; // 不能将类型"number"分配给类型"string"。
}
```

在声明对象时告知编译器该对象下有哪些属性，当开发者访问了对象中不存在的属性时，编译器会及时给出提示。

```typescript
const person = { name: "张三" };
person.age; // 类型"{name: string}"上不存在属性"age"。
```

类型系统使代码变得可预测，能够让开发者更早的发现代码存在的问题和潜在问题。

```typescript
for (var i = 0; i < 5; i++) { }
console.log(i); // 编译器会捕获到错误: cannot find name "i"
```

当重复声明同名变量时，编译器会立即给出提示。

```typescript
let colors = ["red", "green", "blue"];
// 无法重新声明块范围变量"colors"。
let colors = 10;
```

## 2. TypeScript 初体验

### 2.1 第一个 TypeScript 应用

> 需求：向服务端发送请求获取 ID 为 1 的任务对象。
>
> 目标：将 TypeScript 编译为 JavaScript。

------

- [ ] 安装 TypeScript 编译器，用于将 TypeScript 代码编译为 JavaScript 代码
- [ ] 安装 axios 库，用于发送请求
- [ ] 新建 index.ts 文件，用于编写代码
- [ ] 将 TypeScript 代码编译为 JavaScript 代码并执行

------

第一步：安装 TypeScript 编译器，用于将 TypeScript 代码编译为 JavaScript 代码

```bash
# 全局安装 typescript 编译器
npm install -g typescript
# 通过查看 typescript 编译器版本验证编译器是否安装成功
tsc -version
```

第二步：安装 axios 库，用于发送请求。

```bash
# 安装 axios 用于发送请求
npm install axios@0.27.2
```

第三步：新建 index.ts 文件用于编写代码，TypeScript 程序文件的后缀名为 `.ts`。

```typescript
import axios from "axios";

axios.get("https://jsonplaceholder.typicode.com/todos/1").then((response) => {
  console.log(response.data);
});
```

第四步：将 TypeScript 代码编译为 JavaScript 代码并执行。

```bash
# 编译 index.ts 文件, 编译后在同级目录下会多出 index.js 文件, 该文件存储的就是编译后的 JavaScript 代码
tsc index.ts
# 执行 JavaScript 代码
node index.js
```

### 2.2 优化工作流

> 目标：监控 TypeScript 文件的变化，实现自动编译、自动执行代码

------

- [ ] 安装 nodemon、ts-node

- [ ] 创建应用启动脚本
- [ ] 通过应用启动脚本启动应用

------

```bash
# nodemon: 监听文件的变化, 当 TypeScript 文件内容发生变动后调用 ts-node
# ts-node: 将 TypeScript 编译为 JavaScript 并执行
npm install -g nodemon ts-node
```

```json
// package.json
"scripts": {
  "start": "nodemon index.ts"
},
```

```bash
npm start
```

tsc 与 ts-node 的主要区别在于 tsc 根据 tsconfig 编译所有文件，ts-node 会从入口文件开始，并根据模块关系逐步转译文件。

### 2.3 体验类型带来的好处

需求：将任务ID、任务名称、任务是否完成分别输出到控制台中。

```typescript
import axios from "axios";

axios.get("https://jsonplaceholder.typicode.com/todos/1").then((response) => {
  const todo = response.data;
  const id = todo.ID;
  const title = todo.Title;
  const finished = todo.finished;
  console.log(`
    任务的ID是: ${id},
    任务的名称是: ${title},
    任务是否完成: ${finished}
  `);
});
```

以上代码执行后，输出的结果都是 undefined，发生了什么？

```bash
任务的ID是: undefined,
任务的名称是: undefined,
任务是否完成: undefined
```

通过查看得知，任务 ID 对应的属性名称是 id，任务名称对应的属性名称是 title，任务是否完成对应的属性名称是 completed，原来是属性名称写错了。

目前的问题是在书写代码的过程中并没有任何的错误提示，只有代码运行以后开发者才能够知道代码中存在错误，这个问题应该怎么解决呢？

显式告知 TypeScript 编译器 response.data 中存储的数据的类型，编译器会实时检测你写的代码是否符合类型上的要求。

以下代码展示的是通过 TypeScript 约束对象中可以存在的属性，当访问了不存在的属性时编译器会实时进行提示。

```typescript
import axios from "axios";

// interface 意为接口, 可以约束对象中可以有哪些属性, 约束对象中属性的类型 
interface Todo {
  id: number;
  title: string;
  completed: boolean;
}

axios.get("https://jsonplaceholder.typicode.com/todos/1").then((response) => {
  const todo = response.data as Todo;
  const id = todo.ID; // 属性"ID"在类型"Todo"上不存在。你是否指的是"id"?
  const title = todo.Title; // 属性"Title"在类型“Todo"上不存在。你是否指的是"title"?
  const finished = todo.finished; // 属性"finished"在类型"Todo"上不存在。
  console.log(`
    任务的ID是: ${id}, 
    任务的名称是: ${title}, 
    任务是否结束: ${finished}
  `);
});
```

以下代码展示的是通过 TypeScript 约束函数参数的类型，调用函数时如果传入的参数类型错误，编译器会实时进行提示。

```typescript
import axios from "axios";

interface Todo {
  id: number;
  title: string;
  completed: boolean;
}

axios.get("https://jsonplaceholder.typicode.com/todos/1").then((response) => {
  const todo = response.data as Todo;
  const id = todo.id;
  const title = todo.title;
  const completed = todo.completed;
  logTodo(title, id, completed); // 类型"string"的参数不能赋给类型"number"的参数。
});

function logTodo(id: number, title: string, completed: boolean) {
  console.log(`
  任务的ID是: ${id},
  任务的名称是: ${title},
  任务是否结束: ${completed}
`);
}
```

## 3. TypeScript 基础类型

### 3.1 概述

以下表格中列出的所有类型在 TypeScript 中都是支持的。

| JavaScript | TypeScript |
| ---------- | ---------- |
| number     | any        |
| string     | unknow     |
| boolean    | never      |
| null       | enum       |
| undefined  | tuple      |
| object     |            |
| array      |            |

### 3.2 基本数据类型

在 TypeScript 中，开发者可以通过类型注释对变量的类型进行标注。

```typescript
// 数值类型
// :number 类型注释
let apples: number = 5;
// 字符串类型
let speed: string = "fast";
// 布尔值布尔
let hasName: boolean = true;
```

```typescript
// TS2322: Type 'string' is not assignable to type 'number'
// 类型 'string' 不能分配给类型 'number'
apples = "5";    // ❎

// TS2322: Type 'number' is not assignable to type 'string'.
// 类型 'number' 不能分配给类型 'string'
speed = 120;     // ❎

// TS2322: Type 'string' is not assignable to type 'boolean'.
// 类型 'string' 不能分类给类型 'boolean'
hasName = "yes"; // ❎
```

<img src="./images/49.png" width="80%" align="left"/>

### 3.3 any 类型

```typescript
// any 类型
// 变量中可以存储任何数据类型的值
let anything: any = "Hello TypeScript";
anything = true;           // ✅
anything = 3.14;           // ✅
anything = function () {}; // ✅
anything = null;           // ✅
```

### 3.4 unknown 类型

unknow 是严格的 any 类型，在对 unknown 类型的变量执行操作之前必须先确定它的类型。

```typescript
let anything: unknown = "Hello TypeScript";
anything = true;            // ✅
anything = 3.14;            // ✅
anything = function () {};  // ✅

// TS2571: Object is of type 'unknown'. ❎
// console.log(anything.length);

if (typeof anything === "number") {
  anything.toFixed();
} else if (typeof anything === "string") {
  anything.toUpperCase();
} else if (typeof anything === "function") {
  anything();
}
```

https://static.kancloud.cn/cyyspring/tyscript/2684794

### 3.5 数组 Array

```typescript
// 在没有为数组变量标注类型时, 变量的初始值又是一个空数组
// 此时该数组中可以存储任何类型的值
// 虽然写法正确, 但丢失了 TypeScript 类型检查功能
// let colors = never[];
let colors = [];
```

```typescript
// 字符串数组
let colors: string[] = ["red", "green", "blue"];
// 数值数组
let numbers: number[] = [100, 200, 300];
// 布尔数组
let bools: boolean[] = [true, true, false];
```

```typescript
// 二维数组
const carMakers: string[][] = [["宝马", "比亚迪"]];
```

```typescript
// let colors: string[]
// item: string
colors.forEach((item) => {});
// a: string
let a = colors[0];
```

### 3.6 元组 Tuples

元组可以按照顺序约束数组中每个下标对应的数据的类型。

[string, number, boolean] => ["a", 100, false]

观察下列代码中存在的问题。

在 employee 数组中我们约定下标为0的位置存储员工姓名，下标为1的位置存储员工的年龄。

```typescript
let employee = ["张三", 20];
employee[0] = 30;
employee[1] = "李四";
```

以上代码中存在的问题是 employee 数组中的元素没有被类型系统约束，导致在修改元素时没有任何错误提示。

元组是 TypeScript 引入的一种新数据类型，它像数组一样工作但是有一些额外的限制：元组中元素个数是固定，元组中元素类型已知。

元组用来存储一些以特定顺序出现且相关联的数据，通过元组可以约束元素个数及元素类型。

```typescript
const employee: [string, number] = ["张三", 20];
// 不能将类型"number"分配给类型"string"
employee[0] = 30;
// 不能将类型"string"分配给类型"number"
employee[1] = "李四";
```

```typescript
// 使用元组存储 RGB 颜色值及透明度
var bgColor: [number, number, number, number] = [0, 255, 255, 0.5];
```

```typescript
// 创建元组数组(二维数组)
var employees: [number, string][] = [
  [1, "Steve"],
  [2, "Tom"],
];
```

### 3.7 枚举 Enum

枚举用于存储一组密切相关且有限的值，可以提升代码的可读性，可以限定值的范围，比如比赛结果，考试成绩，颜色种类，性别等等。

```typescript
enum Sizes {
  Small,  // Small = 0
  Medium, // Medium = 1
  Large,  // Large = 2
}
console.log(Sizes.Small); // 0
```

```typescript
enum Sizes {
  Small = 1,  // Small = 1
  Medium,     // Medium = 2
  Large,      // Large = 3
}
console.log(Sizes.Small); // 1
```

```typescript
// 如果枚举值为非数值, 则每个属性都必须进行初始化
enum Sizes {
  Small = "s",
  Medium = "m",
  Large = "l",
}
console.log(Sizes.Small); // s
```

```javascript
// 枚举被编译为了 JavaScript 中的对象
var Sizes;
(function (Sizes) {
    Sizes["Small"] = "s";
    Sizes["Medium"] = "m";
    Sizes["Large"] = "l";
})(Sizes || (Sizes = {}));
```

```typescript
// 在声明枚举类型时, 如果使用 const 关键字, TypeScript 编译器将输出更加简洁的代码
const enum Sizes {
  Small = "s",
  Medium = "m",
  Large = "l",
}

let selected = Sizes.Large;
console.log(selected);
```

```javascript
// 这是以上代码的编译结果
var selected = "l" /* Large */;
console.log(selected);
```

```typescript
// 枚举使用示例
enum Sizes {
  Small = "s",
  Medium = "m",
  Large = "l",
}

let selected: Sizes = Sizes.Small;

function updateSize(size: Sizes) {
  selected = size;
}

updateSize(Sizes.Large);
```

| 场景                     | 使用 | 不使用 |
| ------------------------ | ---- | ------ |
| 消息的阅读状态           | YES  |        |
| 从1750年到现在的年份列表 |      | NO     |
| 菜单中饮料的种类         | YES  |        |
| 文章列表的所有标题       |      | NO     |
| 服务器端的电影分类       |      | NO     |
| 颜色选择器中的颜色       | YES  |        |

### 3.8 函数 Function

通过类型注释可以标注函数将要接收的参数的类型，函数将要返回的值的类型。

```typescript
function add(a: number, b: number): number {
  return a + b;
}
```

```typescript
const add = (a: number, b: number): number => {
  return a + b;
};
// TS2345: Argument of type 'string' is not assignable to parameter of type 'number'.
add(10, "20");
```

```typescript
// let logNumber: Function;
let logNumber: (n: number) => number;
logNumber = (m) => {
  return m;
};
```

如果函数没有返回值，可以使用 void 标注。

```typescript
function log(): void {}
```

TypeScript 编译器会检查实参的类型及参数数量是否正确。

```typescript
function sum(a: number, b: number): number {
  return a + b;
}

// TS2554: Expected 2 arguments, but got 3.
sum(10, 20, 30);
```

TypeScript 编译器会检测函数的返回值类型是否正确。

```typescript
// TS2366: Function lacks ending return statement and return type does not include 'undefined'.
function sum(a: number): number {
  if (a > 10) return a + 20;
}
```

TypeScript 编译器会检测函数内部没有使用的变量。

```typescript
function log() {
  // 未使用的 局部变量 x 
  // 移除未使用的 局部变量 'x' 
  let x;
}
```

通过 ? 的方式设置可选参数, 它的类型是要么是它原本的类型要么是 undefined。

```typescript
// c?: number | undefined
function sum(a: number, b: number, c?: number): number {
  if (typeof c !== "undefined") {
    return a + b + c;
  }
  return a + b;
}

sum(10, 20);
```

通过参数默认值的方式设置可选参数，它的类型就是原本的类型。

```typescript
// c?: number
function sum(a: number, b: number, c: number = 0): number {
  return a + b + c;
}

sum(10, 20);
```

在定义函数时，如果形参被定义为解构语法，则使用下面的方式为函数形参设置类型。

```typescript
const logWeather = ({ date, weather }: { date: Date; weather: string }) => {
  console.log(date, weather);
};

const today = {
  date: new Date(),
  weather: "sunny"
};

logWeather(today);
```

```typescript
const profile = {
  // 年龄
  age: 20,
  // 坐标
  coords: {
    lat: 0,
    lng: 15,
  },
};

const { age }: { age: number } = profile;
const { coords }: { coords: { lat: number, lng: number } } = profile; // {lat: 0, lng: 15}
const { coords: { lat, lng } }: { coords: { lat: number, lng: number } } = profile;

// const { age }: { age: number } = profile;
// const {
//  coords: { lat, lng },
// }: { coords: { lat: number; lng: number } } = profile;
```

### 3.9 对象 Object

```typescript
// 对象字面量
let point: { x: number; y: number } = { x: 100, y: 200 };
```

```typescript
let employee: { readonly id: number } = { id: 1 };

// TS2540: Cannot assign to 'id' because it is a read-only property.
employee.id = 2;
```

```typescript
// TS2741: Property 'name' is missing in type '{}' but required in type '{ name: string; }'.
let person: { name: string } = {};
```

```typescript
let people = {};
// TS2339: Property 'name' does not exist on type '{}'.
// people.name = "张三";
```

```typescript
// age?: number | undefined
let student: { age?: number } = {};
student.age = 20;
```

```typescript
// 内置对象 类本身可以作为类实例的类型
let date: Date = new Date();

// 自定义类
// 类可以作为类实例的类型
class Car {}
let car: Car = new Car();
```

### 3.10 never 类型

never 表示永远不会发生的类型，即永远不能有值。比如用于抛出错误的函数，用于执行无限循环的函数，它们的返回值就是 never，再比如 never[]，表示数组中不会有值。

```typescript
const throwError = (message: string): never => {
  throw new Error(message);
};
```

```typescript
// const throwError: (message: string) => void
const throwError = (message: string) => {
  if (!message) throw new Error("error");
};
```

注意：如果一个函数永远都不会有返回值，说明函数调用位置后面的代码永远都不会被执行。

### 3.11 可空类型

在 TypeScript 中，undefined 和 null 这两个值本身也可以作为类型使用。

```typescript
// undefined
let nothing: undefined = undefined;
// null
let nothingMuch: null = null;
```

```typescript
function greet(name: string | null | undefined) {
  if (typeof name === "string") {
    console.log(name.toLowerCase());
  } else {
    console.log("something went wrong");
  }
}

greet(undefined);
```

### 3.12 类型推断

TypeScript 编译器能根据一些简单的规则推断变量的类型。

在没有标注变量类型的情况下，编译器将变量初始值的类型作为该变量的类型。

```typescript
// let apples: number
let apples = 5;
// let speed: string
let speed = "fast";
// let hasName: boolean
let hasName = true;
```

```typescript
// let colors: string[]
let colors = ["red", "green", "blue"];
// let numbers: number[]
let numbers = [100, 200, 300];
// let bools: boolean[]
let bools = [true, true, false];
// let anything: never[]
let anything = [];

let ary: (string | number)[] = [1, 'a', 'b', 2, 'c', 'd', 3, 4];
```

```typescript
// let point: {x: number, y: number}
let point = { x: 100, y: 200 };
```

TypeScript 编译器会试图推断函数返回值的类型。

```typescript
// const add: (a: number, b: number) => number
const add = (a: number, b: number) => {
  return a + b;
};
```

```typescript
// const add: (a: number, b: number) => void
const add = (a: number, b: number) => {};
```

```typescript
// const add: (a: number, b: string) => string
const add = (a: number, b: string) => {
  return a + b;
};
```

```typescript
// const add: () => string
const add = () => {
  return "a";
};
```

```typescript
// const find: (name: string) => string | boolean
const find = (name: string) => {
  if (name) {
    return name;
  } else {
    return false;
  }
};
```

注意：使用函数返回值类型推断时，在编写函数内部代码时就失去了函数返回值类型检测功能，所以函数返回值的类型推荐明确指定。

在 TypeScript 编译器可以推断出变量类型的情况下，开发者不需要编写类型注释，也就是说只有在 TypeScript 不能正确推断变量类型的情况下开发者才需要编写类型注释，那么在哪些情况下 TypeScript 编译器不能正确推断出变量的类型呢？

① 如果变量声明后没有被立即初始化，TypeScript 编译器不能正确的推断出它的类型。

```typescript
// 此时 TypeScript 编译器认为它是 Any 类型，即在该变量中可以存储任意类型的数据。
// 该变量失去了 TypeScript 中的类型检查功能
// let anything: any;
let anything;
anything = 12;
anything = "hello";
anything = true;
```

```typescript
// 需求: 遍历 colors 数组, 从中查找绿色, 如果找到将 foundColor 变量的值设置为 true
let colors = ["red", "green", "blue"];
// let foundColor: any
// 解决办法是要么设置初始值, 要么显式指定变量类型
let foundColor;

for (let i = 0; i < colors.length; i++) {
  if (colors[i] === "green") {
    // 由于在声明 foundColor 时没有指定类型
    // 所以此处除了布尔类型以外的其他类型的值也可以设置
    foundColor = true;
  }
}
```

② 当调用的函数返回值为 Any 类型时，我们应该使用类型注释显式声明它的类型。

```typescript
// let json: string;
let json = '{"name": "张三"}';
// let person: any => let person: {name: string}
let person = JSON.parse(json);
```

③ 当变量可能有多个类型的值时。

```typescript
// 需求: 在数组中查找大于 0 的数值, 如果找到将该值赋值给变量 target, 如果没有找到, 将 false 赋值给变量 target
let numbers = [-10, -1, 20];
// target => boolean | number
let target = false;

for (let i = 0; i < numbers.length; i++) {
  if (numbers[i] > 0) {
    // 不能将类型"number"分配给类型"boolean"
    target = numbers[i];
  }
}
```

```typescript
let target: boolean | number = false;
```

④ 函数的参数必须标注类型，TypeScript 并不能推断函数参数的类型。

```typescript
// TS7006: Parameter 'a' implicitly has an 'any' type
function sum(a, b) {}
```

## 4. TypeScript 高级类型

### 4.1 声明类型

通过 type 关键字可以声明类型，声明的类型可以是基本数据类型也可以是复杂数据类型。

注意：通过 interface 只能声明复杂数据类型。

```typescript
// 也可以将此处的声明理解为 为 string 类型起了个别名
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

### 4.2 联合类型

联合类型是指将多个类型进行联合的使用，联合的结果就是新的类型。

为变量设置联合类型就意味着该变量可以是联合类型中的任意类型。

```typescript
// 联合类型: 为一个变量设置多个类型
let a: string | number | boolean = true;
```

```typescript
// arg 参数既可以是字符串类型也可以是数值类型
function fn(arg: string | number) {}
fn("a");
fn(10);
```

在使用联合类型的变量时，编辑器默认只能列出联合类型中所有类型的同名属性，因为 TypeScript 编译器并不能确定它的具体类型是什么。

<img src="./images/50.png" width="80%" align="left"/>

在使用联合类型的变量时通常需要先缩小变量的类型范围，这样编辑器才能给出更加精准的代码提示。

通过类型判断可以缩小变量的类型范围，通过类型判断缩小变量范围再进行变量操作的行为也被叫做类型守卫。

<img src="./images/51.png" width="80%" align="left"/>

<img src="./images/52.png" width="80%" align="left"/>

观察下列代码中存在的问题。

```typescript
// 披萨的尺寸
let pizzaSize: 'large' | 'medium' | 'small' = 'medium'

function selectPizzaSize (size: 'large' | 'medium'| 'small') {
  pizzaSize = size
}

selectPizzaSize('small')
```

以上代码同一个类型被声明了多次导致重复代码过多，可以使用 type 声明类型复用类型。

```typescript
type Size = 'large' | 'medium' | 'small'; 

let pizzaSize: Size = 'medium'

function selectPizzaSize (size: Size) {
  pizzaSize = size
}

selectPizzaSize('large')
```

### 4.3 交叉类型

交叉类型是指将多个类型叠加合并组成新的类型，新类型中包含了被合并类型的所有属性。

```typescript
type Draggable = {
  drag: () => void;
};

type Resizable = {
  resize: () => void;
};

type UIWidget = Draggable & Resizable;

let textBox: UIWidget = {
  drag: () => {},
  resize: () => {},
};
```

### 4.4 字面量类型

字面量类型是指将字面量值的类型作为变量的类型，将字面量值作为变量的取值范围。

将字面值作为变量的取值范围可以有效避免书写错误。

```typescript
type Quantity = 50 | 100;

// TS2322: Type '54' is not assignable to type 'Quantity'.
let quantity: Quantity = 54;
```

```typescript
type Metric = "cm" | "inch";

// TS2322: Type '"abc"' is not assignable to type 'Metric'.
let metric: Metric = "abc";
```

### 4.5 可选链操作符

> 目标：掌握可选链操作符的使用方式

------

- [ ] 分析一段 JavaScript 代码，为讲解可选链操作符做铺垫
- [ ] 可选链操作符和对象一起使用
- [ ] 可选链操作符和数组一起使用
- [ ] 可选链操作符和函数一起使用

------

① 先来看一段 JavaScript 中的代码，分析代码中的潜在问题。

```javascript
// person 对象中的 name 属性是可选的, 即 person 对象中可能有 name 属性也可能没有 name 属性
// 如果 name 属性存在, 它是字符串类型
const person = { name: "John" };
console.log(person.name.toUpperCase()); // JOHN
```

```javascript
const person = {};
console.log(person.name.toUpperCase());
// Uncaught TypeError: Cannot read properties of undefined (reading 'toUpperCase')
```

```javascript
const person = {};
if (person.name) console.log(person.name.toUpperCase());
```

可选链操作符( `?.` )允许开发者安全的链式访问一个对象上可能为 `null` 或 `undefined` 的属性。

② 可选链操作符和对象一起使用

```typescript
interface Person {
  name?: string;
}

const person: Person = { name: "张三" };
person.name?.toLocaleUpperCase();
```

③ 可选链操作符和数组一起使用

```typescript
interface Person {
  skills: string[] | null;
}

const person: Person = {
  skills: ["编程", "开锁", "飞檐走壁"],
};

person.skills?.forEach((item) => console.log(item));
```

③ 可选链操作符和函数一起使用

```typescript
interface Person {
  sayHello?: () => void;
}

const person: Person = {
  sayHello: () => {
    alert("Hello");
  },
};

person.sayHello?.();
```

### 4.6 空值合并运算符

空值合并运算符 (`??`) 是一个逻辑运算符，当左侧的运算数为 null 或者 undefined 时，返回其右侧运算数，否则返回左侧运算数。

```typescript
let speed: number | null = null;

let ride = {
  // speed: speed !== null ? speed : 30,
  speed: speed ?? 30,
};
```

### 4.7 类型断言

通过类型断言可以覆盖 TypeScript 编译器的推断，当开发者比 TypeScript 更加清楚它的类型时使用。

```typescript
// const phone: HTMLElement | null
const phone = document.getElementById("phone");

console.log((<HTMLInputElement>phone).value);
console.log((phone as HTMLInputElement).value);
```

**！非空断言**

### 4.8 函数重载

函数重载是指多个同名函数具有不同的调用签名，通过不同的调用签名决定到底执行哪一个具体的函数，执行的函数不同做的事情可以不同。

```java
// Java 中的函数重载
public class Sum {
  public int sum(int x, int y) {
    return (x + y);
  }
  public int sum(int x, int y, int z) {
    return (x + y + z);
  }
  public double sum(double x, double y) {
    return (x + y);
  }
  public static void main(String args[]) {
    Sum s = new Sum();
    System.out.println(s.sum(10, 20));
    System.out.println(s.sum(10, 20, 30));
    System.out.println(s.sum(10.5, 20.5));
  }
}
```

JavaScript 没有函数重载特性，虽然没有但是我们可以通过代码模拟实现。

需求：定义 sum 函数，接收两个参数 n 和 m，当 n 和 m 都是数值时进行数值相加，当 n 和 m 都是字符串时进行字符串连接。

```javascript
// 在 JavaScript 中通过代码模拟函数重载功能
function sum(n, m) {
  if (typeof n === "number" && typeof m === "number") {
    // 数值相加
    return n + m;
  } else if (typeof n === "string" && typeof m === "string") {
    // 字符串连接
    return n + "_" + m;
  }
}

sum(10, 20); // 30
sum("hello", "world"); // hello_world
```

```typescript
// 在 TypeScript 中实现函数重载
function add(a: number, b: number): number;
function add(a: string, b: string): string;
function add(a: any, b: any) {
  if (typeof a === "string" && typeof b === "string") {
    return a + b;
  } else if (typeof a === "number" && typeof b === "number") {
    return a + "_" + b;
  }
}

const result1 = add(10, 20);
const result2 = add("a", "b");
```

需求：调用 getMessage 方法获取消息，如果传递的参数是数值类型，表示根据消息 id 获取消息对象，如果传递的参数是字符串类型，则表示根据消息种类获取消息列表。

```typescript
// 消息种类的类型
type MessageType = "string" | "image" | "audio";
```

```typescript
// 消息对象的类型
type Message = {
  id: number;
  type: MessageType;
  content: string;
};
```

```typescript
// 消息数组
const data: Message[] = [
  { id: 1, type: "string", content: "hello-1" },
  { id: 2, type: "image", content: "hello-2" },
  { id: 3, type: "audio", content: "hello-3" },
];
```

```typescript
function getMessage(id: number): Message | undefined;
function getMessage(type: MessageType): Message[];
function getMessage(query: any): any {
  if (typeof query === "number") {
    return data.find(message => message.id === query);
  } else {
    return data.filter(message => message.type === query);
  }
}
```

```typescript
// const r1: Message | undefined
const r1 = getMessage(1);
// const r2: Message[]
const r2 = getMessage("image");
```

### 4.9 索引签名

需求: 定义一个电影院放映厅的座位对象并为每一个座位分配人员，对象属性为座位编号，属性值为看电影的人的名字。

```typescript
const seats: { A1: string; A2: string } = { A1: "", A2: "" };
```

在以上代码中存在的问题是，在创建座位对象时我们并不知道每个座位编号对应的人是谁，而且座位又有上百个，如果在定义对象时一定要将所有座位编号定义出来的话，那将一次性定义上百个属性，而每一个属性的值都是空字符串，这样的代码写出来是没有意义的。座位是伴随着时间动态卖出去的, 所以为座位对象分配人员这件事也应该是动态进行的，也就是说我们应该动态为座位对象添加属性分配人员。

------

在 JavaScript 中，为对象动态添加属性是被允许的，但是在 TypeScript 中是不行的，因为在 TypeScript 中对象要有严格的类型限制。

```typescript
let person = {};
// TS2339: Property 'name' does not exist on type '{}'.
person.name = "张三";
```

在 TypeScript 中动态为对象添加属性要使用索引签名，它可以限制对象中属性的类型和属性值的类型，只要满足条件的键值对都可以动态添加到对象中。

```typescript
// 属性类型的定义使用中括号包裹, 索引签名中定义的属性名称并不是真正的属性名称, 它是用来占位的, 但要求名称要具有含义
// [seatNumber: string] 属性的类型
// : string 属性值的类型
const seats: { [seatNumber: string]: string } = {};

seats.A1 = "张三";
seats.A2 = "李四";
```

### 4.10 类型谓词

类型谓词用于缩小函数参数的类型范围。

如果一个函数的返回值为真，可以指定函数的某一个参数的类型。

```typescript
// 如果 fn 函数的返回值为真, arg 参数的类型就是 string
// parameterName is Type
function fn(arg): arg is string {
  return true;
}
```

```typescript
// 鱼
type Fish = {
  swim: () => void;
};
// 鸟
type Bird = {
  fly: () => void;
};

// 获取宠物 可能得到鱼 也可能得到鸟
function getPet(): Fish | Bird {
  // 鱼对象
  const fish = {
    swim() {
      console.log("swim");
    },
  };
  // 鸟对象
  const bird = {
    fly() {
      console.log("fly");
    },
  };
  return Math.round(Math.random() * 10) > 5 ? fish : bird;
}

// 判断 pet(宠物) 参数是否为鱼类型
function isFish(pet: Fish | Bird): pet is Fish {
  return (<Fish>pet).swim !== undefined;
}

// 获取宠物
let pet = getPet();

// 判断宠物类型
if (isFish(pet)) {
  //  如果是鱼调用游泳方法
  pet.swim();
} else {
  // 否则调用鸟的飞翔方法
  pet.fly();
}
```

```typescript
// 返回参数 value 的数据类型
function getType(value: unknown) {
  return Object.prototype.toString.call(value).slice(8, -1).toLowerCase();
}

// isString 函数的返回值为布尔类型
// 如果 isString 函数的返回值为 true, TypeScript 编译器就认定 s 是字符串类型
function isString(value: unknown): value is String {
  return getType(value) === "string";
}

function toUpperCase(value: unknown) {
  if (isString(value)) {
    value.toUpperCase();
  }
}
```

## 5. 面向对象编程

### 5.1 概述

面向对象和面向过程都属于编写程序的指导思想，指导开发者按照什么样的套路编写代码。

面向过程是侧重于过程的编程，即按照功能代码的执行顺序编写代码。在编写程序时要明确知道每一步要做什么，比如将大象装冰箱一共分为三步，那么按照步骤编写代码即可。

面向对象是侧重于对象的编程，开发者站在上帝视角，要想什么就创建什么对象，然后赋予对象做事情的能力，要做的事情让对象自己完成。比如将大象装冰箱，我们要创建大象对象，创建冰箱对象，赐予大象进冰箱的能力，然后让大象自己进冰箱。

面向过程的优点是简单，思考简单，编写简单。

面向过程的缺点是代码量越大维护成本越高。当程序出问题后，需要从头到尾将步骤过一遍，在此过程中找问题，修复问题，如果代码中步骤非常多的话，是非常耗时间和精力的。

面向对象的优点的是扩展性强维护成本低，当程序出问题后定位对象即可，哪个对象出问题就找哪个对象修复哪个对象就可以了。

面向对象的缺点是新人上手难度高。

### 5.2 类与对象

在面向对象中通过对象来表示具体的事物，对象才可以干具体的事情，那么我们如何创建对象呢？比如创建一个表示车的对象。

现实世界中第一步是画车辆图纸，在图纸中定义车的特征和车的能力，比如车是什么颜色，有多少座位，车可以驾驶。第二步是根据图纸造真实车辆。

面向对象中使用类表示图纸，定义类就是画图纸，类中包含两部分，属性和方法，属性就是特征，方法就是能力。通过类可以创造对象。对象能干什么完全取决于你的类是如何定义的。

```typescript
class Vehicle {
  // 特性: 颜色
  color = "白色";
  // 特性: 最高速度
  maxSpeed = 220;
  // 能力: 驾驶
  drive() {
    console.log("run");
  }
  // 能力: 鸣笛
  honk = () => {
    console.log("didi");
  }
}
```

```typescript
const vehicle = new Vehicle();
console.log(vehicle);
console.log(vehicle.color);
console.log(vehicle.maxSpeed);
vehicle.drive();
vehicle.honk();
```

通过同一个类可以创建多个相同的实物对象，所以属性就是这一类事物拥有的共同特性，方法就是这一类事物拥有的共同的能力。

```typescript
const v1 = new Vehicle();
const v2 = new Vehicle();
const v3 = new Vehicle();
```

通过类创建出来的对象也被称之为实例对象。

```javascript
function Vehicle () {
  this.color = '白色';
  this.maxSpeed = 220;
  this.honk = function () {
    console.log('didi')
  }
}

Vehicle.prototype.drive = function () {
  console.log('run')
}
```

### 5.3 构造函数

构造函数用来为对象属性赋初始值或者执行初始化操作，构造函数的名字是固定的，即 constructor。

```typescript
class Vehicle {
  constructor () {}
}
```

在使用 new 关键字创建对象时可以传递参数、该参数在类的内部通过构造函数接收，参数值一般就是对象中某个属性的初始值。

```typescript
class Vehicle {
  constructor (color: string, maxSpeed: number) {}
}

const vehicle = new Vehicle("红色", 240);
```

在构造函数中 this 关键字指向的是通过类创建出来的对象，所以通过 this 可以找到对象的属性，然后就可以为属性赋值了。

```typescript
class Vehicle {
  color: string;
  maxSpeed: number;

  constructor(color: string, maxSpeed: number) {
    this.color = color;
    this.maxSpeed = maxSpeed;
  }
}

const vehicle = new Vehicle("红色", 240);
```

类的构造函数是被自动调用的，在使用 new 关键字创建对象时被自动调用。

### 5.4 只读属性

只读属性是指属性一旦被赋值，该值就不能被修改。

在类中通过 readonly 修饰符将类属性设置为只读属性。

```typescript
class Vehicle {
  readonly maxSpeed: number;
  constructor(maxSpeed: number) {
    this.maxSpeed = maxSpeed;
  }

  drive() {
    //  TS2540: Cannot assign to 'maxSpeed' because it is a read-only property.
    this.maxSpeed = 900;
  }
}

const vehicle = new Vehicle(240);
// TS2540: Cannot assign to 'maxSpeed' because it is a read-only property.
vehicle.maxSpeed = 300;
```

### 5.5 继承

在面向对象编程中，很多对象都会具有相同的特征和能力，比如猫对象和狗对象，它们都具有五官特征都具备吃饭和奔跑的能力，如果我们分别在猫类和狗类中定义五官特征和奔跑能力的话，那么程序中将出现大量的重复性代码。

要解决上述问题，我们可以对类别进行更高级别的抽象，比如我们可以再定义一个动物类，将动物具备的通用特性定义在动物类中，比如五官特征和奔跑吃饭的能力。那么猫类和狗类如何拥有这些特征和能力呢？

答案就是继承，通过继承可以让具体的猫类和狗类拥有这些特征和能力，这样程序中就不会出现重复性代码了，然后我们还可以在猫类和狗类中继续定义属于猫和狗的独有特征和能力。

```typescript
class Animal {}

class Dog extends Animal {}
class Cat extends Animal {}
```

在以上代码中，Animal 被称之为父类，Dog 和 Cat 被称之为子类。

在程序中分别创建汽车分类，轿车分类、面包车分类，在汽车分类下描述所有汽车的通用特征，在细化分类下只描述该分类独有的特征。

```typescript
// 汽车分类
class Vehicle {
  drive() {
    console.log("run");
  }
}

class Car extends Vehicle {
  brand = "BMW";
}

class Van extends Vehicle {
  brand = "五菱宏光";
}

let car = new Car();
car.drive();
car.brand;

let van = new Van();
van.drive();
van.brand;
```

在子类继承父类后，如果父类需要在初始化的时候传递参数，该参数由子类接收，在子类的构造函数中通过 super 调用父类将参数传递给父类。

```typescript
class Person {
  constructor(public name: string) {}
}

class Student extends Person {
  studentId: number
  // 注意: 在子类继承父类后, 如果要在子类中使用构造函数, 即使父类不需要传递参数
  // 在构造函数中的第一件事情也是必须先调用 super 方法
  constructor(studentId: number, name: string) {
    super(name);
    this.studentId = studentId;
  }
}

const p1 = new Student(1, "张三");
console.log(p1.studentId);
console.log(p1.name);
```

子类在继承父类后，还可以通过重写父类方法对功能进行扩展。

```typescript
class Person {
  walk() {
    console.log("person walk");
  }
}

class Student extends Person {
  // override 表示该方法是覆盖的父类方法, 仅作为标识提升代码的可阅读性, 不会对代码的实际执行产生任何副作用
  override walk() {
    console.log("student walk");
    // 通过 super 调用父类 walk 方法
    super.walk();
  }
}

const p1 = new Student();
p1.walk();
```

### 5.6 访问权限修饰符

通过访问权限修饰符可以指定类中的属性、方法能够在哪些范围内被访问。

| 修饰符    | 作用                                                         |
| --------- | ------------------------------------------------------------ |
| public    | 被 public 关键字修饰的类属性和类方法可以在任何地方使用 (当前类、子类、实例对象) |
| private   | 被 private 关键字修饰的类属性和类方法只能在当前类中使用      |
| protected | 被 protected 关键字修饰的类属性和类方法可以在当前类和子类中使用 |

**① public 公开的**

类属性和类方法在不加任何权限修饰符的情况下，它就是可以在任何地方被访问的，也就是说 public 是默认值，是可以被省略的。

```typescript
class Vehicle {
  constructor() {
    // 1. 在本类中的其他方法中使用
    this.drive();
  }
  public drive() {
    console.log("run");
  }
}

class Car extends Vehicle {
  drive() {
    // 2. 在子类中使用
    super.drive();
  }
}

const car = new Car();
// 3. 在实例对象中使用
car.drive();
```

**② private 私有的**

```typescript
class Vehicle {
  constructor() {
    // 在本类中的其他方法中使用
    this.drive();
  }
  private drive() {
    console.log("run");
  }
}

class Car extends Vehicle {
  drive() {
    // 属性"drive"为私有属性，只能在类"Vehicle"中访问
    super.drive();
  }
}

const vehicle = new Vehicle();
// 属性"drive"为私有属性，只能在类"Vehicle"中访问
vehicle.drive();
```

**③ protected 受保护的**

```typescript
class Vehicle {
  constructor() {
    // 1. 在本类中的其他方法中使用
    this.drive();
  }
  protected drive() {
    console.log("run");
  }
}

class Car extends Vehicle {
  drive() {
    // 2. 在子类中使用
    super.drive();
  }
}

const vehicle = new Vehicle();
// 属性"drive"受保护, 只能在类"Vehicle"及其子类中访问
vehicle.drive();
```

### 5.7 Getter 与 Setter

Getter 和 Setter 是对属性访问(获取和设置)的封装，获取属性值时走 Getter，修改属性值时走 Setter。

可以将属性访问器理解为对属性的保护。

在获取属性或修改属性值时，通过属性访问器可以监听到，监听到以后可以做一些额外的事情。

```typescript
class Employee {
  private _salary: number;
  constructor(salary: number) {
    this._salary = salary;
  }

  get salary() {
    return this._salary;
  }

  set salary(salary: number) {
    this._salary = salary;
  }
}

const employee = new Employee(4000);
console.log(employee.salary);
employee.salary = 6000;
console.log(employee.salary);
```

```bash
# 通过选项的方式指定 target
tsc -t es2016 index.ts
# 通过配置文件的方式指定 target
tsc --init
# 若要走配置文件 直接执行 tsc 命令
tsc
```

### 5.8 参数属性

TypeScript 提供了特殊的语法将构造函数参数转换为具有相同名称的类属性。 

通过在构造函数参数的前面加上权限修饰符 public、private、protected 或 readonly 创建。

```typescript
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

```typescript
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

### 5.9 静态成员

在类中被 static 关键字修饰的类属性和类方法被叫做静态成员，静态成员属于类，所以访问静态成员的方式是类名点上静态成员名称。

```typescript
class Rich {
  static count: number = 0;
}
// 通过 类名 + 静态成员名称 访问静态成员
Rich.count;
```

```typescript
// 以下写法错误
// 静态成员不属于类的实例对象, 所以不能通过类的实例对象访问静态成员
const r = new Rich();
// TS2576: Property 'count' does not exist on type 'Rich'. 
// Did you mean to access the static member 'Rich.count' instead?
r.count;
```

```typescript
class Rich {
  static count: number = 0;
  getCount () {
    return Rich.count;
  }
}
```

对于该类的任何一个对象而言，静态属性就是公共的存储单元，该类的任何对象访问它时，取到的都是相同的值，该类的任何对象修改它时，也都是在对同一个内存单元做操作，所以静态属性主要用在各个对象都要共享的数据。

目标: 获取类的实例对象的数量。

```typescript
 class Rich {
  private static _count: number = 0;

  constructor() {
    Rich._count++;
  }

  getCount() {
    return Rich._count;
  }
}

const r1 = new Rich();
const r2 = new Rich();

console.log(r1.getCount());
console.log(r2.getCount());
```

因为静态成员始终存在于内存，而非静态成员需要实例化才可以分配到内存，所以静态成员不能访问非静态成员，非静态成员可以访问类中的静态成员。

```typescript
class Rich {
  private static _count: number = 0;
  // 静态成员中不能访问非静态成员
  // 静态方法中的 this 指向了类, 而不是类实例
  static fn() {
    // TS2339: Property 'getCount' does not exist on type 'typeof Rich'.
    // this.getCount();
  }
  // 非静态成员中可以访问静态成员
  getCount() {
    return Rich._count;
  }
}
```

### 5.10 抽象类

抽象类因继承而存在，通过抽象类可以约束子类必须实现哪些成员，如果抽象类不被继承，它将毫无意义。

在抽象类中可以只定义成员，具体的成员实现由子类完成且必须完成，所以抽象类不能被直接实例化。

```typescript
abstract class Shape {
  abstract color: string;
  abstract render(): void;
}

class Circle extends Shape {
  constructor(public color: string) {
    super();
  }
  override render(): void {
    console.log("render");
  }
}

// TS2511: Cannot create an instance of an abstract class.
// new Shape();

const circle = new Circle("red");
console.log(circle.color);
circle.render();
```

## 6. 接口 Interface

接口用于声明类型，用于对复杂的数据结构进行类型描述，比如对象、函数、类。

### 6.1 接口概基本使用

声明接口需要使用 interface 关键字。

```typescript
interface User {
  name: string;
  age: number;
}
```

使用接口约束对象的类型。

```typescript
const user: User = {
  name: "张三",
  age: 20,
};
```

使用接口约束函数的类型。

```typescript
interface Sum {
  (n: number, m: number): number;
}

const sum: Sum = function (a, b) {
  return a + b;
};
```

使用接口约束类的成员。

```typescript
interface Calendar {
  name: string;
  addEvent(): void;
  removeEvent(): void;
}

class GoogleCalendar implements Calendar {
  name: string = "test";
  addEvent(): void {}
  removeEvent(): void {}
}
```

### 6.2 宽松的接口检查

TypeScript 接口检查是宽松的，当变量满足了接口规范以后，即使变量中存在接口规范以外的属性也是可以的。

```typescript
interface User {
  name: string;
  age: number;
}

let user: User = {
  name: "张三",
  age: 20,
};

let someone = {
  name: "李四",
  age: 50,
  sex: "男",
};

user = someone;
```

```typescript
interface Reportable {
  summary(): void;
}

function printSummary(item: Reportable): void {
  item.summary()
}

const person = {
  name: "张三",
  summary() {
  	console.log(`您好, 我的名字叫${this.name}`);
  },
};

printSummary(person);
```

对于宽松的接口检查政策字面量是个例外，也就是说对于字面量的接口类型检查是严格的，不能出现接口规范以外的其他属性。

```typescript
interface User {
  name: string;
  age: number;
}

// ✅
const user: User = { name: "张三", age: 20 };

// ❌
// 不能将类型"{ name: string; age: number; sex: string; }"分配给类型"User"
// 对象字面量只可以指定已知属性, "sex"不在类型"User"中
const another: User = { name: "李四", age: 40, sex: "男" };
```

```typescript
interface Reportable {
  summary(): void;
}

function printSummary(item: Reportable): void {
  item.summary();
}

// ❌
// 类型"{ name: string; summary(): void; }"的参数不能赋给类型"Reportable"的参数。
// 对象字面量只可以指定已知属性, "name"不在类型"Reportable"中。ts(2345)
printSummary({
  name: "张三",
  summary() {
    console.log(`您好, 我的名字叫${this.name}`);
  },
});
```

那么如何绕过字面量严格类型检查模式呢？

```typescript
// 使用类型断言
interface User {
  name: string;
  age: number;
}

const another: User = { name: "李四", age: 40, sex: "男" } as User;
```

```typescript
// 使用索引签名
interface User {
  name: string;
  age: number;
  [key: string]: string | number;
}

const another: User = { name: "李四", age: 40, sex: "男" };
```

### 6.3 接口继承

接口具有继承特性即接口与接口之间可以存在继承关系，而且一个接口可以继承多个接口。

```typescript
// 接口继承示例
interface Sizes {
  sizes: string[];
  getAvailableSizes(): string[];
}

interface Shape {
  color: string;
}

interface Pizza extends Sizes, Shape {
  name: string;
}

let pizza: Pizza = {
  name: "张三",
  color: "skyblue",
  sizes: ["large", "small"],
  getAvailableSizes() {
    return this.sizes;
  },
};
```

在继承了接口以后可以对被继承接口中的属性进行重写，但是重写的类型一定要在原有类型的范围以内。

```typescript
interface User {
  // name: string | number | boolean;
  name: any;
  age: number;
}

interface MyUser extends User {
  name: boolean;
}
```

### 6.4 接口合并

接口具有声明合并特性，即多个相同名称的接口会自动合并。

```typescript
interface Box {
  height: number;
  width: number;
}

interface Box {
  scale: number;
}

let box: Box = { height: 5, width: 6, scale: 10 };
```

### 6.5 接口函数重载

```typescript
interface GetMessage {
  (id: number): Message | undefined;
  (type: MessageType): Message[];
}

const getMessage: GetMessage = (query: any): any => {
  if (typeof query === "number") {
    return data.find((message) => message.id === query);
  } else {
    return data.filter((message) => message.type === query);
  }
};
```

## 7. 泛型 Generic

### 7.1 概述

泛型是指将类型作为参数进行传递，通过参数传递类型解决代码复用问题 ( 代码中类型的复用问题，通过类型复用减少重复代码 )。

泛型传参和函数传参要解决的问题是一样的。

```typescript
function a() {
  return 1 + 1;
}

function b() {
  return 2 + 2;
}

function c() {
  return 3 + 3;
}
```

```typescript
function sum(n: number, m: number) {
  return n + m;
}
sum(1, 1);
sum(2, 2);
sum(3, 3);
```

### 7.2 泛型函数

目标：声明一个函数，接收一个参数，接收什么参数返回什么值。

```typescript
// 失去了 ts 中的类型检查
function getValue(value: any): any {
  return value;
}
```

```typescript
function getString(value: string): string {
  return value;
}

function getNumber(value: number): number {
  return value;
}
```

```typescript
function getValue<T>(value: T): T {
  return value;
}

getValue<string>("hello");
getValue<number>(100);

// 泛型实参可以忽略 你传递的参数的类型就是要传递的泛型的类型
getValue("hello");
```

### 7.3  泛型类

需求：通过类创建对象，对象的 key 属性可以是字符串可以是数值，对象的 value 属性可以是字符串可以是数值。

```typescript
{key: string, value: string}
{key: number, value: number}
```

```typescript
class StringKeyValuePair {
  constructor(public key: string, public value: string) {}
}

class NumberKeyValuePair {
  constructor(public key: number, public value: number) {}
}
```

```typescript
class KeyValuePair<K, V> {
  constructor(public key: K, public value: V) {}
}
```

需求：通过类创建对象，对象中有 collection 属性，属性值为数组，数组中可以存储字符串也可以存储数值，通过索引可以获取到collection数组中的值。

```typescript
class ArrayOfNumbers {
  constructor(public collection: number[]) {}
  get(index: number): number {
    return this.collection[index];
  }
}

class ArrayOfStrings {
  constructor(public collection: string[]) {}
  get(index: number): string {
    return this.collection[index];
  }
}
```

在以上代码中，数值数组类和字符串数组类所做的事情是一样的，但由于创建的数据类型不同，所以写成了两个类，它们属于重复代码。

```typescript
class ArrayOfAnything<T> {
  constructor(public collection: T[]) {}
  get(index: number): T {
    return this.collection[index];
  }
}
// constructor ArrayOfAnything<number>(collection: number[]): ArrayOfAnything<number>
new ArrayOfAnything<number>([1, 2, 3]);
// constructor ArrayOfAnything<string>(collection: string[]): ArrayOfAnything<string>
new ArrayOfAnything<string>(["a", "b", "c"]);
```

### 7.4 泛型接口

**需求：**

创建 fetch 方法用于获取数据

当获取用户数据时，fetch 方法的返回值类型为用户

当获取产品数据时， fetch 方法的返回值类型为产品

不论是用户数据还是产品数据都要被包含在响应对象中。

```typescript
interface MyUserResponse {
  data: User;
}

interface MyProductResponse {
  data: Product;
}
```

```typescript
interface MyResponse<T> {
  data: T | null;
}

function fetch<T>(): MyResponse<T> {
  return { data: null };
}

interface User {
  username: string;
}

interface Product {
  title: string;
}

fetch<User>().data?.username;
fetch<Product>().data?.title;
```

### 7.5 泛型约束

泛型约束是指对泛型参数的范围进行约束，就是说虽然类型可以被当做参数传递，但是传递的类型不能是随意的想传什么就传什么，通过泛型约束可以限制能够传递的类型的范围。

```typescript
// 限制类型 T 的范围, 就是说 T 的类型要么是字符串要么是数值 其他的是不可以的
class StringOrNumberArray<T extends string | number> {
  constructor(public collection: T[]) {}
  get(index: number): T {
    return this.collection[index];
  }
}

new StringOrNumberArray<string>(["a", "b"]);
new StringOrNumberArray<number>([100, 200]);
// 类型"boolean"不满足约束"string | number"
// new StringOrNumberArray<boolean>([true, false]);
```

```typescript
function echo<T extends string | number>(value: T): T {
  return value;
}
echo<string>("Hello"); // ✅
echo<number>(100);     // ✅
echo<boolean>(true);   // ❎ 类型"boolean"不满足约束"string | number". ts(2344)
```

```typescript
interface Person {
  name: string;
}

function echo<T extends Person>(value: T): T {
  return value;
}

echo<Person>({ name: "张三" }); // ✅
```

```typescript
class Person {
  constructor(public name: string) {}
}

class Custom extends Person {}

function echo<T extends Person>(value: T): T {
  return value;
}

echo<Person>(new Person("张三"));
echo<Custom>(new Custom("李四"));
```

```typescript
interface Printable {
  print(): void;
}

function echo<T extends Printable>(target: T) {
  target.print();
}

class Car {
  print() {}
}

class Hourse {
  print() {}
}

echo<Car>(new Car());
echo<Hourse>(new Hourse());
```

## 8. 类型操作符

### 8.1 keyof 操作符

keyof 是类型运算符，接收类型返回类型，返回的是接收类型的属性字面量联合类型。

```typescript
interface Product {
  name: string;
  price: number;
}

// 属性字面量联合类型 name | price
// type ProductKeys = "name" | "price"
type ProductKeys = keyof Product;

const keys1: ProductKeys = "price";
const keys2: ProductKeys = "name";
// 不能将类型"a"分配给类型“keyof Product”。
// const keys3: ProductKeys = "a";
```

```typescript
// 在 store 类中声明 find 方法, 该方法的作用是根据属性和属性值在 _objects 数组中查找对象
// [{name: "张三"}, {price:100}]
// store.find("name", "张三"); // {name: "张三"}

class Store<T> {
  protected _objects: T[] = [];

  add(obj: T) {
    this._objects.push(obj);
  }

  find(property: keyof T, value: unknown): T | undefined {
    return this._objects.find((obj) => obj[property] === value);
  }
}

interface Product {
  name: string;
  price: number;
}

const store = new Store<Product>();
store.add({ name: "Hello", price: 100 });
store.find("name", "Hello");
store.find("price", 100);
// TS2345: Argument of type '"a"' is not assignable to parameter of type 'keyof Product'.
store.find("a", 12);
// TS2345: Argument of type '"greet"' is not assignable to parameter of type 'keyof Product'.
store.find("greet", "hi");
```

```typescript
// 多学一招: 获取类型中某一个属性值的类型
interface Product {
  name: string;
  price: number;
}
// type nameType = string
type nameType = Product["name"];
// type nameType = number
type priceType = Product["price"];
```

### 8.2 typeof 操作符

TypeScript 在语言层面又添加了一个新的 typeof 运算符，该运算符可以用来获取一个变量的类型。

```typescript
const person = {
  name: "张三",
  age: 20,
};
type Person = typeof person;
```

```typescript
function fn() {
  return false;
}

type r = ReturnType<typeof fn>;
```

## 10. 映射类型

### 10.1 概述

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

### 10.2 MyReadOnly

>目标：基于 Product 类型，创建新类型，新类型中的属性都是只读的。 

```typescript
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

```typescript
type ReadOnlyProduct = {
 readonly [K in keyof Product]: Product[K];
};

let product: ReadOnlyProduct = {
  name: "test",
  price: 100,
};

// TS2540: Cannot assign to 'name' because it is a read-only property.
product.name = "hello";
```

> 目标：创建 MyReadOnly 类型，通过泛型参数接收旧的类型，返回新类型，新类型中的属性都是只读的。

```typescript
type MyReadOnly<T> = {
  readonly [K in keyof T]: T[K];
};

let product: MyReadOnly<Product> = {
  name: "test",
  price: 100,
};

// TS2540: Cannot assign to 'name' because it is a read-only property.
product.name = "hello";
```

### 10.3 MyOptional 类型

> 目标：创建 MyOptional 类型，通过泛型参数接收旧的类型，返回新类型，新类型中的属性都是可选的。

```typescript
type MyOptional<T> = {
  [K in keyof T]?: T[K];
};
```

### 10.4 MyNullable 类型

> 目标：创建 MyNullable 类型，通过泛型参数接收旧的类型，返回新类型，新类型中的属性都是可以为 null 的。

```typescript
type MyNullable<T> = {
  [K in keyof T]: T[K] | null;
};
```

### 10.5 MyWritable 类型

> 目标：创建 MyWritable 类型，通过泛型参数接收旧的类型，返回新类型，新类型中的属性都是可写的。

```typescript
interface User {
  readonly username: string;
}

type MyWritable<T> = {
  -readonly [K in keyof T]: T[K];
};

const userCanWrite: MyWritable<User> = {
  username: "李四",
};

userCanWrite.username = "王五";
```

### 10.6 MyNecessary 类型

> 目标：创建 MyNecessary 类型，通过泛型参数接收旧的类型，返回新类型，新类型中的属性都是必选的。

```typescript
interface User {
  username?: string;
}

type MyNecessary<T> = {
  [K in keyof T]-?: T[K];
};

// 类型 "{}" 中缺少属性 "username", 但类型 "Necessary<User>" 中需要该属性
const user: MyNecessary<User> = {};
```

## 11. 工具类型

### 11.1 Partial

将类型中的属性都变成可选的，接收类型，返回类型。

```ty
Partial<Type>
```

```typescript
interface Person {
  name: string;
  age: number;
}

type anothor = Partial<Person>;
// {name?: string, age?: number}
```

```typescript
function updateObject<T>(obj: T, props: Partial<T>) {
  return { ...obj, ...props };
}

updateObject<Person>(person, { name: "李四" });
```

### 11.2 Readonly

将类型中的属性都变成只读的，接收类型，返回类型。

```typescript
Readonly<Type>
```

```typescript
interface Person {
  name: string;
  age: number;
}

// {readonly name: string, readonly age: number}
const anthor: Readonly<Person> = {
  name: "李四",
  age: 40,
};

// 不可以修改
// anthor.name = "赵六";
```

### 11.3 Record

```typescript
Record<Keys, Type>
```

```typescript
// 字典
let employees = {
  1: { id: 1, fullname: "John Doe", role: "Designer" },
  2: { id: 2, fullname: "Ibrahima Fall", role: "Developer" },
  3: { id: 3, fullname: "Sara Duckson", role: "Developer" },
}
```

```typescript
interface EmployeeType {
  id: number
  fullname: string
  role: string
}

let employees: Record<number, EmployeeType> = {
  1: { id: 1, fullname: "John Doe", role: "Designer" },
  2: { id: 2, fullname: "Ibrahima Fall", role: "Developer" },
  3: { id: 3, fullname: "Sara Duckson", role: "Developer" },
}
```

### 11.4 Omit

接收类型，得到新类型，在新类型中不要包含 keys

```typescript
Omit<Type, Keys>
```

```typescript
interface Todo {
  title: string;
  description: string;
  completed: boolean;
  createdAt: number;
}

/*
  type TodoPreview = {
    completed: boolean;
    createdAt: number;
  }
*/
// Omit 单词意为"省略"
type TodoPreview = Omit<Todo, "description" | "title">;
```

### 11.5 Pick

接收类型，返回新类型，在新类型中要包含 keys

```typescript
Pick<Type, Keys>
```

```typescript
interface Todo {
  title: string;
  description: string;
  completed: boolean;
}

/*
  type TodoPreview = {
    title: string;
    completed: boolean;
  }
*/
// Pick 单词意为"选择"
type TodoPreview = Pick<Todo, "title" | "completed">;
```

### 11.6 Exclude

接收联合类型，得到新类型，在新类型中排除联合类型中的 ExcludedMembers。

```typescript
Exclude<UnionType, ExcludedMembers>
```

```typescript
// type T0 = "b" | "c"
type T0 = Exclude<"a" | "b" | "c", "a">;
// type T1 = "c"
type T1 = Exclude<"a" | "b" | "c", "a" | "b">;
```

## 4. 地图标记案例

需求：随机创建用户和公司，并将他们的位置标注到地图中。

### 4.1 案例准备

#### 1. 安装依赖

[parcel](https://parceljs.org/) 是前端构建工具，类似 webpack，parcel 的最大特点是打包速速非常快而且零配置开箱即用。

为什么需要模块打包工具？

ts -> js -> 代码不能直接运行，浏览器不识别模块。
ts -> js -> 模块打包 -> 在浏览器中直接运行。

```bash
npm install parcel@2.4.1 -g
yarn global add add parcel@2.4.1 -g
```

使用 [@faker-js/faker](https://www.npmjs.com/package/@faker-js/faker) 可以随机创建模拟数据，当前案例中用于随机生成用户和公司信息。

```bash
npm install @faker-js/faker@6.1.1
```

[@types/google.maps](https://www.npmjs.com/package/@types/google.maps) 是谷歌地图的 TS 类型声明文件。

```bash
npm install @types/google.maps@3.48.3
```

#### 2. 创建应用

```bash
# 创建 maps 目录并切换至 maps 目录
mkdir maps && cd maps
# 创建项目工程文件
npm init -y
# 详见应用源码目录
mkdir src
# 创建应用页面入口
touch index.html
# 切换至应用源码目录并创建应用逻辑入口
cd src && touch index.ts
```

```html
<!-- 在应用页面入口文件中导入应用逻辑入口文件 -->
<!-- 当 parcel 检测到引入的是 TS 文件时, parcel 会先将其转换为 JS, 再将 TS 文件替换为转换后的 JS  -->
<script src="./src/index.ts" type="module"></script>
```

```bash
# 启动开发服务器, 运行应用
parcel index.html
```

### 4.2 创建用户

```typescript
// src/User.ts
import faker from "@faker-js/faker";

export class User {
  // 姓名
  name: string;
  // 位置
  location: {
    // 纬度
    lat: number;
    // 经度
    lng: number;
  };
  constructor() {
    // 随机创建用户姓名
    this.name = faker.name.firstName();
    // 随机创建用户位置
    this.location = {
      lat: parseFloat(faker.address.latitude()),
      lng: parseFloat(faker.address.longitude()),
    };
  }
}
```

```typescript
// src/index.ts
// 导入 User 类
import { User } from "./User";
// 创建用户
const user = new User();
// 测试:在控制台中输出用户信息
console.log(user);
```

### 4.3 创建公司

```typescript
// src/Company.ts
import faker from "@faker-js/faker";

export class Company {
  // 公司名称
  companyName: string;
  // 公司口号
  catchPhrase: string;
  // 公司位置
  location: {
    // 纬度
    lat: number;
    // 经度
    lng: number;
  };
  constructor() {
    // 随机创建公司名称
    this.companyName = faker.company.companyName();
    // 随机创建公司口号
    this.catchPhrase = faker.company.catchPhrase();
    // 随机创建公司位置
    this.location = {
      lat: parseFloat(faker.address.latitude()),
      lng: parseFloat(faker.address.longitude()),
    };
  }
}
```

```typescript
// src/index.ts
// 导入 Company  类
import { Company } from "./Company";
// 创建公司
const company = new Company();
// 测试: 在控制台中输出公司信息
console.log(company);
```

### 4.4 创建地图

<img src="./images/06.png" />

第一步：在应用页面入口文件中添加 Google Map API。

```html
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBNLrJhOMz6idD05pzfn5lhA-TAw-mAZCU"></script>
```

第二步：在应用入口文件中创建用户放置地图的容器。

```html
<style>
  html, body, #map {
    height: 100%;
    margin: 0;
    padding: 0;
  }
</style>
<div id="map"></div>
```

第三步：创建地图

```typescript
new google.maps.Map(document.getElementById("map"), {
  zoom: 1,
  center: {
    lat: 0,
    lng: 0,
  },
});
```

### 4.5 封装地图类

目标：创建 Map 类，在类中封装和地图的相关的业务逻辑，以后在创建地图时只需要对该类进行实例化即可。

```typescript
// src/Map.ts
export class Map {
  // 用于存储地图实例
  private googleMap: google.maps.Map;
  constructor(divId: string) {
    // 创建地图
    this.googleMap = new google.maps.Map(document.getElementById(divId), {
      zoom: 1,
      center: {
        lat: 0,
        lng: 0,
      },
    });
  }
}
```

```typescript
// src/index.ts
import { Map } from "./Map";
// 创建地图
new Map("map");
```

### 4.6 地图标记

<img src="./images/05.png" />

```typescript
// src/Map.ts
import { Company } from "./Company";
import { User } from "./User";

export class Map {
  // 在地图中标记用户位置
  addUserMarker(user: User) {
    new google.maps.Marker({
      map: this.googleMap,
      position: {
        lat: user.location.lat,
        lng: user.location.lng,
      },
    });
  }
  // 在地图中标记公司位置
  addCompanyMarker(company: Company) {
    new google.maps.Marker({
      map: this.googleMap,
      position: {
        lat: company.location.lat,
        lng: company.location.lng,
      },
    });
  }
}
```

```typescript
// src/index.ts
const map = new Map("map");
map.addUserMarker(user);
map.addCompanyMarker(company);
```

### 4.7 消除重复代码

在目前的代码中，addUserMarker 和 addCompanyMarker 两个方法中的代码是一模一样的，为消除重复代码，现决定将两个方法进行合并，并将参数更改为 User 和 Company 的联合类型。

```typescript
import { Company } from "./Company";
import { User } from "./User";

export class Map {
  // 在地图中标记位置
  addMarker(mappable: User | Company) {
    new google.maps.Marker({
      map: this.googleMap,
      position: {
        lat: mappable.location.lat,
        lng: mappable.location.lng,
      },
    });
  }
}
```

由于 location 属性是 User 、Company 类型中的公共属性，所以在addMarker 方法中可以直接调用。

###  4.8 增加程序扩展性

虽然重复代码被消除了，但是这不利于程序的扩展性，如果要在地图中标记其他物体的位置信息，那么就只能在参数后面不断的罗列其他物体的类型信息了。

```typescript
addMarker(mappable: User | Company | Park | School) {}
```

解决问题的方式是定义通用的类型接口，不论是什么数据类型只要满足接口中定义的规范即可，从而解决程序扩展性问题。

```typescript
interface Mappable {
  location: {
    lat: number;
    lng: number;
  };
}
```

```typescript
addMarker(mappable: Mappable) {}
```

在目前的代码中，不论是 User 类的实例还是 Company 类的实例，都是满足 Mappable 接口规范的。

### 4.9 创建标记弹框

目标：在点击地图中的标记时，通过弹框展示标记信息。

```typescript
// src/Map.ts
export class Map {
  // 在地图中标记位置
  addMarker(mappable: Mappable) {
    // 创建标记
    const marker = new google.maps.Marker();
    // 为标记绑定点击事件
    marker.addListener("click", () => {
      // 创建弹窗
      const infoWindow = new google.maps.InfoWindow({
        content: "Hello I am marker content",
      });
      // 打开弹框并指定弹框在哪个地图的哪个的标记的位置上弹出
      infoWindow.open(this.googleMap, marker);
    });
  }
}
```

### 4.10 使用接口约束类

```typescript
// src/Map.ts
export interface Mappable {
  markerContent(): string;
}
```

```typescript
// src/Map.ts
const infoWindow = new google.maps.InfoWindow({
  content: mappable.markerContent(),
});
```

```typescript
// src/User.ts
import { Mappable } from "./Map";

export class User implements Mappable {
  markerContent(): string {
    return `用户名: ${this.name}`;
  }
}
```

```typescript
// src/Company.ts
import { Mappable } from "./Map";

export class Company implements Mappable {
  markerContent(): string {
    return `
      <h2>公司名称: ${this.companyName}</h2>
      <h4>公司口号: ${this.catchPhrase}</h4>
    `;
  }
}
```

## 5. 排序案例

### 5.1 概述

> 目标：通过面向对象编程的方式实现对数值数组、字符串进行排序

```typescript
// 数值数组排序
[10, 5, 18, -3] => [-3, 5, 10, 18]
```

```typescript
// 字符串排序
"PoaJB" => "aBJoP"
```

### 5.2 案例准备

(1) 创建案例目录并通过 tsconfig.json 文件配置应用源码目录和应用输出目录

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
    // 配置应用源码目录, 只有应用源码目录中的 ts 文件才会被编译到 outDir 指定的目录中
    "rootDir": "./src",
    // 应用源码的输出目录
    "outDir": "./build",
  },
  // 只有被 include 包含的目录中的 ts 文件才会被编译
  "include": ["./src"]
}
```

(2) 配置应用程序的启动命令

```bash
npm install nodemon concurrently -D
```

```json
"scripts": {
  "start:build": "tsc -w",
  "start:run": "nodemon build/index.js",
  "start": "concurrently npm:start:*"
}
```

(3) 复习冒泡排序法(升序)

```bash
核心思想：将数组中的值进行两两比较，如果第一个值比第二个值大，将两个值交换位置。
比较轮数: 数组长度 - 1
每一轮比较次数: 数组长度 - 1 - i
```

```typescript
原始值 => [10, 5, 18, -3]
第一轮 => [5, 10, -3, 18] => 3次
第二轮 => [5, -3, 10, 18] => 2次
第三轮 => [-3, 5, 10, 18] => 1次
```

```typescript
let arr = [10, 5, 18, -3];
// 外层循环控制比较的轮数
for (let i = 0; i < arr.length - 1; i++) {
  // 内层循环控制比较的次数
  for (let j = 0; j < arr.length - 1 - i; j++) {
    if (arr[j] > arr[j + 1]) {
      // 交换位置
      let leftHand = arr[j];
      arr[j] = arr[j + 1];
      arr[j + 1] = leftHand;
    }
  }
}
```

### 5.3 需求-排序类-数值数组排序

目标：创建排序类，通过实例化排序类创建排序对象并传递要排序的数据，调用排序对象下的 sort 方法对数据进行排序。

```typescript
class Sorter {
  // 接收排序值
  constructor(public collection: number[]) {}
  // 排序方法
  sort(): void {
    const { length } = this.collection;
    for (var i = 0; i < length - 1; i++) {
      for (var j = 0; j < length - 1 - i; j++) {
        if (this.collection[j] > this.collection[j + 1]) {
          var leftHand = this.collection[j];
          this.collection[j] = this.collection[j + 1];
          this.collection[j + 1] = leftHand;
        }
      }
    }
  }
}

const sorter = new Sorter([10, 5, 18, -3]);
sorter.sort();
console.log(sorter.collection);
```

我们已经初步实现了排序，但目前它只能排序数值数组，并不能对字符串进行排序。

### 5.4  需求-排序类-字符串排序

> 目标：通过联合类型和类型守卫对 collection 参数的类型进行区分，针对不同的类型进行不同的排序。

将 collection 参数的类型指定为联合类型，即参数既可以数值数组也可以是字符串，然后通过数据类型判断区分它们进而做不同的排序操作。

提示：字符串比较内部使用的是 Unicode 编码，根据编码的大小决定排序结果。

```typescript
var string = "Xaa";
console.log(string.charCodeAt(0)); // 88
console.log(string.charCodeAt(1)); // 97
```

```typescript
// src/Sorter.ts
export class Sorter {
  constructor(public collection: number[] | string) {}
  sort(): void {
    // 获取数据长度
    const { length } = this.collection;
    // 判断 collection 是否是数组类型
    if (this.collection instanceof Array) {
      // 数组排序操作
      // 控制比较轮数
      for (let i = 0; i < length - 1; i++) {
        // 控制这一轮比较的次数
        for (let j = 0; j < length - 1 - i; j++) {
          // 数据比较
          if (this.collection[j] > this.collection[j + 1]) {
            // 数据交换位置
            let leftHand = this.collection[j];
            this.collection[j] = this.collection[j + 1];
            this.collection[j + 1] = leftHand;
          }
        }
      }
      // 判断 collection 是否是字符串类型
    } else if (typeof this.collection === "string") {
      // 字符串排序
      // 控制比较轮数
      for (let i = 0; i < length - 1; i++) {
        // 控制这一轮比较的次数
        for (let j = 0; j < length - 1 - i; j++) {
          // 数据比较
          if (
            this.collection[j].toLocaleLowerCase() >
            this.collection[j + 1].toLocaleLowerCase()
          ) {
            // 字符串位置交换
            // 字符串中的字符是不能交换位置的, 
            // 所以此处先将字符串分割为数组, 通过数组下标交换元素位置, 再将数组中的元素连接为字符串
            let tempArray: string[] = this.collection.split("");
            let leftHand = tempArray[j];
            tempArray[j] = tempArray[j + 1];
            tempArray[j + 1] = leftHand;
            this.collection = tempArray.join("");
          }
        }
      }
    }
  }
}

// 验证字符串中的字符不能交换位置
// var string = "Xaa";
// 编译器提示: 类型 "String" 中的索引签名仅允许读取。
// string[0] = "Y";
```

```typescript
// src/index.ts
const numberArraySorter = new Sorter([10, 5, 1, 6]);
numberArraySorter.sort();
console.log(numberArraySorter.collection);

const stringSorter = new Sorter("yaxb");
stringSorter.sort();
console.log(stringSorter.collection);
```

虽然以上操作可以让 Sorter 类支持多种数据的排序但并不理想，如果以后要想对其它数据类型排序，我们不得不回到 Sorter 类中，在参数中继续向联合类型中添加类型，然后在 sort 方法中继续编写判断数据类型的方法，如果要排序的数据类型很多，sort 方法内部的代码将会变得越来越臃肿。

### 5.5 优化-基于组合的重构

> 目标：拆分排序逻辑并使用组合的方式对排序功能代码进行重构

组合是指在一个类中使用了另一个类的实例对象。

排序逻辑可以拆分为通用排序逻辑和针对不同数据结构的特定排序逻辑，两种类别的逻辑要被编写在不同的类中。

通用的排序逻辑在 Sorter 类中编写，不同数据结构的特定排序逻辑在特定的类中编写。

对于数值数组可以为它创建 NumbersCollection 排序类，对于字符串可以为它创建 CharacterCollection 排序类。

冒泡排序的通用逻辑是指控制比较轮数的第一层for循环和控制比较次数的第二层for循环，这些逻辑应该放在 Sorter 类中。

冒泡排序的非通用逻辑是指数据两两比较的逻辑，数据交换位置的逻辑，获取数据长度的逻辑，这些逻辑应该放在特定的排序类中。

不论对什么数据结构进行排序，在排序类中都要提供 length、compare、和 swap，所以我们要创建一个接口来约束特定数据结构的排序类。

<img src="./images/53.png" align="left" width="45%"/>

```typescript
// src/Sorter.ts
export interface Sortable {
  // 返回要排序数据的长度
  length: number;
  // 元素比较 返回布尔值
  compare(leftIndex: number, rightIndex: number): boolean;
  // 元素交换位置 没有返回值
  swap(leftIndex: number, rightIndex: number): void;
}
```

```typescript
// src/NumberArraySort.ts
import { Sortable } from "./Sorter";

// 数值数组排序
export default class NumberArraySort implements Sortable {
  // 接收要排序的数据
  constructor(public data: number[]) {}
  // 提供数据长度
  get length() {
    return this.data.length;
  }
  // 提供数据比较的方法
  compare(leftIndex: number, rightIndex: number): boolean {
    return this.data[leftIndex] > this.data[rightIndex];
  }
  // 提供数据交换位置的方法
  swap(leftIndex: number, rightIndex: number): void {
    let lefthand = this.data[leftIndex];
    this.data[leftIndex] = this.data[rightIndex];
    this.data[rightIndex] = lefthand;
  }
}
```

```typescript
// src/CharacterCollection.ts
import { Sortable } from "./Sorter";

// 字符串排序
export default class StringSort implements Sortable {
  // 接收要排序的数据
  constructor(public data: string) {}
  // 提供数据长度
  get length() {
    return this.data.length;
  }
  // 提供数据比较的方法
  compare(leftIndex: number, rightIndex: number): boolean {
    return this.data[leftIndex] > this.data[rightIndex];
  }
  // 提供数据交换位置的方法
  swap(leftIndex: number, rightIndex: number): void {
    let arr = this.data.split("");
    let leftHand = arr[leftIndex];
    arr[leftIndex] = arr[rightIndex];
    arr[rightIndex] = leftHand;
    this.data = arr.join("");
  }
}
```

```typescript
// src/Sorter.ts
export default class Sorter {
  // 接收特定数据类型排序类的实例对象
  constructor(public sortable: Sortable) {}
  // 排序方法
  sort() {
    // 获取数据长度
    const { length } = this.sortable;
    // 开启循环驱动数据排序
    // 控制比较轮数
    for (let i = 0; i < length - 1; i++) {
      // 控制这一轮比较的次数
      for (let j = 0; j < length - 1 - i; j++) {
        // 获取数据比较方法、调用该方法进行数据大小的比较
        if (this.sortable.compare(j, j + 1)) {
          // 获取数据交换位置的方法、调用该方法交换数据位置
          this.sortable.swap(j, j + 1);
        }
      }
    }
  }
}
```

```typescript
// src/index.ts
import StringSort from "./StringSort";
import Sorter from "./Sorter";

const stringSort = new StringSort("yxa");
const stringSorter = new Sorter(stringSort);
stringSorter.sort();
console.log(stringSort.data);
```

```typescript
// src/index.ts
import NumberArraySort from "./NumberArraySort";
import Sorter from "./Sorter";

const numberArraySort = new NumberArraySort([10, 5, 1]);
const numberArraysorter = new Sorter(numberArraySort);
numberArraysorter.sort();
console.log(numberArraySort.data);
```

在以上代码中仍然存在的问题是调用排序的代码过于复杂，需要同时实例化 Sorter 类和特定数据类型的排序类。

该问题可以通过静态方法解决，在特定数据类型的排序类中添加静态方法执行排序的调用逻辑，外部只需要调用该静态方法即可排序并获取到排序结果。

```typescript
// src/NumberArraySort.ts
import Sorter from "./Sorter";

export default class NumberArraySort implements Sortable {
  static sort(data: number[]) {
    const numberArraySort = new NumberArraySort(data);
    new Sorter(numberArraySort).sort();
    return numberArraySort.data;
  }
}
```

```typescript
// src/StringSort.ts
import Sorter from "./Sorter";

// 字符串排序
export default class StringSort implements Sortable {
  static sort(data: string) {
    const stringSort = new StringSort(data);
    new Sorter(stringSort).sort();
    return stringSort.data;
  }
}
```

```typescript
// src/index.ts
import StringSort from "./StringSort";
import NumberArraySort from "./NumberArraySort";

const r1 = StringSort.sort("yax");
console.log(r1);

const r2 = NumberArraySort.sort([20, 15, 30, 0]);
console.log(r2);
```

### 5.6 优化-基于继承的重构

> 目标：拆分排序逻辑并使用继承的方式对排序功能代码进行重构

目前排序功能的逻辑代码我们已经拆分完成，接下来要做的事情就是将拆分之后的逻辑代码通过继承的方式结合在一起执行。

也就是说我们要解决的核心问题是在 sort 方法中如何调用特定数据类型排序类中提供的 length 方法、swap 方法、 compare 方法。

------

将 Sorter 类作为父类，将特定数据类型排序类作为子类，子类继承父类后使用子类的实例对象调用父类的 sort 方法，此时 sort 方法中的 this 就指向了子类实例对象，那么在 sort 方法中就可以通过 this 关键字获取 length、compare、swap 了。

```typescript
// src/NumberArraySort.ts
import Sorter from "./Sorter";

export default class NumberArraySort extends Sorter {
  constructor(public data: number[]) {
    super();
  }
  get length() {
    return this.data.length;
  }
  compare(leftIndex: number, rightIndex: number): boolean {
    return this.data[leftIndex] > this.data[rightIndex];
  }
  swap(leftIndex: number, rightIndex: number): void {
    let lefthand = this.data[leftIndex];
    this.data[leftIndex] = this.data[rightIndex];
    this.data[rightIndex] = lefthand;
  }
}
```

```typescript
// src/StringSort.ts
import Sorter from "./Sorter";

export default class StringSort extends Sorter {
  constructor(public data: string) {
    super();
  }
  get length() {
    return this.data.length;
  }
  compare(leftIndex: number, rightIndex: number): boolean {
    return this.data[leftIndex] > this.data[rightIndex];
  }
  swap(leftIndex: number, rightIndex: number): void {
    let arr = this.data.split("");
    let leftHand = arr[leftIndex];
    arr[leftIndex] = arr[rightIndex];
    arr[rightIndex] = leftHand;
    this.data = arr.join("");
  }
}
```

```typescript
// src/Sorter.ts
export default class Sorter {
  // 排序方法
  sort() {
    const { length } = this;
    for (let i = 0; i < length - 1; i++) {
      // 这一轮比较的次数
      for (let j = 0; j < length - 1 - i; j++) {
        // 判断
        if (this.compare(j, j + 1)) {
          // 交换元素的位置
          this.swap(j, j + 1);
        }
      }
    }
  }
}
```

在 Sorter 类中通过 this 关键字获取 length、compare、swap，这在代码执行的角度看是没有问题的，但是从代码的编译角度看是不行的，因为 TS 编译器会认为 this 指向的就是当前类的实例，而在当前类的实例对象中并没有 length、compare 以及 swap，所以编译器会报错。

------

解决该问题的方式是将 Sorter 类定义为抽象类，因为抽象象类不能被实例化，所以 Sorter 类中的成员不会被当前类的实例对象调用，又因为抽象类只有被继承后才能执行，意为着 Sorter 类中的成员只能被子类实例对象调用，所以 TS 编译器就知道了 sort 方法中的 this 指向的其实是子类实例对象。

将 Sorter 类定义为抽象类以后程序依然报错，因为 TS 编译器并不能确定子类中一定存在 length 方法、compare 方法、swap 方法，如果子类中没有这些方法那么在 sort 方法中直接调用会导致程序报错，所以在 Sorter 类中还要将以上方法列为抽象成员，以此来约束子类必须提供 length 方法、compare 方法、swap 方法，这样 TS 编译器就认为程序是可以安全执行的。

```typescript
// src/Sorter.ts
export default abstract class Sorter {
  // 要求返回要排序数据的长度
  abstract length: number;
  // 元素比较 返回布尔值
  abstract compare(leftIndex: number, rightIndex: number): boolean;
  // 元素交换位置 没有返回值
  abstract swap(leftIndex: number, rightIndex: number): void;

  // 排序方法
  sort() {
    const { length } = this;
    for (let i = 0; i < length - 1; i++) {
      // 这一轮比较的次数
      for (let j = 0; j < length - 1 - i; j++) {
        // 判断
        if (this.compare(j, j + 1)) {
          // 交换元素的位置
          this.swap(j, j + 1);
        }
      }
    }
  }
}
```

```typescript
// src/index.ts
import StringSort from "./StringSort";
import NumberArraySort from "./NumberArraySort";

const numberArraySort = new NumberArraySort([10, 5, 1]);
numberArraySort.sort();
console.log(numberArraySort.data);

const stringSort = new StringSort("yxa");
stringSort.sort();
console.log(stringSort.data);
```

## 6. 统计数据案例

### 6.1 概述

需求：从 football.csv 文件中读取足球比赛信息，读取完成后将它转换为程序支持的数据格式，分析比赛信息，输出分析结果。

格式转换：从 football.csv 文件中读取的比赛信息为字符串类型，不利于分析操作，所以在分析前要将它转换为数组对象格式。

分析数据：比如计算某支球队赢得比赛的次数、计算某支球队总共的进球数等。

输出结果：比如在控制台输出分析结果、在文件中保存分析结果等。

<img src="./images/07.png" align="left" width="62%"/>

H：表示主队胜利、A：表示客队胜利、D：表示平局

### 6.2 案例准备

(1) 创建案例目录、配置应用源码目录、编译输出目录。

```bash
mkdir stats && cd stats
mkdir src build
npm init -y
tsc --init
npm install nodemon concurrently @types/node
```

```json
// tsconfig.json
{
  "compilerOptions": {
    // 配置应用源码目录
    "rootDir": "./src",
    // 配置编译输出目录
    "outDir": "./build",
  }
}
```

(2) 安装案例所需依赖、配置案例启动命令

```bash
npm install nodemon concurrently @types/node -D
```

```json
// package.json
"scripts": {
  "start:build": "tsc -w",
  "start:run": "nodemon build/index.js",
  "start": "concurrently npm:start:*"
}
```

### 6.3 需求-读取足球比赛信息

> 目标：从 football.csv 文件中读取比赛信息并将比赛信息转换为数组对象。

```typescript
// src/index.ts
import fs from "fs";

const matches = fs
  .readFileSync("football.csv", {
    encoding: "utf-8",
  })
  .split("\n")
  .map((row: string): string[] => row.split(","));
```

```typescript
// 解析结果
const matches = [
  ["27/10/2021", "Watford", "Huddersfield", "3", "0", "H", "M Dean"],
  ["28/10/2021", "Burnley", "Chelsea", "0", "4", "A", "C Pawson"],
];
```

### 6.4 需求-计算曼联赢赛的次数

> 目标：计算曼联球队（Man United）赢得比赛的次数，无论曼联是主场球队还是客场球队。

```typescript
// src/index.ts
let manUnitedWins = 0;

for (let match of matches) {
  // 曼联球队为主场赢得比赛的情况
  if (match[1] === "Man United" && match[5] === "H") {
    manUnitedWins++;
    // 曼联球队为客场赢得比赛的情况
  } else if (match[2] === "Man United" && match[5] === "A") {
    manUnitedWins++;
  }
}

console.log(manUnitedWins);
```

### 6.5 优化-提升程序可阅读性

> 目标：通过枚举提升程序的可阅读性

在以上代码中，在判断曼联球队是否赢得比赛时，我们使用了 H 和 A 进行判断，这个代码是我们刚刚编写的，我们完全知道它们代表什么含义。但是当其他人来看这个代码时，他并不清楚 H 和 A 分别代表什么，或许在将来的某一个时间点，我们再回来看这个代码时也不一定清楚的记得它们的含义，所以从代码的是否可阅读角度来看，直接使用 H 和 A 进行判断降低了程序的可阅读性。

解决以上问题的核心就是如何让 H 和 A 在代码中变得语义化，让人一看就知道它们分别代表什么。

------

或许我们可以声明两个变量来存储 H 和 A，让变量的名字变得有含义，通过变量进行判断来提高程序的可阅读性，像下面这样。

```typescript
const homeWin = "H";
const awayWin = "A";

if (match[1] === "Man United" && match[5] === homeWin) {
  manUnitedWins++;
} else if (match[2] === "Man United" && match[5] === awayWin) {
  manUnitedWins++;
}
```

通过以上的更改确实提高了代码的可阅读性但还是存在一个问题，比赛结果除了有 H、A、还有 D。在以上代码中由于目前没有用到 D，所以就没写，当另外一个人来看这个代码时，他并不知道还有 D 这个比赛结果，这也许就为他解决问题设置了障碍。那我们就将 D 写在这里，像下面这样。

```typescript
const homeWin = "H";
const awayWin = "A";
const draw = "D";
```

虽然将 D 写了出来，但由于 draw 变量没有被使用，这时别人可能会认为它是无用代码，很可能随手就把它删掉了，这也是代码中存在的潜在问题。

------

以上问题我们可以使用枚举解决，枚举就是用来提升代码的可阅读性的，我们可以将枚举的名字定义为 MatchResult，通过名字别人就可以看出这是用来声明比赛结果的，比赛结果 H 可以对应枚举属性 homeWin，表示主场球队赢得了比赛，比赛结果 A 可以对应枚举属性 awayWin，表示客场球队赢得了比赛，比赛结果 D 可以对应枚举属性 draw，表示平局。

使用枚举除了可以提升代码的可阅读性它还可以限定值的范围，在程序中判断比赛结果时开发者就不会写出除了以上三种结果以外的结果了，间接的避免了程序可能出现的问题。

```typescript
enum MatchResult {
  homeWin = "H",
  awayWin = "A",
  draw = "D",
}

if (match[1] === "Man United" && match[5] === MatchResult.homeWin) {} 
else if (match[2] === "Man United" && match[5] === MatchResult.awayWin) {}
```

### 6.6 优化-提升程序可扩展性

> 目标：使用类封装核心功能代码以提升程序的可扩展性

在以上代码中仍然存在的问题是获取数据的代码是硬编码的，是读取的本地 CSV 文件，如果将来数据源发生变化比如从读取本地文件更改为读取数据库，此时代码应该怎么写？是要删除读取本地文件的代码，重新编写读取数据库的代码吗？如果将来数据源又更改为读取本地文件那又要怎么办呢？删除再重新编写？

------

解决以上问题的办法是将获取数据的代码封装到类中，读取本地文件的代码封装成一个类，读取数据库的代码封装成一个类，当前使用哪个数据源就实例化哪个类，这样就算切换数据源也仅仅是删除少量的实例化代码，而核心代码一直在。

```typescript
// src/CsvFileReader.ts
import fs from "fs";

export class CsvFileReader {
  // 用于存储文件读取结果
  data: string[][] = [];
  // 接收要读取的文件路径
  constructor(public filename: string) {}
  // 读取文件内容并对内容进行转换
  read(): void {
    this.data = fs
      .readFileSync(this.filename, { encoding: "utf-8" })
      .split("\n")
      .map((row: string): string[] => row.split(","));
  }
}
```

```typescript
// src/index.ts
import { CsvFileReader } from "./CSVFileReader";

const reader = new CsvFileReader("football.csv");
reader.read();

for (let match of reader.data) {}
```

### 6.7  需求-数据类型转换

> 目标：将比赛信息中的所有字段都转换为它原本的类型

在目前的代码中比赛信息中的所有字段都是字符串类型，接下来我们要做的事情就是将比赛信息中的所有字段都转换为它原本的类型。

(1) 声明元组用于标注一条比赛信息的类型

```typescript
// src/CsvFileReader.ts
type MatchData = [Date, string, string, number, number, MatchResult, string];
```

(2) 定义一个用于将日期字符串转换为日期对象的辅助方法

```typescript
// src/utils.ts
// 将日期字符串转换为日期对象
export function dateStringToDate(dateString: string): Date {
  const dateParts = dateString
    .split("/")
    .map((value: string): number => parseInt(value));
  return new Date(dateParts[2], dateParts[1] - 1, dateParts[0]);
}
```

(3) 将用于表示比赛结果的枚举定义在单独的模块文件中用于复用。

因为在分析曼联球队赢得比赛的次数时用到了该枚举，在转换比赛信息类型时也用到了该枚举。

```typescript
// src/matchResult.ts
export enum MatchResult {
  homeWin = "H",
  awayWin = "A",
  draw = "D",
}
```

```typescript
// src/index.ts
import { MatchResult } from "./matchResult";
```

(4) 将比赛信息中的每一个字段转换为它原本的类型。

```typescript
// src/CsvFileReader.ts
import { MatchResult } from "./matchResult";
import { dateStringToDate } from "./utils";

export class CsvFileReader {
  // 用于存储 CSV 文件读取结果
  data: MatchData[] = [];
  // 读取 CSV 文件内容并对内容进行转换
  read(): void {
    this.data = fs
      .readFileSync(this.filename, { encoding: "utf-8" })
      .split("\n")
      .map((row: string): string[] => row.split(","))
      .map((row: string[]): MatchData => {
        return [
          dateStringToDate(row[0]),
          row[1],
          row[2],
          parseInt(row[3]),
          parseInt(row[4]),
          row[5] as MatchResult,
          row[6],
        ];
      });
  }
}
```

### 6.8 优化-基于继承的重构

> 目标：拆分读取文件逻辑和数据类型转换逻辑，再使用继承思想让两者结合起来执行

在以上代码中存在的问题是 CsvFileReader 类不可复用，因为在该类中执行了数据类型转换逻辑，而该逻辑中的数据类型是针对 football.csv 这个文件的，也就是说 CsvFileReader 类目前只能读取 football.csv 文件，如果使用它读取其他的 csv 文件程序将会报错，因为会产生类型不匹配的问题。

------

通过逻辑拆分再重组可以使 CsvFileReader 类可复用，当前案例中的逻辑可以拆分为文件读取逻辑和数据类型转换逻辑。

CsvFileReader 类提供 read 方法用于读取文件内容，创建 MatchReader 类提供 mapRow 方法用于执行数据类型转换逻辑。

在 read 方法中读取到文件内容以后调用 mapRow 方法对文件内容的数据类型进行转换，那么两个类中的方法如何实现调用呢？

使用继承即可实现。CsvFileReader 是父类，MatchReader 是子类，子类继承父类后子类实例对象可以调用父类中提供的 read 方法读取文件，此时read 方法内部的 this 指向了子类实例对象，所以在 read 方法中就可以通过 this 关键字调用子类提供的 mapRow 方法了。

```typescript
// src/MatchReader.ts
import { CsvFileReader } from "./CsvFileReader";
import { MatchResult } from "./matchResult";
import { dateStringToDate } from "./utils";

type MatchData = [Date, string, string, number, number, MatchResult, string];

// 通过泛型参数告诉 CsvFileReader 类当前我们读取的文件内容的类型是什么 
export class MatchReader extends CsvFileReader<MatchData> {
  mapRow(row: string[]): MatchData {
    return [
      dateStringToDate(row[0]),
      row[1],
      row[2],
      parseInt(row[3]),
      parseInt(row[4]),
      row[5] as matchResult,
      row[6],
    ];
  }
}
```

```typescript
// src/CsvFileReader.ts
// 因为在定义 CsvFileReader 类时我们并不知道将来它会读取具有什么数据类型的 Csv 文件
// 所以此处通过泛型接收类型, 因为继承它的类一定知道使用它读取的文件内容的类型是什么
export abstract class CsvFileReader<T> {
  // 用于存储文件读取结果
  data: T[] = [];
  // 接收要读取的文件名称
  constructor(public filename: string) {}
	// 约束子类必须实现 mapRow 方法
  abstract mapRow(row: string[]): T;

  // 读取 CSV 文件内容并对内容进行转换
  read(): void {
    this.data = fs
      .readFileSync(this.filename, { encoding: "utf-8" })
      .split("\n")
      .map((row: string): string[] => row.split(","))
      .map(this.mapRow);
  }
}
// TypeScript 编译器认为当前类方法中的 this 关键字指向的是当前类的实例对象, 而当前类中没有 mapRow 方法, 所以编译阶段会报错
// 解决办法是将 CsvFileReader 类定义为抽象类, 这样 TypeScript 编译器就知道了方法内部的 this 其实指向的是子类
// 但是 TypeScript 编译器不能确定子类中一定有 mapRow 方法, 所以还是会报错
// 解决办法是在抽象类中定义抽象成员 mapRow, 以此来约束子类必须实现 mapRow, 这样 TypeScript 编译器才认为代码是安全的
```

```typescript
// src/index.ts
// 注意: 由于在 MatchReader 类没有定义构造函数, 所以传递给 MatchReader 类的参数可以直接被父类接收
const reader = new MatchReader("football.csv");
reader.read();
// (property) 0: Date
// reader.data[0][0]
```

### 6.9 优化-基于组合的重构

> 目标：通过组合思想将读取文件的逻辑、数据类型转换的逻辑结合在一起执行

组合是指在一个类中使用另外一个类的实例对象，通过这种方式实现两个类中的逻辑结合在一起执行。

在当前案例中我们可以在 MatchReader 类中使用 CsvFileReader 类的实例对象。在 MatchReader 类中添加一个 load 方法，在 load 方法中调用 CsvFileReader 实例对象下的 read 方法读取文件，再调用该实例对象下的 data 属性获取读取结果，接着在 load 方法中对文件读取的内容进行数据类型的转换，最后在 MatchReader 类中再添加一个 matches 属性用于保存数据类型转换之后的数据。

```typescript
import { MatchResult } from "./matchResult";
import { dateStringToDate } from "./utils";
import { DataReader } from "./dataReader";

type MatchData = [Date, string, string, number, number, MatchResult, string];

export class MatchReader {
  matches: MatchData[] = [];
  // reader 参数被 DataReader 接口约束
  // 这样做的好处是无论是什么数据源对象, 只要你有 read 方法和 data 属性我就可以使用它读取数据进行数据类型转换
  constructor(public reader: DataReader) {}
  load() {
    // 使用数据源对象获取数据
    this.reader.read();
    // 执行数据类型转换逻辑
    this.matches = this.reader.data.map((row: string[]): MatchData => {
      return [
        dateStringToDate(row[0]),
        row[1],
        row[2],
        parseInt(row[3]),
        parseInt(row[4]),
        row[5] as MatchResult,
        row[6],
      ];
    });
  }
}
```

```typescript
// src/dataReader.ts
// 数据源接口规范
export interface DataReader {
  read(): void;
  data: string[][];
}
```

```typescript
import fs from "fs";
import { DataReader } from "./dataReader";

export class CsvFileReader implements DataReader {
  // 用于存储 CSV 文件读取结果
  data: string[][] = [];

  // 接收 CSV 文件名称
  constructor(public filename: string) {}

  // 读取 CSV 文件内容
  read(): void {
    this.data = fs
      .readFileSync(this.filename, { encoding: "utf-8" })
      .split("\n")
      .map((row: string): string[] => row.split(","));
  }
}
```

```typescript
const csvFileReader = new CsvFileReader("football.csv");
const matchReader = new MatchReader(csvFileReader);
matchReader.load();

for (let match of matchReader.matches) {}
```

在使用组合思想完成代码重构以后，我们发现最终的读取文件的调用代码变得复杂，该问题我们可以使用静态方法解决。

在 MatchReader 类中添加静态方法 fromCSVFile，将读取 CSV 文件的调用代码封装到该方法中，那么外部就只需要调用该静态方法即可。

```typescript
// src/MatchReader.ts
export class MatchReader {
  static fromCSVFile(filename: string): MatchReader {
    return new MatchReader(new CsvFileReader(filename));
  }
}
```

```typescript
// src/index.ts
const matchReader = MatchReader.fromCSVFile("football.csv");
matchReader.load();
```

### 6.10 需求-分析与输出概述

在统计案例中我们一般都会对数据进行多种类型的分析，对于分析结果一般也都会有多种存储方式，而我们要达到的目的是不论进行什么样的分析行为，不论使用什么样式的存储方式，两者都能够自由的结合在一起使用。

比如计算某支球队赢得比赛的次数并将计算结果输出到控制台中。

比如计算某支球队的平均进球数并使用文件存储计算结果。

------

为不同的分析行为和不同的输出行为分别创建不同的类，再创建一个用于组合分析行为和输出行为的类，在组合类中统一调用分析行为和输出行为。

为使分析类和输出类进行自由组合，我们要分别创建约束分析类、约束输出类的接口规范，这样在组合类中就可以统一调用分析行为和输出行为了。

为什么不同品牌的电脑硬件可以自由组合呢？这就是因为不同的硬件厂商在生产硬件时都遵循了统一的接口规范。

<img src="./images/54.png" align="left" width="35%"/>

```typescript
const summary = new Summary(
  new WinsAnalysis("Man United"),
  new ConsoleReport()
);
summary.buildAndPrintReport(matchReader.matches);
```

```typescript
const summary = new Summary(
  new AverageGoalsAnalysis("Man United"),
  new HtmlReport()
);
summary.buildAndPrintReport(matchReader.matches);
```

### 6.11 需求-创建组合类

> 目标：创建组合类并接收分析对象和输出对象、使用接口统一约束分析对象和输出对象

```typescript
// src/Summary.ts
import { MatchData } from "./MatchData";

// 用于约束分析行为的接口
export interface Analyzer {
  // 用于实现分析过程的方法, 接收比赛结果列表作为参数, 返回分析结果
  run(matches: MatchData[]): string;
}

// 用于约束输出行为的接口
export interface OutputTarget {
  // 用于实现打印分析结果的方法, 接收分析结果作为参数, 没有返回值
  print(report: string): void;
}

// 用于组合分析行为和输出行为的类
export class Summary {
  // 接收满足分析行为接口的类实例
 	// 接收满足输出行为接口的类实例
  // 只有满足接口要求, 在 Summary 类中才能安全统一的的调用相关方法实现功能
  constructor(public analyzer: Analyzer, public outputTarget: OutputTarget) {}
  // 分析并打印分析结果
  buildAndPrintReport(matches: MatchData[]) {
    // 调用分析类实例中的 run 方法进行分析
    const output = this.analyzer.run(matches);
    // 打印分析结果
    this.outputTarget.print(output);
  }
}
```

### 6.12 需求-创建分析类

> 目标：创建用于计算某支球队赢得比赛次数的类

```typescript
// src/Analyzers/WinsAnalysis.ts
import { MatchData } from "../MatchData";
import { MatchResult } from "../matchResult";
import { Analyzer } from "../Summary";

export class WinsAnalysis implements Analyzer {
  // 接收球队名称
  constructor(public team: string) {}

  run(matches: MatchData[]): string {
    // 记录赢得比赛的次数
    let wins = 0;
    // 开始分析
    for (let match of matches) {
      // 记录球队为主场赢得比赛的情况
      if (match[1] === this.team && match[5] === MatchResult.homeWin) {
        wins++;
        // 记录球队为客场赢得比赛的情况
      } else if (match[2] === this.team && match[5] === MatchResult.awayWin) {
        wins++;
      }
    }
    return `${this.team} 球队赢得了 ${wins} 场球赛`;
  }
}
```

> 目标：创建用于计算某支球队的平均进球数的类

```typescript
// src/Analyzers/AverageGoalsAnalysis.ts
import { MatchData } from "../MatchReader";
import { Analyzer } from "./../Summary";

export class AverageGoalsAnalysis implements Analyzer {
  // 接收球队名称
  constructor(public team: string) {}
  // 执行分析行为
  run(matches: MatchData[]): string {
    // 记录总共的进球数量
    let numberOfGoals = 0;
    // 记录总共的比赛次数
    let numberOfMatches = 0;
    for (let match of matches) {
      if (match[1] === this.team) {
        numberOfGoals += match[3];
        numberOfMatches++;
      } else if (match[2] === this.team) {
        numberOfGoals += match[4];
        numberOfMatches++;
      }
    }
    return `${this.team} 球队进行了 ${numberOfMatches} 次比赛, 平均进球数为${
      numberOfGoals / numberOfMatches
    }`;
  }
}
```

### 6.13 需求-创建输出类

> 目标：创建用于将分析结果输出到控制台的类

```typescript
// src/ReportTargets/ConsoleReport.ts
import { OutputTarget } from "../Summary";

export class ConsoleReport implements OutputTarget {
  print(report: string): void {
    console.log(report);
  }
}
```

> 目标：创建用于将分析结果输出到文件的类

```typescript
import { OutputTarget } from "../Summary";
import fs from "fs";

export class HtmlReport implements OutputTarget {
  print(report: string): void {
    const html = `<h1>分析结果</h1><div>${report}</div>`;
    fs.writeFileSync("report.html", html);
  }
}
```

### 6.14 优化-简化调用逻辑

在创建 Summary 类实例时需要同时创建分析行为实例和输出行为实例，该逻辑可以被静态方法进行简化。

```typescript
// src/Summary.ts
export class Summary {
  // 用于分析球队赢得比赛的信息并使用HTML的方式输出结果
  static winsAnalysisWithHtmlReport(team: string): Summary {
    return new Summary(new WinsAnalysis(team), new HtmlReport());
  }
}
```

```typescript
// src/index.ts
const summary = Summary.winsAnalysisWithHtmlReport("Man United");
summary.buildAndPrintReport(matchReader.matches);
```

## 8. 数据驱动视图更新案例

> 目标：使用 TypeScript 创建一个小型的前端框架，在框架内部实现数据驱动视图更新的功能。

<img src="./images/08.png" align="left" width="50%"/>

### 8.1 需求-应用初始化

> 目标：创建应用文件夹结构、通过 parcel 启动应用

```bash
# 创建应用目录并进入
mkdir web && cd web
# 创建应用工程文件
npm init -y
# 安装 parcel 前端构建工具
npm install parcel@2.4.1 -D
# 创建源码目录并进入
mkdir src && cd src
# 创建应用逻辑入口文件
touch index.ts
```

```typescript
// 应用逻辑入口文件测试代码
console.log("Hello");
```

```bash
# 回到应用根目录创建应用视图入口文件
cd ../ && touch index.html
```

```html
<!-- 在应用视图入口文件中引入应用逻辑入口文件 -->
<script type="module" src="./src/index.ts"></script>
```

```bash
parcel index.html
```

### 8.2 需求-创建用户模型类

> 目标：创建 User 模型类编写和用户相关的操作，创建 UserProps 接口约束 user 对象，在 User 模型类中创建 data 私有属性存储用户信息。

用户信息被设计为私有的原因是我们不希望开发者直接操作用户信息，因为用户信息被修改时程序需要监控到，以实现视图更新逻辑。

```typescript
// src/models/User.ts
interface UserProps {
  name: string;
  age: number;
}

export class User {
  // 接收创建用户时传递的用户数据
  constructor(private data: UserProps) {}
}
```

```typescript
// src/index.ts
import { User } from "./models/User";

const user = new User({ name: "张三", age: 20 });
// 属性"data"为私有属性, 只能在类"User"中访问。ts(2341)
// console.log(user.data);
```

### 8.3 需求-提供操作状态的途径

> 目标：在 User 类中创建 get 方法用于获取用户信息，创建 set 方法用于更新用户信息。

在 User 类中创建 get 方法用于获取用户信息。

```typescript
export class User {
  // 通过属性名称从 data 对象中获取用户信息
  get(propName: string): string | number {
    return this.data[propName];
  }
}
```

```typescript
// src/index.ts
console.log(user.get("name")); // 张三
console.log(user.get("age"));  // 20
```

在更新用户信息时要注意，由于用户信息中只有 name 和 age 两个属性，所以更新信息时也只能更新这两个属性，那么在调用 set 方法时如何限制传递的参数中只能包含 name 和 age 属性呢?

```typescript
// src/models/User.ts
export class User {
  // 通过 UserProps 接口规范可以要求开发者在修改信息时, 只能修改规范中定义的属性, 而且值也必须要符合要求的类型
  set(update: UserProps) {
    this.data = {...this.data, ...update};
  }
}
```

```typescript
// src/index.ts
const user = new User({ name: "张三", age: 20 });
user.set({ name: "李四", age: 30 });
```

虽然在以上代码中实现了用户信息更新，但是在更新时必须传递用户的所有属性，因为 update 参数的类型是 UserProps，如果只更改其中的某一个属性，将不满足 UserProps 接口规范。

```typescript
// 类型"{ name: string; }"的参数不能赋给类型"UserProps"的参数。
// 类型"{ name: string; }"中缺少属性"age", 但类型"UserProps"中需要该属性. ts(2345)
user.set({ name: "李四" });
```

可以将 UserProps 接口中的属性更改为可选的，这样在更新用户信息时就可以只更新某些属性了。

```typescript
interface UserProps {
  name?: string;
  age?: number;
}
```

这样做还有一个好处，就是在创建用户时可以创建一个没有任何属性的空用户对象。比如在注册用户时我们要向用户提供表单让用户填写姓名和年龄，在这个场景下就可以先创建一个空的用户对象，然后在用户提交表单时再通过表单数据设置用户信息。

### 8.4 需求-小型事件系统

> 目标：创建小型事件系统，包含事件监听器和事件触发器。

创建事件系统的目的是当用户信息被修改时可以对视图进行更新操作。

第一步：创建事件监听器用于绑定事件。

事件监听器可以有不同的类型，每个类型下可以有多个事件处理程序。

<img src="./images/09.png" align="left" width="40%"/>

```javascript
// 用于存储事件及事件对应的事件处理函数
{
  "click": [ () => console.log('clicked #1'), () => console.log('clicked #2') ],
  "change": [ () => console.log('changed') ],
}
```

```typescript
// src/models/User.ts
// 定义类型, 回调函数的类型
type Callback = () => void;

user.on('click', () => {})

export class User {
  // 用于保存事件名称和对应的事件处理程序
  events: { [key: string]: Callback[] } = {};
	// 事件侦听器, 用于绑定事件
  on(eventName: string, callback: Callback): void {
    // 通过事件名称获取已有事件监听程序
    // 如果不是第一次绑定事件, 得到的是 Callback 数组, 直接使用
    // 如果是第一次绑定, 得到的是 undefined, 利用逻辑短路思想, 放弃 undefined, 返回空数组
    const handlers = this.events[eventName] || [];
    // 将此次要绑定的事件处理程序添加到数组中
    handlers.push(callback);
    // 将新的事件处理函数数组重新赋值给对应的事件
    this.events[eventName] = handlers;
  }
}
```

```typescript
// src/index.ts
user.on("click", () => {});
user.on("click", () => {});
user.on("change", () => {});
console.log(user);
```

第二步：创建事件触发器用于触发事件。

```typescript
// src/models/User.ts
export class User {
	// 事件触发器用于触发事件
  trigger(eventName: string): void {
    // 获取事件名称对应的事件处理程序
    const handlers = this.events[eventName];
    // 判断是否触发了没有绑定的事件, 如果是阻止程序向下运行, 防止报错
    if (!handlers) return;
    // 依次调用该事件对应的事件处理程序
    handlers.forEach((callback) => callback());
  }
}
```

```typescript
// src/index.ts
user.on("click", () => console.log("click #1"));
user.on("click", () => console.log("click #2"));
user.on("change", () => console.log("change #1"));

user.trigger("click");
user.trigger("change");
```

### 8.5 需求-用户信息同步

> 目标：在 User 模型类中创建 save 方法与 fetch 方法，用于与服务端同步用户信息。

第一步：使用 JSON Server 作为服务端，与客户端进行数据交互。

<img src="./images/10.png" align="left" width="45%"/>

```bash
npm install json-server concurrently@7.4.0 -D
npm install axios
```

```json
{
  "users": [
    {
      "id": 1,
      "name": "张三",
      "age": 20
    },
  ],
  "posts": [],
}
```

```bash
# json-server 提供的是 restful 风格的 api 接口规范
# 创建用户 
POST http://localhost:3005/users
# 查询 id 为 1 的用户信息
GET http://localhost:3005/users/1
# 删除 id 为 1 的用户
DELETE http://localhost:3005/users/1
# 修改 id 为 1 的用户
PUT http://localhost:3005/users/1
PATCH http://localhost:3005/users/1
# 获取所有的用户信息
GET http://localhost:3005/users
```

```json
"scripts": {
  "start:dev": "parcel index.html",
  "start:json-server": "json-server -w ./db.json --port 3005",
  "start": "concurrently npm:start:*"
},
```

② 创建 fetch 方法用于向服务端发送请求获取数据，通过 id 获取数据。

```typescript
// src/models/User.ts
import axios, { AxiosResponse } from "axios";

interface UserProps {
  id?: number;
}

// new User({ id: 1 })

export class User {
  // 根据用户 id 获取用户信息
  // 等待 -> 成功
  // 等待 -> 失败
  fetch() {
     return axios
      .get(`http://localhost:3000/users/${this.get("id")}`)
      .then((response: AxiosResponse) => this.set(response.data));
  }
}
```

```typescript
// src/index.ts
const user = new User({ id: 1 });

user.fetch().then(() => {
  console.log(user.get("name"));
  console.log(user.get("age"));
});
```

③ 创建 save 方法，用于创建用户和修改用户。

```typescript
// src/models/User.ts
export class User {
  save() {
    const id = this.get("id");
    if (typeof id != "undefined") {
      // 修改
      return axios.patch(`http://localhost:3000/users/${id}`, this.data);
    } else {
      // 添加
      return axios.post("http://localhost:3000/users", this.data);
    }
  }
}
```

```typescript
// src/index.ts
// 创建用户
const user = new User({ name: "李四", age: 30 });
user.save();
```

```typescript
// 修改用户
const user = new User({ id: 1, name: "王五" });
user.save();
```

### 8.6 优化-提取事件系统

> 目标：将和事件系统相关的代码从 User 类中拆分出去，通过组合的方式重组代码。

目前代码中存在的问题是我们将所有功能都写在了 User 模型类中，包括一些可以在不同模型类中复用的代码。

比如获取和更新数据的方法、事件系统、与服务器端同步状态的代码在其他的模型类中都会使用，它们是可以被复用的代码。

我们现在要做的事情就是将这些可以复用的代码抽取到不同的类中，然后再通过组合的方式将它们进行重组。

① 创建 Eventing 类用于放置所有和事件系统相关的代码。

```typescript
// src/models/Eventing.ts
type Callback = () => void;

export class Eventing {
  // 用于保存事件名称和对应的事件处理程序
  events: { [key: string]: Callback[] } = {};
  // 事件侦听器, 用于绑定事件
  on(eventName: string, callback: Callback): void {
    // 通过事件名称获取已有事件监听程序
    // 如果不是第一次绑定事件, 得到的是 Callback 数组, 直接使用
    // 如果是第一次绑定, 得到的是 undefined, 利用逻辑短路思想, 放弃 undefined, 返回空数组
    const handlers = this.events[eventName] || [];
    // 将此次要绑定的事件处理程序添加到数组中
    handlers.push(callback);
    // 将拥有新结果的数组重新赋值给对应的时间
    this.events[eventName] = handlers;
  }
  // 事件触发器, 用于触发事件
  trigger(eventName: string): void {
    // 获取事件名称对应的事件处理程序
    const handlers = this.events[eventName];
    // 1) 触发了一个没有绑定的事件
    // 2) 触发的事件曾经绑定过, 但目前已经解绑了
    // 判断是否是以上两种情况, 如果是阻止程序向下运行, 防止报错
    if (!handlers || handlers.length === 0) return;
    // 依次调用该事件对应的事件处理程序
    handlers.forEach((callback) => callback());
  }
}
```

② 在 User 类中通过组合的方式集成事件系统。

```typescript
// src/models/User.ts
import { Eventing } from "./Eventing";

export class User {
  // 在当前模型类中添加事件系统
  public events: Eventing = new Eventing();
}
```

```typescript
// src/index.ts
const user = new User({ name: "张三" });
user.events.on("click", () => console.log("clicked"));
user.events.trigger("click");
```

### 8.7 优化-提取状态同步功能

① 创建 Sync 类用于放置所有和状态同步相关的代码。

```typescript
// src/models/ApiSync.ts
import axios from "axios";
import { HasId } from "../types/HasId";

export class ApiSync<T extends HasId> {
  // 由于 Sync 类要在不同的模型类中复用, 而不同的模型类操作的数据是不一样的
  // 所以在不同模型类中使用时需要传递获取数据的 url 地址
  constructor(public rootUrl: string) {}
	// 由于 Sync 类中没有 get 方法, 在 fetch 方法内部就不能通过 get 方法获取 id 了
  // 所以 fetch 方法现在通过参数接收 id
  fetch(id: number): Promise<T | undefined> {
    return axios.get(`${this.rootUrl}/${id}`).then((response) => response.data);
  }
  save(data: T): Promise<T> {
    const { id } = data;
    if (typeof id != "undefined") {
      // 修改
      return axios.patch(`${this.rootUrl}/${id}`, data).then((response) => response.data);
    } else {
      // 添加
      return axios.post(this.rootUrl, data).then((response) => response.data);
    }
  }
}
```

```typescript
// src/types/HasId.ts
export interface HasId {
  id?: number;
}
```

② 在 User 类中通过组合的方式集成状态同步功能。

```typescript
// src/models/User.ts
import { ApiSync } from "./Sync";

// 基准请求地址
const rootUrl = "http://localhost:3000/users";

export class User {
  // 通过组合的方式集成同步数据功能
  apiSync: ApiSync<UserProps> = new ApiSync<UserProps>(rootUrl);
}
```

```typescript
// src/index.ts
const user = new User({});
user.apiSync.fetch(1).then((response) => {
  user.set(response.data);
});
```

### 8.8 优化-提取状态操作成员

> 目标：提取 User 类中的 data 属性、get、set 方法，使用这部分代码能够在其他模型类中被复用。

第一步：创建 Attributes 类，用于存储 data 属性、get、set 方法。

```typescript
// src/models/Attributes.ts
// 由于 Attributes 类将要在不同的模型类之中复用
// 在定义 Attributes 类时我们并不知道它将要存储什么类型的数据 
// 所以要存储的数据的类型通过泛型进行传递
export class Attributes<T> {
  constructor(private data: T) {}
  get(propName: string): string | number {
    return this.data[propName];
  }
  set(update: T) {
    this.data = { ...this.data, ...update };
  }
}
```

第二步：优化 get 方法参数的类型及返回值的类型。

在以上代码中存在的问题是 get 方法的参数的取值范围并没有被限制，就是说 data 对象中存在什么属性 get 方法中才允许被传递什么参数。

比如 user 对象只有三个属性，id、name、age，那么当 user 对象调用 get 方法时它就只允许传递这三个参数。

按照目前学习的知识点我们很容易将代码写成下面这样。

```typescript
export class Attributes<T> {
  constructor(private data: T) {}
  get(key: keyof T) {
    return this.data[key];
  }
}
```

以上代码目前可以实现我们的需求，user 对象在调用 get 方法以后确实只能传递 id、name、age 属性了。

<img src="./images/11.png" align="left" width="45%"/>

接下来的问题是 get 方法的返回值要如何限制，get 方法的作用是根据属性名称获取属性值，那么属性值的类型就是 get 方法的返回值的类型。

通过 类型[属性名称] 可以获取到属性值的类型，所以 get 方法的返回值的类型我们很可能写成下面这样。

```typescript
export class Attributes<T> {
  constructor(private data: T) {}
  get(key: keyof T): T[keyof T] {
    return this.data[key];
  }
}
```

以上代码其实并不能完全满足我们的要求，因为 keyof T 获取的是类型的属性字面量联合类型，T[keyof T] 翻译过来就是 T["name" | "age"]，它得到的是属性值的类型的属性字面量联合类型，即 string | number，虽然得到的是正确的类型，但是它是联合类型，在使用之前还需要先确定他的类型。

我们希望 get 方法的返回值是一个确定的类型，传递 name 属性时 get 方法的返回值就是字符串，传递 age 属性时 get 方法的返回值就是数值。

<img src="./images/12.png" align="left" width="50%"/>

解决问题的办法是让参数是固定的类型，这样返回值也能是固定的类型，比如参数的类型是字面量 name，那么返回值的类型就能确定为 string。

所以最终我们可以通过泛型解决，在调用 get 方法时将属性的名称作为类型传递到 get 方法中，再通过泛型约束限制能够传递的参数的范围，既然类型有了属性名称有了，那么返回值的类型就可以通过 类型[属性名称] 的方式确定了。

```typescript
export class Attributes<T> {
  constructor(private data: T) {}
  get<K extends keyof T>(key: K): T[K] {
    return this.data[key];
  }
}
```

```typescript
const user = new Attributes<UserProps>({ name: "张三", age: 20 });
// const age = user.get<"age">("age");
// 由于泛型参数可以省略, 此时实际参数的类型就是泛型参数的类型, 所以以上代码可以简写为
// const age: number
const age = user.get("age");
```

```typescript
// src/models/Attributes.ts
// 以下为测试代码
const user = new Attributes<UserProps>({ id: 1, name: "张三", age: 20 });
// const name: string
const name = user.get("name");
// const age: number
const age = user.get("age");
```

第四步：在 User 模型类中通过组合的方式重组 data、get、set

```typescript
// src/models/User.ts
import { Attributes } from "./Attributes";

export class User {
  // 为当前模型类添加模型
  public attributes: Attributes<UserProps>;

  constructor(user: UserProps) {
    this.attributes = new Attributes<UserProps>(user);
  }
}
```

```typescript
const user = new User({ name: "张三", age: 20 });
user.attributes.get("name");
```

### 8.9 优化-减少对象调用层级

> 目标：通过 getter 属性访问器实现减少对象调用层级。

虽然当前解决了内部代码复用问题但是新的问题也产生了，就是在调用模型类内部的方法时代码变得冗长。

```typescript
const user = new User({ id: 1, name: "王五" });

// 上一轮优化后
user.attributes.get("name");
user.sync.fetch(1);
user.events.trigger("click");

// 上一轮优化前
user.get("name");
user.fetch();
user.trigger("click");
```

先来看一段我们能够想到的最直接的缩减调用属性层级的办法。

```typescript
// src/models/User.ts
export class User {
  on(eventName: string, callback: Callback) {
    return this.events.on(eventName, callback);
  }
}
```

```typescript
user.on('click', () => {});
```

以上代码中虽然可以缩减调用属性的层级但是代码过于啰嗦，我们可以利用类的 getter 属性访问器优化以上代码。

```typescript
// src/models/User.ts
export class User {
  get on() {
    return this.events.on;
  }
  get trigger() {
    return this.events.trigger;
  }
  get get() {
    return this.attributes.get;
  }
}
```

```typescript
user.on("click", () => console.log("clicked"));
```

但是在以上代码中还隐藏着一个错误，尝试运行以下代码。

```typescript
user.get("name");
```

<img src="./images/13.png" align="left" width="75%"/>

产生以上错误的原因在于代码运行时 Attributes 类中 get 方法中的 this 指向出现了问题，它本应该指向 Attributes 类实例，现在指向了 User 类实例。User 类中没有 data 属性，访问 data 属性时返回 undefined，再通过 undefined 访问 name，自然就报错了。

解决以上问题的办法是将 Attributes 类中的 get 方法更改为箭头函数，因为箭头函数不绑定 this，所以外部无论怎样调用都不会更改 this 指向。

```typescript
// src/models/Attributes.ts
export class Attributes<T> {
  get = <K extends keyof T>(key: K): T[K] => {
    return this.data[key];
  };
}
```

```typescript
// src/models/Eventing.ts
export class Eventing {
  // 事件侦听器, 用于绑定事件
  on = (eventName: string, callback: Callback): void => { };
  // 事件触发器, 用于触发事件
  trigger = (eventName: string): void => {};
}
```

```typescript
// src/index.ts
// 以下为测试代码
const user = new User({ id: 1, name: "王五" });

console.log(user.get("name"));
user.on("click", () => console.log("clicked"));
user.trigger("click");
```

### 8.10 需求-更新数据触发事件

> 目标：在 User 模型类中添加 set 方法，目的一是减少对象属性调用层级，目的二是在数据更新之后要触发 change 事件。

触发 change 事件的原因是将来当数据发生更改时应用程序的其他部分要随之更新。

```typescript
// src/models/User.ts
export class User {
  set(update: UserProps) {
    this.attributes.set(update);
    this.events.trigger("change");
  }
}
```

```typescript
// src/index.ts
// 以下为测试代码
const user = new User({ id: 1, name: "王五" });
user.on("change", () => console.log("changed"));
user.set({ name: "李四" });
```

### 8.11  需求-获取数据保存数据

> 目标：在 User 类中添加 fetch 方法用于根据 id 获取数据、数据获取完成后更新本地数据。

```typescript
export class User {
  fetch(): void {
    // 获取数据id
    const id = this.get("id");
    // 如果没有id或者类型错误
    if (typeof id !== "number") {
      // 报错: 输出错误信息
      throw new Error("没有id不能获取数据");
    }
    // 根据id发送请求获取数据
    this.apiSync.fetch(id).then((data) => {
      if (data) this.set(data);
    });
  }
}
```

```typescript
// src/index.ts
// 创建用户实例
const user = new User({ id: 1 });
// 监听用户数据发生变化
user.on("change", () => console.log(user));
// 根据 id 获取用户数据
user.fetch();
```

### 8.12 需求-同步状态触发事件

> 目标：在 User 类中添加 save 方法用于添加用户和修改用户，操作成功后触发 save 事件、失败后触发 error 事件。

由于最终在 User 类中我们还是要调用 ApiSync 类下的 save 方法，而在调用 save 方法时需要传递 data 对象，获取 data 对象需要使用 Attributes 类，但是在 Attributes 类中并没有提供获取 data 对象本身的方法，所以我们要先在 Attributes 类中添加 getData 方法用于获取 data 对象供其他的数据模型类使用。

```typescript
// 回顾 save 方法
// src/models/ApiSync.ts
export class ApiSync<T extends HasId> {
  save(data: T) {
    // 获取当前用户对象中的 id
    const { id } = data;
    // 判断 id 是否存在
    if (typeof id === "number") {
      // 修改
      return axios.patch(`${this.rootUrl}/${id}`, data);
    } else {
      // 添加
      return axios.post(this.rootUrl, data);
    }
  }
}
```

```typescript
// src/models/Attributes.ts
export class Attributes<T> {
  getData(): T {
    return this.data;
  }
}
```

在 User 类中添加 save 方法，操作执行成功后触发 save 事件、操作执行失败后触发 error 事件。

```typescript
// src/models/User.ts
export class User {
  save(): void {
    this.apiSync
      .save(this.attributes.getData())
      .then(() => this.trigger("save"))
      .catch(() => this.trigger("error"));
  }
}
```

```typescript
// src/index.ts
// 以下为测试代码
const user = new User({ id: 1, name: "赵六" });
user.on("save", () => console.log(user));
user.save();
```

### 8.13 优化-提取模型类公用逻辑

目前在表示用户的 User 模型类中，所有的属性和方法都可以被看做是数据模型类之间的可以复用的代码，比如表示文章的 Post 模型类，表示订单的 Order 模型类，都会有 get、set、fetch、save、on、trigger 等方法，所以接下来我们要做的事情就是创建一个 Model 类，用于存放数据模型类中可以复用的属性和方法，其他数据模型类通过继承的方式获取他们。

<img src="./images/46.png" align="left" width="35%"/>

第一步：创建 Model 类，将目前 User 类中所有的属性和方法原封不动的拷贝到 Model 类中。

```typescript
// src/models/Model.ts
import { ApiSync } from "./ApiSync";
import { Attributes } from "./Attributes";
import { Eventing } from "./Eventing";

export class Model {
  // 通过组合的方法重组事件功能
  events: Eventing = new Eventing();
  // 通过组合的方式重组同步数据功能
  apiSync: ApiSync<UserProps> = new ApiSync<UserProps>(rootUrl);
  // 通过组合的方式重组操作数据的功能
  attributes: Attributes<UserProps>;
  // 通过构造函数的方式接收用户信息
  constructor(user: UserProps) {
    // 接收到的用户信息通过 Attributes 类的实例进行存储
    this.attributes = new Attributes<UserProps>(user);
  }
  get on() {
    return this.events.on;
  }
  get trigger() {
    return this.events.trigger;
  }
  get get() {
    return this.attributes.get;
  }
  // 修改数据
  // 触发数据更改的 change 事件
  set(update: UserProps) {
    this.attributes.set(update);
    this.events.trigger("change");
  }
  // 根据id获取数据
  // 将数据保存到实例对象中
  fetch() {
    // 获取数据中的 id 属性值
    const id = this.get("id");
    if (typeof id !== "number") {
      throw new Error("id的类型不合法");
    }
    this.apiSync.fetch(id).then((data) => {
      if (data) this.set(data);
    });
  }
  // 将本地创建的新的数据保存在服务器端
  // 修改服务器端id值为什么的数据
  // 操作执行成功后 触发 save 事件 失败后 触发 error 事件
  save() {
    this.apiSync
      .save(this.attributes.getData())
      .then(() => this.trigger("save"))
      .catch(() => this.trigger("error"));
  }
}
```

第二步：修复 Model 类目前存在的报错问题。

代码拷贝完成以后，将 Model 类定义为泛型类，接收泛型 T，用于约束数据的类型，使用类型 T 替换代码中的 UserProps。

由于 ApiSync 类也是一个泛型类，它要求 T 对应的实际参数中要存在一个可选的 id 属性，所以 Model 类中泛型参数 T 也要有一样的要求。

由于在实例化 ApiSync 类时需要传递传递 rootUrl 参数，所以该参数先由 Model 类接收，接收完成后再传递给 ApiSync 类。

```typescript
import { HasId } from "../types/HasId";
import { ApiSync } from "./ApiSync";
import { Attributes } from "./Attributes";
import { Eventing } from "./Eventing";

export class Model<T extends HasId> {
  // 通过组合的方法重组事件功能
  events: Eventing = new Eventing();
  // 通过组合的方式重组同步数据功能
  sync: ApiSync<T>;
  // 通过组合的方式重组操作数据的功能
  attributes: Attributes<T>;
  // 通过构造函数的方式接收用户信息
  constructor(user: T, rootUrl: string) {
    // 接收到的用户信息通过 Attributes 类的实例进行存储
    this.attributes = new Attributes<T>(user);
    // 通过组合的方式重组同步数据功能
    this.sync = new ApiSync<T>(rootUrl);
  }
  get on() {
    return this.events.on;
  }
  get trigger() {
    return this.events.trigger;
  }
  get get() {
    return this.attributes.get;
  }
  // 修改数据
  // 触发数据更改的 change 事件
  set(update: T) {
    this.attributes.set(update);
    this.events.trigger("change");
  }
  // 根据id获取数据
  // 将数据保存到实例对象中
  fetch() {
    // 获取数据中的 id 属性值
    const id = this.get("id");
    if (typeof id !== "number") {
      throw new Error("id的类型不合法");
    }
    this.sync.fetch(id).then((data) => {
      if (data) this.set(data);
    });
  }
  // 将本地创建的新的数据保存在服务器端
  // 修改服务器端id值为什么的数据
  // 操作执行成功后 触发 save 事件 失败后 触发 error 事件
  save() {
    this.sync
      .save(this.attributes.getData())
      .then(() => this.trigger("save"))
      .catch(() => this.trigger("error"));
  }
}
```

第三步：让 User 类继承 Model 类使 User 类重新具备模型类的所有功能

```typescript
// src/models/User.ts
export class User extends Model<UserProps> {}
```

第四步：测试代码是否有效

```typescript
// src/index.ts
import { User } from "./models/User";

const user = new User(
  { id: 1, name: "张三", age: 20 },
  "http://localhost:3005/users"
);

user.on("save", () => console.log("saved"));
user.save();
```

### 8.14 测试-创建文章模型类

```typescript
// src/models/Post.ts
import { Model } from "./Model";

export interface PostProps {
  id?: number;
  title?: string;
}

export class Post extends Model<PostProps> {}
```

```json
// db.json 注意: json 文件中不能有注释
{
  "posts": []
}
```

```typescript
// 测试: 将本地创建好的文章保存到服务器端
// src/index.ts
import { Post } from "./models/Post";

const post = new Post({ title: "测试文章一" }, "http://localhost:3005/posts");

post.on("save", () => console.log("saved"));
post.save();
```

### 8.15 需求-将文章数据保存到本地

> 目标：将文章模型类中的数据源更改为 localStorage

第一步：创建 LocalSync 类，提供 fetch 方法用于从本地获取文章数据、提供 save 方法用于将文章数据保存到本地

```typescript
// src/models/LocalSync.ts
import { HasId } from "../types/HasId";

export class LocalSync<T extends HasId> {
  constructor(public key: string) {}
  fetch(id: number): Promise<T | undefined> {
    return new Promise((resolve, reject) => {
      let storageData = localStorage.getItem(this.key);
      if (typeof storageData === "string") {
        resolve((JSON.parse(storageData) as T[]).find((item) => item.id === id));
      } else {
        reject(new Error("数据没找到"));
      }
    });
  }
  save(data: T): Promise<T> {
    return new Promise((resolve) => {
      let storageData = localStorage.getItem(this.key);
      if (typeof storageData === "string") {
        let list = JSON.parse(storageData) as T[];
        list.push(data);
        localStorage.setItem(this.key, JSON.stringify(list));
      } else {
        localStorage.setItem(this.key, JSON.stringify([data]));
      }
      resolve(data);
    });
  }
}
```

第二步：将 Model 模型类中的 ApiSync 替换为 LocalSync，通过构造函数接收 key 参数表示本地存储中的 key

```typescript
import { LocalSync } from "./LocalSync";

export class Model<T extends HasId> {
  // 通过组合的方式重组同步数据功能
  sync: LocalSync<T>;
  // 通过构造函数的方式接收数据
  constructor(user: T, key: string) {
    // 通过组合的方式重组同步数据功能
    this.sync = new LocalSync<T>(key);
  }
}
```

### 8.16 优化-增加程序的扩展性

> 目标：通过参数接收 Attributes 类实例、Sync 类实例、Eventing 类实例，将更多控制权下方到具体的数据模型类

目前案例中存在的问题是代码的扩展性不够好，因为 User 模型类要与服务器端进行交互，Post 模型类要与本地存储进行交互，但是目前在 Model 模型类中 sync 属性的要么是 ApiSync 类的实例，要么是 LocalSync 类的实例，无法同时满足 User 模型类和 Post 模型类的要求。

所以在 Model 类中我们不能直接实例化那三个类，那三个类的实例对象我们应该通过参数的方式进行接收，这样就将权利下放到了数据类型类，数据模型类如果要和本地存储进行数据交互就传递 LocalSync 类的实例，如果想和服务端进行交互就传递 ApiSync 类的实例。

既然现在要通过参数的方式接收类的实例，那么参数就需要被接口进行约束。比如 ApiSync 类和 LocalSync 类，只有它们都遵从了统一的接口规范，Model 类中才能够统一调用它们提供的属性或方法。

```typescript
// src/models/Model.ts
interface ModelAttributes<T> {
  get<K extends keyof T>(key: K): T[K];
  set(value: T): void;
  getData(): T;
}

interface Sync<T> {
  fetch(id: number): Promise<T | undefined>;
  save(data: T): Promise<T>;
}

interface Events {
  on(eventName: string, callback: () => void): void;
  trigger(eventName: string): void;
}

export class Model<T extends HasId> {
  constructor(
    private attributes: ModelAttributes<T>,
    private sync: Sync<T>,
    private events: Events
  ) {}
}
```

```typescript
// src/index.ts
import { LocalSync } from "./models/LocalSync";
import { Eventing } from "./models/Eventing";
import { ApiSync } from "./models/ApiSync";
import { Attributes } from "./models/Attributes";
import { User, UserProps } from "./models/User";
import { Post, PostProps } from "./models/Post";

// 创建新用户, 将用户保存到服务端
const user = new User(
  new Attributes<UserProps>({ name: "张三", age: 20 }),
  new ApiSync<UserProps>("http://localhost:3005/users"),,
  new Eventing()
);

user.on("save", () => console.log("user saved"));
user.save();

// 创建新文章, 将文章保存到本地存储
const post = new Post(
  new Attributes<PostProps>({ title: "测试文章一" }),
  new LocalSync<PostProps>("posts"),
  new Eventing()
);

post.on("save", () => console.log("post saved"));
post.save();
```

### 8.17 优化-简化调用逻辑

> 目标：使用静态方法简化创建用户、创建文章的逻辑

在创建用户时开发者应该只想关心该用户有哪些属性，如果每次创建用户还需要传递其他参数比如 sync、events，这对于创建用户来说就太麻烦了，所以接下来我们要做的事情就是在 User 模型类中创建 buildUser 方法，通过该方法对创建用户的逻辑进行进一步的封装。在 Post 模型类中创建 buildPost 方法，通过该方法对创建文章的逻辑进行进一步的封装。

```typescript
// src/models/User.ts
import { ApiSync } from "./ApiSync";
import { Attributes } from "./Attributes";
import { Eventing } from "./Eventing";
import { Model } from "./Model";

export interface UserProps {
  id?: number;
  name?: string;
  age?: number;
}

// 用户数据的请求地址
const rootUrl = "http://localhost:3005/users";

export class User extends Model<UserProps> {
  static buildUser(user: UserProps): User {
    return new User(
      new Attributes<UserProps>(user),
      new ApiSync<UserProps>(rootUrl),
      new Eventing()
    );
  }
}
```

```typescript
// src/models/Post.ts
import { Attributes } from "./Attributes";
import { Eventing } from "./Eventing";
import { LocalSync } from "./LocalSync";
import { Model } from "./Model";

export interface PostProps {
  id?: number;
  title?: string;
}

export class Post extends Model<PostProps> {
  static buildPost(post: PostProps): Post {
    return new Post(
      new Attributes(post),
      new LocalSync<PostProps>("posts"),
      new Eventing()
    );
  }
}
```

```typescript
// 以下为测试代码
// src/index.ts
import { User } from "./models/User";
import { Post } from "./models/Post";

const user = User.buildUser({ name: "张三", age: 20 });
user.on("save", () => console.log("user saved"));
user.save();

const post = Post.buildPost({ title: "测试标题一" });

post.on("save", () => console.log("post saved"));
post.save();
```

### 8.18 优化-属性赋值减少调用层级

> 目标：使用属性赋值的方式优化减少对象属性调用层级代码

```typescript
// src/models/Model.ts
export class Model<T extends HasId> {
  constructor(
    private attributes: ModelAttributes<T>,
    private sync: Sync<T>,
    private events: Events
  ) {}
  
  on = this.events.on;
  trigger = this.events.trigger;
  get = this.attributes.get;
}
```

```typescript
function Model(attributes) {
  this.attributes = attributes;
  this.get = this.attributes.get;
}
```

特别注意：以下属于错误写法。

```typescript
export class Model {
  attributes;
  constructor(attributes) {
    this.attributes = attributes;
  }
  // error: 属性"attributes"在其初始化前已被使用。
  get = this.attributes.get;
}
```

```javascript
function Model(attributes) {
  this.get = this.attributes.get;
  this.attributes = attributes;
}
```

### 8.19  需求-创建用户集合类

> 目标：创建用户集合类用于保存用户集合数据。

```typescript
// src/models/Collection.ts
import { User, UserProps } from "./User";
import { Eventing } from "./Eventing";
import axios, { AxiosResponse } from "axios";

export class UserCollection {
  // 用于存储集合的属性
  models: User[] = [];
  // 组装事件系统
  events: Eventing = new Eventing();
  // 缩短方法调用
  on = this.events.on;
  trigger = this.events.trigger;

  // 接收请求地址
  constructor(public rootUrl: string) {}

  // 用于发送请求获取集合数据
  fetch() {
    axios.get(this.rootUrl).then((response: AxiosResponse<UserProps[]>) => {
      // 遍历集合
      response.data.forEach((value: UserProps) => {
        // 将网络数据转换成 User 类实例
        const user = User.buildUser(value);
        // 使用 models 数组存储 User 类实例
        this.models.push(user);
      });
      // 数据获取完成后触发 change 事件
      this.trigger("change");
    });
  }
}
```

```typescript
// 以下为测试代码
// src/index.ts
import { UserCollection } from "./models/Collection";

const userCollection = new Collection("http://localhost:3000/users");

userCollection.on("change", () => {
  console.log(userCollection.models);
});

userCollection.fetch();
```

### 8.20 优化-创建通用模型集合类

在刚刚创建的集合类中，它的问题就在于只能获取用户集合数据，接下来我们要将它改造成能够获取任何集合数据的通用集合类。

```typescript
// src/models/Collection.ts
import { Eventing } from "./Eventing";
import axios, { AxiosResponse } from "axios";

export class Collection<T, K> {
  // 用于存储集合的属性
  models: T[] = [];
  // 组装事件系统
  events: Eventing = new Eventing();
  // 缩短方法调用
  on = this.events.on;
  trigger = this.events.trigger;

  // 接收请求地址
  constructor(public rootUrl: string, public deserialize: (value: K) => T) {}

  // 用于发送请求获取集合数据
  fetch() {
    axios.get(this.rootUrl).then((response: AxiosResponse<K[]>) => {
      // 遍历集合
      response.data.forEach((value: K) => {
        // 将网络数据转换成模型数据并存储
        this.models.push(this.deserialize(value));
      });
      // 数据获取完成后触发 change 事件
      this.trigger("change");
    });
  }
}
```

```typescript
// 以下为测试代码
// src/index.ts
import { Collection } from "./models/Collection";
import { User, UserProps } from "./models/User";

const userCollection = new Collection<User, UserProps>(
  "http://localhost:3005/users",
  (value) => User.buildUser(value)
);
userCollection.on("change", () => {
  console.log(userCollection.models);
});
userCollection.fetch();
```

### 8.21 优化-简化创建集合的逻辑

目前代码中存在的问题是创建集合时泛型参数的传递及普通参数的传递还是比较麻烦的，我们可以在 User 类中暴露一个用于创建用户集合的静态方法，以简化创建用户集合的相关逻辑代码。

```typescript
// src/models/User.ts
import { Collection } from "./Collection";

export class User extends Model<UserProps> {
  // 创建用户集合
  static buildCollection(): Collection<User, UserProps> {
    return new Collection<User, UserProps>(rootUrl, (value: UserProps) =>
      User.buildUser(value)
    );
  }
}
```

```typescript
// 以下为测试代码
// src/index.ts
import { User } from "./models/User";

const userCollection = User.buildCollection();
```

### 8.22 需求-创建用户表单视图类

> 目标：创建用于渲染用户表单的视图类并将用户表单渲染到页面中。

```typescript
// src/views/UserForm.ts
export class UserForm {
  // 模板父级
  constructor(public parent: Element) {}

  // 用于返回视图模板
  template(): string {
    return `
      <h1>用户信息</h1>
      <div>姓名: </div>
      <div>年龄: </div>
      <input type="text">
      <button class="set-age">生成随机年龄</button>
    `;
  }

  // 渲染方法
  // 1. 获取模板 2. 将模板内容渲染至 parent
  render() {
    const template = document.createElement("template");
    template.innerHTML = this.template();
    this.parent.append(template.content);
  }
}
```

```typescript
// src/index.ts
import { UserForm } from "./views/UserForm";

const root = document.querySelector("#root");

if (root != null) {
  const userForm = new UserForm(root);
  userForm.render();
}
```

### 8.23 需求-为模板元素附加事件

> 目标：为模板中的任意元素附加任意事件。

第一步：创建事件关系映射表。

```typescript
export class UserForm {
  // 返回[元素选择器][事件名称][事件处理函数]映射表
  eventsMap(): { [key: string]: () => void } {
    return {
      "click:.set-age": this.onSetAgeClick,
    };
  }
  onSetAgeClick() {
    console.log("clicked");
  }
}
```

第二步：在渲染模板时为元素绑定事件。

```typescript
export class UserForm {
  render() {
    const template = document.createElement("template");
    template.innerHTML = this.template();
    // 为元素绑定事件
    this.bindEvents(template.content);
    this.parent.append(template.content);
  }

  // 事件绑定
  bindEvents(fragment: DocumentFragment): void {
    // 获取元素事件映射表
    const eventsMap = this.eventsMap();
    // 遍历映射表
    for (let eventKey in eventsMap) {
      // 获取事件名称、元素选择器
      const [eventName, selector] = eventKey.split(":");
      // 从模板代码片段中找出要添加事件的元素们
      fragment.querySelectorAll(selector).forEach((element) => {
        // 为元素绑定事件
        element.addEventListener(eventName, eventsMap[eventKey]);
      });
    }
  }
}
```

### 8.24 需求-在视图中绑定模型数据

> 目标：在视图模板中渲染用户数据

```typescript
// src/views/UserForm.ts
import { User } from "../models/User";

export class UserForm {
  // 接收模型数据
  constructor(public parent: Element, public model: User) {}

  template(): string {
    // 渲染模型数据
    return `
      <div>姓名: ${this.model.get("name")}</div>
      <div>年龄: ${this.model.get("age")}</div>
    `;
  }
}
```

```typescript
// 以下为测试代码
// src/index.ts
import { UserForm } from "./views/UserForm";
import { User } from "./models/User";

const root = document.querySelector("#root");

if (root) {
  const user = User.buildUser({ name: "张三", age: 20 });
  const userForm = new UserForm(root, user);
  userForm.render();
} else {
  throw new Error("Root element not found");
}
```

### 8.25 需求-设置年龄为随机值

> 目标：点击按钮生成随机年龄，年龄数据被更改后重新渲染视图。

第一步：创建用于生成随机年龄的方法。

```typescript
// src/views/UserForm.ts
export class UserForm {
  // 设置随机年龄
  // 特别注意: 此处要使用箭头函数, 使该方法中的 this 指向当前类的实例
  // 因为 onSetAgeClick 最终是被 button 元素的点击事件调用起来的
  // 如果不使用箭头函数 那么该方法中的 this 将指向触发事件的元素
  onSetAgeClick = () => {
    this.model.set({ age: Math.ceil(Math.random() * 100) });
  };
}
```

第二步：为按钮绑定事件，事件触发后调用 setRandomAge 方法设置随机年龄。

```typescript
// src/views/UserForm.ts
export class UserForm {
  // 返回[元素][事件名称][事件处理函数]映射表
  eventsMap(): { [key: string]: () => void } {
    return {
      "click:.set-age": this.onSetAgeClick,
    };
  }
  // 用于返回模板字符串的方法
  template(): string {
    return `<button class="set-age">设置随机年龄</button>`;
  }
}
```

第三步：随机年龄设置成功后，重新渲染视图。

由于模型数据被更改后会自动触发 change 事件，所以此处需要监听 change 事件，事件触发后调用 render 方法重新渲染视图即可。

```typescript
// src/views/UserForm.ts
export class UserForm {
  constructor() {
    this.bindModel();
  }
  
  render() {
    // 清空旧视图
    this.parent.innerHTML = "";
  }

  bindModel() {
    // 监听模型数据的变化
    this.model.on("change", () => {
      // 重新渲染视图
      this.render();
    });
  }
}
```

### 8.26 需求-通过表单修改姓名

> 目标：在文本框中输入姓名，点击按钮更新姓名。

```typescript
// src/views/UserForm.ts
export class UserForm {
  // 返回[元素][事件名称][事件处理函数]映射表
  eventsMap(): { [key: string]: () => void } {
    return {
      "click:.set-name": this.onSetNameClick,
    };
  }

  onSetNameClick = () => {
    const input = this.parent.querySelector("input");
    if (input != null) {
      const name = input.value;
      this.model.set({ name });
    }
  };

  template(): string {
    return `
      <input type="text">
      <button class="set-name">设置姓名</button>
    `;
  }
}
```

### 8.27 需求-同步用户信息

> 目标：点击保存用户按钮将本地创建好的用户同步到服务端

```typescript
// src/views/UserForm.ts
export class UserForm {
  eventsMap(): { [key: string]: () => void } {
    return {
      "click:.save-user": this.onUserSaveClick,
    };
  }
  
  onUserSaveClick = () => {
    this.model.save();
  };

  template(): string {
    return `
      <input type="text" placeholder="${this.model.get("name")}">
      <button class="save-user">保存用户</button>
    `;
  }
}
```

### 8.28 优化-创建通用视图类

> 目标：创建 View 类放置和视图相关的通用逻辑，其他具体的视图类通过继承该类拥有这些通用视图逻辑。

① 将可以被视图类之间复用的代码拷贝到 View 类中并通过泛型参数约束接收的模型实例参数

```typescript
// src/views/View.ts
import { Model } from "../models/Model";
import { HasId } from "../types/HasId";

export abstract class View<T extends Model<K>, K extends HasId> {
  // 模板父级
  constructor(public parent: HTMLElement, public model: T) {
    this.bindModel();
  }
  
	// 约束子类必须提供 template 方法
  abstract template(): string;
  // 约束子类必须提供 eventsMap 方法
  abstract eventsMap(): { [key: string]: () => void };

  // 渲染方法
  // 1. 获取模板 2. 将模板内容渲染至 parent
  render () {
    this.parent.innerHTML = "";
    const template = document.createElement("template");
    template.innerHTML = this.template();
    this.bindEvents(template.content);
    this.parent.append(template.content);
  }

  // 事件绑定
  bindEvents(fragment: DocumentFragment): void {
    // 获取元素事件映射表
    const eventsMap = this.eventsMap();
    // 遍历映射表
    for (let eventKey in eventsMap) {
      // 获取事件名称、元素选择器
      const [eventName, selector] = eventKey.split(":");
      // 从模板代码片段中找出要添加事件的元素们
      fragment.querySelectorAll(selector).forEach((element) => {
        // 为元素绑定事件
        element.addEventListener(eventName, eventsMap[eventKey]);
      });
    }
  }

  bindModel() {
    this.model.on("change", () => {
      this.render();
    });
  }
}
```

```typescript
// src/views/UserForm.ts
import { View } from "./View";

export class UserForm extends View<T extends Model<K>, K extends HasId> {
  // 返回[元素][事件名称][事件处理函数]映射表
  eventsMap(): { [key: string]: () => void } {
    return {
      "click:.set-age": this.onSetAgeClick,
      "click:.set-name": this.onSetNameClick,
    };
  }

  onSetAgeClick = () => {
    this.model.setRandomAge();
  };

  onSetNameClick = () => {
    const input = this.parent.querySelector("input");
    if (input) {
      const name = input.value;
      this.model.set({ name });
    }
  };

  // 用于返回模板字符串的方法
  template(): string {
    return `
      <div>
        <h1>用户表单</h1>
        <div>姓名: ${this.model.get("name")}</div>
        <div>年龄: ${this.model.get("age")}</div>
        <input type="text">
        <button class="set-name">设置姓名</button>
        <button class="set-age">设置随机年龄</button>
      </div>
    `;
  }
}
```

② 让用户表单视图类继承通用视图类 View

```typescript
// src/views/UserForm.ts
import { User, UserProps } from "../models/User";

export class UserForm extends View<User, UserProps> {}
```

### 8.29 需求-嵌套视图类

> 目标：创建 UserContainer、UserShow 视图类，在 UserContainer 视图类中渲染 UserForm、UserShow 视图类

<img src="./images/55.png" align="left" width="26%"/>

① 创建 UserShow 视图类用于展示用户信息

```typescript
import { View } from "./View";
import { User, UserProps } from "../models/User";

export class UserShow extends View<User, UserProps> {
  eventsMap(): { [key: string]: () => void } {
    return {};
  }
  template(): string {
    return `
      <h4>用户信息</h4>
      <div>姓名: ${this.model.get("name")}</div>
      <div>年龄: ${this.model.get("age")}</div>
    `;
  }
}
```

注意：由于 UserShow 类继承了 View 抽象类，View 抽象类要求子类实现 template 方法和 eventsMap 方法，但是在 UserShow 类中我们并不打算为元素绑定事件，但是仍然要写一个返回空对象的 eventsMap 方法，这显然是代码中考虑不周的地方。

要解决上述问题，我们只需要在 View 类中添加一个默认的返回空对象的 eventsMap 方法即可，如果子类要绑定事件，可以覆盖父类中的该方法。

```typescript
// src/views/View.ts
export abstract class View<T extends Model<K>, K> {
  // 子类可以通过 eventsMap 方法提供事件关系映射表
  // 如果子类没有提供父子调用自身的 eventMap 方法, 防止程序报错
  eventsMap(): { [key: string]: () => void } {
    return {};
  }
}
```

② 创建 UserContainer 视图类，在该类中准备 UserShow、UserForm 视图的挂载点

```typescript
// src/views/UserContainer.ts
import { View } from "./View";
import { User, UserProps } from "../models/User";

export class UserContainer extends View<User, UserProps> {
  template(): string {
    return `
      <div class="user-show"></div>
      <div class="user-form"></div>
    `;
  }
}
```

③ 在 id 为 root 的 div 容器中渲染 UserContainer 视图类

```react
// src/index.ts
import { UserContainer } from "./views/UserContainer";
import { User } from "./models/User";

const root = document.querySelector("#root");

if (root != null) {
  // 创建用户
  const user = User.buildUser({ name: "张三", age: 20 });
  const userContainer = new UserContainer(root, user);
  userContainer.render();
}
```

④ 在 UserContainer 视图类中渲染 UserShow 和 UserForm 视图类

```typescript
// src/views/UserContainer.ts
import { UserForm } from "./UserForm";
import { UserShow } from "./UserShow";
import { UserProps } from "../models/User";
import { User } from "../models/User";
import { View } from "./View";

export class UserContainer extends View<User, UserProps> {
  template(): string {
    return `
      <div class="user-show"></div>
      <div class="user-form"></div>
    `;
  }
	
  // 重写父类方法
  override render() {
    // 先调用父类方法将当前类渲染到根元素中
    super.render();
    // 获取 UserShow 视图的挂载点
    const userShow = document.querySelector(".user-show");
    // 获取 UserForm 视图的挂载点
    const userForm = document.querySelector(".user-form");
    // 渲染 UserShow 视图
    new UserShow(userShow!, this.model).render();
    // 渲染 UserForm 视图
    new UserForm(userForm!, this.model).render();
  }
}
```

### 8.30 优化-提取可复用逻辑

> 目标：将 UserContainer 视图类中的获取其他视图挂载点的逻辑提取到 View 视图类中

在任何一个具体的视图类中如果想要渲染其他视图类，都必须先获取到其他视图类在当前类中的挂载点元素。

所以接下来我们要做的事情就是将获取其他视图类挂载点的逻辑提取到 View 中。

具体的实现思路是子类负责提供挂载点元素的元素选择器，父类负责根据元素选择器获取元素对象并保存元素对象。

父类获取完元素以后子类根据父类获取到的元素挂载点渲染其他视图。

子类提供其他视图的挂载点信息 -> 父类根据子类提供的挂载点信息获取元素 -> 子类使用父类获取到的挂载点元素渲染其他视图

------

① 在 UserContainer 视图类中提供要渲染的其他视图的挂载点信息。

```typescript
// src/views/UserContainer.ts
export class UserContainer extends View<User, UserProps> {
  // 要渲染的其他视图的挂载点映射表
  regionsMap(): { [key: string]: string } {
    // key: 标识
    // value: 父类元素选择器
    return {
      userShow: ".user-show",
      userForm: ".user-form",
    };
  }
}
```

② 在 View 类中根据子类提供的挂载点信息对象获取真实 DOM 对象并保存

```typescript
import { Model } from "../models/Model";

export abstract class View<T extends Model<K>, K> {
  // 用于存储获取到的目标父级元素
  regions: { [key: string]: Element } = {};

  mapRegions(fragment: DocumentFragment): void {
    // 获取子类提供的父级信息映射表
    const regionsMap = this.regionsMap();
    // 遍历父级信息映射表
    for (let key in regionsMap) {
      // 从映射表中获取父级元素选择器
      const selector = regionsMap[key];
      // 在模板中获取目标父级元素
      const element = fragment.querySelector(selector);
      // 如果目标父级存在
      if (element) {
        // 存储目标父级元素
        this.regions[key] = element;
      }
    }
  }

  render() {
    // 获取到视图 DOM 对象以后从中获取挂载点对象并保存
    this.mapRegions(template.content);
    // 调用子类提供的 onRender 方法渲染其他视图
    this.onRender();
  }
  
  // 如果子类要获取挂载点元素则当前方法需要被覆盖
  // 如果子类不需要获取挂载点元素则走当前方法, 防止程序报错
  regionsMap(): { [key: string]: string } {
    return {};
  }
  // 如果子类要渲染其他视图则当前方法需要被覆盖
  // 如果子类不需要渲染其他视图则走当前方法, 方法程序报错
  onRender() {}
}
```

③ 在 UserContainer 类中添加 onRender 方法用于渲染其他视图

```typescript
// src/views/UserContainer.ts
import { UserForm } from "./UserForm";
import { UserShow } from "./UserShow";

export class UserContainer extends View<User, UserProps> {
  onRender() {
    new UserShow(this.regions.userShow, this.model).render();
    new UserForm(this.regions.userForm, this.model).render();
  }
}
```

```typescript
// 以下为测试代码
// src/index.ts
import { UserContainer } from "./views/UserContainer";
import { User } from "./models/User";

const root = document.querySelector("#root");

if (root != null) {
  // 创建用户
  const user = User.buildUser({ name: "张三", age: 20 });
  const userContainer = new UserContainer(root, user);
  userContainer.render();
}
```

### 8.31 需求-创建用户列表视图类

> 目标：创建 UserList 视图类用于渲染用户列表数据

```typescript
// src/views/UserList.ts
import { Collection } from "../models/Collection";
import { User, UserProps } from "../models/User";

export class UserList {
  constructor(
    public parent: Element,
    public collection: Collection<User, UserProps>
  ) {}

  template() {
    return `
      <h3>用户列表</h3>
      <ul>${this.renderList()}</ul>
    `;
  }
	
  // [User, User, User]
  // ['<li>User</li>', '<li>User</li>', '<li>User</li>']
  // '<li>User</li><li>User</li><li>User</li>'
  renderList() {
    return this.collection.models
      .map((model) => `<li>${model.get("name")}${model.get("age")}</li>`)
      .join("");
  }

  render() {
    const template = document.createElement("template");
    template.innerHTML = this.template();
    this.parent.appendChild(template.content);
  }
}
```

```typescript
// src/index.ts
// 以下为测试代码
import { User } from "./models/User";
import { UserList } from "./views/UserList";

const root = document.querySelector("#root");

if (root != null) {
  const userCollection = User.buildCollection();
  userCollection.on("change", () => {
    const userList = new UserList(root, userCollection);
    userList.render();
  });
  userCollection.fetch();
}
```

### 8.32 优化-创建通用集合视图类

> 目标：创建一个用于渲染集合数据的通用视图类

------

需要提取到集合视图类中的通用逻辑：

(1) 接收挂载点 DOM 对象、接收集合数据的 constructor 构造函数

(2) 用于渲染视图的 render 方法

(3) 用于遍历集合数据的循环代码

------

创建通用集合视图类 CollectionView 的思路：

先将通用逻辑提供到通用视图类中，由父类在 render 中遍历集合数据，子类提供渲染模型对象的方法，父类在循环中调用该方法渲染集合数据。

子类提供集合数据的挂载点元素选择器，父类通过该选择器获取 DOM 对象并将模型对象的渲染结果追加到该挂载点对象中。


------

```typescript
// src/views/CollectionView.ts
import { Collection } from "../models/Collection";

export abstract class CollectionView<T, K> {
  // 接收子类视图挂载点元素
  // 接收集合模型类实例对象
  constructor(public parent: Element, public collection: Collection<T, K>) {}

  // 要求子类必须提供返回视图模板的方法
  abstract template(): string;
  // 要求子类必须提供渲染列表项的方法
  abstract renderItem(model: T): DocumentFragment;
  // 要求子类必须他提供列表项挂载点选择器
  abstract mountSelector: string;

  render() {
    // 创建 template 元素 用于转换视图模板
    const template = document.createElement("template");
    // 转换视图模板为文档碎片对象
    template.innerHTML = this.template();
    // 获取列表挂载点对象
    const mountElement = template.content.querySelector(this.mountSelector);
    // 如果挂载点对象存在
    if (mountElement != null) {
      // 遍历集合数据
      for (let i = 0; i < this.collection.models.length; i++) {
        // 获取模型对象
        const model = this.collection.models[i];
        // 调用子类提供的用于返回文档碎片的列表项对象
        const item = this.renderItem(model);
        // 追加列表项对象到挂载点中
        mountElement.appendChild(item);
      }
    }
    // 将子类视图挂载的目标挂载点中
    this.parent.appendChild(template.content);
  }
}

```

```typescript
// src/views/UserList.ts
import { User, UserProps } from "./../models/User";
import { CollectionView } from "./CollectionView";

export class UserList extends CollectionView<User, UserProps> {
  // 视图模板
  template() {
    return `
      <h3>用户列表</h3>
      <ul class="list"></ul>
    `;
  }
  // 列表挂载点对应的元素选择器
  mountSelector = ".list";

  // 用于返回列表项
  // 将会在循环中反复调用
  renderItem(model: User) {
    const temlate = document.createElement("template");
    temlate.innerHTML = `
      <li>${model.get("name")} ${model.get("age")}</li>
    `;
    return temlate.content;
  }
}

```















































































```typescript
// 使用 const 关键字声明常量, 常量的值又是基本数据类型时
// TypeScript 编译器会将该常量推断字面量类型
// 因为常量值本身没有变化的可能, 使用字面量类型限制更为精确
const test = "test"; // "test"
const isMarray = false; // false
const goal = 100; // 100

// 使用 let 关键字声明的变量没有以上特性
// 因为变量的值可以被修改
let hello = "world"; // string;
let isStudent = true; // boolean;
let x = 199; // number

// 考虑以下代码为何报错?
const request = { url: "api.example.com", method: "POST" };
function handleRequset(url: string, method: "GET" | "POST") {}
// 类型"string"的参数不能赋给类型“"POST" | "GET"”的参数
handleRequset(request.url, request.method);

// requset.method 属性被 ts 编译器推断为了 string 类型, string 类型的值可以是任何字符串
// 而 method 参数为字符串字面量联合类型, 值只能是 "POST" 和 "GET"
// 两者类型不匹配, 所以报错
```

Tsconfig.json 配置详解

