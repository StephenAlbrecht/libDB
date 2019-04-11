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

import com.libDB.api.entity.Book;
import com.libDB.api.mapper.BookRowMapper;
import com.libDB.util.StringUtils;

@Repository
public class BookDaoImpl implements BookDao {
    
    NamedParameterJdbcTemplate template;

    public BookDaoImpl(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    @Override
    public List<Book> getBooksByOptions(String id, String isbn, String title, String author, String genre) {

        String query = "select * from public.\"Book\"";
        
        boolean addAndToQuery = false;
        if (!StringUtils.IsNullOrWhiteSpace(id)) {
            query += " where \"BookID\" = \'" + id + "\'";
            addAndToQuery = true;
        }
        
        if (!StringUtils.IsNullOrWhiteSpace(isbn)) {
            String constraint = "\"ISBN\" = \'" + isbn + "\'";
            query += (addAndToQuery)
                ? " and " + constraint
                : " where " + constraint;
            addAndToQuery = true;
        }
        
        if (!StringUtils.IsNullOrWhiteSpace(title)) {
            String constraint = "\"Title\" LIKE \'%" + title + "%\'";
            query += (addAndToQuery)
                ? " and " + constraint
                : " where " + constraint;
            addAndToQuery = true;
        }
        
        if (!StringUtils.IsNullOrWhiteSpace(author)) {
            String constraint = "\"Author\" LIKE \'%" + author + "%\'";
            query += (addAndToQuery)
                ? " and " + constraint
                : " where " + constraint;
            addAndToQuery = true;
        }
        
        if (!StringUtils.IsNullOrWhiteSpace(genre)) {
            String constraint = "\"Genre\" LIKE \'%" + genre + "%\'";
            query += (addAndToQuery)
                ? " and " + constraint
                : " where " + constraint;
        }

        return template.query(query, new BookRowMapper());
    }
}

