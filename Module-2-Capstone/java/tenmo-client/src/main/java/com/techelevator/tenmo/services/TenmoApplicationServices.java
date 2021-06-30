package com.techelevator.tenmo.services;

import com.techelevator.tenmo.models.Account.Account;
import com.techelevator.view.ConsoleService;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

/*******************************************************************************************************
 * This is where you code Application Services required by your solution
 * 
 * Remember:  theApp ==> ApplicationServices  ==>  Controller  ==>  DAO
********************************************************************************************************/

public class TenmoApplicationServices {

    public static final String API_BASE_URL = "http://localhost:8080/";

    public RestTemplate apiCall = new RestTemplate();
    private ConsoleService console;

    public Double viewBalance(long id) {
        Account usersAccount = new Account();
        try {
         usersAccount = apiCall.getForObject(API_BASE_URL + "account/" + id, Account.class);
        } catch (RestClientResponseException exception) {
            console.printError("Account could not be located.");
        }
        return usersAccount.getBalance();
    }

}
