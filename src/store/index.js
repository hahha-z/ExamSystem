import Vue from 'vue'
import Vuex from 'vuex'
Vue.use(Vuex)

//响应动作
const actions = {}
//操纵数据
const mutations = {}
//储存数据
const state = {}

//创建并暴露store
export default new Vuex.Store({
    actions,
    mutations,
    state,
})