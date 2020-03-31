package com.wj.myssm.service.impl;

import com.wj.myssm.dao.IPermissionDao;
import com.wj.myssm.entity.Permission;
import com.wj.myssm.service.IPermissionService;
import com.wj.myssm.utils.CreateUUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("permissionService")
public class PermissionServiceImpl implements IPermissionService {
    @Autowired
    private IPermissionDao permissionDao;

    @Override
    public void save(Permission permission) throws Exception {
        permission.setId(CreateUUIDUtils.createID());
        permissionDao.save(permission);
    }

    @Override
    public List<Permission> findAll() throws Exception {
        return permissionDao.findAll();
    }
}
