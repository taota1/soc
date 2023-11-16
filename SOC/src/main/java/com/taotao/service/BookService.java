package com.taotao.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.dao.BookDao;
import com.taotao.pojo.Book;
import com.taotao.pojo.Params;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author taotao11
 * @version 1.0
 */
@Service
public class BookService {

    @Resource
    private BookDao bookDao;


    public PageInfo<Book> findBySearch(Params params) {
        // 开启分页查询
        PageHelper.startPage(params.getPageNum(), params.getPageSize());
        // 接下来的查询会自动按照当前开启的分页设置来查询
        List<Book> list = bookDao.findBySearch(params);
        return PageInfo.of(list);
    }

    public void add(Book book) {
        bookDao.insertSelective(book);
    }

    public void update(Book book) {
        bookDao.updateByPrimaryKeySelective(book);
    }

    public void delete(Integer id) {
        bookDao.deleteByPrimaryKey(id);
    }

}
