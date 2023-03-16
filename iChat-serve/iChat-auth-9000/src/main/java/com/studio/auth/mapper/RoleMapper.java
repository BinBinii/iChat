package com.studio.auth.mapper;

import com.studio.auth.entity.Role;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: BinBin
 * @Date: 2023/03/15/16:27
 * @Description:
 */
@Repository("roleMapper")
public interface RoleMapper {

    @Select("SELECT r.id, r.role_name roleName, r.role_desc roleDesc " +
            "FROM tb_role r, tb_user_role ur " +
            "WHERE r.id=ur.role_id AND ur.user_id=#{uid}")
    public List<Role> findByUid(Integer uid);

}
