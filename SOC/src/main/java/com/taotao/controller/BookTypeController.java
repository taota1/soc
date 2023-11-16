package com.taotao.controller;

import com.github.pagehelper.PageInfo;
import com.taotao.config.Result;
import com.taotao.pojo.Book;
import com.taotao.pojo.BookType;
import com.taotao.pojo.Params;
import com.taotao.service.BookService;
import com.taotao.service.BookTypeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author taotao11
 * @version 1.0
 */
@CrossOrigin
@RestController
@RequestMapping("/booktype")
public class BookTypeController {
    @Resource
    private BookTypeService bookTypeService;

    @PostMapping("/add")
    public Result save(@RequestBody BookType bookType) {
        if (bookType.getId() == null) {
            bookTypeService.add(bookType);
        } else {
            bookTypeService.update(bookType);
        }
        return Result.success();
    }

    //分页查询
    @GetMapping("/search")
    public Result findBySearch(Params params) {
        PageInfo<BookType> info = bookTypeService.findBySearch(params);
        return Result.success(info);
    }

    @GetMapping("/findall")
    public Result findAll() {
        List<BookType> all = bookTypeService.findAll();
        return Result.success(all);
    }


    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        bookTypeService.delete(id);
        return Result.success();
    }

    @PutMapping("/delBatch")
    public Result delBatch(@RequestBody List<BookType> list) {
        for (BookType bookType : list) {
            bookTypeService.delete(bookType.getId());
        }
        return Result.success();
    }
}
