<template>

</template>

<script>
import {ElMessage} from "element-plus";

export default {
  name: "Global",
  methods:{
    showMessage(res,option){
      if(res.code==='0'){
        ElMessage({
          type:"success",
          message:option+"成功！"
        })
      }else{
        ElMessage({
          type:"error",
          message:res.msg
        })
      }
    }
  }
}
</script>

<style scoped>

</style>