import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {path: '/order', component: () => import('@/views/OrderPage.vue'), },
  ]
})

export default router
