<template>
    <div class="register">
        <div class="register_box">
            <div class="register_title">Sign up to iChat</div>
            <div class="input_box">
                <p class="input_title">账号</p>
                <input type="text" v-model="formInline.username" placeholder="请输入账号"/>
                <p class="input_title">密码</p>
                <input type="password" v-model="formInline.password" placeholder="请输入密码"/>
                <p class="input_title">密码</p>
                <input type="password" v-model="formInline.affirmPass" placeholder="确认密码"/>
                <div>
                    <div class="register_btn" @click="handleRegister">立即注册</div>
                    <div class="back_btn" @click="router.push({name: 'login'})">返回登录</div>
                </div>
            </div>
        </div>
        
    </div>
</template>
<script lang="ts" setup>
import { reactive, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { mainStore } from '../../store'
import { register } from '../../api/user'
import { ElNotification } from 'element-plus'
interface FormState {
    username: String,
    password: String,
    affirmPass: String
}
const router = useRouter()
const store = mainStore()
const formInline = reactive({
    username: '',
    password: '',
    affirmPass: ''
})
const handleRegister = () => {
    const { username, password, affirmPass } = formInline
    let params = {
        username: username,
        password: password
    }
    if (password != affirmPass) {
        return false
    } else {
        register(params).then(res => {
            if (res.data.code == 200) {
                ElNotification({
                    title: '登录成功',
                    message: '即将跳转至登录页面..',
                    type: 'success',
                })
                setTimeout(() => {
                    router.push({name: 'login'})
                },3000)
            } else {
                ElNotification({
                    title: '登录失败',
                    message: res.data.msg,
                    type: 'error',
                })
            }
        })
    }
}
onMounted(async () => {
    
});
</script>
<style lang="less" scoped>
.register {
    width: 100vw;
    height: 100vh;
    background: url(//res.wx.qq.com/t/wx_fed/webwx/res/static/img/2zrdI1g.jpg) no-repeat 50%;
    background-size: cover;
    color: #191919;
}
.register_box {
    position: absolute;
    top: 50%;
    left: 50%;
    margin-left: -235px;
    margin-top: -325px;
    border-radius: 8px;
    background-color: rgb(255, 255, 255);
    width: 470px;
    height: 600px;
    box-shadow: rgb(153, 153, 153) 0px 2px 10px;
}
.register_title {
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
    color: #191919;
}
.register_btn {
    float: right;
    width: 48%;
    height: 45px;
    border-radius: 8px;
    color: #FFF;
    background-color: #238636;
    line-height: 45px;
    font-size: 18px;
    margin-top: 15px;
    cursor: pointer;
}
.back_btn {
    float: left;
    width: 48%;
    height: 45px;
    border-radius: 8px;
    color: #333;
    background-color: #FFF;
    border: solid #666 1px;
    line-height: 45px;
    font-size: 18px;
    margin-top: 15px;
    text-align: center;
    cursor: pointer;
}
.register_btn:hover {
    background-color: #2DA042;
}
span {
    color: #57A6FF;
    cursor: pointer;
}
span:hover {
    text-decoration: underline;
    text-underline-offset: 2.5px;
}
input:focus {
    border: solid 1px rgb(28, 28, 28);
}
</style>