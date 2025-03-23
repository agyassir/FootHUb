package com.example.jwttest.DTO.Player;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.util.Date;

public class PLayerRequest {
    @NotNull(message = "ID is required for updates")
    private Long id;

    @NotBlank(message = "First name is required")
    @Size(max = 100, message = "First name must be less than 100 characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 100, message = "Last name must be less than 100 characters")
    private String lastName;

    @NotNull(message = "Market value is required")
    @PositiveOrZero(message = "Market value must be a positive number or zero")
    private Double marketValue;


    @NotNull(message = "Club ID is required")
    private Long clubId;
}
