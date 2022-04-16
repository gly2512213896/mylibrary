<template>
  <div  style="padding: 10px">
<!--    功能区-->
    <div style="margin:10px 0">
      <el-button type="primary" @click="add">新增</el-button>
<!--      <el-button type="primary">导入</el-button>-->
<!--      <el-button type="primary">导出</el-button>-->
    </div>
<!--搜索-->
    <div style="margin: 10px 0">
      <el-input v-model="search" placeholder="请输入关键字" style="width: 20%" clearable></el-input>
<!--      <el-button type="primary" style="margin-left: 5px;" @click="load">查询</el-button>-->
      <el-button :icon="Search" style="margin-left: 5px;" @click="load" circle></el-button>

    </div>
    <el-table :data="tableData" border stripe style="width: 100%">
      <el-table-column prop="userId" label="借阅人ID" sortable/>
      <el-table-column prop="userName" label="用户名"/>
      <el-table-column prop="sex" label="性别"/>
      <el-table-column prop="age" label="年龄"/>
      <el-table-column prop="address" label="住址" />
      <el-table-column fixed="right" label="操作" width="100px">
        <template #default="scope">
          <el-button :icon="Edit" @click="handleEdit(scope.row)" circle></el-button>
          <!--          <el-button size="mini" @click="handleEdit(scope.row)">编辑</el-button>-->
          <el-popconfirm title="是否确认删除?" @confirm="handleDelete(scope.row.borrowId)">
            <template #reference>
              <el-button type="danger" :icon="Delete" circle></el-button>
              <!--              <el-button size="mini" type="danger">删除</el-button>-->
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
<!--    分页-->
    <div style="margin: 10px">
      <el-pagination
          v-model:currentPage="currentPage"
          :page-sizes="[5,10,20]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
      >
      </el-pagination>
    </div>
    <el-dialog v-model="dialogVisible" title="用户信息" width="30%">
      <el-form :model="form" label-width="120px">
        <el-form-item label="用户名">
          <el-input v-model="form.userName" style="width: 70%"></el-input>
        </el-form-item>
        <el-form-item label="年龄">
          <el-input v-model="form.age" style="width: 70%"></el-input>
        </el-form-item>
        <el-form-item label="性别">
          <el-radio v-model="form.sex" label="男">男</el-radio>
          <el-radio v-model="form.sex" label="女">女</el-radio>
          <el-radio v-model="form.sex" label="未知">未知</el-radio>
        </el-form-item>
        <el-form-item label="地址">
          <el-input type="textarea" v-model="form.address" style="width: 70%"></el-input>
        </el-form-item>
      </el-form>
      <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="save">确认</el-button>
      </span>
      </template>
    </el-dialog>
  </div>
</template>

<script>


import request from "../utils/request";
import {ElMessage} from "element-plus";
import {isNumber} from "element-plus/es/utils/util";
import Global from "../utils/Global";
import { Search, Edit, Delete, } from "@element-plus/icons-vue";


export default {
  name: 'User',
  components: {

  },
  setup(){
    return{
      Search,
      Edit,
      Delete,
    }

  },
  data() {
    return {
      form:{

      },
      dialogVisible:false,
      search:"",
      currentPage:1,
      pageSize:5,
      total:0,
      tableData: [

      ]
    }
  },
  created() {
    this.load()
  },
  methods:{
    load(){
      request.get("/user",{
        params:{
          pageNum:this.currentPage,
          pageSize:this.pageSize,
          search:this.search
        },
      }).then(res=>{
        console.log(res);
        this.tableData=res.data.records;
        this.total=res.data.total;
      })
    },
    add(){
      this.dialogVisible="true"
      this.form={}
    },
    save(){
      // console.log(!isNaN(Number(this.form.age)))
      // console.log((typeof this.form.age)==="number")
      if(isNaN(Number(this.form.age))){
        ElMessage({
          type:"error",
          message:"名字只能填写数字！"
        })
        return
      }
      if(this.form.userId){
        request.put("/user",this.form).then(res=>{
          console.log(res)

          this.option="更新"
          Global.methods.showMessage(res,this.option)

        })
        this.load()
        this.dialogVisible=false
      }else{
        if(isNaN(Number(this.form.age))){
          ElMessage({
            type:"error",
            message:"年龄只能填写数字！"
          })
          return
        }
        request.post("/user",this.form).then(res => {
          console.log(res)
          this.option="添加"
          Global.methods.showMessage(res,this.option)

          this.load()
          this.dialogVisible=false
        })
      }

    },
    handleDelete(userId){
      console.log(userId)
      request.delete("/user/"+userId).then(res=>{
        this.option="删除"
        Global.methods.showMessage(res,this.option)

        this.load()
      })
    },
    handleEdit(row) {
      //避免直接修改原值
      this.form=JSON.parse(JSON.stringify(row));
      this.dialogVisible=true;
    },
    handleSizeChange(pageSize){
      this.pageSize=pageSize
      this.load()
    },
    handleCurrentChange(pageNum){
      this.pageNum=pageNum
      this.load()
    }
  }
}
</script>
