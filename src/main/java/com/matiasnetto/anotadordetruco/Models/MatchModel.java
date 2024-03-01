package com.matiasnetto.anotadordetruco.Models;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MatchModel {
    private String roomId;
    private int teamAScore = 0;
    private int teamBScore = 0;
    private String matchAdmin = null;
    private UUID adminKey;
    private List<String> teamAPlayers = List.of();
    private List<String> teamBPlayers = List.of();
}
