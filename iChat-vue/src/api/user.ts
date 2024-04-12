import request from "../utils/AuthRequest"

// 登录
export function login(data: object) {
    return request({
        url: '/login',
        method: 'post',
        data
    })
}

// 注册
export function register(data: object) {
    return request({
        url: '/register',
        method: 'post',
        data
    })
}

// 根据token获取用户信息
export function loginToken() {
    return request({
        url: '/login/token',
        method: 'get'
    })
}