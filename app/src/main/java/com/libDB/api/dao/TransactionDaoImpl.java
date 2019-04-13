package com.libDB.api.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ArrayList;
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
        List<Transaction> transactions = new ArrayList<Transaction>();

        if (!StringUtils.IsNullOrWhiteSpace(memberID)) {
            String query = "select \"Title\", \"Author\", \"TimeOut\", \"TimeIn\", "
                    + "\"BranchAddressOut\", \"BranchAddressIn\" "
                    + "from " 
                    +     "(select \"Title\", \"Author\" "
                    +     "from \"Book\" natural join \"Transaction\" "
                    +     "where \"MemberID\" = \'" + memberID + "\') "
                    +     "as \"TA\", "
                    +     "(select \"Address\" as \"BranchAddressOut\" "
                    +     "from \"Branch\" natural join \"Transaction\" "
                    +     "where \"BranchOut\" = \"BranchID\" "
                    +     "and \"MemberID\" = \'" + memberID + "\') "
                    +     "as \"BO\", "
                    +     "(select \"Address\" as \"BranchAddressIn\" "
                    +     "from \"Branch\" natural join \"Transaction\" "
                    +     "where \"BranchIn\" = \"BranchID\" "
                    +     "and \"MemberID\" = \'" + memberID + "\') "
                    +     "as \"BA\", "
                    +     "\"Transaction\" "
                    + "where \"MemberID\" = \'" + memberID + "\'";
            transactions = template.query(query, new TransactionRowMapper());
        }

        return transactions;
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