<template>
  <section style="margin-top:0px;">
    <el-container >
      <el-header>
        <el-menu router
          :default-active="$route.path"
          class="el-menu-demo"
          mode="horizontal"
          @select="handleSelect"
          background-color="#545c64"
          text-color="#fff"
          active-text-color="#ffd04b">
          <el-menu-item index="/navMain/navHeader"  class="myDiv1">match</el-menu-item>
          <!--<el-menu-item index="/navMain/navHeader"  class="myDiv">主页
          </el-menu-item>
          <el-submenu index="2">
            <template slot="title">发现</template>
            <el-menu-item index="/search/searchMain">搜索</el-menu-item>
            <el-menu-item index="2.2">匹配</el-menu-item>
            <el-menu-item index="/search/searchName">用户名搜索</el-menu-item>
          </el-submenu>
          <el-menu-item index="/getMyMesage">消息</el-menu-item>
          <el-submenu index="4">
            <template slot="title">兴趣</template>
            <el-menu-item index="4.1">喜欢</el-menu-item>
            <el-menu-item index="4.2">评级</el-menu-item>
            <el-menu-item index="4.3">喜爱</el-menu-item>
          </el-submenu>
          <el-menu-item index="9" v-show="show9">分析数据</el-menu-item>-->
          <el-submenu index="5" class="aboutMy"  v-show="show5">
            <template slot="title">
              <!--<icon name="idCard" scale="3"></icon>-->
              <img :src="imageUrl" class="self-img">
            </template>
            <el-menu-item index="/my/myMessage">个人中心</el-menu-item>
            <el-menu-item index="/ssage">我的消息</el-menu-item>
            <!--<&#45;&#45;<el-menu-item index="5.2">谁看过我</el-menu-item>
            <el-menu-item index="5.3">促进</el-menu-item>
            <el-menu-item index="5.4">匹配</el-menu-item>
            <el-menu-item index="5.5">与其比赛</el-menu-item>&ndash;&gt;-->
            <el-menu-item index="cancel">注销</el-menu-item>
          </el-submenu>
          <el-menu-item index="7" class="aboutMy" v-show="show7">登陆</el-menu-item>
          <el-menu-item index="8" v-show="show8">注册</el-menu-item>
        </el-menu>
      </el-header>
    </el-container>
    <el-dialog title="登陆"  :visible.sync="dialogTableVisible" center :append-to-body='true' :lock-scroll="false" width="30%">
      <login-name  @cancle="cancleForm" ref="ResidentForm"></login-name>
    </el-dialog>
    <el-dialog title="注册" :visible.sync="dialogFormVisible" center :append-to-body='true' :lock-scroll="false" width="30%">
      <show-nav-center @cancle="cancleForm"></show-nav-center>
    </el-dialog>
    <router-view></router-view>
  </section>
</template>
<script>
  import LoginName  from '../main/Login.vue';
  import ShowNavCenter from '../main/ShowNavCenter.vue';
  import navHeader  from '../navMain/navHeader.vue';
  import myMessage  from '../my/myMessage.vue';
  export default {
    components: {LoginName,ShowNavCenter,navHeader,myMessage},
    name: "navShow",
    data() {
      return {
        show5:false,
        show6:false,
        show7:true,
        show8:true,
        show9:false,
        headShow:true,
        messageShow:false,
        myflag:'',
        activeIndex: '1',
        dialogTableVisible: false,
        dialogFormVisible: false,
        imageUrl:''

      }
    },
    mounted(){
      this.showCenter();
      if(window.sessionStorage.userInfo){
        this.imageUrl=JSON.parse(window.sessionStorage.userInfo).imageUrl;
        this.show5=true;
        this.show6=true;
        this.show7=false;
        this.show8=false;

      }
    },
    methods: {
      showCenter:function(){
        this.$router.push('/navMain/navHeader');
      },
      cancleForm: function (data){
        debugger
        this.myflag=data;
        this.dialogTableVisible=false;
        this.dialogFormVisible=false;
        if(window.sessionStorage.userInfo){
          this.imageUrl=JSON.parse(window.sessionStorage.userInfo).imageUrl;
          this.show5=true;
          this.show6=true;
          this.show7=false;
          this.show8=false;
          this.showCenter();
        }
      },
      handleSelect(key, keyPath) {
        let _this = this;
        if(key==7){
          debugger
          //_this.$router.push('/login');
          _this.dialogTableVisible=true;
        }
        if(key=="message"){
          if(window.sessionStorage.userInfo){
            _this.$router.push('/message/messageNav');
          }else{
            _this.$message({
              message: '请登陆之后查看',
              type: 'warning',
              duration: 2000
            });
            return;
          }
        }
        if(key==8){
          debugger
          //_this.$router.push('/reg');
          _this.dialogFormVisible=true;
        }
        if(key==5.1){
          _this.messageShow=true;
          _this.headShow=false;
        }
        if(key==1) {
          debugger
          _this.$router.push('/navMain/navHeader');
        }
        if(key==2.1) {
          debugger
          _this.$router.push('/search/searchMain');
        }
        if(key=="cancel") {
          debugger
          _this.showCenter();
          window.sessionStorage.userInfo="";
          _this.show5=false;
          _this.show6=false;
          _this.show7=true;
          this.show8=true;
          this.$router.push('/navMain/navHeader');
          window.location.reload();
        }
      }
    }
  }
</script>
<style scoped>
  .myDiv1{
    position: relative;
    margin-left: 200px;
    font-size: larger;
  }
  .myDiv{
    position: relative;
    margin-left: 200px;
  }
  .aboutMy{
    position: relative;
    margin-left: 60%;
  }
  .el-carousel__item h3 {
    color: #475669;
    font-size: 14px;
    opacity: 0.75;
    line-height: 300px;
    margin: 0;
  }
  .self-img{
    position: absolute;
    width: 50px;
    height: 50px;
    border-radius: 50%;
    margin-left: -17px;
    z-index:999;
    align-items: center;
  }
</style>
