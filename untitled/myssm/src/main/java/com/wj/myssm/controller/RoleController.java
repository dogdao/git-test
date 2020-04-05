package com.wj.myssm.controller;

import com.wj.myssm.entity.Permission;
import com.wj.myssm.entity.Role;
import com.wj.myssm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
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

    @RequestMapping("/addPermissionToRole.do")
    public String addPermissionToRole(@RequestParam(name = "roleId",required = true) String roleId,
                                      @RequestParam(name = "ids", required = true) String[] permissionIds) throws Exception {
        System.out.println("<<<roleId:"+roleId);
        roleService.addPermissionToRole(roleId,permissionIds);
        return "redirect:findAll.do";
    }

    @RequestMapping("/findRoleByIdAndAllPermission.do")
    public ModelAndView findRoleByIdAndAllPermission(
            @RequestParam(name = "id",required = true) String roleId) throws Exception {
        ModelAndView m = new ModelAndView();
        //1.根据roleId查找role
        Role role = roleService.findById(roleId);
        //2.查找可以添加的权限
        List<Permission> permissions = roleService.findOtherPermissions(roleId);
        m.addObject("role",role);
        m.addObject("permissionList",permissions);
        m.setViewName("role-permission-add");
        return m;
    }

    //显示角色信息
    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id",required = true) String id) throws Exception {
        ModelAndView m = new ModelAndView();
        Role role = roleService.findById(id);
        m.addObject("role",role);
        m.setViewName("role-show");
        return m;
    }

    /**
     *
     * @param id
     * @return java.lang.String
     * @author daodao
     * @date 2020/3/31 11:33
     * 删除角色信息
     */
    @RequestMapping("/deleteRoleById.do")
    public String deleteRoleById(
            @RequestParam(name = "id",required = true) String id) throws Exception {
        roleService.deleteRoleById(id);
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
    @Secured("ROLE_ADMIN")
    public ModelAndView findAll() throws Exception {
        ModelAndView m = new ModelAndView();
        List<Role> roles = roleService.findAll();
        m.addObject("roleList",roles);
        m.setViewName("role-list");
        return m;
    }

}
