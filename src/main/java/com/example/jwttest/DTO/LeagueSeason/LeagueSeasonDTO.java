package com.example.jwttest.DTO.LeagueSeason;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LeagueSeasonDTO {
private Long id;
private String Season;
private Long leagueId;
private String leagueName;

}
