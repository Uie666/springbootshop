<template>
  <el-form style="width: 60%" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
    <el-form-item label="用户id" prop="id">
      <el-input v-model="ruleForm.id"></el-input>
    </el-form-item>
    <el-form-item label="用户名称" prop="name">
      <el-input v-model="ruleForm.name"></el-input>
    </el-form-item>

    <el-form-item>
      <el-button type="primary" @click="submitForm('ruleForm')">添加</el-button>
      <el-button @click="resetForm('ruleForm')">重置</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
export default {
  data() {
    return {
      ruleForm: {
        id:'',
        name: ''
      },
      rules: {
        id:[
          { required: true, message: '请输入用户id', trigger: 'blur' }
        ],
        name: [
          { required: true, message: '请输入姓名', trigger: 'blur' },
          { min: 1, max: 5, message: '长度在 1 到 5 个字符', trigger: 'blur' }
        ],

      }
    };
  },
  methods: {
    submitForm(formName) {
      const _this=this
      _this.$refs[formName].validate((valid) => {
        if (valid) {
          axios.post('http://localhost:9494/user/save',this.ruleForm).then(function (resp){
            if(resp.data=='success'){
              _this.$message({
                message: '恭喜你添加成功',
                type: 'success'
              })
              _this.$router.push('/PageOne');
            }
          })
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    resetForm(formName) {
      const _this=this
      _this.$refs[formName].resetFields();
    }
  }
}
</script>