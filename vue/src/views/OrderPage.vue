<template>
  <div class="main-content">
    <el-card style="width: 500px; margin: auto; margin-top: 40px">
      <h2>欢迎使用售货机</h2>
      <el-form @submit.prevent="submitOrder">
        <el-form-item label="请选择饮料">
          <el-radio-group v-model="selectedBeverage">
            <el-radio label="Coca">Coca</el-radio>
            <el-radio label="Coffee">Coffee</el-radio>
          </el-radio-group>
        </el-form-item>

        <el-form-item label="请选择配料">
          <el-checkbox-group v-model="selectedDecorators">
            <el-checkbox label="Milk">Milk</el-checkbox>
            <el-checkbox label="Ice">Ice</el-checkbox>
          </el-checkbox-group>
        </el-form-item>

        <el-button type="primary" @click="submitOrder">提交订单</el-button>
      </el-form>

      <div v-if="result.description">
        <hr />
        <h3>订单详情：</h3>
        <p>饮料描述：{{ result.description }}</p>
        <p>总价：￥{{ result.totalCost.toFixed(2) }}</p>
      </div>
    </el-card>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import axios from 'axios'

const selectedBeverage = ref('')
const selectedDecorators = ref([])
const orderResult = ref(null)

const result = ref({})

const submitOrder = async () => {
  if (!selectedBeverage.value) {
    alert('请选择饮料')
    return
  }
  const payload = {
    beverage: selectedBeverage.value,
    decorators: selectedDecorators.value
  }
  try {
    const res = await axios.post('http://localhost:9090/order', payload)
    orderResult.value = res.data
    result.value = res.data
    ElMessage.success('订单提交成功！')
  } catch (err) {
    alert('订单提交失败：' + err.response?.data?.message || err.message)
  }
}
</script>

<style scoped>
.main-content {
  width: 600px;
  margin: 20px auto;
}
</style>
