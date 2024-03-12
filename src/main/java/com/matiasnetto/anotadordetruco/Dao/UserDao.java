package com.matiasnetto.anotadordetruco.Dao;

import com.matiasnetto.anotadordetruco.Dto.CreateNewUserDTORequest;
import com.matiasnetto.anotadordetruco.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserDao {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private String getUserKey(String username) {
        return "user-" + username;
    }

    public User createNewUser(CreateNewUserDTORequest userInData) {
        String passwordHash = userInData.password; //!TODO Encrypt the password!!!!

        String key = getUserKey(userInData.username);
        redisTemplate.opsForHash().put(key, "username", userInData.username);
        redisTemplate.opsForHash().put(key, "fullName", userInData.fullName);
        redisTemplate.opsForHash().put(key, "passwordHash", passwordHash);

        return User.builder()
                .fullName(userInData.fullName)
                .username(userInData.username)
                .passwordHash(passwordHash)
                .build();
    }

    public boolean doesUserExists(String username) {
        return redisTemplate.opsForHash().get(getUserKey(username),"username") != null;
    }

    public User getUserByUsername(String username) {
        String key = getUserKey(username);
        String fullName = (String) redisTemplate.opsForHash().get(key, "fullName");
        String passwordHash = (String) redisTemplate.opsForHash().get(key, "passwordHash");

        return new User(username, fullName,passwordHash);
    }
}
