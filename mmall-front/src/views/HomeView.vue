<template>
  <el-container class="home_container">
    <el-header>
      <div class="home_title">MMall后台管理系统</div>
    </el-header>
    <el-container>

      <el-aside width="200px" style="background-color: #a5e7f0">

        <el-menu router>
<!--el-menu添加router来实现页面跳转-->
          <el-submenu :index="0">
            <template slot="title"><i class="el-icon-goods"></i>商品模块</template>
            <el-menu-item index="/productManager">
              <div style="position: relative;left: 20px;">
                <i class="el-icon-s-unfold"></i>商品管理
              </div>
            </el-menu-item>
            <el-menu-item index="/add">
              <div style="position: relative;left: 20px;">
                <i class="el-icon-circle-plus"></i>添加商品
              </div>
            </el-menu-item>
            <el-menu-item index="/excel">
              <div style="position: relative;left: 20px;">
                <i class="el-icon-folder-add"></i>excel添加
              </div>
            </el-menu-item>
          </el-submenu>

          <el-submenu :index="1">
            <template slot="title"><i class="el-icon-finished"></i>数据统计</template>
            <el-menu-item index="/bar">
              <div style="position: relative;left: 20px;">
                <i class="el-icon-s-data"></i>柱状图
              </div>
            </el-menu-item>
            <el-menu-item index="/pie">
              <div style="position: relative;left: 20px;">
                <i class="el-icon-s-marketing"></i>饼图
              </div>
            </el-menu-item>
          </el-submenu>

          <el-submenu :index="2">
            <template slot="title"><i class="el-icon-folder"></i>数据导出</template>
            <el-menu-item index="/export">
              <div style="position: relative;left: 20px;">
                <i class="el-icon-folder-checked"></i>导出数据
              </div>
            </el-menu-item>

          </el-submenu>

        </el-menu>



      </el-aside>

      <el-container>
        <el-main>
          <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{ path: '/update' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item v-text="this.$router.currentRoute.name"></el-breadcrumb-item>
          </el-breadcrumb>
          <router-view></router-view>
        </el-main>
        <el-footer>@V1.0-Spring Boot + Element UI</el-footer>
      </el-container>

    </el-container>

  </el-container>
</template>

<script>
// @ is an alias to /src
import HelloWorld from '@/components/HelloWorld.vue'

export default {
  name: 'HomeView',
  components: {
    HelloWorld
  }
}
</script>
<style>
  .home_container {
    height: 100%;
    position: absolute;
    top: 0px;
    left: 0px;
    width: 100%;
  }

  .el-header {
    background-color: #20a0ff;
    color: #333;
    text-align: center;
    display: flex;
    align-items: center;
    justify-content: space-between;
  }

  .el-aside {
    background-color: #ECECEC;
  }

  .el-main {
    background-color: #fff;
    color: #000;
    text-align: center;
  }

  .el-footer {
    background-color: #ea7e53;
    color: #fff;
    font-size: 22px;
    line-height: 60px;
  }

  .home_title {
    color: #fff;
    font-size: 22px;
    display: inline;
  }

  .home_userinfo {
    color: #fff;
    cursor: pointer;
  }

  .home_userinfoContainer {
    display: inline;
    margin-right: 20px;
  }
</style>
