import { createRouter, createWebHistory } from 'vue-router'
import OrderHistory from '@/views/OrderHistory.vue'
import OrderPage from '@/views/OrderPage.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {path: '/', redirect: '/order/front'},

    {
      path: '/order',
      component: () => import('@/views/Layout.vue'),
      children: [
        {path: 'history', name: 'orderHistory', component: () => import('@/views/OrderHistory.vue')},
        {path: 'front',name :'orderFront',component: () =>import('@/views/Front.vue') },
      ]
    }
  ]
})

export default router
