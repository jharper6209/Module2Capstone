package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.UserSqlDAO;
import com.techelevator.tenmo.model.Transfer.Transfer;
import com.techelevator.tenmo.model.Transfer.TransferDAO;
import com.techelevator.tenmo.model.Transfer.TransferDTO;
import com.techelevator.tenmo.model.User;
import com.techelevator.tenmo.model.account.Account;
import com.techelevator.tenmo.model.account.AccountDAO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/*******************************************************************************************************
 * This is where you code any API controllers you may create
 *
 * Feel free to create additional controller classs if you would
 * like to have separate controller classses base on functionality or use
********************************************************************************************************/

@RestController
public class ApiController {
AccountDAO theAcctData;
UserSqlDAO theUserData;
TransferDAO theTransferData;
TransferDTO aTransfer;



public ApiController(AccountDAO theAccounts, UserSqlDAO theUsers, TransferDAO theTransfers){
    theAcctData = theAccounts;
    theUserData = theUsers;
    theTransferData = theTransfers;

}
//@PreAuthorize("isAuthenticated()")
@RequestMapping(path="account/{id}", method = RequestMethod.GET)
public Account getBalance(@PathVariable Long id) {
    System.out.println("GET account/" + id);
    return theAcctData.viewBalance(id);
}

@RequestMapping(path = "user", method = RequestMethod.GET)
public List<User> getAllUsers(){
 System.out.println("GET users");
    List<User> users = new ArrayList();
    users = theUserData.findAll();
    return users;
}

    // Process a Transfer - update the sending and to Account balance and record the Transfer in the Transfer table
    //
    // path: user/transfer
    // type of request: HTTP POST
    // receives: a Transfer object  in the request of the POST
    // return: the Transfer object with the Account object in it with updated balance
    //
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "transfer", method = RequestMethod.POST)
    public TransferDTO processTransfer(@RequestParam Long senderId, @RequestParam Long receiverId,
                                       @RequestParam Double amount){
      System.out.println("POST - user/transfer");
      TransferDTO transferDTO = createTransferDto(senderId, receiverId, amount);
      theAcctData.updateAccountBalance(transferDTO.getAccountFrom(), -amount);
      theAcctData.updateAccountBalance(transferDTO.getAccountTo(), amount);

      Transfer transferObject = createTransferFromTransferDto(transferDTO);

      theTransferData.sendTransfer(transferDTO);

      transferDTO.setAccountFrom(theAcctData.viewBalance(senderId));
      transferDTO.setAccountTo(theAcctData.viewBalance(receiverId));
      transferObject.setAccountFrom(senderId);

      // Use the Account DAO to update the balance in the sending account negative amount added is subtract (-theTransferDto.getAmount())******
      // Use the Account DAO to update the balance in the receiving account*******
      // Create a Transfer object using the data in the TransferDTO and any constant values*******
      // Use the TransferDAO to add the new Transfer object to transfer ********
      // Replace the sending Account object in the  TransferDTO with a new version that has teh updated balance*****
      //             Use the AccountDAO to retrieve the sending account*******
      //             Use the TransferDTOADO to set the sending account in the TransferDTO to updated sending Account????????????
      // Replace the sending Account object in the  TransferDTO with a new version that has teh updated balance???????????????
      //             Use the AccountDAO to retrieve the receiving  account
      //             Use the TransferDTOADO to set the receiving  account in the TransferDTO to updated receiving Account
      // return theTransferDTO ************

      return transferDTO;
    }

    @RequestMapping(path = "transfer", method = RequestMethod.GET)
    public List<Transfer> getAllTransfers(){
        System.out.println("GET transfers");
        List<Transfer> transfers = new ArrayList();
        transfers = theTransferData.findAll();
        return transfers;
    }


private TransferDTO createTransferDto(Long senderId, Long receiverId, Double amount) {
    TransferDTO transferDTO = new TransferDTO();
    transferDTO.setAccountFrom(theAcctData.viewBalance(senderId));
    transferDTO.setAccountTo(theAcctData.viewBalance(receiverId));
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

    return transfer;
}






/*private UserDTO convertUserToUserDTO(User user) {
    UserDTO userResult = new UserDTO();
    userResult.setId(user.getId());
    userResult.setUsername(user.getUsername());

    return userResult;
}
*/
}

