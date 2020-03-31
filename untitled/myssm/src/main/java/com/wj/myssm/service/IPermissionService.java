package com.wj.myssm.service;

import com.wj.myssm.entity.Permission;

import java.util.List;

public interface IPermissionService {

    void save(Permission permission) throws Exception;
    List<Permission> findAll() throws Exception;
}
