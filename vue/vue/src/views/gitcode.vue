<template>
  <div>
    <div style="margin-bottom:20px">
      名称：
      <el-input v-model="params.name" style="width: 200px; margin-right: 10px" placeholder="请输入项目名称"></el-input>
      giturl：
      <el-input v-model="params.giturl" style="width: 200px; margin-left: 5px" placeholder="请输入giturl"></el-input>
      <el-button type="warning" style="margin-left: 10px" @click="findBySearch()">查询</el-button>
      <el-button type="warning" style="margin-left: 10px" @click="add()">新增</el-button>
      <el-button type="primary" style="margin-left: 10px" @click="reset()">重置</el-button>
      <el-button type="success" style="margin-left: 10px" @click="exportdata()">导出数据</el-button>

      <el-upload action="http://localhost:9001/soc/api/git/upload" style="display: inline-block; margin-left: 10px" :show-file-list="false" :on-success="successUpload">
        <el-button size="small" type="primary">批量导入</el-button>
      </el-upload>

    </div>
  <div>


    <el-table
            :data="tableData"
            style="width: 100%">
      <el-table-column prop="id" label="id" width="180"></el-table-column>
      <el-table-column prop="name" label="项目名称" width="180"></el-table-column>
      <el-table-column prop="giturl" label="giturl" width="180"></el-table-column>
      <el-table-column prop="description" label="项目描述" width="180"></el-table-column>
      <el-table-column prop="language" label="编程语言" width="200"></el-table-column>
      <el-table-column prop="readme" label="readme" width="200"></el-table-column>
      <el-table-column prop="star" label="star数" width="200"></el-table-column>
      <el-table-column prop="devloper" label="开发者" width="200"></el-table-column>
      <el-table-column prop="type" label="项目类型" width="200"></el-table-column>

<!--      <el-table-column prop="img" label="封面" width="300">-->
<!--        <template v-slot="scope">-->
<!--          <el-image-->
<!--                  style="width: 70px; height: 70px; border-radius: 50%"-->
<!--                  :src="'http://localhost:9001/soc/api/file/download/' + scope.row.img"-->
<!--                  :preview-src-list="['http://localhost:9001/soc/api/file/download/' + scope.row.img]">-->
<!--          </el-image>-->
<!--        </template>-->

<!--      </el-table-column>-->


      <el-table-column label="操作">
        <template slot-scope="scope">

        <el-button type="primary" @click="edit(scope.row)">编辑</el-button>

<!--          <el-button type="primary" @click="down(scope.row.img)">下载</el-button>-->
          <el-popconfirm title="确定删除吗？" @confirm="del(scope.row.id)">
            <el-button slot="reference" type="danger" style="margin-left: 5px">删除</el-button>
          </el-popconfirm>


        </template>


      </el-table-column>

    </el-table>


  </div>

    <div class="block">
      <el-pagination
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              :current-page="params.pageNum"
              :page-sizes="[ 10,]"
              :page-size="params.pageSize"
              layout="total, sizes, prev, pager, next"
              :total="total">
      </el-pagination>
    </div>

    <div>
      <el-dialog title="请填写信息" :visible.sync="dialogFormVisible" width="40%">
        <el-form :model="form">
          <el-form-item label="项目名称" label-width="15%">
            <el-input v-model="form.name" autocomplete="off" style="width: 90%"></el-input>
          </el-form-item>

          <el-form-item label="giturl" label-width="15%">
            <el-input v-model="form.giturl" autocomplete="off" style="width: 90%"></el-input>
          </el-form-item>

          <el-form-item label="项目描述" label-width="15%">
            <el-input v-model="form.description" autocomplete="off" style="width: 90%"></el-input>
          </el-form-item>
          <el-form-item label="编程语言" label-width="15%">
            <el-input v-model="form.language" autocomplete="off" style="width: 90%"></el-input>
          </el-form-item>

          <el-form-item label="readme" label-width="15%">
            <el-input v-model="form.readme" autocomplete="off" style="width: 90%"></el-input>
          </el-form-item>

          <el-form-item label="star数量" label-width="15%">
            <el-input v-model="form.star" autocomplete="off" style="width: 90%"></el-input>
          </el-form-item>

          <el-form-item label="开发者" label-width="15%">
            <el-input v-model="form.devloper" autocomplete="off" style="width: 90%"></el-input>
          </el-form-item>

          <el-form-item label="项目类型" label-width="15%">
            <el-input v-model="form.type" autocomplete="off" style="width: 90%"></el-input>
          </el-form-item>


        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="submit()">确 定</el-button>
        </div>
      </el-dialog>
    </div>

  </div>
</template>


<style>
  .el-table .warning-row {
    background: oldlace;
  }

  .el-table .success-row {
    background: #f0f9eb;
  }
</style>



<script>
  import request from "@/utils/request";
  export default {
    data() {
      return {
        params:{
          name:'',
          giturl:'',
          pageNum:1,
          pageSize:10
        },
        tableData: [
        ],
        total: 0,
        dialogFormVisible: false,
        form: {}
      }
    },
    //页面加载的的时候，在created做一些事情
    created() {
      this.findBySearch();
    },
    //定义一些页面上控件触发事件调用的方法
    methods: {
      // //定义查询函数

      findBySearch(){
        request.get("/git/search",{
          params: this.params
        }).then(res =>{
          if (res.code === '0') {
            this.tableData = res.data.list;
            this.total = res.data.total;
          }else {
            this.$message({
              message: res.msg,
              type: 'success'
            });
          }
        })
      },

      reset(){
       this.params={
         name:'',
         phone:'',
         pageNum:1,
         pageSize:10
       }
      this.findBySearch()
      },

      handleSizeChange(pageSize){
        this.params.pageSize = pageSize;
        this.findBySearch();
      },
      handleCurrentChange(pageNum){
        this.params.pageNum = pageNum;
        this.findBySearch();
      },
      add() {
        this.form = {};
        this.dialogFormVisible = true;
      },
      submit() {
        request.post("/git/addgit", this.form).then(res => {
          if (res.code === '0') {
            this.$message({
              message: '操作成功',
              type: 'success'
            });
            this.dialogFormVisible = false;
            this.findBySearch();
          } else {
            this.$message({
              message: res.msg,
              type: 'error'
            });
          }
        })
      },
      edit(obj) {
        this.form = obj;
        this.dialogFormVisible = true;
      },

      del(id) {
        request.delete("/git/delete?id=" + id).then(res => {
          if (res.code === '0') {
            this.$message({
              message: '删除成功',
              type: 'success'
            });
            this.findBySearch();
          } else {
            this.$message({
              message: res.msg,
              type: 'success'
            });
          }
        })
      },
      exportdata() {
        let user = localStorage.getItem("user");
        location.href = 'http://localhost:9001/soc/api/git/export?token=' + JSON.parse(user).token
      },

      successUpload(res) {
        if (res.code === '0') {
          this.$message.success("批量导入成功")
          this.findBySearch()
        } else {
          this.$message.error(res.msg)
        }
      },



    }
  }
</script>
