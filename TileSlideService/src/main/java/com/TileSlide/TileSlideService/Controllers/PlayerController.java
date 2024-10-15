package com.TileSlide.TileSlideService.Controllers;

import com.TileSlide.TileSlideService.Models.Player;
import com.TileSlide.TileSlideService.Services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class PlayerController {
    @Autowired
    PlayerService playerService;

    @PostMapping("/add")
    public ResponseEntity<?> addPlayer(@RequestBody Player player)
    {
        return playerService.addPlayer(player);
    }

    @GetMapping("/existEmail")
    public ResponseEntity<?> exsitEmail(@RequestParam String email)
    {
        return playerService.existEmail(email);
    }

    @GetMapping("/existuserName")
    public ResponseEntity<?> existUserName(@RequestParam String userName)
    {
        return playerService.existUserName(userName);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Player player)
    {
        return playerService.loginEmployee(player);
    }
    @PutMapping("/updatescore")
    public ResponseEntity<?> updateScore(@RequestParam String email,@RequestParam int score)
    {
        return playerService.updateScore(email,score);
    }

    @GetMapping("/fetch")
    public ResponseEntity<?> fetchPlayer(@RequestParam String email)
    {
        return playerService.fetchPlayer(email);
    }
}
