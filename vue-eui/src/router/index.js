import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'
import PageOne from "@/views/PageOne";
import PageTwo from "@/views/PageTwo";

import PageThree from "@/views/PageThree";

import Index from "@/views/Index";
Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: '用户管理',
    component: Index,
    children:[
      {
        path: '/pageOne',
        name: '查询用户',
        component: PageOne
      },
      {
        path: '/pageTwo',
        name: '添加用户',
        component: PageTwo
      },
      {
        path: '/pageThree',
        name: '修改用户',
        component: PageThree
      }
    ]
  }


]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
