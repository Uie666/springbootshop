<template>
  <div class="login_container">
    <div class="login_box">
      <!-- 头像区域 -->
      <div class="avatar_box">
        <img src="../assets/logo.jpeg" alt="" />
      </div>
      <!-- 登录表单 -->
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
        <el-form-item prop="code">
          <el-row :gutter="10">
            <el-col :span="12">
              <el-input
                  v-model="loginForm.code"
                  placeholder="验证图片"
              ></el-input>
            </el-col>
            <el-col :span="12" style="height: 40px;">
              <el-image
                  :src="codeUrl"
                  @click="getCode"
                  style="width: 100%; height: 40px; cursor: pointer;"
              ></el-image>

            </el-col>
          </el-row>
        </el-form-item>

        <el-form-item class="btns">
          <el-button
              type="primary"
              round
              style="background-color: lightcoral; border: 1px solid #eee"
              @click="login('loginForm')"
          >登录</el-button
          >
          <el-button
              type="info"
              round
              style="background-color: #d32121; border: 1px solid #eee"
              @click="register"
          >注册</el-button
          >
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
//导入登录接口模块

import {Toast} from "vant";
import {setCookie} from "@/plugins/cookie";

export default {
  data() {
    return {
      // 表单数据对象
      loginForm: {
        username: '',
        password: '',
        code:'',
        uuid:''
      },
      codeUrl:'',
      // 表单数据验证规则
      rules: {
        username: [
          { required: true, message: "请输入用户账号", trigger: "blur" },
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
    //登录方法 再次校验-------------------------------
    login(formName) {
      const _this = this
      _this.$refs[formName].validate((valid) => {
        if (valid) {
          setCookie('username',this.loginForm.username,1000*60)
          axios.post('http://localhost:8181/user/login', this.loginForm).then(function (resp) {

            console.log(_this.loginForm.uuid)
            if (resp.data.code == 0) {
              _this.$message({
                message: '恭喜你登入成功',
                type: 'success'
              })
              let instance = Toast('登入成功');
              setTimeout(() => {
                instance.close();
                _this.$router.push('/home');
              }, 1000)
            }else {
              let instance = Toast('用户名或者密码错误');
              return false;
            }
          })
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    getCode(){
      const _this = this
      axios.get('http://localhost:8181/user/code').then(function (resp) {
        console.log(resp.data)
        _this.codeUrl = resp.data.img
        _this.loginForm.uuid = resp.data.uuid
      })
    },

    //添加表单重置方法
    register() {
      this.$router.push("/register");
    },
  },
  created(){
    // 获取验证码
    this.getCode()
  },
};

</script>

<style lang="less" scoped>
.login_container {
  background-color: #80a9f0;
  height: 100%;
}
.login_box {
  width: 500px;
  height: 300px;
  background: #fff;
  border-radius: 3px;
  box-shadow: 0 0 10px #ddd; //阴影
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  .avatar_box {
    height: 150px;
    width: 150px;
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
