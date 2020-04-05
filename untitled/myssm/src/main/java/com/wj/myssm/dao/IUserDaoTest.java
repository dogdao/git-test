package com.wj.myssm.dao;


import com.wj.myssm.entity.UserInfo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

@ContextConfiguration(locations = { "classpath*:conf/applicationContext.xml"})
@WebAppConfiguration("src/main/resources")
public class IUserDaoTest {
    String[] conf ={"conf/applicationContext.xml"};
    ApplicationContext ac;

    @Before
    public void init() {
        //初始化
        ac = new ClassPathXmlApplicationContext(conf);
    }
    @Test
    public void testIUserDao() throws Exception {
        UserInfo user = new UserInfo();
        IUserDao userDao = ac.getBean("userDao",IUserDao.class);
        user = userDao.findByUsername("wj");
        System.out.println("<<<<<"+user.getRoles().get(0).toString());

    }

}
