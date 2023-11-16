package com.taotao.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.dao.SrcListDao;
import com.taotao.exception.CustomException;
import com.taotao.pojo.AdminUser;
import com.taotao.pojo.Params;
import com.taotao.pojo.SrcList;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author taotao11
 * @version 1.0
 */
@Service
public class SrcListService {
    @Resource
    protected SrcListDao srcListDao;

    public PageInfo<SrcList> findBySearch(Params params) {
        // 开启分页查询
        PageHelper.startPage(params.getPageNum(), params.getPageSize());
        // 接下来的查询会自动按照当前开启的分页设置来查询
        List<SrcList> list = srcListDao.findBySearch(params);
        return PageInfo.of(list);
    }



    public SrcList querySrcListById(int id) {
        return srcListDao.querySrcListById(id);
    }

    public void addSrcList(SrcList srcList) {
        if (srcList.getDomain() == null || "".equals(srcList.getDomain())) {
            throw new CustomException("domain不能为空");
        }
        SrcList srcList1 = srcListDao.querySrcListByDomain(srcList.getDomain());

        if (srcList1!=null) {
            throw new CustomException("domain已存在");
        }
        srcListDao.addSrc(srcList);
    }


    public void updateSrcList(SrcList srcList) {
        if (srcList.getDomain() == null || "".equals(srcList.getDomain())) {
            throw new CustomException("domain不能为空");
        }

//        SrcList srcList1 = srcListDao.querySrcListByDomain(srcList.getDomain());
        SrcList srcList1 = srcListDao.querySrcList(srcList);

        if (srcList1!=null) {
            throw new CustomException("domain已存在");
        }
        srcListDao.updateSrcList(srcList);

    }

    public void deleteSRC(Integer id) {
        srcListDao.deleteSRC(id);
    }
}
