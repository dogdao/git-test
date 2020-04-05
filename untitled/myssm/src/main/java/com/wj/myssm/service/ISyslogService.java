package com.wj.myssm.service;

import com.wj.myssm.entity.Syslog;

import java.util.List;

public interface ISyslogService {

    List<Syslog> findAll() throws Exception;
    void save(Syslog syslog) throws Exception;
}

