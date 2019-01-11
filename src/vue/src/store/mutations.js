//import getters from './getter.js';
const state = {
    isShow: true,
    isNavShow: true,
    userInfo: {},
    addCartDatas:[]
}
//定义getters
var getters={
    loading: (state) => {
        return state.isShow;
    },
    shownav: (state) => {
        return state.isNavShow;
    },
    hideNav: (state) => {
        return state.isNavShow;
    },
    getUserInfo: (state) => {
        return state.userInfo;
    },
    cartDatas: (state) => {
        return state.addCartDatas;
    },
    total:(state) => {
        //reduce()计算数组元素相加后的总和
        return state.addCartDatas.reduce(function(money, item){
            return money + item.product_uprice * item.goods_num
        }, 0);
    }
}

//定义Actions，要执行的操作，如流程判断，异步请求等
const actions = {
    cartAdd({commit, state},data) {
        commit('addCartDatas',data); //提交一个变化名称，与increment(state)对应
        //也可以传参数
        //commit('increment', {data:state})
    },
    //数量+
    goodIncrement({commit, state}, product_id) {
        state.addCartDatas.filter(function(item){return item.product_id == product_id})[0].goods_num++;
    },
    //数量减少
    goodDecrement({commit, state}, product_id) {
        let item = state.addCartDatas.filter(function(item){return item.product_id == product_id})[0];
        if (item && item.goods_num > 0)
            item.goods_num--;
    },
    //删除数据
    delCartDatas({commit, state}, product_id) {
        console.log(product_id);
        for(let i=0;i<state.addCartDatas.length;i++){
            if(state.addCartDatas[i].product_id == product_id){
                state.addCartDatas.splice(i,1);
                break;
            }
        }
        
    },
}

const mutations = {
    showLoading: (state) => {
        state.isShow = true
    },
    hideLoading: (state) => {
        state.isShow = false
    }, 
    showNav: (state) => {
        state.isNavShow = true
    },
    hideNav: (state) => {
        state.isNavShow = false
    },
    setUserInfo: (state, userInfo) => {
        state.userInfo = userInfo;
    },
    addCartDatas:(state,data)=>{
        state.addCartDatas.push(data)
    }

}

export default {
    getters,
    state,
    actions,
    mutations
}
