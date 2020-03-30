package com.wj.myssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.wj.myssm.dao.IUserDao;
import com.wj.myssm.entity.Role;
import com.wj.myssm.entity.UserInfo;
import com.wj.myssm.service.IUserService;
import com.wj.myssm.utils.CreateUUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserDao userDao;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = null;
        try {
            userInfo = userDao.findByUsername(username);
        }catch (Exception e){
            e.printStackTrace();
        }
        //处理自己的用户对象封装成UserDetails
        User user = new User(userInfo.getUsername(),"{noop}"+userInfo.getPassword(),userInfo.getStatus()==0?false:true,true,true,true,getAuthorities(userInfo.getRoles()));
        return user;
    }

    private List<SimpleGrantedAuthority> getAuthorities(List<Role> roles){
        List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
        for(Role role : roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        }
        return authorities;
    }

    @Override
    public UserInfo findById(String id) throws Exception {
        UserInfo userInfo = userDao.findById(id);
        System.out.println("<<<"+userInfo.getRoles().get(0).toString());
        return userInfo;
    }

    /**
     *
     * @param page:当前页
     * @param size:每页行数
     * @return java.util.List<com.wj.myssm.entity.UserInfo>
     * @author daodao
     * @date 2020/3/30 21:42
     */
    @Override
    public List<UserInfo> findAll(int page,int size) throws Exception {
        PageHelper.startPage(page,size);
        return userDao.findAll();
    }

    @Override
    public int save(UserInfo userInfo) throws Exception {
        //创建一个UUID
        userInfo.setId(CreateUUIDUtils.createID());
        //给密码加密
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        return userDao.save(userInfo);
    }
}
