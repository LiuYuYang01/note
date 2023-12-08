<script setup lang="ts">
import { ref } from 'vue'
import { OrderForm } from '../types/Order'

import { FormInstance, message } from 'ant-design-vue';
const [messageApi, contextHolder] = message.useMessage();

import http from '@/utils/Request'

// 订单列表
const orderList = ref([])

// 表格配置
const columns = [
  {
    title: '订单ID',
    dataIndex: 'oid',
  },
  {
    title: '订单价格',
    dataIndex: 'price',
  },
  {
    title: '用户名称',
    dataIndex: 'user',
    customRender: (data: any) => data.text.uname
  },
  {
    width: 250,
    title: "操作",
    dataIndex: "operation"
  }
]

const loading = ref<boolean>(false)
const show = ref<boolean>(false)
const title = ref<string>("新增数据")

// 获取订单列表
const getOrderList = async () => {
  loading.value = true

  const { data } = await http.get("/order")

  orderList.value = data

  loading.value = false
}
getOrderList()

// 删除订单
const delOrderData = async (oid: string) => {
  const { status } = await http.delete(`/order/${oid}`)

  if (status != 200) return

  // 重新加载数据
  getOrderList()

  messageApi.success('删除成功')
}

const orderForm = ref<OrderForm>({
  price: 0,
  userId: 0,
  user: {
    uname: "",
  }
});

const formRef = ref<FormInstance>()

// 校验成功触发
const verify = async (values: any) => {
  let res1, res2;

  if (title.value == "新增数据") {
    res1 = await http.post('/order', values)
    res2 = await http.post('/user', { uname: values.user.uname })
  } else if (title.value == "编辑数据") {
    res1 = await http.patch('/order', { oid: orderForm.value.oid, price: orderForm.value.price, userId: orderForm.value.userId })
    res2 = await http.patch('/user', orderForm.value.user)
  }

  if (res1!.status != 200 && res2!.status != 200) return messageApi.success('操作失败')

  // 重新加载数据
  getOrderList()

  messageApi.success('操作成功')

  // 关闭弹框
  show.value = false

  // 重置数据
  formRef.value?.resetFields()
};

// 校验失败触发
const onFinishFailed = (errorInfo: any) => {
  console.log('Failed:', errorInfo);
};

// 编辑订单
const editOrderData = (data: any) => {
  orderForm.value = data

  show.value = !show.value;
  title.value = '编辑数据'
}
</script>

<template>
  <context-holder />

  <!-- 订单列表 -->
  <a-spin tip="Loading..." :spinning="loading">
    <a-table :dataSource="orderList" :columns="columns" :pagination="{ defaultPageSize: 5 }">
      <template #bodyCell="{ column, text, record }">
        <a-space style="width: 100%; display: flex; justify-content: center;" v-if="column.dataIndex === 'operation'">
          <a-button type="primary" block @click="editOrderData(record)">编辑</a-button>
          <a-button type="primary" danger @click="delOrderData(record.oid)">删除</a-button>
        </a-space>
      </template>
    </a-table>
  </a-spin>

  <a-button type="primary" style="width: 100%; height: 40px; margin-bottom: 20px;"
    @click="show = !show; title = '新增数据'">新增订单数据</a-button>

  <!-- 新增&编辑框 -->
  <a-drawer :title="title" placement="right" :closable="false" :open="show" :maskClosable="true" @close="show = !show">
    <a-form :model="orderForm" name="basic" :label-col="{ span: 8 }" :wrapper-col="{ span: 16 }" autocomplete="off"
      @finish="verify" @finishFailed="onFinishFailed" ref="formRef">
      <a-form-item label="用户ID" name="userId" :rules="[{ required: true, message: '请输入用户的id' }]">
        <a-input v-model:value="orderForm.userId" />
      </a-form-item>

      <a-form-item label="订单价格" name="price" :rules="[{ required: true, message: '请输入订单价格' }]">
        <a-input v-model:value="orderForm.price" />
      </a-form-item>

      <a-form-item label="用户名称" :name="['user', 'uname']" :rules="[{ required: true, message: '请输入用户的名称' }]">
        <a-input v-model:value="orderForm.user.uname" />
      </a-form-item>

      <a-form-item :wrapper-col="{ offset: 8, span: 16 }">
        <a-button type="primary" html-type="submit">{{ title }}</a-button>
      </a-form-item>
    </a-form>
  </a-drawer>
</template>

<style scoped lang="scss"></style>
