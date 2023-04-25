<template>
    <div class="chat">
        <div class="chat_box">
            <div class="chat_item_left">
                <div class="window_btn_box">
                    <span class="window_btn" style="background-color: #FE5F57;border: solid 1px #E09C1B;"></span>
                    <span class="window_btn" style="background-color: #FEBC2E;border: solid 1px #E09B19;"></span>
                    <span class="window_btn" style="background-color: #2BC840;border: solid 1px #1EA824;"></span>
                </div>
                <n-avatar
                @click="showUserInfoPopup = !showUserInfoPopup"
                class="header_icon"
                size="small"
                :src="userInfo.image"/>
                <n-icon @click="router.push({name: 'chat'})" class="icon" size="33" color="#766574" :component="ChatbubbleOutline" />
                <n-icon @click="router.push({name: 'contacts'})" class="icon" size="33" color="#766574" :component="Person" />
                <svg  data-name="OpenAI Logo" width="33px" height="33px" style="margin-top:30px" viewBox="140 140 520 520"><defs><linearGradient id="linear" x1="100%" y1="22%" x2="0%" y2="78%"><stop offset="0%" stop-color="rgb(131,211,231)"></stop><stop offset="2%" stop-color="rgb(127,203,229)"></stop><stop offset="25%" stop-color="rgb(86,115,217)"></stop><stop offset="49%" stop-color="rgb(105,80,190)"></stop><stop offset="98%" stop-color="rgb(197,59,119)"></stop><stop offset="100%" stop-color="rgb(197,59,119)"></stop></linearGradient></defs><path id="logo" d="m617.24 354a126.36 126.36 0 0 0 -10.86-103.79 127.8 127.8 0 0 0 -137.65-61.32 126.36 126.36 0 0 0 -95.31-42.49 127.81 127.81 0 0 0 -121.92 88.49 126.4 126.4 0 0 0 -84.5 61.3 127.82 127.82 0 0 0 15.72 149.86 126.36 126.36 0 0 0 10.86 103.79 127.81 127.81 0 0 0 137.65 61.32 126.36 126.36 0 0 0 95.31 42.49 127.81 127.81 0 0 0 121.96-88.54 126.4 126.4 0 0 0 84.5-61.3 127.82 127.82 0 0 0 -15.76-149.81zm-190.66 266.49a94.79 94.79 0 0 1 -60.85-22c.77-.42 2.12-1.16 3-1.7l101-58.34a16.42 16.42 0 0 0 8.3-14.37v-142.39l42.69 24.65a1.52 1.52 0 0 1 .83 1.17v117.92a95.18 95.18 0 0 1 -94.97 95.06zm-204.24-87.23a94.74 94.74 0 0 1 -11.34-63.7c.75.45 2.06 1.25 3 1.79l101 58.34a16.44 16.44 0 0 0 16.59 0l123.31-71.2v49.3a1.53 1.53 0 0 1 -.61 1.31l-102.1 58.95a95.16 95.16 0 0 1 -129.85-34.79zm-26.57-220.49a94.71 94.71 0 0 1 49.48-41.68c0 .87-.05 2.41-.05 3.48v116.68a16.41 16.41 0 0 0 8.29 14.36l123.31 71.19-42.69 24.65a1.53 1.53 0 0 1 -1.44.13l-102.11-59a95.16 95.16 0 0 1 -34.79-129.81zm350.74 81.62-123.31-71.2 42.69-24.64a1.53 1.53 0 0 1 1.44-.13l102.11 58.95a95.08 95.08 0 0 1 -14.69 171.55c0-.88 0-2.42 0-3.49v-116.68a16.4 16.4 0 0 0 -8.24-14.36zm42.49-63.95c-.75-.46-2.06-1.25-3-1.79l-101-58.34a16.46 16.46 0 0 0 -16.59 0l-123.31 71.2v-49.3a1.53 1.53 0 0 1 .61-1.31l102.1-58.9a95.07 95.07 0 0 1 141.19 98.44zm-267.11 87.87-42.7-24.65a1.52 1.52 0 0 1 -.83-1.17v-117.92a95.07 95.07 0 0 1 155.9-73c-.77.42-2.11 1.16-3 1.7l-101 58.34a16.41 16.41 0 0 0 -8.3 14.36zm23.19-50 54.92-31.72 54.92 31.7v63.42l-54.92 31.7-54.92-31.7z" fill="#0DC160"></path></svg>
                <n-icon class="icon" size="33" color="#766574" :component="Menu" />
            </div>
            <div class="chat_item_right">
                <div class="chat_personal_title">
                    <span>ChatGPT</span>
                </div>
                <div class="chat_content" ref="chatContent">
                    <template v-for="item in chating">
                        <div class="user_box empty" v-if="item.status == 0">
                            <div class="icon"></div>
                            <div>
                                <div class="chat_msg">
                                    <!-- <div class="chat_triangle"></div> -->
                                    <span>{{ item.post_mesage }}</span>
                                </div>
                            </div>
                        </div>
                        <div class="hand_box empty" v-if="item.status == 1">
                            <div class="icon">
                                <svg class="chat_gpt_icon" data-name="OpenAI Logo" width="24px" height="24px" viewBox="140 140 520 520"><defs><linearGradient id="linear" x1="100%" y1="22%" x2="0%" y2="78%"><stop offset="0%" stop-color="rgb(131,211,231)"></stop><stop offset="2%" stop-color="rgb(127,203,229)"></stop><stop offset="25%" stop-color="rgb(86,115,217)"></stop><stop offset="49%" stop-color="rgb(105,80,190)"></stop><stop offset="98%" stop-color="rgb(197,59,119)"></stop><stop offset="100%" stop-color="rgb(197,59,119)"></stop></linearGradient></defs><path id="logo" d="m617.24 354a126.36 126.36 0 0 0 -10.86-103.79 127.8 127.8 0 0 0 -137.65-61.32 126.36 126.36 0 0 0 -95.31-42.49 127.81 127.81 0 0 0 -121.92 88.49 126.4 126.4 0 0 0 -84.5 61.3 127.82 127.82 0 0 0 15.72 149.86 126.36 126.36 0 0 0 10.86 103.79 127.81 127.81 0 0 0 137.65 61.32 126.36 126.36 0 0 0 95.31 42.49 127.81 127.81 0 0 0 121.96-88.54 126.4 126.4 0 0 0 84.5-61.3 127.82 127.82 0 0 0 -15.76-149.81zm-190.66 266.49a94.79 94.79 0 0 1 -60.85-22c.77-.42 2.12-1.16 3-1.7l101-58.34a16.42 16.42 0 0 0 8.3-14.37v-142.39l42.69 24.65a1.52 1.52 0 0 1 .83 1.17v117.92a95.18 95.18 0 0 1 -94.97 95.06zm-204.24-87.23a94.74 94.74 0 0 1 -11.34-63.7c.75.45 2.06 1.25 3 1.79l101 58.34a16.44 16.44 0 0 0 16.59 0l123.31-71.2v49.3a1.53 1.53 0 0 1 -.61 1.31l-102.1 58.95a95.16 95.16 0 0 1 -129.85-34.79zm-26.57-220.49a94.71 94.71 0 0 1 49.48-41.68c0 .87-.05 2.41-.05 3.48v116.68a16.41 16.41 0 0 0 8.29 14.36l123.31 71.19-42.69 24.65a1.53 1.53 0 0 1 -1.44.13l-102.11-59a95.16 95.16 0 0 1 -34.79-129.81zm350.74 81.62-123.31-71.2 42.69-24.64a1.53 1.53 0 0 1 1.44-.13l102.11 58.95a95.08 95.08 0 0 1 -14.69 171.55c0-.88 0-2.42 0-3.49v-116.68a16.4 16.4 0 0 0 -8.24-14.36zm42.49-63.95c-.75-.46-2.06-1.25-3-1.79l-101-58.34a16.46 16.46 0 0 0 -16.59 0l-123.31 71.2v-49.3a1.53 1.53 0 0 1 .61-1.31l102.1-58.9a95.07 95.07 0 0 1 141.19 98.44zm-267.11 87.87-42.7-24.65a1.52 1.52 0 0 1 -.83-1.17v-117.92a95.07 95.07 0 0 1 155.9-73c-.77.42-2.11 1.16-3 1.7l-101 58.34a16.41 16.41 0 0 0 -8.3 14.36zm23.19-50 54.92-31.72 54.92 31.7v63.42l-54.92 31.7-54.92-31.7z" fill="#FFF"></path></svg>
                            </div>
                            <div>
                                <div class="chat_msg">
                                    <!-- <div class="chat_triangle"></div> -->
                                    <div v-html="mdToHtml(item.post_mesage)"></div>
                                </div>
                            </div>
                        </div>
                    </template>
                </div>
                <div class="chat_input_box">
                    <div class="stopBtn" v-if="sseStatus == 2" @click="disconnect">
                        <div class="disconnect_btn"></div>Stop generating
                    </div>
                    <div class="refreshBtn" v-if="sseStatus == 0 && chatGptQuestion.first == false" @click="refresh">
                        <n-icon style="position: relative;top: 2px" size="15" color="#40414F" :component="Refresh" @click="sendMessage" />
                        Regenerate response
                    </div>
                    <div class="chat_input">
                        <span v-if="sseStatus == 1 || sseStatus == 2" class="loading"></span>
                        <input v-model="message" placeholder="Send a message..."/>
                        <n-icon v-if="sseStatus == 0" class="sendBtn" size="25" color="#b1b1b1" :component="SendAlt" @click="sendMessage" />
                    </div>
                </div>
                <div class="chat_input_bottom"></div>
            </div>
        </div>
    </div>
</template>
<script lang="ts" setup>
import { reactive, onMounted, ref } from 'vue'
import { useRouter } from 'vue-router'
import { mainStore } from '../../store'
import { loginToken } from '../../api/user'
import { ChatbubbleOutline, Person, Menu, EllipsisHorizontal } from '@vicons/ionicons5'
import { SendAlt } from '@vicons/carbon'
import { Refresh } from '@vicons/tabler'
import { NIcon, NAvatar } from 'naive-ui'
import { UserInfoType, ChatGptMessageType, ChatGptQuestion } from '../../interface/storeInterface'
import { EventSourcePolyfill } from 'event-source-polyfill'
import { marked } from 'marked'

const store = mainStore()
const router = useRouter()
const message = ref('')
const userInfo = ref({} as UserInfoType)
const chating = ref([] as ChatGptMessageType[])
const showUserInfoPopup = ref(false)
const chatContent = ref()
const sse = ref({} as any)
const sseStatus = ref(0)
const chatGptQuestion = ref({} as ChatGptQuestion)

onMounted(async () => {
    loginToken().then(res => {
        userInfo.value = res.data.data
    })
    chatGptQuestion.value.first = true
});
const sendMessage = () => {
    let reqItem:ChatGptMessageType = {
        post_mesage: message.value,
        status: 0
    }
    chatGptQuestion.value.question = message.value
    chating.value.push(reqItem)
    setTimeout(() => {
        chatContent.value.scrollTop = chatContent.value.scrollHeight
    },10)
    let responeText:String = ''
    const eventSource = new EventSourcePolyfill('http://localhost:9003/chat?message=' + message.value, {
        headers: {
            'uid': userInfo.value.userId
        }
    })
    eventSource.onopen = (event:any) => {
        sse.value = event.target
        sseStatus.value = 1
        let respItem:ChatGptMessageType = {
            post_mesage: '|',
            status: 1
        }
        chating.value.push(respItem)
    }
    eventSource.onmessage = (event:any) => {
        console.log(event)
        sseStatus.value = 2
        chatGptQuestion.value.first = false
        if (event.data == '[DONE]') {
            sseStatus.value = 0
            responeText = ''
            if (sse.value) {
                sse.value.close()
            }
            return
        }
        message.value = ''
        let json_data = JSON.parse(event.data)
        if (json_data.content == null || json_data.content == 'null') {
            responeText = '';
            return;
        }
        responeText = responeText + json_data.content;
        chating.value[chating.value.length - 1].post_mesage = responeText
        setTimeout(() => {
            chatContent.value.scrollTop = chatContent.value.scrollHeight
        },10)
    }
}
const disconnect = () => {
    if (sse.value) {
        sse.value.close()
        sseStatus.value = 0
    }
}
const refresh = () => {
    let reqItem:ChatGptMessageType = {
        post_mesage: chatGptQuestion.value.question,
        status: 0
    }
    chating.value.push(reqItem)
    setTimeout(() => {
        chatContent.value.scrollTop = chatContent.value.scrollHeight
    },10)
    let responeText:String = ''
    const eventSource = new EventSourcePolyfill('http://localhost:9003/chat?message=' + chatGptQuestion.value.question, {
        headers: {
            'uid': userInfo.value.userId
        }
    })
    eventSource.onopen = (event:any) => {
        sse.value = event.target
        sseStatus.value = 1
        let respItem:ChatGptMessageType = {
            post_mesage: '|',
            status: 1
        }
        chating.value.push(respItem)
    }
    eventSource.onmessage = (event:any) => {
        sseStatus.value = 2
        chatGptQuestion.value.first = false
        if (event.data == '[DONE]') {
            sseStatus.value = 0
            responeText = ''
            if (sse.value) {
                sse.value.close()
            }
            return
        }
        message.value = ''
        let json_data = JSON.parse(event.data)
        if (json_data.content == null || json_data.content == 'null') {
            responeText = '';
            return;
        }
        responeText = responeText + json_data.content;
        chating.value[chating.value.length - 1].post_mesage = responeText
        setTimeout(() => {
            chatContent.value.scrollTop = chatContent.value.scrollHeight
        },10)
    }
}
const mdToHtml = (content:String) => {
    return marked(content);
}
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
.chat_item_right {
    float: right;
    position: relative;
    width: 1150px;
    height: 100%;
    background-color: #FFFFFF;
    overflow: hidden;
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
        height: 85%;
        overflow: auto;
        .empty:last-child {
            margin-bottom: 180px;
        }
        .hand_box {
            padding-top: 20px;
            padding-bottom: 20px;
            width: 100%;
            overflow: hidden;
            clear: both;
            background-color: #F7F7F8;
            border-bottom: solid 1.5px #E5E5E5;
            * {
                float: left;
            }
            .icon {
                margin-right: 20px;
                background-color: #0CA37F;
                margin-top: 12px;
                margin-left: 145px;
            }
            .chat_gpt_icon {
                width: 28px;
                height: 28px;
                padding: 6px;
            }
            .chat_msg {
                clear: both;
                max-width: 795px;
                font-size: 18px;
                position: relative;
                text-align: left;
            }
            .chat_triangle {
                width: 0;
                height: 0;
                border-top: 10px solid #FFF;
                border-right: 10px solid transparent;
                border-left: 10px solid transparent;
                transform: rotate(90deg);
                position: absolute;
                left: -10px;
                top: 15px;
            }
        }
        .user_box {
            padding-top: 30px;
            padding-bottom: 30px;
            width: 100%;
            overflow: hidden;
            clear: both;
            background-color: #FFF;
            border-bottom: solid 1.5px #E5E5E5;
            * {
                float: left;
            }
            .icon {
                margin-right: 20px;
                margin-left: 145px;
            }
            .chat_msg {
                clear: both;
                // background-color: #95EC69;
                padding: 5px 5px;
                font-size: 18px;
                position: relative;
                max-width: 795px;
                text-align: left;
            }
            .chat_triangle {
                width: 0;
                height: 0;
                border-top: 10px solid #95EC69;
                border-right: 10px solid transparent;
                border-left: 10px solid transparent;
                transform: rotate(90deg);
                position: absolute;
                left: -10px;
                top: 15px;
            }
        }
        .icon {
            width: 40px;
            height: 40px;
            background-color: #666;
            border-radius: 3px;
        }
    }
    .chat_input_box {
        position: absolute;
        bottom: 50px;
        left: 0px;
        width: 100%;
        height: 120px;
        // background-color: #CCC;
        background: linear-gradient(rgba(255, 255, 255, 0), rgb(255, 255, 255));
        .stopBtn {
            position: absolute;
            left: 450px;
            top: 13px;
            border: solid #E5E5E5 1.5px;
            padding: 8px 13px 8px 13px;
            font-size: 15px;
            width: 140px;
            -webkit-text-stroke: 0.3px #40414F;
            color: #40414F;
            border-radius: 5px;
            text-align: center;
            background-color: #FFF;
            cursor: pointer;
            .disconnect_btn {
                float: left;
                width: 10px;
                height: 10px;
                border: solid 2px #40414F;
                border-radius: 3px;
                margin-top: 5px;
                margin-right: 3px;
            }
            
        }
        .stopBtn:hover {
            background-color: #ECECF1;
        }
        .refreshBtn {
            position: absolute;
            left: 450px;
            top: 13px;
            border: solid #E5E5E5 1.5px;
            padding: 8px 13px 8px 13px;
            font-size: 15px;
            width: 170px;
            -webkit-text-stroke: 0.3px #40414F;
            color: #40414F;
            border-radius: 5px;
            text-align: center;
            background-color: #FFF;
            cursor: pointer;
        }
        .refreshBtn:hover {
            background-color: #ECECF1;
        }
    }
    .chat_input {
        width: 850px;
        height: 55px;
        border: solid 1.5px #E5E5E5;
        background-color: #FFF;
        position: relative;
        top: 70px;
        left: 150px;
        border-radius: 5px;
        box-shadow: 0px 0px 15px rgba(0, 0, 0, .15);
        input {
            float: left;
            margin-left: 15px;
            width: 93%;
            height: 85%;
            font-size: 18px;
            outline: none;
            border: none;
            margin-top: 2px;
            -webkit-text-stroke: 0.3px #191919;
            color: #191919;
            letter-spacing: .5px;
        }
        input::-webkit-input-placeholder {
            color: #8E8EA1;
            -webkit-text-stroke: 0.5px #8E8EA1;
        }
        .sendBtn {
            position: absolute;
            right: 15px;
            top: 15px;
            cursor: pointer;
        }
        .loading:after {
            position: absolute;
            top: 0px;
            right: 15px;
            overflow: hidden;
            display: inline-block;
            vertical-align: bottom;
            animation: ellipsis 1.5s infinite;
            font-size: 28px;
            font-weight: bold;
            content: "\2026";
            color: #b1b1b1;
        }
        @keyframes ellipsis {
            from {
                width: 2px;
            }
            to {
                width: 20px;
            }
        }
    }
    .chat_input_bottom {
        width: 100%;
        height: 80px;
        background-color: #FFF;
    }
}
</style>