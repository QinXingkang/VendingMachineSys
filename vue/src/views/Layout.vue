<template>
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
    <el-main>
      <router-view /> <!-- 子页面内容将自动插入这里 -->
    </el-main>
  </el-container>
</template>

<script setup>
import { useRouter, useRoute } from 'vue-router'
import { ref, watch } from 'vue'

const router = useRouter()
const route = useRoute()
const activeTab = ref(route.name)

const handleTabClick = (tab) => {
  router.push({ name: tab.props.name })
}

// 当 URL 改变时自动更新 activeTab
watch(() => route.name, (newName) => {
  activeTab.value = newName
})
</script>

<style scope>

</style>