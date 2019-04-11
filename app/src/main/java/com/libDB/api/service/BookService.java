package com.libDB.api.service;

import java.util.List;

import com.libDB.api.entity.Book;

public interface BookService {

    List<Book> getBooksByOptions(Book book);
}