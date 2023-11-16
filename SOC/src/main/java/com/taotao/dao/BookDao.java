package com.taotao.dao;

/**
 * @author taotao11
 * @version 1.0
 */
import com.taotao.pojo.AdminUser;
import com.taotao.pojo.Book;
import com.taotao.pojo.Params;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BookDao {


    List<Book> findBySearch(@Param("params") Params params);


    void insertSelective(Book book);

    void updateByPrimaryKeySelective(Book book);

    void deleteByPrimaryKey(Integer id);
}
