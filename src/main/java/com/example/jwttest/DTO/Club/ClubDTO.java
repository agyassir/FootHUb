package com.example.jwttest.DTO.Club;

import com.example.jwttest.DTO.LeagueStanding.LeagueStandingDTO;
import com.example.jwttest.DTO.Player.PlayerDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date DateOfEstablishement;
    private String owner;
    private String image;
    private Integer popularityScore;
    private Long StadiumId;
    private String StadiumName;
    private int StadiumCapacity;
    private String CoachName;
    private List<PlayerDTO> players;
    private List<LeagueStandingDTO> leagueStandings;

    private String leagueName;
    private Long leagueId;


    public ClubDTO(long id, String name, int popularityScore) {
        this.id = id;
        this.name = name;
        this.popularityScore = popularityScore;
    }
}
