<template>
  <div>
    <div style="margin-bottom:20px">
      名称：
      <el-input v-model="params.name" style="width: 200px; margin-right: 10px" placeholder="请输入名称"></el-input>

      <el-button type="warning" style="margin-left: 10px" @click="findBySearch">查询</el-button>
      <el-button type="primary" style="margin-left: 10px" @click="add()">新增</el-button>
      <el-button type="primary" style="margin-left: 10px" @click="reset">重置</el-button>

      <el-popconfirm title="确定执行批量删除吗？" @confirm="delBatch()">
        <el-button slot="reference" type="danger" style="margin-left: 5px">批量删除</el-button>
      </el-popconfirm>



    </div>
  <div>



    <el-table :data="tableData" style="width: 100%" ref="table" @selection-change="handleSelectionChange" :row-key="getRowKeys">
      <el-table-column ref="table" type="selection" width="55" align="center" :reserve-selection="true"></el-table-column>

      <el-table-column prop="id" label="id" width="180"></el-table-column>
      <el-table-column prop="name" label="名称" width="180"></el-table-column>
      <el-table-column prop="description" label="描述" width="200"></el-table-column>

      <el-table-column label="操作">
        <template slot-scope="scope">

        <el-button type="primary" @click="edit(scope.row)">编辑</el-button>

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
              :page-sizes="[5, 10, 15, 20]"
              :page-size="params.pageSize"
              layout="total, sizes, prev, pager, next"
              :total="total">
      </el-pagination>
    </div>

    <div>
      <el-dialog title="请填写信息" :visible.sync="dialogFormVisible" width="40%">
        <el-form :model="form">
          <el-form-item label="名称" label-width="15%">
            <el-input v-model="form.name" autocomplete="off" style="width: 90%"></el-input>
          </el-form-item>

          <el-form-item label="描述" label-width="15%">
            <el-input v-model="form.description" autocomplete="off" style="width: 90%"></el-input>
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
          phone:'',
          pageNum:1,
          pageSize:5,
        },
        tableData: [
        ],
        total: 0,
        dialogFormVisible: false,
        form: {},
        multipleSelection: [],

      }
    },
    //页面加载的的时候，在created做一些事情
    created() {
      this.findBySearch();
    },
    //定义一些页面上控件触发事件调用的方法
    methods: {
      //定义查询函数
      // load() {
      //   request.get("/admin/user/getUserList").then(res => {
      //     if (res.code === '0') {
      //       this.tableData = res.data;
      //     }
      //   })
      // },

      findBySearch(){
        request.get("/booktype/search",{
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
         pageSize:5
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
        request.post("/booktype/add", this.form).then(res => {
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
        request.delete("/booktype/" + id).then(res => {
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
      delBatch() {
        if (this.multipleSelection.length === 0) {
          this.$message.warning("请勾选您要删除的项")
          return
        }
        request.put("/booktype/delBatch", this.multipleSelection).then(res => {
          if (res.code === '0') {
            this.$message.success("批量删除成功")
            this.findBySearch()
          } else {
            this.$message.error(res.msg)
          }
        })

      },
      handleSelectionChange(val) {
        this.multipleSelection = val;
      },
      getRowKeys(row) {
        return row.id;
      },
    }
  }
</script>
