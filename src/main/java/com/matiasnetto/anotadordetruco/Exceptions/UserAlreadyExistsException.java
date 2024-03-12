package com.matiasnetto.anotadordetruco.Exceptions;
public class UserAlreadyExistsException extends  Exception{
    public UserAlreadyExistsException() {
        super();
    }
    public UserAlreadyExistsException(String message) {
        super(message);
    }

}
