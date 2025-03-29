package com.example.jwttest.DTO.Club;

import com.example.jwttest.DTO.LeagueStanding.LeagueStandingDTO;
import com.example.jwttest.DTO.Player.PlayerDTO;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClubDTO {
    private Long id;
    private String name;
    private Date DateOfEstablishement;
    private String owner;
    private String image;
    private Integer popularityScore;

    private String StadiumName;
    private int StadiumCapacity;
    private String CoachName;
    private List<PlayerDTO> players;
    private List<LeagueStandingDTO> leagueStandings;

    private String leagueName;
    private Long leagueId;



}
