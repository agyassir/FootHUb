package com.example.jwttest.DTO.Club;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.Date;

@Data
public class ClubRequest {


    @Null
    private Long id=null;

    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name must be less than 100 characters")
    private String name;

    @NotNull(message = "Date of establishment is required")
    @Past(message = "Date of establishment must be in the past or present")
    private Date dateOfEstablishment;

    @NotBlank(message = "Owner is required")
    @Size(max = 100, message = "Owner must be less than 100 characters")
    private String owner;

    @NotNull(message = "Stadium is required")
    private Long stadiumId;

    @NotNull(message = "popularity is required")
    @Min(60)
    @Max(100)
    private Integer popularityScore;

    @NotNull(message = "league is required")
    private Long leagueId;

    @Null
    private String image=null;

}
