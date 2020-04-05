package com.wj.myssm.dao;

import com.wj.myssm.entity.Product;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class IProductDaoTest {
    String[] conf ={"conf/applicationContext.xml",
                    "conf/spring-mvc.xml"};
    ApplicationContext ac;

    @Before
    public void init() {
        //初始化
        ac = new ClassPathXmlApplicationContext(conf);
    }

    @Test
    public void testFindAll() throws Exception{
        IProductDao productDao =
                ac.getBean("productDao",IProductDao.class);
        List<Product> list = productDao.findAll();
        for (Product p: list) {
            System.out.println("<<<"+p.toString());
        }
    }
}
