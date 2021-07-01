package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.model.account.Account;
import com.techelevator.tenmo.model.account.AccountDAO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/*******************************************************************************************************
 * This is where you code any API controllers you may create
 *
 * Feel free to create additional controller classs if you would
 * like to have separate controller classses base on functionality or use
********************************************************************************************************/

@RestController
public class ApiController {
AccountDAO theAcctData;

public ApiController(AccountDAO theAccounts){
    theAcctData = theAccounts;
}
@PreAuthorize("isAuthenticated()")
@RequestMapping(path="account/{id}", method = RequestMethod.GET)
public Account getBalance(@PathVariable long id) {

    return theAcctData.viewBalance(id);
}

}

