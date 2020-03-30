package com.wj.myssm.controller;

import com.github.pagehelper.PageInfo;
import com.wj.myssm.entity.UserInfo;
import com.wj.myssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UsersControllelr {
    @Autowired
    private IUserService userService;

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
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1") int page,
                                @RequestParam(name = "size",required = true,defaultValue = "4") int size) throws Exception {
        ModelAndView m = new ModelAndView();
        List<UserInfo> users = userService.findAll(page,size);
        PageInfo<UserInfo> userInfo = new PageInfo<UserInfo>(users);
        System.out.println(users.toString());
        m.addObject("userInfo",userInfo);
        m.setViewName("user-list");
        return m;
    }

}
