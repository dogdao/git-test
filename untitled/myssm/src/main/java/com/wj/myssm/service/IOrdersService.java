package com.wj.myssm.service;

import com.wj.myssm.entity.Orders;

import java.util.List;

public interface IOrdersService {

    Orders findById(String ordersId);

    List<Orders> findAll(int page,int size);
}
