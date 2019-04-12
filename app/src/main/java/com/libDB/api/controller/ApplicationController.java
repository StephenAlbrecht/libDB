package com.libDB.api.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.libDB.api.entity.Book;
import com.libDB.api.service.BookService;
import com.libDB.api.entity.Transaction;
import com.libDB.api.service.TransactionService;
import com.libDB.api.service.LoginService;


@RestController
@RequestMapping("/api")
class ApplicationController {

    @Resource
    BookService bookService;

    @GetMapping(value = "/getBooks")
    public List<Book> getBooks(
            @RequestParam(name="id", required=false, defaultValue="") String id,
            @RequestParam(name="isbn", required=false, defaultValue="") String isbn,
            @RequestParam(name="title", required=false, defaultValue="") String title,
            @RequestParam(name="author", required=false, defaultValue="") String author,
            @RequestParam(name="genre", required=false, defaultValue="") String genre) 
    {
        return bookService.getBooksByOptions(id, isbn, title, author, genre);
    }

    @Resource
    TransactionService transactionService;

    @GetMapping(value = "/getTransactions")
    public List<Transaction> getTransactions(
            @RequestParam(name="memberID", required=true, defaultValue="") String memberID)
    {
        return transactionService.getTransactionsByMember(memberID);
    }
    
    @Resource
    LoginService loginService;

    @PostMapping(value = "/memberLogin")
    public boolean memberLogin(
        @RequestParam(name="username", required=true, defaultValue="") String id,
        @RequestParam(name="password", required=true, defaultValue="") String password)
    {
        return loginService.validateMember(id, password);
    }
    
    @PostMapping(value = "/employeeLogin")
    public boolean employeeLogin(
        @RequestParam(name="username", required=true, defaultValue="") String id,
        @RequestParam(name="password", required=true, defaultValue="") String password)
    {
        return loginService.validateEmployee(id, password);
    }
}