package com.wj.myssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.wj.myssm.dao.IOrdersDao;
import com.wj.myssm.entity.Orders;
import com.wj.myssm.service.IOrdersService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class IOrdersServiceImpl implements IOrdersService {
    @Resource
    private IOrdersDao iOrdersDao;

    @Override
    public Orders findById(String ordersId) {
        return iOrdersDao.findById(ordersId);
    }

    @Override
    public List<Orders> findAll(int page,int size) {
        PageHelper.startPage(page,size);
        List<Orders> list = iOrdersDao.findAll();
        return list;
    }
}
