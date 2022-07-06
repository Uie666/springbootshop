<template>
  <div class="login_container">
    <div class="login_box">
      <!-- 头像区域 -->
      <div class="avatar_box">
        <img src="../assets/logo.png" alt="" />
      </div>
      <!-- 注册表单 -->
      <el-form
          :model="loginForm"
          :rules="rules"
          label-width="80px"
          class="login_form"
          ref="loginForm"
      >
        <el-form-item label='用户名' prop="username">
          <el-input
              v-model="loginForm.username"
              prefix-icon="el-icon-user"
          ></el-input>
        </el-form-item>
        <el-form-item label='密码' prop="password">
          <el-input
              v-model="loginForm.password"

          ></el-input>
        </el-form-item>
        <el-form-item class="btns">
          <el-button
              type="primary"
              round
              style="background-color: lightcoral; border: 1px solid #eee"
              @click="register('loginForm')"
          >确认注册</el-button>

        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>

import { Toast } from 'vant';
export default{
  data() {
    return {
      // 表单数据对象
      loginForm: {
        username: '',
        password: ''
      },
      // 表单数据验证规则
      rules: {
        username: [
          { required: true, message: "请输入用户昵称:)", trigger: "blur" },
          {
            min: 2,
            max: 25,
            message: "长度在 2 到 25 个字符",
            trigger: "blur",
          },
        ],
        password: [
          { required: true, message: "请输入密码~~", trigger: "blur" },
          { min: 6, max: 15, message: "长度在 6到 15 个字符", trigger: "blur" },
        ],
      },
    };
  },
  methods: {
    //注册方法

    register(formName) {
      const _this = this
      _this.$refs[formName].validate((valid) => {

        console.log(valid)
        if (valid) {
          console.log(this.loginForm)
          axios.post('http://localhost:8181/user/register', this.loginForm).then(function (resp) {
            console.log(resp)
            console.log(resp.data)
            if(resp.data.code==0){
              _this.$message({
                message: '恭喜你添加成功',
                type: 'success'
              })
            console.log(resp)
            let instance = Toast('添加成功');
            setTimeout(() => {
              instance.close();
              _this.$router.push('/login');
            }, 1000)
            }
            else {
              let instance = Toast('用户名已使用');
              console.log('error submit!!');
              return false;
            }
          })
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
  }
};

</script>

<style lang="less" scoped>
.login_container {
  background-color: lightcoral;
  height: 100%;
}
.login_box {
  width: 450px;
  height: 300px;
  background: #fff;
  border-radius: 3px;
  box-shadow: 0 0 10px #ddd; //阴影
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  .avatar_box {
    height: 130px;
    width: 130px;
    border: 1px solid #eee;
    border-radius: 50%;
    padding: 10px; //头像图片和头像盒子的距离
    box-shadow: 0 0 10px #ddd;
    position: absolute;
    left: 50%;
    transform: translate(-50%, -50%);
    background-color: #fff;
  }
  .avatar_box img {
    width: 100%;
    height: 100%;
    border-radius: 50%;
    background-color: #eee;
  }

}
.login_form {
  position: absolute;
  bottom: 0;
  width: 100%;
  padding: 0 20px;
  box-sizing: border-box;
}
.btns {
  display: flex;
  justify-content: flex-end;
  background-color: #eee;
}
</style>
