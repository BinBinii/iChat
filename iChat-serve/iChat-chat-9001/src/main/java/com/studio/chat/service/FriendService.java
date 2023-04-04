package com.studio.chat.service;

import com.studio.common.model.pojo.TbUser;
import com.studio.common.model.vo.FriendReqVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: BinBin
 * @Date: 2023/04/03/08:55
 * @Description:
 */
@Service
public interface FriendService {

    /**
     * 获取好友列表
     * @param userId
     * @return
     */
    List<TbUser> getFriendListData(String userId);

    /**
     * 获取好友请求列表
     * @param userId
     * @return
     */
    List<FriendReqVo> getFriendRequestListData(String userId);

    /**
     * 添加好友
     * @param userId
     * @param hand
     * @return
     */
    boolean addFriend(String userId, String hand);

    /**
     * 通过好友请求
     * @param req
     * @param hand
     * @return
     */
    boolean pass(String req, String hand);

    /**
     * 不通过好友请求
     * @param req
     * @param hand
     * @return
     */
    boolean fail(String req, String hand);

    /**
     * 删除好友
     * @param userId
     * @param hand
     * @return
     */
    boolean delFriend(String userId, String hand);

}
