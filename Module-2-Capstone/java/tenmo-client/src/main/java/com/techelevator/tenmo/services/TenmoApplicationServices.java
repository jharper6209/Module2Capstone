package com.techelevator.tenmo.services;

import com.techelevator.tenmo.models.Account.Account;
import com.techelevator.view.ConsoleService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

/*******************************************************************************************************
 * This is where you code Application Services required by your solution
 * 
 * Remember:  theApp ==> ApplicationServices  ==>  Controller  ==>  DAO
********************************************************************************************************/

public class TenmoApplicationServices {


    public static final String API_BASE_URL = "http://localhost:8080/";
    public static String AUTH_TOKEN = "";

    public RestTemplate apiCall = new RestTemplate();

    public Double viewBalance(long userId) {
        Account usersAccount = new Account();

         usersAccount = apiCall.exchange(API_BASE_URL + "account/" + userId, HttpMethod.GET,  Account.class);

        return usersAccount.getBalance();
    }

    private HttpEntity makeAuthEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(AUTH_TOKEN);
        HttpEntity entity = new HttpEntity<>(headers);
        return entity;

}
}

