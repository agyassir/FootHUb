package com.example.jwttest.DTO.PlayerStats;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlayerStatsDTO {
    private Long id;
    private Integer goals;
    private Integer assists;
    private Double avgMatchRating;
    private Double dribbleSuccessRate;
    private Date matchDate;
    private Integer minutesPlayed;
    private Double passAccuracy;
    private Integer redCards;
    private String season;
    private Double shotsOnTargetPercentage;
    private Double tacklesPerGame;
    private Integer yellowCards;

    // Constructor, getters, setters
}