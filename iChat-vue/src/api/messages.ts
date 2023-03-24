import request from "../utils/request"

export function fetchMessagesHistoryList(query: object) {
    return request({
        url: '/msg/history',
        method: 'get',
        params: query
    })
}