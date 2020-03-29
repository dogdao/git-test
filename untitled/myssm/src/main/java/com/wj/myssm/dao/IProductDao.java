package com.wj.myssm.dao;

import com.wj.myssm.entity.Product;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("productDao")
public interface IProductDao {

    @Select("select * from product where id =#{id}")
    public Product findById(String id);

    //查询所有商品
    public List<Product> findAll() throws Exception;

    //保存商品
    void save(Product product);
}
