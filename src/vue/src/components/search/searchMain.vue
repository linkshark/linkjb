<template>
  <div class="home_m">
    <main class="m_product">
      <div class="left_share_quality_content clearfix">
        <div class="share_quality">
          <div class="share_quality_left not_eng_box">
            <ul>
              <li class="not_eng_item" v-for="item in mainDatas">
                <router-link :to="{ path: '/search/searchShow',query: { id: item.id,ownerid:info.id}}">
                  <img v-lazy="item.imageUrl" alt="" class="not_eng_pic lazy-img-fadein">
                </router-link>
                  <div class="not_eng_info">
                    <p class="not_eng_title">姓名:{{item.name}}</p>
                    <span class="more_info_price_txt">年龄:{{item.age}}岁</span>
                  </div>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </main>
  </div>
</template>
<script>
  export default{
    name: 'searchMain',
    data(){
      return {
        mainDatas:[],
        info:''
      }
    },
    // 生命周期  初始化
    mounted(){
      this.getData();
    },
    //方法
    methods:{
      //图片获取
      getData(){
        let _this=this;
        if(window.sessionStorage.userInfo){
          _this.info=JSON.parse(window.sessionStorage.userInfo);
          if(_this.info.findGender){

              let params = new URLSearchParams();
              let findFlag = _this.info.findFlag;
              params.append('userId', _this.info.id);
              params.append('findFlag', findFlag);
              _this.$http.post('/login/findFlag',params).then((res)=>{

                if(res.data.status == 200){
                  _this.mainDatas = res.data.list;
                }else{
                  _this.$message({
                    showClose: true,
                    message: '返回数据有误！！！',
                    type: 'error',
                    duration:2000
                  });
                }
              },(err)=>{
                console.log(err);
              });
          }
        }else{
          let params = new URLSearchParams();
          params.append('findflag', 0);
          _this.$http.post('/login/findAllUser',params).then((res)=>{

            if(res.data.status == 200){
              _this.mainDatas = res.data.list;
            }else{
              _this.$message({
                showClose: true,
                message: '返回数据有误！！！',
                type: 'error',
                duration:2000
              });
            }
          },(err)=>{
            console.log(err);
          });
        }
      },


    }

  }
</script>
<style>
  @import '../../assets/css/index.css';
</style>
