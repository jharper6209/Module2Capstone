package com.techelevator.tenmo.services;

import com.techelevator.tenmo.models.Account.Account;
import com.techelevator.tenmo.models.User;
import com.techelevator.tenmo.models.transfer.Transfer;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/*******************************************************************************************************
 * This is where you code Application Services required by your solution
 * 
 * Remember:  theApp ==> ApplicationServices  ==>  Controller  ==>  DAO
********************************************************************************************************/

public class TenmoApplicationServices {


    public static final String API_BASE_URL = "http://localhost:8080/";


    public RestTemplate apiCall = new RestTemplate();

    @RequestMapping(path = "account/{id}", method = RequestMethod.GET )
    public Double viewBalance(long userId) {
        Account usersAccount = new Account();

         usersAccount = apiCall.getForObject(API_BASE_URL + "account/" + userId,   Account.class);

        return usersAccount.getBalance();
    }

    @RequestMapping(path="user", method = RequestMethod.GET)
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        users.add(apiCall.getForObject(API_BASE_URL+"user", User.class));
        return users;
    }

    @RequestMapping(path = "user/{id}/transfer", method = RequestMethod.POST)
    public Transfer sendTransfer(@PathVariable long senderId, long receiverId, Double amount) {
        return apiCall.postForObject(API_BASE_URL + "user/" + senderId + "/transfer",
                                     makeEntity(), Transfer.class);
    }


 //   console.getUserInput("Username");

    private HttpEntity<Transfer> makeEntity(Transfer transfer){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Transfer> entity = new HttpEntity(transfer, headers);
        return entity;
    }

    private Transfer makeTransfer() {


        return null;

    }
}

