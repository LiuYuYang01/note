# TailwindCSS

鼠标经过外层 `div` 让里面的指定容器改变颜色

```html
<div className="group flex justify-between w-96 h-96 bg-blue-400">
    <div className="w-44 h-44 bg-yellow-400"></div>
    <div className="w-44 h-44 bg-red-400 group-hover:bg-green-400"></div>
</div>
```

