<template>
  <div>
    <el-row>
      <el-col :offset="20" :span="2">
        <el-button  @click="add()">添加</el-button>
      </el-col>
    </el-row>
    <el-table :data="tableData" border>
      <el-table-column
        v-for="(item,index) in col"
        :key="index"
        fixed
        :prop="item.prop"
        :label="item.name"
        width="150">
      </el-table-column>
      <el-table-column
        fixed
        prop="state"
        label="审核状态"
        width="150">
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
      <el-form label-position="left" label-width="80px">
        <el-form-item :label="item.name" v-for="(item,index) in col" :key="index">
          <el-input v-model="formData[index]"></el-input>
        </el-form-item>
        <el-form-item label="审核状态">
          <el-input v-model="formData[5]"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitForm()">确 定</el-button>
      </div>
    </el-dialog>
  </div>

</template>

<script>
  export default {
    name: 'Detail',
    data(){
      return {
        id : '',
        col : [],
        tableData: [],
        dialogTableVisible: false,
        dialogFormVisible: false,
        formData:['','','','','','','','',''],
      }
    },
    methods:{
      cancel:function(){
        this.dialogFormVisible = false;
        Object.keys(this.formData).forEach(key =>this.formData[key]='');
      },
      initTable:function(id){
        this.axios({
          method: 'get',
          data: "",
          url: 'api/dic/'+id,
        }).then(result => {
          var col_names = result.data.data.dictionaryColName.split(',');
          var cols = [];
          for(var i = 0;i<col_names.length;i++){
            var obj = {};
            obj.name = col_names[i];
            obj.prop = 'col'+(i+1);
            cols.push(obj);
          }
          this.col = cols;
          this.initTableData(id);
        }).catch(error => {
          this.$message.error("网络异常");
        })
      },
      initTableData:function(id){
        this.axios({
          method: 'get',
          data: "",
          url: 'api/dic/detail/query/'+id,
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
          url: 'api/dic/detail/delete/'+row.id,
        }).then(result => {
          if(result.data.code == '1'){
            this.$message.info("删除成功");
            this.initTableData(this.id);
          }else{
            this.$message.error("保存失败");
          }
        }).catch(error => {
          this.$message.error("网络异常");
        })
      },
      add:function(index,row){
        this.dialogFormVisible = true;
        this.formData[6] = 'add';
      },
      edit:function(index,row){
        this.dialogFormVisible = true;
        this.formData[0]=row.col1;
        this.formData[1]=row.col2;
        this.formData[2]=row.col3;
        this.formData[3]=row.col4;
        this.formData[4]=row.col5;
        this.formData[5]=row.state;
        this.formData[6]=row.id;
        this.formData[7]=row.did;
      },
      submitForm:function(){
        this.dialogFormVisible = false;
        var params = {};
        params.col1 = this.formData[0];
        params.col2 = this.formData[1];
        params.col3 = this.formData[2];
        params.col4 = this.formData[3];
        params.col5 = this.formData[4];
        params.state = this.formData[5];
        params.id = this.formData[6];
        var url =  'api/dic/detail/update';
        if(this.formData[6] == 'add'){
          url = 'api/dic/detail/add';
          params.id = '';
          params.did = this.id;
        }
        this.axios({
          method: 'post',
          data: JSON.stringify(params),
          url: url,
        }).then(result => {
          if(result.data.code == '1'){
            this.$message.info("保存成功");
            this.initTableData(this.id);
          }else{
            this.$message.error("保存失败");
          }
        }).catch(error => {
          this.$message.error("网络异常");
        })
      },
    },
    mounted(){
      this.id = this.$route.params.id;
      this.initTable(this.id);
    },


  }
</script>

<style scoped>

</style>
