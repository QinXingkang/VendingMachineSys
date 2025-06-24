import { createRouter, createWebHistory } from 'vue-router'
import OrderHistory from '@/views/OrderHistory.vue'
import OrderPage from '@/views/OrderPage.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {path: '/order', component: () => import('@/views/OrderPage.vue'),},
    {path: '/history',component:() => import('@/views/OrderHistory.vue'),},
  ]
})

export default router
