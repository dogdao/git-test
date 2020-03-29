package com.wj.myssm.dao;

import com.wj.myssm.entity.Member;
import com.wj.myssm.entity.Orders;
import com.wj.myssm.entity.Product;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("iOrdersDao")
public interface IOrdersDao {

    //根据ID查询订单详情
    @Select("select * from orders where id=#{ordersId}")
    @Results({
            @Result(id=true,property = "id",column = "id"),
            @Result(property = "orderNum",column = "orderNum"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "orderStatus",column = "orderStatus"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "payType",column = "payType"),
            @Result(property = "orderDesc",column = "orderDesc"),
            @Result(property = "product",column = "productId",
                    javaType = Product.class,one=@One(select = "com.wj.myssm.dao.IProductDao.findById")),
            @Result(property = "member",column = "memberId",
                    javaType = Member.class,one=@One(select = "com.wj.myssm.dao.IMemberDao.findById")),
            @Result(property = "travellers",column = "id",
                    javaType = java.util.List.class,many = @Many(select = "com.wj.myssm.dao.ITravellerDao.findByOrdersId")),
    })
    Orders findById(String orderId);

    //查询所有订单
    @Select("select * from orders")
    @Results({
        @Result(id=true,property = "id",column = "id"),
            @Result(property = "orderNum",column = "orderNum"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "orderStatus",column = "orderStatus"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "payType",column = "payType"),
            @Result(property = "orderDesc",column = "orderDesc"),
            @Result(property = "product",column = "productId",
            javaType = Product.class,one=@One(select = "com.wj.myssm.dao.IProductDao.findById")),
    })
    List<Orders> findAll();
}
