package com.example.jwttest.DTO.Player;

import com.example.jwttest.DTO.PlayerStats.PlayerStatsDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;
    private String nationality;
    private String position;
    private Integer number;
    private double marketValue;
    private Date contractUntil;
    private boolean trending;
    private List<PlayerStatsDTO> stats;

    // Constructor, getters, setters
}