package com.wj.myssm.service.impl;

import com.wj.myssm.dao.IRoleDao;
import com.wj.myssm.entity.Permission;
import com.wj.myssm.entity.Role;
import com.wj.myssm.service.IRoleService;
import com.wj.myssm.utils.CreateUUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("roleService")

public class RoleServiceImpl implements IRoleService {
    @Autowired
    private IRoleDao roleDao;

    @Override
    public void addPermissionToRole(String roleId, String[] permissionIds) throws Exception {
        for(String permissionId:permissionIds) {
            System.out.println("<<<roleId:"+roleId+", permissionId"+permissionId);
            roleDao.addPermissionToRole(roleId,permissionId);
        }
    }

    @Override
    public List<Permission> findOtherPermissions(String roleId) throws Exception {
        return roleDao.findOtherPermissions(roleId);
    }

    @Override
    public Role findById(String roleId) throws Exception {
        return roleDao.findById(roleId);
    }

    @Override
    public void deleteRoleById(String roleId) throws Exception {
        roleDao.deleteRole_PermissionByRoleId(roleId);
        roleDao.deleteUsers_RoleByRoleId(roleId);
        roleDao.deleteRoleById(roleId);
    }

    @Override
    public void save(Role role) throws Exception {
        role.setId(CreateUUIDUtils.createID());
        roleDao.save(role);
    }

    @Override
    public List<Role> findAll() throws Exception {
        return roleDao.findAll();
    }
}
