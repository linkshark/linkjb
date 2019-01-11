<template>
  <div class="home_m">
    <section style="margin-top:0px;">

      <el-container class="showTab">
        <span class="showIcon">
          <icon name="love1" scale="4"></icon>
        </span>
        <span>交友</span>
        <el-tabs v-model="activeName" @tab-click="handleClick" class="showElTab">
          <el-tab-pane label="所有人" name="first"></el-tab-pane>
          <el-tab-pane label="靓女" name="second"></el-tab-pane>
          <el-tab-pane label="靓仔" name="third"></el-tab-pane>
        </el-tabs>
        <span style="font-size: initial;text-align: right;margin-left: 60%;cursor: pointer;" class="moreImg" @click="vipShow"><a>更多 ></a></span>
      </el-container>
      <main class="m_product">
        <div class="index-tabMain">
          <div class="index-tabList show">
            <div class="bask-right">
              <ul class="bask-ul fn-clear">
                <li class="bask-block" v-for="item in mainDatas">
                  <div class="bask-list">
                    <div class="bask-top">
                      <!--<router-link :to="{ path: '/search/searchShow',query: { id: item.id,ownerid:info.id}}">-->
                        <img v-lazy="item.imageUrl"  @click="showDetail(item.id)" alt="" class="not_eng_pic1 lazy-img-fadein">
                      <!--</router-link>-->
                      <div class="bask-info">
                        <div class="bask-coneter">
                          <p class="bask-p1">{{item.name}}&{{item.age}}</p>
                          <!--<p class="bask-p2"></p>-->
                        </div>
                      </div>
                    </div>
                  </div>
                </li>
              </ul>
            </div>
          </div>
        </div>
      </main>
      <el-dialog :visible.sync="dialogTableVisible" center :append-to-body='true' :lock-scroll="false" width="80%">
        <search-show  v-bind:datas="dataId"  @cancle="cancleForm" ref="ResidentForm"></search-show>
      </el-dialog>
    </section>
  </div>
</template>
<script>
  import searchShow from '../search/searchShow'
  export default{
    components: {searchShow},
    data(){
      return {
        mainDatas:[],
        info:'',
        activeName: 'first',
        findFlag:0,
        dialogTableVisible:false,
        dataId:''
      }
    },

    // 生命周期  初始化
    mounted(){
      this.getData();
    },

    //方法
    methods:{
      vipShow(){
        if(this.info.id==undefined){
          this.$message({
            showClose: true,
            message: '请先登录!!!',
            type: 'warning'
          });
          return;
        }else{
          this.$message({
            showClose: true,
            message: '只有会员可见!!!',
            type: 'warning'
          });
        }
      },
      showDetail(itemId){
        if(this.info.id==undefined){
          this.$message({
            showClose: true,
            message: '请先登录!!!',
            type: 'warning'
          });
          return;
        }else if(this.info.id){
          /*this.$router.push({
            path: '/search/searchShow',
            query: { id: itemId,ownerid:this.info.id}
          });*/

          this.dataId=itemId;
          this.dialogTableVisible=true;
        }
      },
      cancleForm(){

      },
      handleClick(tab, event) {

        this.mainDatas=[];
        if(tab.index==0){
          this.findFlag=0;
        }else if(tab.index==1){
          this.findFlag=1;
        }else if(tab.index==2) {
          this.findFlag = 2;
        }
        this.getData();
      },
      //图片获取
      getData(){

        let _this=this;
        if(window.sessionStorage.userInfo){
          _this.info=JSON.parse(window.sessionStorage.userInfo);
          if(_this.findFlag==1 || _this.findFlag==2){

            let params = new URLSearchParams();
            params.append('userId', _this.info.id);
            params.append('findFlag', _this.findFlag);
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
          _this.$message({
            showClose: true,
            message: '请先登录!!!',
            type: 'warning'
          });
        }
        if(_this.findFlag==0){

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
<style scoped>
  .moreImg:hover{
    color:blue;
  }
  .showTab{
    padding-left:11%;
    height:40px;
    line-height: 40px;
    font-size: larger;
  }
  .showIcon{
    vertical-align: center;
  }
  .showElTab{
    margin-left:20px;

  }
  .not_eng_pic1{
    display: block;
    width: 160px;
    height: 160px;
    overflow: hidden;
    margin: 0 auto;
  }
  .bask-right {
    width: 1133px;
    height: 484px;
    overflow: hidden;
    -webkit-border-radius: 5px;
    -moz-border-radius: 5px;
    -ms-border-radius: 5px;
    -o-border-radius: 5px;
    border-radius: 5px;
    margin: 0 auto;
  }
  .fn-clear {
    zoom: 1;
  }
  .bask-ul {
    width: 101%;
  }
  .bask-ul li {
    float: left;
    width: 160px;
    height: 160px;
    margin: 0 2px 2px 0;
    list-style: none;
  }
  .bask-block {
    position: relative;
  }
  ul, menu, dir {
    display: block;
    list-style-type: disc;
    margin-block-start: 1em;
    margin-block-end: 1em;
    margin-inline-start: 0px;
    margin-inline-end: 0px;
    padding-inline-start: 40px;
  }
  .bask-p1 {
    font-size: 12px;
    line-height: 20px;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
    /*opacity: 0;*/
/*    display:none;*/
  }
  .bask-p2 {
    font-size: 12px;
    line-height: 20px;
    color: #a5a5a5;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
  }
  .bask-top img{
    cursor: pointer;
    transition: all 0.6s;
  }
  .bask-top img:hover{
    transform: scale(1.2);
  }
  .bask-top:hover .bask-coneter{
    background:#000;
    filter:alpha(opacity=60);
    width:160px;
    -moz-opacity:0.5;
    opacity: 0.5;
    position:absolute;
    cursor: pointer;
    /*display:block;*/
    z-index: 999;
  }
  .bask-coneter {
    opacity: 0;
    /*display:none;*/
    margin-top:-40px ;
    color: floralwhite;
  }
  .bask-top {
    height: 100%;
    overflow: hidden;
  }
  bask-info {
    display: none;
    position: absolute;
    bottom: 0px;
    left: 0px;
    width: 100%;
    height: 45px;
    z-index: 10;
    background-color: rgba(0, 0, 0, 0.5);
   }
</style>
