import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'
import ProductManager from '../views/ProductManager.vue'
import Add from '../views/Add.vue'
import Update from '../views/Update.vue'
import Bar from '../views/Bar.vue'
import Pie from '../views/Pie.vue'
import Excel from '../views/Excel.vue'
import Export from '../views/Export'
Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView,
    redirect: '/productManager',
    children:[
      {
        path:'/productManager',
        name:'商品管理',
        component:ProductManager
      },
      {
        path:'/add',
        name:'商品添加',
        component:Add
      },
      {
        path:'/update',
        name:'商品更新',
        component:Update
      },
      {
        path:'/bar',
        name:'柱状图',
        component:Bar
      },
      {
        path:'/pie',
        name:'饼状图',
        component:Pie
      },
      {
        path:'/excel',
        name:'excel图表',
        component:Excel
      },
      {
        path:'/export',
        name:'导出数据',
        component:Export
      }
    ]
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
