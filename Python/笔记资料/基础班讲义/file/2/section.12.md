# 总结

## if
* if往往用来对条件是否满足进行判断
* if有4中基本的使用方法：

1. 基本方法

 	```python
 		if 条件:
 			满足时做的事情
 	```
2. 满足与否执行不同的事情
 
 	```python
 		if 条件:
 			满足时做的事情
 		else:
 			不满足时做的事情
 	```
3. 多个条件的判断
 
 	```python
 		if 条件:
 			满足时做的事情
 		elif 条件2:
 			满足条件2时做的事情
 		elif 条件3:
 			满足条件3时做的事情
 		else:
 			条件都不满足时做的事情

 	```
4. 嵌套
 	
 	```python
 		if 条件:
 			满足时做的事情

 			这里还可以放入其他任何形式的if判断语句
	```

### while
* while循环一般通过数值是否满足来确定循环的条件
	
 	```python
		i = 0
		while i<10:
			print("hello")
			i+=1
 	```

### for
* for循环一般是对能保存多个数据的变量，进行遍历

	```python
		name = 'itheima'

		for x in name:
			print(x)
	```

* if、while、for等其他语句可以随意组合，这样往往可以完成复杂的功能
