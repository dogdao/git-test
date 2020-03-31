package com.wj.myssm.controller;

import com.wj.myssm.entity.Role;
import com.wj.myssm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private IRoleService roleService;

    /**
     *
     * @param roleId
     * @return java.lang.String
     * @author daodao
     * @date 2020/3/31 11:33
     * 删除角色信息
     */
    @RequestMapping("/deleteRole.do")
    public String deleteRoleById(
            @RequestParam(name = "roleId",required = true) String roleId) throws Exception {
        roleService.deleteRoleById(roleId);
        return "redirect:findAll.do";
    }


     /**
      *
      * @param role
      * @return java.lang.String
      * @author daodao
      * @date 2020/3/30 21:13
      * 新建角色
      */
    @RequestMapping("/save.do")
    public String save(Role role) throws Exception {
        roleService.save(role);
        return "redirect:findAll.do";
    }

    /**
     *
     * @param
     * @return org.springframework.web.servlet.ModelAndView
     * @author daodao
     * @date 2020/3/30 20:44
     * 查询所有角色信息
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        ModelAndView m = new ModelAndView();
        List<Role> roles = roleService.findAll();
        m.addObject("roleList",roles);
        m.setViewName("role-list");
        return m;
    }

}
