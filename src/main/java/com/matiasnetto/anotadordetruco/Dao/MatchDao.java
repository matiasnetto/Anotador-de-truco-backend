package com.matiasnetto.anotadordetruco.Dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class MatchDao {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void addPlayerToTeamA(String room_id, String playerName) {
        redisTemplate.opsForSet().add(room_id + "-team-a-players",playerName );
    }

    public void addPlayerToTeamB(String room_id, String playerName) {
        redisTemplate.opsForSet().add(room_id + "-team-b-players",playerName );
    }

    public  void removePlayerFromTeamA(String room_id, String playerName) {
        redisTemplate.opsForSet().remove(room_id + "-team-a-players", playerName);
    }

    public  void removePlayerFromTeamB(String room_id, String playerName) {
        redisTemplate.opsForSet().remove(room_id + "-team-b-players", playerName);
    }

    public void setMatchOwner(String room_id, String playerName) {
        redisTemplate.opsForHash().put(room_id, "match-owner",playerName);
    }

    public String getMatchOwner(String room_id) {
        return (String) redisTemplate.opsForHash().get(room_id,"match-owner");
    }

    public void setAdminKey(String room_id, String adminKey) {
        redisTemplate.opsForHash().put(room_id, "admin-key", adminKey);
    }

    public String getAdminKey(String room_id, String adminKey) {
            return (String)redisTemplate.opsForHash().get(room_id, "admin-key");
    }

    public List<String> getTeamAPlayers(String room_id) {
        return redisTemplate.opsForList().range(room_id + "-team-a-players", 0, -1).stream().map(Object::toString).collect(Collectors.toList());
    }

    public List<String> getTeamBPlayers(String room_id) {
        return redisTemplate.opsForList().range(room_id + "-team-b-players", 0, -1).stream().map(Object::toString).collect(Collectors.toList());
    }

    public Integer getTeamAScore(String room_id) {
        return  Integer.parseInt((String) redisTemplate.opsForHash().get(room_id,"team-a-score"));
    }

    public Integer getTeamBScore(String room_id) {
        return  Integer.parseInt((String) redisTemplate.opsForHash().get(room_id,"team-b-score"));
    }

    public void setTeamAScore(String room_id, int value) {
        redisTemplate.opsForHash().put(room_id,"team-a-score", value);
    }

    public void setTeamBScore(String room_id, int value) {
        redisTemplate.opsForHash().put(room_id,"team-b-score", value);
    }


}
