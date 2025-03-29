package com.example.jwttest.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "league_standing")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class LeagueStanding {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_id")
    @JsonIgnore
    private Club club;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "league_season_id")
    private LeagueSeason Season;

    private int points;
    private int standing;
    private int match_played;
    private int win;
    private int draw;
    private int loss;
    @Column(name = "goalsfor")
    private int GoalsFor;
    @Column(name = "goalsagainst")
    private int GoalsAgainst;

}
