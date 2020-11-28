import Vue from 'vue'
import Router from 'vue-router'
import Dictionary from '@/components/Dictionary'
import All from '@/components/All'
import Detail from '@/components/Detail'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: '',
      component: Dictionary,
      redirect: "/all",
      children: [
        {path: '/all', component: All, name: "All",hidden: false},
        {path: '/detail/:id', component: Detail, name: "Detail",hidden: true},

      ]
    },

  ]
})
