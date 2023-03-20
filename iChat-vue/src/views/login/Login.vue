<template>
    <div class="login">
        <div class="login_box">
            <div class="login_title">Sign in to iChat</div>
            <div class="input_box">
                <p class="input_title">账号</p>
                <input type="text" v-model="formInline.username" placeholder="请输入账号"/>
                <p class="input_title">密码<span style="float: right">忘记密码？</span></p>
                <input type="password" v-model="formInline.password" placeholder="请输入密码"/>
                <div class="login_btn" @click="handleLogin">立即登录</div>
            </div>
        </div>
        <div class="new_box">
            <p>初来乍到iChat?<span> 创建账号</span></p>
        </div>
    </div>
</template>
<script lang="ts" setup>
import { reactive, onMounted } from 'vue'
import { mainStore } from '../../store'
import { login } from '../../api/user'
import { fetchFriendList } from '../../api/friend'
interface FormState {
    username: String,
    password: String
}
const store = mainStore()
const formInline = reactive({
    username: '',
    password: ''
})
const handleLogin = () => {
    const { username, password } = formInline
    const params: FormState = {
        username,
        password
    }
    login(params).then(res => {
        console.log(store.state.token)
    })
}
onMounted(async () => {
    
});
</script>
<style lang="less" scoped>
.login {
    width: 100vw;
    height: 100vh;
    background: url(//res.wx.qq.com/t/wx_fed/webwx/res/static/img/2zrdI1g.jpg) no-repeat 50%;
    background-size: cover;
    color: #333;
}
.login_box {
    position: absolute;
    top: 50%;
    left: 50%;
    margin-left: -235px;
    margin-top: -325px;
    border-radius: 8px;
    background-color: rgb(255, 255, 255);
    width: 470px;
    height: 480px;
    box-shadow: rgb(153, 153, 153) 0px 2px 10px;
}
.login_title {
    margin-top: 50px;
    font-size: 1.8rem;
}
.input_box {
    margin: 0 auto;
    width: 80%;
    margin-top: 30px;
}
.input_title {
    text-align: left;
    font-size: 1rem;
}
.input_box input {
    width: 95%;
    height: 45px;
    border-radius: 8px;
    outline: none;
    border: solid 1px #666;
    background-color: #FFF;
    padding-left: 15px;
    font-size: 16px;
    margin-bottom: 15px;
    color: #333;
}
.login_btn {
    width: 95%;
    height: 45px;
    border-radius: 8px;
    padding-left: 15px;
    color: #FFF;
    background-color: #238636;
    line-height: 45px;
    font-size: 18px;
    margin-top: 15px;
    cursor: pointer;
}
.login_btn:hover {
    background-color: #2DA042;
}
.new_box {
    position: absolute;
    top: 50%;
    left: 50%;
    margin-left: -235px;
    margin-top: 170px;
    border-radius: 8px;
    background-color: rgb(255, 255, 255);
    width: 470px;
    height: 70px;
    box-shadow: rgb(153, 153, 153) 0px 2px 10px;
    font-size: 18px;
    line-height: 35px;
}
span {
    color: #57A6FF;
    cursor: pointer;
}
span:hover {
    text-decoration: underline;
    text-underline-offset: 2.5px;
}
</style>