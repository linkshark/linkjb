<template>
  <div>
    <section>
      <div>
        <section style="height: 100%;width: 100%"  @keyup.enter="onEnter">
          <el-container >
            <el-form style="width:99%;">
              <el-row :gutter="24" style="padding-left: 10px;padding-right: 10px;">
                <el-col :span="24" style="padding: 10px 10px 0 10px;">
                  <el-card class="box-card">
                    <span>基本信息展示</span>
                    <el-button style="float: right; padding: 3px 2px;" type="text" @click="returnback">返回</el-button>
                  </el-card>
                </el-col>
              </el-row>
            </el-form>
          </el-container>
        </section>
      </div>
      <div>
        <div class="LBox">
          <el-card class="box-card">
            <div slot="header" class="clearfix">
              <span style="color: green;">基本信息</span>
            </div>
            <div class="text item">
              <el-form :model="form" :label-position="labelPosition" label-width="120px">
                <el-row style="margin-top: 0px;text-align: left;">
                  <el-col :span="24">
                    <el-form-item label="账号" prop="name">
                      <el-input v-model="form.name" placeholder="账号"></el-input>
                    </el-form-item>
                  </el-col>
                </el-row>
                <el-row style="margin-top: 0px;">
                  <el-col :span="24">
                    <el-form-item label="联系方式" prop="mobile">
                      <el-input v-model="form.mobile" placeholder="联系方式"></el-input>
                    </el-form-item>
                  </el-col>
                </el-row>
                <el-row style="margin-top: 0px;">
                  <el-col :span="24">
                    <el-form-item label="出生日期" prop="birthday">
                      <el-date-picker v-model="form.birthday" :picker-options="pickerOptions1" type="date" style="width:100%;" placeholder="出生日期"></el-date-picker>
                    </el-form-item>
                  </el-col>
                </el-row>
                <el-row style="margin-top: 0px;">
                  <el-col :span="24">
                    <el-form-item label="邮箱" prop="email">
                      <el-input v-model="form.email" placeholder="邮箱"></el-input>
                    </el-form-item>
                  </el-col>
                </el-row>
                <el-row style="margin-top: 0px;">
                  <el-col :span="24">
                    <el-form-item label="性别" prop="gender">
                      <el-radio-group v-model="form.gender">
                        <el-radio border label=1>
                          <icon name="man" scale="2"></icon>
                        </el-radio>
                        <el-radio border label=2>
                          <icon name="woman2" scale="2"></icon>
                        </el-radio>
                      </el-radio-group>
                    </el-form-item>
                  </el-col>
                </el-row>
                <el-row style="margin-top: 0px">
                  <el-col :span="24">
                    <el-form-item label="所受教育" prop="education">
                      <el-select v-model="form.education" placeholder="请选择" style="width:100%;">
                        <el-option
                          v-for="item in options"
                          :key="item.value"
                          :label="item.label"
                          :value="item.value">
                        </el-option>
                      </el-select>
                    </el-form-item>
                  </el-col>
                </el-row>
                <el-row style="margin-top: 0px;">
                  <el-col :span="24">
                    <el-form-item label="身高(cm)" prop="tall">
                      <el-input type="text" v-model="form.tall" ></el-input>
                    </el-form-item>
                  </el-col>
                </el-row>
                <el-row style="margin-top: 0px;">
                  <el-col :span="24">
                    <el-form-item label="体重(kg)" prop="weight">
                      <el-input type="text" v-model="form.weight" ></el-input>
                    </el-form-item>
                  </el-col>
                </el-row>
                <el-row style="margin-top: 0px;">
                  <el-col :span="24">
                    <el-form-item label="工作" prop="job">
                      <el-select v-model="form.job" placeholder="请选择" style="width:100%;">
                        <el-option
                          v-for="item in jobOptions"
                          :key="item.value"
                          :label="item.label"
                          :value="item.value">
                        </el-option>
                      </el-select>
                    </el-form-item>
                  </el-col>
                </el-row>
                <el-row style="margin-top: 0px;">
                  <el-col :span="24">
                    <el-form-item label="收入(年薪/万元)" prop="money">
                      <el-select v-model="form.money" placeholder="请选择" style="width:100%;">
                        <el-option
                          v-for="item in moneyOptions"
                          :key="item.value"
                          :label="item.label"
                          :value="item.value">
                        </el-option>
                      </el-select>
                    </el-form-item>
                  </el-col>
                </el-row>
                <el-row style="margin-top: 0px;">
                  <el-col :span="24">
                    <el-form-item label="是否结过婚" prop="marriageFlag">
                      <el-radio-group v-model="form.marriageFlag">
                        <el-radio border label="1">结过</el-radio>
                        <el-radio border label="2">从未</el-radio>
                      </el-radio-group>
                    </el-form-item>
                  </el-col>
                </el-row>
                <el-row style="margin-top: 0px;">
                  <el-col :span="24">
                    <el-form-item label="是否喝酒" prop="drink">
                      <el-radio-group v-model="form.drink">
                        <el-radio border label="1">经常</el-radio>
                        <el-radio border label="2">从不</el-radio>
                      </el-radio-group>
                    </el-form-item>
                  </el-col>
                </el-row>
                <el-row style="margin-top: 0px;">
                  <el-col :span="24">
                    <el-form-item label="是否抽烟" prop="smoke">
                      <el-radio-group v-model="form.smoke">
                        <el-radio border label="1">经常</el-radio>
                        <el-radio border label="2">从不</el-radio>
                      </el-radio-group>
                    </el-form-item>
                  </el-col>
                </el-row>
                <el-row style="margin-top: 0px;">
                  <el-col :span="24">
                    <el-form-item label="是否想要孩子" prop="child">
                      <el-radio-group v-model="form.child">
                        <el-radio border label="1">经常</el-radio>
                        <el-radio border label="2">从不</el-radio>
                      </el-radio-group>
                    </el-form-item>
                  </el-col>
                </el-row>
                <el-row style="margin-top: 0px;">
                  <el-col :span="24">
                    <el-form-item label="是否运动" prop="sport">
                      <el-radio-group v-model="form.sport">
                        <el-radio border label="1">经常</el-radio>
                        <el-radio border label="2">从不</el-radio>
                      </el-radio-group>
                    </el-form-item>
                  </el-col>
                </el-row>
              </el-form>
            </div>
          </el-card>
        </div>
        <div class="CBox">
          <el-card class="box-card">
            <div slot="header" class="clearfix">
              <span style="color: green;">期望对方</span>
            </div>
            <div class="text item">
              <el-form :model="form" :label-position="labelPosition" label-width="120px">
                <el-row style="margin-top: 270px;">
                  <el-col :span="24">
                    <el-form-item label="性别" prop="findGender">
                      <el-radio-group v-model="form.findGender">
                        <el-radio border label="1">
                          <icon name="man" scale="2"></icon>
                        </el-radio>
                        <el-radio border label="2">
                          <icon name="woman2" scale="2"></icon>
                        </el-radio>
                      </el-radio-group>
                    </el-form-item>
                  </el-col>
                </el-row>
                <el-row style="margin-top: 0px;">
                  <el-col :span="24">
                    <el-form-item label="教育" prop="findEdu">
                      <el-select v-model="form.findEdu" placeholder="请选择" style="width:100%;">
                        <el-option
                          v-for="item in options"
                          :key="item.value"
                          :label="item.label"
                          :value="item.value">
                        </el-option>
                      </el-select>
                    </el-form-item>
                  </el-col>
                </el-row>
                <el-row style="margin-top: 0px;">
                  <el-col :span="24">
                    <el-form-item label="身高(cm)" prop="findTall">
                      <el-input type="text" v-model="form.findTall" ></el-input>
                    </el-form-item>
                  </el-col>
                </el-row>
                <el-row style="margin-top: 0px;">
                  <el-col :span="24">
                    <el-form-item label="体重(kg)" prop="findWeight">
                      <el-input type="text" v-model="form.findWeight" ></el-input>
                    </el-form-item>
                  </el-col>
                </el-row>
                <el-row style="margin-top: 0px;">
                  <el-col :span="24">
                    <el-form-item label="工作" prop="findJob">
                      <el-select v-model="form.findJob" placeholder="请选择" style="width:100%;">
                        <el-option
                          v-for="item in jobOptions"
                          :key="item.value"
                          :label="item.label"
                          :value="item.value">
                        </el-option>
                      </el-select>
                    </el-form-item>
                  </el-col>
                </el-row>
                <el-row style="margin-top: 0px;">
                  <el-col :span="24">
                    <el-form-item label="收入(年薪/万元)" prop="findMoney">
                      <el-select v-model="form.findMoney" placeholder="请选择" style="width:100%;">
                        <el-option
                          v-for="item in moneyOptions"
                          :key="item.value"
                          :label="item.label"
                          :value="item.value">
                        </el-option>
                      </el-select>
                    </el-form-item>
                  </el-col>
                </el-row>
                <el-row style="margin-top: 0px;">
                  <el-col :span="24">
                    <el-form-item label="是否结过婚" prop="findMarriageFlag">
                      <el-radio-group v-model="form.findMarriageFlag">
                        <el-radio border label="1">结过</el-radio>
                        <el-radio border label="2">从未</el-radio>
                      </el-radio-group>
                    </el-form-item>
                  </el-col>
                </el-row>
                <el-row style="margin-top: 0px;">
                  <el-col :span="24">
                    <el-form-item label="是否喝酒" prop="findDrink">
                      <el-radio-group v-model="form.findDrink">
                        <el-radio border label="1">经常</el-radio>
                        <el-radio border label="2">从不</el-radio>
                      </el-radio-group>
                    </el-form-item>
                  </el-col>
                </el-row>
                <el-row style="margin-top: 0px;">
                  <el-col :span="24">
                    <el-form-item label="是否抽烟" prop="findSmoke">
                      <el-radio-group v-model="form.findSmoke">
                        <el-radio border label="1">经常</el-radio>
                        <el-radio border label="2">从不</el-radio>
                      </el-radio-group>
                    </el-form-item>
                  </el-col>
                </el-row>
                <el-row style="margin-top: 0px;">
                  <el-col :span="24">
                    <el-form-item label="是否想要孩子" prop="findChild">
                      <el-radio-group v-model="form.findChild">
                        <el-radio border label="1">经常</el-radio>
                        <el-radio border label="2">从不</el-radio>
                      </el-radio-group>
                    </el-form-item>
                  </el-col>
                </el-row>
                <el-row style="margin-top: 0px;">
                  <el-col :span="24">
                    <el-form-item label="是否运动" prop="findSport">
                      <el-radio-group v-model="form.findSport">
                        <el-radio border label="1">经常</el-radio>
                        <el-radio border label="2">从不</el-radio>
                      </el-radio-group>
                    </el-form-item>
                  </el-col>
                </el-row>
              </el-form>
            </div>
          </el-card>
        </div>
        <div class="RBox">
          <el-card class="box-card">
            <div slot="header" class="clearfix">
              <span style="color: green;">基本信息</span>
            </div>
            <div class="text item">

            </div>
          </el-card>
        </div>
      </div>
    </section>
  </div>
</template>
<script>
  import { regionData,CodeToText,TextToCode } from 'element-china-area-data';
  export default {
    data(){
      return{
        imageUrl:'',
        activeNames:'1',
        pickerOptions1:{
          disabledDate(time) {
            let curDate = (new Date()).getTime();
            let eighteen = 18 * 365 * 24 * 3600 * 1000;
            let eighty = curDate - eighteen;
            console.log(time);
            return time.getTime() > eighty;
          }
        },
        labelPosition:'left',
        options: [{
          value: '0',
          label: '未曾上过学'
        },{
          value: '1',
          label: '小学'
        }, {
          value: '2',
          label: '中学'
        }, {
          value: '3',
          label: '高中'
        }, {
          value: '4',
          label: '大专'
        }, {
          value: '5',
          label: '本科'
        }, {
          value: '6',
          label: '硕士'
        }, {
          value: '7',
          label: '博士'
        }],
        jobOptions: [{
          value: '0',
          label: '行政/秘书'
        },{
          value: '1',
          label: '艺术/创意/表演'
        }, {
          value: '2',
          label: '执行管理'
        }, {
          value: '3',
          label: '财务/会计/房地产'
        }, {
          value: '4',
          label: '劳工/建筑'
        }, {
          value: '5',
          label: '法律'
        }, {
          value: '6',
          label: '医疗/牙科/兽医/健身'
        }, {
          value: '7',
          label: '政治/政府/公务员/军事'
        },{
          value: '8',
          label: '零售/食品服务'
        },{
          value: '9',
          label: '销售与市场营销'
        }, {
          value: '10',
          label: '学生'
        }, {
          value: '11',
          label: '教育/教师/教授'
        }, {
          value: '12',
          label: '技术/科学/计算机/工程'
        }, {
          value: '13',
          label: '旅行/款待/交通'
        }, {
          value: '14',
          label: '建筑/室内设计'
        }, {
          value: '15',
          label: '其他职业'
        }],
        moneyOptions: [{
          value: "0",
          label: '0-5'
        },{
          value: "1",
          label: '5-10'
        }, {
          value: "2",
          label: '10-15'
        }, {
          value: "3",
          label: '15-20'
        }, {
          value: "4",
          label: '20以上'
        }],
        mainDatas:[],
        arr:[],
        adoptions: regionData,
        selectedOptions:[],
        addresShow:'',
        form: {
          findSport:'',
          findChild:'',
          findSmoke:'',
          findDrink:'',
          findMarriageFlag:'',
          findMoney:'',
          findJob:'',
          findWeight:'',
          findTall:'',
          findEdu:'',
          address: [],
          name:'',
          mobile:'',
          email:'',
          birthday:'',
          gender:'',
          findGender:'',
          marriageFlag:'',
          tall:'',
          weight:'',
          detail:'',
          sport:'',
          smoke:'',
          child:'',
          education: '0',
          job:'0',
          money:'0',
          imageUrl:'',
          drink:'',

        }
      }
    },
    mounted(){

    },
    methods:{
      returnback(){
        this.$router.push('/navMain/navHeader');
        window.location.reload();
      },
    }
  }
</script>
<style>
  body{ margin: 0px;width: 100%; }
  .LBox,.RBox{width: 30%;position: absolute;top:75px;}
  .CBox{
    margin:0px 32%;
  }
  .LBox{
    left:10px;
  }
  .RBox{
    right:20px;
  }
  .text {
    font-size: 14px;
  }

  .item {
    margin-bottom: 18px;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }
  .clearfix:after {
    clear: both
  }
</style>
