
<template>
  <div class="echartsYuan">
    <section style="margin-top:0px;">
      <el-container >
        <div>
          <div style="border:solid 0px #f00;font-size:16px;font-weight:bold;color:#33CCFF;font-family:Microsoft Yahei;margin-left:10px;float:left;">
            <img style="vertical-align: middle;"  src="../../assets/images/ic_jbfx.png"/>&nbsp;&nbsp;注册人数学历展示
          </div>
          <div :style="{height:'350px',width:'400px',}" ref="yuanEchart"></div>
        </div>
      </el-container>
    </section>
  </div>
</template>
<script>
  import echarts from "echarts";
  export default {
    name: "showYuan",
    props: ["userJson"],
    data() {
      return {
        chart: null
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
      chinaConfigure() {
        let myChart = echarts.init(this.$refs.yuanEchart); //这里是为了获得容器所在位置
        window.onresize = myChart.resize;
        myChart.setOption({ // 进行相关配置
          tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
          },
          legend: {
            orient : 'horizontal',  //布局  纵向布局 图例标记居文字的左边 vertical则反之
            width:40,      //图行例组件的宽度,默认自适应
            x : 'left',   //图例显示在右边
            y: 'center',   //图例在垂直方向上面显示居中
            itemWidth:10,  //图例标记的图形宽度
            itemHeight:10, //图例标记的图形高度
            textStyle: {
              color: "white",
              fontSize: 13,
            },
            data: ['未曾上过学', '小学', '初中', '高中', '本科', '硕士', '博士'],
          },
          series : [
            {
              name: '学历分布',
              type: 'pie',
              radius : '50%',
              center: ['60%', '55%'],
              data:[
                {value:335, name:'未曾上过学'},
                {value:310, name:'小学'},
                {value:234, name:'初中'},
                {value:135, name:'初中'},
                {value:1548, name:'高中'},
                {value:234, name:'本科'},
                {value:135, name:'硕士'},
                {value:1548, name:'博士'}
              ],
              itemStyle: {
                normal:{
                  show:true,
                  textStyle : {
                    fontWeight : 300 ,
                    fontSize : 16    //文字的字体大小
                  },
                  label : {  //饼图图形上的文本标签
                    show: true,  //平常不显示
//                                 position:'inside', //标签的位置
                    position:'outside',
                    formatter:'{d}%'
                  },
                },
                emphasis: {
                  shadowBlur: 10,
                  shadowOffsetX: 0,
                  shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
              }
            }
          ]
        })
      }
    }
  }
</script>
<style scoped>
  .echartsYuan{
    margin-left: 0px;
    background-image: url("../../assets/images/bg4.png");
  }

</style>
