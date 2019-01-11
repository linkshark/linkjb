
<template>
  <div class="echartsZhe">
    <section style="margin-top:0px;">
      <el-container >
        <div class="Box3">
          <div style="border:solid 0px #f00;font-size:16px;font-weight:bold;color:#33CCFF;font-family:Microsoft Yahei;margin-left:10px;float:left;">
            <img style="vertical-align: middle;"  src="../../assets/images/ic_jrmzl.png"/>&nbsp;&nbsp;最近一周配对人数变化
          </div>
          <div  :style="{height:'350px',width:'400px',}" ref="zheEchart"></div>
        </div><!--end Box3-->
      </el-container>
    </section>
  </div>
</template>
<script>
  import echarts from "echarts";
  export default {
    name: "showZhe",
    props: ["userJson"],
    data() {
      return {
        chart: null,
        dataShow:[341,231,321,223,21,89,193]
      };
    },
    mounted() {
      this.chinaConfigure();
    },
    beforeDestroy() {
      if (!this.chart) {
        return;
      }
      this.chart.dispose();
      this.chart = null;
    },
    methods: {
      tab(){
        var oDate = new Date();   //获取当前时间
        var dayArr = [oDate];     //定义一个数组存储所以时间
        var dayArr1=[];
        for(var i=1;i<7;i++){
          dayArr.push(new Date(oDate.getFullYear(),oDate.getMonth(),oDate.getDate() - i));   //把未来几天的时间放到数组里
        }
        for(var j=0;j<dayArr.length;j++){
          var y = dayArr[j].getFullYear();
          var m = dayArr[j].getMonth() + 1;
          m = m < 10 ? '0' + m : m;
          var d = dayArr[j].getDate();
          d = d < 10 ? ('0' + d) : d;
          dayArr1.push(m + '-' + d);
        }
        return dayArr1.reverse();   //返回一个数组。
      },
      chinaConfigure() {
        let myChart = echarts.init(this.$refs.zheEchart); //这里是为了获得容器所在位置
        window.onresize = myChart.resize;
        myChart.setOption({ // 进行相关配置
          tooltip : {
            trigger: 'axis'
          },
          legend: {
            data:['']
          },
          calculable : true,
          xAxis : [
            {
              type : 'category',
              boundaryGap : false,
              data : this.tab(),
              axisLine: {
                onZero: false,
                lineStyle: {
                  color: '#3C3CC4'
                }
              },
            }
          ],
          yAxis : [
            {
              type : 'value',
              axisLabel : {
                formatter: '{value}'
              },
              axisLine: {
                onZero: false,
                lineStyle: {
                  color: '#3C3CC4'
                }
              },
            }
          ],
          series : [
            {
              name:'配对人数',
              type:'line',
              data:this.dataShow,
              itemStyle: {
                normal: {
                  color: "#2ec7c9",
                  lineStyle: {
                    color: "#2ec7c9"
                  }
                }
              },
              markPoint : {
                data : [
                  {type : 'max', name: '最大值'},
                  {type : 'min', name: '最小值'}
                ]
              }
            }
          ]
        })
      }
    }
  }
</script>
<style scoped>
  .echartsZhe{
    margin-left: 0px;
    background-image: url("../../assets/images/bg4.png");
  }

</style>
