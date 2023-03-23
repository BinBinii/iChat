package com.studio.auth.controller;

import com.studio.auth.config.RsaKeyProperties;
import com.studio.auth.entity.User;
import com.studio.auth.utils.JwtUtils;
import com.studio.common.model.vo.Render;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: BinBin
 * @Date: 2023/03/23/19:32
 * @Description:
 */
@RestController
public class TokenController {

    private RsaKeyProperties rsaKeyProperties;

    public TokenController(RsaKeyProperties rsaKeyProperties) {
        this.rsaKeyProperties = rsaKeyProperties;
    }

    @GetMapping("/login/token")
    public Object getUserInfoByToken(HttpServletRequest request) {
        String token = request.getHeader("token").substring(11);
        User user = JwtUtils.getInfoFromToken(token, rsaKeyProperties.getPublicKey(), User.class).getUserInfo();
        return Render.ok(user);
    }


}
