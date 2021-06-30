package com.techelevator.tenmo.model.account;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component

public class JDBCAccountDAO implements AccountDAO {

 private JdbcTemplate theDatabase;
 public JDBCAccountDAO(DataSource theDataSource){
     this.theDatabase = new JdbcTemplate(theDataSource);
 }

    @Override
    public double viewBalance(long accountId) {
     double currentBalance = 0;
     String sqlViewBalance = "SELECT balance FROM accounts WHERE account_id = ?";
        SqlRowSet results = theDatabase.queryForRowSet(sqlViewBalance, accountId);
        while (results.next()){
            currentBalance = results.getLong("balance");
        }
     return currentBalance;
    }
}
