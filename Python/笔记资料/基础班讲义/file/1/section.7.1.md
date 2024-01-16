## 1. python2版本中的输入

### 1.1 raw_input()
python2中的 raw_input()函数和 Python3 中的 input 使用完全相同

### 1.2 input()

input()函数与raw_input()类似，但其接受的输入必须是表达式。

```python
>>> a = input()
123
>>> a
123
>>> type(a)
<type 'int'>
>>> a = input()
abc
Traceback (most recent call last):
  File "<stdin>", line 1, in <module>
  File "<string>", line 1, in <module>
NameError: name 'abc' is not defined
>>> a = input()
"abc"
>>> a
'abc'
>>> type(a)
<type 'str'>
>>> a = input()
1+3
>>> a
4
>>> a = input()
"abc"+"def"
>>> a
'abcdef'
>>> value = 100
>>> a = input()
value
>>> a
100
```

**input()接受表达式输入，并把表达式的结果赋值给等号左边的变量**
