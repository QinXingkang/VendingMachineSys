<template>
  <div class="common-layout">
    <el-container>
      <el-header>

          <el-tabs
              v-model="activeTab"
              type="card"
              class="custom-tabs"
              @tab-click="handleTabClick"
          >
            <el-tab-pane label="售货页面" name="orderPage" />
            <el-tab-pane label="订单页面" name="orderHistory" />
          </el-tabs>

      </el-header>
      <el-main >

        <el-card class="section-card button-card" shadow="hover">
          <h2>欢迎使用售货机</h2>
        </el-card>

        <!-- 选择饮料和配料 -->
        <el-card class="section-card form-card" shadow="hover">
          <el-form @submit.prevent="submitOrder">
            <el-form-item label="请选择饮料">
              <el-radio-group v-model="selectedBeverage">
                <el-radio label="Coca" class="radio-with-img">
                  <span class="radio-label-text">Coca</span>
                </el-radio>
                <el-radio label="Coffee" class="radio-with-img">
                  <span class="radio-label-text">Coffee</span>
                </el-radio>
              </el-radio-group>
            </el-form-item>

            <el-form-item label="请选择配料及份数">
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
                    style="margin-left: 100px;"
                />
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
import { ref } from 'vue'

import axios from 'axios'
import { ElMessage } from 'element-plus'

import { useRouter } from 'vue-router'

const router = useRouter()

const handleTabClick = (tab) => {
  if (tab.props.name === 'orderPage') {
    router.push('/order')
  } else if (tab.props.name === 'orderHistory') {
    router.push('/history')
  }
}


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

.common-layout {
  background-color: #e6f7ff;
  min-height: 100vh;
  padding: 20px 0;
}

.header-content h2 {
  font-size: 32px;
  font-weight: bold;
  color: #333;
  margin: 0;
}

:deep(.el-tabs--card > .el-tabs__header .el-tabs__item) {
  border: none !important;         /* 移除每个 tab 的边框 */
  margin-right: 8px;               /* 控制标签间距 */
  background-color: #05efca;       /* 设置非激活状态颜色 */
  border-radius: 8px;
  background-color: #9ecaf6;

}

:deep(.el-tabs--card > .el-tabs__header .el-tabs__item.is-active) {
  background-color: #409EFF;       /* 激活状态背景色 */
  color: white;
}

:deep(.el-tabs--card > .el-tabs__header .el-tabs__nav) {
  border: none !important;         /* 移除整个 tab header 的边框 */
}



.decorator-item {
  display: flex;
  align-items: center;
  gap: 20px; /* 复选框与数字输入框间水平间距 */
}

.quantity-input {
  margin-left: 0 !important; /* 取消内联大间距 */
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

.decorator-item {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-bottom: 10px;
}

.quantity-input {
  margin-left: auto;
  margin-right: 10px;
  width: 100px;
}
</style>
