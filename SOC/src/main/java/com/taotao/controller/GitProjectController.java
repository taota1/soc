package com.taotao.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.github.pagehelper.PageInfo;
import com.taotao.config.Result;
import com.taotao.exception.CustomException;
import com.taotao.pojo.Book;
import com.taotao.pojo.GitProject;
import com.taotao.pojo.Params;
import com.taotao.service.BookService;
import com.taotao.service.GitProjectService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author taotao11
 * @version 1.0
 */
@CrossOrigin
@RestController
@RequestMapping("/git")
public class GitProjectController {
    @Resource
    private GitProjectService gitProjectService;

    @GetMapping("/search")
    public Result findBySearch(Params params) {
        PageInfo<GitProject> info = gitProjectService.findBySearch(params);
        return Result.success(info);
    }

    @PostMapping("/addgit")
    public Result save(@RequestBody GitProject gitProject) {
        if (gitProject.getId() == null) {
            gitProjectService.add(gitProject);
        } else {
            gitProjectService.update(gitProject);
        }
        return Result.success();
    }

    @DeleteMapping("/delete")
    public Result delete(Integer id) {
        gitProjectService.delete(id);
        return Result.success();
    }


    @GetMapping("/export")
    public Result export(HttpServletResponse response) throws IOException {
        // 思考：
        // 要一行一行的组装数据，塞到一个list里面
        // 每一行数据，其实就对应数据库表中的一行数据，也就是对应Java的一个实体类
        // 我们怎么知道它某一列就是对应某个表头呢？？ 需要映射数据，我们需要一个Map<key,value>，把这个map塞到list里

        // 1. 从数据库中查询出所有数据
        List<GitProject> all = gitProjectService.findAll();

        if (CollectionUtil.isEmpty(all)) {
            throw new CustomException("未找到数据");
        }

        // 2. 定义一个 List，存储处理之后的数据，用于塞到 list 里
        List<Map<String, Object>> list = new ArrayList<>(all.size());

        // 3. 定义Map<key,value> 出来，遍历每一条数据，然后封装到 Map<key,value> 里，把这个 map 塞到 list 里
        for (GitProject gitProject : all) {
            Map<String, Object> row = new HashMap<>();
            row.put("id", gitProject.getId());
            row.put("名称", gitProject.getName());
            row.put("git地址", gitProject.getGiturl());
            row.put("项目简介", gitProject.getDescription());
            row.put("编程语言", gitProject.getLanguage());
            row.put("文档地址", gitProject.getReadme());
            row.put("star数量", gitProject.getStar());
            row.put("开发者", gitProject.getDeveloper());
            row.put("项目类型", gitProject.getType());
            list.add(row);
        }
//        System.out.println(list);

        // 4. 创建一个 ExcelWriter，把 list 数据用这个writer写出来（生成出来）
        ExcelWriter wr = ExcelUtil.getWriter(true);
        wr.write(list, true);

        // 5. 载 excel
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        response.setHeader("Content-Disposition","attachment;filename=giturl.xlsx");
        ServletOutputStream out = response.getOutputStream();
        wr.flush(out, true);
        wr.close();
        IoUtil.close(System.out);

        return Result.success();
    }


    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws IOException {
        List<GitProject> infoList = ExcelUtil.getReader(file.getInputStream()).readAll(GitProject.class);
        if (!CollectionUtil.isEmpty(infoList)) {
            for (GitProject gitProject : infoList) {
                try {
                    gitProjectService.add(gitProject);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return Result.success();

    }

}
