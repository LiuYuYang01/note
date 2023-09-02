# Python 包

## JSON

**dumps**

将 `python` 字典编码成 `JSON` 字符串

```python
import json

d = {'name': '你猜', 'age': 19, 'city': '江苏'}
s = json.dumps(d)
print(s, type(s))
# {"name": "\u4f60\u731c", "age": 19, "city": "\u6c5f\u82cf"} <class 'str'>
```



从上述代码中会发现 `dumps` 默认会将字典转换为 `unicode` 编码格式，可以通过 `ensure_ascii=False` 设置为中文编码

```python
print(json.dumps(d, ensure_ascii=False))
# {"name": "你猜", "age": 19, "city": "江苏"}
```

