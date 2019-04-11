package com.libDB.api.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.libDB.api.dao.BookDao;
import com.libDB.api.entity.Book;

@Component
public class BookServiceImpl implements BookService {
    
    @Resource
    BookDao bookDao;

    @Override
    public List<Book> getBooksByOptions(Book book) {
        return bookDao.getBooksByOptions(book);
    }
}