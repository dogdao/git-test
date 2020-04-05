package com.wj.myssm.controller;

import com.github.pagehelper.PageInfo;
import com.wj.myssm.entity.Orders;
import com.wj.myssm.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    private IOrdersService service;

    @RequestMapping("/findById.do")
    public ModelAndView findById(
            @RequestParam(name = "id",required = true) String id){
        ModelAndView m = new ModelAndView();
        Orders orders = service.findById(id);
        m.addObject("orders",orders);
        m.setViewName("orders-show");
        return m;
    }


    /**
     *
     * @param page
     * @param size
     * @return org.springframework.web.servlet.ModelAndView
     * @author daodao
     * @date 2020/3/28 17:35
     * 分页查询
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name="page",required = true,defaultValue = "1") Integer page,
                                @RequestParam(name = "size",required = true,defaultValue = "4") Integer size) {
        ModelAndView m = new ModelAndView();
        System.out.println(page+"; "+size);
        List<Orders> list = service.findAll(page,size);
        PageInfo<Orders> pageInfo = new PageInfo<Orders>(list);
        m.addObject("pageInfo",pageInfo);
        m.setViewName("orders-list");
        return m;
    }

    /*未分页查询全部
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(){
        ModelAndView m = new ModelAndView();
        List<Orders> list = service.findAll();
        m.addObject("ordersList",list);
        m.setViewName("orders-list");
        return m;
    }
     */
}
