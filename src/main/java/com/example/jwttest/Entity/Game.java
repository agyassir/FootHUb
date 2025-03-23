package com.example.jwttest.Entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public class Game {
        @Id
        @GeneratedValue(strategy= GenerationType.IDENTITY)
        private long id;
        private Date date;
        private int HomeScore;
        private int AwayScore;
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "home_club_id")
        @JsonManagedReference
        private Club homeTeam;
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name="away_club_id")
        @JsonManagedReference
        private Club awayTeam;
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name="league_id")
        private League league;
}
