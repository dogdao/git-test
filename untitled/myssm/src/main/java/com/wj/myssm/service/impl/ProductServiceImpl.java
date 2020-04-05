package com.wj.myssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.wj.myssm.dao.IProductDao;
import com.wj.myssm.entity.Product;
import com.wj.myssm.service.IProductService;
import com.wj.myssm.utils.CreateUUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("iProductService")
public class ProductServiceImpl implements IProductService {
    @Resource
    private IProductDao productDao;

    @Override
    public void save(Product product) {
        //生成UUID
        product.setId(CreateUUIDUtils.createID());
        System.out.println("save()"+product.toString());
        productDao.save(product);
    }

    @Override
    public List<Product> findAll(int page,int size) throws Exception {
        PageHelper.startPage(page,size);
        return productDao.findAll();
    }
}
