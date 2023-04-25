import request from "../utils/request"

export function fetchMessagesHistoryList(query: object) {
    return request({
        url: '/msg/history',
        method: 'get',
        params: query
    })
}
export function fetchMessages(query: object) {
    return request({
        url: '/msg/list',
        method: 'get',
        params: query
    })
}
export function readMessages(data: object) {
    return request({
        url: '/msg/read',
        method: 'post',
        data
    })
}