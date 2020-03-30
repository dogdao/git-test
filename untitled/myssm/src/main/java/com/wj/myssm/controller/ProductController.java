package com.wj.myssm.controller;

import com.github.pagehelper.PageInfo;
import com.wj.myssm.entity.Product;
import com.wj.myssm.service.IProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Resource
    private IProductService productService;

    /** 
     *  
     * @param product	
     * @return java.lang.String
     * @author daodao
     * @date 2020/3/26 22:18
     * 保存添加的商品信息，并更新查询页面
     */
    @RequestMapping("/save.do")
    public String save(Product product){
        productService.save(product);
        return "redirect:findAll.do";
    }
    /**
     *
     * @param
     * @return org.springframework.web.servlet.ModelAndView
     * @author daodao
     * @date 2020/3/26 22:06
     * 查询所有商品信息
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(
            @RequestParam(name = "page",required = true,defaultValue = "1") int page,
            @RequestParam(name = "size",required = true,defaultValue = "2") int size
    ) throws Exception{
        List<Product> list = productService.findAll(page,size);
        PageInfo<Product> pageInfo = new PageInfo<Product>(list);
        ModelAndView m = new ModelAndView();
        m.addObject("pageInfo",pageInfo);
        m.setViewName("product-list");
        return m;
    }
}
