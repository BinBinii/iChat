package com.studio.auth.controller;

import com.studio.auth.service.RegisterService;
import com.studio.common.model.dto.RegisterDto;
import com.studio.common.model.vo.Render;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: BinBin
 * @Date: 2023/03/27/21:27
 * @Description:
 */
@RestController
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @PostMapping("register")
    public Object register(@RequestBody RegisterDto registerDto) {
        int result = registerService.register(registerDto.getUsername(), registerDto.getPassword());
        if (result == -1) {
            return Render.fail("Database Error");
        }
        if (result == 0) {
            return Render.fail("User name or password cannot be empty");
        }
        if (result == 2) {
            return Render.fail("The user name has already been registered");
        }
        return Render.ok(true);
    }

}
