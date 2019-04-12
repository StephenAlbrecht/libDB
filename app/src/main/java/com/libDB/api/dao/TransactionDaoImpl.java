package com.libDB.api.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.libDB.api.entity.Transaction;
import com.libDB.api.mapper.TransactionRowMapper;
import com.libDB.util.StringUtils;

@Repository
public class TransactionDaoImpl implements TransactionDao {
    
    NamedParameterJdbcTemplate template;

    public TransactionDaoImpl(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    @Override
    public List<Transaction> getTransactionsByMember(String memberID) {

        String query = "select * from public.\"Transaction\"";
        
        if (!StringUtils.IsNullOrWhiteSpace(memberID)) {
            query += " where \"MemberID\" = \'" + memberID + "\'";
        }

        return template.query(query, new TransactionRowMapper());
    }
}
/* this obviously doesnt work just thinking
@Override
public List<Transaction> getTransactionsByMember(String memberID) {

    String query = "select Title, Author, TimeOut, TimeIn, Address, Address from public.\"Transaction\"";
    
    if (!StringUtils.IsNullOrWhiteSpace(memberid)) {
        query += " where \"MemberID\" = \'" + memberid + "\' natural join public.\"Branch\" natural join public.\"Book\"";
    }

    return template.query(query, new TransactionRowMapper());
}
*/