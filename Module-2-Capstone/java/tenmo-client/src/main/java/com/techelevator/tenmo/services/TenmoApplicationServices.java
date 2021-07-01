package com.techelevator.tenmo.services;

import com.techelevator.tenmo.models.Account.Account;
import com.techelevator.tenmo.models.User;
import com.techelevator.tenmo.models.UserDTO;
import com.techelevator.view.ConsoleService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestClientResponseException;
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
    public List<UserDTO> getAllUsers() {
        List<UserDTO> users = new ArrayList<>();
        users.add(apiCall.getForObject(API_BASE_URL+"user", UserDTO.class));
        return users;
    }


}

