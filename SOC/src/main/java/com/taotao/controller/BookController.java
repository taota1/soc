package com.taotao.controller;

import com.github.pagehelper.PageInfo;
import com.taotao.config.Result;
import com.taotao.pojo.Book;
import com.taotao.pojo.Params;
import com.taotao.service.BookService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author taotao11
 * @version 1.0
 */
@CrossOrigin
@RestController
@RequestMapping("/book")
public class BookController {
    @Resource
    private BookService bookService;

    @GetMapping("/search")
    public Result findBySearch(Params params) {
        PageInfo<Book> info = bookService.findBySearch(params);
        return Result.success(info);
    }

    @PostMapping("/addbook")
    public Result save(@RequestBody Book book) {
        if (book.getId() == null) {
            bookService.add(book);
        } else {
            bookService.update(book);
        }
        return Result.success();
    }

    @DeleteMapping("/delete")
    public Result delete(Integer id) {
        bookService.delete(id);
        return Result.success();
    }
}
