<template>
  <div class="center">
    <el-container>
      <el-row class="row-bg" justify="center"></el-row>
      <el-main>
        <el-input v-model="username" style="width: 240px" placeholder="请输入用户名：" clearable /><br><br>
        <el-input v-model="password" style="width: 240px" type="password" placeholder="请输入密码" show-password /><br><br>
        <div class="center-button">
          <el-button type="primary" plain @click="handleClick">登录</el-button>
        </div>
        
      </el-main>
    </el-container>
  </div>

</template>

<style scoped>
.center{
  display: grid;
  place-items: center;
  margin-top: 10%;
}
.center-button {
    display: flex;
    justify-content: center;
    width: 100%;
}

</style>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router';
import axios from 'axios';
import qs from 'querystring'
import { ElMessage } from 'element-plus';
let username = ref('')
let password = ref('')
const router = useRouter()
function handleClick() {
  let data = {
    username: username.value,
    password: password.value,
  }
  axios.post('http://localhost:8080/user/login', qs.stringify(data)).then((res) => {
    console.log(res.data)
    if (res.data.code == 200) {
      sessionStorage.setItem("login", true)
      router.replace('/home')
    }
    else {
      ElMessage.error(res.data.message)
    }
  })
}
</script>