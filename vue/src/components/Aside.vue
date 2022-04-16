<template>
  <div>
    <el-menu
        style="width: 200px; min-height: calc(100vh - 50px)"
        active-text-color="#0ff"
        background-color="#545c64"
        class="el-menu-vertical-Demo"
        :default-active="path"
        text-color="#fff"
        router
        >
      <el-sub-menu index="1">
        <template #title>系统管理</template>
        <el-menu-item index="/user">用户管理</el-menu-item>
        <el-menu-item index="/book">图书管理</el-menu-item>
      </el-sub-menu>
      <el-menu-item index="/borrow">借阅管理</el-menu-item>
    </el-menu>
  </div>
</template>

<script>
export default {
  name: "Aside",
  data(){
    return {
      path:this.$route.path
    }
  }
}
</script>

<style scoped>

</style>