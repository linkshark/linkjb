<template>
  <section style="margin-top:0px;">
    <el-container >
        <el-row>
          <el-col>
            <el-menu router
              :default-active="$route.path"
              class="el-menu-vertical-demo"
              @open="handleOpen"
              @close="handleClose"
              background-color="#545c64"
              text-color="#fff"
              active-text-color="#ffd04b"
              >
              <el-menu-item index="/getMyMesage">
                <span slot="title">别人给我的留言</span>
              </el-menu-item>
              <el-menu-item index="/myMesage">
                <span slot="title">我给别人的留言</span>
              </el-menu-item>
              <el-menu-item index="/myconacter">
                <span slot="title">我的联系人</span>
              </el-menu-item>
            </el-menu>
          </el-col>
        </el-row>
    </el-container>
    <router-view></router-view>
  </section>
</template>
<script>
  export default {
    components: {},
    name: "messageNav",
    data() {
      return {
        uuid:'',
        ownerid:'',
      }
    },
    mounted(){
      this.getMesage();
    },
    methods:{
      getMesage(){
        debugger
        this.$router.push('/getMyMesage');
      },
      handleOpen(){

      },
      handleClose(){

      }
    }
  }
</script>
<style>

  .el-menu-vertical-demo{
    margin-left:20px;
    width:200px;
    height:670px;
  }
</style>
