
<template>
  <div class="echartsPai">
    <section>
      <el-container >
        <div>
          <div style="font-size:16px;font-weight:bold;color:#33CCFF;text-align: left;">
            <img style="vertical-align: middle;"  src="../../assets/images/ic_jbpm.png"/>&nbsp;&nbsp;职业配对信息展示(前十)
          </div>
          <div style="width: 400px;padding-top:10%;">
            <table class="person_list_img" style="width:100%;height:100%;margin:auto;border-collapse:separate;border-spacing:4px 0px;">
              <thead>
                <tr class="name_1 f14 myted">
                  <th style="width:60px;">序号</th>
                  <th>会员职业</th>
                  <th style="width:60px;">配对数</th>
                </tr>
              </thead>
              <tbody  id="perlist" class="name_1 f14">

              </tbody>
            </table>
          </div>
        </div>
      </el-container>
    </section>
  </div>
</template>
<script>
  export default {
    name: "showPai",
    props: ["userJson"],
    data() {
      return{
        dataNum:[{Index:1,
          job:'财务/会计/房地产',
          count:5000
        },{Index:2,
          job:'法律',
          count:3521
        },{Index:3,
          job:'零售/食品服务',
          count:3314
        },{Index:4,
          job:'教育/教师/教授',
          count:3025
        },{Index:5,
          job:'政治/政府/公务员/军事',
          count:2365
        },{Index:6,
          job:'技术/科学/计算机/工程',
          count:2145
        },{Index:7,
          job:'建筑/室内设计',
          count:1854
        },{Index:8,
          job:'艺术/创意/表演',
          count:1654
        },{Index:9,
          job:'执行管理',
          count:1354
        },{Index:10,
          job:'销售与市场营销',
          count:1154
        },

        ]
      }
    },
    mounted() {
      this.addShow();
    },
    methods: {
      addShow(){
        let _this=this;
        let str="";
        if(_this.dataNum) {
          _this.dataNum.forEach(function (item, i) {
            if ((item != null)) {
              if (item.Index % 2 == 0) {
                str += "<tr style=\'background:#5E6BA2;\'>" +
                  "<td>" + item.Index + "</td>" +
                  "<td>" + item.job + "</td>" +
                  "<td>" + item.count + "</td>" +
                  "</tr>";
              } else {
                str += "<tr>" +
                  "<td>" + item.Index + "</td>" +
                  "<td>" + item.job + "</td>" +
                  "<td>" + item.count + "</td>" +
                  "</tr>";
              }

            }
          });
          $("#perlist").html(str);
        }
      }
    }
  }
</script>
<style scoped>
  .echartsMap{
    margin-left: 0px;
    background-image: url("../../assets/images/bg4.png");
  }
  .person_list_img { border-collapse:collapse; font-size:12px;}
  th { background:#012DAF; color:#FFFFFF; line-height:20px; height:25px;padding-right:1px;}
  #perlist tr:hover { background:#bcd4ec;} /*这个将是鼠标高亮行的背景色*/

  .person_list_img td { text-align:center;vertical-align:middle;height:25px;color:white;padding-right:1px;}
  .name_1{ color:#fff; margin-top:2px;}
  .myted th{ text-align:center;}
  .f14{ font-size:14px;}
</style>
