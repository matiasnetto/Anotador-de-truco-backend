package com.matiasnetto.anotadordetruco.Controllers;

import com.matiasnetto.anotadordetruco.Dto.CreateNewUserDTORequest;
import com.matiasnetto.anotadordetruco.Dto.CreateNewUserDTOResponse;
import com.matiasnetto.anotadordetruco.Exceptions.UserAlreadyExistsException;
import com.matiasnetto.anotadordetruco.Models.User;
import com.matiasnetto.anotadordetruco.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> createNewUser(@RequestBody CreateNewUserDTORequest userInData) {
        User myUser;
        try {
            myUser = userService.createNewUser(userInData);
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username: <" + userInData.username + "> already exists, try another username");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

        return  ResponseEntity.ok(new CreateNewUserDTOResponse(myUser.getUsername(), myUser.getFullName()));
    }
}
