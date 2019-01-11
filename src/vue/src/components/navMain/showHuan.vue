
<template>
  <div class="echartsHuan">
    <section style="margin-top:70px;">
      <el-container >
        <div>
          <div style="border:solid 0px #f00;font-size:16px;font-weight:bold;color:#33CCFF;font-family:Microsoft Yahei;margin-left:10px;float:left;">
            <img style="vertical-align: middle;"  src="../../assets/images/ic_jbfx.png" class="phone"/>&nbsp;&nbsp;配对类型
          </div>
          <div :style="{height:'350px',width:'400px'}" ref="myEchart"></div>
        </div>
      </el-container>
    </section>
  </div>
</template>
<script>
  import echarts from "echarts";
  export default {
    name: "showHuan",
    data() {
      return {
        chart: null
      };
    },
    mounted() {
      this.chinaConfigure();
    },
    beforeDestroy() {

    },
    methods: {
      chinaConfigure(){
        let myChart = echarts.init(this.$refs.myEchart); //这里是为了获得容器所在位置
        window.onresize = myChart.resize;
        myChart.setOption({ // 进行相关配置
          tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b}: {c} ({d}%)"
          },
          legend: {
            orient : 'horizontal',  //布局  纵向布局 图例标记居文字的左边 vertical则反之
            width:40,      //图行例组件的宽度,默认自适应
            x : 'right',   //图例显示在右边
            y: 'top',   //图例在垂直方向上面显示居中
            itemWidth:10,  //图例标记的图形宽度
            itemHeight:10, //图例标记的图形高度
            data: ['ManToWoman', 'ManToMan', 'WomanToMan', 'WomanToWoman'],
            textStyle:{    //图例文字的样式
              color:'white',  //文字颜色
              fontSize:12    //文字大小
            }
          },
          color:['#2ec7c9','#3C3CC4','#F709F7','#FF7F50','#FF7F50'],  //手动设置每个图例的颜色
          series: [
            {
              name: '人员占比',
              type: 'pie',
              center:['55%', '61%'],//中心点占x，y轴长度的百分比
              radius: ['40%', '50%'], //饼图的半径,第一项是内半径,第二项是外半径,内半径为0就是真的饼,不是环形
              avoidLabelOverlap: false,
              label: {
                normal: {
                  formatter: '{d}% ',

                },
                emphasis: {
                  show: true,
                  textStyle: {
                    fontSize: '30',
                    fontWeight: 'bold'
                  }
                }
              },
              labelLine: {
                normal: {
                  show: false
                }
              },
              data: [
                {value: 335, name: 'ManToWoman'},
                {value: 310, name: 'ManToMan'},
                {value: 234, name: 'WomanToMan'},
                {value: 135, name: 'WomanToWoman'},
              ]
            }]
        });
      }
    }
  }
</script>
<style scoped>
.echartsHuan{
  margin-top: 70px;

}
</style>
