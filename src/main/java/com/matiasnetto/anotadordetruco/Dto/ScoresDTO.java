package com.matiasnetto.anotadordetruco.Dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ScoresDTO {
    public int teamAScore;
    public int teamBScore;

    public void addPointToTeamA() {
        teamAScore += 1;
    }

    public void addPointToTeamB() {
        teamBScore += 1;
    }


    public void removePointToTeamA() {
        if(teamAScore > 0) {
            teamAScore -= 1;
        }
    }

    public void removePointToTeamB() {
        if(teamBScore > 0) {
            teamBScore -= 1;
        }
    }
}
