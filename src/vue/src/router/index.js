import Vue from 'vue'
import Router from 'vue-router'
import ShowNavCenter from '@/components/main/ShowNavCenter'
import Login from '@/components/main/Login'
import NavShow from '@/components/navMain/navShow'
import imgShow from '@/components/navMain/imgShow'
import Message from '@/components/my/myMessage'
import searchAll from '@/components/search/searchMain'
import navHeader from '@/components/navMain/navHeader'
import searchShow from '@/components/search/searchShow'
import searchName from '@/components/search/searchName'
import messageNav from '@/components/message/messageNav'
import myconacter from '@/components/message/myconacter'
import getMyMesage from '@/components/message/getMyMesage'
import myMesage from '@/components/message/myMesage'
import showHuan from '@/components/navMain/showHuan'
import showMap from '@/components/navMain/showMap'
import showYuan from '@/components/navMain/showYuan'
import showPai from '@/components/navMain/showPai'
import showZhe from '@/components/navMain/showZhe'
import imgTab from '@/components/navMain/imgTab'
import test from '@/components/test/test'
import talk from '@/components/main/talk'
import baiduMap from '@/components/navMain/baiduMap'
import supplierAllBack from '@/components/navMain/supplierAllBack'
import NotFound from '@/components/main/404.vue'

Vue.use(Router);


export default new Router({
  mode: 'history',
  base:'/dist/',
  routes: [
    {
      path: '/home/supplierAllBack',
      name: 'supplierAllBack',
      component: supplierAllBack
    },
    {
      path: '/baiduMap',
      name: 'baiduMap',
      component: baiduMap
    },
    {
      path: '/talk',
      name: 'talk',
      component: talk
    },
    {
      path: '/test',
      name: 'test',
      component: test
    },
    {
      path: '/reg',
      name: 'ShowNavCenter',
      component: ShowNavCenter
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/navMain/navShow',
      name: 'NavShow',
      component: NavShow
    },
    {
      path: '*',
      name: 'NavHeader',
      component: navHeader
    },
    {
      path: '/',
      name: 'NavHeader',
      component: navHeader
    },
    {
      path: '/my/myMessage',
      name: 'Message',
      component: Message
    },{
      path: '/navMain/navHeader',
      name: 'navHeader',
      component: navHeader
    },{
      path: '/navMain/imgShow',
      name: 'imgShow',
      component: imgShow
    },{
      path: '/navMain/imgTab',
      name: 'imgTab',
      component: imgTab
    },{
      path: '/search/searchMain',
      name: 'searchAll',
      component: searchAll
    },{
      path: '/search/searchShow',
      name: 'searchShow',
      component: searchShow
    },{
      path: '/search/searchName',
      name: 'searchName',
      component: searchName
    },{
      path: '/search/showHuan',
      name: 'showHuan',
      component: showHuan
    },{
      path: '/search/showYuan',
      name: 'showYuan',
      component: showYuan
    },{
      path: '/search/showPai',
      name: 'showPai',
      component: showPai
    },{
      path: '/search/showZhe',
      name: 'showZhe',
      component: showZhe
    },{
      path: '/search/showMap',
      name: 'showMap',
      component: showMap
    },{
        path: '/getMyMesage',
        name: 'getMyMesage',
        component: getMyMesage,
      },{
          path: '/myMesage',
          name: 'myMesage',
          component: myMesage,
      },{
        path: '/myconacter',
        name: 'myconacter',
        component:myconacter,
    },
    {
      path: '#',
      name: 'NavShow',
      component: NavShow
    },
  ]
})
