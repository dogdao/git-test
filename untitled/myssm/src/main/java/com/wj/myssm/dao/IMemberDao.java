package com.wj.myssm.dao;

import com.wj.myssm.entity.Member;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository("memberDao")
public interface IMemberDao {
    @Select("select * from member where id = #{id}")
    Member findById(String id);
}
