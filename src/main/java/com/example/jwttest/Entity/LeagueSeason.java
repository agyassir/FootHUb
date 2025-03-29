package com.example.jwttest.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="league_season")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeagueSeason {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch=FetchType.LAZY)
    private League league;
    private String season;
    @OneToMany
    List<LeagueStanding> leagueStandings;

}
