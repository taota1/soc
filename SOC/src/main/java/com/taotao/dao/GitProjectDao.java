package com.taotao.dao;

/**
 * @author taotao11
 * @version 1.0
 */
import com.taotao.pojo.Book;
import com.taotao.pojo.GitProject;
import com.taotao.pojo.Params;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface GitProjectDao {

    List<GitProject> findBySearch(@Param("params") Params params);

    void insertSelective(GitProject gitProject);

    void updateByPrimaryKeySelective(GitProject gitProject);

    void deleteByPrimaryKey(Integer id);

    List<GitProject> findAll();
}
