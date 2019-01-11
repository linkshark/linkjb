<template>
  <section style="height: 100%;width: 84%;margin-left:200px;margin-top: -660px;"  @keyup.enter="onEnter">
    <el-row  v-show="!tableFormShow" style="padding-left: 20px;padding-right: 10px">
      <el-col :span="24" class="toolbar" style="padding: 10px 10px 0 10px;">
        <el-form :inline="true">

        </el-form>
      </el-col>
      <el-col :span="24" >
        <el-table :data="tableData" highlight-current-row  border  :style="tableStyle" :height="tableHeight">
          <el-table-column prop="passerName" label="姓名" width="100" >
          </el-table-column>
          <el-table-column prop="passerId" label="id" width="100">
          </el-table-column>
          <el-table-column prop="message" label="信息" width="600">
          </el-table-column>
          <el-table-column prop="createTime" label="时间" width="200">
          </el-table-column>
          <el-table-column
            label="操作" min-width="80" fixed="right">
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

  </section>
</template>

<script>

  export default {
    components: {},
    data() {
      return {

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
        flag:1,
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
        this.$router.push({path: '/search/searchShow',query: { id: row.passerId,ownerid:this.info.id}})
        console.log(row);
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

</style>
