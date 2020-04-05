package com.wj.myssm.controller;

import com.github.pagehelper.PageInfo;
import com.wj.myssm.entity.Role;
import com.wj.myssm.entity.UserInfo;
import com.wj.myssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UsersControllelr {
    @Autowired
    private IUserService userService;

    //为用户添加角色
    @RequestMapping("/addRoleToUser.do")
    public String addRoleToUser(
            @RequestParam(name = "userId", required = true) String userId,
            @RequestParam(name = "ids", required = true) String[] roleId) throws Exception {
        userService.addRoleToUser(userId,roleId);
        return "redirect:findAll.do";
    }

    //找到用户的角色并找到用户可以添加的角色
    @RequestMapping("/findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(String id) throws Exception {
        ModelAndView m = new ModelAndView();
        //1.查找用户
        UserInfo userInfo = userService.findById(id);
        //根据用户信息可以查询用户可以额外添加的角色
        List<Role> roles = userService.findOtherRoles(id);
        m.addObject("user",userInfo);
        m.addObject("roleList",roles);
        m.setViewName("user-role-add");
        return m;
    }

    //显示用户详情
    @RequestMapping("/findById.do")
    public ModelAndView findById(String id) throws Exception {
        ModelAndView m = new ModelAndView();
        UserInfo userInfo = userService.findById(id);
        m.addObject("user",userInfo);
        m.setViewName("user-show");
        return m;
    }


    //添加新用户
    @RequestMapping("/save.do")
    public String save(UserInfo userInfo) throws Exception {
        userService.save(userInfo);
        return "redirect:findAll.do";
    }

    //分页查询所有用户
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1") Integer page,
                                @RequestParam(name = "size",required = true,defaultValue = "4") Integer size) throws Exception {
        ModelAndView m = new ModelAndView();
        List<UserInfo> users = userService.findAll(page,size);
        PageInfo<UserInfo> userInfo = new PageInfo<UserInfo>(users);
        System.out.println(users.toString());
        m.addObject("userInfo",userInfo);
        m.setViewName("user-list");
        return m;
    }
}
