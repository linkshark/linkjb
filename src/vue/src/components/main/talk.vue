<template>
  <div class="am-cf admin-main" >
    <!-- content start -->
    <div class="admin-content">
      <div class="" style="width: 80%;float:left;">
        <!-- 聊天区 -->
        <div class="am-scrollable-vertical" id="chat-view" style="height: 210px;">
          <ul class="am-comments-list am-comments-list-flip" id="chat">

          </ul>
        </div>
        <!-- 输入区 -->
        <div class="am-form-group am-form">
          <textarea class="" id="message" name="message" rows="5"  placeholder="这里输入你想发送的信息..."  @keyup.enter="onEnter"></textarea>
        </div>
        <!-- 接收者 -->
        <div class="" style="float: left">
          <p class="am-kai">发送给 : <span id="sendto">全体成员</span><button class="am-btn am-btn-xs am-btn-danger" @click="resets()">复位</button></p>
        </div>
        <!-- 按钮区 -->
        <div class="am-btn-group am-btn-group-xs" style="float:right;">
          <button class="am-btn am-btn-default" type="button" @click="getConnection()"><span class="am-icon-plug"></span> 连接</button>
          <button class="am-btn am-btn-default" type="button" @click="closeConnection()"><span class="am-icon-remove"></span> 断开</button>
          <button style="display: none;" class="am-btn am-btn-default" type="button" @click="checkConnection()"><span class="am-icon-bug"></span> 检查</button>
          <button class="am-btn am-btn-default" type="button" @click="clearConsole()"><span class="am-icon-trash-o"></span> 清屏</button>
          <button class="am-btn am-btn-default" type="button" @click="sendMessage()"><span class="am-icon-commenting"></span> 发送</button>
        </div>
      </div>
      <!-- 列表区 -->
      <div class="am-panel am-panel-default" id="showlist" style="float:left;width: 20%;" >
        <div class="am-panel-hd">
          <h3 class="am-panel-title">在线列表 [<span id="onlinenum"></span>]</h3>
        </div>
        <div class="am-panel am-panel-default" style="overflow-y:auto;height: 300px;">
          <!--<ul class="am-list am-list-static am-list-striped" >
            <li>图灵机器人 <button class="am-btn am-btn-xs am-btn-danger" id="tuling" data-am-button>未上线</button></li>
          </ul>-->
          <ul class="am-list am-list-static am-list-striped" id="list" v-for="(item,index) in mylist">
            <el-popover
              placement="right"
              width="60"
              trigger="hover"
            >
              <div>
                <span style="padding-left: 5px;">
                  <img :src="imageUrl" class="self-img" style="display: inline-block;vertical-align: center;margin-top: -10px;">
                </span>
                <span style="margin-left: 40px;">
                  &nbsp; {{infosex}} &nbsp;{{infoage}}岁
                </span>
              </div>
            <li slot="reference" style="margin:5px 0;">
              <span style="cursor: pointer"  @mouseenter="enter(item.userid)" @mouseleave="leave()">{{item.nickname}}</span>
              <button type="button" id="siliao" class="am-btn am-btn-xs am-btn-primary am-round" @click="addChat(item)">
                <icon name="phone1" scale="1.5"></icon> 私聊
              </button>
            </li>
            </el-popover>
          </ul>
        </div>
      </div>
    </div>
    <!--<div>
      <audio class="success"
             src="/src/images/message.mp3" id="audio">
      </audio>
    </div>-->
  </div>
</template>
<script>
  import WebsocketHeartbeatJs from 'websocket-heartbeat-js';
  import $ from 'jquery';
  export default {
    name:'talk',
    data(){
      return{
        visible2:false,
        imageUrl:'',
        infoname:'',
        infoage:'',
        infosex:'',
        wsServer:"ws://www.linkjb.com/linkjb/chatServer/"+sessionStorage.getItem("userid"),
        ws:'',
        mes:'',
        userid:'',
        urlImg:'',
        mylist:[],
        showid:'',
        showMess:{},
        name:'',
        shownum:0,
        heartCheck:{
          timeout: 60000,//60ms
          timeoutObj: null,
          serverTimeoutObj: null,
          reset: function(){
            clearTimeout(this.timeoutObj);
            clearTimeout(this.serverTimeoutObj);
            this.start();
          },
          start: function(){
            var self = this;
            this.timeoutObj = setTimeout(function(){
              ws.send("HeartBeat");
              self.serverTimeoutObj = setTimeout(function(){
                ws.close();//如果onclose会执行reconnect，我们执行ws.close()就行了.如果直接执行reconnect 会触发onclose导致重连两次
              }, self.timeout)
            }, this.timeout)
          },
        },
      }
    },
    mounted(){
      this.userid=JSON.parse(window.sessionStorage.userInfo).id;
      this.name=JSON.parse(window.sessionStorage.userInfo).name;
      this.init();
      this.connector();
    },
    methods:{
      enter(uuid){
        let _this=this;
        let params = new URLSearchParams();
        params.append('id',uuid);
        _this.$http.post('/login/findUserById',params).then((res)=>{
          if(res.data.status == 200){

            _this.imageUrl='';
            _this.infoname='';
            _this.infoage='';
            _this.infosex='';
            _this.imageUrl=res.data.entity.imageUrl;
            _this.infoname=res.data.entity.name;
            _this.infoage=res.data.entity.age;
            _this.infosex=res.data.entity.gender=='1'?'男':'女';
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
        this.visible2=true;
      },
      leave(){
        this.visible2=false;
      },
      showImg(imageUrl){

        this.urlImg=imageUrl;
        console.log(this.urlImg);
      },
      init(){
        /*$("#tuling").click(function(){
          let _this=this;
          var onlinenum = $("#onlinenum").text();
          if($(this).text() == "未上线"){
            $(this).text("已上线").removeClass("am-btn-danger").addClass("am-btn-success");
            alert('图灵机器人加入聊天室');
            $("#onlinenum").text(parseInt(onlinenum) + 1);
          }else{
            $(this).text("未上线").removeClass("am-btn-success").addClass("am-btn-danger");
            alert('图灵机器人离开聊天室');
            $("#onlinenum").text(parseInt(onlinenum) - 1)
          }
        });*/
      },
      getUrlt(){

        let _this=this;
        let params = new URLSearchParams();
        if(_this.showMess.from){
          params.append('id',_this.showMess.from.userid);
          _this.$http.post('/login/findUserById',params).then((res)=>{
            if(res.data.status == 200){

              sessionStorage.setItem("imgt","");
              sessionStorage.setItem("imgt",res.data.entity.imageUrl);
              if(_this.showMess.from.userid != _this.userid){
                debugger
                if(_this.showMess.to!=""){
                  if(_this.showMess.to==_this.name){
                    if (window.Notification) {
                      var ua = navigator.userAgent.toLowerCase();
                      if (ua.indexOf('safari') != -1) {
                        if (ua.indexOf('chrome') > -1) {
                          if(ua.indexOf('mozilla') > -1){
                            if(ua.indexOf('applewebkit') > -1){
                              // Chrome
                              Notification.requestPermission().then(function (permission) {
                                if (permission == "granted") {
                                  console.log("ws收到消息");
                                  var notification = new Notification('通知', {
                                    body: _this.showMess.content,
                                    icon: sessionStorage.getItem("imgt"),
                                    requireInteraction:true
                                  });
                                  /*const audio = document.getElementById('audio');
                                  audio.play();*/
                                  setTimeout(function () {
                                    notification.close();
                                  },1000*60*24)
                                  notification.onclick = function () {
                                    console.log('点击');
                                    notification.close();
                                  };
                                } else {
                                  Notification.requestPermission();
                                  console.log('没有权限,用户拒绝:Notification');
                                }
                              });
                            }
                          }
                        }
                      }
                    } else {
                      console.log('不支持Notification');
                    }
                  }
                }
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
      },
      connector(){
        let _this=this;
        /*const options = {
          url: _this.wsServer,
          pingTimeout: 60000,
          pongTimeout: 60000,
          reconnectTimeout: 60000,
          pingMsg: "heartbeat"
        }
        _this.ws = new WebsocketHeartbeatJs(options);*/
        _this.wsServer = "ws://www.linkjb.com/linkjb/chatServer/"+sessionStorage.getItem("userid");
        _this.ws = new WebSocket(_this.wsServer); //创建WebSocket对象
        _this.ws.onopen = function (evt) {
          _this.$message({
            message: '已经建立连接',
            type: 'success',
            duration: 2000
          });
        };
        _this.ws.onmessage = function (evt) {
          _this.analysisMessage(evt.data);  //解析后台传回的消息,并予以展示
          if(JSON.parse(evt.data).list){
            let len=JSON.parse(evt.data).list.length;
            this.mes=JSON.parse(evt.data).message.content;
            $("#onlinenum").text(len)
          }
          _this.getUrlt();
        };
        _this.ws.onerror = function (evt) {
          _this.$message({
            message: '产生异常',
            type: 'error',
            duration: 2000
          });
        };
        _this.ws.onclose = function (evt) {
          _this.$message({
              message: '长时间未操作，已关闭连接',
              type: 'success',
              duration: 2000
            });
          _this.ws="";
        };
       /* _this.ws.onreconnect = (e) => {
          console.log('reconnecting...');
        }*/
      },
      /**
       * 连接
       */
      getConnection(){
        let _this = this;
        if(_this.ws == ""){
          _this.ws = new WebSocket(_this.wsServer); //创建WebSocket对象
          _this.ws.onopen = function (evt) {
            _this.$message({
              message: '已经建立连接',
              type: 'success',
              duration: 2000
            });
          };
          _this.ws.onmessage = function (evt) {
            _this.analysisMessage(evt.data);  //解析后台传回的消息,并予以展示
            if(JSON.parse(evt.data).list){
              let len=JSON.parse(evt.data).list.length;
              this.mes=JSON.parse(evt.data).message.content;
              $("#onlinenum").text(len)
            }
          };
          _this.ws.onerror = function (evt) {
            this.$message({
              message: '产生异常',
              type: 'error',
              duration: 2000
            });
          };
          _this.ws.onclose = function (evt) {
            _this.$message({
              message: '长时间未操作，已关闭连接',
              type: 'success',
              duration: 2000
            });
            _this.ws="";
          };
        }else{
          _this.$message({
            message: '连接已存在',
            type: 'warning',
            duration: 2000
          });
        }
      },
      /**
       * 关闭连接
       */

      closeConnection(){

        let _this=this;
        if(_this.ws != ""){
          _this.ws.close();
          _this.ws = "";
          //$("#list").html("");    //清空在线列表
          _this.$message({
            message: '已经关闭连接',
            type: 'success',
            duration: 2000
          });
        }else{
          _this.$message({
            message: '未开启连接',
            type: 'warning',
            duration: 2000
          });
        }
      },
      /**
       * 检查连接
       */
      checkConnection(){
        if( this.ws != null){
          if(this.ws.readyState == 0){
            this.$message({
              message: '产生异常',
              type: 'error',
              duration: 2000
            });
          }else{
            this.$message({
              message: '连接正常',
              type: 'success',
              duration: 2000
            });
          }
        }else{
          this.$message({
            message: '连接未开启',
            type: 'warning',
            duration: 2000
          });
        }
      },
      /**
       * 清空聊天区
       */
      clearConsole(){
        $("#chat").html("");
      },
      onEnter(ev){
        debugger
        if(ev.keyCode==13){
          var message = $("#message").val();
          if(message == null || message == "↵" || message == ""){
            this.$message({
              message: '请不要惜字如金',
              type: 'warning',
              duration: 2000
            });
            return;
          }
          this.sendMessage();
        }
      },
      /**
       * 发送信息给后台
       */
      sendMessage(){
        debugger
        if(this.ws == ""){
          this.$message({
            message: '连接未开启',
            type: 'warning',
            duration: 2000
          });
          return;
        }
        var message = $("#message").val();
        var to = $("#sendto").text() == "全体成员"? "": this.showid;
        if(message == null || message == ""){
          this.$message({
            message: '请不要惜字如金',
            type: 'warning',
            duration: 2000
          });
          return;
        }
        var uuid=this.userid;
        /*if($("#tuling").text() == "已上线"){
          this.tuling(message);
        }else{
          console.log("图灵机器人未开启");
        }  //检测是否加入图灵机器人*/
        this.ws.send(JSON.stringify({
          message : {
            content : message,
            from : uuid,
            to : to,      //接收人,如果没有则置空,如果有多个接收人则用,分隔
            time : new Date()
          },
          type : "message"
        }));
      },
      getUrl(){

        let _this=this;
        let params = new URLSearchParams();
        params.append('id',_this.showMess.from.userid);
        _this.$http.post('/login/findUserById',params).then((res)=>{
          if(res.data.status == 200){

            sessionStorage.setItem("img","");
            sessionStorage.setItem("img",res.data.entity.imageUrl);
            this.showChat(_this.showMess);
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
      /**
       * 图灵机器人
       * @param message
       */
      tuling(message){
        console.log(message);
        /*var html;
        $.getJSON("http://www.tuling123.com/openapi/api?key=6ad8b4d96861f17d68270216c880d5e3&info=" + message,function(data){
          if(data.code == 100000){
            html = "<li class=\"am-comment am-comment-primary\"><a href=\"#link-to-user-home\"><img width=\"48\" height=\"48\" class=\"am-comment-avatar\" alt=\"\" src=\"${fuckworld}\"></a><div class=\"am-comment-main\">\n" +
              "<header class=\"am-comment-hd\"><div class=\"am-comment-meta\">   <a class=\"am-comment-author\" href=\"#link-to-user\">Robot</a> 发表于<time> "+this.getDateFull()+"</time> 发送给: ${userid}</div></header><div class=\"am-comment-bd\"> <p>"+data.text+"</p></div></div></li>";
          }
          if(data.code == 200000){
            html = "<li class=\"am-comment am-comment-primary\"><a href=\"#link-to-user-home\"><img width=\"48\" height=\"48\" class=\"am-comment-avatar\" alt=\"\" src=\"${fuckworld}\"></a><div class=\"am-comment-main\">\n" +
              "<header class=\"am-comment-hd\"><div class=\"am-comment-meta\">   <a class=\"am-comment-author\" href=\"#link-to-user\">Robot</a> 发表于<time> "+this.getDateFull()+"</time> 发送给: ${userid}</div></header><div class=\"am-comment-bd\"> <p>"+data.text+"</p><a href=\""+data.url+"\" target=\"_blank\">"+data.url+"</a></div></div></li>";
          }
          $("#chat").append(html);
          var chat = $("#chat-view");
          chat.scrollTop(chat[0].scrollHeight);
          $("#message").val("");  //清空输入区
        });*/
      },
      getDateFull(){
        var date = new Date();
        var currentdate = date.getFullYear() + "-" + this.appendZero(date.getMonth() + 1) + "-" + this.appendZero(date.getDate()) + " " + this.appendZero(date.getHours()) + ":" + this.appendZero(date.getMinutes()) + ":" + this.appendZero(date.getSeconds());
        return currentdate;
      },
      appendZero(s){return ("00"+ s).substr((s+"").length);},  //补0函数
      showNotice(notice){

        $("#chat").append("<div><p class=\"am-text-success\" style=\"text-align:center\"><span class=\"am-icon-bell\"></span> "+notice+"</p></div>");
        var chat = $("#chat-view");
        chat.scrollTop(chat[0].scrollHeight);   //让聊天区始终滚动到最下面
      },
      resets(){
        $("#sendto").text("");
        $("#sendto").text("全体成员");
      },
      analysisMessage(message){

        var message = JSON.parse(message);
        if(message.type == "message"){
          console.log(message)
          this.showMess=message.message;
          this.getUrl();
          //会话消息
        }
        if(message.type == "notice"){
          this.showNotice(message.message.content);
        }
        if(message.list != null && message.list != undefined){      //在线列表
          this.mylist=message.list;
          console.log(this.mylist);
          /*this.showOnline(message.list);*/
        }
      },
      /**
       * 展示会话信息
       */
      showChat(message){

        var to = message.to == null || message.to == ""? "全体成员" : message.to;   //获取接收人
        var isSef = this.userid == message.from.userid ? "am-comment-flip" : "";   //如果是自己则显示在右边,他人信息显示在左边
        var html = "<li class=\"am-comment "+isSef+" am-comment-primary\"><a href=\"#link-to-user-home\"><img width=\"48\" height=\"48\" class=\"am-comment-avatar\"  src=\"" +
          sessionStorage.getItem("img") +
          "\"" +
          ">" +
          "</a><div class=\"am-comment-main\">\n" +
          "<header class=\"am-comment-hd\"><div class=\"am-comment-meta\">   <a class=\"am-comment-author\" href=\"#link-to-user\">"+message.from.nickname+"</a> 发表于<time> "+message.time+"</time> 发送给: "+to+" </div></header><div class=\"am-comment-bd\"> <p>"+message.content+"</p></div></div></li>";

        $("#chat").append(html);
        $("#message").val("");  //清空输入区
        var chat = $("#chat-view");
        chat.scrollTop(chat[0].scrollHeight);   //让聊天区始终滚动到最下面

      },
      /**
       * 展示在线列表
       */
      showOnline(list){
        let _this=this;

        $("#list").html("");    //清空在线列表
        $.each(list, function(index, item){     //添加私聊按钮
          var li = "<li>"+item+"</li>";
/*          if('${userid}' != item){    //排除自己*/
            li = "<li>"+item.nickname+" <button type=\"button\" id=\"siliao\" class=\"am-btn am-btn-xs am-btn-primary am-round\" @click=\"addChat(item)\"><span class=\"am-icon-phone\"><span> 私聊</button></li>";
          /*}*/
          $("#list").append(li);
        });
        $("#onlinenum").text($("#list li").length);     //获取在线人数
      },
      /**
       * 添加接收人
       */
      addChat(user){

        var sendto = $("#sendto");
        if(user.userid!=this.userid){
          sendto.text("");
          this.showid=user.userid;
          sendto.text(user.nickname);
        }
        /*var receive = sendto.text() == "全体成员" ? "" : sendto.text(user.nickname) + ",";
        if(receive.indexOf(user.nickname) == -1){    //排除重复
          sendto.text(receive + user.nickname);
        }*/
      }
    }
  }
</script>
<style scoped>
  .self-img{
    position: absolute;
    width: 40px;
    height: 40px;
    border-radius: 50%;
    margin-left: -17px;
    z-index:999;
    align-items: center;
  }

</style>
