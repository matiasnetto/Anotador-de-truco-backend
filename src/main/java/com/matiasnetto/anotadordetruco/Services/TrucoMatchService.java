package com.matiasnetto.anotadordetruco.Services;

import com.matiasnetto.anotadordetruco.Dao.MatchDao;
import com.matiasnetto.anotadordetruco.Models.MatchModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TrucoMatchService {

    @Autowired
    private MatchDao matchDao;
    public MatchModel createNewMatch(String matchAdmin) {
        UUID uuid = UUID.randomUUID();
        String roomId = uuid.toString().substring(uuid.toString().length() - 8);

        MatchModel match = MatchModel.builder()
                .roomId(roomId)
                .matchAdmin(matchAdmin)
                .adminKey(UUID.randomUUID())
                .build();

        matchDao.setTeamAScore(match.getRoomId(), 0);
        matchDao.setTeamBScore(match.getRoomId(), 0);
        matchDao.setMatchOwner(match.getRoomId(), match.getMatchAdmin());
        matchDao.setAdminKey(match.getRoomId(), match.getAdminKey().toString());

        return match;
    }


    public MatchModel getMatchByRoomId(String roomId) {
        String matchOwner = matchDao.getMatchOwner(roomId);
        int teamAScores = matchDao.getTeamAScore(roomId);
        int teamBScores =  matchDao.getTeamBScore(roomId);
        List<String> teamAPlayers = matchDao.getTeamAPlayers(roomId);
        List<String> teamBPlayers = matchDao.getTeamAPlayers(roomId);

        return MatchModel.builder()
                .roomId(roomId)
                .matchAdmin(matchOwner)
                .teamAScore(teamAScores)
                .teamBScore(teamBScores)
                .teamAPlayers(teamAPlayers)
                .teamBPlayers(teamBPlayers)
                .build();
    }

    public void updateScores(String roomId, int teamAScore, int teamBScore) {
        matchDao.setTeamAScore(roomId, teamAScore);
        matchDao.setTeamBScore(roomId, teamBScore);
    }

    public void  addTeamAPlayer(String roomId, String playerName) {
            matchDao.removePlayerFromTeamB(roomId, playerName);
            matchDao.addPlayerToTeamA(roomId, playerName);
    }

    public void  addTeamBPlayer(String roomId, String playerName) {
        matchDao.removePlayerFromTeamA(roomId, playerName);
        matchDao.addPlayerToTeamB(roomId, playerName);
    }

    public void removeTeamAPlayer(String roomId, String playerName) {
        matchDao.removePlayerFromTeamA(roomId,playerName);
    }

    public void  removeTeamBPlayer(String roomId, String playerName) {
        matchDao.removePlayerFromTeamB(roomId,playerName);
    }

    public List<String> getTeamAPlayers(String roomId) {
       return matchDao.getTeamAPlayers(roomId);
    }

    public List<String> getTeamBPlayers(String roomId) {
        return matchDao.getTeamBPlayers(roomId);
    }

}
