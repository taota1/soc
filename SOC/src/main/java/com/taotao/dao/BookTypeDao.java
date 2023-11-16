package com.taotao.dao;

/**
 * @author taotao11
 * @version 1.0
 */
import com.taotao.pojo.BookType;
import com.taotao.pojo.Params;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BookTypeDao {

    List<BookType> findBySearch(@Param("params") Params params);

    void insertSelective(BookType bookType);

    void updateByPrimaryKeySelective(BookType bookType);

    void deleteByPrimaryKey(Integer id);

    List<BookType> findAll();
}
