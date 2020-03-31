package com.wj.myssm.dao;

import com.wj.myssm.entity.Role;
import com.wj.myssm.entity.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userDao")
public interface IUserDao {

    //保存新增角色信息
    @Insert("insert into users_role (userId,roleId) values (#{userId},#{roleId})")
    void addRoleToUser(@Param("userId") String userId,@Param("roleId") String roleId) throws Exception;

    //查询用户可以添加的角色
    @Select("select * from role where id not in (select roleId from users_role where userId = #{userId})")
    List<Role> findOtherRoles(String userId) throws Exception;

    //根据id查找用户
    @Select("select * from users where id = #{id}")
    @Results({@Result(id = true,property = "id",column = "id"),
              @Result(property = "email",column = "email"),
              @Result(property = "username",column = "username"),
              @Result(property = "password",column = "password"),
              @Result(property = "phoneNum",column = "phoneNum"),
              @Result(property = "status",column = "status"),
              @Result(property = "roles",column = "id",javaType = java.util.List.class,
              many = @Many(select = "com.wj.myssm.dao.IRoleDao.findRoleByUserId"))})
    UserInfo findById(String id) throws Exception;

    //添加用户信息
    @Insert("Insert into users (id,email,username,password,phoneNum,status) " +
            "Values(#{id},#{email},#{username},#{password},#{phoneNum},#{status})")
    int save(UserInfo userInfo) throws Exception;

    //查询所有用户信息
    @Select("select * from users")
    List<UserInfo> findAll() throws Exception;

    //根据用户名查询用户信息
    @Select("select * from users where username = #{username}")
    @Results({@Result(id=true,property = "id",column = "id"),
            @Result(property = "email" ,column = "email"),
            @Result(property = "username",column = "username"),
            @Result(property = "password",column = "password"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column = "id",
            javaType = java.util.List.class,many = @Many(select = "com.wj.myssm.dao.IRoleDao.findByUserId"))
    })
    public UserInfo findByUsername(String username) throws Exception;
}
