package com.wj.myssm.dao;

import com.wj.myssm.entity.Syslog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISyslogDao {

    //查询所有日志
    @Select("select * from syslog")
    List<Syslog> findAll() throws Exception;

    //保存日志
    @Insert("insert into syslog (id,visitTime,username,ip,url,executTime,method)" +
            "values (#{id},#{visitTime},#{username},#{ip},#{url},#{executTime},#{method})")
    void save(Syslog syslog) throws Exception;
}
