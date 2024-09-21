package com.TileSlide.TileSlideService.Services;

import com.TileSlide.TileSlideService.Models.Player;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

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
        return ResponseEntity.status(HttpStatus.OK).body("Data Came");
    }

}
