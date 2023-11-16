package com.taotao.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.dao.BookTypeDao;
import com.taotao.pojo.BookType;
import com.taotao.pojo.Params;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author taotao11
 * @version 1.0
 */
@Service
public class BookTypeService {

    @Resource
    private BookTypeDao bookTypeDao;

    public PageInfo<BookType> findBySearch(Params params) {
        // 开启分页查询
        PageHelper.startPage(params.getPageNum(), params.getPageSize());
        // 接下来的查询会自动按照当前开启的分页设置来查询
        List<BookType> list = bookTypeDao.findBySearch(params);
        return PageInfo.of(list);
    }


    public void add(BookType bookType) {
        bookTypeDao.insertSelective(bookType);
    }

    public void update(BookType bookType) {
        bookTypeDao.updateByPrimaryKeySelective(bookType);
    }

    public void delete(Integer id) {
        bookTypeDao.deleteByPrimaryKey(id);
    }

    public List<BookType> findAll() {
        return bookTypeDao.findAll();
    }
}
