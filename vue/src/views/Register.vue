<template>

    <div id="building">
      <div>
        <!--<div style="width: 100vw;height:100vh;overflow: hidden">background-color: #094249;-->
        <div style="width: 400px;margin: 150px auto">
          <div style="color: #7cfd2c;font-size: 30px;text-align: center;padding:30px 0">欢迎注册</div>
          <el-form ref="form" :model="form" size="normal" :rules="rules">
            <el-form-item prop="adminName">
              <el-input :prefix-icon="Avatar" v-model="form.adminName" placeholder="请输入注册的用户名"></el-input>
            </el-form-item>
            <el-form-item prop="adminPassword">
              <el-input :prefix-icon="Lock" v-model="form.adminPassword" placeholder="请输入注册的密码" show-password></el-input>
            </el-form-item>
            <el-form-item prop="confirm">
              <el-input :prefix-icon="Lock" v-model="form.confirm" placeholder="请再次确认密码" show-password></el-input>
            </el-form-item>
            <el-form-item prop="adminNickName">
              <el-input :prefix-icon="Avatar" v-model="form.adminNickName" placeholder="请为自己取一个昵称"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button style="width: 100%" type="primary" @click="register">注册</el-button>
            </el-form-item>
          </el-form>
        </div>
      </div>
      </div>

<!--    </div>-->

</template>

<script>

import { Avatar, Lock } from "@element-plus/icons-vue";
import {ElMessage} from "element-plus";
import request from "../utils/request";
export default {
  name: "Register",
  data(){
    return{
      form:{

      },
      rules: {
        adminName: {required: true, message: "用户名不能为空",trigger: 'blur',},//
        adminPassword: {required: true,message: "密码不能为空", trigger: 'blur',},//
        confirm:{required: true,message: "请再次确认密码", trigger: 'blur',},//
        adminNickName: {}//message:"请为自己取一个昵称"
      },
    }
  },
  components:{

  },
  setup(){
    return {
      Avatar,
      Lock
    }
  },
  methods:{
    register(){
      if(this.form.adminPassword!==this.form.confirm){
        ElMessage({
          type:"success",
          message:"两次密码输入不一致，请重新输入!"
        })
        return
      }
      this.$refs['form'].validate((valid) => {
        if (valid) {
          request.post("/admin/register",this.form).then(res=>{
            if(res.code==='0'){
              ElMessage({
                type:"success",
                message:"注册成功！"
              })
              this.$router.push("/")
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
#building{
  background:url("../assets/images/register.png");
  width:100%;
  height: 100%;
  position:fixed;
  background-size:100% 100%;}

</style>