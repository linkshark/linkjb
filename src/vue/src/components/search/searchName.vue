<template>
  <section style="height: 100%;width: 100%"  @keyup.enter="onEnter">
    <el-row  v-show="!tableFormShow" style="padding-left: 20px;padding-right: 10px">
      <el-col :span="24" class="toolbar" style="padding: 10px 10px 0 10px;">
        <el-form :inline="true">

        </el-form>
      </el-col>
      <el-col :span="24" >
        <el-table :data="tableData" highlight-current-row  border  :style="tableStyle" :height="tableHeight">
          <el-table-column prop="name" label="姓名" width="100" >
          </el-table-column>
          <el-table-column prop="gender" label="性别" width="60">
          </el-table-column>
          <el-table-column prop="birthday" label="出生日期" width="150">
          </el-table-column>
          <el-table-column prop="marriageFlag" label="婚姻状况" width="100">
          </el-table-column>
          <el-table-column prop="mobile" label="联系电话" width="120">
          </el-table-column>
          <el-table-column prop="email" label="邮箱" width="200">
          </el-table-column>
          <el-table-column prop="money" label="收入" width="100">
          </el-table-column>
          <el-table-column prop="job" label="职业" width="q00">
          </el-table-column>
          <el-table-column prop="address" label="居住地址" width="300">
          </el-table-column>
          <el-table-column
            label="操作" min-width="40" fixed="right">
            <template slot-scope="scope">
              <el-button size="small" style="background-color: #F7BA2A;color: white;border: 0" @click="showMessage(scope.row)">查看</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-col>
      <el-col :span="24" class="toolbar" style="padding-top:6px">
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="page"
          :page-sizes="[10, 20, 50]"
          :page-size="pageSize"
          align="center"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
        </el-pagination>
      </el-col>
    </el-row>
    <my-resident-form v-if="tableFormShow" :datas="tableFormList" :datas2="formFlag" @cancle="cancleForm"></my-resident-form>
  </section>
</template>

<script>

  export default {
    components: {},
    data() {
      return {
        genderOption:[
          {value:1,label:'男'},
          {value:2,label:'女'}
          ],
        insuranceOption:[{value:1,label:'男'},
          {value:2,label:'女'}],
        formFlag:'',
        colStyle:{

        },
        para:{"pageNo":1,"pageSize":10},
        maxHeight:document.documentElement.clientHeight-244,
        tableStyle:{
          width: '100%',
          height:'100%',
        },
        tableHeight:document.documentElement.clientHeight-245,
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
        selectOptions:[
          {selectValue:1,label:'姓名'},
          {selectValue:2,label:'性别'},
          {selectValue:3,label:'婚姻状况'},
          {selectValue:4,label:'联系电话'}
        ]
      }
    },
    methods: {
      selectChange(){
        this.text='';
        this.SelData2='';
        this.SelData4='';
        this.SelData5='';
      },
      showMessage(row){
        debugger
        console.log(row,this.info.id);
        this.$router.push({path: '/search/searchShow',query: { id: row.id,ownerid:this.info.id}})
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
      getTableData(){
        let _this=this;
        if(window.sessionStorage.userInfo) {
          _this.info = JSON.parse(window.sessionStorage.userInfo);
        }
        let params = new URLSearchParams();
        let page=_this.page;
        let pageSize=_this.pageSize;
        params.append('findflag', 0);
        _this.$http.post('/login/findAllUser',params).then((res)=>{
          debugger
          if(res.data.status == 200){
            _this.tableData = res.data.list.slice((page-1)*pageSize,((page)*pageSize-1));
            this.total=res.data.list.length;
            _this.tableData.forEach(function(item,i){
              let birth=item.birthday;
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
              item.birthday=y+'年'+m+'月'+dd+'日';
              let gender=item.gender;
              if(gender=="1"){
                item.gender="男";
              }else if(gender=="2"){
                item.gender="女";
              }
              let marriageFlag=item.marriageFlag;
              if(marriageFlag=="1"){
                item.marriageFlag="从未结婚";
              }else if(marriageFlag=="2"){
                item.marriageFlag="结过婚";
              }else{
                item.marriageFlag="从未结婚";
              }
              let money=item.money;
              if(money=="0"){
                item.money="0-5 万元";
              }else if(money=="1"){
                item.money="5-10 万元";
              }else if(money=="2"){
                item.money="5-10 万元";
              }else if(money==""){
                item.money="10-15 万元";
              }else if(money=="4"){
                item.money="15-20 万元";
              }else if(money=="5"){
                item.money="20 万元以上";
              }else{
                item.money="0-5 万元";
              }
              let job=item.job;
              if(job=="0"){
                item.job="行政/秘书";
              }else if(job=="1"){
                item.job="艺术/创意/表演";
              }else if(job=="2"){
                item.job="执行管理";
              }else if(job=="3"){
                item.job="财务/会计/房地产";
              }else if(job=="4"){
                item.job="劳工/建筑";
              }else if(money=="5"){
                item.job="法律";
              }else if(job=="6"){
                item.job="医疗/牙科/兽医/健身";
              }else if(job=="7"){
                item.job="政治/政府/公务员/军事";
              }else if(job=="8"){
                item.job="零售/食品服务";
              }else if(job=="9"){
                item.job="销售与市场营销";
              }else if(job=="10"){
                item.job="学生";
              }else if(money=="11"){
                item.job="教育/教师/教授";
              }else if(job=="12"){
                item.job="技术/科学/计算机/工程";
              }else if(job=="13"){
                item.job="旅行/款待/交通";
              }else if(job=="14"){
                item.job="建筑/室内设计";
              }else if(money=="15"){
                item.job="其他职业";
              }else{
                item.job="其他职业";
              }
            })

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
      handleSearch(){
        switch(this.selectNo){
          case 1:
            this.para = {"pageNo":1, "pageSize":this.pageSize,cnd:['like',['$','patientName'],['s',this.text]]};
            break;
          case 2:
            this.para = {"pageNo":1, "pageSize":this.pageSize,cnd:['eq',['$','gender'],['s',this.SelData2]]};
            break;
          case 3:
            this.para = {"pageNo":1, "pageSize":this.pageSize,cnd:['like',['$','idCard'],['s',this.text]]};
            break;
          case 4:
            this.para = {"pageNo":1, "pageSize":this.pageSize,cnd:['eq',['$','marriage'],['s',this.SelData4]]};
            break;
        }
        this.getTableData();
      },

    },
    mounted() {
      this.getTableData();
    }
  }

</script>

<style scoped>

</style>
