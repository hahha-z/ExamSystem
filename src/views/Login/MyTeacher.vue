<template>
  <div class="login-container">
    <div class="login-box">
      <h1 class="title">线上考试系统</h1>
      <h2 class="gate">老师端</h2>
      <form @submit.prevent="handleLogin"> 
        <div class="form-item">
          <label for="account">账号</label>
          <input v-model="account" id="account" type="text" placeholder="请输入账号">
        </div>
        <div class="form-item">
          <label for="password">密码</label>
          <input v-model="password" id="password" type="password" placeholder="请输入密码">
        </div>
        <button type="submit" class="login-button">登录</button>
      </form>
    </div>
  </div>
</template>
 
<script>
// import router from '@/router';
// import request from '../../utils/request'

export default {
  data() {
    return {
      account: '',
      password: ''
    };
  },
  methods: {
    async handleLogin() { 
      try {
        // const res = await request.post('/teacher/login', {
        //   account: this.account,
        //   password: this.password
        // });

        // const token = res.data.token
        // const workId = res.data.teacher.workId
        // const roleId = res.data.teacher.roleId

        // localStorage.setItem('token', token);
        // localStorage.setItem('workId', workId);
        // localStorage.setItem('roleId', roleId);

        // 假跳转
        this.$router.push('/teacher/teacherinfo')
        


      } catch (error) {
        console.error('登录失败', error);
      }
    }
  }
}
</script>

<style scoped>
  .login-container {
  background: linear-gradient(to bottom right,rgba(173,216,230,0.7),white);
  height: 100vh; 
  display: flex;
  justify-content: center;
  align-items: center;
}
.gate{
  display: flex;
  color: rgb(61, 61, 212);
  justify-content: center;
  align-items: center;
}

.login-box {
  width: 300px;
  height: 350px;
  background-color: rgha(255,255,255,0.9);
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  display: flex;
  flex-direction: column;
  justify-content: space-around;
}

.title {
  text-align: center;
  margin-bottom: 20px;
}

.form-item {
  margin-bottom: 20px;
}

.form-item label {
  display: block;
  margin-bottom: 5px;
}

.form-item input {
  width: 95%;
  margin: 0 auto;
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 3px;
}

.login-button {
  width: 100%;
  padding: 10px;
  background-color: #2980b9;
  color: white;
  border: none;
  border-radius: 3px;
  cursor: pointer;
}
</style>