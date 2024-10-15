package com.TileSlide.TileSlideService.Services;

import com.TileSlide.TileSlideService.Models.Player;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
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
            player.setHighScore(0);
            mongoTemplate.save(player);
            message="Player added successfully";
        }
        Map<String,String> response=new HashMap<>();
        response.put("message",message);
        return ResponseEntity.status(HttpStatus.OK).body(response);
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
        Map<String,String> response=new HashMap<>();
        response.put("message",message);
        response.put("email",player.getEmail());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    public ResponseEntity<?> updateScore(String email,int score)
    {
        String message="";
        Query query=new Query(Criteria.where("email").is(email));
        Update update=new Update().set("highScore",score);
        Player updatedPlayer=mongoTemplate.findAndModify(query,update,Player.class);
        Map<String,String> response=new HashMap<>();
        if(updatedPlayer!=null)
        {
            message="Player score updated Successfully";
        }
        else
        {
            message="Player not found";
        }
        response.put("message",message);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    public ResponseEntity<?> fetchPlayer(String email)
    {
        Query query=new Query(Criteria.where("email").is(email));
        Player exist=mongoTemplate.findOne(query,Player.class);
        return ResponseEntity.status(HttpStatus.OK).body(exist);
    }
}
