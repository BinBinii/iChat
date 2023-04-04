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