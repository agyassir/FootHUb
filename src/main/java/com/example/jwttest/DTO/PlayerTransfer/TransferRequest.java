package com.example.jwttest.DTO.PlayerTransfer;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class TransferRequest {
    @NotNull(message = "Player ID is required")
    private Long playerId; // ID of the player involved in the transfer

    @NotNull(message = "Club ID is required")
    private Long FromClubId;

    @NotNull(message = "Club ID is required")
    private Long ToClubId;   // ID of the club involved in the transfer

    @Positive(message = "Price must be a positive value")
    private double price;  // Transfer price

    @NotNull(message = "Transfer date is required")
    @PastOrPresent(message = "Transfer date must be in the past or present")
    private Date transferDate; // Date of the transfer

}
