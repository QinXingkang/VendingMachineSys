<template>
    <h2>历史订单</h2>
  <el-card class="section-card">

    <el-table :data="orders" stripe style="width: 100%;">
      <el-table-column prop="id" label="订单编号" width="100"/>
      <el-table-column prop="beverage.name" label="饮料名称"/>
      <el-table-column prop="totalCost" label="总价 (¥)"/>
      <el-table-column label="配料">
        <template #default="{ row }">
          <span v-for="d in row.decoratorDetails" :key="d.id">
            {{ d.decorator.name }} x{{ d.quantity }}<br/>
          </span>
        </template>
      </el-table-column>
      <el-table-column prop="description" label="描述"/>
    </el-table>
  </el-card>

</template>

<script setup>
//请求后端接口
import axios from 'axios'
//弹出消息提示
import { ElMessage } from 'element-plus'
//管理响应式数据和生命周期
import { ref, onMounted } from 'vue'
import { useRoute } from 'vue-router'
const route = useRoute()

//保存历史订单数据
const orders = ref([])
//用于请求后端数据
const fetchOrders = async () => {
  try {
    const res = await axios.get('http://localhost:9090/order/history')
    if (res.data.code === 200) {
      orders.value = res.data.data
    } else {
      ElMessage.error(res.data.message || '加载失败')
    }
  } catch (err) {
    ElMessage.error('请求失败：' + (err.response?.data?.message || err.message))
  }
}

//页面加载时请求历史订单
onMounted(() => {
  fetchOrders()
})

</script>
