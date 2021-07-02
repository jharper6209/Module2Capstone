package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.UserSqlDAO;
import com.techelevator.tenmo.model.AppUser;
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
    // type of request: HTPP POST
    // receives: a Transfer object  in the request of the POST
    // return: the Transfer object with the Account object in it with updated balance
    //
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "user/transfer", method = RequestMethod.POST)
    public TransferDTO processTransfer(@RequestBody TransferDTO theTransferDto){
      System.out.println("POST - user/transfer");
      // Use the Account DAO to update the balance in the sending account negative amount added is subtract (-theTransferDto.getAmount())
      // Use the Account DAO to update the balance in the receiving account
      // Create a Transfer object using the data in the TransferDTO and any constant values
      // Use the TransferDAO to add the new Transfer object to transfer
      // Replace the sending Account object in the  TransferDTO with a new version that has teh updated balance
      //             Use the AccountDAO to retrieve the sending account
      //             Use the TransferDTOADO to set the sending account in the TransferDTO to updated sending Account
      // Replace the sending Account object in the  TransferDTO with a new version that has teh updated balance
      //             Use the AccountDAO to retrieve the receiving  account
      //             Use the TransferDTOADO to set the receiveing  account in the TransferDTO to updated recieving Account
      // return theTransferDTO


      return theTransferDto;
    }










/*private UserDTO convertUserToUserDTO(User user) {
    UserDTO userResult = new UserDTO();
    userResult.setId(user.getId());
    userResult.setUsername(user.getUsername());

    return userResult;
}
*/
}

