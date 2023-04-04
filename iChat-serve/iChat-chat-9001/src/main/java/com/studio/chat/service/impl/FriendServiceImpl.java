package com.studio.chat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.studio.chat.mapper.TbFriendMapper;
import com.studio.chat.mapper.TbFriendReqMapper;
import com.studio.chat.mapper.TbUserMapper;
import com.studio.chat.service.FriendService;
import com.studio.common.model.pojo.TbFriend;
import com.studio.common.model.pojo.TbFriendReq;
import com.studio.common.model.pojo.TbUser;
import com.studio.common.model.vo.FriendReqVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: BinBin
 * @Date: 2023/04/03/08:55
 * @Description:
 */
@Service
public class FriendServiceImpl implements FriendService {

    @Autowired
    private TbFriendMapper tbFriendMapper;
    @Autowired
    private TbFriendReqMapper tbFriendReqMapper;
    @Autowired
    private TbUserMapper tbUserMapper;

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
    public List<FriendReqVo> getFriendRequestListData(String userId) {
        List<FriendReqVo> result = new ArrayList<>();
        QueryWrapper<TbFriendReq> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("req", userId).or().eq("hand", userId);
        List<TbFriendReq> tbFriendReqList = tbFriendReqMapper.selectList(queryWrapper);
        for (TbFriendReq item:tbFriendReqList) {
            TbUser tbUser = new TbUser();
            if (userId.equals(item.getReq())) {
                tbUser = tbUserMapper.selectById(item.getHand());
            }
            if (userId.equals(item.getHand())) {
                tbUser = tbUserMapper.selectById(item.getReq());
            }
            FriendReqVo friendReqVo = new FriendReqVo();
            friendReqVo.setReq(item.getReq())
                    .setHand(item.getHand())
                    .setStatus(item.getStatus())
                    .setCreate_time(item.getCreate_time())
                    .setPass_time(item.getPass_time())
                    .setUsername(tbUser.getUsername())
                    .setSex(tbUser.getSex())
                    .setImage(tbUser.getImage())
                    .setNickname(tbUser.getNickname());
            result.add(friendReqVo);
        }
        return result;
    }

    @Override
    public boolean addFriend(String userId, String hand) {
        if (userId.isEmpty() || hand.isEmpty()) {
            return false;
        }
        QueryWrapper<TbFriendReq> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("req", userId).eq("hand", hand).eq("status", 0);
        List<TbFriendReq> list = tbFriendReqMapper.selectList(queryWrapper);
        if (list.size() > 0) {
            return false;
        }
        TbFriendReq tbFriendReq = new TbFriendReq();
        tbFriendReq.setReq(userId)
                .setHand(hand)
                .setStatus(0)
                .setCreate_time(new Date());
        return tbFriendReqMapper.insert(tbFriendReq) == 1;
    }

    @Override
    public boolean pass(String req, String hand) {
        if (req.isEmpty() || hand.isEmpty()) {
            return false;
        }
        QueryWrapper<TbFriendReq> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("req", req).eq("hand", hand).eq("status", 0);
        List<TbFriendReq> list = tbFriendReqMapper.selectList(queryWrapper);
        if (list.size() == 0) {
            return false;
        }
        TbFriendReq tbFriendReq = list.get(0);
        tbFriendReq.setStatus(1)
                .setPass_time(new Date());
        tbFriendReqMapper.updateById(tbFriendReq);
        TbFriend tbFriend = new TbFriend();
        tbFriend.setUser_id(tbFriendReq.getReq())
                .setFriend_id(tbFriendReq.getHand());
        TbFriend tbFriend1 = new TbFriend();
        tbFriend1.setUser_id(tbFriendReq.getHand())
                .setFriend_id(tbFriendReq.getReq());
        return tbFriendMapper.insert(tbFriend) == 1 && tbFriendMapper.insert(tbFriend1) == 1;
    }

    @Override
    public boolean fail(String req, String hand) {
        if (req.isEmpty() || hand.isEmpty()) {
            return false;
        }
        QueryWrapper<TbFriendReq> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("req", req).eq("hand", hand).eq("status", 0);
        List<TbFriendReq> list = tbFriendReqMapper.selectList(queryWrapper);
        if (list.size() == 0) {
            return false;
        }
        TbFriendReq tbFriendReq = list.get(0);
        tbFriendReq.setStatus(2);
        return tbFriendReqMapper.updateById(tbFriendReq) == 1;
    }

    @Override
    public boolean delFriend(String userId, String hand) {
        if (userId.isEmpty() || hand.isEmpty()) {
            return false;
        }
        // 单方面删除好友
        QueryWrapper<TbFriend> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId).eq("friend_id", hand);
        List<TbFriend> list = tbFriendMapper.selectList(queryWrapper);
        if (list.size() == 0) {
            return false;
        }
        return tbFriendMapper.deleteById(list.get(0).getId()) == 1;
    }
}
