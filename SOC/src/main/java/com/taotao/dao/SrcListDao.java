package com.taotao.dao;

import com.taotao.pojo.AdminUser;
import com.taotao.pojo.Params;
import com.taotao.pojo.SrcList;
import com.taotao.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author taotao11
 * @version 1.0
 */
@Mapper
@Repository
public interface SrcListDao {
    SrcList querySrcListById(Integer id);

    SrcList querySrcListByDomain(String domain);

    SrcList querySrcList(SrcList srcList);


    List<SrcList> findBySearch(@Param("params") Params params);

    int addSrc(SrcList srcList);

    int updateSrcList(SrcList srcList);

    int deleteSRC(Integer id);
}
