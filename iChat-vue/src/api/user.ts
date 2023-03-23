import request from "../utils/request"

// 登录
export function login(data: object) {
    return request({
        url: '/auth/login',
        method: 'post',
        data
    })
}

// 根据token获取用户信息
export function loginToken() {
    return request({
        url: '/auth/login/token',
        method: 'get'
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