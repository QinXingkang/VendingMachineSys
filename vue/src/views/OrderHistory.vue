<template>
  <el-container>
    <el-header>
      <h2>历史订单</h2>
    </el-header>


    <el-main>
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
    </el-main>

  </el-container>
</template>

<script setup>
import axios from 'axios'
import { ElMessage } from 'element-plus'

import { ref, onMounted } from 'vue'

const orders = ref([])

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

onMounted(() => {
  fetchOrders()
})
</script>
