package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.UserSqlDAO;
import com.techelevator.tenmo.model.TransferDTO;
import com.techelevator.tenmo.model.User;
import com.techelevator.tenmo.model.UserDTO;
import com.techelevator.tenmo.model.account.Account;
import com.techelevator.tenmo.model.account.AccountDAO;
import org.springframework.security.access.prepost.PreAuthorize;
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
TransferDTO aTransfer;
UserDTO userDto;


public ApiController(AccountDAO theAccounts, UserSqlDAO theUsers, UserDTO theUserDto, TransferDTO theTransfers){
    theAcctData = theAccounts;
    theUserData = theUsers;
    aTransfer = theTransfers;
    userDto = theUserDto;

}
//@PreAuthorize("isAuthenticated()")
@RequestMapping(path="account/{id}", method = RequestMethod.GET)
public Account getBalance(@PathVariable long id) {

    return theAcctData.viewBalance(id);
}

@RequestMapping(path = "user", method = RequestMethod.GET)
public List<UserDTO> getAllUsers(){
    List<User> users;
    users = theUserData.findAll();
    List<UserDTO> usersLessInfo = new ArrayList();
    for (User theUser : users) {
        usersLessInfo.add(convertUserToUserDTO(theUser));
    }
    return usersLessInfo;

}





private UserDTO convertUserToUserDTO(User user) {
    UserDTO userResult = new UserDTO();
    userResult.setId(user.getId());
    userResult.setUsername(user.getUsername());

    return userResult;
}

}

