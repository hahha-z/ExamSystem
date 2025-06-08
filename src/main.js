//引入Vue
import Vue from 'vue'
//引入App
import App from './App.vue'
//引入VueRouter
import VueRouter from 'vue-router'
//引入路由器
import router from './router'
//引入store
import store from './store'

Vue.config.productionTip = false

Vue.use(VueRouter)

new Vue({
  render: h => h(App),
  // 安装全局事件总线
  beforeCreate(){
        Vue.prototype.$bus = this
    },
  store,
  router:router,
}).$mount('#app')

const originalPush = VueRouter.prototype.push;
VueRouter.prototype.push = function push(location){
  return originalPush.call(this,location).catch(err => err);
}
