package com.taotao.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.dao.BookDao;
import com.taotao.dao.GitProjectDao;
import com.taotao.pojo.Book;
import com.taotao.pojo.GitProject;
import com.taotao.pojo.Params;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author taotao11
 * @version 1.0
 */
@Service
public class GitProjectService {

    @Resource
    private GitProjectDao gitProjectDao;


    public PageInfo<GitProject> findBySearch(Params params) {
        // 开启分页查询
        PageHelper.startPage(params.getPageNum(), params.getPageSize());
        // 接下来的查询会自动按照当前开启的分页设置来查询
        List<GitProject> list = gitProjectDao.findBySearch(params);
        return PageInfo.of(list);
    }

    public void add(GitProject gitProject) {
        gitProjectDao.insertSelective(gitProject);
    }

    public void update(GitProject gitProject) {
        gitProjectDao.updateByPrimaryKeySelective(gitProject);
    }

    public void delete(Integer id) {
        gitProjectDao.deleteByPrimaryKey(id);
    }

    public List<GitProject> findAll() {
        return gitProjectDao.findAll();
    }
}
