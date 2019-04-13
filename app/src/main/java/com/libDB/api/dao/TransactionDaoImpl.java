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

import com.libDB.api.entity.TransactionView;
import com.libDB.api.mapper.TransactionViewRowMapper;
import com.libDB.util.StringUtils;

@Repository
public class TransactionDaoImpl implements TransactionDao {
    
    NamedParameterJdbcTemplate template;

    public TransactionDaoImpl(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    @Override
    public List<TransactionView> getTransactionsByMember(String memberID) {
        List<TransactionView> transactions = new ArrayList<TransactionView>();

        if (!StringUtils.IsNullOrWhiteSpace(memberID)) {
            String query = "select DISTINCT \"Title\", \"Author\", \"TA\".\"TimeOut\", \"TA\".\"TimeIn\", \"BranchAddressOut\", \"BranchAddressIn\""
                + "from "
                + "    (select \"Title\", \"Author\", \"BranchIn\", \"BranchOut\", \"TimeOut\", \"TimeIn\""
                + "    from \"Book\" natural join \"Transaction\""
                + "    where \"MemberID\" = \'" + memberID + "\') "
                + "    as \"TA\", "
                + "    (select \"BranchID\" as \"BOID\", \"TimeOut\" as \"BOTO\", \"Address\" as \"BranchAddressOut\""
                + "    from \"Branch\" natural join \"Transaction\""
                + "    where \"BranchOut\" = \"BranchID\""
                + "    and \"MemberID\" = \'" + memberID + "\')"
                + "    as \"BO\","
                + "    (select \"BranchID\" as \"BIID\", \"TimeIn\" as \"BITI\", \"Address\" as \"BranchAddressIn\""
                + "    from \"Branch\" natural join \"Transaction\""
                + "    where (\"BranchIn\" = \"BranchID\" or \"BranchIn\" is NULL)"
                + "    and \"MemberID\" = \'" + memberID + "\')"
                + "    as \"BI\","
                + "    \"Transaction\""
                + "where \"MemberID\" = \'" + memberID + "\' and \"BIID\" = \"TA\".\"BranchIn\" and \"BOID\" = \"TA\".\"BranchOut\" and \"BITI\" = \"TA\".\"TimeIn\" and \"BOTO\" = \"TA\".\"TimeOut\""
                + "order by \"TA\".\"TimeOut\"  desc";
            transactions = template.query(query, new TransactionViewRowMapper());
        }

        return transactions;
    }
}