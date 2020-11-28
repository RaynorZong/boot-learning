<template>
  <el-container>

    <el-aside width="200px">
      <el-row>
        <el-col :span="24">
          <el-menu :default-openeds="['1']" router>
            <el-submenu index="1">
              <template slot="title">
                <span>数据字典管理</span>
              </template>
              <el-menu-item-group>
                <template slot="title"></template>
                <el-menu-item index="/all">
                  <span>综合管理</span>
                </el-menu-item>
                <el-menu-item v-for="(item,index) in navList" :index="'/detail/'+item.id" :key="index">
                  <span>{{item.dictionaryName}}</span>
                </el-menu-item>
              </el-menu-item-group>
            </el-submenu>
          </el-menu>
        </el-col>
      </el-row>
    </el-aside>
    <el-main>

      <el-col :span="24" class="content-wrapper">
        <transition name="fade" mode="out-in">
          <router-view :key="$route.fullPath" @refresh="refresh"></router-view>
        </transition>
      </el-col>

    </el-main>

  </el-container>

</template>
<script>

  export default {

    data() {
      return {
        navList:[],
        dialogFormVisible: false,
        formData:{},
      }
    },
    methods: {
      initLeftNav:function(){
        this.axios({
          method: 'get',
          data: "",
          url: 'api/dic/',
        }).then(result => {
          this.navList = result.data.data;
        }).catch(error => {
          this.$message.error("网络异常");
        })
      },
      refresh:function(msg){
        this.initLeftNav();
      },
      updateCity(data){//触发子组件城市选择-选择城市的事件
         console.log('toCity:'+data)
      }

    },
    mounted(){
      this.initLeftNav();
    }
  }
</script>

<style>

</style>

