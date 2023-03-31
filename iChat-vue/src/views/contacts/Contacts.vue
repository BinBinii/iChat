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
                <n-icon @click="skipChat" class="icon" size="33" color="#766574" :component="ChatbubbleOutline" />
                <n-icon class="icon" size="33" color="#0DC160" :component="Person" />
                <svg @click="router.push({name: 'chatgpt'})" data-name="OpenAI Logo" width="33px" height="33px" style="margin-top:30px" viewBox="140 140 520 520"><defs><linearGradient id="linear" x1="100%" y1="22%" x2="0%" y2="78%"><stop offset="0%" stop-color="rgb(131,211,231)"></stop><stop offset="2%" stop-color="rgb(127,203,229)"></stop><stop offset="25%" stop-color="rgb(86,115,217)"></stop><stop offset="49%" stop-color="rgb(105,80,190)"></stop><stop offset="98%" stop-color="rgb(197,59,119)"></stop><stop offset="100%" stop-color="rgb(197,59,119)"></stop></linearGradient></defs><path id="logo" d="m617.24 354a126.36 126.36 0 0 0 -10.86-103.79 127.8 127.8 0 0 0 -137.65-61.32 126.36 126.36 0 0 0 -95.31-42.49 127.81 127.81 0 0 0 -121.92 88.49 126.4 126.4 0 0 0 -84.5 61.3 127.82 127.82 0 0 0 15.72 149.86 126.36 126.36 0 0 0 10.86 103.79 127.81 127.81 0 0 0 137.65 61.32 126.36 126.36 0 0 0 95.31 42.49 127.81 127.81 0 0 0 121.96-88.54 126.4 126.4 0 0 0 84.5-61.3 127.82 127.82 0 0 0 -15.76-149.81zm-190.66 266.49a94.79 94.79 0 0 1 -60.85-22c.77-.42 2.12-1.16 3-1.7l101-58.34a16.42 16.42 0 0 0 8.3-14.37v-142.39l42.69 24.65a1.52 1.52 0 0 1 .83 1.17v117.92a95.18 95.18 0 0 1 -94.97 95.06zm-204.24-87.23a94.74 94.74 0 0 1 -11.34-63.7c.75.45 2.06 1.25 3 1.79l101 58.34a16.44 16.44 0 0 0 16.59 0l123.31-71.2v49.3a1.53 1.53 0 0 1 -.61 1.31l-102.1 58.95a95.16 95.16 0 0 1 -129.85-34.79zm-26.57-220.49a94.71 94.71 0 0 1 49.48-41.68c0 .87-.05 2.41-.05 3.48v116.68a16.41 16.41 0 0 0 8.29 14.36l123.31 71.19-42.69 24.65a1.53 1.53 0 0 1 -1.44.13l-102.11-59a95.16 95.16 0 0 1 -34.79-129.81zm350.74 81.62-123.31-71.2 42.69-24.64a1.53 1.53 0 0 1 1.44-.13l102.11 58.95a95.08 95.08 0 0 1 -14.69 171.55c0-.88 0-2.42 0-3.49v-116.68a16.4 16.4 0 0 0 -8.24-14.36zm42.49-63.95c-.75-.46-2.06-1.25-3-1.79l-101-58.34a16.46 16.46 0 0 0 -16.59 0l-123.31 71.2v-49.3a1.53 1.53 0 0 1 .61-1.31l102.1-58.9a95.07 95.07 0 0 1 141.19 98.44zm-267.11 87.87-42.7-24.65a1.52 1.52 0 0 1 -.83-1.17v-117.92a95.07 95.07 0 0 1 155.9-73c-.77.42-2.11 1.16-3 1.7l-101 58.34a16.41 16.41 0 0 0 -8.3 14.36zm23.19-50 54.92-31.72 54.92 31.7v63.42l-54.92 31.7-54.92-31.7z" fill="#766574"></path></svg>
                <n-icon class="icon" size="33" color="#766574" :component="Menu" />
            </div>
            <div class="chat_item_middle">
                <div class="search_box">
                    <input class="search" v-model="searchInput" placeholder="搜索">
                    <div class="add_person"><span>+</span></div>
                </div>
                <div class="contacts_box">
                    <div class="mail_list">
                        <n-icon class="icon" size="28" color="#766574" :component="PersonCircle" />
                        <span>通讯录管理</span>
                    </div>
                    <div class="item_title" @click="handleNewUserList">
                        <n-icon v-if="!showNewUserListFlag" class="icon" size="25" color="#B5B5B5" :component="ChevronForward" />
                        <n-icon v-else class="icon" size="25" color="#B5B5B5" :component="ChevronDown" />
                        <span class="item_title_content">新的朋友</span>
                        <span class="item_title_number">1</span>
                    </div>
                    <div class="new_mail_box">
                        <template v-for="item in 1" v-if="showNewUserListFlag">
                            <div class="new_mail_item">
                                <div class="icon"></div>
                                <div class="content">
                                    <p>用户昵称</p>
                                    <p>添加好友描述</p>
                                </div>
                                <span>未通过</span>
                            </div>
                        </template>
                    </div>
                    <div class="item_title" @click="handleUserList">
                        <n-icon v-if="!showUserListFlag" class="icon" size="25" color="#B5B5B5" :component="ChevronForward" />
                        <n-icon v-else class="icon" size="25" color="#B5B5B5" :component="ChevronDown" />
                        <span class="item_title_content">通讯录</span>
                        <span class="item_title_number">{{ friendList.length }}</span>
                    </div>
                    <div class="mail_box">
                        <template v-for="(item,index) in friendList" v-if="showUserListFlag">
                            <div class="new_mail_item" :style="showUserInfoIndex == index ? 'background-color: #DEDEDE':''" @click="handleUserInfo(item.userId, index)">
                                <div class="icon"></div>
                                <div class="content">
                                    <p>{{item.nickname}}</p>
                                </div>
                            </div>
                        </template>
                    </div>
                </div>
            </div>
            <div class="chat_item_right">
                <div class="bg" v-if="showUserInfoFlag">
                    <div class="user_info_box">
                        <div class="user_info_box_1">
                            <div class="content">
                                <span>{{ showUserInfoData.nickname }}</span>
                            </div>
                            <div class="icon"></div>
                        </div>
                        <div class="user_info_box_2">
                            <div class="prop">
                                <p>备注名</p>
                                <p>地区</p>
                                <p>iChat号</p>
                                <p>来源</p>
                                <p>朋友权限</p>
                            </div>
                            <div class="content">
                                <p>备注名</p>
                                <p>用户所在地区</p>
                                <p>{{ showUserInfoData.username }}</p>
                                <p>添加好友来源</p>
                                <p style="color: #CCC">该功能尚未开通</p>
                            </div>
                        </div>
                        <div class="user_info_box_3">
                            <div class="message" @click="goChating">发消息</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>
<script lang="ts" setup>
import { reactive, onMounted, ref } from 'vue'
import { useRouter } from 'vue-router'
import { mainStore } from '../../store'
import { fetchFriendList } from '../../api/friend'
import { loginToken, fetchUserInfo } from '../../api/user'
import { ChatbubbleOutline, Person, Menu, PersonCircle, ChevronForward, ChevronDown } from '@vicons/ionicons5'
import { NIcon, NAvatar } from 'naive-ui'
import { UserInfoType } from '../../interface/storeInterface'

const store = mainStore()
const router = useRouter()
const searchInput = ref('')
const userInfo = ref({} as UserInfoType)
const friendList = ref([] as UserInfoType[])
const showNewUserListFlag = ref(false)
const showUserListFlag = ref(false)
const showUserInfoFlag = ref(false)
const showUserInfoData = ref({} as UserInfoType)
const showUserInfoIndex = ref()
const showUserInfoPopup = ref(false)

const skipChat = () => {
    router.push({name: 'chat'})
}
const handleNewUserList = () => {
    showNewUserListFlag.value = !showNewUserListFlag.value
}
const handleUserList = () => {
    showUserListFlag.value = !showUserListFlag.value
}
const handleUserInfo = (userId: String, index:Number) => {
    showUserInfoFlag.value = true
    showUserInfoIndex.value = index
    let params = {
        userId: userId
    }
    fetchUserInfo(params).then(res => {
        showUserInfoData.value = res.data.data
    })
}
const getFriendList = () => {
    let params = {
        userId: userInfo.value.userId
    }
    fetchFriendList(params).then(res => {
        friendList.value = res.data.data
    })
}
const goChating = () => {
    store.state.routerChating = showUserInfoData.value.userId.toString()
    skipChat()
}
onMounted(async () => {
    loginToken().then(res => {
        userInfo.value = res.data.data
        getFriendList()
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
.chat_item_middle {
    float: left;
    width: 330px;
    height: 100%;
    background-color: #F7F7F7;
    border-right: solid 1.5px #E0E0E0;
    user-select: none;
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
    .contacts_box {
        width: 100%;
        height: 780px;
        overflow: auto;
    }
    .mail_list {
        margin: 0 auto;
        width: 290px;
        height: 50px;
        background-color: #FFF;
        border-radius: 3px;
        font-size: 18px;
        line-height: 50px;
        -webkit-text-stroke: 0.2px #191919;
        color: #191919;
    }
    .mail_list .icon {
        position: relative;
        top: 7px;
        right: 3px;
    }
    .item_title {
        clear: both;
        width: 290px;
        height: 50px;
        margin: 0 auto;
        color: #191919;
        .icon {
            float: left;
            margin-top: 13px;
        }
        .item_title_content {
            float: left;
            margin-top: 11px;
            margin-left: 3px;
            font-size: 18px;
            -webkit-text-stroke: 0.2px #191919;
            letter-spacing: 0.3px;
        }
        .item_title_number {
            float: right;
            margin-top: 11px;
            font-size: 18px;
            color: #7B7B7B;
            -webkit-text-stroke: 0.2px #7B7B7B;
        }
    }
    .new_mail_box {
        width: 100%;
        overflow: hidden;
        margin: 0 auto;
        .new_mail_item {
            width: 100%;
            height: 80px;
            position: relative;
            .icon {
                width: 50px;
                height: 50px;
                background-color: #666;
                margin-left: 15px;
                border-radius: 5px;
                position: relative;
                top: 15px;
                left: 28px;
                float: left;
            }
            .content {
                position: relative;
                top: 15px;
                left: 42px;
                float: left;
            }
            p {
                line-height: 5px;
                text-align: left;
            }
            p:nth-child(1) {
                font-size: 18px;
                -webkit-text-stroke: 0.2px #191919;
                margin-top: 10px;
            }
            p:nth-child(2) {
                font-size: 16px;
                color: #9B9B9B;
            }
            span {
                position: absolute;
                right: 15px;
                top: 15px;
                color: #ACACAC;
            }
        }
    }
    .mail_box {
        width: 100%;
        overflow: hidden;
        margin: 0 auto;
        .new_mail_item {
            width: 100%;
            height: 80px;
            position: relative;
            .icon {
                width: 50px;
                height: 50px;
                background-color: #666;
                margin-left: 15px;
                border-radius: 5px;
                position: relative;
                top: 15px;
                left: 28px;
                float: left;
            }
            .content {
                position: relative;
                top: 15px;
                left: 42px;
                float: left;
            }
            p {
                text-align: left;
                font-size: 18px;
                -webkit-text-stroke: 0.2px #191919;
                margin-top: 10px;
            }
        }
    }
}

.chat_item_right {
    float: left;
    width: 818.5px;
    height: 100%;
    background-color: #F7F7F7;
    user-select: none;
    .bg {
        width: 100%;
        height: 100%;
        background-color: #FFF;
    }
    .user_info_box {
        width: 75%;
        height: 500px;
        margin: 0 auto;
        position: relative;
        top: 120px;
    }
    .user_info_box_1 {
        width: 100%;
        height: 120px;
        border-bottom: solid #EBEBEB 1.5px;
        .content {
            float: left;
            span {
                font-size: 26px;
                font-weight: 500;
                color: #191919;
            }
        }
        .icon {
            float: right;
            width: 90px;
            height: 90px;
            background-color: #666;
            border-radius: 8px;
        }
    }
    .user_info_box_2 {
        width: 100%;
        height: 250px;
        padding-top: 15px;
        padding-bottom: 15px;
        border-bottom: solid #EBEBEB 1.5px;
        .prop {
            float: left;
            width: 100px;
            text-align: left;
            font-size: 17px;
            color: #7F7F7F;
            -webkit-text-stroke: 0.2px #7F7F7F;
        }
        .content {
            text-align: left;
            float: left;
            font-size: 17px;
            color: #191919;
            -webkit-text-stroke: 0.2px #191919;
        }
    }
    .user_info_box_3 {
        width: 100%;
        height: 300px;
        position: relative;
        .message {
            width: 180px;
            height: 45px;
            background-color: #0DC160;
            line-height: 45px;
            color: #FFF;
            font-size: 18px;
            border-radius: 6px;
            margin: 0 auto;
            margin-top: 90px;
            cursor: pointer;
            -webkit-text-stroke: 0.2px #FFF;
        }
    }
}
</style>