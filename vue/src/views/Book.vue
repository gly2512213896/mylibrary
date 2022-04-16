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
      <el-table-column prop="bookId" label="书籍ID" sortable/>
      <el-table-column prop="bookName" label="书名"/>
      <el-table-column prop="author" label="作者"/>
      <el-table-column prop="describe" label="描述"/>
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
    <el-dialog v-model="dialogVisible" title="书籍信息" width="30%">
      <el-form :model="form" label-width="120px">
        <el-form-item label="书名">
          <el-input v-model="form.bookName" style="width: 70%"></el-input>
        </el-form-item>
        <el-form-item label="作者">
          <el-input v-model="form.author" style="width: 70%"></el-input>
        </el-form-item>
        <el-form-item label="描述">
          <el-input type="textarea" v-model="form.describe" style="width: 70%"></el-input>
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
import Global from "../utils/Global";
import { Search, Edit, Delete, } from "@element-plus/icons-vue";

export default {
  name: 'Book',
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
        bookId:"",
        bookName:"",
        author:"",
        describe:"",
      },
      dialogVisible:false,
      search:"",
      currentPage:1,
      pageSize:5,
      total:0,
      tableData: [
      ],
    }
  },
  created() {
    this.load()
  },
  methods:{
    load(){
      request.get("/book",{
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
      let bookName=this.form.bookName
      //编辑
      if(this.form.bookId){
        if(!(bookName.includes("《")||bookName.includes("》"))){
          this.form.bookName="《"+this.form.bookName+"》"
        }
        request.put("/book",this.form).then(res=>{
          console.log(res)
          this.option="更新"
          Global.methods.showMessage(res,this.option)
        })
        this.load()
        this.dialogVisible=false
      }else{//添加
        if(!(bookName.includes("《")||bookName.includes("》"))){
          this.form.bookName="《"+this.form.bookName+"》"
        }
        request.post("/book",this.form).then(res => {
          console.log(res)
          this.option="添加"
          Global.methods.showMessage(res,this.option)
          this.load()
          this.dialogVisible=false
        })
      }

    },
    handleDelete(bookId){
      console.log(bookId)
      request.delete("/book/"+bookId).then(res=>{
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

<style scoped>

</style>