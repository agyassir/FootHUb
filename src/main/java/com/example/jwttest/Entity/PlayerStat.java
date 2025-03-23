package com.example.jwttest.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "player_stats")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PlayerStat {
    @Id
    @GeneratedValue(strategy = GenerationType
            .IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id", nullable = false)

    private Player player;

     
    private LocalDate matchDate;

     
    private Integer goals;

     
    private Integer assists;

     
    private Integer yellowCards;

     
    private Integer redCards;

     
    private Integer minutesPlayed;

     
    private Double passAccuracy;

     
    private Double dribbleSuccessRate;

     
    private Double shotsOnTargetPercentage;

     
    private Double tacklesPerGame;

     
    private Double avgMatchRating;

    @Column(name = "season", nullable = false)
    private String season;
}
