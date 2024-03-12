package com.matiasnetto.anotadordetruco.Services;

import com.matiasnetto.anotadordetruco.Dao.UserDao;
import com.matiasnetto.anotadordetruco.Dto.CreateNewUserDTORequest;
import com.matiasnetto.anotadordetruco.Exceptions.UserAlreadyExistsException;
import com.matiasnetto.anotadordetruco.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    public User createNewUser(CreateNewUserDTORequest userInData) throws Exception {
        if (userDao.doesUserExists(userInData.username)) {
            throw new UserAlreadyExistsException();
        }

        return userDao.createNewUser(userInData);
    }

}
