package com.example.jwttest.DTO.Club;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.Date;

@Data
public class ClubRequest {


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
}
