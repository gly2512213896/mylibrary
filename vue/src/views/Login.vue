<template>
<!--  <div class="background">-->
<!--    <img :src="loginBG" width="100%" height="100%" alt="" />

background:url("../assets/image/Background2.jpg");
  width:100%;
  height:100vh;
  position:fixed;
  overflow: hidden

https://tse1-mm.cn.bing.net/th/id/OIP-C.FaG6dzohGs3q45-DwsEyQQHaEK?w=326&h=183&c=7&r=0&o=5&dpr=1.25&pid=1.7
-->
<!--  </div>-->
<div id="building">
<!--  style="background: url('D:\\Desktop\\library-system\\vue\\src\\assets\\images\\loginBG.png') no-repeat;width: 100%;height:100vh;overflow: hidden;position:fixed;">&lt;!&ndash;background: rgba(56,17,232,0.59);&ndash;&gt;-->
  <div>
    <div style="width: 400px;margin: 150px auto">
      <div style="color: #cccccc;font-size: 30px;text-align: center;padding:30px 0">欢迎登录</div>
      <el-form ref="form" :model="form" size="normal" :rules="rules">
        <el-form-item prop="adminName">
          <el-input :prefix-icon="Avatar" v-model="form.adminName" clearable></el-input>
        </el-form-item>
        <el-form-item prop="adminPassword">
          <el-input :prefix-icon="Lock" v-model="form.adminPassword" clearable show-password></el-input>
        </el-form-item>
        <el-form-item>
          <div style="text-align: center;">
            <el-button style="width: 50%;" type="primary" @click="login" fixed>登陆</el-button>
          </div>
        </el-form-item>
      </el-form>
      <div>
        没有账户，
        <router-link to="/register" type="primary">立即注册</router-link>
      </div>
    </div>
  </div>
</div>

</template>

<script>

import { Avatar, Lock } from "@element-plus/icons-vue";
import {ElMessage} from "element-plus";
import request from "../utils/request";
export default {
  name: "Login",
  data(){
    return {
      form: {
      },
      rules: {
        adminName: {required: true, message: "请输入用户名", trigger: 'blur',},
        adminPassword: {required: true, message: "请输入密码", trigger: 'blur',},
      },
    }
  },
  components:{},
  setup(){
    return {
      Avatar,
      Lock
    }
  },
  methods:{
    login(){
      this.$refs['form'].validate((valid) => {
        if (valid) {
          request.post("/admin/login",this.form).then(res=>{
            if(res.code==='0'){
              ElMessage({
                type:"success",
                message:"欢迎使用图书借阅管理系统！"
              })
              console.log("登陆之前先打印一下res.data")
              console.log(res)
              console.log(res.data)
              sessionStorage.setItem("admin",JSON.stringify(res.data))
              this.$router.push("/")//登陆后页面跳转
            }else{
              ElMessage({
                type:"error",
                message:res.msg
              })
            }
          })
        }
      })
    }
  }
}
</script>

<style scoped>
/*.background{*/
/*  width:100%;*/
/*  height:100%;*/
/*  z-index:-1;*/
/*  position: absolute;*/
/*}*/

/*.front{*/
/*  z-index:1;*/
/*  position: absolute;*/
/*}*/
#building{
  background:url("../assets/images/loginBG.png");
  width:100%;
  height:100%;
  position:fixed;
  overflow: hidden}
</style>