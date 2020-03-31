package com.wj.myssm.dao;

import com.wj.myssm.entity.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IPermissionDao {

    //保存新建资源权限
    @Insert("insert into permission values(#{id},#{permissionName},#{url})")
    void save(Permission permission) throws Exception;

    //查询所有permission
    @Select("select * from permission")
    List<Permission> findAll() throws Exception;
}
