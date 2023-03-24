<template>
    <div class="chat">
        <div class="chat_box">
            <div class="chat_item_left">
                <div class="window_btn_box">
                    <span class="window_btn" style="background-color: #FE5F57;border: solid 1px #E09C1B;"></span>
                    <span class="window_btn" style="background-color: #FEBC2E;border: solid 1px #E09B19;"></span>
                    <span class="window_btn" style="background-color: #2BC840;border: solid 1px #1EA824;"></span>
                </div>
                <div class="header_icon"></div>
                <n-icon class="icon" size="33" color="#0DC160" :component="ChatbubbleSharp" />
                <n-icon @click="skipContacts" class="icon" size="33" color="#766574" :component="PersonOutline" />
                <n-icon class="icon" size="33" color="#766574" :component="Menu" />
            </div>
            <div class="chat_item_middle">
                <div class="search_box">
                    <input class="search" v-model="searchInput" placeholder="搜索">
                    <div class="add_person"><span>+</span></div>
                </div>
                <div class="chat_records_box">
                    <template v-for="item in chatingList">
                        <div class="chat_records_item" @click="handleChat">
                            <div class="icon"></div>
                            <div class="content">
                                <p>{{ item.nickname }}</p>
                                <p>{{ item.toMsg }}</p>
                                <span>{{ item.sendTime }}</span>
                            </div>
                        </div>
                    </template>
                </div>
            </div>
            <div class="chat_item_right">
                <div v-if="showChat">
                    <div class="chat_personal_title">
                        <span>文件传输助手</span>
                    </div>
                    <div class="chat_content">

                    </div>
                    <div class="chat_input">
                        <div></div>
                        <textarea v-model="message" @keydown.enter="carriageReturn($event)" v-on:keyup.enter="sendMessage"></textarea>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>
<script lang="ts" setup>
import { reactive, ref, onMounted } from 'vue'
import { mainStore } from '../../store'
import { useRouter } from 'vue-router'
import { creatWebSocket, sendWebSocket, closeWebSocket } from '../../utils/webSocket'
import { ChatbubbleSharp, PersonOutline, Menu } from '@vicons/ionicons5'
import { NIcon } from 'naive-ui'
import { loginToken } from '../../api/user'
import { fetchMessagesHistoryList } from '../../api/messages'
import { UserInfoType, MessageToType } from '../../interface/storeInterface'

const store = mainStore()
const router = useRouter()
const searchInput = ref('')
const message = ref('')
const messageType = ref(1)  // 1是单聊 9是群聊
const userInfo = ref({} as UserInfoType)
const showChat = ref(false)
const chatingList = ref([] as MessageToType[])

const getMessagesHistoryList = () => {
    let params = {
        userId: userInfo.value.userId
    }
    fetchMessagesHistoryList(params).then(res => {
        chatingList.value = res.data.data
    })
}
const skipContacts = () => {
    router.push({name: 'contacts'})
}
const handleChat = () => {
    showChat.value = true
}
const sendMessage = () => {
    let data = {
        type: messageType.value,
        params: {
            toMessageId: '4150902',
            message: message.value,
            fileType: 0,
        },
    }
    message.value = ''
    sendWebSocket(data)
}
const carriageReturn = (event:any) => {
    if (event.keyCode == 13) {
        if (!event.metaKey) {
          event.preventDefault();
        }
    }
}
onMounted(async () => {
    loginToken().then(res => {
        userInfo.value = res.data.data
        getMessagesHistoryList()
        creatWebSocket(res.data.data)
    })
    
});
</script>
<style lang="less" scoped>
.chat {
    width: 100vw;
    height: 100vh;
    background: url(//res.wx.qq.com/t/wx_fed/webwx/res/static/img/2zrdI1g.jpg) no-repeat 50%;
    background-size: cover;
    color: #191919;
}
.chat_box {
    margin: 0 auto;
    width: 1230px;
    height: 850px;
    position: relative;
    top: 70px;
    box-shadow: 0px 0px 10px rgba(0, 0, 0, .1);
    background-color: #FFF;
    overflow: hidden;
    border-radius: 12px;
}
.chat_item_left {
    float: left;
    background-image: linear-gradient(#EBCDE8, #DCC8E8);
    width: 80px;
    height: 100%;
    position: relative;
    .icon {
        margin-top: 30px;
        margin-left: 24px;
        display: block;
        transform:scale(1,0.9); 
    }
    .icon:last-child {
        position: absolute;
        bottom: 30px;
        transform:scale(1,1); 
    }
}
.window_btn_box {
    height: 30px;
    margin-bottom: 50px;
}
.window_btn {
    display: block;
    width: 15px;
    height: 15px;
    border-radius: 15px;
    background-color: #CCC;
    float: left;
    margin-left: 7px;
    margin-top: 9px;
}
.header_icon {
    width: 50px;
    height: 50px;
    background-color: #666;
    border-radius: 5px;
    margin: 0 auto;
}
.chat_item_middle {
    float: left;
    width: 330px;
    height: 100%;
    background-color: #F7F7F7;
    border-right: solid 1.5px #E0E0E0;
}
.search_box {
    width: 100%;
    height: 70px;
    background-color: #F7F7F7;
}
.search {
    background-color: #EAEAEA;
    outline: none;
    border: none;
    padding: 5px 18px;
    width: 210px;
    height: 23px;
    margin-top: 20px;
    margin-left: 20px;
    border-radius: 3px;
    border: solid 1.5px #EAEAEA ;
    float: left;
}
.search:focus {
    border: solid 1.5px #0DC160;
}
.add_person {
    width: 25px;
    height: 25px;
    padding: 5px;
    background-color: #EAEAEA;
    margin-top: 20px;
    margin-right: 20px;
    border-radius: 3px;
    float: right;
    font-size: 25px;
    cursor: pointer;
}
.add_person span {
    position: relative;
    top: -9px;
    color: #666;
}
.chat_records_box {
    width: 100%;
    height: 100%;
    background-color: #F7F7F7;
    overflow: auto;
}
.chat_records_item {
    width: 100%;
    height: 50px;
    padding: 20px 0px;
    cursor: default;
    user-select: none;
    .icon {
        width: 50px;
        height: 50px;
        background-color: #666;
        margin-left: 15px;
        border-radius: 5px;
        float: left;
    }
    .content {
        text-align: left;
        width: 77%;
        height: 50px;
        float: right;
        position: relative;
        p {
            margin-top: 0px;
            line-height: 10px;
        }
        p:nth-child(1) {
            margin-top: 4px;
            font-weight: 500;
            font-size: 18px;
        }
        p:nth-child(2) {
            color: #ACACAC;
        }
        span {
            position: absolute;
            right: 20px;
            top: 0;
            font-size: 12px;
            color: #ACACAC;
        }
    }
}

.chat_records_item_check {
    background-color: #DEDEDE;
}
.chat_item_right {
    float: left;
    width: 818.5px;
    height: 100%;
    background-color: #F3F3F3;
    .chat_personal_title {
        width: 100%;
        height: 70px;
        border-bottom: solid 1.5px #E0E0E0;
        text-align: left;
        span {
            position: relative;
            top: 20px;
            left: 30px;
            font-size: 20px;
            font-weight: 500;
        }
    }
    .chat_content {
        width: 100%;
        height: 580px;
        overflow: auto;
    }
    .chat_input {
        width: 100%;
        height: 200px;
        border-top: solid 1.5px #E0E0E0;
        div {
            width: 100%;
            height: 20%;
        }
        textarea {
            width: 96%;
            height: 110px;
            outline: none;
            border: none;
            background-color: #F3F3F3;
            color: #191919;
            padding: 15px;
            font-size: 16px;
            resize: none;
        }
    }
}
</style>