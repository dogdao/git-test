package com.wj.myssm.controller;

import com.wj.myssm.dao.IPermissionDao;
import com.wj.myssm.entity.Permission;
import com.wj.myssm.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private IPermissionService permissionService;

    @RequestMapping("/save.do")
    public String save(Permission permission) throws Exception {
        permissionService.save(permission);
        return "redirect:findAll.do";
    }

    /**
     *
     * @param
     * @return org.springframework.web.servlet.ModelAndView
     * @author daodao
     * @date 2020/3/31 10:36
     * 查询所有资源权限
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        ModelAndView m = new ModelAndView();
        List<Permission> permissions = permissionService.findAll();
        m.addObject("permissionList",permissions);
        m.setViewName("permission-list");
        return m;
    }




}
