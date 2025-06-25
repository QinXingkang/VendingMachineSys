<template>
  <div class="common-layout">
    <el-container>
      <el-card class="section-card button-card" shadow="hover">
        <div class="welcome-text">欢迎使用售货机</div>
        <!-- 通知框 -->
        <el-alert
            title="今日饮品及配料价格表"
            type="info"
            show-icon
            :closable="false"
            style="margin-top: 10px;"
        />
        <div class="card" style="margin-top: 10px">
          <el-table stripe :data="data.beverageTable">
            <el-table-column prop="beverageName" label="饮品名称" />
            <el-table-column prop="beveragePrice" label="饮品价格" />
          </el-table>
          <el-table stripe :data="data.decoratorTable ">
            <el-table-column prop="decoratorName" label="配料名称" />
            <el-table-column prop="decoratorPrice" label="配料价格" />
          </el-table>
        </div>
      </el-card>


      <el-main >
        <!-- 选择饮料和配料 -->
        <el-card class="section-card form-card" shadow="hover">
          <el-form @submit.prevent="submitOrder">
            <el-form-item label="请选择饮料">
              <el-radio-group v-model="selectedBeverage">
                <el-radio label="Coca" >
                  <span class="radio-label-text">Coca</span>
                </el-radio>
                <el-radio label="Coffee" >
                  <span class="radio-label-text">Coffee</span>
                </el-radio>
              </el-radio-group>
            </el-form-item>

            <el-form-item label="请选择配料及份数">
              <div class="decorator-list">
                <div
                    v-for="decorator in decoratorsList"
                    :key="decorator"
                    class="decorator-item"
                >
                  <el-checkbox
                      v-model="selectedDecorators"
                      :label="decorator"
                  >
                    {{ decorator }}
                  </el-checkbox>
                  <el-input-number
                      v-model="decoratorCounts[decorator]"
                      :min="1"
                      :disabled="!selectedDecorators.includes(decorator)"
                      size="small"
                      class="quantity-input"
                  />
                </div>
              </div>
            </el-form-item>

          </el-form>
        </el-card>

        <!-- 提交按钮 -->
        <el-card class="section-card button-card" shadow="hover">
          <el-button type="primary" @click="submitOrder" style="width: 100%;">提交订单</el-button>
        </el-card>

        <!-- 弹出订单结果对话框 -->
        <el-dialog v-model="dialogVisible" title="订单详情" width="400px" center>
          <template #default>
            <p>饮料描述：{{ result.description }}</p>
            <p>总价：￥{{ result.totalCost.toFixed(2) }}</p>
          </template>
          <template #footer>
            <el-button type="primary" @click="dialogVisible = false">我知道了</el-button>
          </template>
        </el-dialog>
      </el-main>
    </el-container>


  </div>
</template>

<script setup>
import { ref , reactive, onMounted} from 'vue'

const data = reactive({
  formVisible: false,
  tableData: [],
  decoratorTable: []
})
const load = () => {
  axios.get('http://localhost:9090/message/detail')
      .then(res => {
        if (res.data.code === 200) {
          const responseData = res.data.data

          // 处理饮品数据
          const beverageList = responseData?.beverages || []
          data.beverageTable = beverageList.map(item => ({
            beverageName: item.name,
            beveragePrice: item.price,
          }))

          // 处理配料数据
          const decoratorList = responseData?.decorators || []
          data.decoratorTable = decoratorList.map(item => ({
            decoratorName: item.name,
            decoratorPrice: item.price,
          }))
        } else {
          ElMessage.error(res.data.msg || '加载失败')
        }
      })
      .catch(err => {
        console.error('加载饮品失败', err)
      })
}
onMounted(() => {
  load()
})


import { useRoute } from 'vue-router'
const route = useRoute()

import axios, {Axios} from 'axios'
import { ElMessage } from 'element-plus'

const selectedBeverage = ref('')
const selectedDecorators = ref([])
const result = ref({})
const dialogVisible = ref(false) // 控制对话框显示



const decoratorsList = ['Milk', 'Ice']
const decoratorCounts = ref({
  Milk: 1,
  Ice: 1
})

const submitOrder = async () => {
  if (!selectedBeverage.value) {
    ElMessage.warning('请选择饮料')
    return
  }

  const payload = {
    beverage: selectedBeverage.value,
    decorators: {}
  }

  selectedDecorators.value.forEach((name) => {
    payload.decorators[name] = decoratorCounts.value[name]
  })

  try {
    const res = await axios.post('http://localhost:9090/order', payload)
    if (res.data.code === 200) {
      result.value = res.data.data
      dialogVisible.value = true // 显示弹窗
      ElMessage.success('订单提交成功！')
    } else {
      ElMessage.error(res.data.message || '订单提交失败')
    }
  } catch (err) {
    ElMessage.error('请求失败：' + (err.response?.data?.message || err.message))
  }
}


</script>

<style scoped>

.welcome-text {
  text-align: center;
  font-size: 28px;
  font-weight: 700;
  font-family: '微软雅黑', 'Microsoft YaHei', Arial, sans-serif;
  color: #1890ff;
  letter-spacing: 2px;
  user-select: none;
}

.common-layout {
  background-color: #e6f7ff;
  min-height: 100vh;
  padding: 20px 0;
}

.decorator-item {
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 20px;
  margin-bottom: 10px;
}

.quantity-input {
  margin-left: auto;
  margin-right: 10px;
  width: 100px;
}

.radio-with-img {
  display: flex;
  align-items: center;
}

.radio-label-text {
  font-size: 18px; /* 字体大小，可调整 */
  font-weight: 500; /* 字体加粗（可选） */
}

.section-card {
  padding: 100px;
  border-radius: 8px;
  background-color: #c7daed;
}

.button-card {
  display: flex;
  justify-content: center;
  background-color: #c7daed;
}

</style>
