import request from "../utils/request"

export function fetchFriendList(query: object) {
    return request({
        url: '/friend/list',
        method: 'get',
        params: query
    })
}

export function fetchFriendReqList(query: object) {
    return request({
        url: '/friend/list/req',
        method: 'get',
        params: query
    })
}

// 获取用户信息
export function fetchUserInfo(query: object) {
    return request({
        url: '/user/get/info',
        method: 'get',
        params: query
    })
}