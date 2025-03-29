package com.example.jwttest.DTO.League;

import com.example.jwttest.DTO.Club.ClubDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LeagueDto {
    private Long id;
    private String name;
    private String country;
    private String logo;
    private String flag;

    // Clubs relationship - using simplified DTOs or just IDs to avoid circular references
    private List<ClubDTO> clubs;
}
