<template>
  <section style="margin-top:0px;">
    <el-container >
      <el-form :model="form"  label-width="120px" style="margin-top: 0px">
      <el-main class="elmain">
        <hr style="border:1px solid #ccc" />
        <el-row >
          <el-col :span="10" style="border-top: inherit">
            <el-form-item prop="imageUrl">
              <img :src="form.imageUrl" class="avatar">
            </el-form-item>
          </el-col>
          <el-col :span="14" style="text-align: left">
            <el-form-item prop="name">
              <p class="not_eng_title">姓名:{{form.name}}</p>
              <p class="not_eng_title">出生日期:{{form.birthday | BrowsingVolume}}</p>
              <p class="not_eng_title">住址:{{form.address}}</p>
              <p >
                <span  class="tubiao youxiang" title="发送信息" @click="dialogLiuVisible = true">
                  <el-badge :value="form.ynum" class="item">
                    <icon name="youxiang" scale="4" ></icon>
                  </el-badge>
                </span>
                <span></span>
                <span  class="tubiao xin" title="喜欢" @click="xShow">
                  <el-badge :value="form.xnum" class="item">
                    <icon name="xin" scale="4"></icon>
                  </el-badge>
                </span>
              </p>
            </el-form-item>
          </el-col>
          <el-col :span="14" style="text-align: left">
            <el-form-item prop="name">
              <p class="not_eng_title">自我介绍:{{form.detail}}</p>
            </el-form-item>
          </el-col>
        </el-row>
        <hr style="border:1px solid #ccc" />
        <el-row >
          <el-col :span="20" style="border-top: inherit">
            <el-form-item>
              <p  class="not_eng_title">
                <span  class="showSvg" title="学历">
                    <icon name="jiaoyu1" scale="2" ></icon>&nbsp;&nbsp;{{form.education}}
                </span>
                <span></span>
                <span  class="showSvg " title="婚姻">
                    <icon name="hunyin1" scale="2"></icon>&nbsp;&nbsp;{{form.marriageFlag}}
                </span>
                <span></span>
              </p>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row >
          <el-col :span="20" style="border-top: inherit">
            <el-form-item>
              <p  class="not_eng_title">
                <span  class="showSvg" title="宝宝">
                    <icon name="baby" scale="2"></icon>&nbsp;&nbsp;{{form.child}}
                </span>
                <span></span>
                <span  class="showSvg1" title="抽烟">
                    <icon name="smoke" scale="2" ></icon>&nbsp;&nbsp;{{form.smoke}}
                </span>
              </p>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row >
          <el-col :span="20" style="border-top: inherit">
            <el-form-item>
              <p  class="not_eng_title">
                <span></span>
                <span  class="showSvg1" title="喝酒">
                    <icon name="drink" scale="2"></icon>&nbsp;&nbsp;{{form.drink}}
                </span>
                <span></span>
                <span  class="showSvg1" title="身高">
                    <icon name="height" scale="2"></icon>&nbsp;&nbsp;{{form.tall}}cm
                </span>
              </p>
            </el-form-item>
          </el-col>
        </el-row>
        <hr style="border:1px solid #ccc" />
      </el-main>
      </el-form>
      <el-aside width="30%">
        <div class="box">
          <div class="carousel-messagebox">
            <transition-group tag="ul" enter-active-class="animated fadeInUp"
                              leave-active-class="animated fadeOutUp">
              <li v-show="idx == isCurrent" v-for="(item, idx) in messageList" :key="idx" :class="['carousel-message']">
                <span class="good">{{item.name}}</span>
                <span>牵手</span>
                <span class="good">{{item.prizeName}}</span>
              </li>
            </transition-group>
          </div>
        </div>
        <div >
          <el-card class="box-card">
            <div slot="header" class="clearfix">
              <span>可能感兴趣</span>
            </div>
            <div v-for="o in 4" :key="o" class="text item">
              {{'列表内容 ' + o }}
            </div>
          </el-card>
        </div>
      </el-aside>
    </el-container>
    <el-dialog title="留言信息" :visible.sync="dialogLiuVisible" width="40%" >
      <el-form>
        <el-form-item label="心语" :label-width="formLabelWidth" prop="leaveMessage">
          <el-input v-model="leaveMessage" auto-complete="new-password" type="textarea"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogLiuVisible = false">取 消</el-button>
        <el-button type="primary" @click="ensure">确 定</el-button>
      </div>
    </el-dialog>
    <el-dialog title="聊天框" :visible.sync="dialogTalkVisible" width="60%" style="margin-top: 170px;">
      <talk></talk>
    </el-dialog>
  </section>
</template>
<script>
  import talk from '../main/talk'
  export default {
    components: {talk},
    name: "searchShow",
    data() {
      return {
        nameList: [
          '电风**啦',
          '你***包',
          '十二****就',
          '高****爸',
          '南城旧****梦',
          '别理****我',
          '诺****曦',
          '悲欢**女',
          '一枫****书',
          '尹雨****沫',
          '呆橘****橘',
          '困****倦',
        ],
        prizeList:[
          '腾讯超会员',
          '华为会员',
          '爱奇艺会员',
          '百度高级会员',
          '小米会员',
          '苏宁会员',
          '苹果高级会员',
          '中兴会员',
        ],
        messageList: [],
        isCurrent:0,
        uuid:'',
        ownerid:'',
        dialogTalkVisible:false,
        dialogLiuVisible: false,
        formLabelWidth: '120px',
        leaveMessage:'',
        info:'',
        form:{
          xnum:'',
          ynum:'',
          imageUrl:'',
          name:'',
          birthday:'',
          detail:'',
          education:'',
          marriageFlag:'',
          child:'',
          smoke:'',
          drink:'',
          tall:''
        }
      }
    },
    props:['datas'],
    watch:{
      datas:{
        handler(newValue, oldValue){

          this.uuid=newValue;
          console.log(newValue, oldValue);
          this.showSome(newValue);
        }
      }
    },
    mounted(){

      this.info=JSON.parse(window.sessionStorage.userInfo);
      /*this.uuid=this.$route.query.id;*/
      console.log(this.datas);
      this.uuid=this.datas;
      this.ownerid=this.info.id;
      /*this.ownerid=this.$route.query.ownerid;*/
      /*console.log(this.$route.query)*/
      this.showSome(this.datas);
      this.autoPlay();
    },
    methods:{
      xShow(){

        let _this=this;
        let params = new URLSearchParams();
        if(_this.ownerid==undefined){
          this.$message({
            showClose: true,
            message: '请先登录!!!',
            type: 'warning'
          });
          return;
        }
        params.append('likeId', _this.uuid);//被查看人的id
        params.append('ownerId', _this.ownerid);//查看人的id
        _this.$http.post('/login/addHeart',params).then((res)=>{

          if(res.data.status == 200){
            _this.form.xnum=res.data.entity.heartLove;
            this.showSome(_this.uuid);
            this.showDetail();
          }else{
            _this.$message({
              showClose: true,
              message: res.data.message,
              type: 'error',
              duration:2000
            });
          }
        },(err)=>{
          console.log(err);
        });
      },
      autoPlay () {
        let _this=this;
        for( let i = 0 ; i < 5 ; i++ ){
          _this.messageList.push({
            name: _this.nameList[Math.floor(Math.random() * _this.nameList.length)],
            prizeName: _this.prizeList[Math.floor(Math.random() * _this.prizeList.length)]
          })
        };
        let length = _this.messageList.length;
        if (_this.isCurrent >= length) {
          _this.isCurrent = 0;
          _this.autoPlay();
        } else {
          setTimeout(()=>{
            _this.isCurrent++;
            _this.autoPlay();
          }, 3000)
        }
      },
      ensure(){
        let _this=this;
        let params = new URLSearchParams();
        if(_this.ownerid==undefined){
          this.$message({
            showClose: true,
            message: '请先登录!!!',
            type: 'warning'
          });
          return;
        }
        params.append('passerId', _this.uuid);//被查看人的id
        params.append('ownerId', _this.ownerid);//查看人的id
        params.append('message', _this.leaveMessage);
        _this.$http.post('/leave/message',params).then((res)=>{

          if(res.data.status == 200){
            _this.$message({
              message: '留言成功',
              type: 'success',
              duration:2000
            });
            this.showSome(_this.uuid);
            this.showDetail();
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
        this.dialogLiuVisible=false;
      },
      showSome:function(newValue){
        let _this=this;
          let params = new URLSearchParams();
          params.append('id',newValue);

          _this.$http.post('/login/findUserById',params).then((res)=>{

            if(res.data.status == 200){
              _this.form = res.data.entity;
              /*let birth=res.data.entity.birthday;
              let d = new Date(birth);
              let y=d.getFullYear(),
                m=d.getMonth()+1,
                dd=d.getDate();
              if(m<10){
                m='0'+m;
              }
              if(dd<10){
                dd='0'+dd;
              }
              _this.form.birthday=y+'年'+m+'月'+dd+'日';*/
              _this.form.imageUrl=res.data.entity.imageUrl;
              _this.form.xnum=res.data.entity.heartLove;
              this.showDetail();
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
        },
        showDetail(){

          if(this.form.education!="null"){
            if(this.form.education=="0"){
              this.form.education="未曾上过学";
            }else if(this.form.education=="1"){
              this.form.education="小学";
            }else if(this.form.education=="2"){
              this.form.education="中学";
            }else if(this.form.education=="3"){
              this.form.education="高中";
            }else if(this.form.education=="4"){
              this.form.education="大专";
            }else if(this.form.education=="5"){
              this.form.education="本科";
            }else if(this.form.education=="6"){
              this.form.education="硕士";
            }else if(this.form.education=="7"){
              this.form.education="博士";
            }
          }
          if(this.form.marriageFlag!="null"){
            if(this.form.marriageFlag=="1"){
              this.form.marriageFlag="从未结婚";
            }else if(this.form.marriageFlag=="2"){
              this.form.marriageFlag="有过结婚";
            }
          }else{
            this.form.marriageFlag="从未结婚";
          }
          if(this.form.child!="null"){
            if(this.form.child=="1"){
              this.form.child="没有小孩";
            }else if(this.form.child=="2"){
              this.form.child="有小孩";
            }
          }
          if(this.form.smoke!="null"){
            if(this.form.smoke=="1"){
              this.form.smoke="从不抽烟";
            }else if(this.form.smoke=="2"){
              this.form.smoke="经常抽烟";
            }
          }else{
            this.form.smoke="从不抽烟";
          }
          if(this.form.drink!="null"){
            if(this.form.drink=="1"){
              this.form.drink="从不喝酒";
            }else if(this.form.drink=="2"){
              this.form.drink="经常喝酒";
            }
          }else{
            this.form.drink="从不喝酒";
          }
        },
      },

  }
</script>
<style scoped>
  .elmain{

  }
  .showSvg{
    padding-right:100px;
  }
  .showSvg1{
    padding-right:100px;
  }
  .item {
    margin-top: 10px;
    margin-right: 20px;
  }
  .avatar {
    width: 200px;
    height: 200px;
    display: block;
  }
  .tubiao{
    cursor: pointer;
  }
  .box{
    width: 300px;
    height: 80px;
    border: 1px dashed pink;
    margin: 0 auto;
    border-radius: 20px;
    display: flex;
    justify-content: center;
    align-items: center;
    margin-bottom: 100px;
  }
  .carousel-messagebox {
    width:260px;
    height: 30px;
    margin-bottom: 20px;
  }
  .box-card {
    width: 300px;
    padding-top: 20px;
    margin:0 auto;
  }
  .carousel-message {
    width:220px;
    height: 30px;
    line-height:30px;
    font-family: PingFangSC-Regular;
    font-size: 12px;
    background: url('../../assets/images/lbn.png') 16px center no-repeat rgba(0,0,0,0.6);
    background-size:20px 16px;
    border-radius: 30px;
    padding-left:30px;
    position: absolute;
    margin-left:-30px;
    color:#fff;
    display: -webkit-box;
    -webkit-box-pack: center;
    -webkit-box-align: center;
  }
  .good {
    color:#ffe795;
    width: 80px;
    overflow: hidden;
    text-overflow:ellipsis;
    white-space: nowrap;
    display: block;
    height: 30px;
    line-height:30px;
    margin-left: 8px;
  }
</style>
