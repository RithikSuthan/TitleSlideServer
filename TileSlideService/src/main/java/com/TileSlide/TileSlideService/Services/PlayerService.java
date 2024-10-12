package com.TileSlide.TileSlideService.Services;

import com.TileSlide.TileSlideService.Models.Player;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Service
public class PlayerService {
    private MongoTemplate mongoTemplate;

    public PlayerService(MongoTemplate mongoTemplate)
    {
        this.mongoTemplate=mongoTemplate;
    }

    public ResponseEntity<?> addPlayer(Player player)
    {
        System.out.println(player);
        String message="";
        if(player.getUserName()=="" || player.getEmail()=="" ||player.getPassword()=="")
        {
            message="Any of the Fields is empty";
        }
        else
        {
            mongoTemplate.save(player);
            message="Player added successfully";
        }
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    public ResponseEntity<?> existEmail(String email)
    {
        String message="";
        Query query=new Query(Criteria.where("email").is(email));
        Player existPlayer=mongoTemplate.findOne(query,Player.class);
        if(existPlayer!=null)
        {
            message="Email exists already";
        }
        else
        {
            message="User doesn't exist";
        }
        Map<String,String> response=new HashMap<>();
        response.put("message",message);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    public ResponseEntity<?> existUserName(String userName)
    {
        String message="";
        Query query=new Query(Criteria.where("userName").is(userName));
        Player exist=mongoTemplate.findOne(query,Player.class);
        if(exist!=null)
        {
            message="User Name Already Taken";
        }
        else
        {
            message="User Name Can be used";
        }
        Map<String,String> response =new HashMap<>();
        response.put("message",message);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    public ResponseEntity<?> loginEmployee(Player player)
    {
        String message="";
        Query query=new Query(Criteria.where("email").is(player.getEmail()).and("password").is(player.getPassword()));
        Player exist=mongoTemplate.findOne(query,Player.class);
        if(exist!=null)
        {
            message="Login Successful";
        }
        else
        {
            message="User doesn't exist";
        }
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }
}
