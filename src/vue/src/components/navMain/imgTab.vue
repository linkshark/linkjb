
<template>
  <div >
    <section style="margin-top:0px;">
      <el-container class="echarts">
        <el-tabs v-model="activeName" @tab-click="handleClick">
          <el-tab-pane label="所有人" name="first">用户管理</el-tab-pane>
          <el-tab-pane label="靓女" name="second">配置管理</el-tab-pane>
          <el-tab-pane label="靓仔" name="third">角色管理</el-tab-pane>
        </el-tabs>

      </el-container>
    </section>
  </div>
</template>
<script>

  export default {
    components:{},
    name: "imgTab",
    props: ["userJson"],
    data() {
      return {
        activeName: 'first'
      };
    },
    mounted() {

    },
    beforeDestroy() {

    },
    methods: {
      handleClick(tab, event) {
        console.log(tab, event);
      }
    }
  }
</script>
<style scoped>

</style>
