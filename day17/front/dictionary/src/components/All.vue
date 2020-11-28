<template>
  <el-container>

    <el-main>
      <el-row>
        <el-col :offset="20" :span="2">
          <el-button @click="add()">添加</el-button>
        </el-col>
      </el-row>
      <el-table :data="tableData" border>
        <el-table-column
          fixed
          prop="dictionaryName"
          label="字典名称"
          width="150">
        </el-table-column>
        <el-table-column
          fixed
          prop="dictionaryColName"
          label="列名称"
          width="300">
        </el-table-column>
        <el-table-column
          fixed
          prop="dictionaryDesc"
          label="描述"
          width="150">
        </el-table-column>
        <el-table-column
          fixed
          prop="dictionaryTable"
          label="标识"
          width="150">
        </el-table-column>
        <el-table-column
          fixed
          prop="state"
          label="启用"
          width="150">
        </el-table-column>
        <el-table-column
          fixed
          prop="dictionaryOrder"
          label="顺序"
          width="100">
        </el-table-column>
        <el-table-column
          label="操作"
          width="200">
          <template slot-scope="scope" >
            <el-popconfirm title="这是一段内容确定删除吗？"   @onConfirm="remove(scope.$index,scope.row)">
              <el-button slot="reference" type="text" size="small" >删除</el-button>
            </el-popconfirm>
            <el-button type="text" size="small" @click="edit(scope.$index,scope.row)">编辑</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-dialog title="编辑" :visible.sync="dialogFormVisible" @close="cancel">
        <el-form label-position="left" label-width="80px"
                 :rules="rules" ref="addForm" prop="addForm" :model="formData">
          <el-form-item label="字典名称" prop="dictionaryName">
            <el-input v-model="formData.dictionaryName" ></el-input>
          </el-form-item>
          <el-form-item label="列名称" prop="dictionaryColName">
            <el-input v-model="formData.dictionaryColName"></el-input>
          </el-form-item>
          <el-form-item label="描述" prop="dictionaryDesc">
            <el-input v-model="formData.dictionaryDesc"></el-input>
          </el-form-item>
          <el-form-item label="标识" prop="dictionaryTable">
            <el-input v-model="formData.dictionaryTable"></el-input>
          </el-form-item>
          <el-form-item label="启用" prop="state">
            <el-input v-model="formData.state"></el-input>
          </el-form-item>
          <el-form-item label="顺序" prop="dictionaryOrder">
            <el-input v-model="formData.dictionaryOrder"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="cancel">取 消</el-button>
          <el-button type="primary" @click="submitForm()">确 定</el-button>
        </div>
      </el-dialog>
    </el-main>

  </el-container>

</template>

<script>
  export default {
    name: 'All',
    data(){
      return {
        id : '',
        col : [],
        tableData: [],
        dialogTableVisible: false,
        dialogFormVisible: false,
        formData: {},
        rules: {
          dictionaryName:[{ required: true, message: '请输入字典名称', trigger: 'blur' }],
          dictionaryColName:[{ required: true, message: '请输入列名称(逗号隔开,最多支持五列)', trigger: 'blur' }],
          dictionaryDesc:[{ required: true, message: '请输入字典描述', trigger: 'blur' }],
          dictionaryOrder:[{ required: false, message: '请输入顺序', trigger: 'blur' },
            {type: 'number', message: '必须为数字值'}],

        }
      }
    },
    methods:{
      cancel:function(){
        this.dialogFormVisible = false;
      },
      initTable:function(){
        this.axios({
          method: 'post',
          data: "",
          url: 'api/dic/list',
        }).then(result => {
          this.tableData = result.data.data;
        }).catch(error => {
          this.$message.error("网络异常");
        })
      },
      remove:function(index,row){
        this.axios({
          method: 'post',
          data: "",
          url: 'api/dic/delete/'+row.id,
        }).then(result => {
          if(result.data.code == '1'){
            this.$message.info("删除成功");
            this.initTable();
            this.$emit('refresh', "");
          }else{
            this.$message.error("保存失败");
          }
        }).catch(error => {
          this.$message.error("网络异常");
        })
      },
      edit:function(index,row){
        this.formData = row;
        this.dialogFormVisible = true;
      },
      add:function(){
        this.formData = {};
        this.dialogFormVisible = true;
        this.formData.id = 'add';
      },
      submitForm:function(){
        this.$refs['addForm'].validate((valid) => {
          if (valid) {
            this.dialogFormVisible = false;
            var url =  'api/dic/update';
            if(this.formData.id == 'add'){
              url = 'api/dic/add';
              this.formData.id = "";
            }
            this.axios({
              method: 'post',
              data: JSON.stringify(this.formData),
              url: url,
            }).then(result => {
              if(result.data.code == '1'){
                this.$message.info("保存成功");
                this.initTable();
                this.formData = {};
                this.$emit('refresh', "");
              }else{
                this.$message.error("保存失败");
              }
            }).catch(error => {
              this.$message.error("网络异常");
            })
          } else {
            console.log('error submit!!');
            return false;
          }
        });


      },
    },
    mounted(){
      this.initTable();
    }
  }
</script>

<style scoped>

</style>
