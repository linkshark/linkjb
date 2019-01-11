<template>
  <div>
    <div>
      <section style="height: 100%;width: 100%"  @keyup.enter="onEnter">
        <el-container >
          <el-form style="width:99%;">
            <el-row :gutter="24" style="padding-left: 10px;padding-right: 10px;">
              <el-col :span="24" style="padding: 10px 10px 0 10px;">
                <el-card class="box-card">
                  <span style="float: left;height:40px;">基本信息展示</span>
                  <el-button style="float: right; padding: 3px 2px;" type="text" @click="returnback">返回</el-button>
                </el-card>
              </el-col>
            </el-row>
          </el-form>
        </el-container>
      </section>
    </div>
    <div>
      Count is {{count}}
      <button @click="increment">+</button>
      <button @click="decrement">-</button>
    </div>
    <!--<div @click="to()">
      <icon name="ali" scale="10"></icon>
    </div>-->
  </div>
</template>
<script>
  //引入mapActions
  import {mapGetters,mapActions} from 'vuex';
  export default {
    computed:{
      ...mapGetters([ 'count' ])
    },
    methods:{
      ...mapActions(['increment','decrement']),
      returnback(){
        /*this.$router.push('/navMain/navHeader');
        window.location.reload();*/
        this.$router.replace({
          path:'/home/supplierAllBack',
          name:'supplierAllBack'
        })
      },
      to(){
        console.log("阿里巴巴")
      }

    }

  }
</script>
<style>

</style>
