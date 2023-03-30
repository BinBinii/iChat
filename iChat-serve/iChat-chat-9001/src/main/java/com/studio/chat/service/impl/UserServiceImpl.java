package com.studio.chat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.studio.chat.mapper.TbFriendMapper;
import com.studio.chat.mapper.TbUserMapper;
import com.studio.chat.service.UserService;
import com.studio.common.model.pojo.TbFriend;
import com.studio.common.model.pojo.TbUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: BinBin
 * @Date: 2023/03/14/22:16
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private TbUserMapper tbUserMapper;
    @Autowired
    private TbFriendMapper tbFriendMapper;

    @Override
    public List<TbUser> getFriendListData(String userId) {
        QueryWrapper<TbFriend> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        List<TbFriend> tbFriendList = tbFriendMapper.selectList(queryWrapper);
        List<TbUser> tbUsers = new ArrayList<>();
        for (TbFriend tbFriend:tbFriendList) {
            TbUser tbUser = tbUserMapper.selectById(tbFriend.getFriend_id());
            tbUser.setPassword("");
            tbUsers.add(tbUser);
        }
        return tbUsers;
    }

    @Override
    public TbUser getUserInfo(String userId) {
        TbUser tbUser = tbUserMapper.selectById(userId);
        tbUser.setPassword("");
        return tbUser;
    }

    @Override
    public boolean update(TbUser tbUser) {
        if (tbUser.getNickname().isEmpty()) {
            return false;
        }

        return tbUserMapper.updateById(tbUser) == 1;
    }
}
