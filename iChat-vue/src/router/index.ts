import { createRouter, createWebHashHistory, RouteRecordRaw } from 'vue-router'

const routes: RouteRecordRaw[] = [{
    path: "/",
    name: "login",
    component: ()=>import("../views/login/Login.vue")
  },{
    path: "/register",
    name: "register",
    component: ()=>import("../views/register/Register.vue")
  },{
    path: "/chat",
    name: "chat",
    component: ()=>import("../views/chat/Chat.vue")
  },{
    path: "/contacts",
    name: "contacts",
    component: ()=>import("../views/contacts/Contacts.vue")
  },{
    path: "/chat/gpt",
    name: "chatgpt",
    component: ()=>import("../views/chatgpt/ChatGpt.vue")
  }
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