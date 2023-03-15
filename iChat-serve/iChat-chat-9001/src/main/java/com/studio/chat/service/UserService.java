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

    List<TbUser> getFriendListData(String userId);
}
