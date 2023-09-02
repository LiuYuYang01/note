# psutil

**安装**

`pip3 install psutil`



## CPU

通过调用 `cpu_percent` 来获取系统 `CPU` 数据

```python
import psutil

cpu = psutil.cpu_percent(interval=1, percpu=True)
print(cpu)
# [10.9, 1.6, 15.6, 10.9, 7.8, 6.2, 6.2, 3.1, 9.4, 1.6, 4.7, 6.2, 3.1, 4.7, 4.7, 4.7]
```



上述获取的是以数组形式存储的CPU数据，我们可以求出平均值然后再保留两位小数

```python
cpu = psutil.cpu_percent(interval=1, percpu=True)
cpu = round(sum(cpu) / len(cpu), 2)

print(cpu) # 6.34
```



## 磁盘

通过调用 `disk_usage` 来获取系统磁盘信息

```python
disk = psutil.disk_usage('/')
# sdiskusage(total=536871243776, used=314116947968, free=222754295808, percent=58.5)
```

- total：表示磁盘的总容量，以字节（bytes）为单位。
- used：表示已使用的磁盘容量，以字节为单位。
- free：表示可用的磁盘容量，以字节为单位。
- percent：表示已使用容量占总容量的百分比。



默认情况下磁盘容量以 `bytes` 为单位，我们可以手动用 `G` 作为单位，并且将结果只保留2为小数

```python
# 总容量
print(round(disk.total / 1_073_741_824))  # 500
# 已使用容量
print(round(disk.used / 1_073_741_824))  # 293
# 可用容量
print(round(disk.free / 1073741824))  # 207
# 已用容量百分比
print(disk.percent)  # 58.5
```

**注意：** 在 `Python 3.6` 及更高版本中，可以使用下划线作为数字的千位分隔符，以提高可读性。在上述代码中，我们使用了 `1_073_741_824` 来表示 `1073741824`，使得代码更易读。



## 内存

通过调用 `virtual_memory` 来获取系统内存

```python
memory = psutil.virtual_memory()
print(memory)
# svmem(total=17024741376, available=7618646016, percent=55.2, used=9406095360, free=7618646016)
```

- total：表示系统内存的总容量，以字节（bytes）为单位。
- available：表示系统内存的可用容量，以字节为单位。
- percent：表示已使用内存占总容量的百分比。
- used：表示已使用的内存容量，以字节为单位。
- free：表示空闲的内存容量，以字节为单位。



```python
# 获取内存总量
print(round(memory.total / 1_073_741_824, 2))  # 15.86
# 获取可用内存
print(round(memory.available / 1_073_741_824, 2))  # 6.38
# 获取内存已使用百分比
print(memory.percent)  # 59.8
# 获取已使用内存
print(round(memory.used / 1_073_741_824, 2))  # 9.48
```



## 开机时间

```python
import psutil
import datetime

boot_time = psutil.boot_time()
boot_time_datetime = datetime.datetime.fromtimestamp(boot_time)

print("系统开机时间:", boot_time_datetime)
```



## 系统名称

使用 `platform` 模块获取当前操作系统的名称

```python
import platform

# 名称
name = platform.system()
# 版本
version = platform.release()

print(name, version)  # Windows 10
```

