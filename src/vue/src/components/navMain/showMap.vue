
<template>
  <div class="echartsMap">
    <section style="margin-top:0px;">
      <el-container >
        <div :style="{height:'700px',width:'100%'}" ref="myEchart"></div>
      </el-container>
    </section>
  </div>
</template>
<script>
  import echarts from "echarts";
  import '../../../node_modules/echarts/map/js/china.js' // 引入中国地图数据
  export default {
    name: "showMap",
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
        let myChart = echarts.init(this.$refs.myEchart); //这里是为了获得容器所在位置
        window.onresize = myChart.resize;
        myChart.setOption({ // 进行相关配置
          //backgroundColor: "#017EB4",
          tooltip: {}, // 鼠标移到图里面的浮动提示框

          visualMap: { //左侧小柱子的配置
            min: 0, // 最小值
            max:10000, //最大值
            left: 'left', // 定位左边
            top: 'bottom', // 定位底部
            text: ['高', '低'], // 上下两个文字
            seriesIndex: [1],
            inRange: {
              color: ['#e0ffff', '#006edd'] // 深浅颜色
            },
            calculable: true // 显示与否
          },
          geo: { // 这个是重点配置区
            map: 'china', // 表示中国地图
            roam: true,
            label: {
              normal: {
                show: true, // 是否显示对应地名
                textStyle: {
                  color: '#0068A0'
                }
              }
            },
            itemStyle: {
              normal: {
                borderColor: '#0068A0'
              },
              emphasis: {
                areaColor: null,
                shadowOffsetX: 0,
                shadowOffsetY: 0,
                shadowBlur: 20,
                borderWidth: 0,
                shadowColor: '#004881'
              }
            }
          },
          series: [{
            type: 'scatter',
            coordinateSystem: 'geo' // 对应上方配置
          },
            {
              name: '注册人数', // 浮动框的标题
              type: 'map',
              geoIndex: 0,
              data: [{
                "name": "北京",
                "value": 599
              }, {
                "name": "上海",
                "value": 142
              }, {
                "name": "黑龙江",
                "value": 44
              }, {
                "name": "深圳",
                "value": 92
              }, {
                "name": "湖北",
                "value": 810
              }, {
                "name": "四川",
                "value": 453
              }, {
                "name": "安徽",
                "value": 300
              }, {
                "name": "浙江",
                "value": 123
              }, {
                "name": "江苏",
                "value": 678
              }, {
                "name": "香港",
                "value": 765
              }, {
                "name": "澳门",
                "value": 256
              }]
            }
          ]
        })
      }
    }
  }
</script>
<style scoped>
  .echartsMap{
    margin: 0 auto;
    background-image: url("../../assets/images/bg4.png");
  }

</style>
