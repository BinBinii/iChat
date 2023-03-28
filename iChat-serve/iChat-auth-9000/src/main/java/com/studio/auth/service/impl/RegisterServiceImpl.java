package com.studio.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.studio.auth.mapper.TbUserMapper;
import com.studio.auth.service.RegisterService;
import com.studio.common.model.pojo.TbUser;
import com.studio.common.utils.IdGen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: BinBin
 * @Date: 2023/03/27/21:09
 * @Description:
 */
@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private TbUserMapper tbUserMapper;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public int register(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            return 0;
        }
        QueryWrapper<TbUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        List<TbUser> tbUsers = tbUserMapper.selectList(queryWrapper);
        if (tbUsers.size() > 0) {
            return 2;
        }
        TbUser tbUser = new TbUser();
        tbUser.setUserId(IdGen.uuid())
                .setUsername(username)
                .setPassword(bCryptPasswordEncoder.encode(password))
                .setSex(1)
                .setImage("")
                .setNickname("")
                .setStatus(1);
        if (tbUserMapper.insert(tbUser) != 1) {
            return -1;
        }
        return 1;
    }
}
