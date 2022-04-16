<template>
  <div>
    <el-card style="width: 40%; margin: 10px">
      <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label="用户名">
          <el-input v-model="form.adminName" disabled></el-input>
        </el-form-item>
        <el-form-item label="昵称">
          <el-input v-model="form.adminNickName"></el-input>
        </el-form-item>
      </el-form>
      <div style="text-align: center">
        <el-button type="primary" @click="changePass">修改密码</el-button>
        <el-button type="primary" @click="update">保存</el-button>
      </div>
    </el-card>

    <!--修改密码表单-->
    <el-dialog v-model="dialogVisible" title="修改密码" width="30%">
      <el-form ref="form" :model="form" size="normal" :rules="rules">
        <el-form-item prop="oldPassword">
          <el-input :prefix-icon="Lock" v-model="form.oldPassword" placeholder="原密码" show-password></el-input>
        </el-form-item>
        <el-form-item prop="newPassword">
          <el-input :prefix-icon="Lock" v-model="form.newPassword" placeholder="新密码" show-password></el-input>
        </el-form-item>
        <el-form-item prop="confirm">
          <el-input :prefix-icon="Lock" v-model="form.confirm" placeholder="确认密码" show-password></el-input>
        </el-form-item>
      </el-form>

      <template #footer>
      <span class="dialog-footer">
        <el-button @click="cancel">取消</el-button>
        <el-button type="primary" @click="updatePass">确认</el-button>
      </span>
      </template>
    </el-dialog>
  </div>



</template>

<script>
import request from "../utils/request";
import {ElMessage} from "element-plus";
import {Avatar, Lock} from "@element-plus/icons-vue";
import Empty from "../utils/Empty";

export default {
  name: "Admin",
  setup(){
    return {
      Avatar,
      Lock
    }
  },

  data() {
    const validateNewPassword0 = (rule, value, callback) => {
      if (value !== JSON.parse(sessionStorage.getItem('admin')).adminPassword) {
        callback(new Error("原密码错误!"));
      } else {
        callback();
      }
    };
    const validateNewPassword1 = (rule, value, callback) => {
      if (value === this.form.oldPassword) {
        callback(new Error("新密码不能与原密码相同!"));
      } else {
        callback();
      }
    };
    const validateNewPassword2 = (rule, value, callback) => {
      if (value !== this.form.newPassword) {
        callback(new Error("与新密码不一致!"));
      } else {
        callback();
      }
    };
    return {
      form: {},
      dialogVisible:false,
      rules: {
        oldPassword: [
          // { required: true, message: "请输入原密码", trigger: "blur" },
          { validator: validateNewPassword0, trigger: "blur" },
        ],
        newPassword: [
          // { required: true, message: "请设置新密码", trigger: "blur",validator: validateNewPassword },
          { validator: validateNewPassword1, trigger: "blur" },

        ],
        confirm: [
          // { required: true, message: "请确认新密码", trigger: "blur",validator: validateNewPassword2 },
          { validator: validateNewPassword2, trigger: "blur" },
        ],
      },

    }
  },
  created() {
    let str = sessionStorage.getItem("admin") || "{}"
    this.form = JSON.parse(str)
  },
  methods: {
    cancel(){
      this.$router.replace("/empty")
    },
    changePass(){
      this.dialogVisible="true"
    },
    updatePass(){
      this.dialogVisible=false;
      let data = {
        adminId:JSON.parse(sessionStorage.getItem('admin')).adminId,
        adminPassword:this.form.newPassword,
      };
      this.$refs['form'].validate((valid) => {
        if(valid){
          request.post("/admin/updatePass", data).then(res => {
            if(res.code==='0'){
              ElMessage({
                type:"success",
                message:"密码修改成功！"
              })
              sessionStorage.setItem('admin',res.data)
            }
          })
        }else{
          ElMessage({
            type:"error",
            message:"请正确填写表单！"
          })
          this.changePass();
        }
      })

    },

    update() {
      request.post("/admin/login", this.form).then(res => {
        console.log(res)
        if(res.code==='0'){
          ElMessage({
            type:"success",
            message:"更新成功！"
          })
          console.log(this.$route.path);
          this.$router.replace("/empty")
          sessionStorage.setItem("admin", JSON.stringify(this.form))
        } else {
          ElMessage({
            type:"error",
            message:res.msg
          })
        }
      })
    }
  }
}
</script>

<style>
/*.avatar-uploader .el-upload {*/
/*  border: 1px dashed #d9d9d9;*/
/*  border-radius: 6px;*/
/*  cursor: pointer;*/
/*  position: relative;*/
/*  overflow: hidden;*/
/*}*/
/*.avatar-uploader .el-upload:hover {*/
/*  border-color: #409EFF;*/
/*}*/
/*.avatar-uploader-icon {*/
/*  font-size: 28px;*/
/*  color: #8c939d;*/
/*  width: 120px;*/
/*  height: 120px;*/
/*  line-height: 120px;*/
/*  text-align: center;*/
/*}*/
/*.avatar {*/
/*  width: 178px;*/
/*  height: 178px;*/
/*  display: block;*/
/*}*/
</style>