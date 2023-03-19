import { createRouter, createWebHashHistory, RouteRecordRaw } from 'vue-router'

const routes: RouteRecordRaw[] = [
  {
      path: "/",
      name: "login",
      component: ()=>import("../views/login/Login.vue")
  },
]

const router = createRouter({
  history: createWebHashHistory(),
  routes,
  scrollBehavior () {
      return {top: 0}
  }
})

router.beforeEach((to, from, next) => {
  next()
})

router.afterEach((to, from, failure) => {
  
})

export { router }