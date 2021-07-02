package com.techelevator.tenmo.services;

import com.techelevator.tenmo.models.Account.Account;
import com.techelevator.tenmo.models.AppUser;
import com.techelevator.tenmo.models.User;
import com.techelevator.tenmo.models.transfer.Transfer;
import com.techelevator.tenmo.models.transfer.TransferDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*******************************************************************************************************
 * This is where you code Application Services required by your solution
 * 
 * Remember:  theApp ==> ApplicationServices  ==>  Controller  ==>  DAO
********************************************************************************************************/

public class TenmoApplicationServices {


    public static final String API_BASE_URL = "http://localhost:8080/";


    public RestTemplate apiCall = new RestTemplate();


    public Double viewBalance(long userId) {
        Account usersAccount = new Account();

         usersAccount = apiCall.getForObject(API_BASE_URL + "account/" + userId,   Account.class);

        return usersAccount.getBalance();
    }


    public List<User> getAllUsers() {

        User[] users = apiCall.getForObject(API_BASE_URL+"user", User[].class);

        return Arrays.asList(users);
    }

    public TransferDTO sendTransfer(Account sendingAccount, Account receivingAccount, Double amount) {

        // Create a TransferDTO with the sendinging, receiving accounts and teh amount
        // Call the server with the API path and TransferDTO - return teh updated TransferDTO
        // return the updated Transfer you got from the server



       // Transfer transfer = new Transfer();
       // return apiCall.postForObject(API_BASE_URL + "user/" + senderId + "/transfer",
       //                              makeEntity(transfer), Transfer.class);
    }

    private HttpEntity<Transfer> makeEntity(TransferDTO transfer){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Transfer> entity = new HttpEntity(transfer, headers);
        return entity;
    }

    private Transfer makeTransfer() {
        return null;
    }
}

