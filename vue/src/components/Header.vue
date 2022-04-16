<template>
  <div style="height: 50px; line-height: 50px; border-bottom: 1px solid #ccc; display: flex" >
    <div style="width: 200px;padding-left: 30px;font-weight: bold;color: #bcbce3">后台管理</div>
    <div style="flex: 1"></div>
    <div style="width: 100px">
      <el-dropdown >
        <el-button :model="form">
          {{form.adminNickName}}<el-icon class="el-icon--right"><arrow-down /></el-icon>
        </el-button>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item @click="$router.push('/admin')">个人信息</el-dropdown-item>
            <el-dropdown-item @click="quit">退出系统</el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </div>
  </div>
</template>

<script>
import router from "../router";

export default {
  name: "Header",
  data(){
    return {
      form:{},
    }
  },
  created() {
    let str = sessionStorage.getItem("admin") || "{}"
    this.form = JSON.parse(str)
    console.log(str)
    console.log(this.form)
  },
  methods:{
    quit(){
      router.push('/login')
      sessionStorage.removeItem("admin")
    }
  }
}
</script>

<style scoped>

</style>