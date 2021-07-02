package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.UserSqlDAO;
import com.techelevator.tenmo.model.Transfer.TransferDTO;
import com.techelevator.tenmo.model.User;
import com.techelevator.tenmo.model.account.Account;
import com.techelevator.tenmo.model.account.AccountDAO;
import org.springframework.web.bind.annotation.*;

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
TransferDTO aTransfer;



public ApiController(AccountDAO theAccounts, UserSqlDAO theUsers, TransferDTO theTransfers){
    theAcctData = theAccounts;
    theUserData = theUsers;
    aTransfer = theTransfers;

}
//@PreAuthorize("isAuthenticated()")
@RequestMapping(path="account/{id}", method = RequestMethod.GET)
public Account getBalance(@PathVariable long id) {

    return theAcctData.viewBalance(id);
}

@RequestMapping(path = "user", method = RequestMethod.GET)
public List<User> getAllUsers(){
    List<User> users;
    users = theUserData.findAll();

    return users;
}






/*private UserDTO convertUserToUserDTO(User user) {
    UserDTO userResult = new UserDTO();
    userResult.setId(user.getId());
    userResult.setUsername(user.getUsername());

    return userResult;
}
*/
}

