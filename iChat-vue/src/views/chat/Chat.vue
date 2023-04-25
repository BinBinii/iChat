<template>
    <div class="chat">
        <div class="chat_box">
            <div class="chat_item_left">
                <UserInfoPopup v-if="showUserInfoPopup" />
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
                <!-- <div class="header_icon" @click="showUserInfoPopup = !showUserInfoPopup"></div> -->
                <n-icon class="icon" size="33" color="#0DC160" :component="ChatbubbleSharp" />
                <n-icon @click="skipContacts" class="icon" size="33" color="#766574" :component="PersonOutline" />
                <svg @click="router.push({name: 'chatgpt'})" data-name="OpenAI Logo" width="33px" height="33px" style="margin-top:30px" viewBox="140 140 520 520"><defs><linearGradient id="linear" x1="100%" y1="22%" x2="0%" y2="78%"><stop offset="0%" stop-color="rgb(131,211,231)"></stop><stop offset="2%" stop-color="rgb(127,203,229)"></stop><stop offset="25%" stop-color="rgb(86,115,217)"></stop><stop offset="49%" stop-color="rgb(105,80,190)"></stop><stop offset="98%" stop-color="rgb(197,59,119)"></stop><stop offset="100%" stop-color="rgb(197,59,119)"></stop></linearGradient></defs><path id="logo" d="m617.24 354a126.36 126.36 0 0 0 -10.86-103.79 127.8 127.8 0 0 0 -137.65-61.32 126.36 126.36 0 0 0 -95.31-42.49 127.81 127.81 0 0 0 -121.92 88.49 126.4 126.4 0 0 0 -84.5 61.3 127.82 127.82 0 0 0 15.72 149.86 126.36 126.36 0 0 0 10.86 103.79 127.81 127.81 0 0 0 137.65 61.32 126.36 126.36 0 0 0 95.31 42.49 127.81 127.81 0 0 0 121.96-88.54 126.4 126.4 0 0 0 84.5-61.3 127.82 127.82 0 0 0 -15.76-149.81zm-190.66 266.49a94.79 94.79 0 0 1 -60.85-22c.77-.42 2.12-1.16 3-1.7l101-58.34a16.42 16.42 0 0 0 8.3-14.37v-142.39l42.69 24.65a1.52 1.52 0 0 1 .83 1.17v117.92a95.18 95.18 0 0 1 -94.97 95.06zm-204.24-87.23a94.74 94.74 0 0 1 -11.34-63.7c.75.45 2.06 1.25 3 1.79l101 58.34a16.44 16.44 0 0 0 16.59 0l123.31-71.2v49.3a1.53 1.53 0 0 1 -.61 1.31l-102.1 58.95a95.16 95.16 0 0 1 -129.85-34.79zm-26.57-220.49a94.71 94.71 0 0 1 49.48-41.68c0 .87-.05 2.41-.05 3.48v116.68a16.41 16.41 0 0 0 8.29 14.36l123.31 71.19-42.69 24.65a1.53 1.53 0 0 1 -1.44.13l-102.11-59a95.16 95.16 0 0 1 -34.79-129.81zm350.74 81.62-123.31-71.2 42.69-24.64a1.53 1.53 0 0 1 1.44-.13l102.11 58.95a95.08 95.08 0 0 1 -14.69 171.55c0-.88 0-2.42 0-3.49v-116.68a16.4 16.4 0 0 0 -8.24-14.36zm42.49-63.95c-.75-.46-2.06-1.25-3-1.79l-101-58.34a16.46 16.46 0 0 0 -16.59 0l-123.31 71.2v-49.3a1.53 1.53 0 0 1 .61-1.31l102.1-58.9a95.07 95.07 0 0 1 141.19 98.44zm-267.11 87.87-42.7-24.65a1.52 1.52 0 0 1 -.83-1.17v-117.92a95.07 95.07 0 0 1 155.9-73c-.77.42-2.11 1.16-3 1.7l-101 58.34a16.41 16.41 0 0 0 -8.3 14.36zm23.19-50 54.92-31.72 54.92 31.7v63.42l-54.92 31.7-54.92-31.7z" fill="#766574"></path></svg>
                <n-icon class="icon" size="33" color="#766574" :component="Menu" />
            </div>
            <div class="chat_item_middle">
                <div class="search_box">
                    <input class="search" v-model="searchInput" placeholder="搜索">
                    <div class="add_person" @click="showCreateGroup"><span>+</span></div>
                </div>
                <div class="chat_records_box">
                    <template v-for="(item,index) in chatingList">
                        <div class="chat_records_item" :class="showChatingIndex == index?'chat_records_item_check':''" @click="handleChat(item.userId, item.hand, item.nickname, index)">
                            <div class="icon"></div>
                            <div class="content">
                                <p>{{ item.nickname }}</p>
                                <p>{{ item.toMsg }}</p>
                                <span>{{ formatDate(item.sendTime) }}</span>
                            </div>
                        </div>
                    </template>
                </div>
            </div>
            <div class="chat_item_right">
                <div v-if="showChat">
                    <div class="chat_personal_title">
                        <span>{{ chatingTitle }}</span>
                    </div>
                    <div class="chat_content" ref="chatContent" @scroll="handleChatScroll">
                        <div>
                            <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" width="20px" height="32px" viewBox="0 0 24 30" style="enable-background:new 0 0 50 50;margin-top: 10px;" xml:space="preserve" v-if="showLoading">
                                <rect x="0" y="7.6416" width="4" height="14.7168" fill="#666" opacity="0.2">
                                    <animate attributeName="opacity" attributeType="XML" values="0.2; 1; .2" begin="0s" dur="0.6s" repeatCount="indefinite"></animate>
                                    <animate attributeName="height" attributeType="XML" values="10; 20; 10" begin="0s" dur="0.6s" repeatCount="indefinite"></animate>
                                    <animate attributeName="y" attributeType="XML" values="10; 5; 10" begin="0s" dur="0.6s" repeatCount="indefinite"></animate>
                                </rect>
                                <rect x="8" y="5.1416" width="4" height="19.7168" fill="#666" opacity="0.2">
                                    <animate attributeName="opacity" attributeType="XML" values="0.2; 1; .2" begin="0.15s" dur="0.6s" repeatCount="indefinite"></animate>
                                    <animate attributeName="height" attributeType="XML" values="10; 20; 10" begin="0.15s" dur="0.6s" repeatCount="indefinite"></animate>
                                    <animate attributeName="y" attributeType="XML" values="10; 5; 10" begin="0.15s" dur="0.6s" repeatCount="indefinite"></animate>
                                </rect>
                                <rect x="16" y="7.3584" width="4" height="15.2832" fill="#666" opacity="0.2">
                                    <animate attributeName="opacity" attributeType="XML" values="0.2; 1; .2" begin="0.3s" dur="0.6s" repeatCount="indefinite"></animate>
                                    <animate attributeName="height" attributeType="XML" values="10; 20; 10" begin="0.3s" dur="0.6s" repeatCount="indefinite"></animate>
                                    <animate attributeName="y" attributeType="XML" values="10; 5; 10" begin="0.3s" dur="0.6s" repeatCount="indefinite"></animate>
                                </rect>
                            </svg>
                        </div>
                        <template v-for="item in chating">
                            <p v-if="item.timer_show" class="time_stamp">{{ formatDateMin(item.send_time) }}</p>
                            <div class="user_box" v-if="item.from_user === userInfo.userId">
                                <div class="icon"></div>
                                <div>
                                    <div class="chat_msg">
                                        <div class="chat_triangle"></div>
                                        <span>{{ item.post_message }}</span>
                                    </div>
                                </div>
                            </div>
                            <div class="hand_box" v-else>
                                <div class="icon"></div>
                                <div>
                                    <span class="hand_nickname">{{ item.from_user_nickname }}</span>
                                    <div class="chat_msg">
                                        <div class="chat_triangle"></div>
                                        <span>{{ item.post_message }}</span>
                                    </div>
                                </div>
                            </div>
                        </template>
                    </div>
                    <div class="chat_input">
                        <div>
                            <n-icon class="icon" size="28" color="#766574" :component="MoodHappy" />
                        </div>
                        <textarea v-model="message" @keydown.enter="carriageReturn($event)" v-on:keyup.enter="sendMessage"></textarea>
                    </div>
                </div>
            </div>
            <div class="create_group_mask" v-if="showCreateGroupPopup">
                <div class="create_group_box">
                    <div class="left">
                        <input class="search" v-model="searchInput" placeholder="搜索">
                        <div style="clear: both;overflow: auto;height: 90%;">
                            <template v-for="item in friendList">
                                <p class="title">{{ item.title }}</p>
                                <template v-for="friend in item.list">
                                    <div :style="chooseCreateGroupFriendIndex == friend.userId?'background-color: #DEDEDE':''" class="friend_item" @click="createGroupchooseFriendItem(friend)">
                                        <n-checkbox size="large" class="checkbox" v-model:checked="friend.checked" />
                                        <div class="icon"></div>
                                        <p class="nickname">{{ friend.nickname }}</p>
                                    </div>
                                </template>
                            </template>
                        </div>
                    </div>
                    <div class="right">
                        <div class="right_item1">
                            <span>发起群聊</span>
                            <span v-if="chooseFriendList.length == 0">未选择联系人</span>
                            <span v-else>已选择{{chooseFriendList.length}}个联系人</span>
                        </div>
                        <div class="right_item2">
                            <div class="friend_item" v-for="friend in chooseFriendList">
                                <div class="icon"></div>
                                <span class="nickname">{{ friend.nickname }}</span>
                                <div class="close">
                                    <n-icon style="margin-top: 3px;" size="14" color="#FFF" :component="Close" @click="deleteChooseFriend(friend)" />
                                </div>
                            </div>
                        </div>
                        <div class="right_item3">
                            <div class="close" @click="showCreateGroupPopup = false">取消</div>
                            <div class="create">创建</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <n-modal v-if="userInfo.nickname==''" v-model:show="showUserIndividualizationModal" transform-origin="center">
            <div class="showUserIndiv_box">
                <p class="title">完善个人信息</p>
                <span>该怎么称呼你：</span>
                <input type="text" placeholder="昵称" />
                <span>你的性别是：</span>
                    <div class="sex_box">
                        <!-- <n-radio-group v-model="userInfo.sex" name="sexradiogroup">
                            <n-radio value="1">男</n-radio>
                            <n-radio value="0">女</n-radio>
                        </n-radio-group> -->
                    </div>
                <span>你的头像：</span>
                <n-upload
                    style="margin-left: 200px;"
                    action="https://www.mocky.io/v2/5e4bafc63100007100d8b70f"
                    list-type="image-card"
                    @preview="handlePreview"
                />
                <div class="userIndivConfirm">确定</div>
            </div>
        </n-modal>
    </div>
</template>
<script lang="ts" setup>
import { reactive, ref, onMounted, onUnmounted } from 'vue'
import { mainStore } from '../../store'
import { useRouter } from 'vue-router'
import { ChatbubbleSharp, PersonOutline, Menu, Close } from '@vicons/ionicons5'
import { MoodHappy } from '@vicons/tabler'
import { NIcon, NModal, NRadio, NRadioGroup, NUpload, NAvatar, NCheckbox } from 'naive-ui'

import UserInfoPopup  from '../../components/UserInfoPopup.vue'
import axios from "axios"

import { loginToken, fetchUserInfo } from '../../api/user'
import { fetchMessagesHistoryList, fetchMessages, readMessages } from '../../api/messages'
import { fetchFriendList } from '../../api/friend'
import { UserInfoType, MessageToType, MessageType, PageInfo, CreateGroupFriendListType, CreateGroupFriend } from '../../interface/storeInterface'
import { creatWebSocket, sendWebSocket, closeWebSocket } from '../../utils/webSocket'
import { pinyin } from 'pinyin-pro'

const store = mainStore()
const router = useRouter()
const searchInput = ref('')
const message = ref('')
const messageType = ref(1)  // 1是单聊 9是群聊
const userInfo = ref({} as UserInfoType)
const showChat = ref(false)
const chatingList = ref([] as MessageToType[])
const chating = ref([] as MessageType[])
const unReadMsgArr = ref([] as Number[])
const pageInfo = ref({} as PageInfo)
// const chating = ref({} as DataTablesResult)
const chatingTitle = ref('' as String)
const selectHand = ref('' as String)
const showChatingIndex = ref(-1 as any)
const showUserIndividualizationModal = ref(true)
const showUserInfoPopup = ref(false)
const showLoading = ref(false)
const showCreateGroupPopup = ref(false)
const chooseCreateGroupFriendIndex = ref('' as String)
const friendList = ref([] as CreateGroupFriendListType[])
const chooseFriendList = ref([] as CreateGroupFriend[])

const chatContent = ref()

const handlePreview = () => {
    
}
const getMessagesHistoryList = () => {
    let params = {
        userId: userInfo.value.userId
    }
    fetchMessagesHistoryList(params).then(res => {
        chatingList.value = res.data.data
        if (store.state.routerChating != '') {
            fetchUserInfo({
                userId: store.state.routerChating
            }).then(res => {
                let chatUser:UserInfoType = res.data.data
                handleChat(userInfo.value.userId, chatUser.userId, chatUser.nickname, 0)
            })
        }
    })
}
const getMessages = (userId: String, hand: String, start: Number) => {
    let params = {
        userId: userId,
        hand: hand,
        start: start
    }
    fetchMessages(params).then(res => {
        showLoading.value = false
        pageInfo.value.pages = res.data.pages
        pageInfo.value.current = res.data.current
        let notesHeight = 0, timer
        if (chating.value.length == 0) {
            for (let i = 0; i < res.data.data.length; i++) {
                if (res.data.data[i].status == 0) {  // 未读消息
                    unReadMsgArr.value.push(res.data.data[i].id)
                }
                if (timer == null) {
                    timer = res.data.data[0].send_time
                    res.data.data[0].timer_show = true
                }
                let timeStamp = (+new Date(res.data.data[i].send_time) - +new Date(timer)) / 1000
                if (timeStamp > 300) {
                    res.data.data[i].timer_show = true
                    timer = res.data.data[i].send_time
                } else {
                    res.data.data[i].timer_show = false
                }
            }
            chating.value = res.data.data
            setTimeout(() => {
                chatContent.value.scrollTop = chatContent.value.scrollHeight
            },10)
        } else {
            let arr:MessageType[] = res.data.data
            for (let i = 0; i < arr.length; i++) {
                if (arr[i].status == 0) {  // 未读信息
                    unReadMsgArr.value.push(arr[i].id)
                }
                if (timer == null) {
                    timer = arr[0].send_time
                    arr[0].timer_show = true
                }
                let timeStamp = (+new Date(arr[i].send_time) - +new Date(timer)) / 1000
                if (timeStamp > 300) {
                    arr[i].timer_show = true
                    timer = arr[i].send_time
                } else {
                    arr[i].timer_show = false
                }
            }
            let newArr = arr.concat(chating.value)
            chating.value = newArr
            setTimeout(() => {
                chatContent.value.scrollTop = chatContent.value.scrollHeight - notesHeight
            },10)
        }
        notesHeight = chatContent.value.scrollHeight
        let data = {
            ids: unReadMsgArr.value
        }
        readMessages(data).then(res => {
        })
    })
    
}
const getUserInfo = (userId: String) => {
    let params = {
        userId: userId
    }
    fetchUserInfo(params).then(res => {
    })
}
const skipContacts = () => {
    router.push({name: 'contacts'})
}
const handleChat = (userId: String, hand: String, nickname: String, index: Number) => {
    selectHand.value = hand
    showChatingIndex.value = index
    showChat.value = true
    chatingTitle.value = nickname
    chating.value = []
    getMessages(userId, hand, 1)
}
const sendMessage = () => {
    let data = {
        type: messageType.value,
        params: {
            toMessageId: selectHand.value,
            fromMessageId: userInfo.value.userId,
            message: message.value,
            fileType: 0,
        },
    }
    let item:MessageType = {
        id: -1,
        post_message: data.params.message,
        status: 0,
        send_time: new Date(),
        from_user: data.params.fromMessageId,
        to_user: data.params.toMessageId,
        from_user_nickname: userInfo.value.nickname,
        timer_show: false
    }
    chating.value.push(item)
    message.value = ''
    chatingList.value[showChatingIndex.value].toMsg = data.params.message
    sendWebSocket(data)
    setTimeout(() => {
        chatContent.value.scrollTop = chatContent.value.scrollHeight
    },1000)
}
const carriageReturn = (event:any) => {
    if (event.keyCode == 13) {
        if (!event.metaKey) {
          event.preventDefault();
        }
    }
}
const formatDate = (time:Date) => {
    let times = new Date(time)
    if (time == null) {
        return ''
    }
    if (times.toDateString() === new Date().toDateString()) {
        return (times.getHours() < 10 ? '0' + times.getHours() : times.getHours()) + ':' + (times.getMinutes() < 10 ? '0' + times.getMinutes() : times.getMinutes())
    } else {
        return times.getFullYear() + '/' + (times.getMonth() < 10 ? '0' + times.getMonth() : times.getMonth()) + '/' + (times.getDay() < 10 ? '0' + times.getDay() : times.getDay())
    }
}
const formatDateMin = (time:Date) => {
    let times = new Date(time)
    if (time == null) {
        return ''
    }
    if (times.toDateString() === new Date().toDateString()) {
        return (times.getHours() < 10 ? '0' + times.getHours() : times.getHours()) + ':' + (times.getMinutes() < 10 ? '0' + times.getMinutes() : times.getMinutes())
    } else {
        return times.getFullYear() + '年' + (times.getMonth() < 10 ? '0' + times.getMonth() : times.getMonth()) + '月' + (times.getDay() < 10 ? '0' + times.getDay() : times.getDay()) + '日 ' + (times.getHours() < 10 ? '0' + times.getHours() : times.getHours()) + ':' + (times.getMinutes() < 10 ? '0' + times.getMinutes() : times.getMinutes())
    }
}
onMounted(async () => {
    loginToken().then(res => {
        userInfo.value = res.data.data
        getMessagesHistoryList()
        creatWebSocket(res.data.data)
        store.state.websocket.onmessage = function (e: any) {
            websocketonmessage(e);
        };
    })
});
onUnmounted(async () => {
    closeWebSocket()
})
const websocketonmessage = (e: any) => {
  let json = JSON.parse(e.data)
  console.log(json)
  if (json.status == 200) {
    switch(json.type) {
        case 2:     // 收到单聊信息
            handleSingleChatInfo(json)
            break;
        case 4:     // 创建聊天
            break;
        case 10:    // 收到群聊信息
            break;
        default:
            break;
    }
  }
  // let data = JSON.parse(decodeUnicode(e.data))
}
const handleSingleChatInfo = (json:any) => {
    for (let i = 0; i < chatingList.value.length; i++) {
        // 修改历史聊天记录
        if (chatingList.value[i].hand == json.params.fromUser.userId) {
            chatingList.value[i].toMsg = json.params.message
        }
        // 修改聊天记录
        if (showChatingIndex.value == i && chatingList.value[i].hand === json.params.fromUser.userId) {
            let item:MessageType = {
                id: -1,
                post_message:json.params.message,
                status: 0,
                send_time: new Date(),
                from_user: json.params.fromUser.userId,
                to_user: json.params.toUser.userId,
                from_user_nickname: json.params.fromUser.nickname,
                timer_show: false,
            }
            chating.value.push(item)
            setTimeout(() => {
                chatContent.value.scrollTop = chatContent.value.scrollHeight
            },1000)
        }
    }
}
const handleChatScroll = () => {
    if (chatContent.value.scrollTop == 0) {
        if (pageInfo.value.current < pageInfo.value.pages) {
            showLoading.value = true
            let nextCurrent = Number(pageInfo.value.current) + 1
            getMessages(userInfo.value.userId, selectHand.value, nextCurrent)
        }
        
    }
}
const showCreateGroup = () => {
    friendList.value = []
    chooseFriendList.value = []
    showCreateGroupPopup.value = !showCreateGroupPopup.value
    let params = {
        userId: userInfo.value.userId
    }
    fetchFriendList(params).then(res => {
        for (let friend of res.data.data) {
            friend.checked = false
            let initial = toPinyin(friend.nickname).substring(0,1).toUpperCase()
            if (friendList.value.length === 0) {
                let friendItem:CreateGroupFriendListType = {
                    title: initial,
                    list: [friend]
                }
                friendList.value.push(friendItem)
            } else {
                let newFlag = true
                for (let item of friendList.value) {
                    if (item.title == initial) {
                        newFlag = false
                        item.list.push(friend)
                        break
                    }
                }
                if (newFlag) {
                    let friendItem:CreateGroupFriendListType = {
                        title: initial,
                        list: [friend]
                    }
                    friendList.value.push(friendItem)
                }
            }   
        }
        friendList.value.sort((n1,n2)=> {
            if (n1.title < n2.title) {
                return -1
            }
            if (n1.title > n2.title) {
                return 1
            }
            return 0
        })
        console.log(friendList.value)
    })
}
const createGroupchooseFriendItem = (friend: CreateGroupFriend) => {
    friend.checked = !friend.checked
    chooseCreateGroupFriendIndex.value = friend.userId
    chooseFriendList.value = []
    for (let i = 0; i < friendList.value.length; i++) {
        for (let j = 0; j < friendList.value[i].list.length; j++) {
            if (friendList.value[i].list[j].checked) {
                chooseFriendList.value.push(friendList.value[i].list[j])
            }
        }
    }
}
const deleteChooseFriend = (friend: CreateGroupFriend) => {
    friend.checked = false
    chooseFriendList.value = []
    for (let i = 0; i < friendList.value.length; i++) {
        for (let j = 0; j < friendList.value[i].list.length; j++) {
            if (friendList.value[i].list[j].checked) {
                chooseFriendList.value.push(friendList.value[i].list[j])
            }
        }
    }
}
const getImgURLOfBase64 = async(imgUrl: String) => {
  // 通过图片地址获取图片，从新获取图片
  var config:any = {
    method: 'get',
    responseType: 'arraybuffer',
    url: imgUrl,
    headers: {
        'Authorization': store.state.token
    }
  };
  // 重新获取请求，获取的是base64位的图片
  return await axios(config).then(response => {
    return 'data:image/png;base64,' + btoa(new Uint8Array(response.data).reduce((data, byte) => data + String.fromCharCode(byte), ''));
  })
}
const toPinyin = (text: any) => {
    return pinyin(text, { toneType: 'none' })
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
    background-color: #FFF;
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
            letter-spacing: .2px;
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
        .time_stamp {
            color: #AAAAAA;
            letter-spacing: 0.2px;
        }
        .hand_box {
            padding-top: 20px;
            padding-bottom: 10px;
            width: 100%;
            overflow: hidden;
            clear: both;
            * {
                float: left;
            }
            .icon {
                margin-left: 20px;
                margin-right: 15px;
            }
            .hand_nickname {
                margin-top: -3px;
                // margin-bottom: 3px;
                color: #AAAAAA;
                user-select: none;
            }
            .chat_msg {
                clear: both;
                background-color: #FFF;
                padding: 10px 15px;
                font-size: 16px;
                border-radius: 5px;
                position: relative;
                max-width: 400px;
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
            padding-top: 20px;
            padding-bottom: 10px;
            width: 100%;
            overflow: hidden;
            clear: both;
            * {
                float: right;
            }
            .icon {
                margin-right: 20px;
                margin-left: 15px;
            }
            .chat_msg {
                clear: both;
                background-color: #95EC69;
                padding: 10px 15px;
                font-size: 16px;
                border-radius: 5px;
                position: relative;
                text-align: left;
            }
            .chat_triangle {
                width: 0;
                height: 0;
                border-top: 10px solid #95EC69;
                border-right: 10px solid transparent;
                border-left: 10px solid transparent;
                transform: rotate(-90deg);
                position: absolute;
                right: -10px;
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
    .chat_input {
        width: 100%;
        height: 200px;
        border-top: solid 1.5px #E0E0E0;
        div {
            width: 100%;
            height: 23%;
            .icon {
                margin-top: 20px;
                float: left;
                margin-left: 10px;
                cursor: pointer;
            }
        }
        textarea {
            width: 96%;
            height: 100px;
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
.showUserIndiv_box {
    width: 500px;
    height: 650px;
    background-color: #F7F7F7;
    border-radius: 10px;
    .title {
        text-align: center;
        font-size: 25px;
        padding-top: 30px;
    }
    input {
        text-align: center;
        outline: none;
        width: 300px;
        height: 35px;
        border-radius: 6px;
        border: solid #b3b3b3 1px;
    }
    span {
        margin-left: 100px;
        display: block;
        padding-top: 20px;
        padding-bottom: 10px;
    }
    .sex_box {
        width: 300px;
        height: 35px;
        background-color: #FFF;
        border-radius: 6px;
        border: solid #b3b3b3 1px;
    }
    .userIndivConfirm {
        width: 80px;
        height: 35px;
        text-align: center;
        line-height: 35px;
        color: #FFF;
        background-color: #0DC160;
        border-radius: 5px;
        margin-top: 80px;
        cursor: pointer;
    }
}
.create_group_mask {
    width: 100%;
    height: 100%;
    background-color: rgba(120, 120, 120, 0.3);
    position: absolute;
    top: 0;
    left: 0;
    .create_group_box {
        position: relative;
        width: 750px;
        height: 550px;
        background-color: #FFF;
        top: 150px;
        left: 230px;
        border-radius: 10px;
        box-shadow: 0px 0px 20px rgba(0, 0, 0, .1);
        overflow: hidden;
        color: #191919;
        .left {
            float: left;
            width: 44.8%;
            height: 100%;
            background-color: #F7F7F7;
            border-right: solid 1.5px #EBEBEB;
            .search {
                width: 260px;
            }
            .title {
                text-align: left;
                color: #7B7B7B;
                font-size: 16px;
                margin-left: 20px;
                line-height: 0px;
                margin-top: 30px;
            }
            .friend_item {
                height: 40px;
                clear: both;
                padding: 15px 0px;
                cursor: pointer;
                .checkbox {
                    float: left;
                    margin-top: 8px;
                    margin-left: 20px;
                    margin-right: 15px;
                    .n-checkbox-box__border {
                    border: #333;
                }
                }
                .icon {
                    float: left;
                    width: 40px;
                    height: 40px;
                    background-color: #666;
                    border-radius: 3px;
                }
                .nickname {
                    float: left;
                    font-size: 16px;
                    color: #181818;
                    -webkit-text-stroke: 0.1px #181818;
                    margin-left: 15px;
                    margin-top: 7px;
                }
            }
        }
        .right {
            float: right;
            width: 55%;
            height: 100%;
            background-color: #FFF;
            padding-top: 20px;
            
            .right_item1 {
                width: 100%;
                height: 20px;
                span:nth-child(1) {
                    font-size: 18px;
                    -webkit-text-stroke: 0.2px #191919;
                    float: left;
                    margin-left: 40px;
                }
                span:nth-child(2) {
                    color: #B2B2B2;
                    float: right;
                    margin-top: 5px;
                    margin-right: 40px;
                }
            }
            .right_item2 {
                margin-left: 5%;
                width: 330px;
                height: 415px;
                margin-top: 5px;
                overflow-y: auto;
                padding-left: 20px;
                padding-right: 20px;
                padding-top: 20px;
                .friend_item {
                    width: 90px;
                    height: 90px;
                    text-align: center;
                    float: left;
                    margin-left: 10px;
                    margin-bottom: 5px;
                    position: relative;
                    .icon {
                        width: 55px;
                        height: 55px;
                        background-color: #666;
                        margin: 0 auto;
                        border-radius: 3px;
                    }
                    .nickname {
                        color: #191919;
                        -webkit-text-stroke: 0.2px #191919;
                    }
                    .close {
                        position: absolute;
                        top: -8px;
                        right: 10px;
                        width: 20px;
                        height: 20px;
                        border-radius: 20px;
                        background-color: #808080;
                    }
                }
            }
            .right_item3 {
                width: 100%;
                .close {
                    float: left;
                    width: 130px;
                    height: 35px;
                    line-height: 35px;
                    font-size: 18px;
                    background-color: #F2F2F2;
                    color: #0DC160;
                    -webkit-text-stroke: 0.2px #0DC160;
                    border-radius: 5px;
                    margin-left: 68px;
                    cursor: pointer;
                }
                .create {
                    float: right;
                    width: 130px;
                    height: 35px;
                    line-height: 35px;
                    font-size: 18px;
                    background-color: #0DC160;
                    color: #FFF;
                    -webkit-text-stroke: 0.2px #FFF;
                    border-radius: 5px;
                    margin-right: 68px;
                    cursor: pointer;
                }
            }
        }
    }
}
</style>