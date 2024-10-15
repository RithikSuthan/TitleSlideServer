package com.TileSlide.TileSlideService.Models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("player")
public class Player {
    private String userName;
    private String email;
    private String password;
    private int highScore;
}

