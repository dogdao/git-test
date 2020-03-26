package com.wj.myssm.service;

import com.wj.myssm.entity.Product;

import java.util.List;

public interface IProductService {
    public void save(Product product);
    public List<Product> findAll() throws Exception;

}
