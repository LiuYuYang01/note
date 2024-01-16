# Ant Design Vue

## Table

```vue
<script setup lang="ts">
import { ref } from 'vue'
import http from '@/utils/Request'

// 表格数据
const orderList = ref([
  {
    oid: 1,
    price: 3999,
    userId: 2,
    user: {
      uid: 1,
      uname: "zs",
      age: 20
    }
  }
])

// 配置每一列的属性
const columns = [
  {
    title: '订单ID', // 列的标题
    key: "oid", // 对应接口中的数据
    dataIndex: 'oid', // 列对应的数据
    align: "center", // 列的对齐方式
    width: 250, // 当前列的宽度
  },
  {
    title: '订单价格',
    key: "price",
    dataIndex: 'price',
  },
  {
    title: '用户名称',
    key: "user",
    dataIndex: 'user',
    customRender: data => data.value.uname
  },
  {
    title: '用户年龄',
    key: "age",
    dataIndex: 'age',
    customRender: ({text, record, index, column}) => {
      console.log(text, record, index, column);
      
      return text.age
    }
  },
  {
    // 设置当前列的宽度
    width: 250,
    title: "操作",
    key: "operation",
    dataIndex: "operation"
  }
]
</script>

<template>
  <a-table rowKey="oid" :dataSource="orderList" :columns="columns">
      
    <template #bodyCell="{ column, text, record }">
      <a-space style="width: 100%; display: flex; justify-content: center;" v-if="column.dataIndex === 'operation'">
        <a-button type="primary" block>编辑</a-button>
        <a-button type="primary" danger>删除</a-button>
      </a-space>
    </template>

  </a-table>
</template>
```



### rowKey

**特别注意**

在 `Table` 中，`dataSource` 和 `columns` 里的数据值都需要指定 `key` 值。对于 `dataSource` 默认将每列数据的 `key` 属性作为唯一的标识。

如果你的数据没有这个属性，务必使用 `rowKey` 来指定数据列的主键。若没有指定，控制台会出现缺少 `key` 的提示，表格组件也会出现各类奇怪的错误。

```html
// 比如你的数据主键是 uid
<Table rowKey="uid" />;

// 或
<Table rowKey={record => record.uid} />;
```



### rowSelection

### selectedRowKeys

指定选中项的 `key` 数组，需要和 `onChange` 进行配合



#### onChange

按钮选中项发生变化时的回调，用于获取按钮选中项



#### columnWidth

当 `columns` 中没有设置列的 `width` 时，则会采取 `columnWidth: 100` 的宽度



### pagination

虽然表格默认有分页，但如果我们想要对分页进行定制，可以这么做

```html
<a-table :columns="columns" :data-source="data" :pagination="pagination"  @change="paginationChange"></a-table>
```

```java
// 分页配置
const pagination = ref({
  current: 1, // 第几页
  defaultPageSize: 5, // 每页显示多少个
  total: 11, // 一共多少页
  showTotal: () => `共 ${11} 条`
})

// 监听页码变化
const paginationChange = (e: { current: number, pageSize: number, defaultPageSize: number, total: number }) => pagination.value.current = e.current
```

如果不想使用表格自带的分页组件，可以通过 `:pagination="flase"` 关闭



### customRender

当遇到复杂数据时可以使用它来渲染，参数分别为当前行的值，当前行数据，行索引

```javascript
customRender: ({text, record, index, column}) => {
	console.log(text, record, index, column);
      
	return text.age
}
```

当前的数据：text

当前行的对象：record

当前数据的索引：index

当前列的信息，如标题：column



### 事件

#### change

监听分页、排序、筛选变化时触发



## a-pagination

```vue
<!-- 分页查询 -->
    <a-pagination v-model:current="pagination.current" :total="pagination.total" :pageSize="pagination.defaultPageSize"
      :pageSizeOptions="['2', '5', '10', '50', '100']" :show-total="(total: number) => `共 ${total} 条`" showQuickJumper showSizeChanger @change="paginationChange" style="display: flex; justify-content: end;margin-top: 20px;" />
```



### pageSize

设置默认每页显示多少个数据

```
:pageSize="pagination.defaultPageSize"
```



### show-total

设置分页左侧的自定义内容

```
:show-total="(total: number) => `共 ${total} 条`"
```



### showQuickJumper

开启每页显示多少个的选项卡



### showSizeChanger

默认情况下只有当数据量大于每页显示的条目数量时，选择每页显示数量的选项框才会显示出来。如果你需要一直显示该选项框，可以设置 `show-size-changer` 属性为 `true`，无论当前数据量大小都会显示出来。



### pageSizeOptions

设置每页显示多少个的选项

```
:pageSizeOptions="['2', '5', '10', '50', '100']"
```



## Select

```vue
<script setup lang="ts">
import { ref } from 'vue';
import type { SelectProps } from 'ant-design-vue';

const options = ref<SelectProps['options']>([
  { value: 'jack', label: 'Jack' },
  { value: 'lucy', label: 'Lucy' },
  { value: 'tom', label: 'Tom' },
]);

// 获取被选则的数据
const handleChange = (value: string) => {
  console.log(`selected ${value}`);
};

// 点击选择框触发
const handleBlur = () => {
  console.log('blur');
};

// 离开选择框触发
const handleFocus = () => {
  console.log('focus');
};

// 对数据进行过滤
const filterOption = (input: string, option: any) => {
  // 将数据全部过滤为大写 展示在选择框
  return option.value.toLowerCase()
};

// 选择的数据
const value = ref<string | undefined>(undefined);
</script>

<template>
  <a-select v-model:value="value" show-search placeholder="Select a person" style="width: 200px" :options="options"
    :filter-option="filterOption" @focus="handleFocus" @blur="handleBlur" @change="handleChange"></a-select>
</template>
```



### field-names

`a-select` 默认格式为：

```javascript
{ value: 'jack', label: 'Jack' }
```



而我们的接口数据为

```javascript
{ name: 'jack', id: 3 }
```



可以发现数据的属性不匹配，这时候就需要使用 `field-names` 属性来解决这个问题：

```javascript
:field-names="{ value: 'id', label: 'name' }"
```



### option-label-prop

`option-label-prop` 用于将指定属性回填到选择框，如果不写就会导致将 `<a-select-option></a-select-option>` 标签中的所有数据都回填到选择框中。



比如下述代码中有两个 `span` 分别是 `name` 与 `type` 的数据，默认情况下这俩数据都会被回显到选择框中，而我们只想回显 `name` 数据，就需要用到这个属性了

```vue
<a-select v-model:value="teacherIds" mode="multiple" placeholder="请选择老师" option-label-prop="label" style="width: 100%">
    
	<a-select-option v-for=" item  in  teacherList " :key="item.tid" :label="item.name" :value="item.tid" :disabled="item.disabled">
		<div style="display: flex; justify-content: space-between; padding: 0 20px;">
			<span>{{ item.name }}</span>

            <span>{{ item.type}}</span>
        </div>
    </a-select-option>
    
</a-select>
```

在 `a-select` 标签上指定 `option-label-prop="label"` ，用于指定回显的属性 `label` ，然后在 `a-select-option` 标签上指定 `:label="item.name"` 即可



## Menu

用于页面之间的跳转

```vue
<script setup lang="ts">
import { h, ref } from 'vue';
import { HomeOutlined, TagsOutlined, UsergroupDeleteOutlined, MenuFoldOutlined } from '@ant-design/icons-vue';
import type { MenuProps } from 'ant-design-vue';
import { RouterLink } from 'vue-router'

// 侧边栏菜单配置
const items = ref<MenuProps['items']>([
  {
    key: '1',
    icon: () => h(HomeOutlined),
    // label: h('a', { href: '/' }, '首页'),
    label: h(RouterLink, { to: '/' }, () => '首页'),
    title: 'home',
  },
  {
    key: '2',
    icon: () => h(TagsOutlined),
    label: '班级管理',
    title: '班级管理',
    children: [
      {
        label: h(RouterLink, { to: '/class' }, () => '班级列表'),
        key: '3',
      },
      {
        label: h(RouterLink, { to: '/class/add' }, () => '新增班级'),
        key: '4',
      },
    ]
  },
  {
    key: '5',
    icon: () => h(UsergroupDeleteOutlined),
    label: '学生管理',
    title: '学生管理',
    children: [
      {
        label: h(RouterLink, { to: '/student' }, () => '学生列表'),
        key: '6',
      },
      {
        label: h(RouterLink, { to: '/student/add' }, () => '新增学生'),
        key: '7',
      },
    ]
  }
]);
</script>

<template>
    <a-menu mode="inline" :items="items" :selectedKeys="['1']" :openKeys="['2']" theme="dark" style="width: 230px"></a-menu>
</template>
```



### mode

导航排列方式，分别为垂直（vertical）、水平（horizontal）、内嵌（inline）三种方式



### theme

导航的主题样式，默认为高亮（light），暗黑（dark）



### selectedKeys

默认选中哪个菜单导航，以字符串数组形式



### openKeys

默认展开哪个多级菜单，以字符串数组形式



### 注意事项

```javascript
label: h('a', { href: '/' }, '首页'), // 直接通过页面跳转
label: h(RouterLink, { to: '/' }, '首页'), // 通过路由跳转
```

