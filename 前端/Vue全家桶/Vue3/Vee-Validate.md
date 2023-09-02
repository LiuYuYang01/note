# Vee-Validate

**安装**

```
npm install vee-validate yup --save
```



## 字段级验证

### 添加表单验证

`VeeValidate` 公开了 2 个您将经常使用的组件

导入它们并在 `Vue` 组件上注册它们，然后将以下元素替换为 `vee-validate` 组件：

- 替换为，同时保留相同的属性。`<input>`  相当于 `<Field />`
- 替换为但删除修饰符。`<form>` 相当于 `<Form /> `

```vue
<script setup lang='ts'>
import { Form, Field } from 'vee-validate';
    
const onSubmit = () => {
  console.log("表单提交成功!");
}
</script>

<template>
  <Form @submit="onSubmit">
    <Field type="text" name="text"/>
    <button>提交</button>
  </Form>
</template>
```



### 获取表单数据

假设输入的账号为：admin    密码为：123123

```vue
<script setup lang='ts'>
import { Form, Field } from 'vee-validate';
const onSubmit = (values:string) => {
  console.log(values);
  // {username: 'admin', password: '123123'}
}
</script>

<template>
  <Form @submit="onSubmit">
    <Field type="text" name="username"/>
    <Field type="password" name="password"/>
    <button>提交</button>
  </Form>
</template>
```



### 校验表单数据

约束用户名6~16字符并且不能等于空

```vue
<script setup lang='ts'>
import { Form, Field } from 'vee-validate';
    
// 校验用户名
const ValidateUsername = (value: string) => {
  if (!value) return "用户名不能为空"

  // 约束用户名6~16字符
  const regex = /^[a-zA-Z0-9]{6,16}$/

  if (!regex.test(value)) return "用户名必须是6~16个字符"

  // 校验成功
  return true
}
</script>

<template>
  <Form>
    <Field type="text" name="username" :rules="ValidateUsername" />
    <button>提交</button>
  </Form>
</template>
```



### 错误消息提示

```vue
<script setup lang='ts'>
import { Form, Field, ErrorMessage } from 'vee-validate';
// 校验用户名
const ValidateUsername = (value: string) => {
  if (!value) return "用户名不能为空"

  // 约束用户名6~16字符
  const regex = /^[a-zA-Z0-9]{6,16}$/

  if (!regex.test(value)) return "用户名必须是6~16个字符"

  // 校验成功
  return true
}
</script>

<template>
  <Form>
    <Field type="text" name="username" :rules="ValidateUsername" />

    <!-- 错误消息 -->
    <ErrorMessage name="username" />

    <div><button>提交</button></div>
  </Form>
</template>
```



使用 `yup` 进行表单校验

```vue
<script setup lang='ts'>
import { Form, Field, ErrorMessage } from 'vee-validate';
import * as yup from 'yup';

// 校验用户名
const ValidateUsername = yup.string().required("用户名不能为空").min(5, "用户名不能少于5个字符").max(16, "用户名不能超过16个字符")
// 如果不写错误信息，会显示默认的
</script>

<template>
  <Form>
    <Field type="text" name="username" :rules="ValidateUsername" />

    <!-- 错误消息 -->
    <ErrorMessage name="username" />

    <div><button>提交</button></div>
  </Form>
</template>
```



### 字段级验证示例

```vue
<script setup lang="ts">
import { Field, Form, ErrorMessage } from 'vee-validate';

// 校验账号
function userName(value: string) {
  // 如果没有值
  if (!value) return '请输入账号：';

  // 内容不能等于空
  if (value && !value.trim().length) return "请输入内容："

  // 账号必须大于6 小于 10位数
  if (value.length < 6 || value.length > 10) return "请输入6 ~ 10位数的账号"

  // 用户名只能由大小字母数字组成
  const rules = /^[a-zA-Z0-9]{6,10}$/
  if (!rules.test(value)) return "你的用户名不规范"

  return "用户名校验成功";
}

// 校验密码
function passWord(value: string) {
  // 如果没有值
  if (!value) return '请输入密码：';

  const rules = /^[a-zA-Z0-9]{6,10}$/
  if (!rules.test(value)) return "你的密码不规范"

  return "密码校验成功"
}
</script>

<template>
  <Form>
    <div>
      <!-- Field 相当于 input -->
      账号：<Field name="user" :rules="userName" />

      <!-- 错误信息 -->
      <ErrorMessage name="user" style="color: red;" />
    </div>

    <div>
      密码：<Field name="pass" :rules="passWord" />

      <!-- 错误信息 -->
      <ErrorMessage name="pass" style="color: red;" type="password"/>
    </div>
  </Form>
</template>
```

**注意：** `Field` 与 `ErrorMessage` 的 `name` 属性必须一致才能起到绑定作用，当表单 `Field` 的内容校验失败时就会在`ErrorMessage` 中显示错误信息



## 表单级验证

```vue
<script setup lang='ts'>
import { Form, Field, ErrorMessage } from 'vee-validate';

// 校验用户名
const Validate = {
  username(value: string) {
    if (!value) return "用户名不能为空"

    // 约束用户名6~16字符
    const regex = /^[a-zA-Z0-9]{6,16}$/

    if (!regex.test(value)) return "用户名必须是6~16个字符"

    // 校验成功
    return true
  },
  password(value: string) {
    if (!value) return "密码不能为空"

    // 约束用户名6~16字符
    const regex = /^[a-zA-Z0-9]{6,16}$/

    if (!regex.test(value)) return "密码必须是6~16个字符"

    // 校验成功
    return true
  },
}
</script>

<template>
  <Form :validation-schema="Validate">
    <div>
      <Field type="text" name="username" />
      <ErrorMessage name="username" />
    </div>

    <div>
      <Field type="password" name="password" />
      <ErrorMessage name="password" />
    </div>

    <div><button>提交</button></div>
  </Form>
</template>
```



使用 `yup` 完成表单级校验

```vue
<script setup lang="ts">
import { Field, Form, ErrorMessage } from 'vee-validate';
import { reactive } from 'vue';
import * as yup from 'yup'

const CommentSchema = yup.object({
  name: yup.string().required("名称不能为空"),
  // 括号中不写 会显示默认的错误信息
  email: yup.string().required().email("请输入正确的邮箱"),
  url: yup.string().url("请输入正确的网站地址"),
})

// 收集评论框的内容
const commentInfo = reactive({
  name: "",
  email: "",
  url: ""
})

// 提交表单数据
const post = () => {
  // 在提交之前先校验一下
  // abortEarly默认为true，代表只要有一个校验失败，剩余的就不再校验了，可以设置为false，这样可以看到所有校验失败的信息
  CommentSchema.validate(commentInfo, { abortEarly: false }).then(value => {
    console.log(value);
  }).catch(error => {
    console.log(error);
  })
}

</script>

<template>
  <Form @submit="post" :validation-schema="CommentSchema">
    <div>
      名称：
      <Field name="name" v-model="commentInfo.name" />

      <!-- 错误提示 -->
      <ErrorMessage name="name" style="color: red;" type="text" />
    </div>

    <div>
      邮箱：
      <Field name="email" type="email" v-model="commentInfo.email" />

      <ErrorMessage name="email" style="color: red;" />
    </div>

    <div>
      网站：
      <Field name="url" type="text" v-model="commentInfo.url" />

      <ErrorMessage name="url" style="color: red;" />
    </div>

    <button>提交</button>
  </Form>
</template>
```



### 

