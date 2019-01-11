// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import Vuex from 'vuex'
import VueRouter from 'vue-router'
import VueLazyload from 'vue-lazyload'
import ElementUI from 'element-ui'
import axios from 'axios'
import qs from 'qs'
import 'element-ui/lib/theme-chalk/index.css'
import infiniteScroll from 'vue-infinite-scroll'
import AmazeVue from 'amaze-vue';
import 'amaze-vue/dist/amaze-vue.css';
import store from './store'
import Icon from 'vue-svg-icon/Icon.vue';
import  'babel-polyfill';
import 'url-search-params-polyfill';
// 引入echarts
import echarts from 'echarts'
import $ from 'jquery'
Vue.component('icon', Icon);

Vue.use(AmazeVue);
Vue.use(ElementUI)
Vue.use(Vuex)
Vue.use(VueLazyload, {
  preLoad: 1.3,
  error: require('./assets/images/err.png'),
  loading: require('./assets/images/loader.gif'),
  attempt: 1,
  listenEvents: ['scroll']
})
Vue.use(VueRouter)
Vue.use(infiniteScroll)
Vue.use(axios)
Vue.config.productionTip = false
//axios的一些配置，比如发送请求显示loading，请求回来loading消失之类的
//拦截器
// axios.interceptors.request.use(function(config) { //配置发送请求的信息
//   store.dispatch('showLoading');
//   return config;
// }, function(error) {
//   return Promise.reject(error);
// });
//
// axios.interceptors.response.use(function(response) { //配置请求回来的信息
//   store.dispatch('hideLoading');
//   return response;
// }, function(error) {
//   return Promise.reject(error);
// });
/*axios.defaults.baseURL = 'http://localhost:8080/linkjb/';*/
axios.defaults.baseURL = 'http://www.linkjb.com/linkjb/';
axios.defaults.headers['Content-Type'] = 'application/x-www-form-urlencoded'
Vue.prototype.$http = axios;
Vue.prototype.$qs = qs;
Vue.prototype.$echarts = echarts
/* eslint-disable no-new */
Vue.filter('BrowsingVolume',function(value){
  let d = new Date(value);
  let y=d.getFullYear(),
    m=d.getMonth()+1,
    dd=d.getDate();
  if(m<10){
    m='0'+m;
  }
  if(dd<10){
    dd='0'+dd;
  }
  value=y+'年'+m+'月'+dd+'日';
  return value;
});
var getFilter=Vue.filter("BrowsingVolume");
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  created(){
    if(localStorage.getItem("isLogin")===null){
      localStorage.setItem("isLogin",'')
    }
    this.$store.state.isLogin=localStorage.getItem('isLogin');
  },
  template: '<App/>'
})
