package com.techelevator.tenmo.model.account;

import org.springframework.security.core.userdetails.User;

public interface AccountDAO {

    public Double viewBalance(long accountId);

    public long getAccountId(User user);

}
