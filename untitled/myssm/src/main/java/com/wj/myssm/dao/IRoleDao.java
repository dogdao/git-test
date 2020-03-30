package com.wj.myssm.dao;

import com.wj.myssm.entity.Role;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IRoleDao {

    //保存新增角色
    @Insert("insert into role (id,roleName,roleDesc) values(#{id},#{roleName},#{roleDesc})")
    void save(Role role) throws Exception;

    //查询所有角色信息
    @Select("select * from role")
    List<Role> findAll() throws Exception;

    @Select("select * from role where id in (select roleId from users_role where userId = #{userId})")
    List<Role> findRoleByUserId(String userId);

    @Select("select * from role where id in " +
            "(select roleId from users_role where userId = #{userId})")
    List<Role> findByUserId(String userId);
}
