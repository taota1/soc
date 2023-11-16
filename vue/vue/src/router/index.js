import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'
import LoginView from "@/views/LoginView.vue"
import Layout from "@/views/Layout.vue"
import Register from "@/views/Register.vue";

Vue.use(VueRouter)

const routes = [

  {
    path: '/login',
    name: 'login',
    component: LoginView
  },
  {
    path: '/register',
    name: 'register',
    component: Register
  },
  {
    path: '/',
    name: 'layout',
    component: Layout,
    children:[
      {
        path: '',
        name: 'home',
        component: HomeView
      },

      {
        path: 'adminuser',
        name: 'adminuser',
        component: () => import('../views/adminuser.vue')
      },

      {
        path: 'user',
        name: 'user',
        component: () => import('../views/user.vue')
      },
      {
        path: 'srclist',
        name: 'srclist',
        component: () => import('../views/SRCList.vue')
      },
      {
        path: 'bugBounty',
        name: 'bugBounty',
        component: () => import('../views/bugBounty.vue')
      },
      {
        path: 'books',
        name: 'books',
        component: () => import('../views/books.vue')
      },

      {
        path: 'booktype',
        name: 'booktype',
        component: () => import('../views/booktype.vue')
      },
      {
        path: 'gitcode',
        name: 'gitcode',
        component: () => import('../views/gitcode.vue')
      },
    ]

  },


]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

// 路由守卫
router.beforeEach((to ,from, next) => {
  if (to.path ==='/login') {
    next();
  }
  const user = localStorage.getItem("user");
  if (!user && to.path !== '/login') {
    return next("/login");
  }
  next();
})

export default router
