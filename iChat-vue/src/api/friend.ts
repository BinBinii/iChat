import request from "../utils/request"

export function fetchFriendList(query: object) {
    return request({
        url: '/user/friend/list',
        method: 'get',
        params: query
    })
}