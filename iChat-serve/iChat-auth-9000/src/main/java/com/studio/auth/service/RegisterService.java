package com.studio.auth.service;

import org.springframework.stereotype.Service;

/**
 * @Author: BinBin
 * @Date: 2023/03/27/21:09
 * @Description:
 */
@Service
public interface RegisterService {
    /**
     * 注册
     * @param username
     * @param password
     * @return
     */
    int register(String username, String password);
}
