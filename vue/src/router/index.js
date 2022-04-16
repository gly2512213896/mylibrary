import { createRouter, createWebHistory } from 'vue-router'
import Layout from '../layout/Layout.vue'

const routes = [
  {
    path: '/',
    name: 'Layout',
    redirect:'/borrow',
    component: Layout,
    children:[
      {
        path: '/borrow',
        name: 'Borrow',
        component:()=>import("../views/Borrow"),
      },
      {
        path: '/book',
        name: 'Book',
        component:()=>import("../views/Book"),
      },
      {
        path: '/user',
        name: 'User',
        component:()=>import("../views/User"),
      },
      {
        path: '/admin',
        name: 'Admin',
        component:()=>import("../views/Admin"),
      },

    ]
  },
  {
    path: '/login',
    name: 'Login',
    component: ()=>import("../views/Login")
  },
  {
    path: '/register',
    name: 'Register',
    component: ()=>import("../views/Register")
  },
  {
    path: '/empty',
    name: 'Empty',
    component:()=>import("../utils/Empty"),
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
