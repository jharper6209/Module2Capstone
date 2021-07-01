package com.techelevator.tenmo.model.account;

import org.springframework.security.core.userdetails.User;

public interface AccountDAO {

    public Account viewBalance(long userId);



}
