package com.libDB.api.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.libDB.api.entity.Book;
import com.libDB.api.service.BookService;

@RestController
@RequestMapping("/api")
class ApplicationController {

    @Resource
    BookService bookService;

    @GetMapping(value = "/getBooks")
    public List<Book> getBooks(Book book) {
        return bookService.getBooksByOptions(book);
    }
}