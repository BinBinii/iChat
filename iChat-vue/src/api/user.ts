import request from "../utils/request"

// 登录
export function login(data: object) {
    return request({
        url: '/auth/login',
        method: 'post',
        data
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