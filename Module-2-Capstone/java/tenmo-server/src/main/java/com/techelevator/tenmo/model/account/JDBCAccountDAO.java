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
    public Double viewBalance(long accountId) {
     double currentBalance = 0;
     String sqlViewBalance = "SELECT balance FROM accounts WHERE account_id = ?";
        SqlRowSet results = theDatabase.queryForRowSet(sqlViewBalance, accountId);
        while (results.next()){
            currentBalance = results.getDouble("balance");
        }
     return currentBalance;
    }

    public long getAccountId(User user) {
     long accountId = 0;
     String sqlQuery = "SELECT account_id FROM accounts WHERE user_id = ?";
     SqlRowSet results = theDatabase.queryForRowSet(sqlQuery, user.getId());
     while (results.next()) {
         accountId = results.getLong("account_id");
     }
     return accountId;
    }




    private Account mapRowToAccount(SqlRowSet row) {
        Account account = new Account();
        account.setBalance(row.getDouble("balance"));
        account.setUserId(row.getLong("user_id"));
        return account;

    }
}
