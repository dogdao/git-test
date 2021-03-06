package com.wj.myssm.dao;

import com.wj.myssm.entity.Permission;
import com.wj.myssm.entity.Role;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IRoleDao {

    //添加新的资源权限
    @Insert("insert into role_permission (roleId,permissionId) values (#{roleId},#{permissionId})")
    void addPermissionToRole(@Param("roleId") String roleId, @Param("permissionId") String permissionId) throws Exception;

    //查询剩余权限
    @Select("select * from permission where id not in(select permissionId from role_permission where roleId = #{roleId})")
    List<Permission> findOtherPermissions(String roleId) throws Exception;

    //根据id查找角色
    @Select("select * from role where id = #{roleId}")
    Role findById(String roleId) throws Exception;

    //删除role_permission表中的数据
    @Delete("delete from role_permission where roleId = #{roleId}")
    void deleteRole_PermissionByRoleId(String roleId) throws Exception;

    //删除user_role表中的数据
    @Delete("delete from users_role where roleId = #{roleId}")
    void deleteUsers_RoleByRoleId(String roleId) throws Exception;

    //删除role表中的角色
    @Delete("delete from role where id = #{roleId}")
    void deleteRoleById(String roleId) throws Exception;

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
