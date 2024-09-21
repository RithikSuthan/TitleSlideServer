package com.TileSlide.TileSlideService.Models;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("player")
public class Player {
    private String userName;
    private String email;
    private String password;
}

