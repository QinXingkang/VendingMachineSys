<template>
  <el-card style="width: 500px; margin: auto; margin-top: 40px">
    <h2>欢迎使用售货机</h2>
    <el-form @submit.prevent="submitOrder">
      <el-form-item label="请选择饮料">
        <el-radio-group v-model="form.beverage">
          <el-radio label="Coca">Coca</el-radio>
          <el-radio label="Coffee">Coffee</el-radio>
        </el-radio-group>
      </el-form-item>

      <el-form-item label="请选择配料">
        <el-checkbox-group v-model="form.decorators">
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
      <p>总价：￥{{ result.cost }}</p>
    </div>
  </el-card>
</template>

<script setup>
import { ref } from 'vue'
import axios from 'axios'

const form = ref({
  beverage: '',
  decorators: []
})

const result = ref({})

const submitOrder = async () => {
  try {
    const res = await axios.post('http://localhost:9090/order', {
      beverage: form.value.beverage,
      decorators: form.value.decorators
    })
    result.value = res.data
  } catch (err) {
    alert('提交失败')
  }
}
</script>
