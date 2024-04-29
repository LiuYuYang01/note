# uniapp

## 快速开始

**创建工程**

Vue3 + JavaScript + Vite

```shell
npx degit dcloudio/uni-preset-vue#vite my-vue3-project
```

Vue3 + TypeScript + Vite

```shell
npx degit dcloudio/uni-preset-vue#vite-ts my-vue3-project
```



## 项目工程结构

```
├── .husky                     # Git Hooks
├── .vscode                    # VS Code 插件 + 设置
├── dist                       # 打包文件夹（可删除重新打包）
├── src                        # 源代码
│   ├── components             # 全局组件
│   ├── composables            # 组合式函数
│   ├── pages                  # 主包页面
│       ├── index               # 首页
│       ├── category            # 分类页
│       ├── cart                # 购物车
│       ├── my                  # 我的
│       ├── goods               # 商品详情
│       └── hot                 # 热门推荐
│       └── login               # 登录页
│   ├── pagesMember            # 分包页面(用户模块)
│       ├── address             # 地址管理
│       ├── address-form        # 地址表单
│       ├── profile             # 用户信息
│       └── settings            # 用户设置
│   ├── pagesOrder             # 分包页面(订单模块)
│       ├── create              # 创建订单
│       ├── detail              # 订单详情
│       ├── list                # 订单列表
│       └── payment             # 支付结果
│   ├── services               # 所有请求
│   ├── static                 # 存放应用引用的本地静态资源的目录
│       ├── images              # 普通图片
│       └── tabs                # tabBar 图片
│   ├── stores                 # 全局 pinia store
│       ├── modules             # 模块
│       └── index.ts            # store 入口
│   ├── styles                 # 全局样式
│       └── fonts.scss          # 字体图标
│   ├── types                  # 类型声明文件
│   ├── utils                  # 全局方法
│   ├── App.vue                # 入口页面
│   ├── main.ts                # Vue初始化入口文件
│   ├── pages.json             # 配置页面路由等页面类信息
│   ├── manifest.json          # 配置appid等打包信息
│   └── uni.scss               # uni-app 内置的常用样式变量
├── .eslintrc.cjs              # eslint 配置
├── .prettierrc.json           # prettier 配置
├── .gitignore                 # git 忽略文件
├── index.html                 # H5 端首页
├── package.json               # package.json 依赖
├── tsconfig.json              # typescript 配置
└── vite.config.ts             # vite 配置
```



## 安全距离

不同手机的安全区域不同，适配安全区域能防止页面重要内容被遮挡。

可通过 `uni.getSystemInfoSync()` 获取屏幕边界到安全区的距离。

```vue
<script setup lang="ts">
const { safeAreaInsets } = uni.getSystemInfoSync()
</script>

<template>
    <view class="navbar" :style="{ paddingTop: safeAreaInsets?.top + 'px' }">
        
    </view>
</template>
```

![安全区域](./image/home_picture_2.89dbf506.png)



## 全局组件类型

在这段代码中，`vue` 模块被导入并扩展了一个名为 `GlobalComponents` 的接口。

通过这个接口，我们可以将 `XtxSwiper` 和 `XtxGuess` 两个组件声明为全局组件，这样它们可以在整个项目的任何地方使用，而无需在每个组件中分别导入和注册它们，并且具备组件类型

```typescript
import 'vue'
import Swiper from '@/components/Swiper.vue'
import Guess from '@/components/Guess.vue'

declare module 'vue' {
  export interface GlobalComponents {
    Swiper: typeof Swiper
    Guess: typeof Guess
  }
}

// 定义组件的类型
type GuessInstance = InstanceType<typeof Guess>
```

```javascript
const guessRef = ref<GuessInstance>()
```



## 生命周期

onLoad 每次进入页面时候触发



## 常用操作

### 获取上个页面传递的数据

App.vue

```vue
<navigator url="/pages/hot/hot?type=100"></navigator>
```

hot.vue

```javascript
onLoad((e) => {
    console.log(e); // { type:100 }
})
```



## 常用API

### 界面

#### 设置页面的标题

```javascript
uni.setNavigationBarTitle({
    title: '新的标题'
});
```



## 遇到的坑

### scroll-view 上拉触底事件失效

在使用 `scroll-view` 组件的 `@scrolltolower` 事件监听是否上拉触底时一定要给他或父级设置高度，否则会导致事件无法触发

```vue
<script setup lang="ts">
// 上拉触底
const onScrolltolower = () => {
  console.log("触底了~");
}
</script>

<template>
  <!-- 滚动容器 -->
   <scroll-view scroll-y @scrolltolower="onScrolltolower">
		<!-- 自定义内容 -->
  </scroll-view>
</template>

<style lang="scss">
page {
  height: 100%;
}
</style>

```





```

```

