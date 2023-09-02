# JavaScript 小技巧

## 扩展运算符

扩展运算符由三个点 `...` 表示，可用于对象 或 数组的解构。

```javascript
const a = { a: 1, b: 2, c: 3 };

const b = { ...a, b: 5 };
console.log(b);
// { a: 1, b: 5, c: 3 }
```



```javascript
const a = [1, 2, 3];

const b = [...a, 4, 5, 6];
console.log(b);
// [ 1, 2, 3, 4, 5, 6 ]
```



使用扩展运算符可以轻松提取和操作数组元素

```javascript
const a = [1, 2, 3, 4, 5];

const b = [...a.slice(0, 2), 6, ...a.slice(4)];
console.log(b);
// [ 1, 2, 6, 5 ]
```



## 封装一个判断类型的函数

使用 `Object.prototype.toString` 来搭配闭包实现传入一个参数判断是否为指定的数据类型



**全写**

```js
        const isType = (type) => {
            return (target) => {
                return `[object ${type}]` === Object.prototype.toString.call(target)
            }
        }
```



**简写**

```javascript
		const isType = type => target => `[object ${type}]` === Object.prototype.toString.call(target)

        const isArray = isType("Array")

        console.log(isArray([])); //true
        console.log(isArray("字符串")); //false
```

