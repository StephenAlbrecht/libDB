package com.libDB.api.service;

import java.util.List;

import com.libDB.api.entity.Transaction;

public interface TransactionService {

    List<Transaction> getTransactionsByMember(String memberID);
}