package com.wj.myssm.service;

import com.wj.myssm.entity.Role;
import com.wj.myssm.entity.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {
    void addRoleToUser(String userId,String[] roleId) throws Exception;
    List<Role> findOtherRoles(String userId) throws Exception;
    UserInfo findById(String id) throws Exception;
    List<UserInfo> findAll(int page,int size) throws Exception;
    int save(UserInfo userInfo) throws Exception;
}
