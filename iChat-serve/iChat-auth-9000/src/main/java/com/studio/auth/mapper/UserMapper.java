package com.studio.auth.mapper;

import com.studio.auth.entity.User;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: BinBin
 * @Date: 2023/03/15/16:27
 * @Description:
 */
@Repository("userMapper")
public interface UserMapper {
    @Select("select * from tb_user where username=#{username}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roles", column = "id", javaType = List.class,
                    many = @Many(select = "com.studio.auth.mapper.RoleMapper.findByUid"))
    })
    public User findByUsername(String username);
}