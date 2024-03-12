package com.matiasnetto.anotadordetruco.Dto;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class PlayersDTO {
    List<String> teamAPlayers;
    List<String> teamBPlayers;
}
