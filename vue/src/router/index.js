import { createRouter, createWebHistory } from 'vue-router'
import OrderHistory from '@/views/OrderHistory.vue'
import OrderPage from '@/views/OrderPage.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {path: '/', redirect: '/order/orderPage'},

    {
      path: '/order',
      component: () => import('@/views/Layout.vue'),
      children: [
        {path: 'orderPage', name: 'orderPage', component: () => import('@/views/OrderPage.vue')},
        {path: 'history', name: 'orderHistory', component: () => import('@/views/OrderHistory.vue')
        }
      ]
    }
  ]
})

export default router
