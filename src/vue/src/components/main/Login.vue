<template>
  <section>
    <el-container>
        <el-form label-width="120px" :model="User" :rules="Rules" ref="User" >
          <el-row>
            <el-col>
              <el-form-item label="账号" prop="name">
                <el-input v-model.number="User.name"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col>
              <el-form-item label="密码" prop="pass">
                <el-input type="password" v-model="User.pass" auto-complete="new-password"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col>
              <el-form-item label="记住密码" prop="delivery">
                <el-switch v-model="User.delivery"></el-switch>
              </el-form-item>
            </el-col>
          </el-row>
          <span class="pwwd">
            <a @click="pwdGet" style="cursor: pointer;">忘记密码？</a>
            <a @click="returnBack(1)" style="cursor: pointer;">还没账号,注册一个</a>
          </span>
        </el-form>

    </el-container>
    <div slot="footer" class="dialog-footer">
      <el-button  @click.native="returnBack">取 消</el-button>
      <el-button type="primary" @click="submitForm()">登 录</el-button>
    </div>
  </section>
</template>

<script>
  export default {
    name: 'LoginName',
    data() {
      return {
        User: {
          name: '',
          pass: '',
          delivery:false,
          beDisabled: true,
          myflag:'ok',
          socket:'',
          t:''
        },
        Rules:{

        }
      }
    },
    mounted(){

    },
      methods:{
        pwdGet(){
          alert("？？？");
        },
        resetForm() {
          this.User.name = '';
          this.User.pass = '';
          this.User.delivery = false;
        },
        returnBack(res){
          let _this=this;
          if(res=="" || res==null){
            _this.$emit('cancle',_this.myflag);
            _this.$router.replace({
              path:'/home/supplierAllBack',
              name:'supplierAllBack'
            })
          }else if(res==1){
            _this.$emit('cancle',1);
          }

        },
        clearO(row) {
          this.ResidentForm = {
            name: '',
            pass: '',
            delivery: false
          };
        },
        submitForm(){

          let _this=this;
          //与父组件通信传值
          //this.$emit('showState', [this.beShow,this.formName.user])
          //提交user password
          let params = new URLSearchParams();
          let user = _this.User.name,
              password = _this.User.pass;
          params.append('userName', user);
          params.append('pw', password);
          _this.$http.post('/login/login',params).then((res)=>{

            if(res.data.status == 200){
              _this.regInfo = res.data;
              window.sessionStorage.userInfo = JSON.stringify(_this.regInfo.entity);
              sessionStorage.setItem("userid",JSON.parse(JSON.stringify(_this.regInfo.entity)).id);
              _this.$message({
                message: '登陆成功',
                type: 'success',
                duration: 1000
              });
              /*this.openClick(_this.regInfo.entity.id);*/
              _this.returnBack();
              //this.$store.commite('isLogin',res.data.status);
              //_this.$router.push('/login');
            }else{
              _this.$message({
                showClose: true,
                message: '账号或密码有误！',
                type: 'error',
                duration:2000
              });
            }
          },(err)=>{
            console.log(err);
          });
        },
        inputBlur(errorItem, inputContent) {
          if (errorItem === 'user') {
            if (inputContent === '') {
              this.User.nameError = '用户名不能为空'
            } else {
              this.User.nameError = '';
            }
          } else if (errorItem === 'password') {
            if (inputContent === '') {
              this.User.passError = '密码不能为空'
            } else {
              this.User.passError = '';
            }
          }
          //对于按钮的状态进行修改
          if (this.User.user != '' && this.User.password != '') {
            this.User.beDisabled = false;
          } else {
            this.User.beDisabled = true;
          }
        }
      }
    }

</script>
<style>
  html,body{
    margin: 0;
    padding: 0;
    position: relative;
  }
  .dialog{
    width: 100%;
    height: 100%;
    background: rgba(0,0,0,.8);
  }
  .loginPage{
    position: absolute;
    top: 50%;
    left: 50%;
    margin-top: -100px;
    margin-left: -175px;
    width: 350px;
    min-height: 300px;
    padding: 30px 20px 20px;
    border-radius: 8px;
    box-sizing: border-box;
    background-color: #fff;
  }
  .loginPage p{
    color: red;
    text-align: left;
  }
  .pwwd{
    position: relative;
    left:100px;
  }
  .dialog-footer{
    position: relative;
    margin-left:100px;
  }
</style>
