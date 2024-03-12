package com.matiasnetto.anotadordetruco.Controllers;

import com.matiasnetto.anotadordetruco.Dto.PlayersDTO;
import com.matiasnetto.anotadordetruco.Dto.ScoresDTO;
import com.matiasnetto.anotadordetruco.Models.Team;
import com.matiasnetto.anotadordetruco.Services.TrucoMatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.stereotype.Controller;

@Controller
public class MatchSocketController {

    @Autowired
    TrucoMatchService matchService;
    @MessageMapping("/match/{roomId}/{team}/scores.add")
    @SendTo("/topic/{roomId}/scores")
    public ScoresDTO addTeamPoint(@DestinationVariable String roomId, @DestinationVariable Team team) {
        ScoresDTO scores = matchService.getScores(roomId);

        if (team == Team.A) {
            scores.addPointToTeamA();
        } else if (team == Team.B) {
            scores.addPointToTeamB();
        }
        matchService.updateScores(roomId,scores);

        return scores;
    }

    @MessageMapping("/match/{roomId}/{team}/scores.remove")
    @SendTo("/topic/{roomId}/scores")
    public ScoresDTO removeTeamPoint(@DestinationVariable String roomId, @DestinationVariable Team team) {
        ScoresDTO scores = matchService.getScores(roomId);

        if (team == Team.A) {
            scores.removePointToTeamA();
        } else if (team == Team.B) {
            scores.removePointToTeamB();
        }
        matchService.updateScores(roomId,scores);

        return scores;
    }

    @MessageMapping("/match/{roomId}/{team}/players.add")
    @SendTo("/topic/{roomId}/players")
    public PlayersDTO addTeamPlayer(@DestinationVariable String roomId, @DestinationVariable Team team, String username) {

        if (team == Team.A) {
            matchService.addTeamAPlayer(roomId,username);
        } else if (team == Team.B) {
            matchService.addTeamBPlayer(roomId,username);
        }

       var playersA = matchService.getTeamAPlayers(roomId);
       var playersB = matchService.getTeamBPlayers(roomId);

       return new PlayersDTO(playersA, playersB);
    }

    @MessageMapping("/match/{roomId}/{team}/players.remove")
    @SendTo("/topic/{roomId}/players")
    public PlayersDTO removeTeamPlayer(@DestinationVariable String roomId, @DestinationVariable Team team, String username) {

        if (team == Team.A) {
            matchService.removeTeamAPlayer(roomId,username);
        } else if (team == Team.B) {
            matchService.removeTeamBPlayer(roomId,username);
        }

        var playersA = matchService.getTeamAPlayers(roomId);
        var playersB = matchService.getTeamBPlayers(roomId);

        return new PlayersDTO(playersA, playersB);
    }


}
