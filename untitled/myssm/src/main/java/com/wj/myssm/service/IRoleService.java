package com.wj.myssm.service;

import com.wj.myssm.entity.Role;

import java.util.List;

public interface IRoleService {

    void save(Role role) throws Exception;
    List<Role> findAll() throws Exception;
}
