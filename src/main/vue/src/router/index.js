import Vue from 'vue'
import Router from 'vue-router'
import subject from '@/components/subject'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'subject',
      component: subject
    }
  ]
})
