package com.libDB.api.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.libDB.api.entity.Transaction;

public class TransactionRowMapper implements RowMapper<Transaction> {

    @Override
    public Transaction mapRow(ResultSet rs, int arg1) throws SQLException {
        Transaction transaction = new Transaction();
        transaction.setBookTitle(rs.getString("Title"));
        transaction.setBookAuthor(rs.getString("Author"));
        transaction.setTimeOut(rs.getString("TimeOut"));
        transaction.setTimeIn(rs.getString("TimeIn"));
        transaction.setBranchAddressOut(rs.getString("BranchAddressOut"));
        transaction.setBranchAddressIn(rs.getString("BranchAddressIn"));

        return transaction;
    }
}