package com.matiasnetto.anotadordetruco.Dto.Responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateNewMatchDTOResponse {
    private String roomId;
    private String adminKey;
}
