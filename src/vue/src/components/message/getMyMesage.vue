<template>
  <section style="height: 100%;width: 84%;margin-left:200px;margin-top: -660px;"  @keyup.enter="onEnter">
    <el-row  v-show="!tableFormShow" style="padding-left: 20px;padding-right: 10px">
      <el-col :span="24" class="toolbar" style="padding: 10px 10px 0 10px;">
        <el-form :inline="true">

        </el-form>
      </el-col>
      <div v-for="(article,key) in tableData" :key="key"  class="article-contain">
        <el-row class="article-title">
          <el-col :span="24">
            <h3>
              {{article.ownerName}}
              <icon name="youxiang" scale="2" ></icon>
            </h3>
          </el-col>
        </el-row>
        <div class="details">
          <el-row>
            <el-col :span="22" :offset="1">
              <h4>{{article.message}}</h4>
            </el-col>
          </el-row><br>
          <el-row>
            <el-col :sm={span:1,offset:12}>
              <h5>回复</h5>
            </el-col>
            <el-col :sm={span:1,offset:1}>
              <h5>举报</h5>
            </el-col>
            <el-col :sm={span:1,offset:1}>
              <h5>查看</h5>
            </el-col>
            <el-col :span="4" :xs="10">
              <h5 class="grey-color">{{article.createTime}}</h5>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="24" style="height:12px"></el-col>
          </el-row>
        </div>
      </div>
    </el-row>
    <el-dialog title="回复信息" :visible.sync="dialogLiuVisible" width="40%" >
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
    <el-dialog title="举报原因" :visible.sync="dialogJuVisible" width="40%" >
      <el-form>
          <el-form-item>
            <el-checkbox-group v-model="jutype">
              <el-checkbox label="语言骚扰" ></el-checkbox>
              <el-checkbox label="色情/暴力" ></el-checkbox>
              <el-checkbox label="反动" ></el-checkbox>
              <el-checkbox label="传播不良信息" ></el-checkbox>
              <el-checkbox label="反动" ></el-checkbox>
              <el-checkbox label="传播不良信息" ></el-checkbox>
            </el-checkbox-group>
          </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogJuVisible = false">再给一次机会</el-button>
        <el-button type="primary" @click="ensure1">忍无可忍</el-button>
      </div>
    </el-dialog>
  </section>
</template>

<script>

  export default {
    components: {},
    data() {
      return {
        jutype:[],
        formLabelWidth: '120px',
        dialogLiuVisible:false,
        dialogJuVisible:false,
        leaveMessage:'',
        formFlag:'',
        colStyle:{

        },
        para:{"pageNo":1,"pageSize":10},
        maxHeight:document.documentElement.clientHeight-244,
        tableStyle:{
          width: '100%',
          height:'100%',
        },
        tableHeight:document.documentElement.clientHeight-145,
        tableFormList:{},
        tableFormShow:false,
        total:0,
        pageSize:10,
        page:1,
        tableData:[],
        text:'',
        SelData2:'',
        SelData4:'',
        SelData5:'',
        selectNo:'',
        info:'',
        uuid:'',
        ownerid:'',
        flag:2,
      }
    },
    methods: {
      backMessage(row){
        this.uuid=row.ownerId;
        this.ownerid=row.passerId;
        this.dialogLiuVisible=true;
      },
      getMessage(){
        this.dialogJuVisible=true;
      },
      selectChange(){
        this.text='';
        this.SelData2='';
        this.SelData4='';
        this.SelData5='';
      },
      showMessage(row){
        console.log(row,this.info.id);
        this.$router.push({path: '/search/searchShow',query: { id: row.ownerId,ownerid:this.info.id}})
      },
      cancleForm(){
        this.tableFormShow=false
        this.handleClear();
      },
      handleClear(){
        this.para = {"pageNo":1, "pageSize":this.pageSize};
        this.selectNo = '';
        this.text=''
        this.SelData=''
        this.getTableData();

      },
      onEnter(ev){
        if(ev.keyCode==13){
          this.handleSearch();
        }
      },
      handleSizeChange(val){
        console.log(val);
        this.pageSize = val;
        this.para.pageSize = val;
        this.getTableData();
      },
      handleCurrentChange(val){
        this.page= val;
        this.para.pageNo = val;
        this.getTableData();
      },
      ensure1(){

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
          debugger
          if(res.data.status == 200){
            _this.$message({
              message: '回复留言成功',
              type: 'success',
              duration:2000
            });
            this.showSome();
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
      getTableData(){
        let _this=this;
        if(window.sessionStorage.userInfo) {
          debugger
          _this.info = JSON.parse(window.sessionStorage.userInfo);
          let params = new URLSearchParams();
          let showIndfo = JSON.parse(window.sessionStorage.userInfo);
          params.append('ownerId', showIndfo.id);
          params.append('flag', _this.flag);
          let page=_this.page;
          let pageSize=_this.pageSize;
          _this.$http.post('/leave/findmessage', params).then((res) => {
            debugger
            if (res.data.status == 200) {
              res.data.entity.forEach(function(item,i){
                let date=new Date(item.createTime);
                let y=date.getFullYear(),
                  m=date.getMonth()+1,
                  dd=date.getDate(),
                mi=date.getHours(),
                hh=date.getMinutes(),
                ss=date.getSeconds();
                if(m<10){
                  m='0'+m;
                }
                if(dd<10){
                  dd='0'+dd;
                }
                if(hh<10){
                  hh='0'+hh;
                }
                if(mi<10){
                  mi='0'+mi;
                }
                if(ss<10){
                  ss='0'+ss;
                }
                item.createTime=y+'-'+m+'-'+dd+'  '+mi+':'+hh+':'+ss;
              })
              _this.tableData = res.data.entity.slice((page - 1) * pageSize, ((page) * pageSize - 1));
              this.total = res.data.entity.length;
            }
          }, (err) => {
            console.log(err);
          });
        }
      },
      handleSearch(){
        this.getTableData();
      },

    },
    mounted() {
      this.getTableData();

    }
  }

</script>

<style scoped>
  .article-contain{
    background: #fff;
    margin: 2rem auto;
    width: 90%;
    border-radius: 10px;
    cursor: pointer;
    border:5px red solid;
  }
  .article-title{
    border-bottom: 1px solid #000;
    text-align: left;
    height:40px;
  }
  .details{
    padding-top: 1rem;
    text-align: left;
    height:100px;
  }
  .grey-color{
    color: grey;
  }
</style>
