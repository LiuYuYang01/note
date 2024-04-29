# HTML & CSS

## HTML
### 简述一下你对 HTML 语义化的理解？
简单来说就是用正确的标签做正确的事情
规范的使用 `HTML` 标签会使页面的内容结构化，便于对浏览器、搜索引擎解析
搜索引擎中的爬虫也依赖于 `HTML` 标签来确定各个关键字的权重，利于网站 `SEO`



### 标签上 title 与 alt 属性的区别是什么？
`title` 属性是用于鼠标悬停在 `img` 标签中所显示的内容，主要用于注释信息，给用户解读的

而 `alt` 是给搜索引擎识别的，在 `img` 图片不显示情况下，就会显示 `alt` 的内容

在定义 `img` 标签时，将 `title` 与 `alt` 属性写全，可以保证在各种浏览器都能正常使用



## CSS

### 如何使盒子水平垂直居中？

[[ CSS \] 四种方式教你如何实现水平垂直居中 - Thrive Code (liuyuyang.net)](https://code.liuyuyang.net/index.php/archives/2210/)



### 块级和行内元素分别有哪些，各有什么特点 ？

**行内元素：** a、span、b、img、strong、input、select、lable、em、button、textarea 、selecting

**块级元素：** div、ul、li、dl、dt、dd、p、h1-h6、blockquote、form

**行内、块级元素区别：**

1、块元素会独占一行，宽度自动撑满父元素的宽度。而行内元素不会独占一行，多个块元素会在一行显示，一行放不下会自动换行

2、块级元素可以直接设置 `width, height` 属性，行内元素设置 `width, height` 无效，需要转换为块元素 (注意：块级元素即使设置了宽度，仍然是独占一行的) 

3、块级元素可以设置 `margin，padding` ，而行内元素只对 `padding-left,padding-right,margin-left,margin-right` 这些水平方向起效果，但是垂直方向的 `padding-top,padding-bottom,margin-top,margin-bottom` 都不会产生效果。（水平方向有效， 竖直方向无效）
