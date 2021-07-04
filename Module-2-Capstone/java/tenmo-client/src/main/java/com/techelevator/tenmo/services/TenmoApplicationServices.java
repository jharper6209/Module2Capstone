package com.techelevator.tenmo.services;

import com.techelevator.tenmo.models.Account.Account;
import com.techelevator.tenmo.models.User;
import com.techelevator.tenmo.models.transfer.Transfer;
import com.techelevator.tenmo.models.transfer.TransferDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

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


    public Account viewBalance(long userId) {
        Account usersAccount = new Account();

        usersAccount = apiCall.getForObject(API_BASE_URL + "account/" + userId, Account.class);

        return usersAccount;
    }


    public List<User> getAllUsers() {

        User[] users = apiCall.getForObject(API_BASE_URL + "user", User[].class);

        return Arrays.asList(users);
    }



    public TransferDTO sendTransfer(Long senderId, Long receiverId, Double amount) {
        // Create a TransferDTO with the sending, receiving accounts and the amount
        TransferDTO transferDto = mapToTransferDto(senderId, receiverId, amount);
        // Call the server with the API path and TransferDTO - return the updated TransferDTO
        apiCall.postForObject(API_BASE_URL + "user/transfer",
                                makeEntity(transferDto), TransferDTO.class);


        // return the updated Transfer you got from the server

        // Transfer transfer = ;

        return transferDto;
    }

    public Transfer viewTransferHistory(Long transferId) {
        Transfer transferHistory = apiCall.getForObject(API_BASE_URL + "user/transfer/" + transferId, Transfer.class);
        return transferHistory;
    }

    public List<Transfer> getAllTransfersForUser(User user) {
        // Frank changed path to be more RESTful (user/{id}/transfer and to use new path
        Transfer[] transfers = apiCall.getForObject(API_BASE_URL+"user/"+user.getId()+"/transfer", Transfer[].class);
        return Arrays.asList(transfers);
    }




    private HttpEntity<TransferDTO> makeEntity(TransferDTO transferDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<TransferDTO> entity = new HttpEntity(transferDto, headers);
        return entity;
    }

    private TransferDTO mapToTransferDto(Long senderId, Long receiverId, Double amount) {
        TransferDTO transferDTO = new TransferDTO();
        transferDTO.setAccountFrom(viewBalance(senderId));
        transferDTO.setAccountTo(viewBalance(receiverId));
        transferDTO.setAmount(amount);

        return transferDTO;
    }



    private Transfer createTransferFromTransferDto(TransferDTO transferDto) {
        Transfer transfer = new Transfer();
        transfer.setAccountFrom(transferDto.getAccountFrom().getAccountId());
        transfer.setAccountTo(transferDto.getAccountTo().getAccountId());
        transfer.setAmount(transfer.getAmount());
        transfer.setTransferStatusId(2L);
        transfer.setTransferTypeId(2L);
        //  transfer.setTransferId(theTransferData.getTransferId());

        return transfer;
    }


}