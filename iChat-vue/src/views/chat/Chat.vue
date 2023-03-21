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
            </div>
            <div class="chat_item_middle">
                <div class="search_box">
                    <input class="search" v-model="searchInput" placeholder="搜索">
                    <div class="add_person"><span>+</span></div>
                </div>
            </div>
            <div class="chat_item_right">
                <div class="chat_personal_title"></div>
            </div>
        </div>
    </div>
</template>
<script lang="ts" setup>
import { reactive, onMounted, ref, defineComponent } from 'vue'
import { mainStore } from '../../store'
import { fetchFriendList } from '../../api/friend'
const store = mainStore()
const searchInput = ref('')
onMounted(async () => {
    let parmas = {
        userId: '4150901'
    }
    fetchFriendList(parmas).then(res => {
        console.log(res)
    })
});
</script>
<style lang="less" scoped>
.chat_box {
    width: 1230px;
    height: 850px;
    background-color: #FFF;
    overflow: hidden;
    border-radius: 12px;
}
.chat_item_left {
    float: left;
    background-color: #E7C8E8;
    width: 80px;
    height: 100%;
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
    border-radius: 3px;
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
.chat_item_right {
    float: left;
    width: 818.5px;
    height: 100%;
    background-color: #F3F3F3;
}
.chat_personal_title {
    width: 100%;
    height: 70px;
    border-bottom: solid 1.5px #E0E0E0;
}
</style>