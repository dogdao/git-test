package com.wj.myssm.service.impl;

import com.wj.myssm.dao.ISyslogDao;
import com.wj.myssm.entity.Syslog;
import com.wj.myssm.service.ISyslogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SyslogServiceImpl implements ISyslogService {
    @Autowired
    private ISyslogDao syslogDao;

    @Override
    public List<Syslog> findAll() throws Exception {
        return syslogDao.findAll();
    }

    @Override
    public void save(Syslog syslog) throws Exception {
        syslogDao.save(syslog);
    }
}
