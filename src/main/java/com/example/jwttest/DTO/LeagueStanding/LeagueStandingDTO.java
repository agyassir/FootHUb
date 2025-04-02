package com.example.jwttest.DTO.LeagueStanding;

import com.example.jwttest.DTO.LeagueSeason.LeagueSeasonDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeagueStandingDTO {

    private Long id;
    private String ClubName;
    private String ClubImage;
    private Long ClubId;
    private int points;
    private int standing;
    private int match_played;
    private LeagueSeasonDTO leagueSeason;
    private int win;
    private int draw;
    private int loss;
    private int GoalsFor;
    private int GoalsAgainst;
}
