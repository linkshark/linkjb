<template>
  <section class="img1">
    <el-container>
      <el-form :model="form" label-width="120px" :rules="ResidentFormRules" ref="form" style="margin-top: 10px" >
        <el-row>
          <el-col>
            <el-form-item label="账号" prop="name">
              <el-input type="text" v-model="form.name" placeholder="账号" ></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col>
            <el-form-item label="密码" prop="pass">
              <el-input type="password" v-model="form.pass" auto-complete="new-password"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row >
          <el-col>
            <el-form-item label="确认密码" prop="checkPass">
              <el-input type="password" v-model="form.checkPass" auto-complete="new-password"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col>
            <el-form-item label="联系方式" prop="mobile" >
              <el-input type="text" v-model="form.mobile" ></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col>
            <el-form-item label="您的性别" prop="gender">
              <el-radio-group v-model="form.gender">
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
        <el-row>
          <el-col>
            <el-form-item label="寻找" prop="findGender">
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
        <el-row>
          <el-col>
            <el-form-item label="出生日期" prop="birth">
              <el-date-picker
                v-model="form.birth"
                align="right"
                type="date"
                placeholder="选择日期"
                :picker-options="pickerOptions1"
                style="width:100%;">
              </el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col>
            <el-form-item prop="email" label="邮箱">
                <el-input type="text" v-model="form.email" ></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col>
            <el-form-item label="头像:">
              <el-upload class="avatar-uploader"
                         action="http://www.linkjb.com/linkjb/upload/imageupload"
                         :show-file-list="false"
              :on-success="handleAvatarSuccess"
              :before-upload="beforeAvatarUpload">
              <img v-if="url" :src="url" class="avatar">
              <i v-else class="el-icon-plus avatar-uploader-icon"></i>
              </el-upload>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="22">
          <el-col :span="1" :offset="11" style="border-top: inherit" align="center">
            <!--<el-button @click.native="returnBack">取消</el-button>-->
            <el-button type="primary" @click.native="submit">注册</el-button>
          </el-col>
        </el-row>
      </el-form>
    </el-container>
    <el-dialog title="登陆" :visible.sync="dialogTableVisible" center :append-to-body='true' :lock-scroll="false" width="30%">
      <login-name></login-name>
    </el-dialog>
  </section>
</template>

<script>
  import $ from 'jquery';
  import LoginName  from './Login.vue';
  export default {
    components: {LoginName},
    name:"ShowNavCenter",
    data() {
      var validateBirth= (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入出生日期'));
        } else {
          callback();
        }
      };
      var validatePass = (rule, value, callback) => {
        var reg=/^(?!([a-zA-Z]+|\d+)$)[a-zA-Z\d]{6,20}$/;
        if (value === '') {
          callback(new Error('请输入密码'));
        } else {
          if (this.form.pass !== '') {
            if(reg.test(value)){
              /*this.$refs.form.validateField('pass');*/
              callback();
            }else{
              callback("密码必须包含数字和字母！");
            }

          }
          callback();
        }
      };
      var validatePass2 = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请再次输入密码'));
        } else if (value !== this.form.pass) {
          callback(new Error('两次输入密码不一致!'));
        } else {
          callback();
        }
      };
      var validatePhoneNo = (rule, value, callback) => {
//                var reg = /^0?1[3|4|5|8][0-9]\d{8}$/;
//                if(!reg.test(value)){
//                    callback(new Error('手机号码格式错误'));
//                }else{
//                    callback();
//                }
        var reg =/^[1][3,4,5,7,8][0-9]{9}$/;
        if(reg.test(value)){
            let params = new URLSearchParams();
            let _this = this;
            params.append('mobile', value);
            _this.$http.post('/regist/checkMobile',params).then((res)=>{

                if(res.data.status == 500) {
                  callback(new Error('该联系方式已被注册'));
                }else{
                  callback();
                }
            });
        }else{
          callback(new Error('手机号码格式错误'));
        }
      };
      var validateEmail= (rule, value, callback) => {
        var reg = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/
          if (value === '') {
            callback(new Error('请输入邮箱'));
          }else{
            if(reg.test(value)){
              let params = new URLSearchParams();
              let _this = this;
              params.append('email', value);
              _this.$http.post('/regist/checkEmail',params).then((res)=> {

                if (res.data.status == 500) {
                  callback(new Error('该邮箱已被注册'));
                } else {
                  callback();
                }
              })
            }else{
              callback(new Error('该邮箱格式不正确'));
            }

          }
      };
      var validateName= (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入账号'));
        }else{
          let params = new URLSearchParams();
          let _this = this;
          params.append('name', value);
          _this.$http.post('/regist/checkName',params).then((res)=>{

            if(res.data.status == 500) {
              callback(new Error('该账号已被注册'));
            }else{
              callback();
            }
          });
        }
      }
      return {
        socket:'',
        picFlag: true,
        pickerOptions1:{
          disabledDate(time) {
            let curDate = (new Date()).getTime();
            let eighteen = 18 * 365 * 24 * 3600 * 1000;
            let eighty = curDate - eighteen;
            console.log(time);
            return time.getTime() > eighty;
          }
        },
        url:'',
        dialogTableVisible: false,
        form: {
          pass: '',
          checkPass: '',
          email: '',
          mobile:'',
          gender:'',
          findGender:'',
          name:'',
          birth:''
        },
        ResidentFormRules:{
          birth: [
            {required: true, validator: validateBirth, trigger: 'blur' }
          ],
          pass: [
            {required: true, validator: validatePass, trigger: 'blur' }
          ],
          checkPass: [
            {required: true, validator: validatePass2, trigger: 'blur' }
          ],
          mobile: [
            {required: true, message: '请输入手机号', trigger: 'blur' },
            {validator:validatePhoneNo ,trigger: 'blur'}
          ],
          name: [
            {required: true, message: '请输入账号', trigger: 'blur' },
            {validator:validateName ,trigger: 'blur'}
          ],
          email: [
            {required: true, message: '请输入邮箱', trigger: 'blur' },
            {validator:validateEmail ,trigger: 'blur'}
          ],
        }
      }
    },
    mounted(){
      let adom = document.getElementsByClassName('el-upload__input')[0];
      console.log(adom);
      adom.setAttribute('style', 'display: none !important;');
    },
    methods: {
      handleAvatarSuccess(res, file) {

        if(res.entity){
          // let url=res.entity.replace(/\\/g,"/");
          //console.log(url);
          this.url = "http://www.linkjb.com/linkjb/images/"+res.entity;
          console.log(this.url);
        }else{
          _this.$message({
            showClose: true,
            message: '上传错误,请联系管理员',
            type: 'error',
            duration:2000
          });
        }

      },
      beforeAvatarUpload(file) {
        const isJPG = file.type === 'image/jpeg';
        const isLt2M = file.size / 1024 / 1024 < 2;
        if (!isJPG) {
          _this.$message({
            showClose: true,
            message: '上传头像图片只能是 JPG 格式!',
            type: 'error',
            duration:2000
          });
          if (!isLt2M) {
            _this.$message({
              showClose: true,
              message: '上传头像图片大小不能超过 2MB!',
              type: 'error',
              duration:2000
            });
          }
          return isJPG && isLt2M;
        }
      },
      returnBack(){
        this.$emit('cancle');
        this.$router.replace({
          path:'/home/supplierAllBack',
          name:'supplierAllBack'
        })
      },
      submit(){
        let _this = this;
        if(_this.url=="" || _this.url==null){
          _this.$message({
            showClose: true,
            message: '请选择一张图片!',
            type: 'error',
            duration:2000
          });
          return;
        }
        let s = [];
        let hexDigits = "0123456789abcdef";
        for (let i = 0; i < 36; i++) {
          s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1);
        }
        s[14] = "4";  // bits 12-15 of the time_hi_and_version field to 0010
        s[19] = hexDigits.substr((s[19] & 0x3) | 0x8, 1);  // bits 6-7 of the clock_seq_hi_and_reserved to 01
        s[8] = s[13] = s[18] = s[23] = "-";

        let uuid = s.join("");
        let flag=0;
        if(_this.form.gender=="1" && _this.form.findGender=="2"){
          flag=1;
        }else if(_this.form.gender=="1" && _this.form.findGender=="1"){
          flag=2;
        }else if(_this.form.gender=="2" && _this.form.findGender=="1"){
          flag=3;
        }else if(_this.form.gender=="2" && _this.form.findGender=="2"){
          flag=4;
        }
        var params = new URLSearchParams();
        var params1 = new URLSearchParams();
        this.$refs['form'].validate((valid) => {

          let nowdate=new Date();
          let now=nowdate.getFullYear();
          let date = new Date(_this.form.birth);
          let time2= date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
          let time1= date.getFullYear();
          let age=now-time1;
          //console.log(time1);
          //params.append('id', uuid);
          params.append('name', _this.form.name);
          params.append('passWord', _this.form.pass);
          params.append('age', age);
          params.append('email', _this.form.email);
          params.append('mobile', _this.form.mobile);
          params.append('gender', _this.form.gender);
          params.append('findGender', _this.form.findGender);
          params.append('birthday', time2);
          params.append('findFlag', flag);
          params.append('imageUrl', _this.url);
          if (valid) {
            this.$confirm('确认提交吗？', '提示', {
            }).then(() => {
                _this.$http.post('/regist/regist',params).then((res)=>{

                  if(res.data.status == 200){
                    _this.regInfo = res.data;
                    console.log(res.data);
                    _this.$message({
                      message: '注册成功',
                      type: 'success',
                      duration:2000
                    });
                    _this.returnBack();
                    //this.dialogTableVisible=true;
                    // _this.$router.push('/login');
                  }else{
                    _this.$message({
                      showClose: true,
                      message: '注册失败!',
                      type: 'error',
                      duration:2000
                    });
                  }
                },(err)=>{
                  console.log(err);
                });

            })
          }
        });
      },

    }
  }
</script>
<style>
  input[type=file] {
    display: none;
  }
  .img1{
    opacity:0.7;
    /*//background: url('../../assets/images/bg.jpg') center center no-repeat;*/
    background-size: contain;
  }
  .el-col {
    border-radius: 4px
  }
  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #20a0ff;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px !important;
    text-align: center;
  }
  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
  .el-upload__input{
    display: none;
  }
  input[type=file] {
    display: none;
  }
</style>
