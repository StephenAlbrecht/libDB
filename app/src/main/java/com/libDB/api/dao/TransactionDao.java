package com.libDB.api.dao;

import java.util.List;

import com.libDB.api.entity.Transaction;

public interface TransactionDao {
    
    List<Transaction> getTransactionsByMember(String memberID);
}