package com.techelevator.tenmo.model.account;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component

public class JDBCAccountDAO implements AccountDAO {

 private JdbcTemplate theDatabase;
 public JDBCAccountDAO(DataSource theDataSource){
     this.theDatabase = new JdbcTemplate(theDataSource);
 }

    @Override
    public Account viewBalance(long userId) {
     Account theAccount = new Account();
     String sqlViewBalance = "SELECT balance FROM accounts WHERE user_id = ?";
        SqlRowSet results = theDatabase.queryForRowSet(sqlViewBalance, userId);
        while (results.next()){
            theAccount = mapRowToAccount(results);
        }
     return theAccount;
    }




    private Account mapRowToAccount(SqlRowSet row) {
        Account account = new Account();
        account.setBalance(row.getDouble("balance"));
        account.setUserId(row.getLong("user_id"));
        return account;

    }
}
