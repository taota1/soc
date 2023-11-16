package com.taotao.controller;

import com.github.pagehelper.PageInfo;
import com.taotao.config.Result;
import com.taotao.pojo.AdminUser;
import com.taotao.pojo.Params;
import com.taotao.pojo.SrcList;
import com.taotao.service.AdminUserService;
import com.taotao.service.SrcListService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import java.util.List;

import static java.awt.SystemColor.info;

/**
 * @author taotao11
 * @version 1.0
 */
@CrossOrigin
@RestController
@RequestMapping("/srcList")
public class SrcListController {

    @Resource
    private SrcListService srcListService;

    @GetMapping("/health")
    public String start() {
        return "up up ";
    }


    @GetMapping("/querySrcListById")
    public Result querySrcListById(int id){
        SrcList srcList = srcListService.querySrcListById(id);
        return Result.success(srcList);
    }


    @GetMapping("/search")
    public Result findBySearchPageSize(Params params) {
        PageInfo<SrcList> info = srcListService.findBySearch(params);
        System.out.println(info);
        return Result.success(info);
    }



    @PostMapping("/addOrUpdateSrcList")
    public Result addSrcList(@RequestBody SrcList srcList ) {
//        srcListService.addSrcList(srcList);
//        return Result.success();

        if (srcList.getId()==null) {
            srcListService.addSrcList(srcList);
        } else {
            srcListService.updateSrcList(srcList);
        }
        return Result.success();
    }

    @GetMapping("/deleteSRC")
    public Result deleteSRC(Integer id){
        srcListService.deleteSRC(id);
        return Result.success();
    }





}
