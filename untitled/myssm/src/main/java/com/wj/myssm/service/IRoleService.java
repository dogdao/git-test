package com.wj.myssm.service;

import com.wj.myssm.entity.Permission;
import com.wj.myssm.entity.Role;

import java.util.List;

public interface IRoleService {

    void addPermissionToRole(String roleId, String[] permissionId) throws Exception;
    List<Permission> findOtherPermissions(String roleId) throws Exception;
    Role findById(String roleId) throws Exception;
    void deleteRoleById(String roleId) throws Exception;
    void save(Role role) throws Exception;
    List<Role> findAll() throws Exception;
}
