package com.example.jwttest.DTO.Player;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlayerTransferDTO {
    private Long id;
    private String playerFirstName;
    private String playerLastName;
    private  String FromClubName;
    private String ToClubName;
    private String FromClubLeagueName;
    private Date transferDate;
    private double price;
}
