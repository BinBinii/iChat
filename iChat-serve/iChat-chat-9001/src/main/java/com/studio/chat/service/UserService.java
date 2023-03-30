package com.studio.chat.service;

import com.studio.common.model.pojo.TbUser;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: BinBin
 * @Date: 2023/03/14/22:16
 * @Description:
 */
@Service
public interface UserService {

    /**
     * 获取好友列表
     * @param userId
     * @return
     */
    List<TbUser> getFriendListData(String userId);

    /**
     * 获取用户信息
     * @param userId
     * @return
     */
    TbUser getUserInfo(String userId);

    /**
     * 更新用户信息
     * @param tbUser
     * @return
     */
    boolean update(TbUser tbUser);
}
