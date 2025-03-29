package com.example.jwttest.DTO.Game;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameDTO {
    private long id;
    private Date date;
    private int HomeScore;
    private int AwayScore;
    private String HomeClubName;
    private String AwayClubName;
    private String HomeClubImage;
    private String AwayClubImage;
    private String HomeClubStadiumName;
    private String LeagueName;

}
