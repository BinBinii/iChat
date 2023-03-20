import request from "../utils/request"

export function login(data: object) {
    return request({
        url: '/auth/login',
        method: 'post',
        data
    })
}