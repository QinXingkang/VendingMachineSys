<template>
  <el-container>
    <el-header>
      <el-tabs
          v-model="activeTab"
          type="card"
          class="custom-tabs"
          @tab-click="handleTabClick"
      >
        <el-tab-pane label="售货页面" name="orderFront" />
        <el-tab-pane label="订单页面" name="orderHistory" />
      </el-tabs>
    </el-header>
    <el-main>
      <router-view :key="route.fullPath" />
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
      .then(() => {
    if (tab.props.name === 'orderFront') {
      // 强制刷新整个页面（浏览器刷新）
      window.location.reload()
    }
  })
}

// 当 URL 改变时自动更新 activeTab
watch(() => route.name, (newName) => {
  activeTab.value = newName
})

</script>

<style scoped>

</style>