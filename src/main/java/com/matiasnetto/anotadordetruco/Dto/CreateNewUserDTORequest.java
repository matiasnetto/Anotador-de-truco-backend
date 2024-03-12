package com.matiasnetto.anotadordetruco.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
public class CreateNewUserDTORequest {
    public String username;
    public String fullName;
    public String password;
}
