
<template>
      <div >
        <section style="margin-top:60px;">
          <el-container class="echarts">
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
                <el-submenu index="5" class="aboutMy"  v-show="show5">
                  <template slot="title">
                    <img :src="imageUrl" class="self-img">
                  </template>
                  <el-menu-item index="/my/myMessage">个人中心</el-menu-item>
                  <el-menu-item index="/showTech">帮助</el-menu-item>
                  <el-menu-item index="cancel">注销</el-menu-item>
                </el-submenu>
                <el-menu-item index="log" class="aboutMy" v-show="show7">登陆</el-menu-item>
                <el-menu-item index="reg"  class="aboutMy1" v-show="show8">注册</el-menu-item>
              </el-menu>
            </el-header>
            <div class="Lbox">
              <div id="showZhe" :style="{height:'350px',width:'400px',}" ref="ZheEchart">
                <show-zhe></show-zhe>
              </div>
              <div id="showYuan" :style="{height:'350px',width:'400px',}" >
                <show-yuan></show-yuan>
              </div>
            </div>
            <div id="showMap" :style="{height:'700px'}">
              <show-map></show-map>
            </div>
            <div class="Rbox">
              <div id="showHuan" :style="{height:'350px',width:'400px',}">
                <show-huan></show-huan>
              </div>
              <div id="showPai" :style="{height:'350px',width:'400px',}">
                <show-pai></show-pai>
              </div>
            </div>
          </el-container>
          <div>
            <img-show></img-show>
          </div>
          <div class="baidumap">
            <baidu-map></baidu-map>
          </div>
          <el-dialog title="登陆"  :visible.sync="dialogTableVisible" center :append-to-body='true' :lock-scroll="false" width="28%">
            <login-name  @cancle="cancleForm" ref="ResidentForm"></login-name>
          </el-dialog>
          <el-dialog title="注册" :visible.sync="dialogFormVisible" center :append-to-body='true' :lock-scroll="false" width="30%">
            <show-nav-center @cancle="cancleForm"></show-nav-center>
          </el-dialog>
          <el-dialog :visible.sync="dialogTalkVisible" center :append-to-body='true' :lock-scroll="false" width="60%">
            <talk  @cancle="cancleForm" ref="ResidentForm"></talk>
          </el-dialog>
          <router-view></router-view>
        </section>
        <div style="position: fixed;right: 20px;bottom:0px;" title="点击打开聊天窗口" @shownum="shownum" @click="showTalk">
          <el-badge :max="10" class="item">
            <icon name="talk1" scale="5"></icon>
          </el-badge>
        </div>
      </div>
</template>
<script>
  import talk from '../main/talk'
  import baiduMap from './baiduMap';
  import showMap from './showMap';
  import showHuan from './showHuan';
  import showYuan from './showYuan';
  import imgShow from './imgShow';
  import showPai from './showPai';
  import echarts from "echarts";
  import '../../../node_modules/echarts/map/js/china.js'
  import ShowZhe from "./showZhe"; // 引入中国地图数据
  import LoginName  from '../main/Login.vue';
  import ShowNavCenter from '../main/ShowNavCenter.vue';
  import navHeader  from '../navMain/navHeader.vue';
  import myMessage  from '../my/myMessage.vue';
  export default {
    components:{talk,ShowZhe,showMap,showHuan,showYuan,showPai,imgShow,LoginName,ShowNavCenter,navHeader,myMessage,baiduMap},
    name: "navHeader",
    props: ["userJson"],
    data() {
      return {
        menum:0,
        dialogTalkVisible:false,
        chart: null,
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
      };
    },
    mounted() {
      this.showCenter();
      if(window.sessionStorage.userInfo){
        this.imageUrl=JSON.parse(window.sessionStorage.userInfo).imageUrl;
        this.show5=true;
        this.show6=true;
        this.show7=false;
        this.show8=false;

      }
    },
    beforeDestroy() {
      if (!this.chart) {
        return;
      }
      this.chart.dispose();
      this.chart = null;
    },
    methods: {
      shownum(num){
        console.log(num)
      },
      showTalk(){
        if(window.sessionStorage.userInfo){
          this.dialogTalkVisible=true
        }else{
          this.$message({
            showClose: true,
            message: '请登录之后查看!!!',
            type: 'warning'
          });
        }
      },
      showCenter:function(){
        this.$router.push('/navMain/navHeader');
      },
      cancleForm: function (data){
        if(data=="ok"){
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
        }else if(data==1){
          this.dialogTableVisible=false;
          this.dialogFormVisible=true;
        }

      },
      handleSelect(key, keyPath) {
        let _this = this;
        if(key=="log"){

          //_this.$router.push('/login');
          _this.dialogTableVisible=true;
        }
        if(key=="message"){
          if(window.sessionStorage.userInfo){
            _this.$router.push('/my/messageNav');
            window.location.reload();
          }else{
            _this.$message({
              message: '请登陆之后查看',
              type: 'warning',
              duration: 2000
            });
            return;
          }
        }
        if(key=="reg"){

          //_this.$router.push('/reg');
          _this.dialogFormVisible=true;
        }
        if(key==5.1){
          _this.messageShow=true;
          _this.headShow=false;
        }
        if(key==1) {

          _this.$router.push('/navMain/navHeader');
        }
        if(key==2.1) {

          _this.$router.push('/search/searchMain');
        }
        if(key=="cancel") {

          _this.showCenter();
          window.sessionStorage.userInfo="";
          _this.show5=false;
          _this.show6=false;
          _this.show7=true;
          _this.show8=true;
          /*_this.$router.push('/navMain/navHeader');*/
          _this.$router.replace({
            path:'/home/supplierAllBack',
            name:'supplierAllBack'
          })
        }
      }
    }
  }
</script>
<style scoped>
  .Lbox{
    width: 400px;
    height: 700px;
    position: absolute;
    top: 70px;
  }
  .Rbox{
    width: 400px;
    height: 700px;
    position: absolute;
    top: 70px;
  }
  .Lbox{
    left:5px;
  }
  .Rbox{
    right:5px;
  }
  .echarts{
    width:100%;
    height:770px;
    background-image: url("../../assets/images/bggr.png");
    margin-top: -70px;
  }
  #showMap{
    margin:0 400px;
    background-image: url("../../assets/images/bg4.png");
  }
  #showHuan{
    margin-top: -70px;
    background-image: url("../../assets/images/bg2.png");
  }
  #showZhe{
    background-image: url("../../assets/images/bg2.png");
    margin-top: 0px;
  }
  #showPai{
    background-image: url("../../assets/images/bg2.png");
  }
  #showYuan{
    background-image: url("../../assets/images/bg2.png");
  }
  .myDiv1{
    position: relative;
    left: 200px;
    font-size: larger;
  }
  .myDiv{
    position: relative;
    left: 200px;
  }
  .aboutMy{
    position: relative;
    left: 60%;
  }
  .aboutMy1{
    position: relative;
    left: 61%;
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
    height: 60px;
    border-radius: 50%;
    margin-left: -17px;
    padding-top:10px;
    z-index:999;
    align-items: center;
  }
  .baidumap{
    margin:10px auto;
  }
  .item {
    margin-top: 10px;
    margin-right: 40px;
  }
</style>
