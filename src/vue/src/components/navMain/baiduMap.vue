<template>
  <div>
    <div>
      <icon name="search" scale="4"></icon>
      <span>寻找附近的对象</span>
      <el-input type="text" id="suggestId" name="address_detail" placeholder="地址" v-model="address_detail" class="input_style">
        <el-button slot="append" icon="el-icon-search" @click="search()">搜索</el-button>
      </el-input>
      <span style="color: #F11938;">(点击搜索显示附近人的坐标)</span>
    </div>
    <div id="allmap">
    </div>
    <remote-js src="http://pv.sohu.com/cityjson?ie=utf-8"></remote-js>
  </div>
</template>
<script>
  import BMap from 'BMap'
  export default{
    components:{
      'remote-js': {
        render(createElement) {
          return createElement('script', { attrs: { type: 'text/javascript', src: this.src }});
        },
        props: {
          src: { type: String, required: true },
        },
      },
    },
    name: "baiduMap",
    data () {
      return{
        address_detail:'',
        userlocation: {lng: "", lat: ""},
        info:"",
        form:[],
        myip:{},
        owninfo:''
      }
    },
    created () {},
    watch: {

    },
    mounted(){
      if(window.sessionStorage.userInfo){
        this.owninfo=JSON.parse(window.sessionStorage.userInfo);
      }else{
        this.owninfo=="";
      }
      this.showDitu();

    },
    methods:{
      showDitu(){
        // 百度地图API功能
        var th = this
        var map = new BMap.Map("allmap");
        var point = new BMap.Point(116.331398,39.897445);
        map.centerAndZoom(point,14);
        map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
        map.addControl(new BMap.NavigationControl());
        map.addControl(new BMap.ScaleControl());
        map.addControl(new BMap.OverviewMapControl());
        map.addControl(new BMap.MapTypeControl());
        function myFun(result){
          th.myip=result.center;
          var cityName = result.name;
          map.setCenter(cityName);
        }
        var myCity = new BMap.LocalCity();
        myCity.get(myFun);
        var ac = new BMap.Autocomplete(    //建立一个自动完成的对象
          {
            "input": "suggestId"
            , "location": map
          })

        var myValue
        ac.addEventListener("onconfirm", function (e) {    //鼠标点击下拉列表后的事件
          var _value = e.item.value;
          myValue = _value.province + _value.city + _value.district + _value.street + _value.business;
          this.address_detail = myValue
          setPlace();
        });

        function setPlace() {
          map.clearOverlays();    //清除地图上所有覆盖物
          function myFun() {
            th.userlocation = local.getResults().getPoi(0).point;    //获取第一个智能搜索的结果
            map.centerAndZoom(th.userlocation, 18);
            map.addOverlay(new BMap.Marker(th.userlocation));    //添加标注
          }

          var local = new BMap.LocalSearch(map, { //智能搜索
            onSearchComplete: myFun
          });
          local.search(myValue);

          //测试输出坐标（指的是输入框最后确定地点的经纬度）
          map.addEventListener("click", function (e) {
            //经度
            console.log(th.userlocation.lng);
            //维度
            console.log(th.userlocation.lat);
          })
        }
      },
      showPeople(data){
        // 百度地图API功能
        var th = this
        var map = new BMap.Map("allmap");
        var point = new BMap.Point(116.331398,39.897445);
        map.centerAndZoom(point,12);
        map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
        var nsip;
        var myCity = new BMap.LocalCity();
        myCity.get(myFun);
        function myFun(result){
          th.myip=result.center;
          var cityName = result.name;
          map.setCenter(cityName);
        }
        // 随机向地图添加25个标注
        /*var bounds = map.getBounds();
        var sw = bounds.getSouthWest();
        var ne = bounds.getNorthEast();
        var lngSpan = Math.abs(sw.lng - ne.lng);
        var latSpan = Math.abs(ne.lat - sw.lat);*/
        for (var i = 0; i < data.length; i ++) {
          var point = new BMap.Point(th.myip.lng +  0.114125544*(Math.random() * 0.7), th.myip.lat -  0.154125544*(Math.random() * 0.7));
          addMarker(point,data[i]);
        }
        var opts={
          width:250,//信息窗口宽度height:100,//信息窗口高度
          title:"他（她）的位置信息是："//信息窗口标题
        }
        // 编写自定义函数,创建标注
        function addMarker(point,info){
          var marker = new BMap.Marker(point);
          map.addOverlay(marker);
          /*var label = new BMap.Label("我是文字标注哦",{offset:new BMap.Size(20,-10)});
          marker.setLabel(label);*/
          var html = [];
          html.push("<div class=\"detailCarTip\" style='width:250px;height:40px;vertical-align:middle;line-height:55px;'>");
          /*html.push("<div style='font-size:14px;font-weight:bold;width:150px;'>" + "(" + "个人信息title" + ")</div>");  //个人信息title
          html.push("<div style='border-bottom:1px solid #ff0000;style='width:350px;height:100px'></div>");     // 红线*/
          html.push("<a href=\"#link-to-user-home\"><img width=\"48\" height=\"48\" class=\"am-comment-avatar\"  src=\"" +
            info.imageUrl +
            "\"" +
            ">" +"<span height=\"48\" >"+"&nbsp;&nbsp;&nbsp;"+info.name+"&nbsp;"+info.age+"岁&nbsp;"+(info.gender=="1"?'男':'女')+"</span>");
          html.push("</div>");
          var infoWindow=new BMap.InfoWindow(html.join(""),opts);//创建信息窗口对象
          marker.addEventListener("click",function(){
            map.openInfoWindow(infoWindow,point);//打开信息窗口
          });
        }
      },
      search(){
        if(this.owninfo==""){
          this.$message({
            showClose: true,
            message: '请先登录!!!',
            type: 'warning'
          });
          return;
        }
        var ip = returnCitySN["cip"];
        this.showInfo(ip);
      },
      showInfo(ip){
        debugger
        let _this=this;
        _this.info=JSON.parse(window.sessionStorage.userInfo);
        let params = new URLSearchParams();
        params.append('userId', _this.info.id);
        params.append('IPAddress', ip);
        params.append('findFlag', _this.info.findFlag);
        _this.$http.post('/login/getUserByIPAddress',params).then((res)=>{
          debugger
          if(res.data.status == 200){
            _this.form=res.data.list;
            if(res.data.list.length>0){
              _this.showPeople(res.data.list);
            }
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
      }
    }
  }
</script>
<style scoped>
  #allmap {
    width: 1000px;
    height: 400px;
    overflow: hidden;
    margin:10px auto;
    font-family:"微软雅黑";
  }
  .input_style{
    width:30%;
    margin-left:0px;
  }
  svg{
    padding-top: 10px;
    margin-left:-400px;
  }
</style>
