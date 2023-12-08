# HarmonyOS

## 封装

### 组件封装

将代码抽离出来进行组件封装

```ts
// 核心代码：登录组件
@Component
struct Login {
  @State username: string = ""
  @State password: string = ""

  build() {
    Column() {
      Row() {
        Text("账号：").width("20%")
        TextInput({ text: this.username, placeholder: "请输入账号" })
          .width("80%").onChange((value: string) => this.username = value)
      }

      Row() {
        Text("密码：").width("20%")
        TextInput({ text: this.password, placeholder: "请输入密码" })
          .width("80%")
          .type(InputType.Password)
          .onChange((value: string) => this.password = value)
      }.margin({ top: 10 })

      Row() {
        Button("重置", {}).onClick(() => {
          this.username = ""
          this.password = ""
        }).margin({ right: 10 }).backgroundColor("#777")

        Button("登录").onClick((event: ClickEvent) => {
          if (this.username !== "admin" || this.password !== "123123") {
            console.log("账号或密码错误，请重新输入")
          }
        })
      }.margin({ top: 10 })
    }.padding(20)
  }
}

// 首页
@Entry
@Component
struct Index {
  @Styles func(){
    .width("100%")
    .backgroundColor("#357bfc")
  }

  build() {
    Column() {
      // 核心代码：使用登录组件
      Login()
    }
  }
}
```



当然也可以把组件单独抽离出来，使用时候手动引入

通过 `export` 将组件导出，也可以使用 `export default` 默认导出

```ts
// pages/Login.ets
// 核心代码：导出组件
@Component
export default struct Login {
  @State username: string = ""
  @State password: string = ""

  build() {
    Column() {
      Row() {
        Text("账号：").width("20%")
        TextInput({ text: this.username, placeholder: "请输入账号" })
          .width("80%").onChange((value: string) => this.username = value)
      }

      Row() {
        Text("密码：").width("20%")
        TextInput({ text: this.password, placeholder: "请输入密码" })
          .width("80%")
          .type(InputType.Password)
          .onChange((value: string) => this.password = value)
      }.margin({ top: 10 })

      Row() {
        Button("重置", {}).onClick(() => {
          this.username = ""
          this.password = ""
        }).margin({ right: 10 }).backgroundColor("#777")

        Button("登录").onClick((event: ClickEvent) => {
          if(this.username !== "admin" || this.password !== "123123"){
            console.log("账号或密码错误，请重新输入")
          }
        })
      }.margin({ top: 10 })
    }.padding(20)
  }
}
```



引入并使用组件

```ts
// pages/index.ets
// 核心代码：引入组件
import Login from '../components/Login'

@Entry
@Component
struct Index {
  build() {
    Column() {
      // 核心代码：使用登录组件
      Login()
    }
  }
}
```



### 样式封装





### 结构封装
