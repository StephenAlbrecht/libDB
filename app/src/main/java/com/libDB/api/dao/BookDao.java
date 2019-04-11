package com.libDB.api.dao;

import java.util.List;

import com.libDB.api.entity.Book;

public interface BookDao {
    
    List<Book> getBooksByOptions(Book book);
}