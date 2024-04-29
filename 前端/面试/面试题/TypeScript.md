# TypeScript

## 什么是方法重载？

方法重载简单来说就是根据函数的参数数量、类型不同的情况下具有不同的行为

`Ts` 是本身是不支持方法重载的，但我们可以手动实现一个

```typescript
function fn (data: string): string
function fn (data: number): string

function fn (data: string | number): string {
  if (typeof data === 'string') {
    return '字符串'
  } else {
    return '数值'
  }
}

fn('100') // 字符串
fn(200) // 数值
```



像其他语言比如 `Java` 就支持方法重载，就拿这个最简单的例子来说：你传什么类型，他就调用对应类型的函数

```java
public void fn(String data) {}
public void fn(Integer data) {}
public void fn(Boolean data) {}
```

